package pl.pelotasplus.droidreads.screens.friendsreview;

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
import pl.pelotasplus.droidreads.api.model.Review;
import pl.pelotasplus.droidreads.views.RatingBar;

/**
 * Created by alek on 08/03/15.
 */
public class FriendsReviewViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    private static final String TAG = FriendsReviewViewHolder.class.getSimpleName();

    @InjectView(R.id.imageView)
    public ImageView imageView;

    @InjectView(R.id.titleTextView)
    public TextView titleTextView;

    @InjectView(R.id.ratingBar)
    public RatingBar ratingBar;

    @InjectView(R.id.container)
    View container;

    Review review;

    Picasso picasso;

    public FriendsReviewViewHolder(View v) {
        super(v);

        ButterKnife.inject(this, v);

        container.setOnClickListener(this);

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams)
                container.getLayoutParams();
        params.height = v.getContext().getResources()
                .getDimensionPixelSize(R.dimen.book_grid_component_height);
        container.setLayoutParams(params);

        picasso = new Picasso
                .Builder(imageView.getContext())
                .build();
        picasso.setIndicatorsEnabled(true);
    }

    public void setReview(final Review review) {
        this.review = review;

        titleTextView.setText(review.getUser().getName());
        ratingBar.setRating(review.getRating());

        loadCover();
    }

    private void loadCover() {
        picasso
                .load(review.getUser().getImage_url())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
//        if (review == null) return;
//        FriendsReviewsActivity.showReviews(v.getContext(), book);
    }
}
