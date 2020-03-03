package com.avances.lima.presentation.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import pl.aprilapps.easyphotopicker.EasyImage;

public class ImportPhotoBottomFragment extends BottomSheetDialogFragment {

    private Context mContext;
    private Activity mActivity;
    private Fragment mFragment;
    private ImportPhotoBottomFragment.Listener mListener;
    private static final int FROM_CAMERA = 0;
    private static final int FROM_GALLERY = 1;
    private static final int CANCEL = 2;
    public static final String EXTRA_PARAMETER_DATA_TAG = "EXTRA_PARAMETER_DATA_TAG";
    public static int EXTRA_PARAMETER_DATA_VALUE = 0;


    //-------------------------------------COMO SE USA--------------------------------

    //implements ImportPhotoBottomFragment.Listener
/*
    public void showBottomSourcePhoto(int requestCode) {
        ImportPhotoBottomFragment bottomSheetFragment = new ImportPhotoBottomFragment();
        Bundle args = new Bundle();
        args.putInt(ImportPhotoBottomFragment.EXTRA_PARAMETER_DATA_TAG, requestCode);
        bottomSheetFragment.setArguments(args);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
*/
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
            }

            @Override
            public void onImagesPicked(List<File> imagesFiles, EasyImage.ImageSource source, int type) {
                File _file = imagesFiles.size() > 0 ? imagesFiles.get(0) : null;
                if (_file != null) {
                    int typeFileSource = ComponentFilePhoto.getTypeFileSource(source);
                    String description = getImageFileDescription(type, typeFileSource);
                    ComponentFilePhoto filePhoto = new ComponentFilePhoto(getContext(), _file, typeFileSource, description);
                    setPhotosWell(type, filePhoto);
                } else {
                   // showErrorMessage(getString(R.string.import_from_error));
                }
            }
        });

        }
*/

/*
    private String getImageFileDescription(int typePhoto, int typeSource) {
        String description = "";
        switch (typePhoto) {
            case Constantes.REQUEST_PHOTOS_SELECTED.PHOTOS_TRANSPORT_ADITTIONAL:
                description = description.concat("Imagen adicional del transporte");
                break;
            case Constantes.REQUEST_PHOTOS_SELECTED.PHOTO_TRANSPORT_ONE:
                description = description.concat("Imagen de transport");
                break;
            case Constantes.REQUEST_PHOTOS_SELECTED.PHOTO_TRANSPORT_TWO:
                description = description.concat("Imagen de transport");
                break;
            default:
        }

        description = description.concat("\n");
        switch (typeSource) {
            case ComponentFilePhoto.CAMERA:
                description = description.concat("Imagen capturada desde la cámara");
                break;
            case ComponentFilePhoto.GALLERY:
                description = description.concat("Imagen capturada desde la galería");
                break;
        }
        return description;
    }
*/
/*
    ComponentFilePhoto getPhotoOne;
    ComponentFilePhoto getPhotoTwo;
    ImageView img_PhotoTwo;
    ImageView img_PhotoOne;
    private void setPhotosWell(int typePhoto, ComponentFilePhoto file) {

        ArrayList<ComponentFilePhoto> listPhoto = new ArrayList<>();
        listPhoto.add(file);

        switch (typePhoto) {
            case Constantes.REQUEST_PHOTOS_SELECTED.PHOTOS_TRANSPORT_ADITTIONAL:
                break;
            case Constantes.REQUEST_PHOTOS_SELECTED.PHOTO_TRANSPORT_ONE:
                getPhotoOne = file;
              //  Glide.with(SampleActivity.this).load(getPhotoOne.getFile()).apply(new RequestOptions().placeholder(R.drawable.ic_galeria)).into(img_PhotoOne);
                break;
            case Constantes.REQUEST_PHOTOS_SELECTED.PHOTO_TRANSPORT_TWO:
                getPhotoTwo = file;
              //  Glide.with(SampleActivity.this).load(getPhotoTwo.getFile()).apply(new RequestOptions().placeholder(R.drawable.ic_galeria)).into(img_PhotoTwo);
                break;
            default:
        }
    }
*/
    //-------------------------------------------------------------------------------



    public ImportPhotoBottomFragment() {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_photo_import_list, container, false);

        Bundle mArgs = this.getArguments();
        if (mArgs != null) {
            EXTRA_PARAMETER_DATA_VALUE = mArgs.getInt("EXTRA_PARAMETER_DATA_TAG");
        }

        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView)view;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ImportPhotoBottomFragment.ItemAdapter(3));
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment parent = this.getParentFragment();
        if (parent != null) {
            this.mListener = (ImportPhotoBottomFragment.Listener)parent;
            this.mFragment = parent;
        } else {
            this.mListener = (ImportPhotoBottomFragment.Listener)context;
            this.mActivity = (Activity)context;
        }

        if (this.mFragment == null && this.mActivity == null) {
            this.mListener.onErrorImportPhoto("errorr");
        }

    }

    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    public Context getContext() {
        this.mContext = (Context)(this.mActivity != null ? this.mActivity : this.mFragment.getContext());
        return this.mContext;
    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final int mItemCount;

        ItemAdapter(int itemCount) {
            this.mItemCount = itemCount;
        }

        public ImportPhotoBottomFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ImportPhotoBottomFragment.this.new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        public void onBindViewHolder(ImportPhotoBottomFragment.ViewHolder holder, int position) {
            switch(position) {
                case 0:
                    holder.icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_photograph));
                    holder.text.setText("Importar desde cámara");
                    break;
                case 1:
                    holder.icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_gallery));
                    holder.text.setText("Importar desde galería");
                    break;
                case 2:
                    holder.icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_cancel));
                    holder.text.setText("Cancelar");
                    break;
                default:
                    holder.text.setText(String.valueOf(position));
            }

        }

        public int getItemCount() {
            return this.mItemCount;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout container;
        final ImageView icon;
        final TextView text;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_photo_import_item_list, parent, false));
            this.container = (LinearLayout)this.itemView.findViewById(R.id.container);
            this.icon = (ImageView)this.itemView.findViewById(R.id.icon);
            this.text = (TextView)this.itemView.findViewById(R.id.text);
            this.container.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (ImportPhotoBottomFragment.this.mListener != null) {
                        ViewHolder.this.onItemClick(ViewHolder.this.getAdapterPosition());
                        ImportPhotoBottomFragment.this.dismiss();
                    } else {
                        ImportPhotoBottomFragment.this.mListener.onErrorImportPhoto("Error al intentar importar imáganes");
                    }

                }
            });
        }

        private void onItemClick(int position) {
            try {
                switch(position) {
                    case 0:
                        EasyImage.openCameraForImage(ImportPhotoBottomFragment.this.mActivity, ImportPhotoBottomFragment.EXTRA_PARAMETER_DATA_VALUE > 0 ? ImportPhotoBottomFragment.EXTRA_PARAMETER_DATA_VALUE : 0);
                        break;
                    case 1:
                        EasyImage.openGallery(ImportPhotoBottomFragment.this.mActivity, ImportPhotoBottomFragment.EXTRA_PARAMETER_DATA_VALUE > 0 ? ImportPhotoBottomFragment.EXTRA_PARAMETER_DATA_VALUE : 0);
                    case 2:
                }
            } catch (Exception var3) {
                ImportPhotoBottomFragment.this.mListener.onErrorImportPhoto("Error al intentar importar imáganes");
            }

        }
    }

    public interface Listener {
        void onErrorImportPhoto(String var1);
    }
}