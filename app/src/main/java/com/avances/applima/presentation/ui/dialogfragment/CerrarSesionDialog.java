package com.avances.applima.presentation.ui.dialogfragment;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.AppLimaDb;
import com.avances.applima.data.datasource.db.dao.CountryDao;
import com.avances.applima.data.datasource.db.dao.DistritNeighborhoodDao;
import com.avances.applima.data.datasource.db.dao.EventDao;
import com.avances.applima.data.datasource.db.dao.GenderDao;
import com.avances.applima.data.datasource.db.dao.InterestDao;
import com.avances.applima.data.datasource.db.dao.PlaceDao;
import com.avances.applima.data.datasource.db.dao.RouteDao;
import com.avances.applima.data.datasource.db.dao.SuggestedTagDao;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.avances.applima.presentation.ui.activities.Splash;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class CerrarSesionDialog extends DialogFragment {


    ImageView ivClose;
    RelativeLayout rlLogOut, rlNotNow;

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    AppLimaDb appLimaDb= new AppLimaDb() {
        @Override
        public PlaceDao placeDao() {
            return null;
        }

        @Override
        public InterestDao interestDao() {
            return null;
        }

        @Override
        public DistritNeighborhoodDao distritNeighborhoodDao() {
            return null;
        }

        @Override
        public RouteDao routeDao() {
            return null;
        }

        @Override
        public EventDao eventDao() {
            return null;
        }

        @Override
        public CountryDao countryDao() {
            return null;
        }

        @Override
        public GenderDao genderDao() {
            return null;
        }

        @Override
        public SuggestedTagDao suggestedTagDao() {
            return null;
        }

        @NonNull
        @Override
        protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
            return null;
        }

        @NonNull
        @Override
        protected InvalidationTracker createInvalidationTracker() {
            return null;
        }

        @Override
        public void clearAllTables() {

        }
    };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.cerrar_sesion_dialog, new LinearLayout(getActivity()), false);

        initUI(view);

        clickEvents();


        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }


    void initUI(View view) {

        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        rlLogOut = (RelativeLayout) view.findViewById(R.id.rlLogOut);
        rlNotNow = (RelativeLayout) view.findViewById(R.id.rlNotNow);
    }


    void clickEvents() {
        rlLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Helper.logOut(getContext(), Helper.getUserAppPreference(getContext()).getRegisterLoginType());
                UserPreference userPreference = Helper.getUserAppPreference(getContext());
                userPreference.setLogged(false);
                Helper.saveUserAppPreference(getContext(), userPreference);

                cliearSharedPrefernce();

            }
        });

        rlNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {

            //numero de pixeles que tendra de ancho
            // int width = 700;
            int width = ViewGroup.LayoutParams.MATCH_PARENT;

            //la altura se ajustara al contenido
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;

            dialog.getWindow().setLayout(width, height);

            //se lo asigno a mi dialogfragment

            //con esto hago que sea invicible
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }


    void cliearSharedPrefernce()
    {
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        //finish();

       // deleteSQLITE();

        UserPreference userPreference=Helper.getUserAppPreference(getContext());
        userPreference.setFirstSyncSuccess(true);
        Helper.saveUserAppPreference(getContext(),userPreference);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void deleteSQLITE() {
        try {
            getApplicationContext().deleteDatabase(AppLimaDb.DB_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
