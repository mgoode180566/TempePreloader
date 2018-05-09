/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelgoode
 */
public class Group {

    private static final Logger log = Logger.getLogger(Group.class.getName());
    String factory;
    String model;
    String quality;
    String color;
    String po;
    String complex_po;
    String size;
    String additionalCodeDesc;
    String price_country_2;
    String id_importer_country;
    Long quantity;
    ArrayList<String> contracts = new ArrayList<>();
    ArrayList<SKULine> skulines = new ArrayList<>();
    ArrayList<Date> shippingDates = new ArrayList<>();

    public Group() {
        this.factory = "";
        this.color = "";
        this.model = "";
        this.quality = "";
        this.complex_po = "";
        this.po = "";
        this.size = "";
        this.additionalCodeDesc = "";
        this.price_country_2 = "";
        this.quantity = 0L;
        this.id_importer_country = "";
    }

    public Group(String factory, String color, String model, String quality, String size, String additional_Code, String additional_Code_Desc, String price_country_2, String id_importer_country) {

        this.factory = factory.trim();
        this.color = color.trim();
        this.model = model.trim();
        this.quality = quality.trim();
        this.size = size;
        this.additionalCodeDesc = this.getAdditionalCodeDesc(additional_Code, additional_Code_Desc);
        this.price_country_2 = price_country_2;
        this.complex_po = "";
        this.po = "";
        this.quantity = 0L;
        this.id_importer_country = id_importer_country;

    }

    private String getAdditionalCodeDesc(String additional_Code, String additional_Code_Desc) {
        // 1. check in additional_Code if contains 'Order' eg. 'Order,RN,Volutary'
        // 2. using same index the get the correct value from additional_Code_Desc
        // 3. return value if found
        if (additional_Code.toUpperCase().contains("Order".toUpperCase())) {
            List fieldList = new ArrayList();
            String[] fields = additional_Code.split(",");
            Collections.addAll(fieldList, fields);
            List valueList = new ArrayList();
            String[] values = additional_Code_Desc.split(",");
            Collections.addAll(valueList, values);
            return (String) valueList.get(fieldList.indexOf("ORDER"));
        } else {
            return "";
        }
    }

    public void addQty(long qty) {

        this.quantity = this.quantity + qty;

    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getComplex_po() {
        return complex_po;
    }

    public void setComplex_po(String complex_po) {
        this.complex_po = complex_po;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getPrice_country_2() {
        return price_country_2;
    }

    public void setPrice_country_2(String price_country_2) {
        this.price_country_2 = price_country_2;
    }

    public String getId_importer_country() {
        return id_importer_country;
    }

    public void setId_importer_country(String id_importer_country) {
        this.id_importer_country = id_importer_country;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.factory);
        hash = 47 * hash + Objects.hashCode(this.model);
        hash = 47 * hash + Objects.hashCode(this.quality);
        hash = 47 * hash + Objects.hashCode(this.color);
        hash = 47 * hash + Objects.hashCode(this.additionalCodeDesc);
        hash = 47 * hash + Objects.hashCode(this.price_country_2);
        hash = 47 * hash + Objects.hashCode(this.id_importer_country);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (!Objects.equals(this.factory, other.factory)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.quality, other.quality)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.additionalCodeDesc, other.additionalCodeDesc)) {
            return false;
        }
        if (!Objects.equals(this.price_country_2, other.price_country_2)) {
            return false;
        }
        if (!Objects.equals(this.id_importer_country, other.id_importer_country)) {
            return false;
        }
        return true;
    }

    public void saveGroup(BufferedWriter out, IgnoreCalloutList ignoreList, HashMap<String, Callout> calloutMap, ArrayList<String> _TEMPE_FAM_DOBLE) {

        //BufferedWriter out = null;
        complex_po = this.getComplexPO();

        try {
            //out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

            Iterator iter = this.skulines.iterator();
            while (iter.hasNext()) {
                SKULine skuline = (SKULine) iter.next();
                Date date = this.shippingDates.get(0); // take the earliest date
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                skuline.setShippingDate(format.format(date));
                if (skuline.skuLine.length() > 0) {
                    String c = skuline.callout;
                    String sk = skuline.getCallout();
                    if (!ignoreList.contains(skuline.getCallout())) {
                        Callout callout = (Callout) calloutMap.get(skuline.getCallout());
                        if (callout != null) {
                            if (skuline.getFrontalLabel().toUpperCase().equals("C")) {
                                long skuQty = skuline.getQuantity();
                                if (_TEMPE_FAM_DOBLE.contains(skuline.getID_Family())) { // double the qty
                                    out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuQty * 2, callout.getFrontalLabelC(), skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                                    out.newLine();
                                } else {
                                    out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuQty, callout.getFrontalLabelC(),skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                                    out.newLine();
                                }
                            } else if (skuline.getFrontalLabel().toUpperCase().equals("B")) {
                                out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuline.getQuantity(), callout.getFrontalLabelB(), skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                                out.newLine();
                            }

                            if (!callout.getSeal().equals("")) {
                                out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuline.getQuantity(), callout.getSeal(), skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                                out.newLine();
                            }

                            if ((skuline.getAlarm().equals("2")) && (!callout.getAlarm().equals(""))) {
                                out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuline.getQuantity(), callout.getAlarm(), skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                                out.newLine();
                            }

                            if ((skuline.getBag().equals("1")) && (!callout.getBagHeel().equals(""))) {
                                out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuline.getQuantity(), callout.getBagHeel(), skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                                out.newLine();
                            }

                        }

                        out.write(String.format("%s%s;%d;%s;%s;%s", skuline.getSkuLine(), complex_po, skuline.getQuantity(), skuline.getCallout(), skuline.getIMPORTER_COUNTRY_DESC_1(), skuline.getIMPORTER_COUNTRY_DESC_2()));
                        out.newLine();

                        log.debug(String.format("Callout %s omitted from output", skuline.getCallout()));
                    }
                } else {
                    log.debug("Skuline was zero length");
                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    private String getComplexPO() {

        Iterator iter = contracts.iterator();

        String s = "";

        while (iter.hasNext()) {

            s = s + (String) iter.next() + "-";

        }

        return s;

    }

    public void addSKU(SKULine skuLine) {

        try {
            boolean matchFound = false;

            for (SKULine asku : this.skulines) {

                if (asku.equals(skuLine)) { // matching size found

                    this.addQty(skuLine.getQuantity());

                    asku.addQty(skuLine.getQuantity());

                    matchFound = true;

                }

            }

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            this.shippingDates.add(format.parse(skuLine.getShippingDate()));
            Collections.sort(this.shippingDates);

            if (!matchFound) {

                this.skulines.add(skuLine);

            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

    public void expandGroup() {

        ArrayList<SKULine> newLines = new ArrayList();

        for (SKULine sku : this.skulines) {

            //this.addVariableStickers(sku, newLines);
            //this.addHeelsBag(sku, newLines);
            //this.addAlarm(sku, newLines);
            this.addAdditionalItems(sku, newLines);

        }

        this.skulines.addAll(newLines);

    }

    private void addVariableStickers(SKULine sku, ArrayList<SKULine> lines) {

        if (sku.values.frontalLabel.equals("C")) {

            lines.add(new SKULine(sku, this.getSize(), "ADFB", sku.values.brand, sku.values.season));

        } else if (sku.values.frontalLabel.equals("B")) {

            lines.add(new SKULine(sku, this.getSize(), "ADFBAG", sku.values.brand, sku.values.season));

        }

    }

    private void addHeelsBag(SKULine sku, ArrayList<SKULine> lines) {

        if (sku.values.heelsBag.equals("1")) {

            lines.add(new SKULine(sku, "BAG_HEELS"));

        }

    }

    private void addAlarm(SKULine sku, ArrayList<SKULine> lines) {

        if ((sku.values.alarm.equals("0")) && (sku.values.brand.equals("BB"))) {

            lines.add(new SKULine(sku, this.getSize(), "BBADCO", sku.values.brand, sku.values.season));

        }

    }

    private void addAdditionalItems(SKULine sku, ArrayList<SKULine> lines) {

        String _DELIMITER = ",";

        String[] ids = sku.values.ID_Additional_Labels.split(_DELIMITER);

        String[] descs = sku.values.DESC_Additional_Labels.split(_DELIMITER);

        List<String> idList = Arrays.asList(ids);

        List<String> descList = Arrays.asList(descs);

        List<String> callouts = new ArrayList();

        if (idList.size() > 0) {

            int i = 0;

            for (String s : idList) {

                if (!s.trim().equals("0")) {

                    callouts.add(String.format("%s-%s", s.trim(), descList.get(i).trim()));

                }

                i++;

            }

        }

        if (callouts.size() > 0) {

            for (String d : callouts) {

                lines.add(new SKULine(sku, d.trim()));

            }

        }

    }
}
