package com.javarush.test.level28.lesson15.big01.model;


import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class LinkedInStrategy implements Strategy
{
  //  private static final String URL_FORMAT = "http://www.linkedin.com/vsearch/j?keywords=java+%s";
    //https://www.linkedin.com/vsearch/j?keywords=java+%s
    private static final String URL_FORMAT = "https://www.linkedin.com/vsearch/j?keywords=java+%s&locationType=I&countryCode=ru&rsid=4483804581442662715296&page_num=%d&pt=jobs&openFacets=L,C";
    private static final String referrer = "http://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";
    private static final String userAgent = "Mozilla/5.0";
    private static final int timeout = 5 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        //return new ArrayList<>();

        try
        {
            searchString = URLEncoder.encode(searchString, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
        }

        List<Vacancy> vacancies = new ArrayList<>();
        int nom = 0;
        try
        {
            while (true)
            {
                Document doc = getDocument(searchString, ++nom); // получили документ и увеличили счетчик
                // System.out.println(nom);
                Elements elements = (Elements) doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break; // если нет вакансий прекращаю

                for (Element element : elements)
                {
                    // title
                    Element titleElement = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElement.text();

                    // salary
                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null)
                    {
                        salary = salaryElement.text();
                    }

                    // city
                    Element cityElement = element.select("[data-qa=vacancy-serp__vacancy-address]").first();
                    String city = "";
                    if (cityElement != null)
                    {
                        city = cityElement.text();
                    }

                    // company
                    Element companyElement = element.select("[data-qa=vacancy-serp__vacancy-employer]").first();
                    String companyName = "";
                    if (companyElement != null)
                    {
                        companyName = companyElement.text();
                    }

                    // site
                    String siteName = "http://hh.ua/";

                    // url
                    String url = titleElement.attr("href");

                    // add vacancy to the list
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);

//            System.out.println("Title = " + title);
//            System.out.println("Salary = " + salary);
//            System.out.println("City = " + city);
//            System.out.println("CompanyName = " + companyName);
//            System.out.println("SiteName = " + siteName);
//            System.out.println("URL = " + url);
//            System.out.println();
//            System.out.println();
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {

        String url = String.format(URL_FORMAT, searchString, page);//searchString город, 1 это страница

        try
        {
            Document doc = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(timeout).get();
            //  String s = doc.html();
            return doc;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}