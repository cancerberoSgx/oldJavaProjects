package org.sgx.jmencoder.streamplayer.test.probes.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ModelJTree extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private JTree tree;

  private DefaultTreeModel model;

  private DefaultMutableTreeNode rootNode;

  public ModelJTree() {
    DefaultMutableTreeNode philosophersNode = getPhilosopherTree();
    model = new DefaultTreeModel(philosophersNode);
    tree = new JTree(model);
    JButton addButton = new JButton("Add Philosopher");
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        addPhilosopher();
      }
    });
    JButton removeButton = new JButton("Remove Selected Philosopher");
    removeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        removeSelectedPhilosopher();
      }
    });
    JPanel inputPanel = new JPanel();
    inputPanel.add(addButton);
    inputPanel.add(removeButton);

    Container container = getContentPane();

    container.add(new JScrollPane(tree), BorderLayout.CENTER);

    container.add(inputPanel, BorderLayout.NORTH);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(400, 300);
    setVisible(true);

  }

  private void addPhilosopher() {
    DefaultMutableTreeNode parent = getSelectedNode();
    if (parent == null) {
      JOptionPane.showMessageDialog(ModelJTree.this, "Select an era.", "Error",
          JOptionPane.ERROR_MESSAGE);

      return;
    }
    String name = JOptionPane.showInputDialog(ModelJTree.this, "Enter Name:");
    model.insertNodeInto(new DefaultMutableTreeNode(name), parent, parent.getChildCount());

  }

  private void removeSelectedPhilosopher() {
    DefaultMutableTreeNode selectedNode = getSelectedNode();
    if (selectedNode != null)
      model.removeNodeFromParent(selectedNode);
  }

  private DefaultMutableTreeNode getSelectedNode() {
    return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
  }

  private DefaultMutableTreeNode getPhilosopherTree() {
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Philosophers");
    DefaultMutableTreeNode ancient = new DefaultMutableTreeNode("Ancient");
    rootNode.add(ancient);

    ancient.add(new DefaultMutableTreeNode("Socrates"));

    DefaultMutableTreeNode medieval = new DefaultMutableTreeNode("Medieval");
    rootNode.add(medieval);

    return rootNode;
  }
  public static void main(String args[]) {
    new ModelJTree();
  }
}