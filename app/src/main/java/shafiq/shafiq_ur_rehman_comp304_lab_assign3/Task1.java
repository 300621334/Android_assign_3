package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;


//Good = https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1b-p-draw-on-a-canvas/11-1b-p-draw-on-a-canvas.html
//https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
public class Task1 extends Activity {

    boolean isMouseDown = false, isMouseMoving = false;
    ImageView child;//ctrl that'll hold canvas(drawing surface made of px)
    ViewGroup parent;
    RadioGroup radGp;
    Spinner sizesDropDown;
    String colorSelected, sizeSelected;
    MyCanvasView myCanvasView;
    Bundle bundleForOnCreate, styleBundle;

    //W & H of View is NOT calculated unless onCreate() is fully finished executing. Hence get W/H via onClick()
    int vWidth, vHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        bundleForOnCreate = savedInstanceState;

     /*   //Get ref to controls & get their values via Listeners
        radGp = findViewById(R.id.radGpColor);
        sizesDropDown = findViewById(R.id.spinThickness);
        radGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                colorSelected =  radioGroup.getChildAt(i).toString();
            }
        });
        sizesDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sizeSelected = adapterView.getSelectedItem().toString();
            }
        });

        //Store values in Bundle
        styleBundle.putString("color", colorSelected);
        styleBundle.putString("size", sizeSelected);
*/

        //make a custom View object
        myCanvasView = new MyCanvasView(this);

        //replace ImageView: https://stackoverflow.com/questions/3334048/android-layout-replacing-a-view-with-another-view-on-run-time
        child = (ImageView) findViewById(R.id.imgViewInCanvas);
        parent =  (ViewGroup) child.getParent();
        //int index = parent.indexOfChild(child);
        parent.removeView(child);
        parent.addView(myCanvasView/*, index*/);
    }//onCreate ends

    public void btnClk_ClearCanvas(View view) {
       onCreate(bundleForOnCreate);//re-draw canvas
    }
}//class ends
