package com.example.m.kyodai;

import android.util.Log;

import java.util.Vector;

public class Grille {

    // taille de la carte
    static final int    CarteWidth    = 5;
    static final int    CarteHeight  = 8;
    static final int    CARTETILESIZE = 55;


   // constante modelisant les differentes types de cases
    static final int    CST_papillon1     = 0;
    static final int    CST_papillon2     = 1;
    static final int    CST_papillon3     = 2;
    static final int    CST_papillon4     = 3;
    static final int    CST_papillon5     = 4;
    static final int    CST_papillon6     = 5;
    static final int    CST_papillon7     = 6;
    static final int    CST_papillon8     = 7;
    static final int    CST_papillon9     = 8;
    static final int    CST_papillon10    = 9;
    static final int    CST_papillon11    = 10;
    static final int    CST_papillon12    = 11;

    // créer aleatoirement la grande grille
    static int[][] getRandomGrill()
    {
        int nbp1=8,nbp2=8, nbp3=8,nbp4=8, nbp5=8,nbp6=0, nbp7=0,nbp8=0, nbp9=0,nbp10=0, nbp11=0,nbp12=0,papillon=0;
        int [][] ref    = new int[CarteHeight][CarteWidth];
        Vector liste = new Vector();
        int y,x;
        for (int i=0 ; i<nbp1;i++){
            liste.add(new Integer(0));
        }
        for (int i=0 ; i<nbp2;i++){
            liste.add(new Integer(1));
        }
        for (int i=0 ; i<nbp3;i++){
            liste.add(new Integer(2));
        }
    for (int i=0 ; i<nbp4;i++){
            liste.add(new Integer(3));
        }
        for (int i=0 ; i<nbp5;i++){
            liste.add(new Integer(4));
        }
        for (int i=0 ; i<nbp6;i++){
            liste.add(new Integer(5));
        }
        for (int i=0 ; i<nbp7;i++){
            liste.add(new Integer(6));
        }
        for (int i=0 ; i<nbp8;i++){
            liste.add(new Integer(7));
        }
        for (int i=0 ; i<nbp9;i++){
            liste.add(new Integer(8));
        }
        for (int i=0 ; i<nbp10;i++){
            liste.add(new Integer(9));
        }
        for (int i=0 ; i<nbp11;i++){
            liste.add(new Integer(10));
        }
        for (int i=0 ; i<nbp12;i++){
            liste.add(new Integer(11));
        }

        Log.i("ssssbbb", "getRandomGrill: "+liste.size());

         int k =0;
        for (int i=0;i<CarteHeight;i++)
        {
            for (int j=0;j<CarteWidth;j++)
            {
                int n = (int)(Math.random()*((CarteHeight*CarteWidth)-k)+1)-1;

                Log.i("sssssaaa", "getRandomGrill: "+(((CarteHeight*CarteWidth)-k)+1));
                Log.i("sssss", "getRandomGrill: "+n);
                ref[i][j] = ((Integer) liste.elementAt(n)).intValue();
                liste.removeElementAt(n);
                k++;
            }
        }
        return ref;
    }

   public static boolean findall(int[][] carte) {    //verifie si tous les papillons sont disparus
        boolean all=true;
        for (int i=0;i<CarteHeight;i++) {
            for (int j = 0; j < CarteWidth; j++) {
                if(carte[i][j]!=99){
                    all=false;
                    break;
                }
            }
            if(!all)
                break;
        }
        return all;
    }
}
