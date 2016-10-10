package myRider;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

class MenuHelper
{
    private static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    private static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

     static void initToolsMenu(View view, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Инструменты");
        addMenuItem(helpMenu, "Закладка", view);
        addMenuItem(helpMenu, "Новая глава", view);
        menuBar.add(helpMenu);
    }
     static void initHelpMenu(View view, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Помощь");
       // addMenuItem(helpMenu, "Ключ", view);
        addMenuItem(helpMenu, "Настройки", view);
      //  addMenuItem(helpMenu, "Язык интерфейса", view);
        menuBar.add(helpMenu);
        addMenuItem(helpMenu, "О программе", view);

    }

     static void initVocabularyMenu(View view, JMenuBar menuBar) {
        JMenu vocabularyMenu = new JMenu("Словарь");
        addMenuItem(vocabularyMenu, "Новый словарь", view);
        addMenuItem(vocabularyMenu, "Открыть словарь", view);
        addMenuItem(vocabularyMenu, "Сохранить словарь", view);
        addMenuItem(vocabularyMenu, "Сохранить словарь как...", view);
        menuBar.add(vocabularyMenu);
    }

     static void initFileMenu(View view, JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        addMenuItem(fileMenu, "Открыть файл", view);
        addMenuItem(fileMenu, "Закрыть файл", view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Выход", view);
    }
}
