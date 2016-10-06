package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Sid927 on 19.06.2016.
 */
@XmlType(name = "shop")
@XmlRootElement
public class Shop
{
    @XmlElementWrapper(name = "goods",nillable = true)
    public List<String> names;

    public  int count;

    public double  profit;

    public List<String> secretData ;

    public Shop()
    {
        names = new ArrayList<>();
        secretData = new ArrayList<>();
    }
}
