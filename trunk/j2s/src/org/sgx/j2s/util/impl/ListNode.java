package org.sgx.j2s.util.impl;

public class ListNode<E>{
    E   element;
    ListNode<E> next;
    
    ListNode( E theElement )    {
        this( theElement, null );
    }

    ListNode( E theElement, ListNode<E> n )    {
        element = theElement;
        next    = n;
    }

}