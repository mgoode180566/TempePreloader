/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelgoode
 *
 * Master file which will contain one header line and ALL skulines from all
 * files found
 *
 *
 *
 *
 *
 *
 *
 */
public class SKUDataFile {

    private static final Logger log = Logger.getLogger(SKUDataFile.class.getName());

    String _FACTORY = "SUPPLIER_FACTORY_ID";
    String _COLOR = "ID_COLOUR";
    String _MODEL = "MODEL";
    String _QUALITY = "QUALITY";
    String _SIZE = "ID_SIZES";
    String _PO = "ORDER Nº";
    String _QTY = "QUANTITY_SIZE_ORDERS";
    String _SEASON = "SEASON";
    String _BRAND = "ID_BRAND";
    String _C = "ID_SECTION";
    String _LL = "ID_LABEL_BRAND";
    String _FRONTAL_LABEL = "FRONTAL_LABEL";
    String _HEELS_BAG = "BAG_HEELS";
    String _ALARM = "ALARM";
    String _Additional = "ID_LABEL-OTHERS_ADDITIONAL";
    String _Additional_Desc = "DESC_LABEL-OTHERS_ADDITIONAL";
    String _Type_Code_Additional = "TYPE_CODE_ADDITIONAL";
    String _Type_Code_Desc_Additional = "TYPE_CODE_DESC_ADDITIONAL";
    String _PRICE_COUNTRY_2 = "PRICE_COUNTRY_2";
    String _ID_FAMILY = "ID_FAMILY";
    String _ID_SUBFAMILY = "ID_SUBFAMILY";
    String _SHIPPING_DATE = "SHIPPING DATE";
    String _IMPORTERS = "IMPORTER_COUNTRY_DESC";
    String _ID_IMPORTER_COUNTRY = "ID_IMPORTER_COUNTRY";

    String headerLine;

    HeaderList headers;

    public SKUDataFile(String filename, GroupList groupList, HashMap<String, Callout> calloutMap) {

        //PropertyConfigurator.configure("log4j.properties");
        // open file 
        BufferedReader br = null;
        try {

            InputStream is = new FileInputStream(filename);

            String UTF8 = "utf8";
            String _Delimiter = ";";
            br = new BufferedReader(new InputStreamReader(is, UTF8));

            StringBuilder sb = new StringBuilder();
            String headerLine = br.readLine();
            String[] fieldNames = headerLine.split(_Delimiter);
            headers = new HeaderList(Arrays.asList(fieldNames));
            // which family field do we use as we have a choice of two
            String familyField = "";
            if (headers.indexOf(_ID_FAMILY) > -1) {
                familyField = _ID_FAMILY;
            } else {
                familyField = _ID_SUBFAMILY;
            }
            String line = br.readLine();
            while (line != null) {
                if (!line.endsWith(_Delimiter)) {
                    line = line + _Delimiter;
                }
                String[] fieldValues = line.split(_Delimiter);
                FieldValues values = new FieldValues(Arrays.asList(fieldValues));
                long qty = Long.parseLong((String) values.get(headers.indexOf(_QTY)));
                Group group = new Group(
                        (String) values.get(headers.indexOf(_FACTORY)),
                        (String) values.get(headers.indexOf(_COLOR)),
                        (String) values.get(headers.indexOf(_MODEL)),
                        (String) values.get(headers.indexOf(_QUALITY)),
                        (String) values.get(headers.indexOf(_SIZE)),
                        (String) values.get(headers.indexOf(_Type_Code_Additional)),
                        (String) values.get(headers.indexOf(_Type_Code_Desc_Additional)),
                        (String) values.get(headers.indexOf(_PRICE_COUNTRY_2)),
                        (String) values.get(headers.indexOf(_ID_IMPORTER_COUNTRY)));

                SkuValues skuvalues = new SkuValues((String) values.get(headers.indexOf(_SEASON)),
                        (String) values.get(headers.indexOf(_BRAND)),
                        (String) values.get(headers.indexOf(_C)),
                        (String) values.get(headers.indexOf(_LL)),
                        (String) values.get(headers.indexOf(_FRONTAL_LABEL)),
                        (String) values.get(headers.indexOf(_HEELS_BAG)),
                        (String) values.get(headers.indexOf(_ALARM)),
                        (String) values.get(headers.indexOf(_Additional)),
                        (String) values.get(headers.indexOf(_Additional_Desc)),
                        (String) values.get(headers.indexOf(familyField)),
                        (String) values.get(headers.indexOf(_PRICE_COUNTRY_2)),
                        (String) values.get(headers.indexOf(_SHIPPING_DATE)),
                        (String) values.get(headers.indexOf(_IMPORTERS)));
                 
                groupList.addGroup(group, line, (String) values.get(0), qty, skuvalues, skuvalues.getID_Family());
                
                line = br.readLine();
            }
            is.close();
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
    }

    public String getHeaderLine() {
        return headerLine;
    }

    public void setHeaderLine(String headerLine) {
        this.headerLine = headerLine;
    }

    public int getSizePos() {
        return headers.indexOf(_SIZE);
    }

}
