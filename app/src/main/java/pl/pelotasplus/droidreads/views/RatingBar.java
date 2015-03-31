package pl.pelotasplus.droidreads.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.R;

/**
 * Created by alek on 22/03/15.
 */
public class RatingBar extends LinearLayout {
    private float rating;
    private int ratingColor;

    @InjectView(R.id.star1)
    RatingStar star1;

    @InjectView(R.id.star2)
    RatingStar star2;

    @InjectView(R.id.star3)
    RatingStar star3;

    @InjectView(R.id.star4)
    RatingStar star4;

    @InjectView(R.id.star5)
    RatingStar star5;

    public RatingBar(Context context) {
        super(context);
        init();
    }

    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ratingColor = getContext().getResources().getColor(R.color.star_enabled);
    }

    public void setRating(float rating) {
        this.rating = rating;

        if (rating >= 1) {
            star1.setTextColor(ratingColor);
        }
        if (rating >= 2) {
            star2.setTextColor(ratingColor);
        }
        if (rating >= 3) {
            star3.setTextColor(ratingColor);
        }
        if (rating >= 4) {
            star4.setTextColor(ratingColor);
        }
        if (rating >= 5) {
            star5.setTextColor(ratingColor);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.inject(this);
    }
}
