/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

/**
 *
 * @author michaelgoode
 */
public class SkuValues {
    
    String season;
    String brand;
    String C;
    String LL;
    String frontalLabel;
    String heelsBag;
    String alarm;
    String ID_Additional_Labels;
    String DESC_Additional_Labels;
    String ID_Family;
    String priceCountry2;
    String shippingDate;
    String IMPORTER_COUNTRY_DESC;
    
    public SkuValues( String season, String brand, String c, String ll, String frontal, String heelsBag, String alarm, String ID_Additional_Labels, String DESC_Additional_Labels, String family, String Price_Country_2, String shippingDate, String importer  ) {
        this.season = season;
        this.brand = brand;
        this.C = c;
        this.LL = ll;
        this.frontalLabel = frontal;
        this.heelsBag = heelsBag;
        this.alarm = alarm;
        this.ID_Additional_Labels = ID_Additional_Labels;
        this.DESC_Additional_Labels = DESC_Additional_Labels;
        this.ID_Family = family;
        this.priceCountry2 = Price_Country_2;
        this.shippingDate = shippingDate;
        this.IMPORTER_COUNTRY_DESC = importer;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getLL() {
        return LL;
    }

    public void setLL(String LL) {
        this.LL = LL;
    }

    public String getFrontalLabel() {
        return frontalLabel;
    }

    public void setFrontalLabel(String frontalLabel) {
        this.frontalLabel = frontalLabel;
    }

    public String getHeelsBag() {
        return heelsBag;
    }

    public void setHeelsBag(String heelsBag) {
        this.heelsBag = heelsBag;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getPriceCountry2() {
        return priceCountry2;
    }

    public void setPriceCountry2(String priceCountry2) {
        this.priceCountry2 = priceCountry2;
    }

    public String getID_Family() {
        return ID_Family;
    }

    public void setID_Family(String ID_Family) {
        this.ID_Family = ID_Family;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }
    
    
    
    
    
    
    
}
