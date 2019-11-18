package Modelo;

public class Usuario {

    private int Id;
    private String Usuario;
    private String Contraseña;
    private String Telefono;
    private String Mascota;
    private String Estatus;

    public Usuario(String usuario, String contraseña, String telefono, String mascota) {
        Usuario = usuario;
        Contraseña = contraseña;
        Telefono = telefono;
        Mascota = mascota;
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;

    }

    public String getMascota() {
        return Mascota;

    }

    public void setMascota(String mascota) {
        Mascota = mascota;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    
}
