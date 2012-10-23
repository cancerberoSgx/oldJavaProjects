/*
 * @(#)CharacterIterator.java	1.19 03/12/19
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * (C) Copyright Taligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The original version of this source code and documentation
 * is copyrighted and owned by Taligent, Inc., a wholly-owned
 * subsidiary of IBM. These materials are provided under terms
 * of a License Agreement between Taligent and Sun. This technology
 * is protected by multiple US and International patents.
 *
 * This notice and attribution to Taligent may not be removed.
 * Taligent is a registered trademark of Taligent, Inc.
 *
 */

package org.stringtree.json;


/** this is a copy of java.text.characterIterator */

public interface CharacterIterator extends Cloneable
{
    public static final char DONE = '\uFFFF';

    public char first();

    public char last();

    public char current();

    public char next();

    public char previous();

    public char setIndex(int position);

    public int getBeginIndex();

    public int getEndIndex();

    public int getIndex();

    public Object clone();

}
