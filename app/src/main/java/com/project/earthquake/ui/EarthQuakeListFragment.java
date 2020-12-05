package com.project.earthquake.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.earthquake.R;
import com.project.earthquake.data.network.EarthQuakeItem;
import com.project.earthquake.data.network.EarthQuakeRsp;
import com.project.earthquake.di.EarthQuakeAppComponent;
import com.project.earthquake.repository.IEarthQuakeRepository;
import com.project.earthquake.ui.common.EarthQuakeApplication;
import com.project.earthquake.ui.common.EarthQuakeBaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This fragment will show list of recent earthquake information.
 */
public class EarthQuakeListFragment extends EarthQuakeBaseFragment {
    private RecyclerView earthquakesList;
    private EarthQuakesAdapter earthquakesListAdapter;
    private List<EarthQuakeItem> items;
    private TextView emptyTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_earth_quake_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyTextView = view.findViewById(R.id.empty_response);
        emptyTextView.setVisibility(View.GONE);
        //init view model
        IEarthQuakeRepository iEarthQuakeRepository = getEarthQuakeRepository();
        EarthQuakeListViewModel.Factory factory = new EarthQuakeListViewModel.Factory(iEarthQuakeRepository);
        EarthQuakeListViewModel viewModel = new ViewModelProvider(this, factory)
                                    .get(EarthQuakeListViewModel.class);
        earthquakesList = view.findViewById(R.id.earth_quake_list_view);

        if(!isNetworkConnected()) {
            showAlertDialog(getString(R.string.netwrok_error),
                    getString(R.string.check_your_connection),
                    getString(R.string.ok),
                    (dialog, which) -> {dialog.dismiss(); getActivity().finish();},
                    null,
                    null);
            return;
        }

        viewModel.getEarthQuakeData(44.1f,-9.9f,-22.4f,55.2f, "mkoppelman")
                .observe(getViewLifecycleOwner(), listResource -> {
                    if(listResource.isLoading()) {
                        showProgressDialog(null, getString(R.string.refreshing_data_please_wait));
                    } else if(listResource.isSuccess()) {
                        EarthQuakeRsp response = listResource.consumeData();
                        if(response != null) {
                            items = response.getItems();
                            if(items == null || items.size() == 0) {
                                emptyTextView.setVisibility(View.VISIBLE);
                                emptyTextView.setText(getString(R.string.no_info_found));
                                earthquakesList.setVisibility(View.GONE);
                            } else {
                                emptyTextView.setVisibility(View.GONE);
                                earthquakesList.setVisibility(View.VISIBLE);
                                //sorting on timestamp value..
                                Collections.sort(items, (o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()));
                                earthquakesListAdapter = new EarthQuakesAdapter(items);
                                earthquakesList.setAdapter(earthquakesListAdapter);
                                earthquakesListAdapter.setOnClickListener(pos -> handleOnClick(pos));
                            }
                        }
                        cancelProgressDialog();

                    } else if(listResource.isError()) {
                        cancelProgressDialog();
                        showAlertDialog(getString(R.string.error),
                                getString(R.string.something_went_wrong),
                                getString(R.string.ok),
                                (dialog, which) -> dialog.dismiss(),
                                null,
                                null);
                    }
                });

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        earthquakesList.addItemDecoration(decoration);
        earthquakesList.setLayoutManager(new LinearLayoutManager(getContext()));
        earthquakesList.setVisibility(View.VISIBLE);
    }

    protected void handleOnClick(int position) {
        //Navigate to map view fragment
        NavController controller = NavHostFragment.findNavController(this);
        NavDirections directions = EarthQuakeListFragmentDirections
                .actionEarthQuakeListFragmentToEarthQuakeMapViewFragment(items.get(position));
        controller.navigate(directions);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}
