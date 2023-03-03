package com.cl.mykowel.fragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.databinding.FragmentEventBinding;
import com.cl.mykowel.model.model_my_kovel.model_event.ItemEvent;

import java.util.ArrayList;


public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private ArrayList<ItemEvent> events;

    public EventRecyclerViewAdapter(Context context, ArrayList<ItemEvent> events) {

        this.context = context;

        this.events = events;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.titleTextView.setText(events.get(position).getTitle());
        holder.placeTextView.setText(events.get(position).getPlace());
        holder.dateTextView.setText(events.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleTextView;
        //поле з даними номера
        public final TextView dateTextView;
        public final TextView placeTextView;


        public ViewHolder(FragmentEventBinding binding) {
            super(binding.getRoot());
            titleTextView = binding.titleEventTextView;
            placeTextView = binding.placeEventTextView;
            dateTextView = binding.dateEventTextView;
        }


    }
}