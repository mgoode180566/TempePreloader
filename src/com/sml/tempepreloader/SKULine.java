/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.util.Objects;

/**
 *
 * @author michaelgoode
 */
public class SKULine {

    String skuLine;
    String size;
    String callout;
    String frontalLabel;
    String ID_Family;
    String bag;
    String alarm;
    String shippingDate;
    String IMPORTER_COUNTRY_DESC_1;
    String IMPORTER_COUNTRY_DESC_2;

    SkuValues values;
    long quantity;

    public SKULine(SKULine sku, String callout) {
        this.skuLine = sku.getSkuLine();
        this.size = sku.size;
        this.quantity = sku.getQuantity();
        this.callout = callout;
        this.ID_Family = sku.getID_Family();
        this.IMPORTER_COUNTRY_DESC_1 = sku.getIMPORTER_COUNTRY_DESC_1();
        this.IMPORTER_COUNTRY_DESC_2 = sku.getIMPORTER_COUNTRY_DESC_2();
    }

    public SKULine(String skuLine, String size, long qty, SkuValues values, String family) {
        this.skuLine = skuLine;
        this.size = size;
        this.quantity = qty;
        this.values = values;
        this.ID_Family = family;

        this.frontalLabel = values.getFrontalLabel();
        this.bag = values.heelsBag;
        this.alarm = values.alarm;
        this.shippingDate = values.getShippingDate();

        if (values.brand.equals("B")) {
            values.brand = "BK";
        } else if (values.brand.equals("M")) {
            values.brand = "MD";
        } else if (values.brand.equals("N")) {
            values.brand = "OY";
        } else if (values.brand.equals("P")) {
            values.brand = "PB";
        } else if (values.brand.equals("V")) {
            values.brand = "ST";
        } else if (values.brand.equals("Q")) {
            values.brand = "UQ";
        } else if (values.brand.equals("Z")) {
            values.brand = "ZA";
        } else if (values.brand.equals("H")) {
            values.brand = "ZH";
        } else if (values.brand.equals("A")) {
            values.brand = "ZS";
        }
        setImporters(values.IMPORTER_COUNTRY_DESC);

        //String x  = "TE" + values.season.substring(0,1) + values.season.substring(3,5) + values.brand + values.C + String.format("%02d",Integer.parseInt(values.LL));
        //String y  = values.season.substring(3,5) ; //+ values.season.substring(2,2);
        
        if (values.LL.contains("b")) {
            String prefix = values.LL.substring(0, values.LL.length()-1);
            values.LL = String.format("%02db", Integer.parseInt(prefix));
        } else {
            values.LL = String.format("%02d", Integer.parseInt(values.LL));
        }
        this.callout = "TE" + values.season.substring(0, 1) + values.season.substring(3, 5) + values.brand + values.C + values.LL;
    }

    public SKULine(SKULine skuLine, String size, String suffix, String brand, String season) {
        this.skuLine = skuLine.getSkuLine();
        this.size = size;
        this.quantity = skuLine.quantity;
        this.values = values;

        if (brand.equals("B")) {
            brand = "BK";
        } else if (brand.equals("M")) {
            brand = "MD";
        } else if (brand.equals("N")) {
            brand = "OY";
        } else if (brand.equals("P")) {
            brand = "PB";
        } else if (brand.equals("V")) {
            brand = "ST";
        } else if (brand.equals("Q")) {
            brand = "UQ";
        } else if (brand.equals("Z")) {
            brand = "ZA";
        } else if (brand.equals("H")) {
            brand = "ZH";
        } else if (brand.equals("A")) {
            brand = "ZS";
        }
        setImporters(values.IMPORTER_COUNTRY_DESC);

        //String x  = "TE" + values.season.substring(0,1) + values.season.substring(3,5) + values.brand + values.C + String.format("%02d",Integer.parseInt(values.LL));
        //String y  = values.season.substring(3,5) ; //+ values.season.substring(2,2);
        this.callout = "TE" + season.substring(0, 1) + season.substring(3, 5) + brand + suffix;
    }

    public String getSkuLine() {
        return skuLine;
    }

    public void setSkuLine(String skuLine) {
        this.skuLine = skuLine;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getCallout() {
        return callout;
    }

    public void setCallout(String callout) {
        this.callout = callout;
    }

    public String getBag() {
        return bag;
    }

    public void setBag(String bag) {
        this.bag = bag;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setImporters(String importer) {
        if (importer.length() > 950) {
            IMPORTER_COUNTRY_DESC_1 = importer.substring(0, 950);
            IMPORTER_COUNTRY_DESC_2 = importer.substring(950, importer.length());
        } else {
            IMPORTER_COUNTRY_DESC_1 = importer;
            IMPORTER_COUNTRY_DESC_2 = "";
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.size);
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
        final SKULine other = (SKULine) obj;
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        return true;
    }

    public void addQty(long qty) {
        quantity = quantity + qty;
    }

    public String getFrontalLabel() {
        return frontalLabel;
    }

    public void setFrontalLabel(String frontalLabel) {
        this.frontalLabel = frontalLabel;
    }

    public String getID_Family() {
        return ID_Family;
    }

    public void setID_Family(String ID_Family) {
        this.ID_Family = ID_Family;
    }

    public String getIMPORTER_COUNTRY_DESC_1() {
        return IMPORTER_COUNTRY_DESC_1;
    }

    public void setIMPORTER_COUNTRY_DESC_1(String IMPORTER_COUNTRY_DESC_1) {
        this.IMPORTER_COUNTRY_DESC_1 = IMPORTER_COUNTRY_DESC_1;
    }

    public String getIMPORTER_COUNTRY_DESC_2() {
        return IMPORTER_COUNTRY_DESC_2;
    }

    public void setIMPORTER_COUNTRY_DESC_2(String IMPORTER_COUNTRY_DESC_2) {
        this.IMPORTER_COUNTRY_DESC_2 = IMPORTER_COUNTRY_DESC_2;
    }

}
