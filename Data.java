package com.example.natalka.terra24h;

import java.io.Serializable;
import java.sql.Date;

public class Data implements Serializable {
    private int id;
    private int nr_node;
    private int nr_term;
    private String date;
    private double tempe;
    String tempString;

    public Data ()
    {

    }

    public Data(String date, String tempString)
    {
        this.date=date;
        this.tempString=tempString;
    }
    public Data (int id, int nr_node, int nr_term, String date, double tempe)
    {
        this.id=id;
        this.nr_node=nr_node;
        this.nr_term=nr_term;
        this.date=date;
        this.tempe=tempe;
    }

    public int getId ()
    {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getTempe() {
        return tempe;
    }

    public int getNr_node() {
        return nr_node;
    }

    public int getNr_term() {
        return nr_term;
    }

    public void setDate(String date)
    {
        this.date=date;

    }

    public String getTempString()
    {
        return tempString;
    }

    public void setTempe (double tempe)
    {
        this.tempe=tempe;
    }

    public  void setTempString (String tempString)
    {
        this.tempString=tempString;
    }
}

