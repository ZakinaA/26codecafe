package bts.sio.codecafe.model;

import java.time.LocalDate;

public class Pompier {

    private int id;
    private String nom;
    private String prenom;
    private String numeroBip;
    private LocalDate dateNaissance;
    private String indiceTraitement;
    private LocalDate dateObtentionIndice;
    private String statut;
    private Caserne uneCaserne;
    // private Grade unGrade;        
    // private Fonction uneFonction; 

    public Pompier() {
    }

    public Pompier(int id) {
        this.id = id;
    }

    public Pompier(int id, String nom, String prenom, String numeroBip,
            LocalDate dateNaissance, String indiceTraitement,
            LocalDate dateObtentionIndice, String statut,
            Caserne uneCaserne) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroBip = numeroBip;
        this.dateNaissance = dateNaissance;
        this.indiceTraitement = indiceTraitement;
        this.dateObtentionIndice = dateObtentionIndice;
        this.statut = statut;
        this.uneCaserne = uneCaserne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroBip() {
        return numeroBip;
    }

    public void setNumeroBip(String numeroBip) {
        this.numeroBip = numeroBip;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getIndiceTraitement() {
        return indiceTraitement;
    }

    public void setIndiceTraitement(String indiceTraitement) {
        this.indiceTraitement = indiceTraitement;
    }

    public LocalDate getDateObtentionIndice() {
        return dateObtentionIndice;
    }

    public void setDateObtentionIndice(LocalDate dateObtentionIndice) {
        this.dateObtentionIndice = dateObtentionIndice;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Caserne getUneCaserne() {
        return uneCaserne;
    }

    public void setUneCaserne(Caserne uneCaserne) {
        this.uneCaserne = uneCaserne;
    }

    
    // public Grade getUnGrade() { return unGrade; }
    // public void setUnGrade(Grade unGrade) { this.unGrade = unGrade; }
    // public Fonction getUneFonction() { return uneFonction; }
    // public void setUneFonction(Fonction uneFonction) { this.uneFonction = uneFonction; }
}
