package pl.pelotasplus.droidreads.screens.updates;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Update;

public class UpdatesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Update>> {
    public static final String TAG = UpdatesFragment.class.getSimpleName();

    public static UpdatesFragment newInstance() {
        return new UpdatesFragment();
    }

    UpdatesAdapter updatesAdapter;

    @InjectView(R.id.recyclerView)
    ObservableRecyclerView recyclerView;

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    MainActivityInterface mainActivityInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updatesAdapter = new UpdatesAdapter(mainActivityInterface);
    }

    @Override
    public void onResume() {
        super.onResume();

        mainActivityInterface.setToolbarTitle(R.string.toolbar_title_updates);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_updates, container, false);

        ButterKnife.inject(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLoaderManager().restartLoader(0, null, UpdatesFragment.this);
            }
        });

        mainActivityInterface.setScrollable(recyclerView);

        return v;
    }

    protected void onRefreshFinished() {
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void onRefreshStarted() {
        swipeRefreshLayout.post(new Runnable() {
            @Override public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainActivityInterface = (MainActivityInterface) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setAdapter(updatesAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<Update>> onCreateLoader(int id, Bundle args) {
        onRefreshStarted();
        return new UpdatesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Update>> loader, List<Update> data) {
        onRefreshFinished();

        if (data == null)
            return;

        updatesAdapter.setData(data);
        updatesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Update>> loader) {
        // no-op
    }
}