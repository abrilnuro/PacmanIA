package com.aimewexample.pacman;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;

import com.aimewexample.pacman.adapters.PacmanGridAdapter;
import com.aimewexample.pacman.models.Nodo;
import com.aimewexample.pacman.utils.HandlerGrid;
import com.aimewexample.pacman.utils.Profundidad;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Nodo> nodes;
    private GridView gridView;
    private PacmanGridAdapter adapter;
    private HandlerGrid handlerGrid;
    public List<Nodo> recorridoFinalPacman = new ArrayList<Nodo>();

    private Dialog dialogStartGame;
    private Dialog dialogPlayAgain;
    private Button buttonDialogPlayAgain;
    private Button buttonDialogStartGame;
    private Button buttonDialogBack;
    int metodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideElements();
        setContentView(R.layout.activity_main);

        //cachar el intent y ejecutar el metodo seleccionado
        Profundidad.crearArbolProfundidad(); //CREANDO ARBOL DE 64 NODOS (cambiar en este metodo)
        Intent intent = getIntent();
        metodo = intent.getIntExtra("metodo", 0);

        //mostrar cuadro de opciones
        dialogStartGame();

        //inicialzar lista
        initArray();

        //configurar gridView
        gridView = (GridView)findViewById(R.id.grid_view);
        adapter = new PacmanGridAdapter(this, nodes, getScreenSize());
        gridView.setAdapter(adapter);

        //desactivar el scroll del gridView
        gridView.setOnTouchListener(new android.view.View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_dialog_start_game:
                dialogStartGame.dismiss();
                ejecutarMetodo();
                break;
            case R.id.button_dialog_play_again:
                dialogPlayAgain.dismiss();
                ejecutarMetodo();
                break;
            case R.id.button_dialog_back:
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void ejecutarMetodo(){
        if(metodo == 1){
            recorridoFinalPacman = Profundidad.mostrarRecorridoFinal();  //Metodo para profundiad
            handlerGrid = new HandlerGrid(this, gridView, (ArrayList<Nodo>) recorridoFinalPacman);
            handlerGrid.start();
        }else {
            recorridoFinalPacman = Profundidad.mostrarRecorridoFinalAestrella();  //Metodo para A*
            handlerGrid = new HandlerGrid(this, gridView, (ArrayList<Nodo>) recorridoFinalPacman);
            handlerGrid.start();
        }
    }

    //metodo que calcula el tamaño de la pantalla del dispositivo
    public int getScreenSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return height;
    }

    public void dialogPlayAgain(){
        dialogPlayAgain = new Dialog(this);
        dialogPlayAgain.setContentView(R.layout.dialog_play_again);
        dialogPlayAgain.setTitle("GAME OVER");
        dialogPlayAgain.setCanceledOnTouchOutside(false);

        //referenciar elementos
        buttonDialogPlayAgain = (Button)dialogPlayAgain.findViewById(R.id.button_dialog_play_again);
        buttonDialogBack = (Button)dialogPlayAgain.findViewById(R.id.button_dialog_back);
        buttonDialogBack.setOnClickListener(this);
        buttonDialogPlayAgain.setOnClickListener(this);

        dialogPlayAgain.show();
    }

    public void dialogStartGame(){
        dialogStartGame = new Dialog(this);
        dialogStartGame.setContentView(R.layout.dialog_start_game);
        dialogStartGame.setTitle("Presiona para comenzar");
        dialogStartGame.setCanceledOnTouchOutside(false);

        //referenciar elementos
        buttonDialogStartGame = (Button)dialogStartGame.findViewById(R.id.button_dialog_start_game);
        buttonDialogStartGame.setOnClickListener(this);

        dialogStartGame.show();
    }

    //metodo que oculta la statusBar y navigationBar
    public void hideElements() {
        if(Build.VERSION.SDK_INT < 19){
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(android.view.View.GONE);
        } else {
            //for higher api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    public void initArray(){
        nodes = new ArrayList();
        for (int i = 1; i <65 ; i++) {
            nodes.add(new Nodo(i));
        }
    }
}
