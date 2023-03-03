package com.cl.mykowel.fragments.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.databinding.FragmentContactBinding;
import com.cl.mykowel.model.model_my_kovel.model_contact.ItemContact;

import java.util.ArrayList;


public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ItemContact> contacts;

    //конструктору в який попадає context і масив itemContact які буду вподальшому транслюватись на екран
    public ContactRecyclerViewAdapter(Context context, ArrayList<ItemContact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    //створєються обєкти ViewHolder які містять записані в собі дані карточок
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentContactBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    //в цьому методі записується дані в карточку які виводяться
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.ownerTextView.setText(contacts.get(position).getOwner());
        holder.numberTextView.setText(contacts.get(position).getPnumber());

    }

    //виводить кількість елементів у нашому масиві itemContact
    @Override
    public int getItemCount() {
        return contacts.size();
    }




    /*клас в якому зберігаються дані карточки а саме карточок contacts*/
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //поле з даними назви власника номера
        public final TextView ownerTextView;
        //поле з даними номера
        public final TextView numberTextView;

        public ViewHolder(FragmentContactBinding binding) {
            super(binding.getRoot());
            //встановлюється listener щоб можна було відсліжувати натиск на карточки
            binding.getRoot().setOnClickListener(this);
            //ініціалізація полів
            ownerTextView = binding.fragmentTextOwner;
            numberTextView = binding.fragmentTextNphone;
        }


        /*Метод який слідкує за натиском на якийсь елемент RecyclerView
        відповідно до натиснутої картки цей номер копіється і відкривається він у
        програмі phone і можна буде зателефонувати по ньому*/

        @Override
        public void onClick(View view) {
            //отримуєм позицію нашого item по якому натиснули
            int position = getBindingAdapterPosition();

            //отримуєм відповідний ItemContact і створюєм обєкт класу
            ItemContact itemContact = contacts.get(position);

            //створюєм Intent по якому буде відкриватись app phone і набератись номер
            Intent intent = new Intent(Intent.ACTION_DIAL);
            //ставим позначку що відкриватимемо не з Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //встановлюєм номер витягаючи його з раніше стореного itemContact
            intent.setData(Uri.parse("tel:" + itemContact.getPnumber()));
            //запускаєм і передаєм наш intent
            context.startActivity(intent);
        }
    }
}