package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 17/02/15.
 */
public class SeriesWork {
    @Element
    long id;

    @Element
    long user_position;

    @Element
    Series series;
}
