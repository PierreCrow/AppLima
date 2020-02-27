package com.avances.applima.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.avances.applima.R;
import com.avances.applima.presentation.ui.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class PrivacePoliciesDialog extends DialogFragment {


    Button btnMiraflores, btnPuebloLibre, btnBarranco;
    ImageView ivClose;
    LinearLayout transparent_linear_filter;
    Button btnInteres1, btnInteres2, btnInteres3, btnInteres4, btnInteres5;
    public static boolean interes1Pressed, interes2Pressed, interes3Pressed, interes4Pressed, interes5Pressed;
    ImageView ivDistrit1, ivDistrit2, ivDistrit3, ivDistrit4, ivDistrit5;
    ImageView ivDistrit1_on, ivDistrit2_on, ivDistrit3_on, ivDistrit4_on, ivDistrit5_on;
    public static boolean distritPressed1, distritPressed2, distritPressed3, distritPressed4, distritPressed5;
    TextView tvDistrit1, tvDistrit2, tvDistrit3, tvDistrit4, tvDistrit5;

    List<String> filters;
    Button btnAplicar;

    @Override
    public void onDismiss(DialogInterface dialog) {

        juntartags();
        sendCallback();


    }

    public interface CierraDialogFilter {
        public void onClose_Filter(Boolean close, Context context);
    }


    void sendCallback() {
       // Activity ahhh = getActivity();
        //   GastosFragment dda = (GastosFragment)getFragmentManager().findFragmentById(R.id.containerView);
        //  List<Fragment> ah=dda.getChildFragmentManager().getFragments();
        //  Fragment ahhh=ah.get(0);

        Fragment ahhh=new HomeFragment();

        if (ahhh instanceof CierraDialogFilter) {
            ((CierraDialogFilter) ahhh).onClose_Filter(true,getContext());
        }

    }


    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.terms_and_condition, new LinearLayout(getActivity()), false);

        ivClose = (ImageView) view.findViewById(R.id.ivClose);
        transparent_linear_filter = (LinearLayout) view.findViewById(R.id.transparent_linear_filter);
        btnAplicar=(Button)view.findViewById(R.id.btnAplicar);

        transparent_linear_filter.setOnClickListener(new View.OnClickListener() {
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

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();


            }
        });


        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder.setContentView(view);
        return builder;

    }


    void setFields()
    {
        if (distritPressed1) {
            ivDistrit1_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed2) {
            ivDistrit2_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed3) {
            ivDistrit3_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed4) {
            ivDistrit4_on.setVisibility(View.VISIBLE);
        }

        if (distritPressed5) {
            ivDistrit5_on.setVisibility(View.VISIBLE);
        }


        if (interes1Pressed) {
            btnInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            btnInteres1.setTextColor(Color.WHITE);
        }

        if (interes2Pressed) {
            btnInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            btnInteres1.setTextColor(Color.WHITE);
        }

        if (interes3Pressed) {
            btnInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            btnInteres1.setTextColor(Color.WHITE);
        }

        if (interes4Pressed) {
            btnInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            btnInteres1.setTextColor(Color.WHITE);
        }

        if (interes5Pressed) {
            btnInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
            btnInteres1.setTextColor(Color.WHITE);
        }
    }

    void juntartags() {

        if (distritPressed1) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=tvDistrit1.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=tvDistrit1.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }

        }

        if (distritPressed2) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=tvDistrit2.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=tvDistrit2.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }


        }

        if (distritPressed3) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=tvDistrit3.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=tvDistrit3.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }


        }

        if (distritPressed4) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=tvDistrit4.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=tvDistrit4.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }

        }

        if (distritPressed5) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=tvDistrit5.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=tvDistrit5.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }


        }


        if (interes1Pressed) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=btnInteres1.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=btnInteres1.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }


        }

        if (interes2Pressed) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=btnInteres2.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=btnInteres2.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }

        }

        if (interes3Pressed) {

            boolean alreadyExist=false;

            if(HomeFragment.tags.size()>0)
            {
                String newTag=btnInteres3.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=btnInteres3.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }

        }

        if (interes4Pressed) {

            boolean alreadyExist=false;
            if(HomeFragment.tags.size()>0)
            {
                String newTag=btnInteres4.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=btnInteres4.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }

        }

        if (interes5Pressed) {
            boolean alreadyExist=false;
            if(HomeFragment.tags.size()>0)
            {
                String newTag=btnInteres5.getText().toString().toLowerCase();
                for(int i=0;i<HomeFragment.tags.size();i++)
                {
                    if(newTag.equals(HomeFragment.tags.get(i)))
                    {
                        alreadyExist=true;
                    }
                }
                if(!alreadyExist)
                {
                    HomeFragment.tags.add(newTag);
                }
            }
            else
            {
                String newTag=btnInteres5.getText().toString().toLowerCase();
                HomeFragment.tags.add(newTag);
            }

        }


    }

    void clickEvents() {
        btnInteres1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interes1Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes1Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres1.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes1Pressed = true;
                }


            }
        });
        btnInteres2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interes2Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes2Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres2.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes2Pressed = true;
                }
            }
        });
        btnInteres3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes3Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes3Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres3.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes3Pressed = true;
                }
            }
        });
        btnInteres4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes4Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes4Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres4.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes4Pressed = true;
                }
            }
        });
        btnInteres5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interes5Pressed) {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.BLACK);
                    }
                    btnInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_off);

                    interes5Pressed = false;
                } else {
                    if (v instanceof Button) {
                        ((Button) v).setTextColor(Color.WHITE);
                    }
                    btnInteres5.setBackgroundResource(R.drawable.shape_home_filter_interes_on);
                    interes5Pressed = true;
                }
            }
        });


        ivDistrit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (distritPressed1) {
                    ivDistrit1_on.setVisibility(View.GONE);

                    distritPressed1 = false;
                } else {
                    ivDistrit1_on.setVisibility(View.VISIBLE);
                    distritPressed1 = true;
                }


            }
        });

        ivDistrit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (distritPressed2) {
                    ivDistrit2_on.setVisibility(View.GONE);

                    distritPressed2 = false;
                } else {
                    ivDistrit2_on.setVisibility(View.VISIBLE);
                    distritPressed2 = true;
                }


            }
        });

        ivDistrit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (distritPressed3) {
                    ivDistrit3_on.setVisibility(View.GONE);

                    distritPressed3 = false;
                } else {
                    ivDistrit3_on.setVisibility(View.VISIBLE);
                    distritPressed3 = true;
                }


            }
        });

        ivDistrit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (distritPressed4) {
                    ivDistrit4_on.setVisibility(View.GONE);

                    distritPressed4 = false;
                } else {
                    ivDistrit4_on.setVisibility(View.VISIBLE);
                    distritPressed4 = true;
                }


            }
        });


        ivDistrit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (distritPressed5) {
                    ivDistrit5_on.setVisibility(View.GONE);

                    distritPressed5 = false;
                } else {
                    ivDistrit5_on.setVisibility(View.VISIBLE);
                    distritPressed5 = true;
                }


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
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

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


}
