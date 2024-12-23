package org.app.wine2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Wine {
    private final DoubleProperty fixedAcidity = new SimpleDoubleProperty();
    private final DoubleProperty volatileAcidity = new SimpleDoubleProperty();
    private final DoubleProperty citricAcid = new SimpleDoubleProperty();
    private final DoubleProperty residualSugar = new SimpleDoubleProperty();
    private final DoubleProperty chlorides = new SimpleDoubleProperty();
    private final DoubleProperty freeSulfurDioxide = new SimpleDoubleProperty();
    private final DoubleProperty totalSulfurDioxide = new SimpleDoubleProperty();
    private final DoubleProperty density = new SimpleDoubleProperty();
    private final DoubleProperty pH = new SimpleDoubleProperty();
    private final DoubleProperty sulphates = new SimpleDoubleProperty();
    private final DoubleProperty alcohol = new SimpleDoubleProperty();
    private final StringProperty quality = new SimpleStringProperty();
    private final StringProperty color = new SimpleStringProperty();

    // Constructor
    public Wine(double fixedAcidity, double volatileAcidity, double citricAcid, double residualSugar,
                double chlorides, double freeSulfurDioxide, double totalSulfurDioxide, double density,
                double pH, double sulphates, double alcohol, String quality, String color) {
        this.fixedAcidity.set(fixedAcidity);
        this.volatileAcidity.set(volatileAcidity);
        this.citricAcid.set(citricAcid);
        this.residualSugar.set(residualSugar);
        this.chlorides.set(chlorides);
        this.freeSulfurDioxide.set(freeSulfurDioxide);
        this.totalSulfurDioxide.set(totalSulfurDioxide);
        this.density.set(density);
        this.pH.set(pH);
        this.sulphates.set(sulphates);
        this.alcohol.set(alcohol);
        this.quality.set(quality);
        this.color.set(color);
    }

    // Getter and Setter Methods for all properties
    public DoubleProperty fixedAcidityProperty() {
        return fixedAcidity;
    }

    public DoubleProperty volatileAcidityProperty() {
        return volatileAcidity;
    }

    public DoubleProperty citricAcidProperty() {
        return citricAcid;
    }

    public DoubleProperty residualSugarProperty() {
        return residualSugar;
    }

    public DoubleProperty chloridesProperty() {
        return chlorides;
    }

    public DoubleProperty freeSulfurDioxideProperty() {
        return freeSulfurDioxide;
    }

    public DoubleProperty totalSulfurDioxideProperty() {
        return totalSulfurDioxide;
    }

    public DoubleProperty densityProperty() {
        return density;
    }

    public DoubleProperty pHProperty() {
        return pH;
    }

    public DoubleProperty sulphatesProperty() {
        return sulphates;
    }

    public DoubleProperty alcoholProperty() {
        return alcohol;
    }

    public StringProperty qualityProperty() {
        return quality;
    }

    public StringProperty colorProperty() {
        return color;
    }

    // Getter for color as String
    public String getColor() {
        return color.get();
    }

    // Setter for color
    public void setColor(String color) {
        this.color.set(color);
    }
}