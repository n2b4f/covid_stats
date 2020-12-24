package com.example.kursach_covid_stats;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OneObjectEx1{
    private int number_in_web_table;
    private String number_in_table;
    private String name;
    private String cases;
    private String cases_on_thousand;
    private String died;
    private String recovered;
    public String get_number(){return number_in_table;};
    public String get_name(){return name;};
    public String get_cases(){return cases;};
    public String get_cases_on_thousand(){return cases_on_thousand;};
    public String get_died(){return died;};
    public String get_recovered(){return recovered;};

    public OneObjectEx1(Document doc, int number_of_table,int number_in_web_table) {
        this.number_in_web_table = number_in_web_table;
        Elements tables = doc.getElementsByTag("table");
        Element current_table = tables.get(number_of_table);
        Element elements_from_current_table = current_table.children().get(1);
        Elements array_of_names = elements_from_current_table.children();

        number_in_table = array_of_names.get(number_in_web_table).children().get(0).text();
        name = array_of_names.get(number_in_web_table).children().get(1).children().get(1).text();
        cases = array_of_names.get(number_in_web_table).children().get(2).ownText();
        cases_on_thousand = array_of_names.get(number_in_web_table).children().get(3).ownText();
        died = array_of_names.get(number_in_web_table).children().get(5).ownText();
        recovered = array_of_names.get(number_in_web_table).children().get(7).ownText();
    }
}
