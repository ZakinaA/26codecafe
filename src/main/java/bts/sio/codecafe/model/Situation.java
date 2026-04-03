package bts.sio.codecafe.model;

/**
 * Author: JoackimV
 * Created: 04/04/2026 00:40
 * Last modified: 04/04/2026 00:40
 */
public class Situation {
    private int id;
    private String libelle;
    private int archive;

    public Situation() {
    }

    public Situation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}
