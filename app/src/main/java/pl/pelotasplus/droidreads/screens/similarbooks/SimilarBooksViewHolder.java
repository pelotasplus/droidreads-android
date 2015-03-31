package pl.pelotasplus.droidreads.screens.similarbooks;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Book;
import pl.pelotasplus.droidreads.screens.bookdetails.BookDetailsActivity;
import pl.pelotasplus.droidreads.views.RatingBar;

/**
 * Created by alek on 08/03/15.
 */
public class SimilarBooksViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    private static final String TAG = SimilarBooksViewHolder.class.getSimpleName();

    @InjectView(R.id.imageView)
    public ImageView imageView;

    @InjectView(R.id.titleTextView)
    public TextView titleTextView;

    @InjectView(R.id.ratingBar)
    public RatingBar ratingBar;

    @InjectView(R.id.container)
    View container;

    Book book;

    Picasso picasso;

    public SimilarBooksViewHolder(View v) {
        super(v);

        ButterKnife.inject(this, v);

        container.setOnClickListener(this);

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams)
                container.getLayoutParams();
        params.height = v.getContext().getResources()
                .getDimensionPixelSize(R.dimen.book_grid_component_height);
        container.setLayoutParams(params);

        picasso = new Picasso.Builder(imageView.getContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.d(TAG, "uri=" + uri + " ext " + exception);
                        picasso.load(book.getImageUrl()).into(imageView);
                    }
                }).build();
        picasso.setIndicatorsEnabled(true);
    }

    public void setBook(final Book book) {
        this.book = book;

        titleTextView.setText(book.getTitle());
        ratingBar.setRating(book.getAverageRating());

        loadCover();
    }

    private void loadCover() {
        picasso
                .load(book.getOpenLibraryCoverImageUrl())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Drawable drawable = imageView.getDrawable();
                        Rect rect = drawable.getBounds();
                        if (rect.width() == 1) {
                            picasso.load(book.getImageUrl()).into(imageView);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (book == null) return;

        BookDetailsActivity.showDetails(v.getContext(), book);
    }
}
