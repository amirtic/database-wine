package org.app.wine2;

public class Wine {
    private Double fixedAcidity;
    private Double volatileAcidity;
    private Double citricAcid;
    private Double residualSugar;
    private Double chlorides;
    private Double freeSulfurDioxide;
    private Double totalSulfurDioxide;
    private Double density;
    private Double pH;
    private Double sulphates;
    private Double alcohol;
    private String quality;  // تغيير النوع إلى String
    private String color;

    // Constructor
    public Wine(Double fixedAcidity, Double volatileAcidity, Double citricAcid, Double residualSugar,
                Double chlorides, Double freeSulfurDioxide, Double totalSulfurDioxide, Double density,
                Double pH, Double sulphates, Double alcohol, String quality, String color) {
        this.fixedAcidity = fixedAcidity;
        this.volatileAcidity = volatileAcidity;
        this.citricAcid = citricAcid;
        this.residualSugar = residualSugar;
        this.chlorides = chlorides;
        this.freeSulfurDioxide = freeSulfurDioxide;
        this.totalSulfurDioxide = totalSulfurDioxide;
        this.density = density;
        this.pH = pH;
        this.sulphates = sulphates;
        this.alcohol = alcohol;
        this.quality = quality;  // الاحتفاظ بنوعية نصية للـ quality
        this.color = color;
    }



    // Getters
    public Double getFixedAcidity() { return fixedAcidity; }
    public Double getVolatileAcidity() { return volatileAcidity; }
    public Double getCitricAcid() { return citricAcid; }
    public Double getResidualSugar() { return residualSugar; }
    public Double getChlorides() { return chlorides; }
    public Double getFreeSulfurDioxide() { return freeSulfurDioxide; }
    public Double getTotalSulfurDioxide() { return totalSulfurDioxide; }
    public Double getDensity() { return density; }
    public Double getpH() { return pH; }
    public Double getSulphates() { return sulphates; }
    public Double getAlcohol() { return alcohol; }
    public String getQuality() { return quality; }  // getter الخاص بـ quality
    public String getColor() { return color; }

    public
    double getProperty(String property) {
        return 0;
    }
}