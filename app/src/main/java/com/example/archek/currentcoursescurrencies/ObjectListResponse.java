package com.example.archek.currentcoursescurrencies;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "ValCurs")
public class ValCurs
{
    @Attribute(name = "Date")
    private String date;

    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true)
    private ArrayList<ObjectResponse> objectResponses;

    public String getDate ()
    {
        return date;
    }

    public String getName ()
    {
        return name;
    }

    public ArrayList<ObjectResponse> getCurrencies ()
    {
        return objectResponses;
    }


}