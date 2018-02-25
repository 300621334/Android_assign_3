package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Task3 extends AppCompatActivity {

    //Variables
    ImageView img;
    Animation anim;


    //Called when activity created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);
        img = (ImageView)findViewById(R.id.imgEarth);

        //Start ANimation method
        startAnimation(img);

        //Listener for animation events
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //Button clicked - Stop Animation
    public void stopAnimation(View view) {
        img.clearAnimation();
    }

    //Button clicked - Start ANimation
    public void startAnimation(View view) {
        anim = AnimationUtils.loadAnimation(this, R.anim.earth);
        img.startAnimation(anim);
    }
}
