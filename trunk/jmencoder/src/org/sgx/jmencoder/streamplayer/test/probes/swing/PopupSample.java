package org.sgx.jmencoder.streamplayer.test.probes.swing;

import javax.swing.*;


public class PopupSample {

  public static void main(final String args[]) {
    JFrame frame = new JFrame("PopupSample Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Create popup menu, attach popup menu listener
    JPopupMenu popupMenu = new JPopupMenu("Title");

    // Cut
    JMenuItem cutMenuItem = new JMenuItem("Cut");
    popupMenu.add(cutMenuItem);

    // Copy
    JMenuItem copyMenuItem = new JMenuItem("Copy");
    popupMenu.add(copyMenuItem);
    // Paste
    JMenuItem pasteMenuItem = new JMenuItem("Paste");
    pasteMenuItem.setEnabled(false);
    popupMenu.add(pasteMenuItem);

    // Separator
    popupMenu.addSeparator();

    // Find
    JMenuItem findMenuItem = new JMenuItem("Find");
    popupMenu.add(findMenuItem);
    JButton label = new JButton();
    frame.add(label);
    label.setComponentPopupMenu(popupMenu);

    frame.setSize(350, 250);
    frame.setVisible(true);
  }
}