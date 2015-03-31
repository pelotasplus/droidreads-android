package pl.pelotasplus.droidreads.screens.notifications;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Challenge;
import pl.pelotasplus.droidreads.api.model.Friend;
import pl.pelotasplus.droidreads.api.model.GroupResource;
import pl.pelotasplus.droidreads.api.model.Notification;
import pl.pelotasplus.droidreads.api.model.ResourceType;
import pl.pelotasplus.droidreads.api.model.Topic;
import pl.pelotasplus.droidreads.api.model.User;

public class NotificationsListEntryViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.container)
    View ontainer;

    @InjectView(R.id.textView)
    TextView textView;

    @InjectView(R.id.imageView)
    ImageView imageView;

    @InjectView(R.id.timestampTextView)
    TextView timestampTextView;

    public NotificationsListEntryViewHolder(View v) {
        super(v);

        ButterKnife.inject(this, v);
    }

    public void setNotification(Notification notification) {
        ResourceType resourceType = notification.getResourceType();

        System.out.println("XXX=" + resourceType);

        switch (resourceType) {
            case FRIEND:
                showFriend(notification);
                break;
            case COMMENT:
                showComment(notification);
                break;
            case CHALLENGE:
                showChallenge(notification);
                break;
            case UNKNOWN:
            default:
                showUnknown(notification);
                break;
        }
    }

    private void showChallenge(Notification notification) {
        GroupResource groupResource = notification.getGroupResource();
        Challenge challenge = groupResource.getChallenge();

        User user = notification.getActors().get(0);

        textView.setText(challenge.getDescription());

        Picasso
                .with(textView.getContext())
                .load(user.getImage_url())
                .resizeDimen(R.dimen.notifications_friend_image_size, R.dimen.notifications_friend_image_size)
                .centerCrop()
                .into(imageView);

        timestampTextView.setText(notification.getCreatedAtAgo());
    }

    private void showComment(Notification notification) {
        GroupResource groupResource = notification.getGroupResource();
        Topic topic = groupResource.getTopic();

        String userOrUsers = "";

        for (User user: notification.getActors()) {
            userOrUsers += user.getName() + ", ";
        }
        userOrUsers = userOrUsers.substring(0, userOrUsers.lastIndexOf(","));

        textView.setText(textView.getContext().getString(
                R.string.commented_on, userOrUsers, topic.getTitle()
        ));

        Picasso
                .with(textView.getContext())
                .load(notification.getActors().get(0).getImage_url())
                .resizeDimen(R.dimen.notifications_friend_image_size, R.dimen.notifications_friend_image_size)
                .centerCrop()
                .into(imageView);

        timestampTextView.setText(notification.getCreatedAtAgo());
    }

    private void showUnknown(Notification notification) {
        textView.setText(notification.getBody().getText());
    }

    private void showFriend(Notification notification) {
        User user = notification.getActors().get(0);

        textView.setText(textView.getContext().getString(
                R.string.became_your_friend, user.getName()
        ));

        Picasso
                .with(textView.getContext())
                .load(user.getImage_url())
                .resizeDimen(R.dimen.notifications_friend_image_size, R.dimen.notifications_friend_image_size)
                .centerCrop()
                .into(imageView);

        GroupResource groupResource = notification.getGroupResource();

        Friend friend = groupResource.getFriend();

        timestampTextView.setText(friend.getCreatedAtAgo());
    }
}