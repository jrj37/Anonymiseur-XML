package DataGeneration;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
public class Banque {
    String[] TabBic={ "CCBPFRPPXXX","BNPAFRPPXXX","AGRIFRPPXXX","SOGEFRPPXXX","CEPAFRPPXXX", "CMCIFR2AXXX","CCFRFRPPXXX", "PSSTFRPPXXX","CMCIFR2AXXX","BLOPFRP1XXX"};
    private static final String AlphabetLettre = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private int randomindex;
    private String randomValueBanque;
    private String randomValueGuice;
    private int cle;
    private static final int LENGTH = 11;
    public String generateRandomLetters() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(AlphabetLettre.length());
            char randomLetter = AlphabetLettre.charAt(randomIndex);
            sb.append(randomLetter);
        }
        return sb.toString();
    }
    //generer un IBAN francais (FR suivie de 25 chiffres)
    public String genererIBAN(Properties properties) {
        // Générer un indice aléatoire
        Random random = new Random();

        String listeNumbanque=properties.getProperty("liste.banque");
        String[] Numbanque= listeNumbanque.split(",");

        String listeNumGuichet=properties.getProperty("liste.guichet");
        String[] NumGuichet= listeNumGuichet.split(",");

        StringBuilder iban = new StringBuilder("");
        StringBuilder ibanRes= new StringBuilder("FR");

        //Bug random
        int [] rd1= {random.nextInt(9),random.nextInt(9),random.nextInt(9),random.nextInt(9),random.nextInt(9),random.nextInt(9)};
        int [] rd2= {random.nextInt(9),random.nextInt(9),random.nextInt(9),random.nextInt(9),random.nextInt(9)};
        List<String> rd1String= new ArrayList<>();
        List<String> rd2String= new ArrayList<>();
        for (int i=0;i<rd1.length;i++){
            rd1String.add(Integer.toString(rd1[i]));
        }
        for (int i=0;i<rd2.length;i++){
            rd2String.add(Integer.toString(rd2[i]));
        }
        String randomNumaccountString1=rd1String.get(0);
        String randomNumaccountString2=rd2String.get(0);

        for(int i=1;i<rd1String.size();i++){
            randomNumaccountString1=randomNumaccountString1.concat(rd1String.get(i));
        }
        for(int i=1;i<rd2String.size();i++){
            randomNumaccountString2=randomNumaccountString2.concat(rd2String.get(i));
        }

        randomindex = random.nextInt(Numbanque.length);
        // Obtenir la valeur aléatoire du tableau
        randomValueBanque = Numbanque[randomindex];
        randomValueGuice= NumGuichet[randomindex];

        iban.append(randomValueBanque);
        iban.append(randomValueGuice);
        iban.append(randomNumaccountString1);
        iban.append(randomNumaccountString2);

        //calcul de la clé
        String ibanString= iban+"1527";
        BigInteger ibanLong = new BigInteger(ibanString);
        int modulo=97;
        BigInteger remainder =  ibanLong.mod(BigInteger.valueOf(97));
        cle= 98-remainder.intValue();
        if (0<=cle && cle<=9){
            ibanRes.append(0);
            ibanRes.append(cle);
        }
        else{ibanRes.append(cle);}

        ibanRes.append(randomValueBanque);
        ibanRes.append(randomValueGuice);
        ibanRes.append(randomNumaccountString1);
        ibanRes.append(randomNumaccountString2);

        return ibanRes.toString();
    }
    public String genererBic() {
        Random random=new Random();
        int Index=random.nextInt(TabBic.length);
        String randomValue = TabBic[Index];
        return randomValue;
    }

}

