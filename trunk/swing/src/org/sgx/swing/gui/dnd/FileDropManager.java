package org.sgx.swing.gui.dnd;
//package org.sgx.swing.gui.dnd;
//
//import java.awt.Component;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.awt.dnd.DnDConstants;
//import java.awt.dnd.DropTarget;
//import java.awt.dnd.DropTargetDragEvent;
//import java.awt.dnd.DropTargetDropEvent;
//import java.awt.dnd.DropTargetEvent;
//import java.awt.dnd.DropTargetListener;
//import java.io.File;
//import java.net.URI;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//import javax.swing.JComponent;
//
//public class FileDropManager implements FileDrop.Listener {
//
//	Component target;
////	private DropTarget dt;
//
//	List<FileDropListener> listeners = new LinkedList<FileDropListener>();
//	public void addDropListener(FileDropListener l) {
//		listeners.add(l);
//	}
//	public FileDropManager(Component target) {
//		super();
//		this.target = target;
//		
//		
////		dt = new DropTarget(target, this);
//	}
//	public void dragEnter(DropTargetDragEvent dtde) {
//	}
//	public void dragExit(DropTargetEvent dte) {
//	}
//	public void dragOver(DropTargetDragEvent dtde) {
//	}
//	public void dropActionChanged(DropTargetDragEvent dtde) {
//	}
//	public static boolean isUnix() {
//		return System.getProperty("path.separator").equals(":");
//	}
////	public void drop(DropTargetDropEvent dtde) {	
////		Transferable tr = dtde.getTransferable();
////		
////		DataFlavor[] a = dtde.getCurrentDataFlavors();
////		for (int i = 0; i < a.length; i++) {
////			System.out.println(a[i]);
////		}
////		
////		java.util.List<File> list = null;
////		try {					    
////			if(isUnix()) {
////				DataFlavor nixFileDataFlavor = new DataFlavor("text/uri-list;class=java.lang.String");
////				String data = (String)tr.getTransferData(nixFileDataFlavor);
////				list = new LinkedList<File>();
////				for(StringTokenizer st = new StringTokenizer(data, "\r\n"); st.hasMoreTokens();) {
////				    String token = st.nextToken().trim();
////				    if(token.startsWith("#") || token.isEmpty())   {
////				         // comment line, by RFC 2483
////				         continue;
////				    }
////			         File file = new File(new URI(token));
////			         list.add(file);
////				}
////			}
////			else { //windows
////					DataFlavor[] flavors = tr.getTransferDataFlavors();
////					for (int i = 0; i < flavors.length; i++) {
////						if (flavors[i].isFlavorJavaFileListType()) {
////							dtde.acceptDrop(DnDConstants.ACTION_COPY);
////							list = (java.util.List<File>) tr.getTransferData(flavors[i]);							
////						}
////					}
////					dtde.rejectDrop();
////			}
////			for(FileDropListener l: listeners) {
////				l.notifyFileDrop(list);
////			}
////			dtde.dropComplete(true);
////			return;
////			
////		} catch (Exception e) {
////			e.printStackTrace();
////			dtde.rejectDrop();
////		}
////	}		  
//	@Override
//	public void filesDropped(File[] files) {
//		for(FileDropListener l: listeners) {
//			l.notifyFileDrop(files);
//		}
//	}
//}
