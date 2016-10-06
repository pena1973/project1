package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller
{
 //   private Provider[] providers;
    private Model model;

    public Controller(Model model)
    {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }
    public  void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
    //
//    public Controller(Provider... providers)
//    {
//        if (providers.length==0)
//        {
//            throw new IllegalArgumentException();
//        }
//        this.providers = providers;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "Controller{" +
//                "providers=" + Arrays.toString(providers) +
//                '}';
//    }

//    public void scan()
//    {
//
//        List<Vacancy> list = new ArrayList<>();
//
//        try
//        {
//
//            for (Provider provider : providers)
//        {
//                List<Vacancy> listVacPr = provider.getJavaVacancies("Москва");
//
//                for (Vacancy vac : listVacPr)
//                {
//                    list.add(vac);
//                }
//            }
//
//        System.out.println(list.size());
//        }
//        catch (NullPointerException e)
//        {
//            System.out.println(0);
//        }
//    }
}
