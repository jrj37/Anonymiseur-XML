package Graphics;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class LecteurAudio {

    public void LectureMusique(String cheminFichier) {
        try {
            // Création d'un objet AdvancedPlayer avec le chemin du fichier audio
            AdvancedPlayer lecteur = new AdvancedPlayer(new java.io.FileInputStream(cheminFichier));

            // Lecture de la musique
            lecteur.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
