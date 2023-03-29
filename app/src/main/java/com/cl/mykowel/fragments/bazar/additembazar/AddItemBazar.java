package com.cl.mykowel.fragments.bazar.additembazar;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


import com.cl.mykowel.R;
import com.cl.mykowel.databinding.FragmentAddItemBazarBinding;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

import java.util.HashMap;

/*
 * Цей фрагмент він створюється для того щоб мати можливість
 * добавляти нове замовлення в базар
 * коли ми нажимає у BazarFragment кнопку + то цей фрагмент він перекриває поверх нього*
 * */

public class AddItemBazar extends Fragment {
    private FragmentAddItemBazarBinding binding;
    private HashMap<String, String> spinnerMap;
    private final String[] spinner_list = {"Виберіть категорію", "Одяг і взуття","Для саду та городу", "Їжа та продукти", "Товари для дітей", "Товари для побуту", "Електроніка та механіка","Нерухомість"};
    private Spinner spinner;
    //    private ArrayList<String> spinner_list;
    private ArrayAdapter spinerAdapter;
    private ImageView image;
    private String photoPath;
    private ActivityResultLauncher<String> pickImageLauncher;
    public static final int GALLERY_PERMISSION_REQUEST_CODE = 100;
    private Toolbar toolbar;
    private AddItemBazarViewModel viewModel;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private String category;
    private String selectedCategory;
    private Button btnAddPhoto;
    private Button btnSendItem;
    private ActionBar toolbar2;

    public AddItemBazar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentAddItemBazarBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        // Inflate the layout for this fragment

        viewModel = new ViewModelProvider(this).get(AddItemBazarViewModel.class);

        spinner = binding.spinner;

        image = binding.imageAddItemBazar;

        titleEditText = binding.titleAddItemEditText;

        descriptionEditText = binding.descriptionAddItemEditText;

        priceEditText = binding.priceAddItemEditText;

        btnAddPhoto = binding.btnAddPhoto;

        btnSendItem = binding.btnSendItemBazar;

        //створюється тулбар із кнопкою яка містить іконку стрілочки яка повертає назад


        initViewModel();


        Spinner spinner = binding.spinner;
        spinerAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, spinner_list);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinerAdapter);
        spinnerMap = new HashMap<>();
        spinnerMap.put("Одяг і взуття","0134");
        spinnerMap.put("Їжа та продукти","0179");
        spinnerMap.put("Для саду та городу","0718");
        spinnerMap.put("Товари для дітей","0755");
        spinnerMap.put("Товари для побуту","0759");
        spinnerMap.put("Електроніка та механіка","0372");
        spinnerMap.put("Нерухомість","0910");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String) spinner.getSelectedItem();
                if(selectedCategory != "Виберіть категорію"){
                    category = spinnerMap.get(selectedCategory);
                    Log.d("tron", category);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                // Обробка вибраного фото
                if (uri != null) {
                    photoPath = getRealPathFromURI(getContext(), uri);
                    image.setImageURI(uri);
                }
            }
        });


        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        btnSendItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createItemBazar();
                saveBackReplacement();


            }
        });
        toolbar = binding.toolbar;

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBackReplacement();
            }
        });

        return view;

    }

    //цей метод здійснює перевірку чи є дозвіл до галереї а також //якшо він є то вікриває галерею
    private void openGallery() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION_REQUEST_CODE);
        } else {
            pickImageLauncher.launch("image/*");
        }
    }


    private void initViewModel() {
        viewModel.getCreateItemBazarObserver().observe(getViewLifecycleOwner(), new Observer<ItemBazar>() {
            @Override
            public void onChanged(ItemBazar itemBazar) {

            }
        });
    }

    //цей метод робить із юрі силку стрінгову
    private String getRealPathFromURI(Context context, Uri uri) {
        String filePath;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                filePath = uri.getPath();
            } else {
                cursor.moveToFirst();

                int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                filePath = cursor.getString(index);
                cursor.close();
            }
        } else {
            filePath = uri.getPath();
        }
        return filePath;

    }


    private void saveBackReplacement() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            // Якщо є, повертаємося до попереднього фрагменту з back stack "myBackStack"
            fragmentManager.saveBackStack("replacement");
        }
    }

    private void createItemBazar() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
//        Double price  = Double.parseDouble(priceEditText.getText().toString());
        String price = priceEditText.getText().toString();

        ItemBazar itemBazar = new ItemBazar(title, description, price, photoPath, category,"a");

        viewModel.createItemBazar(itemBazar, getContext());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

    }
}