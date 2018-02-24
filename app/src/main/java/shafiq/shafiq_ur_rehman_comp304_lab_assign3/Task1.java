package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

//Good = https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1b-p-draw-on-a-canvas/11-1b-p-draw-on-a-canvas.html
//https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-11-canvas/11-1a-p-create-a-simple-canvas/11-1a-p-create-a-simple-canvas.html
public class Task1 extends AppCompatActivity {

    boolean isMouseDown = false, isMouseMoving = false;
    ImageView imgV;//ctrl that'll hold canvas(drawing surface made of px)
    Bitmap bmp;//Holds pixels. Canvas is made up of pixels. Hence pass bmp to canvas. Canvas is wrapper around bmp
    Canvas canvas;//surface on w to draw. Has fn to instruct the drawing.
    Paint paint;//brush/pen to draw pixels with

    //W & H of View is NOT calculated unless onCreate() is fully finished executing. Hence get W/H via onClick()
    int vWidth, vHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        imgV = (ImageView) findViewById(R.id.imgInCanvas);
        bmp = Bitmap.createBitmap(1/*imgV.getWidth()*/, 1 /*imgV.getHeight()*/, Bitmap.Config.ARGB_8888);//err W & Ht must b > 0 bcoz inside onCreate() W/Ht of view are not available yet//a Bitmap.config configuration object. A bitmap configuration describes how pixels are stored. Each color is encoded in 8 bits (8+8+8+8=4bytes)
        //associate Canvas   with mBitmap, so that drawing on the canvas draws on the bitmap
        canvas = new Canvas(bmp);//providing a bmp explicitly to canvas. bcoz it's a wrapper around Bitmap. If a class extends View then onDraw(Canvas) of View provides a canvas for that view
        canvas.drawColor(Color.BLACK);//paint the whole canvas as black surface
        imgV.draw(canvas);//re-draws imgV on top of canvas I provide, by passing it to onDraw(canvas) instead of canvas provided by Android. Init draw was done at setContentView()
        imgV.setImageBitmap(bmp);

        paint = new Paint();
        //canvas = setCanvas(canvas);
        paint = setPaint(paint);


        //MousePressed on ImageView containing canvas
        findViewById(R.id.layoutCanvas).setOnTouchListener(new View.OnTouchListener(){//Listener for mouse down etc

            @Override
            public boolean onTouch(View view, MotionEvent e) {
                isMouseDown = (e.getAction() == MotionEvent.ACTION_DOWN);//true if mouse pressed
                isMouseMoving = (e.getAction() == MotionEvent.ACTION_MOVE);//mouse moving?

                if(isMouseMoving && isMouseDown)
                {
                    drawOnCanvas(e.getX(), e.getY());
                }
                return false;
            }
        });
    }//onCreate ends

    private Paint setPaint(Paint p) {
        p.setColor(Color.RED);
        p.setStrokeWidth(5);
        //p.setStyle(Paint.Style.FILL);//to fill rect etc, then no nee for setStrokeWidth() bcoz border will not be separately visible
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);//smooth lines
        return p;
    }

    private Canvas setCanvas(Canvas c) {
        c.drawColor(Color.BLACK);//make whole canvas black to begin with
        return c;
    }

    private void drawOnCanvas(float x, float y) {
        canvas.drawCircle(x, y, 50, paint);
    }

    public void getWidthHeigtht(View view) {
    }
}
