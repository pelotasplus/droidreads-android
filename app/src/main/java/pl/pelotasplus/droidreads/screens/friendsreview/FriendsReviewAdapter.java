package pl.pelotasplus.droidreads.screens.friendsreview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Review;

/**
 * Created by alek on 20/03/15.
 */
public class FriendsReviewAdapter extends RecyclerView.Adapter<FriendsReviewViewHolder> {
    private static final String TAG = FriendsReviewAdapter.class.getSimpleName();

    List<Review> reviews = new ArrayList<>();

    public FriendsReviewAdapter() {
    }

    @Override
    public FriendsReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_or_book_square, parent, false);
        return new FriendsReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FriendsReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.setReview(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(List<Review> friendReviews) {
        this.reviews = friendReviews;
    }
}