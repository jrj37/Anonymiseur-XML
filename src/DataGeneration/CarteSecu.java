package DataGeneration;

import java.util.Random;

public class CarteSecu {
    private int genre;
    private int birthday;
    private int birthday1;
    private int birthday2;
    private String birthdayprint;
    private int mois;
    private String moisprint;
    private int mois1;
    private int mois2;
    private int departement1;
    private int departement2;
    private String departementprint;
    private int departement;
    private int commune;
    private String communeprint;
    private int commune1;
    private int commune2;
    private int commune3;
    private int ordre;
    private String ordreprint;
    private  int ordre1;
    private int ordre2;
    private int ordre3;
    private int somme;
    private int cle;
    private String cleprint;
    public double CalculCle(){
        double somme= Math.pow(genre,2)+Math.pow(birthday,2)+Math.pow(departement,2)+Math.pow(commune,2)+Math.pow(ordre,2);
        double division= somme%97;
        double calcul=97-division;
        return calcul;
    }
    public String IntTostring(int a,int b,int c){
        String strNumber1 = Integer.toString(a);
        String strNumber2 = Integer.toString(b);
        String strNumber3 = Integer.toString(c);
        String strNumber;
        if (c==-1 && b==-1){
            strNumber=strNumber1;
            return strNumber;
        }
        if (c==-1){
            strNumber=strNumber1+strNumber2;
        }
        else{ strNumber=strNumber1+strNumber2+strNumber3;
        }
        return strNumber;


    }
    public String RandomNumSecu(){
        //Genre
        Random random = new Random();
        genre = random.nextInt(2)+1;

        //mois de naissance
        mois1= random.nextInt(10);
        mois2= random.nextInt(10);
        if (mois1==0 && mois2==0){
            mois1= random.nextInt(10);
            mois2= random.nextInt(10);
        }
//Pour le calcul de la clé
        moisprint=IntTostring(mois1,mois2,-1);
       mois= Integer.valueOf( moisprint);

        //Année de naissance
        birthday1= random.nextInt(10);
        birthday2= random.nextInt(10);
        birthdayprint= IntTostring(birthday1,birthday2,-1);
        birthday=Integer.valueOf( birthdayprint);
        //département
        departement1=random.nextInt(10);
        departement2= random.nextInt(10);
        departementprint=IntTostring(departement1,departement2,-1);
        departement=Integer.valueOf(departementprint);
        // commune
        commune1= random.nextInt(10);
        commune2=random.nextInt(10);
        commune3=random.nextInt(10);
        communeprint=IntTostring(commune1,commune2,commune3);
        commune=Integer.valueOf(communeprint);
        //ordre de naissance
        ordre1=random.nextInt(9)+1;
        ordre2=random.nextInt(10);
        ordre3=random.nextInt(10);
        ordreprint=IntTostring(ordre1,ordre2,ordre3);
        ordre=Integer.valueOf(ordreprint);
        //Clé
        double cledouble=CalculCle();
        cle= (int)cledouble;
        cleprint=IntTostring(cle,-1,-1);
        if (0<=cle && cle<=9){
            return genre+birthdayprint+moisprint+ departementprint+communeprint+ordreprint+"0"+cleprint;
        }
        return genre+birthdayprint+moisprint+ departementprint+communeprint+ordreprint+cleprint;

    }
    }


