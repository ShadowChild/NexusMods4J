package uk.co.innoxium.nexusmods4j.response;

import lombok.ToString;

import java.util.ArrayList;

@ToString
public class Game {

    long id;
    String name;
    String forum_url;
    String nexusmods_url;
    String genre;
    long file_count;
    long downloads;
    String domain_name;
    int approved_date;
    long file_views;
    long authors;
    long file_endorsements;
    long mods;
    ArrayList<Category> categories;

    @ToString
    static class Category {

        long category_id;
        String name;
        String parent_category; // This can be a boolean or int, so unsure what to use here?
    }
}
