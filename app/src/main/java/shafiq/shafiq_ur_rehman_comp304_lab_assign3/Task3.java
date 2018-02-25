package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Task3 extends AppCompatActivity {

    /*What you can do is remove animation.setRepeatCount(Animation.INFINITE); and set android:repeatCount="infinite" for each of your animation tag, instead of the parent set tag.
     * https://stackoverflow.com/questions/40520259/android-continues-loop-for-animation*/
    ImageView img;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        img = (ImageView)findViewById(R.id.imgEarth);
        startAnimation(img);

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

    public void stopAnimation(View view) {
        img.clearAnimation();
    }

    public void startAnimation(View view) {
        anim = AnimationUtils.loadAnimation(this, R.anim.earth);
        img.startAnimation(anim);
    }
}
