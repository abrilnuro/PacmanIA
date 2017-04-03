package com.aimewexample.pacman.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.aimewexample.pacman.MainActivity;
import com.aimewexample.pacman.R;
import com.aimewexample.pacman.models.Nodo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aimew on 19/03/2017.
 */

public class HandlerGrid {

    private boolean started = false;
    private Handler handler = new Handler();
    private int position = 0;

    private GridView gridView;
    private ArrayList<Nodo> nodes;
    private Context context;
    private View rowView;

    public HandlerGrid(Context context, GridView gridView, ArrayList<Nodo> nodes) {
        this.context = context;
        this.gridView = gridView;
        this.nodes = nodes;
    }

    public void initHandler(){
        start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rowView = gridView.getChildAt(nodes.get(position).getNumero());
            final ImageView image = (ImageView) rowView.findViewById(R.id.image_item_pacman);

            image.animate().alpha(1.0f);
            image.postDelayed(new Runnable() {
                public void run() {
                    image.animate().alpha(0f);
                }
            }, 600);

            if(started) {
                position = position + 1;
                start();
            }
            if (position == nodes.size()){
                stop();
            }
        }
    };

    public void start() {
        started = true;
        handler.postDelayed(runnable, 800);
    }

    public void stop() {
        started = false;
        handler.removeCallbacks(runnable);
        showDialog();
    }

    public void showDialog(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)context).dialogPlayAgain();
            }
        }, 1000);
    }
}
