package pl.pelotasplus.droidreads.screens.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.Notification;

public class NotificationsFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Notification>> {
    public static final String TAG = NotificationsFragment.class.getSimpleName();
    private static final String EXTRA_SET_SCROLLABLE = TAG + "/EXTRA_SET_SCROLLABLE";

    NotificationsAdapter updatesAdapter;

    @InjectView(R.id.recyclerView)
    ObservableRecyclerView recyclerView;

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    MainActivityInterface mainActivityInterface;

    public static NotificationsFragment newInstance(boolean setScrollable) {
        NotificationsFragment fragment = new NotificationsFragment();

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_SET_SCROLLABLE, setScrollable);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updatesAdapter = new NotificationsAdapter();
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
                getLoaderManager().restartLoader(0, null, NotificationsFragment.this);
            }
        });


        if (getArguments().getBoolean(EXTRA_SET_SCROLLABLE, false)) {
            mainActivityInterface.setScrollable(recyclerView);
        }

        return v;
    }

    protected void onRefreshFinished() {
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void onRefreshStarted() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setAdapter(updatesAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();

        mainActivityInterface.setToolbarTitle(R.string.toolbar_title_notifications);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainActivityInterface = (MainActivityInterface) activity;
    }

    @Override
    public Loader<List<Notification>> onCreateLoader(int id, Bundle args) {
        onRefreshStarted();
        return new NotificationsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Notification>> loader, List<Notification> data) {
        onRefreshFinished();

        if (data == null)
            return;

        updatesAdapter.setData(data);
        updatesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Notification>> loader) {
        // no-op
    }
}