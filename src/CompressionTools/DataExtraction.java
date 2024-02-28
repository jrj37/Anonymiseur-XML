package CompressionTools;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DataExtraction {
    File outputFile;
    public String extractFilesFromTGZ(String tgzFilePath, String outputDirectory) {
        try {
            File tgzFile = new File(tgzFilePath);
            FileInputStream fis = new FileInputStream(tgzFile);
            GzipCompressorInputStream gzis = new GzipCompressorInputStream(fis);
            TarArchiveInputStream taris = new TarArchiveInputStream(gzis);

            TarArchiveEntry entry;
            while ((entry = taris.getNextTarEntry()) != null) {
                if (!entry.isDirectory()) {
                    outputFile = new File(outputDirectory, entry.getName());
                    createParentDirectories(outputFile);
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = taris.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
            }

            taris.close();
            gzis.close();
            fis.close();

            System.out.println("Extraction complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile.toString();
    }
    private void createParentDirectories(File file) {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }
     public String[] ParcoursDossierTGZ(String cheminDossier) {
            File dossier = new File(cheminDossier);
            File[] fichiers = dossier.listFiles();

            List<String> nomsFichiersTgz = new ArrayList<>();

            if (fichiers != null) {
                for (File fichier : fichiers) {
                    if (fichier.isFile() && fichier.getName().endsWith(".tgz")) {
                        nomsFichiersTgz.add(cheminDossier+"/"+fichier.getName());
                    }
                }
            }

            return nomsFichiersTgz.toArray(new String[0]);
        }
    public String[] ParcoursDossierZIP(String cheminDossier) {
        File dossier = new File(cheminDossier);
        File[] fichiers = dossier.listFiles();

        List<String> nomsFichiersZIP = new ArrayList<>();

        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isFile() && fichier.getName().endsWith(".zip")) {
                    nomsFichiersZIP.add(cheminDossier+"/"+fichier.getName());
                }
            }
        }
        return nomsFichiersZIP.toArray(new String[0]);
    }
        public void unzipFile(String zipFilePath, String destinationFolderPath) throws IOException {
            ZipFile zipFile = new ZipFile(zipFilePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            byte[] buffer = new byte[1024];
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();
                String entryPath = destinationFolderPath + File.separator + entryName;

                if (entry.isDirectory()) {
                    File dir = new File(entryPath);
                    dir.mkdirs();
                } else {
                    File outputFile = new File(entryPath);
                    File parentDir = outputFile.getParentFile();
                    parentDir.mkdirs();

                    InputStream inputStream = zipFile.getInputStream(entry);
                    FileOutputStream outputStream = new FileOutputStream(outputFile);

                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }

                    outputStream.close();
                    inputStream.close();
                }
            }

            zipFile.close();
        }


}
