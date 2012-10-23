package org.sgx.j2s.util.impl;

import java.util.Iterator;


/**
 * Linked list implementation of the list iterator
 *    using a header node.
 * @author Mark Allen Weiss
 * @see LinkedList
 * 
 * @j2sIgnoreImport java.util.Iterator
 */
public class LinkedListItr<E> implements org.sgx.j2s.util.Iterator<E>, Iterator<E> {
    ListNode<E> current;    // Current position    
    /**
     * Construct the list iterator
     * @param theNode any node in the linked list.
     */
    LinkedListItr( ListNode theNode )    {
        current = theNode;
    }
    /**
     * Test if the current position is past the end of the list.
     * @return true if the current position is null.
     */
    public boolean isPastEnd( )    {
        return current == null;
    }
    
    public E next() {
    	E o = current.element;
    	advance();
    	return o;
    }

    /**
     * Return the item stored in the current position.
     * @return the stored item or null if the current position
     * is not in the list.
     */
    public Object retrieve( )    {
        return isPastEnd( ) ? null : current.element;
    }

    /**
     * Advance the current position to the next node in the list.
     * If the current position is null, then do nothing.
     */
    public void advance( )    {
        if( !isPastEnd( ) )
            current = current.next;
    }

	public boolean hasNext() {
		return current!=null;
	}
	public void remove() {
		throw new RuntimeException("IMPLEMENT ME PLEASE!");
	}

}

