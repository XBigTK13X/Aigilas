package launcher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Archive {
    private Archive(){}

    static public void unzip(File zipFile) {
        try {
            int BUFFER = 2048;

            ZipFile zip = new ZipFile(zipFile);
            String newPath = zipFile.getName().substring(0, zipFile.getName().length() - 4);

            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            while (zipFileEntries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();
                File destFile = new File(newPath, currentEntry);
                File destinationParent = destFile.getParentFile();

                destinationParent.mkdirs();

                if (!entry.isDirectory()) {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    byte data[] = new byte[BUFFER];

                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            BUFFER);

                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }

                if (currentEntry.endsWith(".zip")) {
                    unzip(destFile);
                }
            }
        }
        catch (Exception e) {
            LaunchLogger.exception(e);
        }
    }
}
