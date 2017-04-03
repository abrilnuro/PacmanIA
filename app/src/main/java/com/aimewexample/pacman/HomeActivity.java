package com.aimewexample.pacman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonHomeProfundidad;
    private Button buttonHomeAsterisco;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //referenciar elementos
        buttonHomeProfundidad = (Button) findViewById(R.id.button_home_profundidad);
        buttonHomeAsterisco = (Button)findViewById(R.id.button_home_asterisco);

        //asignar escuchadores
        buttonHomeProfundidad.setOnClickListener(this);
        buttonHomeAsterisco.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_home_profundidad:
                intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.putExtra("metodo",1);
                startActivity(intent);
                break;
            case R.id.button_home_asterisco:
                intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.putExtra("metodo",2);
                startActivity(intent);
                break;
        }
    }
}
