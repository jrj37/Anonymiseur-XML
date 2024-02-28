package src.Select;

import javax.swing.*;

import java.io.File;

import static java.lang.System.exit;
public class ChoixDossier {
    private String cheminDossier="";
    private String cheminFichier="";
    public String Dossier() {
        // Création d'une boîte de dialogue pour sélectionner un dossier
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Affichage de la boîte de dialogue
        int result = fileChooser.showOpenDialog(null);

        // Vérification de la sélection d'un dossier
        if (result == JFileChooser.APPROVE_OPTION) {
            // Récupération du dossier sélectionné
            cheminDossier = fileChooser.getSelectedFile().getPath();
            return cheminDossier;
        } else {
            exit(1);
        }
        return cheminDossier;
    }
    public File CreateDirectory(String folderPath){
        File folderTMP = new File(folderPath+"/"+"TMP/");
        if (!folderTMP.exists()) { // Vérifiez si le dossier n'existe pas déjà
            boolean created = folderTMP.mkdir(); // Créez le dossier

            if (created) {
                System.out.println("Le dossier a été créé avec succès !");
            } else {
                System.out.println("Échec de la création du dossier.");
            }
        } else {
            System.out.println("Le dossier existe déjà.");
        }
        return folderTMP;
    }
    public String Fichier() {
        // Création d'une boîte de dialogue pour sélectionner un dossier
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Affichage de la boîte de dialogue
        int result = fileChooser.showOpenDialog(null);

        // Vérification de la sélection d'un dossier
        if (result == JFileChooser.APPROVE_OPTION) {
            // Récupération du dossier sélectionné
            cheminFichier = fileChooser.getSelectedFile().getPath();
            return cheminFichier;
        } else {
            exit(1);
        }
        return cheminFichier;
    }
}
