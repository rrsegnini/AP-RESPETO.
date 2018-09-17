package cr.ac.tec.ec.respeto;

public class Usuario {
    private String id;
    private int cedula;
    private String nombreCompleto;
    private Genero gender;
    private String genero;
    private int edad;
    private String alias;
    private String email;
    private String contrasenna;

    public Usuario() {
    }

    public Usuario(String id, int cedula, String nombreCompleto, Genero genero, int edad, String alias, String email) {
        this.id = id;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.gender = genero;
        this.edad = edad;
        this.alias = alias;
        this.email = email;
    }

    public Usuario(String id, int cedula, String nombreCompleto, Genero genero, int edad, String alias, String email, String contrasenna) {
        this.id = id;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.gender = genero;
        this.edad = edad;
        this.alias = alias;
        this.email = email;
        this.contrasenna = contrasenna;
    }

    //creado para utilizarlo en la base
    public Usuario(String id,int cedula, String nombreCompleto, String genero, int edad, String alias, String email) {
        this.id = id;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.genero = genero;
        this.edad = edad;
        this.alias = alias;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        if (this.alias != "") {
            return this.nombreCompleto;
        }
        return this.alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    // Generate a reference to a new location and add some data using push()
    var newPostRef = postsRef.push({
      author: "gracehop",
      title: "Announcing COBOL, a New Programming Language"
    });
    // Get the unique ID generated by push() by accessing its key
    var postID = newPostRef.key;


    */
}
