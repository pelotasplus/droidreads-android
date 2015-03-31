package pl.pelotasplus.droidreads.api.googlebooks;

import pl.pelotasplus.droidreads.api.googlebooks.model.VolumesResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by alek on 19/03/15.
 */
public interface GoogleBooksAPI {
    @GET("//books/v1/volumes")
    VolumesResponse volumes(@Query("q") String isbn);
}
