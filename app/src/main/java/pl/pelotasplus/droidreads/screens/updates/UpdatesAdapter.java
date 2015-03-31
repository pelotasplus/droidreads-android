package pl.pelotasplus.droidreads.screens.updates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Update;

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesListEntryViewHolder> {
    private List<Update> data;
    private MainActivityInterface mainActivityInterface;

    public UpdatesAdapter(MainActivityInterface mainActivityInterface) {
        data = new ArrayList<>();
        this.mainActivityInterface = mainActivityInterface;
    }

    @Override
    public UpdatesListEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_updates_list_entry, viewGroup, false);
        return new UpdatesListEntryViewHolder(mainActivityInterface, v);
    }

    @Override
    public void onBindViewHolder(UpdatesListEntryViewHolder viewHolder, int position) {
        final Update update = data.get(position);
        viewHolder.setUpdate(update);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Update> data) {
        this.data = data;
    }
}
