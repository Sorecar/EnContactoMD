package Modelo;

public class Noticia {

    private int Id;
    private String Usuario;
    private String Noticia;
    private String Fecha;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Noticia(String Usuario, String Noticia) {
        this.Usuario = Usuario;
        this.Noticia = Noticia;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getNoticia() {
        return Noticia;
    }

    public void setNoticia(String noticia) {
        this.Noticia = noticia;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

}
