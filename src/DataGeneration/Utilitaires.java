package DataGeneration;

import java.util.Properties;
import java.util.Random;
public class Utilitaires {
    //generer un adresse de residence
    public  String genererAdresseResidence(Properties properties) {
        String nomsRues=properties.getProperty("liste.Rues");
        String[] ListenomsRues= nomsRues.split(",");
        Random rand = new Random();
        String numeroRue = String.valueOf(rand.nextInt(100) + 1);
        String nomRue = ListenomsRues[rand.nextInt(ListenomsRues.length)];
        return numeroRue + " " + nomRue;
    }
    //generer un adresse mail
    public String genererAdresseMail(Properties properties) {

        String noms=properties.getProperty("liste.nomsmail");
        String[] mots= noms.split(",");

        String domaines=properties.getProperty("liste.domaines");
        String[] ListeDomaines= domaines.split(",");

        Random rand = new Random();
        String nomUtilisateur = mots[rand.nextInt(mots.length)];
        String domaine = ListeDomaines[rand.nextInt(ListeDomaines.length)];
        return nomUtilisateur + "@" + domaine;
    }
    public String genererVille(Properties properties) {

        String villes=properties.getProperty("liste.Villes");
        String[] Listevilles= villes.split(",");

        Random rand = new Random();
        return Listevilles[rand.nextInt(Listevilles.length)];
    }
    // generer un nom d'entreprise
    public String genererEntreprise(Properties properties) {

        String company=properties.getProperty("liste.entreprises");
        String[] Listeentreprises= company.split(",");

        Random rand = new Random();
        return Listeentreprises[rand.nextInt(Listeentreprises.length)];
    }
    //generer un nom
    public String genererNom(Properties properties) {
        String Nom=properties.getProperty("liste.noms");
        String[] ListeNom= Nom.split(",");

        Random rand = new Random();

        return ListeNom[rand.nextInt(ListeNom.length)];
    }

    //generer un prenom
    public String genererPrenom(Properties properties) {

        String Nom=properties.getProperty("liste.prenoms");
        String[] ListePrenoms= Nom.split(",");

        Random rand = new Random();
        return ListePrenoms[rand.nextInt(ListePrenoms.length)];
    }

    //generer le code postale
    public String genererCodePostal() {

        Random rand = new Random();
    // generer un numero de departement entre 1 et 95
        int departmentCode = rand.nextInt(95) + 1;
        String formattedDepartmentCode = String.format("%02d", departmentCode); // Formats the department code with leading zeros if necessary
        StringBuilder codePostal = new StringBuilder(formattedDepartmentCode);
        for (int i = 0; i < 3; i++) {
            codePostal.append(rand.nextInt(10));
        }
        return codePostal.toString();
    }
}
