package com.cl.mykowel.fragments.home;

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
import com.cl.mykowel.databinding.FragmentNewsListBinding;
import com.cl.mykowel.fragments.adapter.NewsRecyclerViewAdapter;
import com.cl.mykowel.fragments.contact.ContactViewModel;
import com.cl.mykowel.model.model_news.ItemNews;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends Fragment {
// призначено для того щоб зєднатись із layout News_list
    private FragmentNewsListBinding binding;
//наш HomeViewModel
    private HomeViewModel homeViewModel;
//наш список обєктів ітемів
    private ArrayList<ItemNews> itemNews;
//наш ліст наших ітемів які будть виведені в fragment_news_list
    private RecyclerView recyclerView;
//адаптер до fragment_news_list
    private NewsRecyclerViewAdapter adapter;




    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 4;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }
    /*
    В Android метод newInstance() є типовим шаблоном, який використовується під час створення нового екземпляра фрагмента.
    Цей метод   зазвичай використовується для передачі аргументів у фрагмент, таких як дані або параметри конфігурації
    Метод newInstance() створює новий екземпляр фрагмента та встановлює всі необхідні аргументи.
    Цей метод зазвичай викликається під час створення нового фрагмента в Activity, а не безпосереднього виклику конструктора.
    */


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsFragment newInstance(int columnCount) {
        NewsFragment fragment = new NewsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentNewsListBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        getItemsNews();




//        View view = inflater.inflate(R.layout.fragment_news_list, container, false);




        return root;
    }

    /*
    В Android метод onDestroyView() є методом життєвого циклу класу Fragment,
    який викликається, коли інтерфейс користувача Fragment видаляється.
    Цей метод викликається перед onDestroy() і onDetach(). Цей метод є аналогом onCreateView().

    У цьому методі ви можете звільнити будь-які ресурси, пов’язані з інтерфейсом користувача Fragment,
    такі як перегляди, слухачі, анімація тощо.
    Крім того, важливо очистити будь-які посилання на об’єкт View, який повертає onCreateView(),
    щоб запобігти витокам пам’яті.

    Важливо зауважити, що метод onDestroyView() викликається лише тоді,
    коли видаляється інтерфейс користувача фрагмента, а не коли знищується сам фрагмент.

    Крім того, коли інтерфейс користувача Fragment створюється заново,
    або шляхом зміни конфігурації, або шляхом видалення та повторного додавання до активності,
    буде викликано onDestroyView(), а потім onCreateView()
    буде викликано знову, щоб відтворити представлення.
    */

    //дестрой для bottom nev bar

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


//наблюдач якшо змінюється і з repository надходять дані то він заповнює масив який обєктів які будуть виведені
    public void getItemsNews() {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getAllNewsData().observe(getViewLifecycleOwner(),
                new Observer<List<ItemNews>>() {
                    @Override
                    public void onChanged(List<ItemNews> resultList) {
                        itemNews = (ArrayList<ItemNews>) resultList;
                        //викликає fillRecyclerView який заповнить
                        fillRecyclerView();
                    }
                });

    }



//заповнює отдєльний обєкт
    private void fillRecyclerView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.fragment_list_news);


        adapter = new NewsRecyclerViewAdapter(getActivity().getApplicationContext(), itemNews);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(getActivity().getApplicationContext(), 1));

        } else {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(getActivity().getApplicationContext(), 4));

        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}