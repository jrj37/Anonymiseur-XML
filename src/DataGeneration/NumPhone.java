package DataGeneration;

import java.util.Random;

public class NumPhone {
    private int[] tabNumero = new int[10];
    private String strNumber1;
    public String generateNum() {
        String numero="";
        Random random = new Random();
        tabNumero[0] = 0;
        tabNumero[1] = random.nextInt(9)+1;
        for (int i = 2; i < 10; i++) {
            tabNumero[i] = random.nextInt(10);
        }
        for (int i=0;i<tabNumero.length;i++){
            strNumber1 = Integer.toString(tabNumero[i]);
            if (i%2!=0)
            { numero+=strNumber1;}
            else{
                if (i==0){
                    numero+=strNumber1;
                }
                else{
                numero+=" ";
                numero+=strNumber1;}
            }
        }
      return numero;
    }

}
