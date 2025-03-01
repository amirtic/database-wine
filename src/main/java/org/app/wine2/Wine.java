

package org.app.wine2;

import javafx.beans.property.*;

/**
 * Represents a wine sample with various chemical properties and attributes.
 * This class uses JavaFX properties to support data binding in UI components.
 */
public class Wine {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty hireDate = new SimpleStringProperty();
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

    /**
     * Constructs a new {@code Wine} object with all properties initialized.
     *
     * @param fixedAcidity         Fixed acidity level.
     * @param volatileAcidity      Volatile acidity level.
     * @param citricAcid           Citric acid concentration.
     * @param residualSugar        Residual sugar level.
     * @param chlorides            Chloride concentration.
     * @param freeSulfurDioxide    Free sulfur dioxide level.
     * @param totalSulfurDioxide   Total sulfur dioxide level.
     * @param density              Density of the wine.
     * @param pH                   pH level of the wine.
     * @param sulphates            Sulphate concentration.
     * @param alcohol              Alcohol percentage.
     * @param quality              Quality rating of the wine.
     * @param color                Color of the wine (e.g., "red" or "white").
     * @param id                   Unique identifier for the wine sample.
     * @param hireDate             Date associated with the sample.
     */
    public Wine(double fixedAcidity, double volatileAcidity, double citricAcid, double residualSugar,
                double chlorides, double freeSulfurDioxide, double totalSulfurDioxide, double density,
                double pH, double sulphates, double alcohol, String quality, String color, int id, String hireDate) {
        this.id.set(id);
        this.hireDate.set(hireDate);
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

    /**
     * Gets the wine's unique ID property.
     * @return The ID property.
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Gets the hire date property.
     * @return The hire date property.
     */
    public StringProperty hireDate() {
        return hireDate;
    }

    // Getter methods for all properties

    /**
     * Gets the fixed acidity property.
     * @return The fixed acidity property.
     */
    public DoubleProperty fixedAcidityProperty() {
        return fixedAcidity;
    }

    /**
     * Gets the volatile acidity property.
     * @return The volatile acidity property.
     */
    public DoubleProperty volatileAcidityProperty() {
        return volatileAcidity;
    }

    /**
     * Gets the citric acid property.
     * @return The citric acid property.
     */
    public DoubleProperty citricAcidProperty() {
        return citricAcid;
    }

    /**
     * Gets the residual sugar property.
     * @return The residual sugar property.
     */
    public DoubleProperty residualSugarProperty() {
        return residualSugar;
    }

    /**
     * Gets the chlorides property.
     * @return The chlorides property.
     */
    public DoubleProperty chloridesProperty() {
        return chlorides;
    }

    /**
     * Gets the free sulfur dioxide property.
     * @return The free sulfur dioxide property.
     */
    public DoubleProperty freeSulfurDioxideProperty() {
        return freeSulfurDioxide;
    }

    /**
     * Gets the total sulfur dioxide property.
     * @return The total sulfur dioxide property.
     */
    public DoubleProperty totalSulfurDioxideProperty() {
        return totalSulfurDioxide;
    }

    /**
     * Gets the density property.
     * @return The density property.
     */
    public DoubleProperty densityProperty() {
        return density;
    }

    /**
     * Gets the pH property.
     * @return The pH property.
     */
    public DoubleProperty pHProperty() {
        return pH;
    }

    /**
     * Gets the sulphates property.
     * @return The sulphates property.
     */
    public DoubleProperty sulphatesProperty() {
        return sulphates;
    }

    /**
     * Gets the alcohol property.
     * @return The alcohol property.
     */
    public DoubleProperty alcoholProperty() {
        return alcohol;
    }

    /**
     * Gets the quality property.
     * @return The quality property.
     */
    public StringProperty qualityProperty() {
        return quality;
    }

    /**
     * Gets the color property.
     * @return The color property.
     */
    public StringProperty colorProperty() {
        return color;
    }
}
