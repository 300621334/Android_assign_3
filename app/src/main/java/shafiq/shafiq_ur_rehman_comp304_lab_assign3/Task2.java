package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Task2 extends AppCompatActivity {

    //region >>>Variables
    ImageView img;
    Button btnStartAnim, btnStopAnim;
    BitmapDrawable frame1,frame2,frame3,frame4,frame5;
    AnimationDrawable anim = new AnimationDrawable();
    //endregion

    //Called when activity created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        img = (ImageView) findViewById(R.id.imgViewAnim);
        btnStartAnim = (Button) findViewById(R.id.btnStart);
        btnStopAnim = (Button) findViewById(R.id.btnStop);

        //Convert PNG into drawable-images because AnimationDrawable accepts Drawables
        frame1 = (BitmapDrawable) getResources().getDrawable(R.drawable.boy1);
        frame2 = (BitmapDrawable) getResources().getDrawable(R.drawable.boy2);
        frame3 = (BitmapDrawable) getResources().getDrawable(R.drawable.boy3);
        frame4 = (BitmapDrawable) getResources().getDrawable(R.drawable.boy4);
        frame5 = (BitmapDrawable) getResources().getDrawable(R.drawable.boy5);

        //Add frames to animation, & duration in millisec
        anim.addFrame(frame1, 100);
        anim.addFrame(frame2, 100);
        anim.addFrame(frame3, 100);
        anim.addFrame(frame4, 100);
        anim.addFrame(frame5, 100);
        anim.addFrame(frame5, 100);
        anim.addFrame(frame4, 100);
        anim.addFrame(frame3, 100);
        anim.addFrame(frame2, 100);
        anim.addFrame(frame1, 100);

        //Set anim to loop
        anim.setOneShot(false);

        //set anim as background of ImageView
        img.setBackground(anim);
    }

    //Start Animation
    public void startAnim(View view)
    {
        anim.setVisible(true, false);
        anim.start();
    }

    //Stop Animation
    public void stopAnim(View view)
    {
        anim.stop();
        anim.setVisible(false,false);
    }
}
