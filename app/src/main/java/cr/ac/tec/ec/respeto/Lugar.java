package cr.ac.tec.ec.respeto;

import android.location.Location;

public class Lugar {

    private int id;
    private Location ubicacion;

    public Lugar(int id, Location ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Location ubicacion) {
        this.ubicacion = ubicacion;
    }
}
