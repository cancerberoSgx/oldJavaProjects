/*
 * Copyright (c) 2000 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 */

package org.sgx.j2s.html.dom;

import org.sgx.j2s.xml.parser.XMLParser;

/**
 * The <code>NodeList</code> interface provides the abstraction of an ordered 
 * collection of nodes, without defining or constraining how this collection 
 * is implemented. <code>NodeList</code> objects in the DOM are live.
 * <p>The items in the <code>NodeList</code> are accessible via an integral 
 * index, starting from 0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Core-20001113'>Document Object Model (DOM) Level 2 Core Specification</a>.
 */
public class NodeList {
	/**
	 * @j2sIgnore
	 */
	org.w3c.dom.NodeList _nodelist;
	/**
	 * @j2sIgnore
	 */
	public NodeList(org.w3c.dom.NodeList _nodelist) {
		super();
		this._nodelist = (org.w3c.dom.NodeList)_nodelist;
		length=_nodelist.getLength();
	}
	
	public int length=0;

	/**
     * Returns the <code>index</code>th item in the collection. If 
     * <code>index</code> is greater than or equal to the number of nodes in 
     * the list, this returns <code>null</code>.
     * @param indexIndex into the collection.
     * @return The node at the <code>index</code>th position in the 
     *   <code>NodeList</code>, or <code>null</code> if that is not a valid 
     *   index.
     *   @j2sIgnore
     */
	public Node item(int index){
		 return XMLParser.getInstance().getNodeFor(_nodelist.item(index));
	}
//
//    /**
//     * The number of nodes in the list. The range of valid child node indices 
//     * is 0 to <code>length-1</code> inclusive. 
//     */
//    public abstract int getLength();

	
}
