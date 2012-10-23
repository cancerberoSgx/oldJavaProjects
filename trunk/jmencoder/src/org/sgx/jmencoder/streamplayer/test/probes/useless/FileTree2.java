package org.sgx.jmencoder.streamplayer.test.probes.useless;
//package org.sgx.jmencoder.streamplayer.test;
//
//
//import java.awt.*;
//
//import java.awt.event.*;
//
//import java.io.*;
//
//import java.util.*;
//
//import javax.swing.*;
//
//import javax.swing.tree.*;
//
//import javax.swing.event.*;
//
//public class FileTree2 extends JFrame
//
//{
//
//  // Unchanged code from section 17.3
//
//  /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//protected JPopupMenu m_popup;
//
//  protected Action m_action;
//
//  protected TreePath m_clickedPath;
//
//protected JTree m_tree;
//
//  public FileTree2() {
//
//    super("Directories Tree [Popup Menus]");
//
//    setSize(400, 300);
//
//    m_tree = new JTree();
//    getContentPane().setLayout(new BorderLayout());
//    getContentPane().add(m_tree);
//    // Unchanged code from sectionn 17.3
//
//    m_popup = new JPopupMenu();
//
//    m_action = new AbstractAction() {
//
//      public void actionPerformed(ActionEvent e) {
//
//        if (m_clickedPath==null)
//
//          return;
//
//        if (m_tree.isExpanded(m_clickedPath))
//
//          m_tree.collapsePath(m_clickedPath);
//
//        else
//
//          m_tree.expandPath(m_clickedPath);
//
//      }
//
//    };
//
//    m_popup.add(m_action);
//
//    m_popup.addSeparator();
//
//    Action a1 = new AbstractAction("Delete") {
//
//      public void actionPerformed(ActionEvent e) {
//
//        m_tree.repaint();
//
//        JOptionPane.showMessageDialog(FileTree2.this,
//
//          "Delete option is not implemented",
//
//          "Info", JOptionPane.INFORMATION_MESSAGE);
//
//      }
//
//    };
//
//    m_popup.add(a1);
//
//    Action a2 = new AbstractAction("Rename") {
//
//      public void actionPerformed(ActionEvent e) {
//
//        m_tree.repaint();
//
//        JOptionPane.showMessageDialog(FileTree2.this,
//
//          "Rename option is not implemented",
//
//          "Info", JOptionPane.INFORMATION_MESSAGE);
//
//      }
//
//    };
//
//    m_popup.add(a2);
//
//    m_tree.add(m_popup);
//
//    m_tree.addMouseListener(new PopupTrigger());
//
//    WindowListener wndCloser = new WindowAdapter() {
//
//      public void windowClosing(WindowEvent e) {
//
//        System.exit(0);
//
//      }
//
//    };
//
//    addWindowListener(wndCloser);
//
//    setVisible(true);
//
//  }
//
//  // Unchanged code from section 17.3
//
//  class PopupTrigger extends MouseAdapter {
//
//    public void mouseReleased(MouseEvent e) {
//
//      if (e.isPopupTrigger()) {
//
//        int x = e.getX();
//
//        int y = e.getY();
//
//        TreePath path = m_tree.getPathForLocation(x, y);
//
//        if (path != null) {
//
//          if (m_tree.isExpanded(path))
//
//            m_action.putValue(Action.NAME, "Collapse");
//
//          else
//
//            m_action.putValue(Action.NAME, "Expand");
//
//          m_popup.show(m_tree, x, y);
//
//          m_clickedPath = path;
//
//        }
//
//      }
//
//    }
//
//  }
//
//  public static void main(String argv[]) {
//
//    new FileTree2();
//
//  }
//
//}