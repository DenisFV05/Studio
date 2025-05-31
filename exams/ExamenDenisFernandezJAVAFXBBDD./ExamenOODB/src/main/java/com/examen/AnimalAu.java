package com.examen;

public class AnimalAu extends Animal {

    private double envergadura = 0.0;
    private String migratoria = "";

    // Constructor buit
    public AnimalAu() {}

    // TO DO: Iniciar atributs de l'objecte segons els paràmetres rebuts
    // Constructor amb els paràmetres
    public AnimalAu(int id, String nomComu, String nomCientific, String ordre, String habitat, String alimentacio,
            String carateristicaDistintiva, double envergadura, String migratoria) {
        super(id, nomComu, nomCientific, ordre, habitat, alimentacio, carateristicaDistintiva);
        this.envergadura = envergadura;
        this.migratoria = migratoria;
    }

    // TO DO 'getters' i 'setters' funcionant correctament
    // Getters i setters
    
    public double getEnvergadura() {return envergadura;}
    public void setEnvergadura(double envergadura) {this.envergadura = envergadura;}

    public String getMigratoria() {return migratoria;}
    public void setMigratoria(String migratoria) {this.migratoria = migratoria;}
    
    // TO DO retornar la cadena segons la sortida esperada
    
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
                "Envergadura:", envergadura,
                "Migratoria:", migratoria
        );
    }
}
