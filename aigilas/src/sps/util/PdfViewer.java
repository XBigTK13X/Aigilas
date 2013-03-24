package sps.util;

import sps.core.Logger;

import java.awt.*;
import java.io.File;

public class PdfViewer {
    public static void open(String name) {
        try {
            File pdfFile = new File("assets/doc/" + name + ".pdf");
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                }
                else {
                    Logger.error("Awt Desktop is not available, which is required to view a PDF");
                }
            }
            else {
                Logger.error("PDF doesn't exist");
            }

        }
        catch (Exception ex) {
            Logger.exception("An error occurred while attempting to open a PDF.", ex, false);
        }

    }
}