package com.avances.lima.presentation.ui.dialogfragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.avances.lima.R;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.SingleClick;

public class PickCameraGalleryDialog extends DialogFragment {

    LinearLayout llCamera, llGalerry, llTransparent;
    SingleClick singleClick;
    public static int importTypee = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.pick_camera_gallery, new LinearLayout(getActivity()), false);
        initUI(view);

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;
    }


    void initUI(View view) {

        llCamera = (LinearLayout) view.findViewById(R.id.llCamera);
        llGalerry = (LinearLayout) view.findViewById(R.id.llGallery);
        llTransparent = (LinearLayout) view.findViewById(R.id.llTransparent);

        onClickListener();

        llCamera.setOnClickListener(singleClick);
        llGalerry.setOnClickListener(singleClick);
        llTransparent.setOnClickListener(singleClick);
    }

    void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.llCamera:
                        importTypee = Constants.TYPE_PHOTO_IMPORT.CAMERA;
                        if (!hasCameraPermission()) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA},
                                    Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
                        } else {
                            displayCameraOrGallery(Constants.TYPE_PHOTO_IMPORT.CAMERA);
                        }
                        break;
                    case R.id.llGallery:
                        importTypee = Constants.TYPE_PHOTO_IMPORT.GALLERY;
                        if (!hasStoragePermission()) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_EXTERNAL_STORAGE},
                                    Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
                        } else {
                            displayCameraOrGallery(Constants.TYPE_PHOTO_IMPORT.GALLERY);
                        }
                        break;
                    case R.id.llTransparent:
                        dismiss();
                        break;
                }
            }
        };
    }


    void displayCameraOrGallery(int i) {
        if (i == Constants.TYPE_PHOTO_IMPORT.CAMERA) {
            openCamera();
        } else {
            openGallery();
        }
    }

    void openCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
    }


    public boolean hasCameraPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasStoragePermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
    }

}
