package cr.ac.tec.ec.respeto;

import java.util.Date;

public class Denuncia {
    private int id;
    private String descripcion;
    private int idLugar;
    private Date fechaHora;
    private int idUsuario;
    private int idComentario;

    public Denuncia() {}

    public Denuncia(int id, String descripcion, int idLugar, Date fechaHora, int idUsuario, int idComentario) {
        this.id = id;
        this.descripcion = descripcion;
        this.idLugar = idLugar;
        this.fechaHora = fechaHora;
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
}
