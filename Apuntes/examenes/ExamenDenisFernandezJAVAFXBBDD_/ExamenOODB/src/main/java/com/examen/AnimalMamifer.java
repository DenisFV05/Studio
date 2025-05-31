package com.examen;

// TO DO implementar 'AnimalMamifer' per tal que funcioni el main
// - constructor
// - getters i setters
// - toString

public class AnimalMamifer extends Animal {

    private double pes_mitja = 0.0;
    private double esperancaMIN = 0.0;
    private double esperancaMAX = 0.0;

    // Constructor buit
    public AnimalMamifer() {}

    // Constructor amb els paràmetres
    public AnimalMamifer(int id, String nomComu, String nomCientific, String ordre, String habitat, String alimentacio,
            String carateristicaDistintiva, double pes_mitja, double esperancaMIN, double esperancaMAX) {
        super(id, nomComu, nomCientific, ordre, habitat, alimentacio, carateristicaDistintiva);
        this.pes_mitja = pes_mitja;
        this.esperancaMIN = esperancaMIN;
        this.esperancaMAX = esperancaMAX;
    }

    // Getters i setters

    public double getPes_mitja() {return pes_mitja;}
    public void setPes_mitja(double pes_mitja) {this.pes_mitja = pes_mitja;}

    public double getEsperancaMIN() {return esperancaMIN;}
    public void setEsperancaMIN(double esperancaMIN) {this.esperancaMIN = esperancaMIN;}

    public double getEsperancaMAX() {return esperancaMAX;}
    public void setEsperancaMAX(double esperancaMAX) {this.esperancaMAX = esperancaMAX;}

    @Override
    public String toString() {
        return String.format(
                "\n\t%s (%s), %s" +
                "\n\t%-12s %s" +
                "\n\t%-12s %s" +
                "\n\t%-12s %s",
                getNomComu(), getNomCientific(), getOrdre(),
                "Hàbitat:", cutString(getHabitat()),
                "Alimentació:", cutString(getAlimentacio()),
                "Distinció:", cutString(getCarateristicaDistintiva()),
                "Pes mitjà:", pes_mitja,
                "Vida min:",  esperancaMIN,
                "Vida max:", esperancaMAX
        );
    }
}