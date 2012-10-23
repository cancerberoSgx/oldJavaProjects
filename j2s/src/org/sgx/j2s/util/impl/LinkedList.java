package org.sgx.j2s.util.impl;

import org.sgx.j2s.util.Collection;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;


//LinkedList class

//CONSTRUCTION: with no initializer
//Access is via LinkedListItr class

//******************PUBLIC OPERATIONS*********************
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//LinkedListItr zeroth( )--> Return position to prior to first
//LinkedListItr first( ) --> Return first position
//void insert( x, p )    --> Insert x after current iterator position p
//void remove( x )       --> Remove x
//LinkedListItr find( x )
//--> Return position that views x
//LinkedListItr findPrevious( x )
//--> Return position prior to x
//******************ERRORS********************************
//No special errors

/**
 * Linked list implementation of the list
 *    using a header node.
 * Access to the list is via LinkedListItr.
 * @author Mark Allen Weiss
 * @see LinkedListItr
 * @j2sRequireImport org.sgx.j2s.util.impl.ListNode
 */
public class LinkedList<E> implements List<E>{

	private ListNode<E> header, footer;
	private int size;
	

	/**
	 * Construct the list
	 */
	public LinkedList( )    {
		header = new ListNode<E>( null );
		footer=header;
		size=0;
	}

	/**
	 * Test if the list is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )    {
		return header.next == null;
	}
	/**
	 * Return an iterator representing the header node.
	 */
	public Iterator<E> zeroth( )    {
		return new LinkedListItr<E>( header );
	}
	/**
	 * Return an iterator representing the first node in the list.
	 * This operation is valid for empty lists.
	 */
	public Iterator<E> iterator( )    {
		return new LinkedListItr<E>( header.next );
	}
	/**
	 * Return iterator corresponding to the first node containing an item.
	 * @param x the item to search for.
	 * @return an iterator; iterator isPastEnd if item is not found.
	 */
	public Iterator<E> find( E x )	{
		ListNode<E> itr = header.next;
		while( itr != null && !itr.element.equals( x ) )
			itr = itr.next;
		return new LinkedListItr<E>( itr );
	}

	/**
	 * Return iterator prior to the first node containing an item.
	 * @param x the item to search for.
	 * @return appropriate iterator if the item is found. Otherwise, the
	 * iterator corresponding to the last element in the list is returned.
	 */
	public Iterator<E> findPrevious( Object x )    {
		ListNode<E> itr = header;
		while( itr.next != null && !itr.next.element.equals( x ) )
			itr = itr.next;
		return new LinkedListItr<E>( itr );
	}

	
	/**
	 * Remove the first occurrence of an item.
	 * @param x the item to remove.
	 */
	public void remove( E x )    {
		ListNode<E> itr = header;
		while( itr.next != null && !itr.next.element.equals( x ) )
			itr = itr.next;    		 
		LinkedListItr<E> p = new LinkedListItr<E>( itr );
		if( p.current.next != null ) {
			p.current.next = p.current.next.next;  // Bypass deleted node
			size--;
		}
		if(p.current.next==null) //sgurin
			footer=p.current;
	}
	
	/**
	 * Insert after p.
	 * @param x the item to insert.
	 * @param i the position prior to the newly inserted item.
	 */
	public void insert( E x, Iterator<E> i)    {
		LinkedListItr<E> p = (LinkedListItr<E>)i;
		if( p != null && p.current != null ) {
			ListNode<E> ln = new ListNode<E>( x, p.current.next );
			if(p.current.next==null)
				footer=ln;
			p.current.next = ln;
			size++;
		}
	}
	
	public void append(E x) {
		insert(x, new LinkedListItr<E>(footer));
	}
	
	public void preppend(E x) {
		insert(x, new LinkedListItr<E>(header));
	}
	
	/**
	 * Make the list logically empty.
	 */
	public void clear( )    {
		header.next = null;
		footer=header;
		size=0;
	}
	
	public int size() {
		return size;
	}
	
	
	public String toString() {
		Iterator<E> i =iterator();
		String s = "(";
		while(i.hasNext()) {
			s=s+i.next();
			if(i.hasNext())
				s=s+", ";
		}
		return s+")";
	}

	public boolean equals(List l) {
		Iterator i1 = l.iterator(), i2 = iterator();
		if(l.size()!=size())
			return false;			
		while(i1.hasNext()) {
			if(!i1.next().equals(i2.next()))
				return false;
		}
		return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		for(E o : c) {
			append(o);
		}
		return true;
	}

//	todo: equals, add(o,i)
//	/**
//	* Returns the element at the specified position in this list.
//	*
//	* @param index index of element to return.
//	* @return the element at the specified position in this list.
//	* 
//	* @throws IndexOutOfBoundsException if the index is out of range (index
//	* 		  &lt; 0 || index &gt;= size()).
//	*/
//	E get(int index);

//	/**
//	* Returns the index in this list of the first occurrence of the specified
//	* element, or -1 if this list does not contain this element.
//	* More formally, returns the lowest index <tt>i</tt> such that
//	* <tt>(o==null ? get(i)==null : o.equals(get(i)))</tt>,
//	* or -1 if there is no such index.
//	*
//	* @param o element to search for.
//	* @return the index in this list of the first occurrence of the specified
//	* 	       element, or -1 if this list does not contain this element.
//	* @throws ClassCastException if the type of the specified element
//	* 	       is incompatible with this list (optional).
//	* @throws NullPointerException if the specified element is null and this
//	*         list does not support null elements (optional).
//	*/
//	int indexOf(Object o);
//	/**
//	* Removes the element at the specified position in this list (optional
//	* operation).  Shifts any subsequent elements to the left (subtracts one
//	* from their indices).  Returns the element that was removed from the
//	* list.
//	*
//	* @param index the index of the element to removed.
//	* @return the element previously at the specified position.
//	* 
//	* @throws UnsupportedOperationException if the <tt>remove</tt> method is
//	*		  not supported by this list.
//	* @throws IndexOutOfBoundsException if the index is out of range (index
//	*            &lt; 0 || index &gt;= size()).
//	*/
//	Object remove(int index) {

//	}

}