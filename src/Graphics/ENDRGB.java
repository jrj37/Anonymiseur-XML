package Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

public class ENDRGB {

    public void affichage() {
        // Création d'une fenêtre
        JFrame frame = new JFrame("Anonymisation de données");

        // Création d'un label pour afficher le message avec des couleurs changeantes
        JLabel label = new JLabel("Les données sont anonymisées !");
        label.setHorizontalAlignment(JLabel.CENTER); // Alignement horizontal du texte au centre
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Définition de la police et de la taille du texte

        // Chargement de l'image et redimensionnement
        ImageIcon imageIcon = new ImageIcon("data/Anonymous_emblem.svg.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Création d'un label pour afficher l'image redimensionnée
        JLabel imageLabel = new JLabel(scaledIcon);

        // Ajout des labels à la fenêtre
        frame.getContentPane().add(label, BorderLayout.NORTH);
        frame.getContentPane().add(imageLabel, BorderLayout.CENTER);

        // Configuration de la fenêtre
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrage de la fenêtre sur l'écran
        frame.setVisible(true);

        // Création et démarrage du thread de mise à jour de couleur
        Thread colorThread = new Thread(() -> {
            try {
                while (true) {
                    // Rouge
                    label.setForeground(Color.RED);
                    Thread.sleep(500); // Attendre pendant 500 millisecondes

                    // Vert
                    label.setForeground(Color.GREEN);
                    Thread.sleep(500); // Attendre pendant 500 millisecondes

                    // Bleu
                    label.setForeground(Color.BLUE);
                    Thread.sleep(500); // Attendre pendant 500 millisecondes
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        colorThread.start(); // Démarrage du thread
    }
}
