package Tests;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Select.ChoixDossier;
import Select.SelectModule;

public class Test {

public Test(File file, List<List<String>> BaliseTotal) throws ParserConfigurationException, IOException, SAXException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(file);

    for (int k=0;k< BaliseTotal.size();k++) {
        // On parcourt toutes les balises de la sécu sociale
        for (int j = 0; j < BaliseTotal.get(k).size(); j++) {
            NodeList Cartelist = document.getElementsByTagName(BaliseTotal.get(k).get(j));

            for (int i = 0; i < Cartelist.getLength(); i++) {
                //numero de sécurité sociale
                Node Numnode = Cartelist.item(i);
                if (Numnode.getNodeType() == Node.ELEMENT_NODE) {
                    Element NumElement = (Element) Numnode;
                    System.out.println(NumElement.getTextContent());
                }
            }
        }
}
    }
    public static List<List<String>> Balise(Properties properties) {

        //liste de balise
        String listeBaliseSecSoci = properties.getProperty("liste.baliseSecSoci");
        List<String> BaliseSecSoci= List.of(listeBaliseSecSoci.split(","));

        String listeBaliseNumPhone = properties.getProperty("liste.baliseNumTele");
        List<String> BaliseNumPhone= List.of(listeBaliseNumPhone.split(","));

        String listeBaliseRIB = properties.getProperty("liste.baliseRib");
        List<String> BaliseRIB= List.of(listeBaliseRIB.split(","));

        String listeBaliseBic=properties.getProperty("liste.baliseBic");
        List<String> BaliseBic= List.of(listeBaliseBic.split(","));

        String listeCodepos=properties.getProperty("liste.baliseCodepos");
        List<String> BaliseCodepos= List.of(listeCodepos.split(","));

        String listebaliseVille=properties.getProperty("liste.baliseVille");
        List<String> baliseVille= List.of(listebaliseVille.split(","));

        String listeBaliseEmail=properties.getProperty("liste.baliseEmail");
        List<String> BaliseEmail= List.of(listeBaliseEmail.split(","));

        String listeBaliseAdresse=properties.getProperty("liste.baliseAdresse");
        List<String> BaliseAdresse= List.of(listeBaliseAdresse.split(","));

        String listeBaliseNomPrenom=properties.getProperty("liste.baliseNomPrenom");
        List<String> BaliseNomPrenom= List.of(listeBaliseNomPrenom.split(","));

        String listeBaliseNom=properties.getProperty("liste.baliseNom");
        List<String> BaliseNom= List.of(listeBaliseNom.split(","));

        String listeBalisePrenom=properties.getProperty("liste.balisePrenom");
        List<String> BalisePrenom= List.of(listeBalisePrenom.split(","));

        List<List<String>> BaliseTotal = new ArrayList<>();

        // Construction de la baliseTotal
        BaliseTotal.add(BaliseSecSoci);
        BaliseTotal.add(BaliseNumPhone);
        BaliseTotal.add(BaliseRIB);
        BaliseTotal.add(BaliseCodepos);
        BaliseTotal.add(baliseVille);
        BaliseTotal.add(BaliseEmail);
        BaliseTotal.add(BaliseBic);
        BaliseTotal.add(BaliseAdresse);
        BaliseTotal.add(BaliseNomPrenom);
        BaliseTotal.add(BaliseNom);
        BaliseTotal.add(BalisePrenom);
        return BaliseTotal;
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        SelectModule selectModule=new SelectModule();
        selectModule.SelectModul();
        // Attendre que la fenêtre de sélection soit fermée
        while (selectModule.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Properties properties=new Properties();
        String ChooseModule=selectModule.getFileOutput();
        FileInputStream fileInputStream = new FileInputStream(ChooseModule);
        properties.load(fileInputStream);

        fileInputStream.close();
        ChoixDossier choixDossier=new ChoixDossier();
        File file = new File(choixDossier.Fichier());

        List<List<String>> BaliseTot= Balise(properties);

        new Test(file,BaliseTot);
    }
}
