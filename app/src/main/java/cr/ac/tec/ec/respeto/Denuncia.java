package cr.ac.tec.ec.respeto;

import java.util.ArrayList;
import java.util.Date;

public class Denuncia {
    private int id;
    private String descripcion;
    private int idLugar;
    private Date fechaHora;
    private String idUsuario;


    public Denuncia() {}

    public Denuncia(int id, String descripcion, int idLugar, Date fechaHora, String idUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.idLugar = idLugar;
        this.fechaHora = fechaHora;
        this.idUsuario = idUsuario;
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

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


}
