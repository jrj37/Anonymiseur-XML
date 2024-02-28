package src.Start;

import CompressionTools.DataCompression;
import CompressionTools.DataExtraction;
import Select.*;
import Graphics.*;
import Treatment.Parcours;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

public class Initialisation {
    //Data
    private MoveDirectory moveDirectory= new MoveDirectory();
    private LittleFunction littleFunction=new LittleFunction();
    private Explain messageFichier= new Explain();
    private ChoixDossier choixDossier=new ChoixDossier();
    private Properties properties = new Properties();
    private Parcours parcours = new Parcours();
    private DataExtraction dataExtraction=new DataExtraction();
    private SelectModule  ChooseProperties = new  SelectModule();
    private DataCompression dataCompression=new DataCompression();
    private String[] tab;
    private String[] tabZIP;
    private String folderPath;
    private String folderPathOutput;
    private String selectedFile;
    private File folderTMP;
    private String cheminChanson = "data/ascenseur.mp3";
    private LecteurAudio lecteurAudio= new LecteurAudio();
    boolean reloadMusique= false;
    public Initialisation(){

        try {
            messageFichier.Message("Choississez un fichier .properties");
            ChooseProperties.SelectModul();
            // Attendre que la fenêtre de sélection soit fermée
            while (ChooseProperties.isVisible()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Récupérer le fichier sélectionné
            selectedFile = ChooseProperties.getFileOutput();

            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Choix des dossiers
        messageFichier.Message("Choisissez un dossier avec vos data");
        folderPath = choixDossier.Dossier();
        messageFichier.Message("Choisissez un dossier pour récupérer vos data");
        folderPathOutput = choixDossier.Dossier();
        folderTMP=choixDossier.CreateDirectory(folderPath);
    }
    public void Start()throws IOException{
        //Parcours des fichiers TGZ et ZIP
        tab=dataExtraction.ParcoursDossierTGZ(folderPath);
        tabZIP=dataExtraction.ParcoursDossierZIP(folderPath);

        //Gif gifViewer = new Gif();
        //gifViewer.initialize(); //Si on veut ajouter un git pendant l'attente

        if (selectedFile.equals("FileProperties/DKPIE_A.properties")){
            StartTGZ();
        }
        else{
            StartZIP();
        }
        moveDirectory.supprimerDossier(folderTMP);
    }

    public void StartZIP(){
        //Musique();
        for (String FichierZIP : tabZIP) {
            System.out.println(FichierZIP);
            try{
                dataExtraction.unzipFile(FichierZIP,folderTMP.getPath());
                StartAlgorithme();
                File[] files=parcours.ParcoursFile(folderTMP.getPath());
                dataCompression.zipFiles(files,FichierZIP,folderPathOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reloadMusique=true;
    }
    public void StartTGZ() throws IOException {
        // Extraction des fichiers
        //Musique(); Si on veut mettre de la musique
        for (String FichierTGZ : tab) {
            dataExtraction.extractFilesFromTGZ(FichierTGZ, folderTMP.getPath());
            StartAlgorithme();
            ZIPtgzDirectory(FichierTGZ);
        }
        reloadMusique=true;
    }

    public void StartAlgorithme(){
        //Calcul du nombre de fichiers à traiter
        int TotalXML=parcours.FindXMLandCount(folderTMP.getPath());
        //Barre de Progression et lancement de l'algorithme
        ProgressBar progressBar=new ProgressBar();
        parcours.ParcoursAndTreatment(folderTMP.getPath(),properties,progressBar,TotalXML);
    }
    public void ZIPtgzDirectory(String FichierTGZ) throws IOException {
        File[] files=parcours.ParcoursFile(folderTMP.getPath());

        File file= new File(files[0].getAbsolutePath()+"1.0");

        moveDirectory.deplacerDossier(files[0],file);
        dataCompression.compressDirectory(file.getAbsolutePath(),littleFunction.getLastFolder(FichierTGZ),folderPathOutput);
        moveDirectory.supprimerDossier(file);
    }
    public void Musique() {
        Thread lecteurThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (reloadMusique==false){
                    lecteurAudio.LectureMusique(cheminChanson);
                }
            }
        });
        lecteurThread.start();
    }
}


