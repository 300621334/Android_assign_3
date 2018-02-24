package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Shafiq on 2018-02-23.
 */



public class MyCanvasView extends View {

    //region Variables
    private Paint mPaint, framePaint;
    private Path mPath;
    private int mDrawColor;
    private int mBackgroundColor;
    public Canvas mExtraCanvas;//Extra: because they are not the default canvas and bitmap used in the onDraw()
    public Bitmap mExtraBitmap;
    public float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;//buffer so that not every touch is drawn. Rest is just interpolated
    Rect mFrame;
    //endregion

    //default constructor
    public MyCanvasView(Context context) {
        super(context);
        //get int value for colors. Args=(resources, id for color, theme/null)
        mBackgroundColor = ResourcesCompat.getColor(getResources(), R.color.opaque_orange, null);
        mDrawColor = ResourcesCompat.getColor(getResources(), R.color.opaque_yellow, null);
        //why not int: = getResources().getColor(R.color.opaque_orange)

        // Holds the path we are currently drawing.
        mPath = new Path();

        // Set up the paint with which to draw.
        mPaint = new Paint();
        mPaint.setColor(mDrawColor);
        mPaint.setAntiAlias(true);// Smoothe out edges
        mPaint.setDither(true);// Dithering affects colors on higher-precision device
        mPaint.setStyle(Paint.Style.STROKE); // default: FILL
        mPaint.setStrokeJoin(Paint.Join.ROUND); // default: MITER
        mPaint.setStrokeCap(Paint.Cap.ROUND); // default: BUTT
        mPaint.setStrokeWidth(12); // default: Hairline-width (really thin)

        //Frame color
        framePaint = new Paint();
        framePaint.setColor(Color.GREEN);
        framePaint.setStyle(Paint.Style.STROKE); // default: FILL
        framePaint.setStrokeWidth(10); // default: Hairline-width (really thin)
    }
    //2nd constructor
    public MyCanvasView(Context context, AttributeSet attributeSet)
    {
    super(context);

/*    //get int value for colors. Args=(resources, id for color, theme/null)
    mBackgroundColor = ResourcesCompat.getColor(getResources(), R.color.opaque_orange, null);
    mDrawColor = ResourcesCompat.getColor(getResources(), R.color.opaque_yellow, null);
    //why not int: = getResources().getColor(R.color.opaque_orange)

    // Holds the path we are currently drawing.
    mPath = new Path();

    // Set up the paint with which to draw.
    mPaint = new Paint();
    mPaint.setColor(mDrawColor);
    mPaint.setAntiAlias(true);// Smoothe out edges
    mPaint.setDither(true);// Dithering affects colors on higher-precision device
    mPaint.setStyle(Paint.Style.STROKE); // default: FILL
    mPaint.setStrokeJoin(Paint.Join.ROUND); // default: MITER
    mPaint.setStrokeCap(Paint.Cap.ROUND); // default: BUTT
    mPaint.setStrokeWidth(12); // default: Hairline-width (really thin)*/


}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*this canvas linked to view is different than canvas I made in onSizeChanged()
        But pass uses same bitmap i.e. draws on same bitmap*/
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);

        // Draw a frame around the picture.
        canvas.drawRect(mFrame, framePaint);

    }

    /*Cannot create Canvas inside onCreate because .getWidth() will not work on View (because) android do those calculations AFTER onCreate() is finished
    onSizeChanged() is good place to make Canvas bcoz it's called on rotating screen as well as when activity first inflates the view from no sizes
    W & H passed to this fn are that of this VIEW.
    But in my case canvas is the size of ImageView*/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

       // parent = findViewById(R.id.layoutCanvas);

        
        // Create bitmap same as size of ImageView, create canvas to draw on that bitmap
        //mExtraBitmap = Bitmap.createBitmap(parent.getWidth(), parent.getHeight(), Bitmap.Config.ARGB_8888);//crashes app but with error:Could not find class 'android.graphics.drawable.RippleDrawable'!!!
        mExtraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);

        //When we ask Canvas to draw, it draws on th bmp asso w it
        mExtraCanvas.drawColor(mBackgroundColor);

        // Calculate the rect a frame around the picture.
        int inset = 40;
        mFrame = new Rect (inset, inset, w - inset, h - inset);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();//relative to canvas. NOT screen
        float y = event.getY();

/*Invalidate() is inside the case statements because there are many other types of motion events passed
into this listener, and we don't want to invalidate the view for those.*/
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                // No need to invalidate because we are not drawing anything.
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                // No need to invalidate because we are not drawing anything.
                break;
            default:
                // Do nothing.
        }
        return true;
    }
    private void touchStart(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    //Calculate the distance that has been moved (dx, dy).
    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            // Reset mX and mY to the last drawn point.
            mX = x;
            mY = y;
            // Save the path in the extra bitmap,
            // which we access through its canvas.
            mExtraCanvas.drawPath(mPath, mPaint);
        }
    }

    //Reset the path so it doesn't get drawn again when you draw more lines on the screen.
    private void touchUp() {
// Reset the path so it doesn't get drawn again.
        mPath.reset();
    }
}
