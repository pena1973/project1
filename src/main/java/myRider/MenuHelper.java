package myRider;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

class MenuHelper
{
    private static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener)
    {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action)
    {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    private static JMenuItem addMenuItem(JMenu parent, Action action)
    {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

    static void initHelpMenu(View view, JMenuBar menuBar)
    {
        JMenu helpMenu = new JMenu("Помощь");
        addMenuItem(helpMenu, "Настройки", view);
        menuBar.add(helpMenu);
        addMenuItem(helpMenu, "О программе", view);

    }

    static void initVocabularyMenu(View view, JMenuBar menuBar, Set<String> historyVocabularySet)
    {
        JMenu vocabularyMenu = new JMenu("Словарь");
        addMenuItem(vocabularyMenu, "Новый словарь", view);
        addMenuItem(vocabularyMenu, "Открыть словарь", view);
        addMenuItem(vocabularyMenu, "Сохранить словарь", view);
        addMenuItem(vocabularyMenu, "Сохранить словарь как...", view);

        if (historyVocabularySet.size() > 0)
        {
            vocabularyMenu.addSeparator();
        }

        Iterator iterator = historyVocabularySet.iterator();

        while (iterator.hasNext())
        {
            addMenuItem(vocabularyMenu, (String) iterator.next(), view);
        }
        vocabularyMenu.addSeparator();
        menuBar.add(vocabularyMenu);
    }

    static void initFileMenu(View view, JMenuBar menuBar, Set<String> historySet)
    {
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        addMenuItem(fileMenu, "Открыть файл", view);
        addMenuItem(fileMenu, "Закрыть файл", view);

        if (historySet.size() > 0)
        {
            fileMenu.addSeparator();
        }

        Iterator iterator = historySet.iterator();

        while (iterator.hasNext())
        {
            addMenuItem(fileMenu, (String) iterator.next() + " ", view);
        }

        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Выход", view);
    }
}
