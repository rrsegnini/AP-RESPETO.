package cr.ac.tec.ec.respeto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Denuncia {
    private int id;
    private String descripcion;
    private String idLugar;
    private long fechaHora;
    private String idUsuario;
    private String nombreUsuario;



    public Denuncia() {}

    public Denuncia(String descripcion, String idLugar, long fechaHora, String idUsuario, String nombreUsuario) {
        this.descripcion = descripcion;
        this.idLugar = idLugar;
        this.fechaHora = fechaHora;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(String idLugar) {
        this.idLugar = idLugar;
    }

    public long getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(long fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getFechaHoraString() {
        Date d = new Date(fechaHora);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(d);
        return strDate;

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
