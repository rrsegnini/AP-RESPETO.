package cr.ac.tec.ec.respeto;

import java.util.Date;

public class Comentario {
    private int id;
    private String idDenuncia;
    private String cuerpo;
    private int idUsuario;
    private Date fechaHora;



    public Comentario() {
    }

    public Comentario(int id, String idDenuncia, String cuerpo, int idUsuario, Date fechaHora) {
        this.id = id;
        this.idDenuncia = idDenuncia;
        this.cuerpo = cuerpo;
        this.idUsuario = idUsuario;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }


    public String getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(String idDenuncia) {
        this.idDenuncia = idDenuncia;
    }
}
