package org.sgx.j2s.util;


public interface Collection<E> extends Iterable<E>{
	  /**
     * Test if the list is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( );
 
    /**
     * Make the list logically empty.
     */
    public void clear( ) ;

    /**
     * Return an iterator representing the first node in the list.
     * This operation is valid for empty lists.
     */
    public Iterator<E> iterator( );
    	
	public int size();
	
	 /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)   
     */
    boolean addAll(Collection<? extends E> c);
    
    
}
