package CompressionTools;

import Select.MoveDirectory;

import org.apache.commons.compress.archivers.tar.*;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DataCompression {
    public void compressDirectory(String sourceDirPath, String destinationFilePath, String FolderPathOutput) throws IOException {
        // Create the destination folder if it doesn't exist
        Path destinationDir = Paths.get(FolderPathOutput);
        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }

        String destinationFileName = Paths.get(destinationFilePath).getFileName().toString();
        String compressedFilePath = destinationDir.resolve(destinationFileName).toString();

        try (OutputStream fos = Files.newOutputStream(Paths.get(compressedFilePath));
             OutputStream gzos = new GzipCompressorOutputStream(fos);
             TarArchiveOutputStream tarOutputStream = new TarArchiveOutputStream(gzos)) {

            Path sourceDir = Paths.get(sourceDirPath);
            Files.walk(sourceDir)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        try {
                            String entryName = sourceDir.relativize(path).toString();
                            TarArchiveEntry tarEntry = new TarArchiveEntry(path.toFile(), entryName);
                            tarOutputStream.putArchiveEntry(tarEntry);
                            IOUtils.copy(Files.newInputStream(path), tarOutputStream);
                            tarOutputStream.closeArchiveEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    public void zipFiles(File[] files, String zipFilePath,String FolderPathOutput) throws IOException {
        File destinationDir = new File(FolderPathOutput);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }

        String zipFileName = new File(zipFilePath).getName();
        String zipFilePathInDataExtract = new File(destinationDir, zipFileName).getPath();

        try (FileOutputStream fos = new FileOutputStream(zipFilePathInDataExtract);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            byte[] buffer = new byte[1024];

            for (File file : files) {
                if (!file.exists()) {
                    System.out.println("Le fichier '" + file.getPath() + "' n'existe pas.");
                    continue;
                }

                FileInputStream fis = new FileInputStream(file);
                zos.putNextEntry(new ZipEntry(file.getName()));

                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                fis.close();
                zos.closeEntry();

                MoveDirectory moveDirectory = new MoveDirectory();
                moveDirectory.supprimerDossier(file);
            }
        }
    }
}
