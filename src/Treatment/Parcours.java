package Treatment;

import Select.DKPIEorDKA;
import Select.LittleFunction;
import Graphics.ProgressBar;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import java.util.Properties;
public class Parcours {
    private int counter=0;
    private int counterXML=0;
    private DKPIEorDKA dkpiEorDKA= new DKPIEorDKA();
    private LittleFunction littleFunction= new LittleFunction();
    public void ParcoursAndTreatment(String folderPath, Properties properties, ProgressBar progressBar,int TotalXML) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        XMLTreatment xml =new XMLTreatment();

        if (files != null) {
            for (File file : files) {
                if ( isXML(file)==true) {
                    System.out.println("il y a un fichier xml");
                    try {
                        // Charger le fichier XML d'entrée
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(file);

                            if (littleFunction.extract(file.getName()).equals("DSI")){
                                dkpiEorDKA.GenereSecu();
                                dkpiEorDKA.ParseXSI(document, dkpiEorDKA.getSecu());
                            }
                            else{
                                dkpiEorDKA.ParseXSI(document,dkpiEorDKA.getSecu());
                            }

                        // Effectuer le traitement sur le document XML
                        xml.XMLedit(document,properties);

                        // Générer le fichier XML de sortie
                        xml.saveXMLToFile(document,file);

                        System.out.println("Le traitement du fichier XML est terminé.");

                        counter++;
                        progressBar.miseAJourBarreProgression(counter,TotalXML);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {
                    // Récursivement, appelez la fonction pour traiter les fichiers du sous-dossier
                    ParcoursAndTreatment(file.getAbsolutePath(),properties,progressBar,TotalXML);
                }
            }
        }
    }
    public boolean isXML(File file) {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension.equalsIgnoreCase("xml");
    }
    public int FindXMLandCount(String folderPath){

        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if ( isXML(file)==true) {
                    counterXML++;
                }else if (file.isDirectory()) {
                    // Récursivement, appelez la fonction pour traiter les fichiers du sous-dossier
                    FindXMLandCount(file.getAbsolutePath());
                }
            }
        }
        return counterXML;
    }
    public File[] ParcoursFile(String folderPath){
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            System.out.println("Liste des fichiers dans le dossier :");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Le dossier est vide ou n'existe pas.");
        }
        return files;
    }
}
