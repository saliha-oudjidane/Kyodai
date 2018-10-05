package com.example.m.kyodai;

import android.util.Log;

import java.util.Vector;

public class Matrice {

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
    
    static  int[][] dupCarte(int[][] carte)// duplicata de la carte
    {
        int [][] tmpcarte=new int[CarteHeight][CarteWidth];
        for(int i=0;i<CarteHeight;i++)
        {
            for (int j=0;j<CarteWidth;j++)
            {
                tmpcarte[i][j]=carte[i][j];
            }
        }
        return tmpcarte;
    }
  /*  static  int[][] depColTop(int[][]carte,int colone)
    {
        int [][] tmpcarte=dupCarte(carte);
        for (int i=0;i<CarteHeight;i++) {
            if(i==0)
                carte[CarteHeight-1][colone]=tmpcarte[i][colone];
            else {
                    carte[i-1][colone]=tmpcarte[i][colone];
            }
        }
        return carte;
    }
    static  int[][] depColDown(int[][]carte,int colone)
    {
        int [][] tmpcarte=dupCarte(carte);
        for (int i=0;i<CarteHeight;i++) {
            if(i==0)
                carte[i][colone]=tmpcarte[CarteHeight-1][colone];
            else
                carte[i][colone]=tmpcarte[i-1][colone];
        }
        return carte;
    }
 static  int[][] depLineLeft(int[][]carte,int line)
    {
        int [][] tmpcarte=dupCarte(carte);
        for (int i=0;i<CarteWidth;i++) {
            if(i==0)
                carte[line][CarteWidth-1]=tmpcarte[line][i];
            else {
                carte[line][i-1]=tmpcarte[line][i];
            }
        }
        return carte;
    }

    static  int[][] depLineRight(int[][]carte,int line)
    {
        
        int [][] tmpcarte=dupCarte(carte);
        for (int i=0;i<CarteWidth;i++) {
            if(i==0)
                carte[line][i]=tmpcarte[line][CarteWidth-1];
            else
                carte[line][i]=tmpcarte[line][i-1];

        }
        return carte;
    }*/

    static String ArrayToString(int[][] table)
    {
        int ii =table.length;
        Log.i("iiii", "ArrayToString: "+ii);
        int jj=table[0].length;
        String s="";
        for (int i=0;i<ii;i++)
        {
            for(int j=0;j<jj;j++){
                s=s+String.valueOf(table[i][j]);
                if (j<jj-1)
                    s=s+",";
            }
            if (i<ii-1)
                s=s+";";
        }
        return s;
    }

    static int[][] StringToArray(String s)
    {
        String[] ligne=s.split(";");
        int ii=ligne.length;
        int[][] table=new int[ii][ii];
        for (int i=0;i<ii;i++)
        {
            String[] cell=ligne[i].split(",");
            for(int j=0;j<ii;j++)
                table[i][j]=Integer.valueOf(cell[j]);
        }
        return table;

    }

    public static boolean findall(int[][] carte) {
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
