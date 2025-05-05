package com.exercici0600;

public class Personatge {
    private String nom;
    private String activitat;
    private String dataNaixement;
    private String descripcio;
    private String rutaFoto;

    public Personatge() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getActivitat() { return activitat; }
    public void setActivitat(String activitat) { this.activitat = activitat; }

    public String getDataNaixement() { return dataNaixement; }
    public void setDataNaixement(String dataNaixement) { this.dataNaixement = dataNaixement; }

    public String getDescripcio() { return descripcio; }
    public void setDescripcio(String descripcio) { this.descripcio = descripcio; }

    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }
} 