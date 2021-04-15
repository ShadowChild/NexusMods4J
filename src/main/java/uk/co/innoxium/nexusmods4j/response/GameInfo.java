package uk.co.innoxium.nexusmods4j.response;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    /**
     * Gets a category instance for a child category, unless it is the root category
     * @param childCategory - The category to get the parent of
     * @return - Returns the Category instance of the parent category, unless not found, or it's the root, in which case, returns null
     */
    @Nullable
    public Category getParentCategory(@NotNull Category childCategory) {

        if(childCategory.parent_category.equalsIgnoreCase("false")) return null;

        for(Category cat : categories) {

            if(cat.category_id == childCategory.category_id) {

                return cat;
            }
        }
        return null;
    }

    @ToString
    public static class Category {

        public long category_id;
        public String name;
        public String parent_category; // This can be a boolean or int, so unsure what to use here?
    }
}
