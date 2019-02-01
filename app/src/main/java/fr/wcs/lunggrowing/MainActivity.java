package fr.wcs.lunggrowing;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            final View imageView = findViewById(R.id.imageView);

            final ImageView logobleuclair = findViewById(R.id.imageView);

            final ImageView logobleufonçé = findViewById(R.id.imageView2);

            final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);

            Button btPlus = (Button) findViewById(R.id.btPlus);

            final ValueAnimator animatorcolor = ValueAnimator.ofFloat(0f, 1f);
            final  AnimatorSet growing = new AnimatorSet().setDuration(3000);
            final  AnimatorSet collapsing = new AnimatorSet().setDuration(3000);
            final   AnimatorSet squence = new AnimatorSet();


            Button btcolor = findViewById(R.id.btColor);

            btcolor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    growing.end();
                    collapsing.end();
                    squence.end();
                    squence.cancel();



                    animatorcolor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            logobleufonçé.setAlpha((Float) animation.getAnimatedValue());
                        }
                    });

                    animatorcolor.setDuration(3000);
                    animatorcolor.setRepeatMode(ValueAnimator.REVERSE);
                    animatorcolor.setRepeatCount(-1);
                    animatorcolor.start();
                }
            });


            btPlus.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    animatorcolor.end();
                    logobleufonçé.setAlpha(0f);



                    growing
                            .play(ObjectAnimator.ofFloat(imageView, "scaleX", 2f))
                            .with(ObjectAnimator.ofFloat(imageView, "scaleY", 2f));


                    AnimatorSet pausing = new AnimatorSet().setDuration(300);

//                    pausing
//                            .play(ObjectAnimator.ofFloat(imageView, "scaleX", 2f))
//                            .with(ObjectAnimator.ofFloat(imageView, "scaleY", 2f))
//                            .after(growing);


                    collapsing.setStartDelay(1000);

                    collapsing
                            .play(ObjectAnimator.ofFloat(imageView, "scaleX", 1f))
                            .with(ObjectAnimator.ofFloat(imageView, "scaleY", 1f));



                    squence.playSequentially(growing,collapsing);

                    squence.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //super.onAnimationEnd(animation);
                            animation.start();
                        }
                    });
                    squence.start();

                    /*imageView.animate().
                            scaleX(1.5f).
                            scaleY(1.5f).
                            setDuration(3000).start();*/
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    imageView.animate().
//                            scaleX(1f/(1.5f)).
//                            scaleY(1f/(1.5f)).
//                            setDuration(5000).start();
//

                    //    imageView.startAnimation(anim);

                }
            });

        }



}
