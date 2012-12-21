package launcher;

import org.apache.commons.io.FileUtils;
import sps.core.Logger;

import java.io.File;
import java.net.URL;

public class Updater {
    //Working update paths
    private File update = new File("aigilas-update.zip");
    private File updateDir = new File("aigilas-update");
    private String licenseText;

    public Updater(){

    }

    public boolean runIfNeeded(String license) {
        boolean success = checkLicense(license) && checkVersion() && downloadUpdate() && applyUpdate();
        clean();
        Logger.info("Finished applying updates.");
        return success;
    }


    private boolean checkLicense(String license) {
        Logger.info("Validating SE license");
        Logger.info("Checking to see if a stable edition license has been entered.");
        if (licenseText != null && !licenseText.isEmpty()) {
            //TODO Validate against the server
            return true;
        }
        return false;
    }

    private boolean checkVersion() {
        try {
            Logger.info("Checking for updates.");
            //TODO makeRequest <- store in uR
            File versionPath = new File("assets/data/version.dat");

            String myVersion = FileUtils.readFileToString(versionPath);
            Logger.info("Detected version: " + myVersion);

            URL versionCheckUrl = new URL("http://www.simplepathstudios.com/download.php?target=aigilas&version=" + myVersion);
            //TODO This is most likely not the actual way to query
            String result = versionCheckUrl.getContent().toString();
            return result.contains("true");
        }
        catch (Exception e) {
            Logger.exception(e);
        }
        return true;
    }

    private boolean downloadUpdate(){
        try {
            Logger.info("Preparing the update location");

            if (update.exists()) {
                FileUtils.forceDelete(update);
            }

            int responseTimeoutMs = 10000;
            int downloadTimeoutMs = 60000;
            Logger.info("Attempting to download an update using license: [" + licenseText + "]");
            String spsLicenseUrl = "http://www.simplepathstudios.com/download.php?target=aigilas&license=" + licenseText;
            Logger.info("Downloading latest stable edition");
            FileUtils.copyURLToFile(new URL(spsLicenseUrl), update, responseTimeoutMs, downloadTimeoutMs);
        }
        catch (Exception e) {
            Logger.exception(e);
        }
        return true;
    }

    private boolean applyUpdate() {
        try {
            Logger.info("Unzipping: " + update.getAbsolutePath());
            Archive.unzip(update);
            Logger.info("Replacing old content");
            File updateAssets = new File("aigilas-update/assets");
            File baseAssets = new File("./");
            FileUtils.copyDirectoryToDirectory(updateAssets, baseAssets);

            File updateCore = new File("aigilas-update/aigilas.jar");
            File baseCore = new File("aigilas.jar");
            FileUtils.copyFile(updateCore, baseCore);
        }
        catch (Exception e) {
            Logger.exception(e);
        }
        return true;
    }

    private boolean clean() {
        try {
            Logger.info("Cleaning up temporary files");
            FileUtils.forceDelete(update);
            FileUtils.deleteDirectory(updateDir);
        }
        catch (Exception e) {
            Logger.exception(e);
        }
        return true;
    }
}
