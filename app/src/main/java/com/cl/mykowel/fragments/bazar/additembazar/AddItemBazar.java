package com.cl.mykowel.fragments.bazar.additembazar;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.cl.mykowel.R;
import com.cl.mykowel.activity.authorization.AuthorizationViewModel;
import com.cl.mykowel.fragments.bazar.BazarViewModel;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

/*
 * Цей фрагмент він створюється для того щоб мати можливість
 * добавляти нове замовлення в базар
 * коли ми нажимає у BazarFragment кнопку + то цей фрагмент він перекриває поверх нього*
 * */

public class AddItemBazar extends Fragment {
    private String photoPath;
    private ActivityResultLauncher<String> pickImageLauncher;

    public static final int GALLERY_PERMISSION_REQUEST_CODE = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private AddItemBazarViewModel viewModel;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;

    private Button btnAddPhoto;
    private Button btnSendItem;


    private Toolbar toolbar;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item_bazar, container, false);

        viewModel = new ViewModelProvider(this).get(AddItemBazarViewModel.class);

        titleEditText = view.findViewById(R.id.titleAddItemEditText);
        descriptionEditText = view.findViewById(R.id.descriptionAddItemEditText);

        priceEditText = view.findViewById(R.id.priceAddItemEditText);

        btnAddPhoto = view.findViewById(R.id.btn_add_photo);
        btnSendItem = view.findViewById(R.id.btn_send_item_bazar);

        initViewModel();


        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                // Обробка вибраного фото
                photoPath = getRealPathFromURI(getContext(), uri);
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
            }
        });

        //створюється тулбар із кнопкою яка містить іконку стрілочки яка повертає назад
        Toolbar toolbar = view.findViewById(R.id.toolbar);

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
            if (cursor == null) {
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

    private  void createItemBazar(){
        String title  = titleEditText.getText().toString();
        String description  = descriptionEditText.getText().toString();
//        Double price  = Double.parseDouble(priceEditText.getText().toString());
        String price  = priceEditText.getText().toString();

        ItemBazar itemBazar = new ItemBazar(title, description, price, photoPath);

        viewModel.createItemBazar(itemBazar, getContext());

    }

}