package com.cl.mykowel.fragments.bazar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.R;
import com.cl.mykowel.databinding.FragmentBazarListBinding;
import com.cl.mykowel.fragments.adapter.BazarRecyclerViewAdapter;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

import java.util.ArrayList;
import java.util.List;


public class BazarFragment extends Fragment {


    // призначено для того щоб зєднатись із layout News_list
    private FragmentBazarListBinding binding;
    //наш HomeViewModel
    private BazarViewModel bazarViewModel;
    //наш список обєктів ітемів
    private ArrayList<ItemBazar> itemBazars;
    //наш ліст наших ітемів які будть виведені в fragment_news_list
    private RecyclerView recyclerView;
    //адаптер до fragment_news_list
    private BazarRecyclerViewAdapter adapter;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;



    @SuppressWarnings("unused")
    public static BazarFragment newInstance(int columnCount) {
        BazarFragment fragment = new BazarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBazarListBinding.inflate(inflater, container, false);

        View view = binding.getRoot();




        getItemBazar();

        recyclerView = view.findViewById(R.id.fragment_list_bazar);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getItemBazar() {
        bazarViewModel = new ViewModelProvider(this).get(BazarViewModel.class);

        bazarViewModel.getBazarRepository().observe(getViewLifecycleOwner(), new Observer<List<ItemBazar>>() {
            @Override
            public void onChanged(List<ItemBazar> resultList) {

                itemBazars = (ArrayList<ItemBazar>) resultList;

                fillRecyclerView();
            }
        });

    }


    private void fillRecyclerView(){

        adapter = new BazarRecyclerViewAdapter(getActivity().getApplicationContext(), itemBazars);

        if(recyclerView != null){
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 4));
            }

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        } else {
        }


    }



}