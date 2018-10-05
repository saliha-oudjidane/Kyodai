package com.example.m.kyodai;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;


public class KyodaiView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    int etat = 0;
    int [] image1= {-1,-1,-1};
    boolean StartPart = false;
    TextView chrono;

    private Bitmap 		win;
    private Bitmap      selectpapillon;
    private Bitmap      papillon1;
    private Bitmap      papillon2;
    private Bitmap      papillon3;
    private Bitmap      papillon4;
    private Bitmap      papillon5;
    private Bitmap      papillon6;
    private Bitmap      papillon7;
    private Bitmap      papillon8;
    private Bitmap      papillon9;
    private Bitmap      papillon10;
    private Bitmap      papillon11;
    private Bitmap      papillon12;


	// Declaration des objets Ressources et Context permettant d'accéder aux ressources de notre application et de les charger
    private Resources 	mRes;    
    private Context 	mContext;

    // tableau modelisant la carte du jeu
    public int[][] carte;

    // ancres pour pouvoir centrer la carte du jeu
    int        carteTopAnchor;                   // coordonnées en Y du point d'ancrage de notre carte
    int        carteLeftAnchor;                  // coordonnées en X du point d'ancrage de notre carte

        private     boolean in      = true;
        private     Thread  mthread;
        SurfaceHolder holder;

        Paint paint;
        
    /**
     * The constructor called from the main JetBoy activity
     * 
     * @param context 
     * @param attrs 
     */
    public KyodaiView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // permet d'ecouter les surfaceChanged, surfaceCreated, surfaceDestroyed
    	holder = getHolder();
        holder.addCallback(this);
        mContext	= context;

        // chargement des images
        mRes 		= mContext.getResources();
        selectpapillon   = BitmapFactory.decodeResource(mRes, R.drawable.selectpapilllon);
        papillon1   = BitmapFactory.decodeResource(mRes, R.drawable.papillon1);
        papillon2	= BitmapFactory.decodeResource(mRes, R.drawable.papillon2);
        papillon3 	= BitmapFactory.decodeResource(mRes, R.drawable.papillon3);
        papillon4	= BitmapFactory.decodeResource(mRes, R.drawable.papillon4);
        papillon5	= BitmapFactory.decodeResource(mRes, R.drawable.papillon5);
        papillon6	= BitmapFactory.decodeResource(mRes, R.drawable.papillon6);
        papillon7	= BitmapFactory.decodeResource(mRes, R.drawable.papillon7);
        papillon8	= BitmapFactory.decodeResource(mRes, R.drawable.papillon8);
        papillon9	= BitmapFactory.decodeResource(mRes, R.drawable.papillon9);
        papillon10	= BitmapFactory.decodeResource(mRes, R.drawable.papillon10);
        papillon11	= BitmapFactory.decodeResource(mRes, R.drawable.papillon11);
        papillon12	= BitmapFactory.decodeResource(mRes, R.drawable.papillon12);

    	win 		= BitmapFactory.decodeResource(mRes, R.drawable.win);

        setCarte(new int[Grille.CarteHeight][Grille.CarteWidth]);
        loadleve();
    	// initialisation des parmametres du jeu
    	initparameters();
    	// creation du thread
        mthread   = new Thread(this);

        // prise de focus pour gestion des touches
        setFocusable(true);

    }

    private void loadleve() {

        setCarte(Grille.getRandomGrill());
    }

    // initialisation du jeu
    public void initparameters() {
    	paint = new Paint();
    	paint.setColor(0xff0000);
    	paint.setDither(true);
    	paint.setColor(0xFFFFFF00);
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeJoin(Paint.Join.ROUND);
    	paint.setStrokeCap(Paint.Cap.ROUND);
    	paint.setStrokeWidth(2);
    	paint.setTextAlign(Paint.Align.LEFT);
        carteTopAnchor  = (getHeight()- Grille.CarteHeight* Grille.CARTETILESIZE)/2;
        carteLeftAnchor = (getWidth()- Grille.CarteWidth* Grille.CARTETILESIZE)/2;
        if ((mthread!=null) && (!mthread.isAlive())) {
            if (mthread.getState() == Thread.State.NEW)
        	    mthread.start();
        }
    }

    // dessin du gagne si gagne
    private void paintwin(Canvas canvas) {
    	canvas.drawBitmap(win, carteLeftAnchor+ 1* Grille.CARTETILESIZE, carteTopAnchor+ 1* Grille.CARTETILESIZE, null);
    }    
    
    // dessin de la carte du jeu
    private void paintcarte(Canvas canvas) {
    	for (int i = 0; i< Grille.CarteHeight; i++) {
            for (int j = 0; j< Grille.CarteWidth; j++) {
                if(i==image1[0] && j==image1[1])
                    canvas.drawBitmap(selectpapillon, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                switch (getCarte()[i][j]) {
                    case Grille.CST_papillon1:
                    	canvas.drawBitmap(papillon1, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                    	break;
                    case Grille.CST_papillon2:
                    	canvas.drawBitmap(papillon2,carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon3:
                        canvas.drawBitmap(papillon3, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon4:
                        canvas.drawBitmap(papillon4, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon5:
                        canvas.drawBitmap(papillon5, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon6:
                        canvas.drawBitmap(papillon6, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon7:
                        canvas.drawBitmap(papillon7, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon8:
                        canvas.drawBitmap(papillon8, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon9:
                        canvas.drawBitmap(papillon9, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon10:
                        canvas.drawBitmap(papillon10, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon11:
                        canvas.drawBitmap(papillon11, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                    case Grille.CST_papillon12:
                        canvas.drawBitmap(papillon12, carteLeftAnchor+ j* Grille.CARTETILESIZE, carteTopAnchor+ i* Grille.CARTETILESIZE, null);
                        break;
                }
            }
        }
    }
    // Si tous les papillons sont disparus
    public boolean isWon() {

        return Grille.findall(getCarte());
    }
    
    // dessin du jeu
    private void nDraw(Canvas canvas) {
		canvas.drawRGB(0,0,0);
    	if (isWon()) {
           paintcarte(canvas);
           paintwin(canvas);
        } else {
           paintcarte(canvas);
        }
    }
    // callback sur le cycle de vie de la surfaceview
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	initparameters();
    }

    public void surfaceCreated(SurfaceHolder arg0) {
    }

    public void surfaceDestroyed(SurfaceHolder arg0) {     // quand on sort de l'activité principale et y retourne après la carte se charge à nouveau
    	in = false;
    }    

    /**
     * run (run du thread cr��)
     * on endort le thread, on modifie le compteur d'animation, on prend la main pour dessiner et on dessine puis on lib�re le canvas
     */
    public void run() {
    	Canvas c = null;
        while (in) {
            try {
                mthread.sleep(50);

                try {
                    c = holder.lockCanvas(null);
                    nDraw(c);
                } finally {
                	if (c != null) {
                		holder.unlockCanvasAndPost(c);
                    }
                }
            } catch(Exception e) {
            	Log.e("PBLM RUN", "Probleme dans run");
            }
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) { // pour recuperer et afficher les evenements tactils dans le logger
        Log.i("TAG", "onTouchEvent: "+event.getAction());
        AfficherInfoLog(event);
        return true;
    }

public void AfficherInfoLog(MotionEvent event) {
                Float leftclick = event.getX() - carteLeftAnchor;
                Float topclick = event.getY() - carteTopAnchor;
                Log.i("tag", "Message: "+carteLeftAnchor+" tr "+leftclick);

                if (leftclick > 0 && topclick > 0) {
                    int xx = (int) (leftclick / Grille.CARTETILESIZE);
                    int yy = (int) (topclick / Grille.CARTETILESIZE);

                    if (xx < Grille.CarteWidth && yy < Grille.CarteHeight) {

                        Log.i("tagxy", "MessageCoordonées: "+yy+"  coord "+xx);
                        if(carte[yy][xx]!=99)
                        switch (etat){
                            case 0:{
                                image1[0]=yy;
                                image1[1]=xx;
                                image1[2]=carte[yy][xx];

                                etat++;
                                break;
                            }
                            case 1:{

                                if(image1[0]!=yy || image1[1]!=xx)
                                {
                                    Log.i("tagyyxx", "Message: "+image1[2]+" fff "+carte[yy][xx]);
                                    if(image1[2]==carte[yy][xx]){
                                        carte[yy][xx]=99;
                                        carte[image1[0]][image1[1]]=99;
                                        image1[0]=99;
                                        image1[1]=99;
                                        image1[2]=99;

                                    }
                                    etat=0;
                                }
                                break;
                            }
                        }
                    } else
                        Log.i(" ", "Clic en dehors de la carte");
                } else {
                    Log.i(" ", "Clic en dehors de la carte");
                }

    }

    public int[][] getCarte() {
        return carte;
    }

    public void setCarte(int[][] carte) {
        this.carte = carte;
    }

    public void DecTimeer(){
        int chronometre = Integer.valueOf(chrono.getText().toString());
        chronometre--;
        chrono.setText(String.valueOf(chronometre));
    }

}
