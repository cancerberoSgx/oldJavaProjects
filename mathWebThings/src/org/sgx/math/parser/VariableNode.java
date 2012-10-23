/*
 * @(#)VariableNode.java	1.00 12/31/2001
 *
 * Copyright 2001 by Oliver Kurt. All Rights Reserved.
 *
 * This software is the proprietary information of Oliver Kurt, Germany.
 * Use is subject to license terms.
 *
 */

package org.sgx.math.parser;

import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;

/**
 * Dieser Knoten beinhaltet nur eine Variable.
 * <p>
 * Da er keine Kindknoten mehr beinhalten kann, ist er ein Blatt des Baumes.
 *
 * @author: Oliver Kurt
 * @date: 12/31/2001
 */
public class VariableNode implements Node {

  /**
   * name der variable. erlaubt sind a..z, A..Z
   */
  private char name;

  /**
   * Konstruktor.
   * <p>
   * Wird nur von Node aufgerufen.
   */
  protected VariableNode(char name) {
    this.name = name;
  }

  public double getValue(Vector variables) {
	  for (Iterator iterator = variables.iterator(); iterator.hasNext();) {
		  Variable v = (Variable) iterator.next();
		  if ( v.getName() == name ) {
			  return v.getValue();
		  }
	  }
	  throw new RuntimeException("Variable not set: "+name);

	  //    Enumeration enum = variables.elements();
	  //    while ( enum.hasMoreElements() ) {
	  //      Variable v = (Variable)enum.nextElement();
	  //      if ( v.getName() == name ) {
	  //        return v.getValue();
	  //      }
	  //    }
	  //    throw new RuntimeException("Variable not set: "+name);
  }

}