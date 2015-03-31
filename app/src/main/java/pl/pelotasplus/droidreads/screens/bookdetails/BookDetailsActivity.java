package pl.pelotasplus.droidreads.screens.bookdetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.PaletteTransformation;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.SpacesItemDecoration;
import pl.pelotasplus.droidreads.api.googlebooks.model.Volume;
import pl.pelotasplus.droidreads.api.model.Book;
import pl.pelotasplus.droidreads.api.model.Review;
import pl.pelotasplus.droidreads.screens.bookdescription.BookDescriptionActivity;
import pl.pelotasplus.droidreads.screens.friendsreview.FriendsReviewActivity;
import pl.pelotasplus.droidreads.screens.friendsreview.FriendsReviewAdapter;
import pl.pelotasplus.droidreads.screens.similarbooks.SimilarBooksActivity;
import pl.pelotasplus.droidreads.screens.similarbooks.SimilarBooksAdapter;

public class BookDetailsActivity extends FragmentActivity
        implements LoaderManager.LoaderCallbacks<BookDetails>, ObservableScrollViewCallbacks {
    private static final String TAG = BookDetailsActivity.class.getSimpleName();
    public static final String EXTRA_BOOK = TAG + "/EXTRA_BOOK";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.imageView)
    ImageView imageView;

    @InjectView(R.id.descriptionTextView)
    TextView descriptionTextView;

    @InjectView(R.id.authorTextView)
    TextView authorTextView;

    @InjectView(R.id.titleTextView)
    TextView titleTextView;

    @InjectView(R.id.scrollView)
    ObservableScrollView scrollView;

    @InjectView(R.id.readDescriptionButton)
    Button readDescriptionButton;

    @InjectView(R.id.ratingTextView)
    TextView ratingTextView;

    @InjectView(R.id.reviewsCountTextView)
    TextView reviewsCountTextView;

    @InjectView(R.id.formatTextView)
    TextView formatTextView;

    @InjectView(R.id.categoryTextView)
    TextView categoryTextView;

    @InjectView(R.id.similarBooksContainer)
    View similarBooksContainer;

    @InjectView(R.id.coverContainer)
    RelativeLayout coverContainer;

    @InjectView(R.id.similarBooksRecyclerView)
    RecyclerView recyclerView;

    @InjectView(R.id.moreSimilarButton)
    Button moreSimilarButton;

    @InjectView(R.id.goodreadsIdTextView)
    TextView goodreadsIdTextView;

    @InjectView(R.id.googlebooksIdTextView)
    TextView googlebooksIdTextView;

    @InjectView(R.id.friendsReviewsContainer)
    LinearLayout friendsReviewsContainer;

    @InjectView(R.id.moreFriendsReviewsButton)
    Button moreFriendsReviewsButton;

    @InjectView(R.id.friendsReviewsRecyclerView)
    RecyclerView friendsReviewsRecyclerView;

    @InjectView(R.id.authorImageView)
    ImageView authorImageView;

    @InjectView(R.id.publisherTextView)
    TextView publisherTextView;

    @InjectView(R.id.isbnTextView)
    TextView isbnTextView;

    @InjectView(R.id.goodreadsIdContaner)
    View goodreadsIdContaner;

    @InjectView(R.id.googlebooksIdContaner)
    View googlebooksIdContaner;

    @InjectView(R.id.pagesTextView)
    TextView pagesTextView;

    private int parallaxImageHeight;

    private Book book;
    private Volume volume;

    private int whiteColor, primaryColor;

    private static final int COLS_COUNT = 3;
    private SimilarBooksAdapter similarBooksAdapter;
    private FriendsReviewAdapter friendsReviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bookJson = getIntent().getStringExtra(EXTRA_BOOK);
        if (TextUtils.isEmpty(bookJson)) {
            book = new Book();
            book.setId(11468377);
            book.setId(17574849);
        } else {
            book = Book.fromJson(bookJson);
        }

        setContentView(R.layout.activity_book_details);

        ButterKnife.inject(this);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.this.finish();
            }
        });

        primaryColor = getResources().getColor(R.color.colorPrimary);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, primaryColor));

        whiteColor = getResources().getColor(android.R.color.white);
        toolbar.setTitleTextColor(ScrollUtils.getColorWithAlpha(0, whiteColor));
        toolbar.setSubtitleTextColor(ScrollUtils.getColorWithAlpha(0, whiteColor));

        parallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.book_details_parallax_image_height);

        scrollView.setScrollViewCallbacks(this);

        setupSimilarBooks();
        setupFriendsReviews();

        getSupportLoaderManager().initLoader(BookDetailsLoader.ID, null, this);
    }

    private void setupFriendsReviews() {
        GridLayoutManager manager = new GridLayoutManager(this, COLS_COUNT);

        friendsReviewsRecyclerView.setLayoutManager(manager);
        friendsReviewsRecyclerView.addItemDecoration(new SpacesItemDecoration(COLS_COUNT, 10));

        friendsReviewsAdapter = new FriendsReviewAdapter();
        friendsReviewsRecyclerView.setAdapter(friendsReviewsAdapter);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        friendsReviewsRecyclerView.setHasFixedSize(true);

        friendsReviewsRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        moreFriendsReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book == null) return;
                FriendsReviewActivity.showReviews(BookDetailsActivity.this, book);
            }
        });
    }

    private void setupSimilarBooks() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, COLS_COUNT);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(COLS_COUNT,
                getResources().getDimensionPixelSize(R.dimen.similar_book_padding)));

        similarBooksAdapter = new SimilarBooksAdapter(COLS_COUNT);
        recyclerView.setAdapter(similarBooksAdapter);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        moreSimilarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book == null) return;
                SimilarBooksActivity.showSimilar(BookDetailsActivity.this, book.getSimilarBooks());
            }
        });
    }

    @Override
    public Loader<BookDetails> onCreateLoader(int id, Bundle args) {
        return new BookDetailsLoader(this, book.getId());
    }

    @Override
    public void onLoadFinished(Loader<BookDetails> loader, BookDetails data) {
        if (data == null)
            return;

        book = data.book;
        volume = data.volume;

        showBookHeader();
        showBookDescription();
        showSimilarBooks();
        showFriendsReviews();
        showCover();
        showFooterDetails();
        showBookIcons();
    }

    private void showBookHeader() {
        // author
        Log.d(TAG, "AUTHOR " + book.getAuthor().getImageUrl());

        authorTextView.setText(book.getAuthor().getName());
        Picasso.with(this).load(book.getAuthor().getImageUrl()).into(authorImageView);

        // title
        titleTextView.setText(book.getTitle());

        // toolbar
        toolbar.setTitle(book.getTitle());
//        toolbar.setSubtitle(String.format(
//                getString(R.string.book_details_toolbar_subtitle),
//                book.getAuthor().getName()
//        ));
    }

    private void showCover() {
        String url;
        if (volume != null) {
            url = volume.getVolumeInfo().getImageLinks().getThumbnail();
        } else {
            url = book.getImageUrl();
        }

        Picasso.with(this)
                .load(url)
//                .fit().centerCrop()
                .transform(PaletteTransformation.instance())
                .into(imageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap(); // Ew!
                        Palette palette = PaletteTransformation.getPalette(bitmap);

                        int bgColor = palette.getVibrantColor(android.R.color.white);
                        coverContainer.setBackgroundColor(bgColor);
                    }
                });
    }

    private void showBookDescription() {
        // description
        String description = book.getDescription();
        Log.d(TAG, "description=" + description);
        if (TextUtils.isEmpty(description)) {
            descriptionTextView.setText("(no description)");
        } else {
            descriptionTextView.setText(Html.fromHtml(description));
        }

        int linesCount = descriptionTextView.getLayout().getLineCount();
        if (linesCount > 8) {
            readDescriptionButton.setVisibility(View.VISIBLE);
            descriptionTextView.setEllipsize(TextUtils.TruncateAt.END);
        } else {
            readDescriptionButton.setVisibility(View.GONE);
        }

        readDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDescriptionActivity.start(BookDetailsActivity.this, book);
            }
        });
    }

    private void showBookIcons() {
        // rating
        ratingTextView.setText(String.valueOf(book.getAverageRating()));
        reviewsCountTextView.setText(book.getWork().getRatingsCountFormatted());

        // format
        formatTextView.setText(book.getFormat());
        pagesTextView.setText(String.format(getString(R.string.pages), book.getNumPages()));

        // category
        if (volume != null && volume.getVolumeInfo().getCategories().size() >= 1) {
            categoryTextView.setText(volume.getVolumeInfo().getCategories().get(0));
        } else {
            categoryTextView.setText(book.getPopularShelve().getName());
        }
    }

    private void showFooterDetails() {
        goodreadsIdTextView.setText(String.valueOf(book.getId()));

        goodreadsIdContaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoodReads();
            }
        });

        isbnTextView.setText(book.getIsbn());

        publisherTextView.setText(String.format(
                getString(R.string.publisher_with_date),
                book.getPublisher(), book.getPublicationYear()
        ));

        if (volume != null) {
            googlebooksIdTextView.setText(volume.getId());

            googlebooksIdContaner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGoogleBooks();
                }
            });
        } else {
            googlebooksIdContaner.setVisibility(View.GONE);
        }
    }

    private void openGoogleBooks() {
        openUrl(volume.getVolumeInfo().getPreviewLink());
    }

    private void openGoodReads() {
        openUrl(book.getLink());
    }

    private void openUrl(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }

    private void showSimilarBooks() {
        List<Book> similar = book.getSimilarBooks();
        if (similar.size() > COLS_COUNT) {
            moreSimilarButton.setVisibility(View.VISIBLE);
        }
        similarBooksAdapter.setSimilarBooks(similar);
        similarBooksAdapter.notifyDataSetChanged();
        if (similar.size() > 0) {
            similarBooksContainer.setVisibility(View.VISIBLE);
        }
    }

    private void showFriendsReviews() {
        List<Review> reviews = book.getFriendReviews();
        if (reviews.size() > COLS_COUNT) {
            moreFriendsReviewsButton.setVisibility(View.VISIBLE);
        }
        friendsReviewsAdapter.setReviews(reviews);
        friendsReviewsAdapter.notifyDataSetChanged();
        if (reviews.size() > 0) {
            friendsReviewsContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<BookDetails> loader) {
        // no-op
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        float alpha = 1 - (float) Math.max(0, parallaxImageHeight - scrollY) / parallaxImageHeight;

        toolbar.setTitleTextColor(ScrollUtils.getColorWithAlpha(alpha, whiteColor));
        toolbar.setSubtitleTextColor(ScrollUtils.getColorWithAlpha(alpha, whiteColor));

        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, primaryColor));

        imageView.setTranslationY(scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public static void showDetails(Context context, Book book) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_BOOK, book.toJson());
        context.startActivity(intent);
    }
}