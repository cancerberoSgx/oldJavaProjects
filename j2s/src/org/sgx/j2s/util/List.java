package org.sgx.j2s.util;

public interface List<E> extends Collection<E> {
	
    /**
     * Insert after p.
     * @param x the item to insert.
     * @param p the position prior to the newly inserted item.
     */
    public void insert( E x, Iterator<E> p );

    /**
     * Return iterator corresponding to the first node containing an item.
     * @param x the item to search for.
     * @return an iterator; iterator isPastEnd if item is not found.
     */
    public Iterator<E> find( E x );

    /**
     * Return iterator prior to the first node containing an item.
     * @param x the item to search for.
     * @return appropriate iterator if the item is found. Otherwise, the
     * iterator corresponding to the last element in the list is returned.
     */
    public Iterator<E> findPrevious( E x );

    /**
     * Remove the first occurrence of an item.
     * @param x the item to remove.
     */
    public void remove( E x );
 
	public void append(E x);
	
	public void preppend(E x); 
	

//todo: equals, add(o,i), toString
//    E get(int index);
//    int indexOf(Object o);
//    Object remove(int index);
}
