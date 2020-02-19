package com.avances.applima.presentation.ui.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.presentation.ui.fragments.DistritDetailFragment;
import com.avances.applima.presentation.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DistritHorizontalListDataAdapter extends RecyclerView.Adapter<DistritHorizontalListDataAdapter.SingleItemRowHolder> {


    private List<DistritNeighborhood> itemsList;
    private Context mContext;

    Integer counter = 0;
    Integer imageOne=0,imageTwo=1,imageThree=2, imageViewing;
    public OnDistritHorizontalClickListener mlistener;


    public DistritHorizontalListDataAdapter(OnDistritHorizontalClickListener mlistener,Context context, List<DistritNeighborhood> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener=mlistener;
    }



    public interface OnDistritHorizontalClickListener {
        void onDistritHorizontalClicked(View v, Integer position);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v;
        if (Helper.getUserAppPreference(mContext).isLogged()) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_distrit_home_logged_in, null);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_distrit_home, null);
        }


        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        DistritNeighborhood dbDistritNeighborhood = itemsList.get(i);
        holder.tvTitle.setText(dbDistritNeighborhood.getDistrit());
        holder.tvSubTittle.setText(dbDistritNeighborhood.getShortDescription());

       // Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eaters_collective_rs2opgfbemk_unsplash);

        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(0),holder.imagen1,mContext);
        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(1),holder.imagen2,mContext);
        Helper.urlToImageView(dbDistritNeighborhood.getImageList().get(2),holder.imagen3,mContext);



        // ImageViewAnimatedChange(mContext,holder.itemImage,bitmap);

        //   nose(holder.itemImage,holder);

        startTimer(holder);



    }

    @Override
    public int getItemCount() {

        // return Integer.MAX_VALUE;

        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        protected TextView tvTitle,tvSubTittle;

        protected LinearLayout itemImage;

        ViewSwitcher switcher;//= (ViewSwitcher) v;

        ImageView imagen1, imagen2,imagen3;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvSubTittle = (TextView) view.findViewById(R.id.tvSubTittle);
            this.itemImage = (LinearLayout) view.findViewById(R.id.imagenDistrito);
          //  this.switcher = (ViewSwitcher) view.findViewById(R.id.switcher);

            this.imagen1 = (ImageView) view.findViewById(R.id.imagen1);
            this.imagen2 = (ImageView) view.findViewById(R.id.imagen2);
            this.imagen3 = (ImageView) view.findViewById(R.id.imagen3);

            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
           // Integer aa=v.getId();

            mlistener.onDistritHorizontalClicked(view,this.getPosition());

          //  loadDistritDetailFragment();
        }
    }


    void loadDistritDetailFragment() {


        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DistritDetailFragment accountFragment = new DistritDetailFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }



    void startTimer(SingleItemRowHolder holder) {

       imageViewing=imageOne;

        ArrayList<Drawable> imagenes = new ArrayList<>();
   //     imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.miramira));
    //    imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.adrian_dascal_9z_ab1v0vlc_unsplash));
    //    imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.andres_urena__e8aox5rids_unsplash));

        CountDownTimer cTimer = null;
        cTimer = new CountDownTimer(1000000000, 6000) {
            public void onTick(long millisUntilFinished) {


                switch (imageViewing)
                {
                    case 0:
                        holder.imagen1.setVisibility(View.VISIBLE);
                        holder.imagen2.setVisibility(View.GONE);
                        holder.imagen3.setVisibility(View.GONE);
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.imagen1, "alpha",0, 1);
                        objectAnimator.setDuration(1000);
                        objectAnimator.setStartDelay(0);
                        objectAnimator.start();
                        imageViewing=imageTwo;
                        break;

                    case 1:

                        holder.imagen2.setVisibility(View.VISIBLE);
                        holder.imagen3.setVisibility(View.GONE);
                        holder.imagen1.setVisibility(View.GONE);
                        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(holder.imagen2, "alpha",0, 1);
                        objectAnimator2.setDuration(1000);
                        objectAnimator2.setStartDelay(0);
                        objectAnimator2.start();
                        imageViewing=imageThree;
                        break;

                    case 2:
                        holder.imagen3.setVisibility(View.VISIBLE);
                        holder.imagen2.setVisibility(View.GONE);
                        holder.imagen2.setVisibility(View.GONE);
                        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(holder.imagen3, "alpha",0, 1);
                        objectAnimator3.setDuration(1000);
                        objectAnimator3.setStartDelay(0);
                        objectAnimator3.start();
                        imageViewing=imageOne;
                        break;

                }




/*
                if (counter == 0) {
                    //  dice.setBackground(imagenes.get(1));
                    counter++;
                    holder.imagen2.setBackground(imagenes.get(1));
                } else {
                    if (counter == 1) {
                        //  dice.setBackground(imagenes.get(2));
                        counter++;
                        holder.imagen1.setBackground(imagenes.get(2));
                    } else {

                        if (counter == 2) {
                            //  dice.setBackground(imagenes.get(0));
                            counter = 0;
                            holder.imagen2.setBackground(imagenes.get(0));

                        }


                    }

                }


                if (holder.switcher.getDisplayedChild() == 0) {
                    holder.switcher.showNext();
                } else {
                    holder.switcher.showPrevious();
                }
*/

            }

            public void onFinish() {
            }
        };
        cTimer.start();
    }



    //-----------------------------------------------------
    public static void ImageViewAnimatedChange(Context c, final LinearLayout v, final Bitmap new_image) {


        ArrayList<Drawable> imagenes = new ArrayList<>();
    //    imagenes.add(ContextCompat.getDrawable(c, R.drawable.willian_justen_de_vasconcellos_zs8bpxw3oum_unsplash));
    //    imagenes.add(ContextCompat.getDrawable(c, R.drawable.eaters_collective_rs2opgfbemk_unsplash));
    //    imagenes.add(ContextCompat.getDrawable(c, R.drawable.eaters_collective_rs2opgfbemk_unsplash));


        Random random = new Random();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                int randomNum = random.nextInt(2);




             /*   Animation aniFade = AnimationUtils.loadAnimation(c,R.anim.fadein);
                v.startAnimation(aniFade);
                v.setBackground(imagenes.get(randomNum));
*/


                final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
                final Animation anim_in = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
                anim_out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // v.setImageBitmap(new_image);
                        Drawable d = new BitmapDrawable(c.getResources(), new_image);
                        //  v.setBackground(d);
                        v.setBackground(imagenes.get(randomNum));
                        anim_in.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                            }
                        });
                        v.startAnimation(anim_in);
                    }
                });
                v.startAnimation(anim_out);


                handler.postDelayed(this, 4000);
            }
        }, 4000);


    }


    void nose(LinearLayout dice, SingleItemRowHolder holder) {
     /*   int iddd1=R.drawable.descarga;
        int iddd2=R.drawable.ic_filter;

        int[] images = new int[0];

        images[1]=iddd1;
        images[2]=iddd2;
*/


        ArrayList<Drawable> imagenes = new ArrayList<>();
      //  imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.miramira));
     //   imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.adrian_dascal_9z_ab1v0vlc_unsplash));
     //   imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.andres_urena__e8aox5rids_unsplash));
        //imagenes.add(ContextCompat.getDrawable(mContext, R.drawable.descarga));


        Random random = new Random();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                int randomNum = random.nextInt(2);
                int randomNum1 = random.nextInt(2);


                if (counter == 0) {
                    //  dice.setBackground(imagenes.get(1));
                    counter++;
                    holder.imagen2.setBackground(imagenes.get(1));
                } else {
                    if (counter == 1) {
                        //  dice.setBackground(imagenes.get(2));
                        counter++;
                        holder.imagen1.setBackground(imagenes.get(2));
                    } else {

                        if (counter == 2) {
                            //  dice.setBackground(imagenes.get(0));
                            counter = 0;
                            holder.imagen2.setBackground(imagenes.get(0));

                        }


                    }

                }



             /*   Animation aniFade = AnimationUtils.loadAnimation(mContext,R.anim.fadein);
                dice.startAnimation(aniFade);
                dice.setBackground(imagenes.get(randomNum));
*//*
                TransitionDrawable td = new TransitionDrawable( new Drawable[] {
                        imagenes.get(randomNum),
                        imagenes.get(randomNum1)
                });
                Animation aniFade = AnimationUtils.loadAnimation(mContext,R.anim.fadein);
                dice.startAnimation(aniFade);
                dice.setBackground(td);
*/


                //   holder.imagen1.setBackground(imagenes.get(randomNum));
                //   holder.imagen2.setBackground(imagenes.get(randomNum1));

                if (holder.switcher.getDisplayedChild() == 0) {
                    holder.switcher.showNext();
                } else {
                    holder.switcher.showPrevious();
                }

                handler.postDelayed(this, 4000);
            }
        }, 4000);

    }


}