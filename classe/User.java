package classe;

public class User {

    private int id;
    private String login;
    private String nom;
    private String prenom;
    private String password;
    private boolean isAdmin;

    public User() {}

    public User(String login, String nom, String prenom, String password, boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(int id, String login, String nom, String prenom, String password, boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
