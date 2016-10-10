package myRider;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledEditorKit;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Controller
{
    private View view;
    private Settings settings;

    private DefaultStyledDocument document;
    private DefaultStyledDocument documentVocabulary;

    private File currentFile;
    private File currentFileVocabulary;

    // соединение
    private static HttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    private static Header contentType = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");

    //  общие
    public Controller(View view)
    {
        this.view = view;
    }

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void init()
    {
        NewVocabulary();
    }

    public void exit()
    {

        System.exit(0);
        //Исходя из общепринятой практики и установленных стандартов код выхода равный 0 — сигнализирует об успешном выполнении задачи.
    }

    //  все методы настроек
    public void showSettings()
    {
        settings.showDialog(this);
    }

    public void saveSettings(Map<String, String> map)
    {
        File file = new File("C:\\Project1\\property.txt");

        try (PrintWriter out = new PrintWriter(file.getAbsoluteFile()))
        {
            if (!file.exists())
            {
                file.createNewFile();
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

    //  все методы текста
    public void resetDocument()
    {
        StyledEditorKit htmlKit = new StyledEditorKit();
        document = (DefaultStyledDocument) htmlKit.createDefaultDocument();
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

        }
    }

    public void closeDocument()
    {
        resetDocument();
    }

    public DefaultStyledDocument getDocument()
    {
        return document;
    }

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
        //view.setTitle(currentFile.getName() + " - словарь");
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
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            catch (BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveVocabulary()
    {
        if (currentFileVocabulary == null) saveVocabularuAs();

        else
        {
            try (FileWriter writer = new FileWriter(new File(currentFileVocabulary.toString() + ".txt")))
            {
                new StyledEditorKit().write(writer, documentVocabulary, 0, documentVocabulary.getLength());
            }
            catch (BadLocationException | IOException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveVocabularuAs()
    {
        String path = "vocabulary.txt";

        JFileChooser jFileChooser = new JFileChooser();
        MyFileFilter filter = new MyFileFilter();
        jFileChooser.setFileFilter(filter);
        int n = jFileChooser.showSaveDialog(view);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            currentFileVocabulary = jFileChooser.getSelectedFile();

            try (FileWriter writer = new FileWriter(new File(currentFileVocabulary.toString() + ".txt")))
            {
                new StyledEditorKit().write(writer, documentVocabulary, 0, documentVocabulary.getLength());
            }
            catch (BadLocationException | IOException e)
            {
                ExceptionHandler.log(e);
            }


        }
    }

    public DefaultStyledDocument getVocabulary()
    {
        return documentVocabulary;
    }

    //  служебные процедуры и функции
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
                + "key=trnsl.1.1.20160928T190600Z.7a199fa95d360255.bca499bd605822f36e9144bf0e334af8dfbb391e"
                + "&text=" + wrd.trim()
                + "&lang=" + direction.trim();
        String responseString = "";
        String translate = "";
        try
        {
//            HttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build(); // сделать его статиком
//            Header contentType = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpPost httpPost = new HttpPost(urlRequest);
            httpPost.addHeader(contentType);
            HttpResponse client = httpClient.execute(httpPost);

            HttpEntity enty = client.getEntity();
            responseString = (EntityUtils.toString(enty, "UTF-8"));
            //System.out.println(responseString);
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
            documentVocabulary.replace(documentVocabulary.getLength(), 0, translate, null);
            view.updateVocabulary();
        }
        catch (BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        //documentVocabulary.getText(0,documentVocabulary.getLength())
        //System.out.println(translation + " - " + wrd);
    }

}
