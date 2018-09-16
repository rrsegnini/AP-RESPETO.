package cr.ac.tec.ec.respeto;

import android.location.Location;

public class Lugar {

    private String id;
    private Location ubicacion;

    public Lugar(String id, Location ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Location ubicacion) {
        this.ubicacion = ubicacion;
    }
}
