/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelgoode
 */
public class TempePreloader {

    /**
     * @param args the command line arguments
     */
    private static final Logger log = Logger.getLogger(TempePreloader.class.getName());

    public static void main(String[] args) {

        //String inputFolder = "\\\\SMLEDI\\EDIDateFile\\TempeCSV\\Preloader";
        //String inputFolder = "C:\\Tempe\\Input";
        //System.out.println(config.inputFolder);
        try {

            // Creating FileHandler
            Map m = System.getenv();

            Config config = new Config();

            config.getConfig();

            File dir = new File(config.inputFolder);

            File[] files = dir.listFiles();

            if (files.length < 1) {
                log.debug("Nothing found to process");
            } else {

                IgnoreCalloutList ignoreCallouts = IgnoreCalloutList.getInstance();

                ignoreCallouts.load();

                HashMap<String, Callout> calloutMap = JXLSheet.getInstance().readCallouts(config.calloutLookups);

                ArrayList<String> doubles = JXLSheet.getInstance().readDoubles(config.doubleFile);

                log.debug(String.format("Input folder=%s", config.inputFolder));
                log.debug(String.format("Output folder=%s", config.outputFolder));

                boolean fileFound = false;

                GroupList groupList = new GroupList();

                for (File file : files) {

                    if ((file.isFile()) && (!file.isDirectory())) {

                        fileFound = true;

                        log.debug(String.format("Processing...%s", file.getAbsolutePath()));

                        SKUDataFile skuFile = new SKUDataFile(file.getAbsolutePath(), groupList, calloutMap);

                        file.delete();

                    }

                }

                if (fileFound) {

                    groupList.expandGroups();

                    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd--hh-mm-ss");

                    Date dNow = new Date();

                    String outFileName = "TEMPE-" + ft.format(dNow) + ".txt";

                    if (groupList.size() > 0) {

                        groupList.saveGroups(String.format("%s\\%s", config.outputFolder, outFileName), ignoreCallouts, calloutMap, doubles);
                        log.debug(String.format("Saved...%s\\%s", config.outputFolder, outFileName));

                    }

                    log.debug("Finished..." + outFileName);
                }

            }
        } catch (Exception ex) {
                System.out.println(ex.getMessage());
                log.fatal(ex);
        }

    }
}
