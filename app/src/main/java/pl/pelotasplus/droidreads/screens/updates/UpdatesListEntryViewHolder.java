package pl.pelotasplus.droidreads.screens.updates;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.UserStatus;
import pl.pelotasplus.droidreads.spans.SpanUtils;
import pl.pelotasplus.droidreads.api.model.Book;
import pl.pelotasplus.droidreads.api.model.Update;
import pl.pelotasplus.droidreads.dagger.Injector;
import pl.pelotasplus.droidreads.screens.bookdetails.BookDetailsActivity;
import pl.pelotasplus.droidreads.spans.TextColourSpan;
import pl.pelotasplus.droidreads.views.RatingBar;

public class UpdatesListEntryViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    private static final String TAG = UpdatesListEntryViewHolder.class.getSimpleName();

    @InjectView(R.id.actorTextView)
    public TextView actorTextView;

    @InjectView(R.id.timestampTextView)
    public TextView timestampTextView;

    @InjectView(R.id.container)
    View container;

    @InjectView(R.id.avatarImageView)
    ImageView avatarImageView;

//    @InjectView(R.id.updateTextView)
//    TextView updateTextView;

//    @InjectView(R.id.coverImageView)
//    ImageView coverImageView;

//    @InjectView(R.id.titleTextView)
//    TextView titleTextView;

    /* friend update */
    @InjectView(R.id.friendContainer)
    View friendContainer;

    @InjectView(R.id.friendImageView)
    ImageView friendImageView;

    @InjectView(R.id.friendTextView)
    TextView friendTextView;

    /* book update */
    @InjectView(R.id.bookContainer)
    View bookContainer;

    @InjectView(R.id.bookUpdateRatingBar)
    RatingBar bookUpdateRatingBar;

    @InjectView(R.id.bookUpdateImageView)
    ImageView bookUpdateImageView;

    @InjectView(R.id.bookUpdateTitleTextView)
    TextView bookUpdateTitleTextView;

    @InjectView(R.id.bookUpdateAuthorTextView)
    TextView bookUpdateAuthorTextView;

    @InjectView(R.id.updateTextView)
    TextView updateTextView;

    @InjectView(R.id.authorContainer)
    View authorContainer;

    @Inject
    Context context;

    Update update;

    private MainActivityInterface mainActivityInterface;

    public UpdatesListEntryViewHolder(MainActivityInterface mainActivityInterface, View v) {
        super(v);

        Injector.getInstance().inject(this);

        ButterKnife.inject(this, v);

        this.mainActivityInterface = mainActivityInterface;

        v.setOnClickListener(this);
    }

    public void setUpdate(final Update update) {
        this.update = update;

        authorContainer.setVisibility(View.GONE);
        bookContainer.setVisibility(View.GONE);
        friendContainer.setVisibility(View.GONE);
        bookUpdateRatingBar.setVisibility(View.GONE);

        Picasso.with(context).load(update.getActor().getImageUrl()).into(avatarImageView);
        actorTextView.setText(update.getActor().getName());
        timestampTextView.setText(update.getUpdatedAtAgo());

        switch (update.getUpdateType()) {
            case FRIEND:
                updateFriendEntry();
                break;
            case REVIEW:
                updateBookEntry();
                break;
            case READ_STATUS:
                updateReadStatus();
                break;
            case RECOMMENDATION:
//                updateText = actorTextView.getContext().getString(
//                        R.string.recommended2,
//                        update.getActor().getName(),
//                        update.getActionText()
//                );

                Log.d(TAG, "RECOMMENDATION " + update);

                break;
            case USER_STATUS:
                updateUserStatus();
//                updateText = actorTextView.getContext().getString(
//                        R.string.user_status,
//                        update.getObjectWrapper().getUserStatus().getPage(),
//                        update.getObjectWrapper().getUserStatus().getBook().getNumPages(),
//                        update.getObjectWrapper().getUserStatus().getPercent(),
//                        update.getObjectWrapper().getUserStatus().getBook().getTitle()
//                );

                Log.d(TAG, "USER_STATUS " + update);

                break;
            case UNKNOWN:
            default:
                Log.d(TAG, "UNKNWON " + update);

//                updateText = update.getUpdateTypeAsStr() +
//                        ": " +
//                        update.getUpdateType() +
//                        ": " +
//                        update.getAction_text();

                break;
        }

    }

    private void updateUserStatus() {
        final Book book = update.getObjectWrapper().getUserStatus().getBook();

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityInterface.showBookDetails(book);
            }
        });

        Picasso.with(context)
                .load(update.getImageUrl())
                .into(bookUpdateImageView);

        bookUpdateTitleTextView.setText(book.getTitle());

        bookUpdateAuthorTextView.setText(book.getAuthor().getName());

        CharacterStyle redText = TextColourSpan.newInstance(context, R.color.background_material_light);

        final UserStatus userStatus = update.getObjectWrapper().getUserStatus();
        String updateText = context.getString(R.string.update_user_status,
                userStatus.getPage(),
                userStatus.getBook().getNumPages(),
                userStatus.getPercent()
        );

        updateTextView.setText(updateText);

        bookContainer.setVisibility(View.VISIBLE);
        authorContainer.setVisibility(View.VISIBLE);
    }

    private void updateFriendEntry() {
        String name = update.getActionText();
        Log.d(TAG, "Context=" + context);
        name = name.replace(
                context.getString(R.string.is_friend_with_action_text),
                "");
        name = name.trim();

        friendTextView.setText(name);

        Picasso.with(context)
                .load(update.getImageUrl())
                .into(friendImageView);

        friendContainer.setVisibility(View.VISIBLE);
    }

    private void updateReadStatus() {
        final Book book = update.getObjectWrapper().getReadStatus().getReview().getBook();

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityInterface.showBookDetails(book);
            }
        });

        Picasso.with(context)
                .load(update.getImageUrl())
                .into(bookUpdateImageView);

        bookUpdateTitleTextView.setText(book.getTitle());

        bookUpdateAuthorTextView.setText(book.getAuthor().getName());

        String shelfName = update.getObjectWrapper().getReadStatus().getStatus();

        CharacterStyle shelfStyle = TextColourSpan.newInstance(context, R.color.darker_gray);

        String updateText = context.getString(
                R.string.update_added_to_shelf,
                shelfName
        );

        SpanUtils.formatUsingSpans(updateTextView, updateText, shelfName, shelfStyle);

        bookContainer.setVisibility(View.VISIBLE);
        authorContainer.setVisibility(View.VISIBLE);
    }

    private void updateBookEntry() {
        final Book book = update.getObjectWrapper().getBook();

        updateTextView.setText(R.string.update_gave_stars);

        bookUpdateRatingBar.setRating(update.getAction().getRating());

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityInterface.showBookDetails(book);
            }
        });

        Picasso.with(context)
                .load(update.getImageUrl())
                .into(bookUpdateImageView);

        bookUpdateTitleTextView.setText(book.getTitle());

        bookUpdateAuthorTextView.setText(book.getAuthor().getName());

        bookContainer.setVisibility(View.VISIBLE);
        authorContainer.setVisibility(View.VISIBLE);
        bookUpdateRatingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(@NotNull View v) {
        if (update == null) return;

        Book book = null;

        switch (update.getUpdateType()) {
            case FRIEND:
                break;
            case REVIEW:
                book = update.getObjectWrapper().getBook();
                BookDetailsActivity.showDetails(context, book);
                break;
            case READ_STATUS:
                book = update.getObjectWrapper().getReadStatus().getReview().getBook();
                BookDetailsActivity.showDetails(context, book);
                break;
            case RECOMMENDATION:
                break;
            case USER_STATUS:
                book = update.getObjectWrapper().getUserStatus().getBook();
                BookDetailsActivity.showDetails(context, book);
                break;
            case UNKNOWN:
                break;
        }
    }
}