package com.cl.mykowel.fragments.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cl.mykowel.databinding.FragmentNewsBinding;
import com.cl.mykowel.model.model_news.ItemNews;

import java.util.ArrayList;

/**/
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private ArrayList<ItemNews> news;

    //    private final List<PlaceholderItem> mValues;
    //конструктору в який попадає context і масив itemNews які буду вподальшому транслюватись на екран
    public NewsRecyclerViewAdapter(Context context, ArrayList<ItemNews> news) {
        this.context = context;
        this.news = news;
//        this.mValues = mValues;
    }

    //створєються обєкти ViewHolder які містять записані в собі дані карточок
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    //в цьому методі записується дані в карточку які виводяться
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.titleTextView.setText(news.get(position).getTitle());
//        holder.contentTextView.setText(news.get(position).getContentText());

        String imagePath = "" +
                news.get(position).getImage();
        Glide.with(context)
                .load(imagePath)
                .into(holder.newsImageView);

    }
    //виводить кількість елементів у нашому масиві itemContact

    @Override
    public int getItemCount() {
        return news.size();
    }

    /*клас в якому зберігаються дані карточки а саме карточок news*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View

            .OnClickListener {
        //поле з даними зкаголовка

        public TextView titleTextView;
        //        public TextView contentTextView;
        //поле з даними фото
        public ImageView newsImageView;

        //коснтруктор
        public ViewHolder(FragmentNewsBinding binding) {
            super(binding.getRoot());
            //встановлюється listener щоб можна було відсліжувати натиск на карточки
            binding.getRoot().setOnClickListener(this);
            newsImageView = binding.fragmentNewsImage;
            titleTextView = binding.fragmentNewsTitle;
        }

        /*Метод який слідкує за натиском на якийсь елемент RecyclerView
         відповідно до натиснутої картки цей url копіється і відкривається він у
         програмі browser для перегляду відповідної сторінки*/
        @Override
        public void onClick(View view) {
            //отримуєм позицію нашого item по якому натиснули

            int position = getBindingAdapterPosition();
            //отримуєм відповідний ItemNews і створюєм обєкт класу

            ItemNews itemNews = news.get(position);
            //створюєм Intent по якому буде відкриватись browser
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //встановлюєм url витягаючи його з раніше стореного itemNews

            intent.setData(Uri.parse(itemNews.getUrl()));
            //ставим позначку що відкриватимемо не з Activity

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //запускаєм і передаєм наш intent

            context.startActivity(intent);
        }


    }
}