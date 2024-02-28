package Select;

import java.io.File;
import java.io.IOException;
public class MoveDirectory {
    public void deplacerDossier(File source, File destination) throws IOException {
        if (!source.isDirectory()) {
            throw new IllegalArgumentException("Le paramètre source doit être un dossier.");
        }

        if (!destination.isDirectory()) {
            // Créer le dossier de destination s'il n'existe pas
            if (!destination.exists()) {
                if (!destination.mkdirs()) {
                    throw new IOException("Impossible de créer le dossier de destination.");
                }
            } else {
                throw new IllegalArgumentException("Le paramètre destination doit être un dossier.");
            }
        }

        File nouveauDossier = new File(destination.getAbsolutePath() + File.separator + source.getName());

        if (!nouveauDossier.mkdir()) {
            throw new IOException("Impossible de créer le nouveau dossier.");
        }

        File[] fichiers = source.listFiles();

        if (fichiers != null) {
            for (File fichier : fichiers) {
                File nouveauFichier = new File(nouveauDossier.getAbsolutePath() + File.separator + fichier.getName());
                fichier.renameTo(nouveauFichier);
            }
        }

        if (!source.delete()) {
            throw new IOException("Impossible de supprimer le dossier source.");
        }
    }
    public void supprimerDossier(File dossier) {
        if (dossier.isDirectory()) {
            // Récupérer la liste des fichiers et dossiers dans le dossier
            File[] fichiers = dossier.listFiles();

            // Supprimer les fichiers et dossiers contenus dans le dossier récursivement
            for (File fichier : fichiers) {
                supprimerDossier(fichier);
            }
        }
        // Supprimer le dossier lui-même
        dossier.delete();
        }
}
