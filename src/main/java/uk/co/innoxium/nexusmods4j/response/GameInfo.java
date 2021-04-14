package uk.co.innoxium.nexusmods4j.response;

import lombok.ToString;

import java.util.ArrayList;

@ToString
public class GameInfo {

    public long id;
    public String name;
    public String forum_url;
    public String nexusmods_url;
    public String genre;
    public long file_count;
    public long downloads;
    public String domain_name;
    public int approved_date;
    public long file_views;
    public long authors;
    public long file_endorsements;
    public long mods;
    public ArrayList<Category> categories;

    @ToString
    static class Category {

        long category_id;
        String name;
        String parent_category; // This can be a boolean or int, so unsure what to use here?
    }
}
