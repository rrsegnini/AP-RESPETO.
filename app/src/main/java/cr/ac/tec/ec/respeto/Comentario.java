package cr.ac.tec.ec.respeto;

import java.util.Date;

public class Comentario {
    private String id;
    private String idDenuncia;
    private String cuerpo;
    private String idUsuario;
    private Date fechaHora;


    public Comentario() {
    }

    public Comentario(String id, String idDenuncia, String cuerpo, String idUsuario, Date fechaHora) {
        this.id = id;
        this.idDenuncia = idDenuncia;
        this.cuerpo = cuerpo;
        this.idUsuario = idUsuario;
        this.fechaHora = fechaHora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
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
