package com.example.testapp;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Random;

public class HomePage extends Activity {
    Paint p = new Paint();
//Bitmap bmp=Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);

    int x = 0, y = 0, i = 1, j = 1, inc;
    int fps = 30;
    Random rnd = new Random();
    Handler h = new Handler();
    ImageView img;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            img.invalidate();
        }
    };

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setDither(false);
        p.setStrokeWidth(3);
        p.setAntiAlias(true);
        p.setColor(Color.parseColor("#CD5C5C"));
        p.setStrokeWidth(15);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);

        img = new ImageView(this) {
            @Override
            protected void onDraw(Canvas cnv) {
                x += i;
                y += j;
                if (x > 300) i = -1;
                if (y > 300) j = -1;
                if (x < 0) i = 1;
                if (y < 0) j = 1;

                cnv.drawPoint(x, y, p);
                h.postDelayed(r, fps);
            }
        };
//img.setImageBitmap(bmp);
        setContentView(img);
    }
}