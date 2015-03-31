package pl.pelotasplus.droidreads.api.googlebooks.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alek on 19/03/15.
 */
public class VolumesResponse {
    String kind;
    long totalItems;
    List<Volume> items = new ArrayList<>();

    public long getTotalItems() {
        return totalItems;
    }

    public List<Volume> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "VolumesResponse{" +
                "kind='" + kind + '\'' +
                ", totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
