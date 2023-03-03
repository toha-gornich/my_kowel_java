package com.cl.mykowel.fragments.events;

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
import com.cl.mykowel.databinding.FragmentEventListBinding;
import com.cl.mykowel.fragments.adapter.EventRecyclerViewAdapter;
import com.cl.mykowel.model.model_my_kovel.model_event.ItemEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EventsFragment extends Fragment {

    private FragmentEventListBinding binding;

    private EventsViewModel eventsViewModel;


    private ArrayList<ItemEvent> itemEvents ;

    private RecyclerView recyclerView;

    private EventRecyclerViewAdapter adapter;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;




    public static EventsFragment newInstance(int columnCount) {
        EventsFragment fragment = new EventsFragment();
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

        binding = FragmentEventListBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
//        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        recyclerView = view.findViewById(R.id.fragment_event_list);

        getItemEvent();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getItemEvent (){

        eventsViewModel = new ViewModelProvider(this).get(EventsViewModel.class);

        eventsViewModel.getEventRepository().observe(getViewLifecycleOwner(), new Observer<List<ItemEvent>>() {
            @Override
            public void onChanged(List<ItemEvent> resultList) {
                itemEvents = (ArrayList<ItemEvent>)resultList;

                fillRecyclerView();
            }
        });


        }

      private void fillRecyclerView(){

          adapter = new EventRecyclerViewAdapter(getActivity().getApplicationContext(), itemEvents);
          if (recyclerView != null) {
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