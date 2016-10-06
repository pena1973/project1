package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();    // панель с двумя вкладками
    private JTextPane htmlTextPane = new JTextPane();      // компонент для визуального редактирования html
    private JEditorPane plainTextPane = new JEditorPane(); // компонент для редактирования html в виде

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    public View()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }
        catch (InstantiationException e)
        {
            ExceptionHandler.log(e);
        }
        catch (IllegalAccessException e)
        {
            ExceptionHandler.log(e);
        }
        catch (UnsupportedLookAndFeelException e)
        {
            ExceptionHandler.log(e);
        }

    }
    //  текста, он будет отображать код html

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public Controller getController()
    {
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        String commanda = actionEvent.getActionCommand();
        if (commanda.equals("Новый"))
        {
            controller.createNewDocument();
        } else if (commanda.equals("Открыть"))
        {
            controller.openDocument();
        } else if (commanda.equals("Сохранить"))
        {
            controller.saveDocument();
        } else if (commanda.equals("Сохранить как..."))
        {
            controller.saveDocumentAs();
        } else if (commanda.equals("Выход"))
        {
            exit();
        } else if (commanda.equals("О программе"))
        {
            showAbout();
        }
    }

    public void init()
    {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void exit()
    {
        controller.exit();
    }


    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor()
    {
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(800, 600));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui()
    {

        initMenuBar();
        initEditor();
        pack();
        //Если вместо указания размеров окна, вызвать метод pack(),
        // они будут подобраны оптимальным образом с учетом
        // предпочтений всех элементов, размещенных в этом окне.
    }

    public boolean canUndo()
    {
        return undoManager.canUndo();
    }

    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    public void selectedTabChanged()
    {
    if (tabbedPane.getSelectedIndex()==0){

        controller.setPlainText(plainTextPane.getText());
    }
    else if (tabbedPane.getSelectedIndex()==1){

        plainTextPane.setText(controller.getPlainText());
    }
        resetUndo();
    }

    public void undo()
    {

        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo()
    {

        try {
            undoManager.redo();
        } catch (CannotRedoException e)
        {
            ExceptionHandler.log(e);
        }

    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public boolean isHtmlTabSelected()
    {

        return (tabbedPane.getSelectedIndex() == 0);
    }

    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(tabbedPane, "Куку", "Заголовок куку", JOptionPane.INFORMATION_MESSAGE);
    }
}












