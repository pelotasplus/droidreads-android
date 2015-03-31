package pl.pelotasplus.droidreads.screens.notifications;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Notification;
import pl.pelotasplus.droidreads.api.model.Update;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsListEntryViewHolder> {
    private List<Notification> data;

    public NotificationsAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public NotificationsListEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.view_notifications_list_entry, viewGroup, false);
        return new NotificationsListEntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationsListEntryViewHolder viewHolder, int position) {
        final Notification notification = data.get(position);
        viewHolder.setNotification(notification);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Notification> data) {
        this.data = data;
    }
}
