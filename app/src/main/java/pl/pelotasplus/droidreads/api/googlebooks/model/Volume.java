package pl.pelotasplus.droidreads.api.googlebooks.model;

/**
 * Created by alek on 19/03/15.
 */
public class Volume {
    String kind;
    String id;
    String etag;
    String selfLink;

    VolumeInfo volumeInfo;

    @Override
    public String toString() {
        return "Volume{" +
                "kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                ", etag='" + etag + '\'' +
                ", selfLink='" + selfLink + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }

    public String getId() {
        return id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public String getSelfLink() {
        return selfLink;
    }
}
