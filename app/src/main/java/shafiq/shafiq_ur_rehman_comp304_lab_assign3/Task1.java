package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;



//Good = https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1b-p-draw-on-a-canvas/11-1b-p-draw-on-a-canvas.html
//https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
public class Task1 extends Activity {

    boolean isMouseDown = false, isMouseMoving = false;
    ImageView child;//ctrl that'll hold canvas(drawing surface made of px)
    ViewGroup parent;

    //W & H of View is NOT calculated unless onCreate() is fully finished executing. Hence get W/H via onClick()
    int vWidth, vHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        //make a custom View object
        MyCanvasView myCanvasView;
        myCanvasView = new MyCanvasView(this);

        //replace ImageView: https://stackoverflow.com/questions/3334048/android-layout-replacing-a-view-with-another-view-on-run-time
        child = (ImageView) findViewById(R.id.imgViewInCanvas);
        parent =  (ViewGroup) child.getParent();
        //int index = parent.indexOfChild(child);
        parent.removeView(child);
        parent.addView(myCanvasView/*, index*/);

        //Display X,Y coordinates
        String x = Float.toString( myCanvasView.mX);
        String y = Float.toString( myCanvasView.mY);
        ((EditText)findViewById(R.id.txtValueX)).setText(x);
        ((EditText)findViewById(R.id.txtValueY)).setText(y);


    }//onCreate ends
}//class ends
