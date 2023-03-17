package com.cl.mykowel.fragments.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cl.mykowel.R;
import com.cl.mykowel.databinding.FragmentBazarBinding;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class BazarRecyclerViewAdapter extends RecyclerView.Adapter<BazarRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ItemBazar> bazars;


    public BazarRecyclerViewAdapter(Context context, ArrayList<ItemBazar> bazars){
        this.context = context;
        this.bazars = bazars;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentBazarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.titleTextView.setText(bazars.get(position).getTitle());
        holder.descriptionTextView.setText(bazars.get(position).getDescription());
        holder.priceTextView.setText(bazars.get(position).getPrice());
//        holder.categoryTextView.setText(bazars.get(position).getCategory());
        holder.phoneTextView.setText(bazars.get(position).getPnumber());

        String imagePath = "" + bazars.get(position).getPhoto();
            Glide.with(context)
                    .load(imagePath)
                    .into(holder.bazarImageView);



    }

    @Override
    public int getItemCount() {
        return bazars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TextView descriptionTextView;

        public TextView priceTextView;

//        public TextView categoryTextView;

        public ImageView bazarImageView;

        public TextView phoneTextView;


        public ViewHolder(FragmentBazarBinding binding) {
            super(binding.getRoot());

            titleTextView = binding.titleBazarText;

            descriptionTextView = binding.descriptionBazarText;

            priceTextView = binding.priceBazarText;

//            categoryTextView = binding.categoryBazarText;
            phoneTextView = binding.phoneBazarText;

            bazarImageView = binding.photoBazarImage;
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + priceTextView.getText() + "'";
//        }
    }
}