package src.Select;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectModule extends JFrame {
    private JComboBox<String> fileComboBox;
    private String fileOutput="";
    public void SelectModul() {
        setTitle("Sélection de fichier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création de la liste des fichiers
        String[] files = {"FileProperties/DKPIE_A.properties", "FileProperties/DK_A.properties"};
        fileComboBox = new JComboBox<>(files);

        // Création du bouton de sélection
        JButton selectButton = new JButton("Sélectionner");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOutput=(String)fileComboBox.getSelectedItem();
                System.out.println(fileOutput);
                dispose();
            }
        });

        // Ajout des composants à la fenêtre
        setLayout(new FlowLayout());
        add(fileComboBox);
        add(selectButton);

        pack();
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }

    public String getFileOutput() {
        return fileOutput;
    }
}
