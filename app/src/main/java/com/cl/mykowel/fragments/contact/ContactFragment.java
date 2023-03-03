package com.cl.mykowel.fragments.contact;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.R;
import com.cl.mykowel.databinding.FragmentContactListBinding;
import com.cl.mykowel.fragments.adapter.ContactRecyclerViewAdapter;
import com.cl.mykowel.model.model_my_kovel.model_contact.ItemContact;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ContactFragment extends Fragment {

    // призначено для того щоб зєднатись із layout News_list
    private FragmentContactListBinding binding;
    //наш HomeViewModel
    private ContactViewModel contactViewModel;
    //наш список обєктів ітемів
    private ArrayList<ItemContact> itemContacts;
    //наш ліст наших ітемів які будть виведені в fragment_news_list
    private RecyclerView recyclerView;
    //адаптер до fragment_news_list
    private ContactRecyclerViewAdapter adapter;


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;



    /*
    В Android метод newInstance() є типовим шаблоном, який використовується під час створення нового екземпляра фрагмента.
    Цей метод   зазвичай використовується для передачі аргументів у фрагмент, таких як дані або параметри конфігурації
    Метод newInstance() створює новий екземпляр фрагмента та встановлює всі необхідні аргументи.
    Цей метод зазвичай викликається під час створення нового фрагмента в Activity, а не безпосереднього виклику конструктора.
    */

    public static ContactFragment newInstance(int columnCount) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    /*
    В Android метод onCreate() є методом життєвого циклу класу Fragment, який викликається під час першого створення Fragment.
    Цей метод зазвичай використовується для ініціалізації інтерфейсу користувача фрагмента та будь-яких інших необхідних ресурсів
    або залежностей.
    Методу onCreate() передається об’єкт Bundle, який містить будь-який збережений стан для фрагмента, якщо він доступний.
    У цьому методі ви можете роздувати макет для фрагмента,
    ініціалізувати будь-які змінні-члени та налаштувати будь-які інші необхідні ресурси чи залежності.

    Важливо зауважити, що метод onCreate() викликається перед тим, як відобразиться інтерфейс користувача Fragment.
    Це означає, що будь-які трудомісткі завдання, наприклад мережеві запити, слід виконувати в окремому потоці,
    щоб уникнути сповільнення інтерфейсу користувача програми.
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /*
    В Android метод onCreateView() є методом життєвого циклу класу Fragment,
    який викликається, коли потрібно створити інтерфейс користувача Fragment.
    Цей метод повертає об’єкт View, який представляє інтерфейс користувача Fragment.
    Методу onCreateView() передається LayoutInflater, ViewGroup і об’єкт Bundle,
    який містить будь-який збережений стан для фрагмента, якщо він доступний.

    У цьому методі ви можете роздувати макет для фрагмента, викликаючи метод inflate() об’єкта LayoutInflater
    і передаючи ідентифікатор ресурсу макета. Ви також можете ініціалізувати будь-які види або віджети,
    які є частиною інтерфейсу користувача Fragment.
    Після повернення об’єкта View його буде додано до ViewGroup, переданого як другий аргумент.

    Важливо зазначити, що метод onCreateView() викликається лише тоді, коли інтерфейс користувача Fragment потрібно створити вперше.
    Після створення інтерфейсу користувача Fragment об’єкт View, повернутий цим методом,
    буде повторно використано для майбутніх екземплярів Fragment.
    */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactListBinding.inflate(inflater, container, false);
//        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        View view = binding.getRoot();
//        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        getItemContact();


        recyclerView = view.findViewById(R.id.fragment_list_contact);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void getItemContact() {
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        contactViewModel.getContactRepository().observe(getViewLifecycleOwner(), new Observer<List<ItemContact>>() {
            @Override
            public void onChanged(List<ItemContact> resultList) {
                itemContacts = (ArrayList<ItemContact>) resultList;

                fillRecycleView();
            }
        });
    }


    private void fillRecycleView() {


        adapter = new ContactRecyclerViewAdapter(getActivity().getApplicationContext(), itemContacts);
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