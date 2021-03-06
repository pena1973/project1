package myRider;

import myRider.listeners.DoubleClicListener;
import myRider.listeners.FrameListener;
import myRider.listeners.PopupClass;
import myRider.listeners.TabbedPaneChangeListener;
// http://math.sgu.ru/sites/chairs/prinf/materials/java/lesson8.htm
// https://www.pdflib.com/download/
// http://stackoverflow.com/questions/18098400/how-to-get-raw-text-from-pdf-file-using-java
// https://habrahabr.ru/post/83860/
// https://www.jetbrains.com/help/idea/2016.2/creating-and-opening-forms.html - редактор форм
// http://code.makery.ch/library/javafx-8-tutorial/ru/part1/
// http://stackoverflow.com/questions/30898039/clickable-text-from-jtextpane
// http://www.sbp-program.ru/java/sbp-jpopupmenu.htm
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;


public class View extends JFrame implements ActionListener
{
    private Controller controller;

    private JTextPane textPane = new JTextPane();      // текст
    private JTextPane vocabularyPane = new JTextPane(); // словарь
    private JScrollPane scrl1 = new JScrollPane(textPane); // скролл
    private JScrollPane scrl2 = new JScrollPane(vocabularyPane);

    private JPanel panel1 = createPanel(new TitledBorder("Текст")); //  для текста
    private JPanel panel2 = createPanel(new TitledBorder("Словарь")); //  для словаря
    private JPopupMenu popup = new JPopupMenu();
    private JSeparator divider = new JSeparator(SwingConstants.VERTICAL);// разделитель

    //

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
        if (commanda.equals("Новый словарь"))
        {
            controller.NewVocabulary();
        } else if (commanda.equals("Открыть словарь"))
        {
            controller.openVocabulary();
        } else if (commanda.equals("Открыть файл"))
        {
            controller.openDocument();
        } else if (commanda.equals("Закрыть файл"))
        {
            controller.closeDocument();
        } else if (commanda.equals("Сохранить словарь"))
        {
            controller.saveVocabulary();
        } else if (commanda.equals("Сохранить словарь как..."))
        {
            controller.saveVocabularuAs();
        } else if (commanda.equals("Выход"))
        {
            exit();
        } else if (commanda.equals("О программе"))
        {
            showAbout();

        } else if (commanda.equals("Настройки"))
        {
            showSettings();

        } else
        {
            String s = commanda.toString();
            if (s.substring(s.length() - 1, s.length()).equals(" "))
                controller.openDocument(s.trim());
            else
                controller.openVocabulary(s);
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

    public void showAbout()
    {
        controller.showDialogAbout();
     //   JOptionPane.showMessageDialog(textPane, "Программа Мой ридер. Автор Баринова Наталья. Отзывы сюда: pena@pisem.net  ", "О программе", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showSettings()
    {
        controller.showDialogSettings();
    }

    public void setSettings()
    {
        Map<String, String> map = controller.getSettings();

        if (map.isEmpty())
        {
            textPane.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            vocabularyPane.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        } else
        {

            String font = map.get("fonts");
            int style = (map.get("bold").equals("true") ? Font.BOLD : 0) + (map.get("italic").equals("true") ? Font.ITALIC : 0);
            int size = Integer.parseInt(map.get("size"));


            textPane.setFont(new Font(font, style, size));
            vocabularyPane.setFont(new Font(font, style, size));

        }
    }



    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar, controller.getHistory());
        MenuHelper.initVocabularyMenu(this, menuBar, controller.getVocabularyHistory());
        MenuHelper.initHelpMenu(this, menuBar);


        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    private JPanel createPanel(Border border)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), border));
        return panel;
    }

    public void initEditor()
    {

        panel1.setBackground(Color.getHSBColor(235, 215, 250));
        panel2.setBackground(Color.getHSBColor(220, 215, 250));

        textPane.setContentType("text");
        textPane.addMouseListener(new DoubleClicListener(this));
        textPane.addMouseListener(new PopupClass(this));
        vocabularyPane.setContentType("text");
        divider.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        panel1.add(scrl1, BorderLayout.EAST);
        panel2.add(scrl2, BorderLayout.WEST);


        add(panel1);
        add(divider);
        add(panel2);

        scrl1.setPreferredSize(new Dimension(800, 600));
        scrl2.setPreferredSize(new Dimension(200, 600));

        getContentPane().add(panel1, BorderLayout.WEST);
        getContentPane().add(divider, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.EAST);

        Dimension result = getSize();
    }

    public void initGui()
    {
        setTitle("Мой Ридер");
        initMenuBar();
        initEditor();
        pack();
        controller.resetDocument();
        controller.resetVocabulary();

        //Если вместо указания размеров окна, вызвать метод pack(),
        // они будут подобраны оптимальным образом с учетом
        // предпочтений всех элементов, размещенных в этом окне.


    }

    @Override
    public void doLayout()
    {
        super.doLayout();
        Dimension result = this.getSize();
        textPane.setSize(new Dimension((int) ((result.width) * 0.8), result.height));
        vocabularyPane.setSize(new Dimension((int) ((result.width) * 0.2), result.height));
        scrl1.setPreferredSize(new Dimension((int) ((result.width - 68) * 0.8), result.height));
        scrl2.setPreferredSize(new Dimension((int) ((result.width - 68) * 0.2), result.height));
    }

    public void updateTxt()
    {
        textPane.setDocument(controller.getDocument());
    }


    public void updateVocabulary()
    {
        vocabularyPane.setDocument(controller.getVocabulary());
    }

    public void textPaneMouseClicked(MouseEvent evt)
    {
        try
        {
            if (evt.getButton() == 1) // если первая кнопка
            {
                String wrd = null;
                int pt = textPane.viewToModel(evt.getPoint());
                int spt = Utilities.getWordStart(textPane, pt);
                int ept = Utilities.getWordEnd(textPane, pt);
                textPane.setSelectionStart(spt);
                textPane.setSelectionEnd(ept);
                wrd = textPane.getSelectedText();

                ArrayList<String> listWords = controller.getWords(wrd, "en-ru");
                popup.removeAll();
                for (String s : listWords)
                {
                    JMenuItem item;
                    popup.add(item = new JMenuItem(s));
                    item.setHorizontalTextPosition(JMenuItem.RIGHT);
                    final String finalWrd = wrd;
                    item.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            controller.addWord(finalWrd, e.getActionCommand().toString().trim());
                        }
                    });

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void openMenu(MouseEvent event)
    {
        if (event.isPopupTrigger())
        {
            popup.show(event.getComponent(),
                    event.getX(), event.getY());
        }
    }
}
