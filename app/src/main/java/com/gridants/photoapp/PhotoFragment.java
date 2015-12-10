package com.gridants.photoapp;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prats on 10/12/15.
 */
public class PhotoFragment extends Fragment implements View.OnClickListener {

    Button openPicture;
    ImageView picture;
    TextView path;
    RecyclerView mRecyclerView;
    static final int RESULT_PIC = 1;
    Context context;
    PhotoGallery photoGallery;
    List<PhotoModel> photoModels = new ArrayList<>();

    public static PhotoFragment newInstance(){
        PhotoFragment fragment = new PhotoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoGallery = new PhotoGallery(getActivity());
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Intent pickPicture =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPicture.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(pickPicture, RESULT_PIC);
        return inflater.inflate(R.layout.fragment_layout, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openPicture = (Button) view.findViewById(R.id.open_picture);

        picture = (ImageView) view.findViewById(R.id.picture);
        openPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(
//                Intent.createChooser(intent, "Select Picture"),
//                RESULT_PIC);
     //   Intent pickPicture =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       // startActivityForResult(pickPicture, RESULT_PIC);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_PIC && resultCode == getActivity().RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

//            if(cursor.moveToFirst()) {
//                              do {
//                                      int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                                       String picturePath = cursor.getString(columnIndex);
//                                       File file  = new File(picturePath);
//                                       photoGallery.addImage(file);
//                                   }
//                              while(cursor.moveToNext());
//            }


                Uri selectedImageUri = data.getData();

                String[] projection = { MediaStore.Images.Media.DATA };
                Cursor cursor1 = getActivity().getContentResolver().query(selectedImageUri, projection, null, null, null);
                int column_index = cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor1.moveToFirst();

               String selectedImagePath = cursor1.getString(column_index);

            System.out.println("Image Path : " + selectedImagePath);
                picture.setVisibility(View.VISIBLE);
           // picture.setImageURI(selectedImageUri);

            picture.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));

           photoGallery.addImage(new File(selectedImagePath));

        }

    }
}
