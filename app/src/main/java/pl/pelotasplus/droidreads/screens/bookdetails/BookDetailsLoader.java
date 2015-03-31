package pl.pelotasplus.droidreads.screens.bookdetails;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import javax.inject.Inject;

import pl.pelotasplus.droidreads.api.GoodreadsAPI;
import pl.pelotasplus.droidreads.api.googlebooks.GoogleBooksAPI;
import pl.pelotasplus.droidreads.api.googlebooks.model.VolumesResponse;
import pl.pelotasplus.droidreads.api.model.BookShowResponse;
import pl.pelotasplus.droidreads.dagger.Injector;
import retrofit.RetrofitError;

public class BookDetailsLoader extends AsyncTaskLoader<BookDetails> {
    private static final String TAG = BookDetailsLoader.class.getSimpleName();

    public static int ID = 1000;

    BookDetails loaderResult = null;

    long bookId;

    @Inject
    GoodreadsAPI goodreadsAPI;

    @Inject
    GoogleBooksAPI googleBooksAPI;

    public BookDetailsLoader(Context context, long bookId) {
        super(context);

        Injector.getInstance().inject(this);

        this.bookId = bookId;
    }

    @Override
    public BookDetails loadInBackground() {
        BookDetails ret = new BookDetails();

        try {
            BookShowResponse response = goodreadsAPI.book_show(bookId);
            ret.book = response.getBook();

            String isbnStr = String.format("isbn:%s", ret.book.getIsbn());
            VolumesResponse volumesResponse = googleBooksAPI.volumes(isbnStr);
            if (volumesResponse.getTotalItems() >= 1) {
                ret.volume = volumesResponse.getItems().get(0);
            }
        } catch (RetrofitError error) {
            handleApiError(error);
            return null;
        }

        return ret;
    }

    private void handleApiError(RetrofitError error) {
        error.printStackTrace();
    }

    @Override
    public void deliverResult(BookDetails result) {
        loaderResult = result;

        if (isStarted())
            super.deliverResult(result);
    }

    @Override
    protected void onStartLoading() {
        if (loaderResult != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(loaderResult);
        }

        if (takeContentChanged() || loaderResult == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        loaderResult = null;
    }
}