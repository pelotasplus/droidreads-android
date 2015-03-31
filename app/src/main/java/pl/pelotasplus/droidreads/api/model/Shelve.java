package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Attribute;

/**
 * Created by alek on 16/02/15.
 */
public class Shelve {
    @Attribute
    String name;

    @Attribute(required = false)
    long count;

    @Attribute(required = false)
    boolean exclusive;

    @Attribute(required = false)
    long review_shelf_id;

    @Attribute(required = false)
    boolean sortable;

    @Override
    public String toString() {
        return "Shelve{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", exclusive=" + exclusive +
                ", review_shelf_id=" + review_shelf_id +
                ", sortable=" + sortable +
                '}';
    }

    public String getName() {
        return name;
    }
}
