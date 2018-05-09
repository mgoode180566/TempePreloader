/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author michaelgoode
 */
public class JXLSheet {

    private static JXLSheet instance = null;

    protected JXLSheet() {
        // Exists only to defeat instantiation.
    }

    public static JXLSheet getInstance() {
        if (instance == null) {
            instance = new JXLSheet();
        }
        return instance;
    }

    public HashMap<String, Callout> readCallouts(String filename) {

        File inputWorkbook = new File(filename);
        Workbook w;
        HashMap<String, Callout> hashMap = null;

        try {

            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            hashMap = new HashMap<String, Callout>();

            for (int i = 1; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = null;
                    Callout callout = null;
                    cell = sheet.getCell(0, i);
                    callout = new Callout();
                    callout.lookupCallout = cell.getContents().trim();
                    cell = sheet.getCell(1, i);
                    callout.frontalLabelC = cell.getContents().trim();
                    cell = sheet.getCell(2, i);
                    callout.frontalLabelB = cell.getContents().trim();
                    cell = sheet.getCell(3, i);
                    callout.seal = cell.getContents().trim();
                    cell = sheet.getCell(4, i);
                    callout.alarm = cell.getContents().trim();
                    cell = sheet.getCell(5, i);
                    callout.bagHeel = cell.getContents().trim();

                    hashMap.put(callout.lookupCallout, callout);

                }
            }

            w.close();

            return hashMap;

        } catch (IOException ex) {
            Logger.getLogger(JXLSheet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (BiffException ex) {
            Logger.getLogger(JXLSheet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<String> readDoubles(String filename) {

        File inputWorkbook = new File(filename);
        Workbook w;
        ArrayList<String> doubles = null;

        try {

            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);

            doubles = new ArrayList<String>();

            for (int i = 1; i < sheet.getRows(); i++) {
                Cell cell = sheet.getCell(0, i);
                String v = cell.getContents().trim();
                doubles.add(v);
            }

            w.close();

            return doubles;

        } catch (IOException ex) {
            Logger.getLogger(JXLSheet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (BiffException ex) {
            Logger.getLogger(JXLSheet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
