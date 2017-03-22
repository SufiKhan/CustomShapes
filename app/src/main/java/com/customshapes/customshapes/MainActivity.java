package com.customshapes.customshapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DemoView demoview;
    private int angleStartPoint = 150;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demoview = new DemoView(this);
        setContentView(demoview);
    }

    private class DemoView extends View{
        public DemoView(Context context){
            super(context);
        }

        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // custom drawing code here
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
//            drawPointedPath(canvas, paint, 0, height/2, width);
            drawPathWithCurve(canvas, paint, 0, height/2, width);
        }
        public void drawPointedPath(Canvas canvas, Paint paint, int x, int y, int width) {
            Path path = new Path();
            path.moveTo(x, 0); // Top --- start x and y from 0
            path.lineTo(width, 0); // Right --- draw a line on x(horizontally) upto Width and  vertically line should move upto  i.e 0
            path.lineTo(width, y-angleStartPoint); // Bottom ---- draw a line on y(Vertically) upto y-50 and horizontally line should move upto width
            path.lineTo(width/2, y); // Left --- draw a line on x(horizontally) upto Width/2 i.e center of screen and  vertically line should move upto  i.e y
            path.lineTo(x,y-angleStartPoint); // Back to Top --- draw a line on y(Vertically) upto y-50 and horizontally line should move upto x
            path.close();
            canvas.drawPath(path, paint);
        }
        public void drawPathWithCurve(Canvas canvas, Paint paint, int x, int y, int width) {
            int partOneHeight = y/3;
            int partOneWidth = width/3;
            Path path = new Path();
            path.moveTo(width, 0); // Top --- start x and y from 0
            path.lineTo(x, 0); // Right --- draw a line on x(horizontally) upto Width and  vertically line should move upto  i.e 0
            path.lineTo(x, y-angleStartPoint); // Bottom ---- draw a line on y(Vertically) upto y-50 and horizontally line should move upto width
            path.lineTo(x+angleStartPoint,y); // Left --- draw a line on x(horizontally) upto Width/2 i.e center of screen and  vertically line should move upto  i.e y
            final float x2 = (partOneWidth*2 + partOneWidth) / 2;
            final float y2 = (y + partOneHeight) / 2;
            path.quadTo(x2, y2, width-angleStartPoint, y);
            path.lineTo(width,y-angleStartPoint); // Back to Top --- draw a line on y(Vertically) upto y-50 and horizontally line should move upto x
            path.close();
            canvas.drawPath(path, paint);
        }
    }
}
