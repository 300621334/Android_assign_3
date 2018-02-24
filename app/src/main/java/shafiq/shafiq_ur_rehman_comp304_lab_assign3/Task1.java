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

public class Task1 extends AppCompatActivity {

    boolean isMouseDown = false, isMouseMoving = false;
    ImageView imgV;
    Bitmap bmp;
    Canvas canvas;
    Paint paint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        imgV = (ImageView) findViewById(R.id.imgInCanvas);
        bmp = Bitmap.createBitmap(50/*imgV.getWidth()*/, 50 /*imgV.getHeight()*/, Bitmap.Config.ARGB_8888);//err W & Ht must b > 0!!
        canvas = new Canvas(bmp);//providing a bmp explicitly to canvas. bcoz it's a wrapper around Bitmap. If a class extends View then onDraw(Canvas) of View provides a canvas for that view
        canvas.drawColor(Color.BLACK);
        imgV.draw(canvas);
        imgV.setImageBitmap(bmp);


        //canvas = setCanvas(canvas);
        //paint = setPaint(paint);
        paint = new Paint();

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
}
