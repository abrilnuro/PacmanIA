package com.aimewexample.pacman.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimewexample.pacman.MainActivity;
import com.aimewexample.pacman.R;
import com.aimewexample.pacman.models.Nodo;

import java.util.ArrayList;

/**
 * Created by aimew on 16/03/2017.
 */

public class PacmanGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Nodo> nodes;
    private ImageView imageItemPacman;
    private TextView textItemNodo;
    private int heightScreen;

    @Override
    public int getCount() {
        return nodes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //inflar el view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_grid_pacman, null);

        //asignar un color al view meta y al view destino
        if(i==0 || i ==39){
            view.setBackgroundColor(Color.parseColor("#ff33b5e5"));
        }

        //ajustar los view al tamaño de la pantalla
        view.setMinimumHeight(heightScreen/8);

        //referenciar elementos
        imageItemPacman = (ImageView) view.findViewById(R.id.image_item_pacman);
        textItemNodo = (TextView) view.findViewById(R.id.text_item_nodo);
        textItemNodo.setText(String.valueOf(nodes.get(i).getNumero()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).dialogPlayAgain();
            }
        });

        return view;
    }

    public PacmanGridAdapter(Context context, ArrayList<Nodo> nodes, int heightScreen) {
        this.context = context;
        this.nodes = nodes;
        this.heightScreen = heightScreen;
    }
}
