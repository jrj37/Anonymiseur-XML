package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Explain {
    public void Message(String message) {
        // Création d'une fenêtre
        JFrame frame = new JFrame("Application");
        // Création d'un label pour afficher le message de fin
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER); // Alignement horizontal du texte au centre

        // Définition de la taille de la police
        Font font = new Font("Arial", Font.BOLD, 20);
        label.setFont(font);

        // Ajout du label à la fenêtre avec un espace vide en bas
        frame.getContentPane().add(Box.createVerticalStrut(1000)); // Espacement vertical de 50 pixels
        frame.getContentPane().add(label, BorderLayout.CENTER);

        // Création du bouton "Continuer"
        JButton button = new JButton("Continuer");

        // Ajout d'un ActionListener pour gérer l'événement de clic du bouton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Supprimer la fenêtre lorsque le bouton est cliqué
                frame.dispose();
            }
        });

        // Création d'un panel pour centrer le bouton
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(button);

        // Ajout du panel à la fenêtre dans la région SOUTH
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        // Configuration de la fenêtre
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrage de la fenêtre sur l'écran
        frame.setVisible(true);
            while (frame.isVisible()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

}
