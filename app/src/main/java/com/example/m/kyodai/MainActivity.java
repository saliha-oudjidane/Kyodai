package com.example.m.kyodai;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable{
    Boolean Mythread = true;
    MenuItem pause,save,sound;
    private KyodaiView mKyodai;
    TextView chrono;
    private     Thread chrono_thread;
    MediaPlayer music;

    @Override
   protected void onCreate(Bundle savedInstanceState) {   // Creation de l'activity Main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chrono=(TextView) this.findViewById(R.id.int_timer);
        chrono.setText("100");

        // recuperation de la vue une fois cree à partir de son id
        mKyodai = (KyodaiView)findViewById(R.id.KyodaiView);
        mKyodai.setVisibility(View.VISIBLE);
        int load = getIntent().getIntExtra("Chargement",0);
        music= MediaPlayer.create(MainActivity.this,R.raw.idir1);
        music.setLooping(true);
        music.start();

       if(load==1)
       {
          chrono.setText(String.valueOf(getIntent().getIntExtra("nbtimer",0)));
           chrono_thread.start();
       }
        mKyodai.initparameters();
        chrono_thread   = new Thread(this);
        if ((chrono_thread!=null) && (!chrono_thread.isAlive())) {

            chrono_thread.start();

        }

    }

    protected void onStop() {// couper la music quand on sort
        super.onStop();
        music.stop();
    }


    // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        pause = menu.findItem(R.id.menu_pause);
        sound = menu.findItem(R.id.menu_sound);
        sound.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // Creer le menu dans l'interface principale
        switch (item.getItemId()) {
             case R.id.menu_sound:
                if(music.isPlaying()){
                    music.pause();
                    sound.setIcon(R.drawable.couperson);
                }else {
                    music.start();
                    sound.setIcon(R.drawable.activerson);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void run() {
        while (Mythread){
            try {
                chrono_thread.sleep(1000);
                if(mKyodai.StartPart && !mKyodai.isWon()) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // DecTimeer();
                        }
                    });
                }
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }

}
