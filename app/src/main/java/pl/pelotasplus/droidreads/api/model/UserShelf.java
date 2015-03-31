package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 12/01/15.
 */
public class UserShelf {
    @Element
    long book_count;

    @Element(required = false)
    String description;

    @Element(required = false)
    String display_fields;

    @Element
    boolean exclusive_flag;

    @Element
    boolean featured;

    @Element
    long id;

    @Element
    String name;

    @Element(required = false)
    String order;

    @Element(required = false)
    long per_page;

    @Element(required = false)
    boolean recommend_for;

    @Element(required = false)
    String sort;

    @Element(required = false)
    boolean sticky;

    @Override
    public String toString() {
        return "UserShelf{" +
                "book_count=" + book_count +
                ", description='" + description + '\'' +
                ", display_fields='" + display_fields + '\'' +
                ", exclusive_flag=" + exclusive_flag +
                ", featured=" + featured +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order='" + order + '\'' +
                ", per_page=" + per_page +
                ", recommend_for=" + recommend_for +
                ", sort='" + sort + '\'' +
                ", sticky=" + sticky +
                '}';
    }
}
