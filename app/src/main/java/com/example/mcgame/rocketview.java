package com.example.mcgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class rocketview extends View
{
    private Bitmap rocket[] = new Bitmap[2];
    private int rocketx = 10;
    private int rockety;
    private int rocketspeed;

    private int canvaswidth,canvashight;

    private int yellowx,yellowy,yellowspeed=16;
    private Paint yellowpaint = new Paint();

    private int greenx,greeny,greenspeed=20;
    private Paint greenpaint=new Paint();

    private int redx,redy,redspeed=25;
    private Paint redpaint=new Paint();

    private int score,lifecounter;


    private boolean touch = false;

    private Bitmap backgroundimage;
    private Paint scorepaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public rocketview(Context context) {
        super(context);
        rocket[0]= BitmapFactory.decodeResource(getResources(),R.drawable.rocket);
        rocket[1]=BitmapFactory.decodeResource(getResources(),R.drawable.rocket2);
        backgroundimage = BitmapFactory.decodeResource(getResources(),R.drawable.darksky);
        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);

        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);

        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(60);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.brokenheart);

        rockety=550;
        score=0;
        lifecounter=3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvaswidth = canvas.getWidth();
        canvashight = canvas.getHeight();

        canvas.drawBitmap(backgroundimage, 0, 0, null);
        int minrockety = rocket[0].getHeight();
        int maxrockety = canvashight - rocket[0].getHeight() * 1;
        rockety = rockety + rocketspeed;

        if(rockety<minrockety)
        {
            rockety=minrockety;
        }
        if(rockety>maxrockety)
        {
            rockety=maxrockety;
        }
        rocketspeed=rocketspeed + 2;


        if(touch)
        {
            canvas.drawBitmap(rocket[1], rocketx, rockety,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(rocket[0],rocketx,rockety,null);
        }





        yellowx = yellowx - yellowspeed;

        if(hitballchecker(yellowx,yellowy))
        {
            score = score + 10;
            yellowx = -100;
        }
        if(yellowx < 0)
        {
            yellowx=canvaswidth + 21;
            yellowy=(int) Math.floor(Math.random() * (maxrockety - minrockety)) + minrockety;
        }
        canvas.drawCircle(yellowx,yellowy,25,yellowpaint);

        greenx = greenx - greenspeed;

        if(hitballchecker(greenx,greeny))
        {
            score = score + 20;
            greenx = -100;
        }
        if(greenx<0)
        {
            greenx=canvaswidth+21;
            greeny=(int) Math.floor(Math.random() * (maxrockety - minrockety)) + minrockety;
        }
        canvas.drawCircle(greenx, greeny,20, greenpaint);

        redx = redx - redspeed;

        if(hitballchecker(redx,redy))
        {
            redx =-100;
            lifecounter--;

            if(lifecounter == 0)
            {
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();

                Intent gameO = new Intent(getContext(), endactivity.class);
                gameO.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameO.putExtra("Score", score);
                getContext().startActivity(gameO);
            }
        }
        if(redx<0)
        {
            redx=canvaswidth+21;
            redy=(int) Math.floor(Math.random() * (maxrockety - minrockety)) + minrockety;
        }
        canvas.drawCircle(redx, redy,15, redpaint);

        canvas.drawText("Score : " +score, 20, 60,scorepaint);


        for(int i=0;i<3;i++)
        {
            int x = (int) (450 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if(i < lifecounter)
            {
                canvas.drawBitmap(life[0], x, y, null);
            }
            else
            {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }


    }



    public boolean hitballchecker(int x,int y)
    {
        if(rocketx < x && x <(rocketx+rocket[0].getWidth()) && rockety < y && y < (rockety+rocket[0].getHeight()))
        {
            return true;

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch=true;
            rocketspeed=-22;

        }
        return true;
    }
}
