package bts.sio.codecafe.model;

import java.time.LocalTime;

/**
 * Author: JoackimV
 * Created: 02/04/2026 18:33
 * Last modified: 02/04/2026 18:33
 */
public class Intervention {
    private int id;
    private String rue;
    private String copos;
    private String ville;
    private LocalTime heureAppel;
    private LocalTime heureArrivee;
    private int duree;
    private int archive;

    public Intervention() {
    }

    public Intervention(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCopos() {
        return copos;
    }

    public void setCopos(String copos) {
        this.copos = copos;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public LocalTime getHeureAppel() {
        return heureAppel;
    }

    public void setHeureAppel(LocalTime heureAppel) {
        this.heureAppel = heureAppel;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}
