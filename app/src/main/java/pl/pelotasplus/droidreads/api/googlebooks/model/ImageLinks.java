package pl.pelotasplus.droidreads.api.googlebooks.model;

/**
 * Created by alek on 19/03/15.
 */
public class ImageLinks {
    String smallThumbnail;
    String thumbnail;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public String toString() {
        return "ImageLinks{" +
                "smallThumbnail='" + smallThumbnail + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
