$(function () {
    $(".javascript code").each(function (i) {
        var html = $(this).html();
        this.className = i % 2 ? "odd" : "even";
        html = html.replace(/\b(abstract|boolean|break|byte|case|catch|char|class|const|continue|debugger|default|delete|do|double|else|enum|export|extends|false|final|finally|float|for|function|goto|if|implements|import|in|instanceof|int|interface|long|native|new|null|package|private|protected|public|return|short|static|super|switch|synchronized|this|throw|throws|transient|true|try|typeof|var|void|volatile|while|with)\b/g, "<b>$1</b>");
        // html = html.replace(/(\/.*\/[gim]*)/g, "<i>$1</i>");
        html = html.replace(/(\"[^\"]*\")/g, "<i>$1</i>");
        html = html.replace(/( \= | \- | \+ | \* | \&\& | \|\| | \/ | == | === )/g, '<span class="s">$1</span>');
        html = html.replace(/\b(\d+)\b/g, '<span class="d">$1</span>');
        html = html.replace(/(\/\/.*)$/g, '<span class="c">$1</span>');
        $(this).html(html);
    });
    $(".css code").each(function () {
        var html = $(this).html();
        html = html.replace(/\b(\-moz\-border\-radius|azimuth|background|background\-attachment|background\-color|background\-image|background\-position|background\-repeat|border\-bottom|border\-bottom\-color|border\-bottom\-style|border\-bottom\-width|border\-collapse|border\-color|border\-left|border\-left\-color|border\-left\-style|border\-left\-width|border\-right|border\-right\-color|border\-right\-style|border\-right\-width|border\-spacing|border\-style|border\-top|border\-top\-color|border\-top\-style|border\-top\-width|border\-width|bottom|caption\-side|clear|clip|color|content|counter\-increment|counter\-reset|cue|cue\-after|cue\-before|cursor|direction|display|elevation|empty\-cells|float|font|font\-family|font\-size|font\-size\-adjust|font\-stretch|font\-style|font\-variant|font\-weight|height|left|letter|letter\-spacing|line\-height|list\-style|list\-style\-image|list\-style\-position|list\-style\-type|list|margin|margin\-bottom|margin\-left|margin\-right|margin\-top|marker|marker\-offset|marks|max\-height|max\-width|min\-height|min\-width|opacity|orphans|outline|outline\-color|outline\-style|outline\-width|overflow|overflow(\-[xy])?|padding|padding\-bottom|padding\-left|padding\-right|padding\-top|page|page\-break\-after|page\-break\-before|page\-break\-inside|pause|pause\-after|pause\-before|pitch|pitch\-range|play\-during|position|quotes|richness|right|scrollbar|size|speak|speak\-header|speak\-numeral|speak\-punctuation|speech\-rate|stress|table\-layout|text|text\-align|text\-decoration|text\-indent|text\-shadow|text\-transform|top|unicode\-bidi|vertical|vertical\-align|visibility|voice\-family|volume|white|white\-space|widows|width|word\-spacing|word|z\-index|opacity|filter|border)\b/g, "<b>$1</b>");
        html = html.replace(/\b(absolute|all\-scroll|always|auto|baseline|below|bidi\-override|block|bold|bolder|both|bottom|break\-all|break\-word|capitalize|center|char|circle|col\-resize|collapse|crosshair|dashed|decimal|default|disabled|disc|distribute|distribute\-all\-lines|distribute\-letter|distribute\-space|dotted|double|e\-resize|ellipsis|fixed|groove|hand|help|hidden|horizontal|ideograph\-alpha|ideograph\-numeric|ideograph\-parenthesis|ideograph\-space|inactive|inherit|inline|inline\-block|inset|inside|inter\-ideograph|inter\-word|italic|justify|keep\-all|left|lighter|line|line\-edge|line\-through|list\-item|loose|lower\-alpha|lower\-roman|lowercase|lr\-tb|ltr|medium|middle|move|n\-resize|ne\-resize|newspaper|no\-drop|no\-repeat|none|normal|not\-allowed|nowrap|nw\-resize|oblique|outset|outside|overline|pointer|progress|relative|repeat|repeat\-x|repeat\-y|ridge|right|row\-resize|rtl|s\-resize|scroll|se\-resize|separate|small\-caps|solid|square|static|strict|super|sw\-resize|table\-footer\-group|table\-header\-group|tb\-rl|text|text\-bottom|text\-top|thick|thin|top|transparent|underline|upper\-alpha|upper\-roman|uppercase|url|vertical\-ideographic|vertical\-text|visible|w\-resize|wait|whitespace)\b/g, "<var>$1</var>");
        html = html.replace(/(\"[^\"]*\")/g, "<i>$1</i>");
        html = html.replace(/(^|\s)(\*|#|\.|a|abbr|acronym|address|area|b|base|big|blockquote|body|br|button|caption|cite|code|col|colgroup|dd|del|dfn|div|dl|dt|em|fieldset|form|frame|frameset|h1|h2|h3|h4|h5|h6|head|hr|html|i|iframe|img|input|ins|kbd|label|legend|li|link|map|meta|noframes|noscript|object|ol|optgroup|option|p|param|pre|q|samp|script|select|small|span|strike|strong|style|sub|sup|table|tbody|td|textarea|tfoot|th|thead|title|tr|tt|ul|var)($|\s)/g, '$1<b class="t">$2</b>$3');
        html = html.replace(/(\-?\d+)/g, '<span class="d">$1</span>');
        html = html.replace(/(#[0-9a-f]+);/g, '<span class="c">$1</span>');
        $(this).html(html);
    });
    $(".html code").each(function () {
        var html = $(this).html();
        html = html.replace(/(\"[^\"]*\")/g, "<i>$1</i>");
        html = html.replace(/(&lt;.*?&gt;)/g, '<b>$1</b>');
        $(this).html(html);
    });
});

