package Graphics;

import javax.swing.*;
import java.awt.*;

public class ProgressBar {
    private JProgressBar progressBar;
    private JLabel label;

    public ProgressBar() {
        // Création de la fenêtre
        JFrame frame = new JFrame("Barre de progression");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLayout(new BorderLayout());

        // Création de la barre de progression
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        frame.add(progressBar, BorderLayout.CENTER);

        // Création du label pour afficher le pourcentage
        label = new JLabel("0%");
        frame.add(label, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        frame.setVisible(true);
    }

    public void miseAJourBarreProgression(int valeur, int valeurMax) {
        double pourcentage = (valeur / (double) valeurMax) * 100;  // Calcul du pourcentage de progression
        int pourcentageArrondi = (int) Math.min(pourcentage, 100);  // Limite le pourcentage à 100 s'il dépasse

        // Mise à jour de la barre de progression et du label
        progressBar.setValue(pourcentageArrondi);
        label.setText(pourcentageArrondi + "%");
    }

}
