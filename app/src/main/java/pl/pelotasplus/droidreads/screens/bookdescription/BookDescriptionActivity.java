package pl.pelotasplus.droidreads.screens.bookdescription;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Book;

public class BookDescriptionActivity extends FragmentActivity implements ObservableScrollViewCallbacks {
    private static final String TAG = BookDescriptionActivity.class.getSimpleName();
    public static final String EXTRA_BOOK = TAG + "/EXTRA_BOOK";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.descriptionTextView)
    TextView descriptionTextView;

    @InjectView(R.id.scrollView)
    ObservableScrollView scrollView;

    private Book book;
    private int scrollY = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_description);

        ButterKnife.inject(this);

        Drawable icon = getResources().getDrawable(R.drawable.abc_ic_clear_mtrl_alpha);
        icon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDescriptionActivity.this.finish();
            }
        });

        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(android.R.color.white)));

        scrollView.setScrollViewCallbacks(this);

        String bookJson = getIntent().getStringExtra(EXTRA_BOOK);
        book = Book.fromJson(bookJson);

        showBookDescription();
    }

    private void showBookDescription() {
        descriptionTextView.setText(
                book.getDescription() +
                book.getDescription()
        );

        toolbar.setTitle(book.getTitle());
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        this.scrollY = scrollY;

//        if (scrollY <= 0) {
//            finish();
//        }

//        float alpha = 1 - (float) Math.max(0, parallaxImageHeight - scrollY) / parallaxImageHeight;
//
//        toolbar.setTitleTextColor(ScrollUtils.getColorWithAlpha(alpha, whiteColor));
//        toolbar.setSubtitleTextColor(ScrollUtils.getColorWithAlpha(alpha, whiteColor));
//
//        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, primaryColor));
//
//        imageView.setTranslationY(scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollY <= 0) {
            finish();
        }
    }

    public static void start(Context context, Book book) {
        Intent intent = new Intent(context, BookDescriptionActivity.class);
        intent.putExtra(EXTRA_BOOK, book.toJson());
        context.startActivity(intent);
    }
}