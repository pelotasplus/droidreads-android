package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 17/02/15.
 */
public class Series {
    @Element
    long id;

    @Element
    String title;

    @Element
    String description;

    @Element
    String note;

    @Element
    long series_works_count;

    @Element
    long primary_work_count;

    @Element
    boolean numbered;

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                ", series_works_count=" + series_works_count +
                ", primary_work_count=" + primary_work_count +
                ", numbered=" + numbered +
                '}';
    }
}
