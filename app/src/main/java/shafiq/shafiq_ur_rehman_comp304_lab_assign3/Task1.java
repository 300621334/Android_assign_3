package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;



//Good = https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1b-p-draw-on-a-canvas/11-1b-p-draw-on-a-canvas.html
//https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html

public class Task1 extends AppCompatActivity  {

    //region Variables
    boolean isMouseDown = false, isMouseMoving = false;
    ImageView child;//ctrl that'll hold canvas(drawing surface made of px)
    ViewGroup parent;
    ImageView imgV;//ctrl that'll hold canvas(drawing surface made of px)
    Bitmap bmp;//Holds pixels. Canvas is made up of pixels. Hence pass bmp to canvas. Canvas is wrapper around bmp
    Canvas canvas;//surface on w to draw. Has fn to instruct the drawing.
    Paint paint;//brush/pen to draw pixels with
    int startx = 10;
    int starty = 10;
    int endx=10;
    int endy=10;
    Spinner sizeSpinner;
    RadioGroup radGp;
    float lineSize = 20;
    int lineColor = Color.RED;

    //W & H of View is NOT calculated unless onCreate() is fully finished executing. Hence get W/H via onClick()
    int vWidth, vHeight;


    //endregion



//Called when activity created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        imgV = (ImageView) findViewById(R.id.imgInCanvas);
        radGp = findViewById(R.id.radGpColor);
        sizeSpinner = (Spinner) findViewById(R.id.spinSize);

        //Instantiate Paint
        paint = new Paint();
        paint = setPaint();


        //region>>> Listen to changes in Color/Size selection
        radGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            /*
            * NOTE: RadBtn Index & ID are 2 different things
            * 'i' below is ID (NOT index) w is like 231987387 sth >> so >> findViewByID(i)
            * CANNOT do .getChildAt(i)....
            * https://stackoverflow.com/questions/45484718/im-trying-to-get-the-radio-buttons-textfrom-a-fragment-but-its-showing-null
            * */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                View v = findViewById(id);
                RadioButton b = (RadioButton)v;
                int index = radioGroup.indexOfChild(b);
                RadioButton r = (RadioButton)radioGroup.getChildAt(index);
                String txt = r.getText().toString().toUpperCase();

                switch(txt)
                {
                    case "RED":
                        lineColor = Color.RED;
                        break;
                    case "GREEN":
                        lineColor = Color.GREEN;
                            break;
                    case "YELLOW":
                        lineColor = Color.YELLOW;
                        break;
                }
                setPaint();
            }
        });
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lineSize = Float.parseFloat(adapterView.getSelectedItem().toString());
                setPaint();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
                lineSize = 20.0f;
                setPaint();
            }
        });

        //Touch Listener
       imgV.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent e) {
               switch (e.getAction())
               {
                   case MotionEvent.ACTION_DOWN:
                       startx = Math.round(e.getX());//rounds float to nearest int
                       starty = Math.round(e.getY());
                       return  true;
                   case MotionEvent.ACTION_UP:
                       endx = Math.round(e.getX());//rounds float to nearest int
                       endy = Math.round(e.getY());
                       drawLine();
                       imgV.invalidate();
                       break;
               }
               return  true;
           }
       });
        //endregion

        //Since content view size is NOT measures until onCreate finishes. so nee to call a method AFTER that
        imgV.post(new Runnable()
        {
            @Override
            public void run()
            {
                vWidth = imgV.getWidth();
                vHeight = imgV.getHeight();
                bmp = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);//err W & Ht must b > 0 bcoz inside onCreate() W/Ht of view are not available yet//a Bitmap.config configuration object. A bitmap configuration describes how pixels are stored. Each color is encoded in 8 bits (8+8+8+8=4bytes)

                //associate Canvas   with mBitmap, so that drawing on the canvas draws on the bitmap
                canvas = new Canvas(bmp);//providing a bmp explicitly to canvas. bcoz it's a wrapper around Bitmap. If a class extends View then onDraw(Canvas) of View provides a canvas for that view
                canvas.drawColor(Color.BLACK);//paint the whole canvas as black surface
                imgV.draw(canvas);//re-draws imgV on top of canvas I provide, by passing it to onDraw(canvas) instead of canvas provided by Android. Init draw was done at setContentView()
                imgV.setImageBitmap(bmp);
            }
        });

    }//onCreate ends



   //Keyboard events handled
    //This onKeyDown() is stopping phone's physical back btn to move back to prev act!!!
    //resolves ONLY when the WHOLE method is removed. Removing inside code doesn't help!!!
    //So I added KeyEvent.KEYCODE_BACK to go go back to previous activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        imgV.setFocusable(true);
        imgV.requestFocus();

        //return super.onKeyDown(keyCode, event);
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                moveDown(imgV);//imgV is dummy arg. ust bcoz arrow imgs need to pass a View in OnClick(View v)
                return  true;//Return true to prevent this event from being propagated further, or false to indicate that you have not handled this event and it should continue to be propagated.
            case KeyEvent.KEYCODE_DPAD_UP:
                moveUp(imgV);
                return  true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                moveLeft(imgV);
                return  true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                moveRight(imgV);
                return  true;
            case KeyEvent.KEYCODE_BACK:
                super.onBackPressed();
                return  true;
        }
        //Return true to prevent this event from being propagated further, or false to indicate that you have not handled this event and it should continue to be propagated.
        return  false;//if none of 4 keys
    }


    //region >>> Move Methods MUST be public or else ImageView's onClick() cannot fire them
    public void moveRight(View v) {
        endx +=5;
        drawLine();
        imgV.invalidate();
    }

    public void moveLeft(View v) {
        endx -=5;
        drawLine();
        imgV.invalidate();
    }

    public void moveUp(View v) {
        endy -=5;
        drawLine();
        imgV.invalidate();
    }

    public void moveDown(View v) {
        endy += 5;
        drawLine( );
        imgV.invalidate();
    }
    //endregion

    //Draw Line
    private void drawLine() {
        ((EditText)findViewById(R.id.txtX)).setText(String.valueOf(endx));
        ((EditText)findViewById(R.id.txtY)).setText(String.valueOf(endy));
        canvas.drawLine(startx,starty,endx,endy,paint);
        startx = endx;
        starty=endy;
    }

    //Clear Canvas
    public void clearCanvas(View view)
    {
        canvas.drawColor(Color.BLACK);
        ((EditText)findViewById(R.id.txtX)).setText("");
        ((EditText)findViewById(R.id.txtY)).setText("");
        startx = endx = 10;
        starty= endy=10;
        imgV.invalidate();//If sth changes & need to be reflected on screen, call Invalidate(). It calls onDraw() of view
    }
    private Paint setPaint() {
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineSize);
        //p.setStyle(Paint.Style.FILL);//to fill rect etc, then no nee for setStrokeWidth() bcoz border will not be separately visible
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//smooth lines
        return paint;
    }

    private Canvas setCanvas(Canvas c) {
        c.drawColor(Color.BLACK);//make whole canvas black to begin with
        return c;
    }

    private void drawOnCanvas(float x, float y) {
        canvas.drawCircle(x, y, 50, paint);
    }

}
