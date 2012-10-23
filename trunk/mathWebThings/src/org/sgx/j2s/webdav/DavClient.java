package org.sgx.j2s.webdav;
/* Low level (subset of) WebDAV client implementation 

Basically what one would expect from a basic DAV client, it
provides a method for every HTTP method used in basic DAV, it
parses PROPFIND requests to handy JS structures and accepts 
similar structures for PROPPATCH.

Requests are handled asynchronously, so instead of waiting until
the response is sent back from the server and returning the
value directly, a handler is registered that is called when
the response is available and the method that sent the request
is ended. For that reason all request methods accept a 'handler'
argument, which will be called (with 3 arguments: statuscode,
statusstring and content (the latter only where appropriate))
when the request is handled by the browser.
The reason for this choice is that Mozilla sometimes freezes
when using XMLHttpRequest for synchronous requests.

The only 'public' methods on the class are the 'initialize'
method, that needs to be called first thing after instantiating
a DavClient object, and the methods that have a name similar to
an HTTP method (GET, PUT, etc.). The latter all get at least a
'path' argument, a 'handler' argument and a 'context' argument:

    'path' - an absolute path to the target resource
    'handler' - a function or method that will be called once
            the request has finished (see below)
    'context' - the context used to call the handler, the
            'this' variable inside methods, so usually the
            object (instance) the handler is bound to (ignore 
            when the handler is a function)

All handlers are called with the same 3 arguments:

    'status' - the HTTP status code
    'statusstring' - a string representation (see STATUS_CODES
            array above) of the status code
    'content' - can be a number of different things:
            * when there was an error in a method that targets
                a single resource, this contains the error body
            * when there was an error in a method that targets
                a set of resources (multi-status) it contains
                a Root object instance (see below) that contains
                the error messages of all the objects
            * if the method was GET and there was no error, it
                will contain the contents of the resource
            * if the method was PROPFIND and there was no error,
                it will contain a Root object (see below) that
                contains the properties of all the resources
                targeted
            * if there was no error and there is no content to
                return, it will contain null
    'headers' - a mapping (associative array) from lowercase header
                name to value (string)

Basic usage example:

    function handler(status, statusstring, content, headers) {
        if (content) {
            if (status != '200' && status != '204') {
                if (status == '207') {
                    alert('not going to show multi-status ' +
                            here...');
                };
                alert('Error: ' + statusstring);
            } else {
                alert('Content: ' + content);
            };
        };
    };

    var dc = new DavClient();
    dc.initialize('localhost');

    // create a directory
    dc.MKCOL('/foo', handler);

    // create a file and save some contents
    dc.PUT('/foo/bar.txt', 'baz?', handler);

    // load and alert it (alert happens in the handler)
    dc.GET('/foo/bar.txt', handler);

    // lock the file, we need to store the lock token from 
    // the result
    function lockhandler(status, statusstring, content, headers) {
        if (status != '200') {
            alert('Error unlocking: ' + statusstring);
        } else {
            window.CURRENT_LOCKTOKEN = headers.locktoken;
        };
    };
    dc.LOCK('/foo/bar.txt', 'http://johnnydebris.net/', 
                lockhandler);

    // run the following bit only if the lock was set properly
    if (window.CURRENT_LOCKTOKEN) {
        // try to delete it: this will fail
        dc.DELETE('/foo/bar.txt', handler);
        
        // now unlock it using the lock token stored above
        dc.UNLOCK('/foo/bar.txt', window.CURRENT_LOCKTOKEN,
                  handler);
    };

    // delete the dir
    dc.DELETE('/foo', handler);

For detailed information about the HTTP methods and how they
can/should be used in a DAV context, see http://www.webdav.org.

This library depends on version 0.3 of the 'dommer' package
and version 0.2 of the 'minisax.js' package, both of which
should be available from http://johnnydebris.net under the
same license as this one (GPL).

If you have questions, bug reports, or patches, please send an 
email to johnny@johnnydebris.net.
*/





/**
 * this is DavClient - a JavaScript WebDAV client library (http://johnnydebris.net/javascript/.)
 * ported to java2script by sgurin. 
 * @author sgurin
 *
 * @j2sPrefix





//jsbase/exception.js

var global = this;
global.exception = new function() {
    var exception = this;
    this.Exception = function(message) {
        if (message !== undefined) {
            this._initialize('Exception', message);
        };
    };
    this.Exception.prototype._initialize = function(name, message) {
        this.name = name;
        this.message = message;
        var stack = this.stack = exception._createStack();
        this.lineNo = exception._getLineNo(stack);
        this.fileName = exception._getFileName(stack);
    };
    this.Exception.prototype.toString = function() {
        var lineNo = this.lineNo;
        var fileName = this.fileName;
        var stack = this.stack;
        var exc = this.name + ': ' + this.message + '\n';
        if (lineNo || fileName || stack) {
            exc += '\n';
        };
        if (fileName) {
            exc += 'file: ' + fileName;
            if (lineNo) {
                exc += ', ';
            } else {
                exc += '\n';
            };
        };
        if (lineNo) {
            exc += 'line: ' + lineNo + '\n';
        };
        if (stack) {
            exc += '\n';
            var lines = stack.split('\n');
            for (var i=0; i < lines.length; i++) {
                var line = lines[i];
                if (line.charAt(0) == '(') {
                    line = 'function' + line;
                };
                exc += line + '\n';
            };
        };
        return exc;
    };
    this.ValueError = function(message) {
        if (message !== undefined) {
            this._initialize('ValueError', message);
        };
    };
    this.ValueError.prototype = new this.Exception;

    this.AssertionError = function(message) {
        if (message !== undefined) {
            this._initialize('AssertionError', message);
        };
    };
    this.AssertionError.prototype = new this.Exception;
    this.NotSupported = function(message) {
        if (message !== undefined) {
            this._initialize('NotSupported', message);
        };
    };

    this.NotSupported.prototype = new this.Exception;
    
    this.NotFound = function(message) {
        if (message !== undefined) {
            this._initialize('NotFound', message);
        };
    };

    this.NotFound.prototype = new this.Exception;

    this.HTTPError = function(status) {
        if (status !== undefined) {
            this._initialize('HTTPError', status);
        };
    };

    this.HTTPError.prototype = new this.Exception;

    this.MissingDependency = function(missing, from) {
        if (missing !== undefined) {
            var message = missing;
            if (from) {
                message += ' (from ' + from + ')';
            };
            this._initialize('MissingDependency', message);
        };
    };
    this.NotFound.prototype = new this.Exception;
    this._createStack = function() {       
    };
    this._getLineNo = function(stack) {
    };
    this._getFileName = function(stack) {
    };
}();












//jsbase/string.js
 * 
var global = this;
global.string = new function() {
    var string = this;

    this.strip = function(s) {
        var stripspace = /^\s*([\s\S]*?)\s*$/;
        return stripspace.exec(s)[1];
    };

    this.reduceWhitespace = function(s) {
        s = s.replace(/\r/g, ' ');
        s = s.replace(/\t/g, ' ');
        s = s.replace(/\n/g, ' ');
        while (s.indexOf('  ') > -1) {
            s = s.replace('  ', ' ');
        };
        return s;
    };

    this.entitize = function(s) {
    	var ret = s.replace(/&/g, '&amp;');
        ret = ret.replace(/"/g, '&quot;');
        ret = ret.replace(/</g, '&lt;');
        ret = ret.replace(/>/g, '&gt;');
        return ret;
    };

    this.deentitize = function(s) {
        var ret = s.replace(/&gt;/g, '>');
        ret = ret.replace(/&lt;/g, '<');
        ret = ret.replace(/&quot;/g, '"');
        ret = ret.replace(/&amp;/g, '&');
        return ret;
    };

    this.urldecode = function(s) {
        var reg = /%([a-fA-F0-9]{2})/g;
        var str = s;
        while (true) {
            var match = reg.exec(str);
            if (!match || !match.length) {
                break;
            };
            var repl = new RegExp(match[0], 'g');
            str = str.replace(repl, 
                    String.fromCharCode(parseInt(match[1], 16)));
        };
        // XXX should we indeed replace these?
        str = str.replace(/\+/g, ' ');
        return str;
    };

    this.urlencode = function(s) {
        return escape(s);
    };

    this.escape = function(s) {
        s = s.replace(/\\/g, '\\\\');
        s = s.replace(/\n/g, '\\\n');
        s = s.replace(/\r/g, '\\\r');
        s = s.replace(/\t/g, '\\\t');
        s = s.replace(/'/g, "\\'");
        s = s.replace(/"/g, '\\"');
        return s;
    };

    this.unescape = function(s) {
        s = s.replace(/\\\n/g, '\n');
        s = s.replace(/\\\r/g, '\r');
        s = s.replace(/\\\t/g, '\t');
        s = s.replace(/\\'/g, '\'');
        s = s.replace(/\\"/g, '"');
        s = s.replace(/\\\\/g, '\\');
        return s;
    };

    this.centerTruncate = function(s, maxlength) {
        if (s.length <= maxlength) {
            return s;
        };
        var chunklength = maxlength / 2 - 3;
        var start = s.substr(0, chunklength);
        var end = s.substr(s.length - chunklength);
        return start + ' ... ' + end;
    };

    this.startsWith = function(s, start) {
        return s.substr(0, start.length) == start;
    };

    this.endsWith = function(s, end) {
        return s.substr(s.length - end.length) == end;
    };
    
    this.format = function(s, indent, maxwidth) {
        if (indent.length > maxwidth) {
            throw('Size of indent must be smaller than maxwidth');
        };
        s = string.reduceWhitespace(s);
        var words = s.split(' ');
        var lines = [];
        while (words.length) {
            var currline = indent;
            while (1) {
                var word = words.shift();
                if (!word || 
                        (currline.length > indent.length && 
                            currline.length + word.length > maxwidth)) {
                    break;
                };
                currline += word + ' ';
            };
            lines.push(currline);
        };
        return lines.join('\r\n');
    };
}();










//jsbase/array.js
 * 
var global = this;
global.array = new function() {
    var array = this;    
    this.contains = function(a, element, objectequality) {
        return (this.indexOf(a, element, !objectequality) > -1);
    };
    this.indexOf = function(a, element, compareValues) {
        for (var i=0; i < a.length; i++) {
            if (!compareValues) {
                if (element === a[i]) {
                    return i;
                };
            } else {
                if (element == a[i]) {
                    return i;
                };
            };
        };
        return -1;
    };
    this.removeDoubles = function(a) {
        var ret = [];
        for (var i=0; i < a.length; i++) {
            if (!this.contains(ret, a[i])) {
                ret.push(a[i]);
            };
        };
        return ret;
    };
    this.map = function(a, func) {
        for (var i=0; i < a.length; i++) {
            a[i] = func(a[i]);
        };
        return this;
    };

    this.reversed = function(a) {
        var ret = [];
        for (var i = a.length; i > 0; i--) {
            ret.push(a[i - 1]);
        };
        return ret;
    };
    this.StopIteration = function(message) {
        if (message !== undefined) {
            this._initialize('StopIteration', message);
        };
    };
    if (global.exception) {
        StopIteration.prototype = global.exception.Exception;
    };
    var Iterator = this.Iterator = function(a) {
        if (a) {
            this._initialize(a);
        };
    };
    Iterator._initialize = function(a) {
        this._array = a;
        this._i = 0;
    };
    Iterator.next = function() {
        if (this._i >= this._array.length) {
            this._i = 0;
            throw(StopIteration('no more items'));
        };
        return this._i++;
    };
    this.iterate = function(a) {
        if (!a.__iter_index__) {
            a.__iter_index__ = 0;
        } else if (a.__iter_index__ >= a.length) {
            a.__iter_index__ = undefined;
            return undefined;
        };
        return a[a.__iter_index__++];
    };
}();








// minisax/minisax.js
 

function SAXParser() {
};

SAXParser.prototype.initialize = function(xml, handler) { 
    this.xml = xml;
    this.handler = handler;
    this.handler.namespaceToPrefix = {};

    this.starttagreg = /\<([^: \t\n]+:)?([a-zA-Z0-9\-_]+)([^\>]*?)(\/?)\>/m;
    this.endtagreg = /\<\/([^: \t\n]+:)?([a-zA-Z0-9\-_]+)[^\>]*\>/m;
    this.attrstringreg = /(([^:=]+:)?[^=]+=\"[^\"]*\")/m;
    this.attrreg = /([^=]*)=\"([^\"]*)\"/m;

    this._namespace_stack = [];

    this._current_nodename_stack = [];
    this._current_namespace_stack = [];
};

SAXParser.prototype.parse = function() {
    var xml = this._removeXMLdeclaration(this.xml);
    this.handler.startDocument();
    while (1) {
        var chunk = this._getNextChunk(xml);
        if (chunk == '') {
            break;
        };
        xml = xml.substr(chunk.length);
        if (chunk.charAt(0) == '<') {
            if (chunk.charAt(1) == '/') {
                this.handleEndTag(chunk);
                this._namespace_stack.pop();
            } else if (chunk.charAt(1) == '!') {
                chunk = string.deentitize(chunk);
                if (!chunk.indexOf('-->') == chunk.length - 3) {
                    var more = xml.substr(0, xml.indexOf('-->'));
                    xml = xml.substr(more.length);
                    chunk += more;
                };
                chunk = chunk.substr(4, chunk.length - 7);
                this.handler.comment(chunk);
            } else {
                var singleton = false;
                if (chunk.charAt(chunk.length - 2) == '/') {
                    singleton = true;
                };
                this._pushNamespacesToStack();
                this.handleStartTag(chunk, singleton);
                if (singleton) {
                    this._namespace_stack.pop();
                };
            };
        } else {
            chunk = string.deentitize(chunk);
            this.handler.characters(chunk);
        };
    };
    this.handler.endDocument();
};

SAXParser.prototype.handleStartTag = function(tag, is_singleton) {
    var match = this.starttagreg.exec(tag);
    if (!match) {
        throw('Broken start tag: ' + tag);
    };
    
    var prefix = match[1];
    var nodename = match[2];
    if (prefix) {
        prefix = prefix.substr(0, prefix.length - 1);
    } else {
        prefix = '';
    };
    
    var attrs = this._splitAttributes(match[3]);
    attrs = this._getAndHandleNamespaceDeclarations(attrs);
    
    var attributes = {};
    for (var i=0; i < attrs.length; i++) {
        this.handleAttribute(attrs[i], attributes);
    };
    
    var namespace = this._namespace_stack[
                    this._namespace_stack.length - 1
                ][prefix];

    this.handler.startElement(namespace, nodename, attributes);
    if (is_singleton) {
        this.handler.endElement(namespace, nodename);
    } else {
        this._current_nodename_stack.push(nodename);
        this._current_namespace_stack.push(namespace);
    };
};

SAXParser.prototype.handleEndTag = function(tag) {
    var match = this.endtagreg.exec(tag);
    if (!match) {
        throw('Broken end tag: ' + tag);
    };
    var prefix = match[1];
    var nodename = match[2];
    if (prefix) {
        prefix = prefix.substr(0, prefix.length - 1);
    } else {
        prefix = '';
    };
    namespace = this._namespace_stack[
                        this._namespace_stack.length - 1
                    ][prefix];

    var current_nodename = this._current_nodename_stack.pop();
    var current_namespace = this._current_namespace_stack.pop();
    if (nodename != current_nodename || 
            namespace != current_namespace) {
        var exc = 'Ending ';
        if (namespace != '') {
            exc += namespace + ':';
        };
        exc += nodename + ' doesn\'t match opening ';
        if (current_namespace != '') {
            exc += current_namespace + ':';
        };
        exc += current_nodename;
        throw(exc); 
    }
    this.handler.endElement(namespace, nodename);
};

SAXParser.prototype.handleAttribute = function(attr, attributemapping) {
    var match = this.attrreg.exec(attr);
    if (!match) {
        throw('Broken attribute: ' + attr);
    };
    var prefix = '';
    var name = match[1];
    var lname = match[1];
    var value = string.deentitize(match[2]);
    if (name.indexOf(':') > -1) {
        var tuple = name.split(':');
        prefix = tuple[0];
        lname = tuple[1];
    };
    var namespace = '';
    if (prefix == 'xml') {
        namespace = 'http://www.w3.org/XML/1998/namespace';
        if (!this.handler.namespaceToPrefix[namespace]) {
            this.handler.namespaceToPrefix[namespace] = prefix;
        };
    } else if (prefix != '') {
        namespace = this._namespace_stack[
                            this._namespace_stack.length - 1
                        ][prefix];
    };
    if (!attributemapping[namespace]) {
        attributemapping[namespace] = {};
    };
    attributemapping[namespace][lname] = value;
};

SAXParser.prototype._removeXMLdeclaration = function(xml) {
    var declreg = /\<\?[^>]*\?\>/g;
    xml = xml.replace(declreg, '');
    return xml;
};

SAXParser.prototype._getNextChunk = function(xml) {
    if (xml.charAt(0) == '<') {
        return xml.substr(0, xml.indexOf('>') + 1);
    } else {
        return xml.substr(0, xml.indexOf('<'));
    };
};

SAXParser.prototype._splitAttributes = function(attrstring) {
    var attrs = string.strip(attrstring);
    var attrlist = [];
    while (1) {
        var match = this.attrstringreg.exec(attrstring);
        if (!match) {
            break;
        };
        attrlist.push(string.strip(match[1]));
        attrstring = attrstring.replace(match[0], '');
    };
    return attrlist;
};
SAXParser.prototype._getAndHandleNamespaceDeclarations = function(attrarray) {
    var leftover = [];
    for (var i=0; i < attrarray.length; i++) {
        var attr = attrarray[i];
        var match = this.attrreg.exec(attr);
        if (!match) {
            throw('Broken attribute: ' + attr);
        };
        if (match[1].indexOf('xmlns') == -1) {
            leftover.push(attr);
            continue;
        };
        var nsname = match[1];
        var value = string.deentitize(match[2]);
        if (nsname.indexOf(':') > -1) {
            nsname = nsname.split(':')[1];
            this._registerNamespace(value, nsname);
        } else {
            this._registerNamespace(value);
        };
    };
    return leftover;
};
SAXParser.prototype._registerNamespace = function(namespace, prefix) {
    if (!prefix) {
        prefix = '';
    };
    if (!this.handler.namespaceToPrefix[namespace]) {
        this.handler.namespaceToPrefix[namespace] = prefix;
    };
    this._namespace_stack[this._namespace_stack.length - 1][prefix] = 
                                                            namespace;
};
SAXParser.prototype._pushNamespacesToStack = function() {
    var newnss = {};
    for (var prefix in this._namespace_stack[this._namespace_stack.length - 1]) {
        newnss[prefix] = this._namespace_stack[
                                this._namespace_stack.length - 1
                            ][prefix];
    };
    this._namespace_stack.push(newnss);
};
function SAXHandler() {};
SAXHandler.prototype.startDocument = function() {};
SAXHandler.prototype.startElement = function(namespaceURI, nodeName,     attributes) {};
SAXHandler.prototype.endElement = function(namespaceURI, nodeName) {};
SAXHandler.prototype.characters = function(chars) {};
SAXHandler.prototype.comment = function(comment) {};
SAXHandler.prototype.endDocument = function() {};











// dommer/dommer.js

var WARN_ON_PREFIX = true;

// give this a namespace...
try {
    var global = window;
} catch(e) {
    var global = this;
};
global.dommer = new function() {
    function DOMException(errorcode, message) {
        this.code = null;
        this.error = null;
        this.message = message
        for (var attr in DOMException) {
            if (DOMException[attr] == errorcode) {
                this.error = attr;
                break;
            };
        };
        this.code = errorcode;
        if (!this.error) {
            this.error = 'Unknown';
        };
        this.stack = stack = createStack();
        this.lineNumber = getLineNo(stack);
        this.fileName = getFileName(stack);
    };

    this.DOMException = DOMException;

    // error codes
    // XXX should we make these global, like in the specs?
    DOMException.INDEX_SIZE_ERR = 1,
    DOMException.DOMSTRING_SIZE_ERR = 2;
    DOMException.HIERARCHY_REQUEST_ERR = 3;
    DOMException.WRONG_DOCUMENT_ERR = 4;
    DOMException.INVALID_CHARACTER_ERR = 5;
    DOMException.NO_DATA_ALLOWED_ERR = 6;
    DOMException.NO_MODIFICATION_ALLOWED_ERR = 7;
    DOMException.NOT_FOUND_ERR = 8;
    DOMException.NOT_SUPPORTED_ERR = 9;
    DOMException.INUSE_ATTRIBUTE_ERR = 10;
    DOMException.INVALID_STATE_ERR = 11;
    DOMException.SYNTAX_ERR = 12;
    DOMException.INVALID_MODIFICATION_ERR = 13;
    DOMException.NAMESPACE_ERR = 14;
    DOMException.INVALID_ACCESS_ERR = 15;

    DOMException.prototype.toString = function() {
        var ret = 'DOMException: ' + this.error + ' (' + this.code + ')';
        if (this.message) {
            ret += ' - ' + this.message;
        };
        return ret;
    };

    function Node() {
        this.ELEMENT_NODE = 1;
        this.ATTRIBUTE_NODE = 2;
        this.TEXT_NODE = 3;
        this.CDATA_SECTION_NODE = 4;
        this.ENTITY_REFERENCE_NODE = 5;
        this.ENTITY_NODE = 6;
        this.PROCESSING_INSTRUCTION_NODE = 7;
        this.COMMENT_NODE = 8;
        this.DOCUMENT_NODE = 9;
        this.DOCUMENT_TYPE_NODE = 10;
        this.DOCUMENT_FRAGMENT_NODE = 11;
        this.NOTATION_NODE = 12;
        
        // These are defined in-line rather than on .prototype to allow using
        // them below, too. This way we don't have to check whether attributes
        // are already protected while this constructor is ran or not (in JS,
        // when you set 'Foo.prototype = new Bar;', the Bar constructor is
        // actually ran, in our case this means that the state of the 
        // superclass changes).
        this._protectAttribute = function(attr) {
            this.__defineSetter__(attr,
                function(value) {
                    throw(
                        (new DOMException(
                            DOMException.NO_MODIFICATION_ALLOWED_ERR, attr))
                    );
                }
            );
            this.__defineGetter__(attr,
                function() {
                    return this['_' + attr];
                }
            );
        };

        this._setProtected = function(name, value) {
            this['_' + name] = value;
            if (!this.__defineSetter__) {
                this[name] = value;
            };
        };

        this.nodeValue = null;
        if (this.__defineSetter__) {
            // on browsers that support __define[GS]etter__, perform integrity
            // checks
            // nodeValue should be settable on certain nodeTypes
            this.__defineSetter__('nodeValue',
                function(nodeValue) {
                    if (this.nodeType != this.TEXT_NODE &&
                            this.nodeType != this.ATTRIBUTE_NODE && 
                            this.nodeType != this.COMMENT_NODE) {
                        throw(
                            (new DOMException(
                                DOMException.NO_DATA_ALLOWED_ERR,
                                'nodeValue'))
                        );
                    };
                    // XXX should check on allowed chars here, but not 
                    // sure which?
                    this._nodeValue = nodeValue;
                }
            );
            // XXX not sure if we should protect reading .nodeValue
            this.__defineGetter__('nodeValue',
                function() {
                    if (this.nodeType != this.TEXT_NODE &&
                            this.nodeType != this.ATTRIBUTE_NODE &&
                            this.nodeType != this.COMMENT_NODE) {
                        throw(
                            (new DOMException(
                                DOMException.NO_DATA_ALLOWED_ERR,
                                'nodeValue'))
                        );
                    };
                    return this._nodeValue;
                }
            );
            var toprotect = ['nodeType', 'nodeName', 'parentNode', 
                                'childNodes', 'firstChild', 'lastChild', 
                                'previousSibling', 'nextSibling', 
                                'attributes', 'ownerDocument', 'namespaceURI', 
                                'localName'];
            for (var i=0; i < toprotect.length; i++) {
                this._protectAttribute(toprotect[i]);
            };
        };
            
        this._setProtected('namespaceURI', null);
        this._setProtected('prefix', null);
        this._setProtected('nodeName', null);
        this._setProtected('localName', null);
        this._setProtected('parentNode', null);
        // note that this is shared between subclass instances, so should be
        // re-set in every .initialize() (so below is just for show)
        this._setProtected('childNodes', []);
        this._setProtected('firstChild', null);
        this._setProtected('lastChild', null);
        this._setProtected('previousSibling', null);
        this._setProtected('nextSibling', null);
        this._setProtected('ownerDocument', null);
    };

    this.Node = Node;

    var thrownotsupported = function() {throw('not supported');};

    // XXX these should be implemented at some point...
    Node.prototype.normalize = thrownotsupported;
    Node.prototype.isSupported = thrownotsupported; // hehehe...

    // non-standard method, use this always instead of setting .prefix 
    // yourself, as this will update the .nodeName property too
    Node.prototype.setPrefix = function(prefix) {
        if (this.__defineSetter__) {
            this._prefix = prefix;
            this._nodeName = prefix + ':' + this.localName;
        } else {
            this.prefix = prefix;
            this.nodeName = prefix + ':' + this.localName;
        };
    };

    Node.prototype.cloneNode = function() {
        throw(
            (new DOMException(DOMException.NOT_SUPPORTED_ERR))
        );
    };

    Node.prototype.hasChildNodes = function() {
        return (this.childNodes && this.childNodes.length > 0);
    };

    Node.prototype.hasAttributes = function() {
        return (this.attributes !== undefined && this.attributes.length);
    };

    Node.prototype.appendChild = function(newChild) {
        this._checkModificationAllowed();
        this._attach(newChild);
    };

    Node.prototype.removeChild = function(oldChild) {
        this._checkModificationAllowed();
        this._checkIsChild(oldChild);
        var newChildren = new NodeList();
        var found = false;
        for (var i=0; i < this.childNodes.length; i++) {
            if (this.childNodes[i] === oldChild) {
                oldChild._setProtected('parentNode', null);
                var previous = oldChild.previousSibling;
                if (previous) {
                    oldChild._setProtected('previousSibling', null);
                    previous._setProtected('nextSibling', 
                        oldChild.nextSibling);
                };
                var next = oldChild.nextSibling;
                if (next) {
                    next._setProtected('previousSibling', previous);
                    oldChild._setProtected('nextSibling', null);
                };
                continue;
            };
            newChildren.push(this.childNodes[i]);
        };
        this._setProtected('childNodes', newChildren);
        this._setProtected('firstChild', 
                (this.childNodes.length > 0 ? this.childNodes[0] : null));
        this._setProtected('lastChild', (
                this.childNodes.length > 0 ? 
                    this.childNodes[this.childNodes.length - 1] : null));
    };

    Node.prototype.replaceChild = function(newChild, refChild) {
        this._checkModificationAllowed();
        this._checkIsChild(refChild);
        this._attach(newChild, refChild, true);
    };

    Node.prototype.insertBefore = function(newChild, refChild) {
        this._checkModificationAllowed();
        this._checkIsChild(refChild);
        this._attach(newChild, refChild);
    };

    Node.prototype._attach = function(newChild, refChild, replace) {
        // see if the child is in the same document
        if (newChild.ownerDocument != this.ownerDocument) {
            throw(
                (new DOMException(DOMException.WRONG_DOCUMENT_ERR))
            );
        };
        // see if the child is of an allowed type
        if (newChild.nodeType != newChild.ELEMENT_NODE && 
                newChild.nodeType != newChild.TEXT_NODE &&
                newChild.nodeType != newChild.CDATA_SECTION_NODE &&
                newChild.nodeType != newChild.COMMENT_NODE) {
            throw(
                (new DOMException(DOMException.HIERARCHY_REQUEST_ERR))
            );
        };
        // see if the child isn't a (grand)parent of ourselves
        var currparent = this;
        while (currparent && currparent.nodeType != newChild.DOCUMENT_NODE) {
            if (currparent === newChild) {
                throw(
                    (new DOMException(DOMException.HIERARCHY_REQUEST_ERR))
                );
            };
            currparent = currparent.parentNode;
        };
        // seems to be okay, add it
        newChild._setProtected('parentNode', this);
        if (!refChild) {
            if (this.childNodes.length) {
                this.childNodes[this.childNodes.length - 1]._setProtected(
                    'nextSibling', newChild);
                newChild._setProtected('previousSibling',
                    this.childNodes[this.childNodes.length - 1]);
            };
            this.childNodes.push(newChild);
        } else {
            var newchildren = [];
            var found = false;
            for (var i=0; i < this.childNodes.length; i++) {
                var currChild = this.childNodes[i];
                if (currChild === refChild) {
                    newchildren.push(newChild);
                    var previous = this.childNodes[i - 1];
                    if (previous) {
                        newChild._setProtected('previousSibling', previous);
                        previous._setProtected('nextSibling', newChild);
                    };
                    if (!replace) {
                        newchildren.push(currChild);
                        currChild._setProtected('previousSibling', newChild);
                        newChild._setProtected('nextSibling', currChild);
                    } else {
                        currChild._setProtected('parentNode', null);
                        currChild._setProtected('previousSibling', null);
                        currChild._setProtected('nextSibling', null);
                        var next = this.childNodes[i + 1];
                        newChild._setProtected('nextSibling', next);
                        next._setProtected('previousSibling', newChild);
                    };
                    found = true;
                } else {
                    newchildren.push(currChild);
                };
            };
            if (!found) {
                throw(
                    (new DOMException(DOMException.NOT_FOUND_ERR))
                );
            };
            this._setProtected('childNodes', newchildren);
        };
        this._setProtected('firstChild', this.childNodes[0]);
        this._setProtected('lastChild', 
            this.childNodes[this.childNodes.length - 1]);
    };

    Node.prototype._checkModificationAllowed = function() {
        if (this.nodeType != this.ELEMENT_NODE &&
                this.nodeType != this.DOCUMENT_NODE &&
                this.nodeType != this.DOCUMENT_FRAGMENT_NODE) {
            throw(
                (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR))
            );
        };
    };

    Node.prototype._checkIsChild = function(refChild) {
        if (refChild.parentNode !== this) {
            throw(
                (new DOMException(DOMException.NOT_FOUND_ERR))
            );
        };
    };

    function DocumentFragment() {
        this._setProtected('nodeType', 11);
    };

    DocumentFragment.prototype = new Node;
    this.DocumentFragment = DocumentFragment;

    function Element() {
        this._setProtected('nodeType', 1);
    };

    Element.prototype = new Node;
    this.Element = Element;

    Element.prototype.initialize = function(namespaceURI, qname, 
                                                    ownerDocument) {
        // XXX the specs are very vague about an id, it says the DOM 
        // implementation must have info about which attributes are of the id 
        // type, I'll just use the property here for now...
        this.id = ''; // empty string like in Mozilla, seems weird to me though

        this._setProtected('attributes', []);
        this._setProtected('childNodes', []);
        this._setProtected('ownerDocument', ownerDocument);

        // try to ensure integrity by defining getters and setters for certain
        // properties, since this only works in certain browsers it makes sense to 
        // test your applications on one of those platforms, see also 
        // WARN_ON_PREFIX in the top of the document
        if (this.__defineSetter__) {
            this._nodeName = this.nodeName;
            this.__defineSetter__('nodeName', function() {
                            throw(
                                (new DOMException(
                                    DOMException.NO_MODIFICATION_ALLOWED_ERR)))
                            });
            this.__defineGetter__('nodeName', 
                                    function() {return this._nodeName});
            this.__defineSetter__('prefix', 
                                function(value) {
                                    if (WARN_ON_PREFIX) {
                                        throw('Setting prefix directly ' +
                                                'breaks integrity of the ' +
                                                'XML DOM in Internet ' +
                                                'Explorer browsers!');
                                    };
                                    this._prefix = value;
                                    this._nodeName = this._prefix + 
                                                        this._localName;
                                });
            this.__defineGetter__('prefix', function() {return this._prefix});
        };
        // XXX both the ns and qname need integrity checks
        this._setProtected('namespaceURI', namespaceURI);
        if (qname.indexOf(':') > -1) {
            var tup = qname.split(':');
            this.setPrefix(tup.shift());
            this._setProtected('localName', tup.join(':'));
        } else {
            this.setPrefix(null);
            this._setProtected('localName', qname);
        };
        if (this.prefix) {
            this._setProtected('nodeName', this.prefix + ':' + this.localName);
        } else {
            this._setProtected('nodeName', this.localName);
        };
    };

    Element.prototype.toString = function() {
        return '<Element "' + this.nodeName + '" (type ' + 
                    this.nodeType + ')>';
    };

    Element.prototype.toXML = function(context) {
        // context is used when toXML is called recursively
        // marker
        var no_prefix_id = '::no_prefix::';
        if (!context) {
            context = {
                namespace_stack: []
            };
        };
        var new_namespaces = {}; // any namespaces that weren't declared yet
        var current_namespaces = {};
        var last_namespaces = context.namespace_stack[
                                    context.namespace_stack.length - 1];
        context.namespace_stack.push(current_namespaces);
        if (last_namespaces) {
            for (var prefix in last_namespaces) {
                current_namespaces[prefix] = last_namespaces[prefix];
            };
        };
        var xml = '<' + this.nodeName;
        var prefix = this.prefix || no_prefix_id;
        if (this.namespaceURI && 
                (current_namespaces[prefix] != this.namespaceURI)) {
            current_namespaces[prefix] = this.namespaceURI;
            new_namespaces[prefix] = this.namespaceURI;
        };
        for (var i=0; i < this.attributes.length; i++) {
            var attr = this.attributes[i];
            var aprefix = attr.prefix || no_prefix_id;
            if (attr.namespaceURI &&
                    current_namespaces[aprefix] != attr.namespaceURI) {
                current_namespaces[aprefix] = attr.namespaceURI;
                new_namespaces[aprefix] = attr.namespaceURI;
            };
            xml += ' ' + attr.nodeName + '="' + 
                    string.entitize(attr.nodeValue) + '"';
        };

        // take care of any new namespaces
        for (var prefix in new_namespaces) {
            xml += ' xmlns';
            if (prefix != no_prefix_id) {
                xml += ':' + prefix;
            };
            xml += '="' + string.entitize(new_namespaces[prefix]) + '"';
        };
        
        if (this.childNodes.length) {
            xml += '>';
            for (var i=0; i < this.childNodes.length; i++) {
                xml += this.childNodes[i].toXML(context);
            };
            xml += '</' + this.nodeName + '>';
        } else {
            xml += ' />';
        };
        context.namespace_stack.pop();
        return xml;
    };

    Element.prototype.cloneNode = function(deep) {
        var el = new Element();
        el.initialize(this.namespaceURI, this.nodeName, this.ownerDocument);
        for (var i=0; i < this.attributes.length; i++) {
            var clone = this.attributes[i].cloneNode();
            clone._setProtected('ownerElement', el);
            el.attributes.push(clone);
        };
        if (deep) {
            for (var i=0; i < this.childNodes.length; i++) {
                var clone = this.childNodes[i].cloneNode(true);
                clone._setProtected('parentNode', el);
                el.appendChild(clone);
            };
        };
        return el;
    };

    Element.prototype.getAttributeNodeNS = function(namespaceURI, qname) {
        for (var i=0; i < this.attributes.length; i++) {
            var attr = this.attributes[i];
            if (attr.namespaceURI == namespaceURI && attr.nodeName == qname) {
                return attr;
            };
        };
    };

    Element.prototype.getAttributeNode = function(name) {
        return this.getAttributeNodeNS(undefined, name);
    };

    Element.prototype.getAttribute = function(name) {
        var attr = this.getAttributeNode(name)
        return (attr ? attr.nodeValue : null);
    };

    Element.prototype.getAttributeNS = function(namespaceURI, name) {
        var attr = this.getAttributeNodeNS(namespaceURI, name);
        return (attr ? attr.nodeValue : null);
    };

    Element.prototype.hasAttributeNS = function(namespaceURI, name) {
        return !!(this.getAttributeNS(namespaceURI, name));
    };

    Element.prototype.hasAttribute = function(name) {
        return this.hasAttributeNS(this.namespaceURI, name);
    };

    Element.prototype.setAttributeNS = function(namespaceURI, name, value) {
        for (var i=0; i < this.attributes.length; i++) {
            var attr = this.attributes[i];
            if (attr.namespaceURI == namespaceURI && attr.nodeName == name) {
                attr.nodeValue = value;
                return;
            };
        };
        var attr = new Attribute();
        attr.initialize(namespaceURI, name, value, this.ownerDocument);
        attr._setProtected('ownerElement', this);
        this.attributes.push(attr);
    };

    Element.prototype.setAttribute = function(name, value) {
        this.setAttributeNS(undefined, name, value);
    };

    Element.prototype.setAttributeNodeNS = function(newAttr) {
        for (var i=0; i < this.attributes.length; i++) {
            var attr = this.attributes[i];
            if (attr.namespaceURI == newAttr.namespaceURI && 
                    attr.nodeName == newAttr.nodeName) {
                throw(
                    (new DOMException(DOMException.INUSE_ATTRIBUTE_ERR))
                );
            };
        };
        this.attributes.push(newAttr);
    };

    Element.prototype.setAttributeNode = function(newAttr) {
        this.setAttributeNodeNS(newAttr);
    };

    Element.prototype.removeAttributeNS = function(namespaceURI, name) {
        for (var i=0; i < this.attributes.length; i++) {
            var attr = this.attributes[i];
            if (attr.namespaceURI == namespaceURI && attr.nodeName == name) {
                delete this.attributes[i];
                return true;
            };
        };
        return false;
    };

    Element.prototype.removeAttribute = function(name) {
        return this.removeAttributeNS(this.namespaceURI, name);
    };

    Element.prototype.getElementsByTagNameNS = function(namespaceURI, 
                                                                name, ret) {
        if (!ret) {
            ret = [];
        };
        for (var i=0; i < this.childNodes.length; i++) {
            var child = this.childNodes[i];
            if (name == child.nodeName || name == '*') {
                if ((!namespaceURI && !child.namespaceURI) || 
                        (namespaceURI == child.namespaceURI)) {
                    ret.push(child);
                };
            };
            if (child.nodeType == 1) {
                child.getElementsByTagNameNS(namespaceURI, name, ret);
            };
        };
        return ret;
    };

    Element.prototype.getElementsByTagName = function(name) {
        return this.getElementsByTagNameNS(this.namespaceURI, name);
    };

    Element.prototype.getElementById = function(id) {
        if (this.id == id) {
            return this;
        };
        for (var i=0; i < this.childNodes.length; i++) {
            var child = this.childNodes[i];
            if (child.id == id) {
                return child;
            };
            if (child.nodeType == 1) {
                var found = this.childNodes[i].getElementById(id);
                if (found) {
                    return found;
                };
            };
        };
    };

    function TextNode() {
        this._setProtected('nodeType', 3);
        this._setProtected('nodeName', '#text');
    };

    TextNode.prototype = new Node;
    this.TextNode = TextNode;

    TextNode.prototype.initialize = function(data, ownerDocument) {
        this._setProtected('ownerDocument', ownerDocument);
        this._setProtected('childNodes', new NodeList());
        this.nodeValue = data;
    };

    TextNode.prototype.toXML = function() {
        return string.entitize(this.nodeValue);
    };

    TextNode.prototype.cloneNode = function() {
        var node = new TextNode();
        node.initialize(this.nodeValue, this.ownerDocument);
        return node;
    };

    function CommentNode() {
        this._setProtected('nodeType', 8);
        this._setProtected('nodeName', '#comment');
    };

    CommentNode.prototype = new TextNode;
    this.CommentNode = CommentNode;

    CommentNode.prototype.initialize = function(data, ownerDocument) {
        this._setProtected('ownerDocument', ownerDocument);
        this._setProtected('childNodes', []);
        this._setProtected('nodeValue', data);
    };

    CommentNode.prototype.toXML = function() {
        return "<!--" + this.nodeValue + "-->";
    };

    function Attribute() {
        this._setProtected('nodeType', 2);
    };

    Attribute.prototype = new Node;
    this.Attribute = Attribute;

    Attribute.prototype.initialize = function(namespaceURI, qname, value,
                                                    ownerDocument) {
        // XXX some code duplication here...
        if (qname.match(/[^a-zA-Z0-9_\-:]/g)) {
            throw(
                (new DOMException(DOMException.INVALID_CHARACTER_ERR))
            );
        };
        this._setProtected('ownerDocument', ownerDocument);
        this._setProtected('namespaceURI', namespaceURI);
        this._setProtected('nodeValue', value);
        this._setProtected('childNodes', []);

        // try to ensure integrity by defining getters and setters for certain
        // properties, since this only works in certain browsers it makes sense to 
        // test your applications on one of those platforms, see also 
        // WARN_ON_PREFIX in the top of the document
        if (this.__defineSetter__) {
            this._nodeName = this.nodeName;
            this.__defineSetter__('nodeName', function() {
                            throw(
                                (new DOMException(
                                    DOMException.NO_MODIFICATION_ALLOWED_ERR)))
                            });
            this.__defineGetter__('nodeName', 
                                    function() {return this._nodeName});
            this.__defineSetter__('prefix', 
                                function(value) {
                                    if (WARN_ON_PREFIX) {
                                        throw('Setting prefix directly ' +
                                                'breaks integrity of the ' +
                                                'XML DOM in Internet ' +
                                                'Explorer browsers!');
                                    };
                                    this._prefix = value;
                                    this._nodeName = this._prefix + 
                                                        this._localName;
                                });
            this.__defineGetter__('prefix', function() {return this._prefix});
            this._protectAttribute('ownerElement');
        };
        this._setProtected('ownerElement', null);
        if (qname.indexOf(':') > -1) {
            var tup = qname.split(':');
            this.setPrefix(tup.shift());
            this._setProtected('localName', tup.join(':'));
        } else {
            this.setPrefix(null);
            this._setProtected('localName', qname);
        };
        if (this.prefix) {
            this._setProtected('nodeName', this.prefix + ':' + this.localName);
        } else {
            this._setProtected('nodeName', this.localName);
        };
    };

    Attribute.prototype.toXML = function() {
        ret = this.nodeName + '="' + string.entitize(this.nodeValue) + '"';
        return ret;
    };

    Attribute.prototype.cloneNode = function() {
        var attr = new Attribute();
        attr.initialize(this.namespaceURI, this.nodeName, this.nodeValue, 
                        this.ownerDocument);
        return attr;
    };

    Attribute.prototype.toString = function() {
        return this.nodeValue;
    };

    function Document() {
        this._setProtected('nodeType', 9);
        this._setProtected('nodeName', '#document');
    };

    Document.prototype = new Node;
    this.Document = Document;

    Document.prototype.initialize = function() {
        this._setProtected('ownerDocument', this);
        this._setProtected('childNodes', []);
        this.documentElement = null;
        this.namespaceToPrefix = {};
    };

    Document.prototype.toXML = function() {
        return this.documentElement.toXML();
    };

    Document.prototype.appendChild = function(newChild) {
        if (this.documentElement) {
            throw(
                (new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
                    'document already has a document element'))
            );
        };
        this._checkModificationAllowed();
        this._attach(newChild);
        this.documentElement = newChild;
    };


    Document.prototype.createElement = function(nodeName) {
        return this.createElementNS(this.namespaceURI, nodeName);
    };

    Document.prototype.createElementNS = function(namespaceURI, nodeName) {
        var el = new Element();
        el.initialize(namespaceURI, nodeName, this);
        return el;
    };

    Document.prototype.createTextNode = function(data) {
        var el = new TextNode();
        el.initialize(string.deentitize(data), this);
        return el;
    };

    Document.prototype.createAttributeNS = function(namespaceURI, nodeName) {
        var el = new Attribute();
        el.initialize(namespaceURI, nodeName, null, this);
        return el;
    };

    Document.prototype.createAttribute = function(nodeName) {
        return this.createAttributeNS(undefined, nodeName);
    };

    Document.prototype.createComment = function(data) {
        var el = new CommentNode();
        el.initialize(data, this);
        return el;
    };

    Document.prototype.importNode = function(node) {
        node._setProtected('ownerDocument', this);
    };

    function DOMHandler() {
    };

    this.DOMHandler = DOMHandler;

    DOMHandler.prototype.startDocument = function() {
        this.document = new Document();
        this.document.initialize();
        this.current = null;
        this.namespaces = new Array();
        this.namespaceToPrefix = {};
    };

    DOMHandler.prototype.startElement = function(namespaceURI, nodename, 
                                                        attrs) {
        if (namespaceURI && !array.contains(this.namespaces, namespaceURI)) {
            this.namespaces.push(namespaceURI);
            this.document.namespaceToPrefix = this.namespaceToPrefix;
        };
        var node = this.document.createElementNS(namespaceURI, nodename);
        var prefix = undefined;
        if (namespaceURI) {
            prefix = this.namespaceToPrefix[namespaceURI];
            if (prefix) {
                node.setPrefix(prefix);
            };
        };
        for (var ans in attrs) {
            if (ans && ans != '' && !array.contains(this.namespaces, ans)) {
                this.namespaces.push(ans);
            };
            var nsattrs = attrs[ans];
            for (var aname in nsattrs) {
                if (aname == 'prefix') {
                    continue;
                };
                if (ans) {
                    var attr = this.document.createAttributeNS(ans, aname);
                    attr.setPrefix(this.namespaceToPrefix[ans]);
                    attr.nodeValue = nsattrs[aname];
                    node.setAttributeNodeNS(attr);
                } else {
                    var attr = this.document.createAttribute(aname);
                    attr.nodeValue = nsattrs[aname];
                    node.setAttributeNode(attr);
                };
            };
        };
        if (!this.current) {
            this.document.documentElement = node;
            this.document._setProtected('childNodes', [node]);
            this.current = node;
            this.current._setProtected('parentNode', this.document);
            this.current._setProtected('ownerDocument', this.document);
        } else {
            this.current.appendChild(node);
            this.current = node;
        };
    };

    DOMHandler.prototype.characters = function(data) {
        if (!this.current && string.strip(data) == '') {
            return;
        };
        var node = this.document.createTextNode(data);
        this.current.appendChild(node);
    };

    DOMHandler.prototype.comment = function(data) {
        if (!this.current && string.strip(data) == '') {
            return;
        };
        var node = this.document.createComment(data);
        if (this.current) {
            this.current.appendChild(node);
        } else {
            this.document.comment = node;
        };
    };

    DOMHandler.prototype.endElement = function(namespaceURI, nodename) {
        var prefix = this.namespaceToPrefix[namespaceURI];
        if (nodename != this.current.localName || 
                namespaceURI != this.current.namespaceURI) {
            throw('non-matching end tag ' + namespaceURI + ':' + 
                    prefix + ':' + nodename + ' for start tag ' + 
                    this.current.namespaceURI + ':' + this.current.nodeName);
        };
        this.current = this.current.parentNode;
    };

    DOMHandler.prototype.endDocument = function() {
    };

    function DOM() {
    };

    this.DOM = DOM;

    DOM.prototype.createDocument = function() {
        var document = new Document();
        document.initialize();
        return document;
    };

    DOM.prototype.toXML = function(docOrEl, encoding) {
        var xml = '<?xml version="1.0"';
        if (encoding) {
            xml += ' encoding="' + encoding + '"';
        };
        xml += '?>\n';
        return xml + docOrEl.toXML();
    };

    DOM.prototype.parseXML = function(xml) {
        var handler = new DOMHandler();
        var parser = new SAXParser();
        parser.initialize(xml, handler);
        parser.parse();
        var document = handler.document;
        this._copyNamespaceMapping(document, handler.namespaceToPrefix);
        return document;
    };

    DOM.prototype.buildFromHandler = function(handler) {
        var document = handler.document;
        this._copyNamespaceMapping(document, handler.namespaceToPrefix);
        return document;
    };

    DOM.prototype._copyNamespaceMapping = function(document, namespaces) {
        document.namespaceToPrefix = namespaces;
    };

    // an implementation of an array, exactly the same as the one in JS 
    // (although incomplete) itself, this because friggin' IE has problems 
    // using Array as prototype (it won't update .length on mutations)
    function BaseArray() {
        for (var i=0; i < arguments.length; i++) {
            this[i] = arguments[i];
        };
        this.length = arguments.length;
    };

    BaseArray.prototype.concat = function() {
        throw('Not supported');
    };

    BaseArray.prototype.join = function() {
        throw('Not supported');
    };

    BaseArray.prototype.pop = function() {
        var item = this[this.length - 1];
        delete this[this.length - 1];
        this.length = this.length - 1;
        return item;
    };

    BaseArray.prototype.push = function(item) {
        this[this.length] = item;
        this.length = this.length + 1;
        return item;
    };

    BaseArray.prototype.reverse = function() {
        throw('Not supported');
    };

    BaseArray.prototype.shift = function() {
        var item = this[0];
        for (var i=1; i < this.length; i++) {
            this[i-1] = this[i];
        };
        delete this[length - 1];
        this.length = this.length - 1;
        return item;
    };

    BaseArray.prototype.unshift = function(item) {
        for (var i=0; i < this.length; i++ ) {
            this[this.length - i] = this[(this.length - i) - 1];
        };
        this[0] = item;
        this.length = this.length + 1;
        return ;
    };

    BaseArray.prototype.splice = function() {
        // XXX we may want to support this later
        throw('Not supported');
    };

    BaseArray.prototype.toString = function() {
        var ret = [];
        for (var i=1; i < this.length; i++) {
            ret.push(this[i].toString());
        };
        return ret.join(', ');
    };

    // for subclassing and such...
    this.BaseArray = BaseArray;

    function NodeList() {
    };

    NodeList.prototype = new BaseArray;
    this.NodeList = NodeList;

    NodeList.prototype.item = function(index) {
        return this[index];
    };

    function NamedNodeMap() {
    };

    NamedNodeMap.prototype = new BaseArray;
    this.NamedNodeMap = NamedNodeMap;

    NamedNodeMap.prototype.item = function(index) {
        return this[index];
    };

    NamedNodeMap.prototype.getNamedItem = function(name) {
        for (var i=0; i < this.length; i++) {
            if (this[i].nodeName == name) {
                return this[i];
            };
        };
        return undefined;
    };

    NamedNodeMap.prototype.setNamedItem = function(arg) {
        // this should generate exceptions, but I'm not sure when...
        // XXX how 'bout when arg is not the proper type?!?
        for (var i=0; i < this.length; i++) {
            if (this[i].nodeName == arg.nodeName) {
                this[i] = arg;
                return;
            };
        };
        this.push(arg);
    };

    NamedNodeMap.prototype.removeNamedItem = function(name) {
        // a bit nasty: deleting an element from an array will not actually 
        // free the index, instead something like undefined or null will end 
        // up in its place, so we walk the array here, move every element 
        // behind the item to remove one up, and pop the last item when 
        // we're done
        var delete_mode = false;
        for (var i=0; i < this.length; i++) {
            if (this[i] === name) {
                delete_mode = true;
            };
            if (delete_mode) {
                this[i] = this[i + 1];
            };
        };
        if (!delete_mode) {
            throw(
                (new DOMException(DOMException.NOT_FOUND_ERR))
            );
        };
        // the last element is now in the array twice
        this.pop();
    };
}();
function createStack() {
    var stack = undefined;
    try {notdefined()} catch(e) {stack = e.stack};
    if (stack) {
        stack = stack.split('\n');
        stack.shift();
        stack.shift();
    };
    return stack ? stack.join('\n') : '';
};

function getLineNo(stack) {
    if (!stack) {
        return;
    };
    stack = stack.toString().split('\n');
    var chunks = stack[0].split(':');
    var lineno = chunks[chunks.length - 1];
    if (lineno != '0') {
        return lineno;
    };
};
function getFileName(stack) {
    if (!stack) {
        return;
    };
    stack = stack.toString().split('\n');
    var chunks = stack[0].split(':');
    var filename = chunks[chunks.length - 2];
    return filename;
};


var global = this;
// create a namespace for our stuff... notice how we define a class and create
// an instance at the same time
global.davlib = new function() {   
    var davlib = this;

    this.DEBUG = 0;

    this.STATUS_CODES = {
        '100': 'Continue',
        '101': 'Switching Protocols',
        '102': 'Processing',
        '200': 'OK',
        '201': 'Created',
        '202': 'Accepted',
        '203': 'None-Authoritive Information',
        '204': 'No Content',
        // seems that there's some bug in IE (or Sarissa?) that 
        // makes it spew out '1223' status codes when '204' is
        // received... needs some investigation later on
        '1223': 'No Content',
        '205': 'Reset Content',
        '206': 'Partial Content',
        '207': 'Multi-Status',
        '300': 'Multiple Choices',
        '301': 'Moved Permanently',
        '302': 'Found',
        '303': 'See Other',
        '304': 'Not Modified',
        '305': 'Use Proxy',
        '307': 'Redirect',
        '400': 'Bad Request',
        '401': 'Unauthorized',
        '402': 'Payment Required',
        '403': 'Forbidden',
        '404': 'Not Found',
        '405': 'Method Not Allowed',
        '406': 'Not Acceptable',
        '407': 'Proxy Authentication Required',
        '408': 'Request Time-out',
        '409': 'Conflict',
        '410': 'Gone',
        '411': 'Length Required',
        '412': 'Precondition Failed',
        '413': 'Request Entity Too Large',
        '414': 'Request-URI Too Large',
        '415': 'Unsupported Media Type',
        '416': 'Requested range not satisfiable',
        '417': 'Expectation Failed',
        '422': 'Unprocessable Entity',
        '423': 'Locked',
        '424': 'Failed Dependency',
        '500': 'Internal Server Error',
        '501': 'Not Implemented',
        '502': 'Bad Gateway',
        '503': 'Service Unavailable',
        '504': 'Gateway Time-out',
        '505': 'HTTP Version not supported',
        '507': 'Insufficient Storage'
    };

    this.DavClient = function() {
    
    };

    this.DavClient.prototype.initialize = function(host, port, protocol) {
        this.host = host || location.hostname;
        this.port = port || location.port || 80;
        this.protocol = (protocol || 
                         location.protocol.substr(0, 
                                                  location.protocol.length - 1
                                                  ) ||
                         'http');
        this.request = null;
    };

    this.DavClient.prototype.OPTIONS = function(path, handler, context) {
        // XXX how does this work with * paths?
        var request = this._getRequest('OPTIONS', path, handler, context);
        request.send('');
    };

    this.DavClient.prototype.GET = function(path, handler, context) {
        var request = this._getRequest('GET', path, handler, context);
        request.send('');
    };

    this.DavClient.prototype.PUT = function(path, content, handler, 
                                            context, locktoken) {
        var request = this._getRequest('PUT', path, handler, context);
        request.setRequestHeader("Content-type", "text/xml,charset=UTF-8");
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        request.send(content);
    };

    this.DavClient.prototype.DELETE = function(path, handler, 
                                               context, locktoken) {
        var request = this._getRequest('DELETE', path, handler, context);
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        //request.setRequestHeader("Depth", "Infinity");
        request.send('');
    };

    this.DavClient.prototype.MKCOL = function(path, handler, 
                                              context, locktoken) {
        var request = this._getRequest('MKCOL', path, handler, context);
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        request.send('');
    };

    this.DavClient.prototype.COPY = function(path, topath, handler, 
                                             context, overwrite, locktoken) {
        var request = this._getRequest('COPY', path, handler, context);
        var tourl = this._generateUrl(topath);
        request.setRequestHeader("Destination", tourl);
        if (overwrite) {
            request.setRequestHeader("Overwrite", "F");
        };
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        request.send('');
    };

    this.DavClient.prototype.MOVE = function(path, topath, handler, 
                                             context, overwrite, locktoken) {
        var request = this._getRequest('MOVE', path, handler, context);
        var tourl = this._generateUrl(topath);
        request.setRequestHeader("Destination", tourl);
        if (overwrite) {
            request.setRequestHeader("Overwrite", "F");
        };
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        request.send('');
    };

    this.DavClient.prototype.PROPFIND = function(path, handler, 
                                                 context, depth) {
        var request = this._getRequest('PROPFIND', path, handler, context);
        depth = depth || 0;
        request.setRequestHeader('Depth', depth);
        request.setRequestHeader('Content-type', 'text/xml; charset=UTF-8');
        // XXX maybe we want to change this to allow getting selected props
        var xml = '<?xml version="1.0" encoding="UTF-8" ?>' +
                    '<D:propfind xmlns:D="DAV:">' +
                    '<D:allprop />' +
                    '</D:propfind>';
        request.send(xml);
    };

    // XXX not sure about the order of the args here
    this.DavClient.prototype.PROPPATCH = function(path, handler, context, 
                                                  setprops, delprops,
                                                  locktoken) {
        var request = this._getRequest('PROPPATCH', path, handler, context);
        request.setRequestHeader('Content-type', 'text/xml; charset=UTF-8');
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        var xml = this._getProppatchXml(setprops, delprops);
        request.send(xml);
    };

    this.DavClient.prototype.LOCK = function(path, owner, handler, context, 
                                             scope, type, depth, timeout,
                                             locktoken) {
        if (!scope) {
            scope = 'exclusive';
        };
        if (!type) {
            type = 'write';
        };
        var request = this._getRequest('LOCK', path, handler, context);
        if (depth) {
            request.setRequestHeader('Depth', depth);
        };
        if (!timeout) {
            timeout = "Infinite, Second-4100000000";
        } else {
            timeout = 'Second-' + timeout;
        };
        if (locktoken) {
            request.setRequestHeader('If', '<' + locktoken + '>');
        };
        request.setRequestHeader("Content-Type", "text/xml; charset=UTF-8");
        request.setRequestHeader('Timeout', timeout);
        var xml = this._getLockXml(owner, scope, type);
        request.send(xml);
    };

    this.DavClient.prototype.UNLOCK = function(path, locktoken, 
                                               handler, context) {
        var request = this._getRequest('UNLOCK', path, handler, context);
        request.setRequestHeader("Lock-Token", '<' + locktoken + '>');
        request.send('');
    };

    this.DavClient.prototype._getRequest = function(method, path, 
                                                    handler, context) {
        var request = davlib.getXmlHttpRequest();
        if (method == 'LOCK') {
            // LOCK requires parsing of the body on 200, so has to be treated
            // differently
            request.onreadystatechange = this._wrapLockHandler(handler, 
                                                            request, context);
        } else {
            request.onreadystatechange = this._wrapHandler(handler, 
                                                            request, context);
        };
        var url = this._generateUrl(path);
        request.open(method, url, true);
        // refuse all encoding, since the browsers don't seem to support it...
        request.setRequestHeader('Accept-Encoding', ' ');
        return request
    };

    this.DavClient.prototype._wrapHandler = function(handler, request,
                                                     context) {
        var self = this;
        function HandlerWrapper() {
            this.execute = function() {
                if (request.readyState == 4) {
                    var status = request.status.toString();
                    var headers = self._parseHeaders(
                                        request.getAllResponseHeaders());
                    var content = request.responseText;
                    if (status == '207') {
                        content = self._parseMultiStatus(content);
                    };
                    var statusstring = davlib.STATUS_CODES[status];
                    handler.call(context, status, statusstring, 
                                    content, headers);
                };
            };
        };
        return (new HandlerWrapper().execute);
    };

    this.DavClient.prototype._wrapLockHandler = function(handler, request, 
                                                         context) {
        var self = this;
        function HandlerWrapper() {
            this.execute = function() {
                if (request.readyState == 4) {
                    var status = request.status.toString();
                    var headers = self._parseHeaders(
                                        request.getAllResponseHeaders());
                    var content = request.responseText;
                    if (status == '200') {
                        content = self._parseLockinfo(content);
                    } else if (status == '207') {
                        content = self._parseMultiStatus(content);
                    };
                    var statusstring = davlib.STATUS_CODES[status];
                    handler.call(context, status, statusstring, 
                                 content, headers);
                };
            };
        };
        return (new HandlerWrapper().execute);
    };

    this.DavClient.prototype._generateUrl = function(path){
        var url = this.protocol + '://' + this.host;
        if (this.port) {
            url += ':' + this.port;
        };
        url += path;
        return url;
    };

    this.DavClient.prototype._parseMultiStatus = function(xml) {
        var handler = new davlib.MultiStatusSAXHandler();
        var parser = new SAXParser();
        parser.initialize(xml, handler);
        parser.parse();
        return handler.root;
    };

    this.DavClient.prototype._parseLockinfo = function(xml) {
        var handler = new davlib.LockinfoSAXHandler();
        var parser = new SAXParser();
        parser.initialize(xml, handler);
        parser.parse();
        return handler.lockInfo;
    };

    this.DavClient.prototype._getProppatchXml = function(setprops, delprops) {
        var xml = '<?xml version="1.0" encoding="UTF-8" ?>\n' +
                    '<D:propertyupdate xmlns:D="DAV:">\n';

        var shouldsetprops = false;
        for (var attr in setprops) {
            shouldsetprops = true;
        };
        if (shouldsetprops) {
            xml += '<D:set>\n';
            for (var ns in setprops) {
                for (var key in setprops[ns]) {
                    xml += '<D:prop>\n' +
                            this._preparePropElement(ns, key,
                                                     setprops[ns][key]) +
                            '</D:prop>\n';
                };
            };
            xml += '</D:set>\n';
        };

        var shoulddelprops = false;
        for (var attr in delprops) {
            shoulddelprops = true;
        };
        if (shoulddelprops) {
            xml += '<D:remove>\n<D:prop>\n';
            for (var ns in delprops) {
                for (var i=0; i < delprops[ns].length; i++) {
                    xml += '<' + delprops[ns][i] + ' xmlns="' + ns + '"/>\n';
                };
            };
            xml += '</D:prop>n</D:remove>\n';
        };

        xml += '</D:propertyupdate>';

        return xml;
    };

    this.DavClient.prototype._getLockXml = function(owner, scope, type) {
        var xml = '<?xml version="1.0" encoding="utf-8"?>\n'+
                    '<D:lockinfo xmlns:D="DAV:">\n' +
                    '<D:lockscope><D:' + scope + ' /></D:lockscope>\n' +
                    '<D:locktype><D:' + type + ' /></D:locktype>\n' +
                    '<D:owner>\n<D:href>' + 
                    string.entitize(owner) + 
                    '</D:href>\n</D:owner>\n' +
                    '</D:lockinfo>\n';
        return xml;
    };

    this.DavClient.prototype._preparePropElement = function(ns, key, value) {
        var dom = new dommer.DOM();
        // currently we expect the value to be a well-formed bit of XML that 
        // already contains the ns and key information...
        var doc = dom.parseXML(value);
        // ... so we don't need the following bit
        return doc.documentElement.toXML();
    };

    this.DavClient.prototype._parseHeaders = function(headerstring) {
        var lines = headerstring.split('\n');
        var headers = {};
        for (var i=0; i < lines.length; i++) {
            var line = string.strip(lines[i]);
            if (!line) {
                continue;
            };
            var chunks = line.split(':');
            var key = string.strip(chunks.shift());
            var value = string.strip(chunks.join(':'));
            var lkey = key.toLowerCase();
            if (headers[lkey] !== undefined) {
                if (!headers[lkey].push) {
                    headers[lkey] = [headers[lkey, value]];
                } else {
                    headers[lkey].push(value);
                };
            } else {
                headers[lkey] = value;
            };
        };
        return headers;
    };

    // MultiStatus parsing stuff

    this.Resource = function(href, props) {
        this.items = [];
        this.parent;
        this.properties = {}; // mapping from namespace to key/dom mappings
    };

    this.Root = function() {
    };

    this.Root.prototype = new this.Resource;

    // XXX this whole thing is rather messy...
    this.MultiStatusSAXHandler = function() {
    };

    this.MultiStatusSAXHandler.prototype = new SAXHandler;

    this.MultiStatusSAXHandler.prototype.startDocument = function() {
        this.resources = [];
        this.depth = 0;
        this.current = null;
        this.current_node = null;
        this.current_prop_namespace = null;
        this.current_prop_name = null;
        this.current_prop_handler = null;
        this.prop_start_depth = null;
        // array with all nodenames to be able to build a path
        // to a node and check for parent and such
        this.elements = [];
    };

    this.MultiStatusSAXHandler.prototype.endDocument = function() {
        this.buildTree();
    };

    this.MultiStatusSAXHandler.prototype.startElement = function(namespace, 
                                                        nodeName, attributes) {
        this.depth++;
        this.elements.push([namespace, nodeName]);
        davlib.debug('start: ' + namespace + ':' + nodeName);
        davlib.debug('parent: ' + (this.elements.length ? 
                                   this.elements[this.elements.length - 2] :
                                   ''));
        if (this.current_node == 'property') {
            this.current_prop_handler.startElement(namespace, nodeName, 
                                                   attributes);
            return;
        };

        if (namespace == 'DAV:' && nodeName == 'response') {
            var resource = new davlib.Resource();
            if (this.current) {
                resource.parent = this.current;
            };
            this.current = resource;
            this.resources.push(resource);
        } else {
            var parent = this.elements[this.elements.length - 2];
            if (!parent) {
                return;
            };
            if (namespace == 'DAV:' && parent[0] == 'DAV:' && 
                    parent[1] == 'response' || parent[1] == 'propstat') {
                // default response vars
                if (nodeName == 'href') {
                    this.current_node = 'href';
                } else if (nodeName == 'status') {
                    this.current_node = 'status';
                };
            } else if (parent[0] == 'DAV:' && parent[1] == 'prop') {
                // properties
                this.current_node = 'property';
                this.current_prop_namespace = namespace;
                this.current_prop_name = nodeName;
                // use a DOMHandler to propagate calls to for props
                this.current_prop_handler = new dommer.DOMHandler();
                this.current_prop_handler.startDocument();
                this.current_prop_handler.startElement(namespace, nodeName, 
                                                       attributes);
                this.start_prop_depth = this.depth;
                davlib.debug('start property');
            };
        };
    };

    this.MultiStatusSAXHandler.prototype.endElement = function(namespace, 
                                                               nodeName) {
        davlib.debug('end: ' + namespace + ':' + nodeName);
        if (namespace == 'DAV:' && nodeName == 'response') {
            if (this.current) {
                this.current = this.current.parent;
            };
        } else if (this.current_node == 'property' && 
                namespace == this.current_prop_namespace && 
                nodeName == this.current_prop_name &&
                this.start_prop_depth == this.depth) {
            davlib.debug('end property');
            this.current_prop_handler.endElement(namespace, nodeName);
            this.current_prop_handler.endDocument();
            var dom = new dommer.DOM();
            var doc = dom.buildFromHandler(this.current_prop_handler);
            if (!this.current.properties[namespace]) {
                this.current.properties[namespace] = {};
            };
            this.current.properties[namespace][this.current_prop_name] = doc;
            this.current_prop_namespace = null;
            this.current_prop_name = null;
            this.current_prop_handler = null;
        } else if (this.current_node == 'property') {
            this.current_prop_handler.endElement(namespace, nodeName);
            this.depth--;
            this.elements.pop();
            return;
        };
        this.current_node = null;
        this.elements.pop();
        this.depth--;
    };

    this.MultiStatusSAXHandler.prototype.characters = function(data) {
        if (this.current_node) {
            if (this.current_node == 'status') {
                this.current[this.current_node] = data.split(' ')[1];
            } else if (this.current_node == 'href') {
                this.current[this.current_node] = data;
            } else if (this.current_node == 'property') {
                this.current_prop_handler.characters(data);
            };
        };
    };

    this.MultiStatusSAXHandler.prototype.buildTree = function() {
        // XXX Splitting this up wouldn't make it less readable,
        // I'd say...
        
        // first find root element
        var minlen = -1;
        var root;
        var rootpath;
        // var url_reg = /^.*:\/\/[^\/]*(\/.*)$/;
        for (var i=0; i < this.resources.length; i++) {
            var resource = this.resources[i];
            resource.path = resource.href.split('/');
            if (resource.path[resource.path.length - 1] == '') {
                resource.path.pop();
            };
            var len = resource.path.length;
            if (minlen == -1 || len < minlen) {
                minlen = len;
                root = resource;
                root.parent = null;
            };
        };

        // now build the tree
        // first get a list without the root
        var elements = [];
        for (var i=0; i < this.resources.length; i++) {
            var resource = this.resources[i];
            if (resource == root) {
                continue;
            };
            elements.push(resource);
        };
        while (elements.length) {
            var leftovers = [];
            for (var i=0; i < elements.length; i++) {
                var resource = elements[i];
                var path = resource.path;
                var current = root;
                // we want to walk each element on the path to see if there's
                // a corresponding element already available, and if so 
                // continue walking until we find the parent element of the
                // resource
                if (path.length == root.path.length + 1) {
                    root.items.push(resource);
                    resource.parent = root;
                } else {
                    // XXX still untested, and rather, ehrm, messy...
                    for (var j = root.path.length; j < path.length - 1; 
                            j++) {
                        for (var k=0; k < current.items.length; k++) {
                            var item = current.items[k];
                            if (item.path[item.path.length - 1] ==
                                    path[j]) {
                                if (j == path.length - 2) {
                                    // we have a match at the end of the path
                                    // and all elements before that, this is 
                                    // the current resource's parent
                                    item.items.push(resource);
                                    resource.parent = item;
                                } else {
                                    // a match means we this item is one in our
                                    // path to the root, follow it
                                    current = item;
                                };
                                break;
                            };
                        };
                    };
                    leftovers.push(resource);
                };
            };
            elements = leftovers;
        };

        this.root = root;
    };

    this.LockinfoSAXHandler = function() {
    };

    this.LockinfoSAXHandler.prototype = new SAXHandler;

    this.LockinfoSAXHandler.prototype.startDocument = function() {
        this.lockInfo = {};
        this.currentItem = null;
        this.insideHref = false;
    };

    this.LockinfoSAXHandler.prototype.startElement = function(namespace, 
                                                              nodeName,
                                                              attributes) {
        if (namespace == 'DAV:') {
            if (nodeName == 'locktype' ||
                    nodeName == 'lockscope' ||
                    nodeName == 'depth' ||
                    nodeName == 'timeout' ||
                    nodeName == 'owner' ||
                    nodeName == 'locktoken') {
                this.currentItem = nodeName;
            } else if (nodeName == 'href') {
                this.insideHref = true;
            };
        };
    };

    this.LockinfoSAXHandler.prototype.endElement = function(namespace, 
                                                            nodeName) {
        if (namespace == 'DAV:') {
            if (nodeName == 'href') {
                this.insideHref = false;
            } else {
                this.currentItem = null;
            };
        };
    };

    this.LockinfoSAXHandler.prototype.characters = function(data) {
        if (this.currentItem && 
                (this.currentItem != 'owner' || this.insideHref) &&
                (this.currentItem != 'locktoken' || this.insideHref)) {
            this.lockInfo[this.currentItem] = data;
        };
    };

    // some helper functions
    this.getXmlHttpRequest = function() {
        try{
            return new XMLHttpRequest();
        } catch(e) {
            // not a Mozilla or Konqueror based browser
        };
        try {
            return new ActiveXObject('Microsoft.XMLHTTP');
        } catch(e) {
            // not IE either...
        };
        alert('Your browser does not support XMLHttpRequest, required for ' +
                'WebDAV access.');
        throw('Browser not supported');
    };
    this.debug = function(text) {
        if (!davlib.DEBUG) {
            return;
        };
        var div = document.createElement('div');
        var text = document.createTextNode(text);
        div.appendChild(text);
        document.getElementsByTagName('body')[0].appendChild(div);
    };
}();


 *
 */
public class DavClient {
	public DavClient(String base) {
		
		
		

		//var dc = new DavClient();
		
	                
		
	}
	
public static void main(String[] a) {
	/**
	 * @j2sNative
function handler(status, statusstring, content, headers) {
if (content) {
if (status != '200' && status != '204') {
    if (status == '207') {
        alert('not going to show multi-status ' +
                'here...');
    };
    alert('Error: ' + statusstring);
} else {
    alert('Content: ' + content);
};
};
};

var dc = new davlib.DavClient();
dc.initialize('localhost:8080');
dc.GET('/webdav/uno.txt', handler);

	 */{}
}
	//get, put, delete, mkcol, copy, move, PROPFIND, PROPPATCH, lock, unlock, 

}
