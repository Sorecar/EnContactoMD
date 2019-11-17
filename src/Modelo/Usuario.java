package Modelo;

public class Usuario {

    private int Id;
    private String Usuario;
    private String Contraseña;
    private String NumTelefono;
    private String NomMascota;
    private String Estatus;

    public Usuario(String usuario, String contraseña, String numTel, String nomMascota) {
        Usuario = usuario;
        Contraseña = contraseña;
        NumTelefono = numTel;
        NomMascota = nomMascota;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;

    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        NumTelefono = numTelefono;

    }

    public String getNomMascota() {
        return NomMascota;

    }

    public void setNomMascota(String nomMascota) {
        NomMascota = nomMascota;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    
}
