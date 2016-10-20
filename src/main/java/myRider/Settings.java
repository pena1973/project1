package myRider;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Settings extends JDialog
{
    private static volatile Settings dialog = null; // потоков нет, но на будущее

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField userNameTextField;
    private JTextField keyYandexTextField;
    private JCheckBox showTipsCheckBox;
    private JComboBox fontsComboBox;
    private JCheckBox boldCheckBox;
    private JCheckBox italicCheckBox;
    private JComboBox sizeComboBox;
    private JList list;

    private Controller controller;


    private Settings()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK()
    {
        //  userNameTextField.getText()
        Map<String, String> map = new HashMap<>();
        map.put("userName", userNameTextField.getText());
        map.put("keyYandex", keyYandexTextField.getText());
        map.put("showTips", showTipsCheckBox.isSelected() ? "true" : "false");
        map.put("fonts", fontsComboBox.getSelectedItem().toString());
        map.put("size", sizeComboBox.getSelectedItem().toString());
        map.put("bold", boldCheckBox.isSelected() ? "true" : "false");
        map.put("italic", italicCheckBox.isSelected() ? "true" : "false");
        controller.saveSettings(map);
        controller.addTips();
        controller.setSettings();
        controller.initKey();
        dispose();
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }

    //  псевдо конструктор, делаем диалог синглтоном и только тогда когда его вызвали.
    public static Settings showDialog(Controller controller)
    {
        if (dialog == null)
        {
            synchronized (Settings.class)
            {
                dialog = new Settings();
                dialog.controller = controller;
                dialog.setTitle("Настройки");
                dialog.pack();
            }
        }

        Map<String, String> map = controller.getSettings();
        if (!map.isEmpty())
        {
            dialog.userNameTextField.setText(map.get("userName"));
            dialog.keyYandexTextField.setText(map.get("keyYandex"));
            dialog.showTipsCheckBox.setSelected(map.get("showTips").equals("true"));
            dialog.fontsComboBox.setSelectedItem(map.get("fonts"));
            dialog.sizeComboBox.setSelectedItem(map.get("size"));
            dialog.boldCheckBox.setSelected(map.get("bold").equals("true"));
            dialog.italicCheckBox.setSelected(map.get("italic").equals("true"));

        }

        dialog.setVisible(true);
        return dialog;
    }

    private void createUIComponents()
    {
        // // TODO: place custom component creation code here
    }


}
