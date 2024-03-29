package org.sgx.j2s.util;
/**
 * 
 * @author nati
 *
 * @param <E>
 */
public interface Iterator<E> extends java.util.Iterator<E> {
	/**
	 * Returns <tt>true</tt> if the iteration has more elements. (In other
	 * words, returns <tt>true</tt> if <tt>next</tt> would return an element
	 * rather than throwing an exception.)
	 *
	 * @return <tt>true</tt> if the iterator has more elements.
	 */
	boolean hasNext();

	/**
	 * Returns the next element in the iteration.  Calling this method
	 * repeatedly until the {@link #hasNext()} method returns false will
	 * return each element in the underlying collection exactly once.
	 *
	 * @return the next element in the iteration.
	 * @exception NoSuchElementException iteration has no more elements.
	 */
	E next();

//	/**
//	 * 
//	 * Removes from the underlying collection the last element returned by the
//	 * iterator (optional operation).  This method can be called only once per
//	 * call to <tt>next</tt>.  The behavior of an iterator is unspecified if
//	 * the underlying collection is modified while the iteration is in
//	 * progress in any way other than by calling this method.
//	 *
//	 * @exception UnsupportedOperationException if the <tt>remove</tt>
//	 *		  operation is not supported by this Iterator.
//
//	 * @exception IllegalStateException if the <tt>next</tt> method has not
//	 *		  yet been called, or the <tt>remove</tt> method has already
//	 *		  been called after the last call to the <tt>next</tt>
//	 *		  method.
//	 */
//	void remove();
}
