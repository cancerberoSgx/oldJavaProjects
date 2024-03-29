package org.sgx.j2stests.addressbook;


/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/* Imports */
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.ResourceBundle;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * AddressBookExample is an example that uses <code>org.eclipse.swt</code>
 * libraries to implement a simple address book. This application has save,
 * load, sorting, and searching functions common to basic address books.
 */
public class SWTAddressBook {  

  private Shell shell;

  private Table table;

  private SearchDialog searchDialog;

//  private File file;

  private boolean isModified;

  private String[] copyBuffer;

  private int lastSortColumn = -1;

  private static final String DELIMITER = "\t";

  private static final String[] columnNames = {
      "Last_name","First_name",
      "Business_phone","Home_phone",
      "Email", "Fax" };

  public static void main(String[] args) {
    Display display = new Display();
    SWTAddressBook application = new SWTAddressBook();
    Shell shell = application.open(display);
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }

  public Shell open(Display display) {
    shell = new Shell(display);
    shell.setLayout(new FillLayout());
    shell.addShellListener(new ShellAdapter() {
      public void shellClosed(ShellEvent e) {
        e.doit = closeAddressBook();
      }
    });

    createMenuBar();

    searchDialog = new SearchDialog(shell);
    searchDialog.setSearchAreaNames(columnNames);
    searchDialog.setSearchAreaLabel("Column");
    searchDialog.addFindListener(new FindListener() {
      public boolean find() {
        return findEntry();
      }
    });

    table = new Table(shell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
    table.setHeaderVisible(true);
    table.setMenu(createPopUpMenu());
    table.addSelectionListener(new SelectionAdapter() {
      public void widgetDefaultSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length > 0)
          editEntry(items[0]);
      }
    });
    for (int i = 0; i < columnNames.length; i++) {
      TableColumn column = new TableColumn(table, SWT.NONE);
      column.setText(columnNames[i]);
      column.setWidth(150);
      final int columnIndex = i;
      column.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
          sort(columnIndex);
        }
      });
    }

    newAddressBook();

    shell.setSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, 300);
    shell.open();
    return shell;
  }

  private boolean closeAddressBook() {
    if (isModified) {
      // ask user if they want to save current address book
      MessageBox box = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES
          | SWT.NO | SWT.CANCEL);
      box.setText(shell.getText());
      box.setMessage("Close_save");

      int choice = box.open();
      if (choice == SWT.CANCEL) {
        return false;
      } else if (choice == SWT.YES) {
        if (!save())
          return false;
      }
    }

    TableItem[] items = table.getItems();
    for (int i = 0; i < items.length; i++) {
      items[i].dispose();
    }

    return true;
  }

  /**
   * Creates the menu at the top of the shell where most of the programs
   * functionality is accessed.
   * 
   * @return The <code>Menu</code> widget that was created
   */
  private Menu createMenuBar() {
    Menu menuBar = new Menu(shell, SWT.BAR);
    shell.setMenuBar(menuBar);

    // create each header and subMenu for the menuBar
    createFileMenu(menuBar);
    createEditMenu(menuBar);
    createSearchMenu(menuBar);
    createHelpMenu(menuBar);

    return menuBar;
  }

  /**
   * Converts an encoded <code>String</code> to a String array representing
   * a table entry.
   */
  private String[] decodeLine(String line) {
    if (line == null)
      return null;

    String[] parsedLine = new String[table.getColumnCount()];
    for (int i = 0; i < parsedLine.length - 1; i++) {
      int index = line.indexOf(DELIMITER);
      if (index > -1) {
        parsedLine[i] = line.substring(0, index);
        line = line
            .substring(index + DELIMITER.length(), line.length());
      } else {
        return null;
      }
    }

    if (line.indexOf(DELIMITER) != -1)
      return null;

    parsedLine[parsedLine.length - 1] = line;

    return parsedLine;
  }

  private void displayError(String msg) {
    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
    box.setMessage(msg);
    box.open();
  }

  private void editEntry(TableItem item) {
    DataEntryDialog dialog = new DataEntryDialog(shell);
    dialog.setLabels(columnNames);
    String[] values = new String[table.getColumnCount()];
    for (int i = 0; i < values.length; i++) {
      values[i] = item.getText(i);
    }
    dialog.setValues(values);
    values = dialog.open();
    if (values != null) {
      item.setText(values);
      isModified = true;
    }
  }

  private String encodeLine(String[] tableItems) {
    String line = "";
    for (int i = 0; i < tableItems.length - 1; i++) {
      line += tableItems[i] + DELIMITER;
    }
    line += tableItems[tableItems.length - 1] + "\n";

    return line;
  }

  private boolean findEntry() {
    Cursor waitCursor = new Cursor(shell.getDisplay(), SWT.CURSOR_WAIT);
    shell.setCursor(waitCursor);

    boolean matchCase = searchDialog.getMatchCase();
    boolean matchWord = searchDialog.getMatchWord();
    String searchString = searchDialog.getSearchString();
    int column = searchDialog.getSelectedSearchArea();

    searchString = matchCase ? searchString : searchString.toLowerCase();

    boolean found = false;
    if (searchDialog.getSearchDown()) {
      for (int i = table.getSelectionIndex() + 1; i < table
          .getItemCount(); i++) {
        if (found = findMatch(searchString, table.getItem(i), column,
            matchWord, matchCase)) {
          table.setSelection(i);
          break;
        }
      }
    } else {
      for (int i = table.getSelectionIndex() - 1; i > -1; i--) {
        if (found = findMatch(searchString, table.getItem(i), column,
            matchWord, matchCase)) {
          table.setSelection(i);
          break;
        }
      }
    }

    shell.setCursor(null);
    if (waitCursor != null)
      waitCursor.dispose();

    return found;
  }

  private boolean findMatch(String searchString, TableItem item, int column,
      boolean matchWord, boolean matchCase) {

    String tableText = matchCase ? item.getText(column) : item.getText(
        column).toLowerCase();
    if (matchWord) {
      if (tableText != null && tableText.equals(searchString)) {
        return true;
      }

    } else {
      if (tableText != null && tableText.indexOf(searchString) != -1) {
        return true;
      }
    }
    return false;
  }

  private void newAddressBook() {
    shell.setText("Title_bar"
        + "New_title");
//    file = null;
    isModified = false;
  }

  private void newEntry() {
    DataEntryDialog dialog = new DataEntryDialog(shell);
    dialog.setLabels(columnNames);
    String[] data = dialog.open();
    if (data != null) {
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(data);
      isModified = true;
    }
  }

  private void openAddressBook() {
    FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);

    fileDialog.setFilterExtensions(new String[] { "*.adr;", "*.*" });
    fileDialog.setFilterNames(new String[] {
        "Book_filter_name" + " (*.adr)",
        "All_filter_name" + " (*.*)" });
    String name = fileDialog.open();

    if (name == null)
      return;
//    File file = new File(name);
//    if (!file.exists()) {
//      displayError("File" + file.getName()
//          + " " + "Does_not_exist");
//      return;
//    }

//    Cursor waitCursor = new Cursor(shell.getDisplay(), SWT.CURSOR_WAIT);
//    shell.setCursor(waitCursor);
//
//    FileReader fileReader = null;
//    BufferedReader bufferedReader = null;
//    String[] data = new String[0];
//    try {
//      fileReader = new FileReader(file.getAbsolutePath());
//      bufferedReader = new BufferedReader(fileReader);
//      String nextLine = bufferedReader.readLine();
//      while (nextLine != null) {
//        String[] newData = new String[data.length + 1];
//        System.arraycopy(data, 0, newData, 0, data.length);
//        newData[data.length] = nextLine;
//        data = newData;
//        nextLine = bufferedReader.readLine();
//      }
//    } catch (FileNotFoundException e) {
//      displayError("File_not_found" + "\n"
//          + file.getName());
//      return;
//    } catch (IOException e) {
//      displayError("IO_error_read" + "\n"
//          + file.getName());
//      return;
//    } finally {
//
//      shell.setCursor(null);
//      waitCursor.dispose();
//
//      if (fileReader != null) {
//        try {
//          fileReader.close();
//        } catch (IOException e) {
//          displayError("IO_error_close"
//              + "\n" + file.getName());
//          return;
//        }
//      }
//    }

//    String[][] tableInfo = new String[data.length][table.getColumnCount()];
//    int writeIndex = 0;
//    for (int i = 0; i < data.length; i++) {
//      String[] line = decodeLine(data[i]);
//      if (line != null)
//        tableInfo[writeIndex++] = line;
//    }
//    if (writeIndex != data.length) {
//      String[][] result = new String[writeIndex][table.getColumnCount()];
//      System.arraycopy(tableInfo, 0, result, 0, writeIndex);
//      tableInfo = result;
//    }
//    Arrays.sort(tableInfo, new RowComparator(0));
//
//    for (int i = 0; i < tableInfo.length; i++) {
//      TableItem item = new TableItem(table, SWT.NONE);
//      item.setText(tableInfo[i]);
//    }
//    shell.setText("Title_bar"
//        + fileDialog.getFileName());
//    isModified = false;
//    this.file = file;
  }

  private boolean save() {
//    if (file == null)
//      return saveAs();
//
//    Cursor waitCursor = new Cursor(shell.getDisplay(), SWT.CURSOR_WAIT);
//    shell.setCursor(waitCursor);
//
//    TableItem[] items = table.getItems();
//    String[] lines = new String[items.length];
//    for (int i = 0; i < items.length; i++) {
//      String[] itemText = new String[table.getColumnCount()];
//      for (int j = 0; j < itemText.length; j++) {
//        itemText[j] = items[i].getText(j);
//      }
//      lines[i] = encodeLine(itemText);
//    }
//
//    FileWriter fileWriter = null;
//    try {
//      fileWriter = new FileWriter(file.getAbsolutePath(), false);
//      for (int i = 0; i < lines.length; i++) {
//        fileWriter.write(lines[i]);
//      }
//    } catch (FileNotFoundException e) {
//      displayError("File_not_found" + "\n"
//          + file.getName());
//      return false;
//    } catch (IOException e) {
//      displayError("IO_error_write" + "\n"
//          + file.getName());
//      return false;
//    } finally {
//      shell.setCursor(null);
//      waitCursor.dispose();
//
//      if (fileWriter != null) {
//        try {
//          fileWriter.close();
//        } catch (IOException e) {
//          displayError("IO_error_close"
//              + "\n" + file.getName());
//          return false;
//        }
//      }
//    }
//
//    shell.setText("Title_bar" + file.getName());
//    isModified = false;
    return true;
  }

  private boolean saveAs() {
//
//    FileDialog saveDialog = new FileDialog(shell, SWT.SAVE);
//    saveDialog.setFilterExtensions(new String[] { "*.adr;", "*.*" });
//    saveDialog.setFilterNames(new String[] { "Address Books (*.adr)",
//        "All Files " });
//
//    saveDialog.open();
//    String name = saveDialog.getFileName();
//
//    if (name.equals(""))
//      return false;
//
//    if (name.indexOf(".adr") != name.length() - 4) {
//      name += ".adr";
//    }
//
//    File file = new File(saveDialog.getFilterPath(), name);
//    if (file.exists()) {
//      MessageBox box = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES
//          | SWT.NO);
//      box.setText("Save_as_title");
//      box.setMessage("File" + file.getName()
//          + " " + "Query_overwrite");
//      if (box.open() != SWT.YES) {
//        return false;
//      }
//    }
//    this.file = file;
    return save();
  }

  private void sort(int column) {
    if (table.getItemCount() <= 1)
      return;

    TableItem[] items = table.getItems();
    String[][] data = new String[items.length][table.getColumnCount()];
    for (int i = 0; i < items.length; i++) {
      for (int j = 0; j < table.getColumnCount(); j++) {
        data[i][j] = items[i].getText(j);
      }
    }

//    Arrays.sort(data, new RowComparator(column));

    if (lastSortColumn != column) {
      for (int i = 0; i < data.length; i++) {
        items[i].setText(data[i]);
      }
      lastSortColumn = column;
    } else {
      // reverse order if the current column is selected again
      int j = data.length - 1;
      for (int i = 0; i < data.length; i++) {
        items[i].setText(data[j--]);
      }
      lastSortColumn = -1;
    }

  }

  /**
   * Creates all the items located in the File submenu and associate all the
   * menu items with their appropriate functions.
   * 
   * @param menuBar
   *            Menu the <code>Menu</code> that file contain the File
   *            submenu.
   */
  private void createFileMenu(Menu menuBar) {
    // File menu.
    MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
    item.setText("File_menu_title");
    Menu menu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(menu);
    /**
     * Adds a listener to handle enabling and disabling some items in the
     * Edit submenu.
     */
    menu.addMenuListener(new MenuAdapter() {
      public void menuShown(MenuEvent e) {
        Menu menu = (Menu) e.widget;
        MenuItem[] items = menu.getItems();
        items[1].setEnabled(table.getSelectionCount() != 0); // edit
                                    // contact
//        items[5].setEnabled((file != null) && isModified); // save
//        items[6].setEnabled(table.getItemCount() != 0); // save as
      }
    });

    // File -> New Contact
    MenuItem subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("New_contact");
    subItem.setAccelerator(SWT.MOD1 + 'N');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        newEntry();
      }
    });
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Edit_contact");
    subItem.setAccelerator(SWT.MOD1 + 'E');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        editEntry(items[0]);
      }
    });

    new MenuItem(menu, SWT.SEPARATOR);

    // File -> New Address Book
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("New_address_book");
    subItem.setAccelerator(SWT.MOD1 + 'B');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        if (closeAddressBook()) {
          newAddressBook();
        }
      }
    });

    // File -> Open
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Open_address_book");
    subItem.setAccelerator(SWT.MOD1 + 'O');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        if (closeAddressBook()) {
          openAddressBook();
        }
      }
    });

    // File -> Save.
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Save_address_book");
    subItem.setAccelerator(SWT.MOD1 + 'S');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        save();
      }
    });

    // File -> Save As.
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Save_book_as");
    subItem.setAccelerator(SWT.MOD1 + 'A');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        saveAs();
      }
    });

    new MenuItem(menu, SWT.SEPARATOR);

    // File -> Exit.
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Exit");
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        shell.close();
      }
    });
  }

  /**
   * Creates all the items located in the Edit submenu and associate all the
   * menu items with their appropriate functions.
   * 
   * @param menuBar
   *            Menu the <code>Menu</code> that file contain the Edit
   *            submenu.
   * 
   * @see #createSortMenu()
   */
  private MenuItem createEditMenu(Menu menuBar) {
    // Edit menu.
    MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
    item.setText("Edit_menu_title");
    Menu menu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(menu);

    /**
     * Add a listener to handle enabling and disabling some items in the
     * Edit submenu.
     */
    menu.addMenuListener(new MenuAdapter() {
      public void menuShown(MenuEvent e) {
        Menu menu = (Menu) e.widget;
        MenuItem[] items = menu.getItems();
        int count = table.getSelectionCount();
        items[0].setEnabled(count != 0); // edit
        items[1].setEnabled(count != 0); // copy
        items[2].setEnabled(copyBuffer != null); // paste
        items[3].setEnabled(count != 0); // delete
        items[5].setEnabled(table.getItemCount() != 0); // sort
      }
    });

    // Edit -> Edit
    MenuItem subItem = new MenuItem(menu, SWT.CASCADE);
    subItem.setText("Edit");
    subItem.setAccelerator(SWT.MOD1 + 'E');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        editEntry(items[0]);
      }
    });

    // Edit -> Copy
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Copy");
    subItem.setAccelerator(SWT.MOD1 + 'C');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        copyBuffer = new String[table.getColumnCount()];
        for (int i = 0; i < copyBuffer.length; i++) {
          copyBuffer[i] = items[0].getText(i);
        }
      }
    });

    // Edit -> Paste
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Paste");
    subItem.setAccelerator(SWT.MOD1 + 'V');
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        if (copyBuffer == null)
          return;
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(copyBuffer);
        isModified = true;
      }
    });

    // Edit -> Delete
    subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("Delete");
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        items[0].dispose();
        isModified = true;
      }
    });

    new MenuItem(menu, SWT.SEPARATOR);

    // Edit -> Sort(Cascade)
    subItem = new MenuItem(menu, SWT.CASCADE);
    subItem.setText("Sort");
    Menu submenu = createSortMenu();
    subItem.setMenu(submenu);

    return item;

  }

  /**
   * Creates all the items located in the Sort cascading submenu and associate
   * all the menu items with their appropriate functions.
   * 
   * @return Menu The cascading menu with all the sort menu items on it.
   */
  private Menu createSortMenu() {
    Menu submenu = new Menu(shell, SWT.DROP_DOWN);
    MenuItem subitem;
    for (int i = 0; i < columnNames.length; i++) {
      subitem = new MenuItem(submenu, SWT.NULL);
      subitem.setText(columnNames[i]);
      final int column = i;
      subitem.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
          sort(column);
        }
      });

    }

    return submenu;
  }

  /**
   * Creates all the items located in the Search submenu and associate all the
   * menu items with their appropriate functions.
   * 
   * @param menuBar
   *            Menu the <code>Menu</code> that file contain the Search
   *            submenu.
   */
  private void createSearchMenu(Menu menuBar) {
    // Search menu.
    MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
    item.setText("Search_menu_title");
    Menu searchMenu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(searchMenu);

    // Search -> Find...
    item = new MenuItem(searchMenu, SWT.NULL);
    item.setText("Find");
    item.setAccelerator(SWT.MOD1 + 'F');
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        searchDialog.setMatchCase(false);
        searchDialog.setMatchWord(false);
        searchDialog.setSearchDown(true);
        searchDialog.setSearchString("");
        searchDialog.setSelectedSearchArea(0);
        searchDialog.open();
      }
    });

    // Search -> Find Next
    item = new MenuItem(searchMenu, SWT.NULL);
    item.setText("Find_next");
    item.setAccelerator(SWT.F3);
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        searchDialog.open();
      }
    });
  }

  /**
   * Creates all items located in the popup menu and associates all the menu
   * items with their appropriate functions.
   * 
   * @return Menu The created popup menu.
   */
  private Menu createPopUpMenu() {
    Menu popUpMenu = new Menu(shell, SWT.POP_UP);

    /**
     * Adds a listener to handle enabling and disabling some items in the
     * Edit submenu.
     */
    popUpMenu.addMenuListener(new MenuAdapter() {
      public void menuShown(MenuEvent e) {
        Menu menu = (Menu) e.widget;
        MenuItem[] items = menu.getItems();
        int count = table.getSelectionCount();
        items[2].setEnabled(count != 0); // edit
        items[3].setEnabled(count != 0); // copy
        items[4].setEnabled(copyBuffer != null); // paste
        items[5].setEnabled(count != 0); // delete
        items[7].setEnabled(table.getItemCount() != 0); // find
      }
    });

    // New
    MenuItem item = new MenuItem(popUpMenu, SWT.CASCADE);
    item.setText("Pop_up_new");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        newEntry();
      }
    });

    new MenuItem(popUpMenu, SWT.SEPARATOR);

    // Edit
    item = new MenuItem(popUpMenu, SWT.CASCADE);
    item.setText("Pop_up_edit");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        editEntry(items[0]);
      }
    });

    // Copy
    item = new MenuItem(popUpMenu, SWT.CASCADE);
    item.setText("Pop_up_copy");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        copyBuffer = new String[table.getColumnCount()];
        for (int i = 0; i < copyBuffer.length; i++) {
          copyBuffer[i] = items[0].getText(i);
        }
      }
    });

    // Paste
    item = new MenuItem(popUpMenu, SWT.CASCADE);
    item.setText("Pop_up_paste");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        if (copyBuffer == null)
          return;
        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(copyBuffer);
        isModified = true;
      }
    });

    // Delete
    item = new MenuItem(popUpMenu, SWT.CASCADE);
    item.setText("Pop_up_delete");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        TableItem[] items = table.getSelection();
        if (items.length == 0)
          return;
        items[0].dispose();
        isModified = true;
      }
    });

    new MenuItem(popUpMenu, SWT.SEPARATOR);

    // Find...
    item = new MenuItem(popUpMenu, SWT.NULL);
    item.setText("Pop_up_find");
    item.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        searchDialog.open();
      }
    });

    return popUpMenu;
  }

  /**
   * Creates all the items located in the Help submenu and associate all the
   * menu items with their appropriate functions.
   * 
   * @param menuBar
   *            Menu the <code>Menu</code> that file contain the Help
   *            submenu.
   */
  private void createHelpMenu(Menu menuBar) {

    // Help Menu
    MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
    item.setText("Help_menu_title");
    Menu menu = new Menu(shell, SWT.DROP_DOWN);
    item.setMenu(menu);

    // Help -> About Text Editor
    MenuItem subItem = new MenuItem(menu, SWT.NULL);
    subItem.setText("About");
    subItem.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        MessageBox box = new MessageBox(shell, SWT.NONE);
        box.setText("About_1"
            + shell.getText());
        box.setMessage(shell.getText()
            + "About_2");
        box.open();
      }
    });
  }

  /**
   * To compare entries (rows) by the given column
   */
  private class RowComparator implements Comparator {
    private int column;

    /**
     * Constructs a RowComparator given the column index
     * 
     * @param col
     *            The index (starting at zero) of the column
     */
    public RowComparator(int col) {
      column = col;
    }

    /**
     * Compares two rows (type String[]) using the specified column entry.
     * 
     * @param obj1
     *            First row to compare
     * @param obj2
     *            Second row to compare
     * @return negative if obj1 less than obj2, positive if obj1 greater
     *         than obj2, and zero if equal.
     */
    public int compare(Object obj1, Object obj2) {
      String[] row1 = (String[]) obj1;
      String[] row2 = (String[]) obj2;

      return row1[column].compareTo(row2[column]);
    }
  }
}

/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
/**
 * SearchDialog is a simple class that uses <code>org.eclipse.swt</code>
 * libraries to implement a basic search dialog.
 */
class SearchDialog {

  Shell shell;

  Text searchText;

  Combo searchArea;

  Label searchAreaLabel;

  Button matchCase;

  Button matchWord;

  Button findButton;

  Button down;

  FindListener findHandler;

  /**
   * Class constructor that sets the parent shell and the table widget that
   * the dialog will search.
   * 
   * @param parent
   *            Shell The shell that is the parent of the dialog.
   */
  public SearchDialog(Shell parent) {
    shell = new Shell(parent, SWT.CLOSE | SWT.BORDER | SWT.TITLE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    shell.setLayout(layout);
    shell.setText("Search");
    shell.addShellListener(new ShellAdapter() {
      public void shellClosed(ShellEvent e) {
        // don't dispose of the shell, just hide it for later use
        e.doit = false;
        shell.setVisible(false);
      }
    });

    Label label = new Label(shell, SWT.LEFT);
    label.setText("Dialog_find_what");
    searchText = new Text(shell, SWT.BORDER);
    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
    gridData.widthHint = 200;
    searchText.setLayoutData(gridData);
    searchText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        boolean enableFind = (searchText.getCharCount() != 0);
        findButton.setEnabled(enableFind);
      }
    });

    searchAreaLabel = new Label(shell, SWT.LEFT);
    searchArea = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
    gridData = new GridData(GridData.FILL_HORIZONTAL);
    gridData.widthHint = 200;
    searchArea.setLayoutData(gridData);

    matchCase = new Button(shell, SWT.CHECK);
    matchCase.setText("Dialog_match_case");
    gridData = new GridData();
    gridData.horizontalSpan = 2;
    matchCase.setLayoutData(gridData);

    matchWord = new Button(shell, SWT.CHECK);
    matchWord.setText("Dialog_match_word");
    gridData = new GridData();
    gridData.horizontalSpan = 2;
    matchWord.setLayoutData(gridData);

    Group direction = new Group(shell, SWT.NONE);
    gridData = new GridData();
    gridData.horizontalSpan = 2;
    direction.setLayoutData(gridData);
    direction.setLayout(new FillLayout());
    direction.setText("Dialog_direction");

    Button up = new Button(direction, SWT.RADIO);
    up.setText("Dialog_dir_up");
    up.setSelection(false);

    down = new Button(direction, SWT.RADIO);
    down.setText("Dialog_dir_down");
    down.setSelection(true);

    Composite composite = new Composite(shell, SWT.NONE);
    gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
    gridData.horizontalSpan = 2;
    composite.setLayoutData(gridData);
    layout = new GridLayout();
    layout.numColumns = 2;
    layout.makeColumnsEqualWidth = true;
    composite.setLayout(layout);

    findButton = new Button(composite, SWT.PUSH);
    findButton.setText("Dialog_find");
    findButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    findButton.setEnabled(false);
    findButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        if (!findHandler.find()) {
          MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION
              | SWT.OK | SWT.PRIMARY_MODAL);
          box.setText(shell.getText());
          box.setMessage("Cannot_find"
              + "\"" + searchText.getText() + "\"");
          box.open();
        }
      }
    });

    Button cancelButton = new Button(composite, SWT.PUSH);
    cancelButton.setText("Cancel");
    cancelButton.setLayoutData(new GridData(
        GridData.HORIZONTAL_ALIGN_BEGINNING));
    cancelButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        shell.setVisible(false);
      }
    });

    shell.pack();
  }

  public String getSearchAreaLabel(String label) {
    return searchAreaLabel.getText();
  }

  public String[] getsearchAreaNames() {
    return searchArea.getItems();
  }

  public boolean getMatchCase() {
    return matchCase.getSelection();
  }

  public boolean getMatchWord() {
    return matchWord.getSelection();
  }

  public String getSearchString() {
    return searchText.getText();
  }

  public boolean getSearchDown() {
    return down.getSelection();
  }

  public int getSelectedSearchArea() {
    return searchArea.getSelectionIndex();
  }

  public void open() {
    if (shell.isVisible()) {
      shell.setFocus();
    } else {
      shell.open();
    }
    searchText.setFocus();
  }

  public void setSearchAreaNames(String[] names) {
    for (int i = 0; i < names.length; i++) {
      searchArea.add(names[i]);
    }
    searchArea.select(0);
  }

  public void setSearchAreaLabel(String label) {
    searchAreaLabel.setText(label);
  }

  public void setMatchCase(boolean match) {
    matchCase.setSelection(match);
  }

  public void setMatchWord(boolean match) {
    matchWord.setSelection(match);
  }

  public void setSearchDown(boolean searchDown) {
    down.setSelection(searchDown);
  }

  public void setSearchString(String searchString) {
    searchText.setText(searchString);
  }

  public void setSelectedSearchArea(int index) {
    searchArea.select(index);
  }

  public void addFindListener(FindListener listener) {
    this.findHandler = listener;
  }

  public void removeFindListener(FindListener listener) {
    this.findHandler = null;
  }
}

/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
interface FindListener {

  public boolean find();

}

/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
/**
 * DataEntryDialog class uses <code>org.eclipse.swt</code> libraries to
 * implement a dialog that accepts basic personal information that is added to a
 * <code>Table</code> widget or edits a <code>TableItem</code> entry to
 * represent the entered data.
 */
class DataEntryDialog {

  Shell shell;

  String[] values;

  String[] labels;

  public DataEntryDialog(Shell parent) {
    shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
    shell.setLayout(new GridLayout());
  }

  private void addTextListener(final Text text) {
    text.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        Integer index = (Integer) (text.getData("index"));
        values[index.intValue()] = text.getText();
      }
    });
  }

  private void createControlButtons() {
    Composite composite = new Composite(shell, SWT.NULL);
    composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);

    Button okButton = new Button(composite, SWT.PUSH);
    okButton.setText("OK");
    okButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        shell.close();
      }
    });

    Button cancelButton = new Button(composite, SWT.PUSH);
    cancelButton.setText("Cancel");
    cancelButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        values = null;
        shell.close();
      }
    });

    shell.setDefaultButton(okButton);
  }

  private void createTextWidgets() {
    if (labels == null)
      return;

    Composite composite = new Composite(shell, SWT.NULL);
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);

    if (values == null)
      values = new String[labels.length];

    for (int i = 0; i < labels.length; i++) {
      Label label = new Label(composite, SWT.RIGHT);
      label.setText(labels[i]);
      Text text = new Text(composite, SWT.BORDER);
      GridData gridData = new GridData();
      gridData.widthHint = 400;
      text.setLayoutData(gridData);
      if (values[i] != null) {
        text.setText(values[i]);
      }
      text.setData("index", new Integer(i));
      addTextListener(text);
    }
  }

  public String[] getLabels() {
    return labels;
  }

  public String getTitle() {
    return shell.getText();
  }

  /**
   * Returns the contents of the <code>Text</code> widgets in the dialog in
   * a <code>String</code> array.
   * 
   * @return String[] The contents of the text widgets of the dialog. May
   *         return null if all text widgets are empty.
   */
  public String[] getValues() {
    return values;
  }

  /**
   * Opens the dialog in the given state. Sets <code>Text</code> widget
   * contents and dialog behaviour accordingly.
   * 
   * @param dialogState
   *            int The state the dialog should be opened in.
   */
  public String[] open() {
    createTextWidgets();
    createControlButtons();
    shell.pack();
    shell.open();
    Display display = shell.getDisplay();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }

    return getValues();
  }

  public void setLabels(String[] labels) {
    this.labels = labels;
  }

  public void setTitle(String title) {
    shell.setText(title);
  }

  /**
   * Sets the values of the <code>Text</code> widgets of the dialog to the
   * values supplied in the parameter array.
   * 
   * @param itemInfo
   *            String[] The values to which the dialog contents will be set.
   */
  public void setValues(String[] itemInfo) {
    if (labels == null)
      return;

    if (values == null)
      values = new String[labels.length];

    int numItems = Math.min(values.length, itemInfo.length);
    for (int i = 0; i < numItems; i++) {
      values[i] = itemInfo[i];
    }
  }
}


           
 