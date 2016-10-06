package com.javarush.test.level40.lesson04.task02;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Опять POST, а не GET
Исправь ошибки в методе sendPost, чтобы он отправлял POST-запрос с переданными параметрами.
Реализация должна использовать библиотеки Apache HttpClient версии 4.5.1 и Apache HttpCore 4.4.3.
*/
//  http://automation-remarks.com/java-rest-client/  - статья про это

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("http://requestb.in/1h4qhvv1", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(url);
        request.addHeader("User-Agent", "Mozilla/5.0");
        request.addHeader("Accept-Language", "en-US,en;q=0.5");
//        для одного параметра
//        StringEntity input = new StringEntity(urlParameters);
//        request.setEntity(input);

//        для нескольких параметров
//        List nameValuePairs = new ArrayList();
//        nameValuePairs.add(new BasicNameValuePair("name", "zapp")); //you can as many name value pair as you want in the list.
//        nameValuePairs.add(new BasicNameValuePair("mood", "good")); //you can as many name value pair as you want in the list.
//        nameValuePairs.add(new BasicNameValuePair("locale", "")); //you can as many name value pair as you want in the list.
//        nameValuePairs.add(new BasicNameValuePair("id", "777")); //you can as many name value pair as you want in the list.


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        String[] s = urlParameters.split("&");
        for (int i = 0; i < s.length; i++)
        {
            String g = s[i];
            nameValuePairs.add(new BasicNameValuePair(g.substring(0,g.indexOf("=")), g.substring(g.indexOf("=")+1)));
        }

        request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpResponse response = client.execute(request);
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }
}
