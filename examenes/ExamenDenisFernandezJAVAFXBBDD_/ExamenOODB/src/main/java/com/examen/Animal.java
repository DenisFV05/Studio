package com.examen;

public class Animal {

    private int id = -1;
    private String nomComu = "";
    private String nomCientific = "";
    private String ordre = "";
    private String habitat = "";
    private String alimentacio = "";
    private String carateristicaDistintiva = "";

    // Constructor buit
    public Animal() {}

    // TO DO: Iniciar atributs de l'objecte segons els paràmetres rebuts
    // Constructor amb els paràmetres
    public Animal(int id, String nomComu, String nomCientific, String ordre, String habitat, 
    String alimentacio, String carateristicaDistintiva) {
        this.id = id;
        this.nomComu = nomComu;
        this.nomCientific = nomCientific;
        this.ordre = ordre;
        this.habitat = habitat;
        this.alimentacio = alimentacio;
        this.carateristicaDistintiva = carateristicaDistintiva;
    }

    // TO DO 'getters' i 'setters' funcionant correctament
    // Getters i setters

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNomComu() {return nomComu;}
    public void setNomComu(String nomComu) {this.nomComu = nomComu;}

    public String getNomCientific() {return nomCientific;}
    public void setNomCientific(String nomCientific) {this.nomCientific = nomCientific;}

    public String getOrdre() {return ordre;}
    public void setOrdre(String ordre) {this.ordre = ordre;}

    public String getHabitat() {return habitat;}
    public void setHabitat(String habitat) {this.habitat = habitat;}

    public String getAlimentacio() {return alimentacio;}
    public void setAlimentacio(String alimentacio) {this.alimentacio = alimentacio;}

    public String getCarateristicaDistintiva() {return carateristicaDistintiva;}
    public void setCarateristicaDistintiva(String carateristicaDistintiva) {this.carateristicaDistintiva = carateristicaDistintiva;}

    protected String cutString(String value) {
        int MAX_LEN = 50;
        if (value == null) return "";
        return value.length() <= MAX_LEN ? value : value.substring(0, MAX_LEN) + "...";
    }

    @Override
    public String toString() {
        return String.format(
                "\n\t%s (%s), %s" +
                "\n\t%-12s %s" +
                "\n\t%-12s %s" +
                "\n\t%-12s %s",
                nomComu, nomCientific, ordre,
                "Hàbitat:", cutString(habitat),
                "Alimentació:", cutString(alimentacio),
                "Distinció:", cutString(carateristicaDistintiva)
        );
    }
}
