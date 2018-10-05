package com.example.m.kyodai;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Accueil extends Activity {
        Button accueil_apropos,accueil_load,accueil_play,accueil_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        accueil_exit=(Button) findViewById(R.id.accueil_exit);
        accueil_apropos=(Button) findViewById(R.id.accueil_apropos);
                accueil_play=(Button) findViewById(R.id.accueil_play);
        accueil_apropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_Accueil.this,Activity_Apropos.class);
                startActivity(intent);
            }
        });

        //Enregistrez l'écouteur onClick avec l'implémentation ci-dessus



        accueil_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_Accueil.this,MainActivity.class);
                startActivity(intent);
            }
        });
        accueil_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }

            }
        });


    }
}
