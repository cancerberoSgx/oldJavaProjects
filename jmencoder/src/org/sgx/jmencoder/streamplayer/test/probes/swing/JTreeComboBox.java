package org.sgx.jmencoder.streamplayer.test.probes.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;


//////////////////////////////////////////////////////////////

public class JTreeComboBox extends JComboBox implements TreeSelectionListener {
 
	private static final long serialVersionUID = 1L;
	protected Color selectedBackground;
    protected Color selectedForeground;
    protected Color background;
    protected Color foreground;

    protected JTree tree = null;

    public JTreeComboBox(TreeModel aTreeModel) {
        initializeTree();
        setTreeModel(aTreeModel);
    }

    public void setCellRenderer(TreeCellRenderer theRenderer) {
        tree.setCellRenderer(theRenderer);
    }

    public JTree getTree() {
        return tree;
    }

    private void initializeTree() {
        tree = new JTree();
        tree.setCellRenderer(new CustomTreeRenderer());
        tree.setVisibleRowCount(8);
        tree.setBackground(background);
        tree.addTreeSelectionListener(this);
        tree.getSelectionModel().setSelectionMode
            (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);

    }

    public void setTreeModel(TreeModel aModel) {
        tree.setModel(aModel);

        setSelection(aModel.getRoot());
    }

    public void makeVisible(TreePath aPath) {
        tree.makeVisible(aPath);
    }

    public void setSelectedItem(Object item) {
        if(treeContains((TreeNode)tree.getModel().getRoot(), (TreeNode)item))
            setSelection(item);
    }

    private void setSelection(Object item) {
        removeAllItems();
        addItem(item);
    }

    private boolean treeContains(TreeNode root, TreeNode node) {
        if(root.getIndex(node) != -1)
            return true;

        for(int i = 0; i < root.getChildCount(); i++)
            if(treeContains(root.getChildAt(i), node))
                return true;

        return false;
    }

    public void updateUI() {
        ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);
        if (cui instanceof MetalComboBoxUI) {
            cui = new MetalTreeComboBoxUI();
        } else if (cui instanceof MotifComboBoxUI) {
            cui = new MotifTreeComboBoxUI();
        } else if (cui instanceof WindowsComboBoxUI) {
            cui = new WindowsTreeComboBoxUI();
        }
        setUI(cui);
    }


    public void valueChanged(TreeSelectionEvent e) {
        TreeNode selectedNode = (TreeNode)
            tree.getLastSelectedPathComponent();

        if (selectedNode == null)
              return;

          setSelection(selectedNode);

          hidePopup();
    }

    // Inner classes are used purely to keep TreeComboBox component in one file
    //////////////////////////////////////////////////////////////
    // UI Inner classes -- one for each supported Look and Feel
    //////////////////////////////////////////////////////////////

    class MetalTreeComboBoxUI extends MetalComboBoxUI {
        protected ComboPopup createPopup() {
            return new TreePopup( comboBox );
        }
    }

    class WindowsTreeComboBoxUI extends WindowsComboBoxUI {
        protected ComboPopup createPopup() {
            return new TreePopup( comboBox );
        }
    }

    class MotifTreeComboBoxUI extends MotifComboBoxUI {
		private static final long serialVersionUID = 1L;

		protected ComboPopup createPopup() {
            return new TreePopup( comboBox );
        }
    }


     //////////////////////////////////////////////////////////////
    // TreePopup inner class
    //////////////////////////////////////////////////////////////

    class TreePopup implements ComboPopup, MouseMotionListener,
                   MouseListener, KeyListener, PopupMenuListener {

    protected JComboBox comboBox;
    protected JPopupMenu popup;


    public TreePopup(JComboBox comboBox) {
        this.comboBox = comboBox;

        // check Look and Feel
        background = UIManager.getColor("ComboBox.background");
        foreground = UIManager.getColor("ComboBox.foreground");
        selectedBackground = UIManager.getColor("ComboBox.selectionBackground");
        selectedForeground = UIManager.getColor("ComboBox.selectionForeground");

        selectedBackground = new Color(153,153,204);

        //System.out.println(background + " " + foreground);
        //System.out.println(selectedBackground + " " + selectedForeground);

        initializePopup();
    }

    //========================================
    // begin ComboPopup method implementations
    //
    public void show() {
        try {
        // if setSelectedItem() was called with a valid date, adjust the calendar
        //calendar.setTime( dateFormat.parse( comboBox.getSelectedItem().toString() ) );
        } catch (Exception e) {}
        updatePopup();
        popup.show(comboBox, 0, comboBox.getHeight());
        popup.setVisible(true);
    }

    public void hide() {
        popup.setVisible(false);
    }

    protected JList list = new JList();
    public JList getList() {
        return list;
    }

    public MouseListener getMouseListener() {
        return this;
    }

    public MouseMotionListener getMouseMotionListener() {
        return this;
    }

    public KeyListener getKeyListener() {
        return this;
    }

    public boolean isVisible() {
        return popup.isVisible();
    }

    public void uninstallingUI() {
        popup.removePopupMenuListener(this);
    }

    //
    // end ComboPopup method implementations
    //======================================



    //===================================================================
    // begin Event Listeners
    //

    // MouseListener

    public void mousePressed( MouseEvent e ) {}
    public void mouseReleased( MouseEvent e ) {}
    // something else registered for MousePressed
    public void mouseClicked(MouseEvent e) {
        //System.out.println("clicked");
        if ( !SwingUtilities.isLeftMouseButton(e) )
            return;
        if ( !comboBox.isEnabled() )
            return;
        if ( comboBox.isEditable() ) {
            comboBox.getEditor().getEditorComponent().requestFocus();
        } else {
            comboBox.requestFocus();
        }
        togglePopup();
    }

    protected boolean mouseInside = false;
    public void mouseEntered(MouseEvent e) {
        mouseInside = true;
    }
    public void mouseExited(MouseEvent e) {
        mouseInside = false;
    }

    // MouseMotionListener
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    // KeyListener
    public void keyPressed(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void keyReleased( KeyEvent e ) {
        if ( e.getKeyCode() == KeyEvent.VK_SPACE ||
             e.getKeyCode() == KeyEvent.VK_ENTER ) {
            togglePopup();
        }
    }

    /**
     * Variables hideNext and mouseInside are used to
     * hide the popupMenu by clicking the mouse in the JComboBox
     */
    public void popupMenuCanceled(PopupMenuEvent e) {}
    protected boolean hideNext = false;
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        hideNext = mouseInside;
    }
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

    //
    // end Event Listeners
    //=================================================================

    //===================================================================
    // begin Utility methods
    //

    protected void togglePopup() {
        //System.out.println("toggle "  + popup.isVisible());

        if ( isVisible() || hideNext  ) {
            hide();
        } else {
            show();
        }
        hideNext = false;
    }

    //
    // end Utility methods
    //=================================================================
    JScrollPane scroller = new JScrollPane();
    protected void initializePopup() {
        popup = new JPopupMenu();
        popup.setLayout(new BorderLayout());
        popup.setBorder(new EmptyBorder(0,0,0,0));
        popup.addPopupMenuListener(this);
        popup.add(scroller);
        popup.pack();
    }

    protected void updatePopup() {
        //System.out.println("update");
        scroller.setViewportView(tree);

        int width = comboBox.getWidth();
        int height = (int) tree.getPreferredScrollableViewportSize().getHeight();

        popup.setPopupSize(width, height);
    }

    }



    class CustomTreeRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 1L;
		protected Object lastNode = null;

        public CustomTreeRenderer() {
            setOpaque(true);
            //setBackgroundNonSelectionColor(tree.getBackground());
            tree.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent me) {
                    TreePath treePath = tree.getPathForLocation(me.getX(), me.getY());
                    Object obj;
                    if (treePath!=null) {
                        obj = treePath.getLastPathComponent();
                    } else {
                        obj = null;
                    }
                    if (obj!=lastNode) {
                        lastNode = obj;
                        tree.repaint();
                    }
                }
            });
        }

        public Component getTreeCellRendererComponent(
            JTree tree, Object value,
            boolean isSelected, boolean isExpanded,
            boolean isLeaf, int row, boolean hasFocus) {

            JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value,
                isSelected, isExpanded, isLeaf, row, hasFocus);
            if (value==lastNode || (lastNode == null && isSelected)) {
                label.setBackground(selectedBackground);
                //label.setBackground(Color.red);
                label.setForeground(selectedForeground);
            } else {
                label.setBackground(background);
                label.setForeground(foreground);
            }

            return label;
        }
    }

    //////////////////////////////////////////////////////////////
    // This is only included to provide a sample GUI
    //////////////////////////////////////////////////////////////
    public static void main(String args[]) {
        DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("test1");
        DefaultTreeModel treeModel1 = new DefaultTreeModel(root1);
        DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("test2");
        DefaultTreeModel treeModel2 = new DefaultTreeModel(root2);

        for(int i= 1; i < 10; i++)
            treeModel1.insertNodeInto(new DefaultMutableTreeNode("Node"+i),
                root1,root1.getChildCount());


        for(int i= 1; i < 10; i++)
            treeModel2.insertNodeInto(new DefaultMutableTreeNode("Node"+i),
                root2,root2.getChildCount());
        //TreeComboBox tc1 = new TreeComboBox();
        //tc1.setTree(t1);

        JFrame f = new JFrame();
        Container p = f.getContentPane();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
        c.add(new JLabel("Tree 1:"));
        JTreeComboBox cb = new JTreeComboBox(treeModel1);
        c.add(cb);
        c.add(new JLabel("Tree 2:"));
        JTreeComboBox dcb = new JTreeComboBox(treeModel2);
        dcb.setEditable(true);
        c.add(dcb);

        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTreeComboBox src = (JTreeComboBox) e.getSource();

                //JOptionPane.showMessageDialog(null,
                System.out.println("Selected " + src.getSelectedItem());

            }
        });
        //c.add(new DateComboBox());
        //c.add(new JComboBox(new String[] {"Item"}));
        /*
        JComboBox cb = new JComboBox(new String[] {"Item"});
        cb.setEditable(true);
        c.add(cb);
        */
        p.add(c);
        p.add(Box.createVerticalStrut(200));
        p.add(Box.createVerticalGlue());

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            });
        f.setSize(500, 200);
        f.setVisible(true);
    }

}
