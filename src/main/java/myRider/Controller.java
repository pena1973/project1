package myRider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Iterator;


public class Controller
{
    private View view;
    private Settings settings;
    private About about;
    private String key;
    private String directory;
    private Model model;

    //    private DefaultStyledDocument document;
    private DefaultStyledDocument documentVocabulary;
    private DefaultStyledDocument document;
//    private StyledDocument documentVocabulary;

    private File currentFile;
    private File currentFileVocabulary;

    private File propertyFile = new File(System.getProperty("user.dir") + "\\property.txt");
    private File historyFile = new File(System.getProperty("user.dir") + "\\history.txt");
    private File historyVokabularyFile = new File(System.getProperty("user.dir") + "\\historyVabulary.txt");


    // хранение листа истории открытия файлов
    private Set<String> historySet = new HashSet<>();
    private Set<String> historyVokabularySet = new HashSet<>();

    // соединение
    private static HttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    private static Header contentType = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");

    //  общие
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
    }

    public static void main(String[] args)
    {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);


        view.setController(controller);
        view.init();
        controller.init();
        controller.initKey();

    }

    public void init()
    {
        NewVocabulary();
    }

    public void initKey()
    {
        Map<String, String> map = getSettings();
        key = map.get("keyYandex");
    }

    public void exit()
    {

        System.exit(0);
        //Исходя из общепринятой практики и установленных стандартов код выхода равный 0 — сигнализирует об успешном выполнении задачи.
    }

    //  все методы about
    public void showDialogAbout()
    {
        about.showDialog(this);
    }

    //  все методы настроек
    public void showDialogSettings()
    {
        settings.showDialog(this);
    }

    public void saveSettings(Map<String, String> map)
    {
        //    File file = new File("C:\\Project1\\property.txt");

        try (PrintWriter out = new PrintWriter(propertyFile.getAbsoluteFile()))
        {
            if (!propertyFile.exists())
            {
                propertyFile.createNewFile();
            }

            //Записываем текст у файл
            for (Map.Entry<String, String> e : map.entrySet())
            {
                out.println(e.getKey() + "=" + e.getValue());
            }
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }

    }

    public Map<String, String> getSettings()
    {

        Map<String, String> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(propertyFile)))
        {
            String c;
            while ((c = reader.readLine()) != null)
            {
                String[] prop = c.split("=");
                map.put(prop[0], prop.length > 1 ? prop[1] : "");
            }
        }
        catch (IOException e)
        {

            ExceptionHandler.log(e);
        }
        return map;
    }

    public void setSettings()
    {
        view.setSettings();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    //  все методы текстового документа
    public void resetDocument()
    {
        StyledEditorKit htmlKit = new StyledEditorKit();
        document = (DefaultStyledDocument) htmlKit.createDefaultDocument();
        view.setSettings();
        view.updateTxt();
    }

    public void openDocument()
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new MyFileFilter());
        int n = jFileChooser.showOpenDialog(view);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            StyledEditorKit htmlKit = new StyledEditorKit();

            try (FileReader reader = new FileReader(currentFile))
            {
                htmlKit.read(reader, document, 0);
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
            addTips();
        }

    }

    public void openDocument(String pathFile)
    {
        if (pathFile == null)
            openDocument();
        else
        {
            currentFile = new File(pathFile);

            if (currentFile.exists())
            {
                resetDocument();
                view.setTitle(currentFile.getName());
                StyledEditorKit htmlKit = new StyledEditorKit();

                try (FileReader reader = new FileReader(currentFile))
                {
                    htmlKit.read(reader, document, 0);

                }
                catch (IOException e)
                {
                    ExceptionHandler.log(e);
                }
                catch (BadLocationException e)
                {
                    ExceptionHandler.log(e);
                }
                addTips();

            } else
            {

                openDocument();
            }
        }
    }

    public void closeDocument()
    {
        SetHistory(currentFile);
        resetDocument();
    }

    public StyledDocument getDocument()
    {
        return document;
    }
////////////////////////////////////////////////////////////////////////////////////////////

    //  все методы словаря
    public void resetVocabulary()
    {
        StyledEditorKit htmlKit = new StyledEditorKit();
        documentVocabulary = (DefaultStyledDocument) htmlKit.createDefaultDocument();
        view.updateVocabulary();
    }

    public void NewVocabulary()
    {
        resetVocabulary();
        currentFileVocabulary = null;
    }

    public void openVocabulary()
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new MyFileFilter());
        int n = jFileChooser.showOpenDialog(view);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFileVocabulary = jFileChooser.getSelectedFile();
            resetVocabulary();
            StyledEditorKit htmlKit = new StyledEditorKit();
            try (FileReader reader = new FileReader(currentFileVocabulary))
            {
                htmlKit.read(reader, documentVocabulary, 0);
                model.addWords(documentVocabulary.getText(0, documentVocabulary.getLength()));
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
            addTips();
            SetVocabularyHistory(currentFileVocabulary);
        }

    }

    public void openVocabulary(String pathFile)
    {
        if (pathFile == null)
            openVocabulary();
        else
        {
            currentFileVocabulary = new File(pathFile);

            if (currentFileVocabulary.exists())
            {
                resetVocabulary();
              //  view.setTitle(currentFileVocabulary.getName());
                StyledEditorKit htmlKit = new StyledEditorKit();

                try (FileReader reader = new FileReader(currentFileVocabulary))
                {
                    htmlKit.read(reader, documentVocabulary, 0);
                    model.addWords(documentVocabulary.getText(0, documentVocabulary.getLength()));
                }
                catch (IOException e)
                {
                    ExceptionHandler.log(e);
                }
                catch (BadLocationException e)
                {
                    ExceptionHandler.log(e);
                }
                addTips();
                SetVocabularyHistory(currentFileVocabulary);
            } else
            {
                openVocabulary();

            }
        }
    }

    public void saveVocabulary()
    {
        if (currentFileVocabulary == null) saveVocabularuAs();

        else
        {
            String path = currentFileVocabulary.toString();

            if (!path.substring(path.length() - 3).equals("txt"))
            {
                path = path + ".txt";
            }

            try (FileWriter writer = new FileWriter(new File(path)))
            {
                new StyledEditorKit().write(writer, documentVocabulary, 0, documentVocabulary.getLength());
                SetVocabularyHistory(currentFileVocabulary);
            }
            catch (BadLocationException | IOException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveVocabularuAs()
    {

        JFileChooser jFileChooser = new JFileChooser();
        MyFileFilter filter = new MyFileFilter();
        jFileChooser.setFileFilter(filter);
        int n = jFileChooser.showSaveDialog(view);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFileVocabulary = jFileChooser.getSelectedFile();
            String path = currentFileVocabulary.toString();

            if (!path.substring(path.length() - 3).equals("txt"))
            {
                path = path + ".txt";
            }

            try (FileWriter writer = new FileWriter(new File(path)))

            {
                new StyledEditorKit().write(writer, documentVocabulary, 0, documentVocabulary.getLength());
                SetVocabularyHistory(currentFileVocabulary);
            }
            catch (BadLocationException | IOException e)
            {
                ExceptionHandler.log(e);
            }


        }
    }

    //public DefaultStyledDocument getVocabulary()
    public StyledDocument getVocabulary()
    {
        return documentVocabulary;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    //  служебные процедуры и функции
    void SetHistory(File currentFile)
    {

        try (PrintWriter out = new PrintWriter(historyFile.getAbsoluteFile()))
        {
            if (!historyFile.exists())
            {
                historyFile.createNewFile();
            }

            historySet.add(currentFile.toString());

            //Записываем лист в файл первые пять
            int count=5;
            Iterator iterator = historySet.iterator();
            while (iterator.hasNext())
            {
                count--;
                out.println(iterator.next().toString().trim());
            }

        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }

    }

    void SetVocabularyHistory(File currentVocabularyFile)
    {

        try (PrintWriter out = new PrintWriter(historyVokabularyFile.getAbsoluteFile()))
        {
            if (!historyVokabularyFile.exists())
            {
                historyVokabularyFile.createNewFile();
            }

            historyVokabularySet.add(currentVocabularyFile.toString());

            //Записываем лист в файл первые пять
            int count=5;
            Iterator iterator = historyVokabularySet.iterator();
            while (iterator.hasNext()&count>0)
            {
                count--;
                out.println(iterator.next().toString().trim());
            }

        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }

    }

    Set<String> getHistory()
    {

        if (!historyFile.exists()) return historySet;

        try (BufferedReader reader = new BufferedReader(new FileReader(historyFile)))
        {
            String c;
            while ((c = reader.readLine()) != null)
            {
                historySet.add(c);
            }
        }
        catch (IOException e)
        {

            ExceptionHandler.log(e);
        }
        return historySet;
    }

    Set<String> getVocabularyHistory()
    {
        if (!historyVokabularyFile.exists()) return historyVokabularySet;

        try (BufferedReader reader = new BufferedReader(new FileReader(historyVokabularyFile)))
        {
            String c;
            while ((c = reader.readLine()) != null)
            {
                historyVokabularySet.add(c);
            }
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        return historyVokabularySet;
    }

    // Добавляю полсказки в текст
    public void addTips()
    {
        StyleContext context = new StyleContext();
        Style style = context.addStyle("test", null);
        StyleConstants.setForeground(style, Color.BLUE);

        Map<String, String> map = getSettings();
        boolean showTips = map.get("showTips").equals("true") ? true : false;
        int count = 0;
        String word = "";

        if (showTips)
        {
            try
            {
                while (count < document.getLength())
                {

                    String s = document.getText(count, 1);
                    if (s.matches("\\S"))
                    {

                        word = word.concat(s);

                    } else
                    {
                        word = word.replaceAll("[\\.\\,\\(\\)]","");
                        String translation = model.getTranslate(word);
                        if (translation != null)
                            document.replace(count, 0, "{" + translation + "}", style);
                        word = "";
                    }
                    count++;
                }
                view.updateTxt();
            }

            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        } else
        {
            resetDocument();

            StyledEditorKit htmlKit = new StyledEditorKit();

            try (FileReader reader = new FileReader(currentFile))
            {
                htmlKit.read(reader, document, 0);
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }

            view.updateTxt();

        }
    }

    // обработка слова

    public ArrayList<String> getWords(String wrd, String direction)
    {
// ключ доступа к яндекс trnsl.1.1.20160928T190600Z.7a199fa95d360255.bca499bd605822f36e9144bf0e334af8dfbb391e
// http://drupalace.ru/lesson/programmnyy-perevod-teksta-s-pomoshchyu-google-translate
//        try
//        {
//            //Document doc = Jsoup.connect("https://translate.google.ru/#en/ru/plain").get();
//            Connection connect = Jsoup.connect("http://translate.google.ru/translate_a/t?client=x&text=plain&hl=en&sl=en&tl=ru");
//            Document doc = connect.post();
//
////            String title = doc.title();
////            Element body = doc.body();
////            Element head = doc.head();
//                   }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
// String urlRequest = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160928T190600Z.7a199fa95d360255.bca499bd605822f36e9144bf0e334af8dfbb391e&text=plain&lang=en-ru";
//
//        //String urlRequest = "http://translate.google.ru/translate_a/t?client=x&text="+wrd+"&sl="+languageRu+"&tl="+languageEn;
//        String urlRequest = "http://translate.google.ru/translate_a/t?client=x&text=text&sl=en&tl=ru";
//        //String urlRequest = "https://translate.google.ru/#en/ru/plain;
//        //String urlRequest = "http://translate.google.ru/translate_a/t?client=x&text=plain&hl=en&sl=en&tl=ru";
//        //String urlRequest = "http://translate.google.com/m?hl=ru&sl=auto&tl=ru&ie=UTF-8&prev=_m&q=broaden";

//        StringBuilder strBuilder = new StringBuilder();
//            URL url = new URL(urlRequest);
//            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//            httpConnection.connect();
//            int rc = httpConnection.getResponseCode();
//
//            if (rc == 200) {
//                String line = null;
//                BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
//                while ((line = buffReader.readLine()) != null) {
//                    strBuilder.append(line + '\n');
//                }
//            }


        String urlRequest = "https://translate.yandex.net/api/v1.5/tr.json/translate?"
//                + "key=trnsl.1.1.20160928T190600Z.7a199fa95d360255.bca499bd605822f36e9144bf0e334af8dfbb391e"
                + "key=" + key
                + "&text=" + wrd.trim()
                + "&lang=" + direction.trim();
        String responseString = "";
        String translate = "";
        try
        {
            HttpPost httpPost = new HttpPost(urlRequest);
            httpPost.addHeader(contentType);
            HttpResponse client = httpClient.execute(httpPost);

            HttpEntity enty = client.getEntity();
            responseString = (EntityUtils.toString(enty, "UTF-8"));

            translate = getTranslateFromJSON(responseString);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }

        ArrayList<String> listWords = new ArrayList<String>();
        listWords.add(translate);
        return listWords;

    }

    public String getTranslateFromJSON(String str)
    {
        // формат ответа
        // {"code":200,"lang":"en-ru","text":["сюрреалистично"]}

        JSONParser parser = new JSONParser();
        JSONObject object = null;
        try
        {
            object = (JSONObject) parser.parse(str);
            StringBuilder sb = new StringBuilder();
            if (object.get("code").toString().equals("200"))
            {
                JSONArray array = (JSONArray) object.get("text");
                for (Object s : array)
                {
                    sb.append(s.toString() + "\n");
                }
                return sb.toString();
            }
        }
        catch (ParseException e)
        {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public void addWord(String translation, String wrd)
    {

        String translate = ((documentVocabulary.getLength() != 0) ? "\n" : "") + translation + " - " + wrd;

        try
        {
            if (!model.isWordExist(translation))
            {
                documentVocabulary.replace(documentVocabulary.getLength(), 0, translate, null);
                view.updateVocabulary();
                model.addWord(translate);
                addTips();
            }
        }
        catch (BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////
}
