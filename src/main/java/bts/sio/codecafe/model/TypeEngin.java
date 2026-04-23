package bts.sio.codecafe.model;

/**
 * Author: JoackimV
 * Created: 23/04/2026 14:30
 * Last modified: 23/04/2026 14:30
 */
public class TypeEngin {
    private int id;
    private String libelle;
    private int archive;

    public TypeEngin() {
    }

    public TypeEngin(int id) {
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
