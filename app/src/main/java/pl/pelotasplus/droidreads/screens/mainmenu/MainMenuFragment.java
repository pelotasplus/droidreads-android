package pl.pelotasplus.droidreads.screens.mainmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.pelotasplus.droidreads.MainActivityInterface;
import pl.pelotasplus.droidreads.R;
import pl.pelotasplus.droidreads.api.model.User;
import pl.pelotasplus.droidreads.dagger.Injector;

public class MainMenuFragment extends Fragment {
    public static final String TAG = MainMenuFragment.class.getSimpleName();

    MainActivityInterface mainActivityInterface;

    MainMenuAdapter menuAdapter;

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    @InjectView(R.id.logoutButton)
    Button logoutButton;

    @InjectView(R.id.currentUserTextView)
    TextView currentUserTextView;

    @InjectView(R.id.imageView)
    ImageView imageView;

    @Inject
    User currentUser;

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuAdapter = new MainMenuAdapter(mainActivityInterface);

        Injector.getInstance().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        ButterKnife.inject(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(menuAdapter);

        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityInterface.doLogout();
            }
        });

        Picasso.with(getActivity()).load(currentUser.getImage_url()).into(imageView);

        currentUserTextView.setText(currentUser.getName());

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainActivityInterface = (MainActivityInterface) activity;
    }

//    public void setChecked(MainMenuItem menuItem) {
//        getListView().setItemChecked(menuItem.ordinal(), true);
//    }
}