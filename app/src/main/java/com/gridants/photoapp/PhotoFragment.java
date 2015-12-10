package com.gridants.photoapp;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

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

    public static PhotoFragment newInstance(){
        PhotoFragment fragment = new PhotoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        Intent pickPicture =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPicture, RESULT_PIC);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_PIC && resultCode == getActivity().RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

//            try {
//                Bitmap bitmap =MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//                picture.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Uri uri = Uri.fromFile(new File(picturePath));
            cursor.close();
            if(picturePath != null){
                Toast.makeText(getActivity(), "URL PRESENT" , Toast.LENGTH_LONG).show();
            }
            Picasso.with(getActivity()).load(uri).into(picture);

        }
    }
}
