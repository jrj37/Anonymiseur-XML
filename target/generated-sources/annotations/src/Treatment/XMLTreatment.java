package src.Treatment;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import DataGeneration.Banque;
import DataGeneration.CarteSecu;
import DataGeneration.NumPhone;
import DataGeneration.Utilitaires;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class XMLTreatment {
    private String listeBaliseSecSoci ="";
    private List<String> BaliseSecSoci;
    private String listeBaliseNumPhone="";
    private List<String> BaliseNumPhone;
    private String listeBaliseRIB="";
    private List<String>BaliseRIB;
    private String value="";
    private String listeBaliseAdresse="";
    private List<String> BaliseAdresse;
    private String listeBaliseBic="";
    private List<String> BaliseBic;
    private String listeCodepos="";
    private List<String> BaliseCodepos;
    private String listebaliseVille;
    private List<String> baliseVille;
    private String listeBaliseEmail;
    private List<String> BaliseEmail;
    private String listeBaliseNomPrenom;
    private List<String> BaliseNomPrenom;
    private String listeBaliseNom;
    private String listeBalisePrenom;
    private List<String> BaliseNom;
    private List<String > BalisePrenom;
    public void XMLedit(Document document,Properties properties) {

        //liste de balise
        listeBaliseSecSoci = properties.getProperty("liste.baliseSecSoci");
        BaliseSecSoci= List.of(listeBaliseSecSoci.split(","));

        listeBaliseNumPhone = properties.getProperty("liste.baliseNumTele");
        BaliseNumPhone= List.of(listeBaliseNumPhone.split(","));

        listeBaliseRIB = properties.getProperty("liste.baliseRib");
        BaliseRIB= List.of(listeBaliseRIB.split(","));

        listeBaliseBic=properties.getProperty("liste.baliseBic");
        BaliseBic= List.of(listeBaliseBic.split(","));

        listeCodepos=properties.getProperty("liste.baliseCodepos");
        BaliseCodepos= List.of(listeCodepos.split(","));

        listebaliseVille=properties.getProperty("liste.baliseVille");
        baliseVille= List.of(listebaliseVille.split(","));

        listeBaliseEmail=properties.getProperty("liste.baliseEmail");
        BaliseEmail= List.of(listeBaliseEmail.split(","));

        listeBaliseAdresse=properties.getProperty("liste.baliseAdresse");
        BaliseAdresse= List.of(listeBaliseAdresse.split(","));

        listeBaliseNomPrenom=properties.getProperty("liste.baliseNomPrenom");
        BaliseNomPrenom= List.of(listeBaliseNomPrenom.split(","));

        listeBaliseNom=properties.getProperty("liste.baliseNom");
        BaliseNom= List.of(listeBaliseNom.split(","));

        listeBalisePrenom=properties.getProperty("liste.balisePrenom");
        BalisePrenom= List.of(listeBalisePrenom.split(","));

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

        HelpProcess2(document,BaliseTotal,properties);
    }
    public void HelpProcess2(Document document,List<List<String>> BaliseTotal,Properties properties){
        // Data random
        CarteSecu Carte=new CarteSecu();
        NumPhone Phone =new NumPhone();
        Banque banque = new Banque();
        Utilitaires data= new Utilitaires();

        String value="";
        for (int k=0;k< BaliseTotal.size();k++) {
            // On parcourt toutes les balises de la sécu sociale
            for (int j = 0; j < BaliseTotal.get(k).size(); j++) {
                NodeList Cartelist = document.getElementsByTagName(BaliseTotal.get(k).get(j));

                for (int i = 0; i < Cartelist.getLength(); i++) {
                    //numero de sécurité sociale
                    Node Numnode = Cartelist.item(i);
                    if (Numnode.getNodeType() == Node.ELEMENT_NODE) {
                        Element NumElement = (Element) Numnode;
                        NumElement.setTextContent(value);

                        switch (k) {
                            case 0:
                                NumElement.setTextContent(Carte.RandomNumSecu());
                                break;
                            case 1:
                                NumElement.setTextContent(Phone.generateNum());
                                break;
                            case 2:
                                NumElement.setTextContent(banque.genererIBAN(properties));
                                break;
                            case 3:
                                NumElement.setTextContent(data.genererCodePostal());
                                break;
                            case 4:
                                NumElement.setTextContent(data.genererVille(properties));
                                break;
                            case 5:
                                NumElement.setTextContent(data.genererAdresseMail(properties));
                                break;
                            case 6:
                                NumElement.setTextContent(banque.genererBic());
                                break;
                            case 7:
                                NumElement.setTextContent(data.genererAdresseResidence(properties));
                                break;
                            case 8:
                                NumElement.setTextContent(data.genererNom(properties)+" "+data.genererPrenom(properties));
                                break;
                            case 9:
                                NumElement.setTextContent(data.genererNom(properties));
                                break;
                            case 10:
                                NumElement.setTextContent(data.genererPrenom(properties));
                                break;
                            default:
                                return;
                        }


                    }
                }
            }
        }
    }
    public void generateOutputXML(Document document, String outputFile)
            throws TransformerException, ParserConfigurationException {
        // Configurer le transformateur pour générer le fichier XML de sortie
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(outputFile);

        // Générer le fichier XML de sortie
        transformer.transform(source, result);
    }
    public void saveXMLToFile(Document document, File file) {
        try {
            // Créer un objet TransformerFactory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // Créer un objet Transformer
            Transformer transformer = transformerFactory.newTransformer();

            // Créer une source DOM pour le document XML
            DOMSource source = new DOMSource(document);

            // Créer un objet StreamResult pour le fichier de sortie
            StreamResult result = new StreamResult(file);

            // Enregistrer les modifications dans le fichier XML
            transformer.transform(source, result);

            System.out.println("Le fichier XML a été enregistré avec les modifications.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



