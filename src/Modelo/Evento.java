package Modelo;

public class Evento {
    
    private String Nombre;
    private String Contacto;
    private String Fecha;
    private String Hora;
    private String Lugar;
    private String Descripcion;
    private String Usuario;

    public Evento(String Nombre, String Contacto, String Fecha, String Hora, String Lugar, String Descripcion, String Usuario) {
        this.Nombre = Nombre;
        this.Contacto = Contacto;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Lugar = Lugar;
        this.Descripcion = Descripcion;
        this.Usuario = Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String Contacto) {
        this.Contacto = Contacto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    
    
}
