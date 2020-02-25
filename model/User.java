package model;

public class User {

    private int id;
    private String nom;
    private String prenom;

    public User() {
        this.nom = "nomDefaut";
        this.prenom = "prenomDefault";
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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
}
