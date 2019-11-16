package Modelo;

public class Noticia {
    
    private String Usuario;
    private String Noticia;
    private String HoraNoticia;
    private String FechaNoticia;

    public Noticia(String Usuario, String Noticia, String HoraNoticia, String FechaNoticia) {
        this.Usuario = Usuario;
        this.Noticia = Noticia;
        this.HoraNoticia = HoraNoticia;
        this.FechaNoticia = FechaNoticia;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNoticia() {
        return Noticia;
    }

    public void setNoticia(String Noticia) {
        this.Noticia = Noticia;
    }

    public String getHoraNoticia() {
        return HoraNoticia;
    }

    public void setHoraNoticia(String HoraNoticia) {
        this.HoraNoticia = HoraNoticia;
    }

    public String getFechaNoticia() {
        return FechaNoticia;
    }

    public void setFechaNoticia(String FechaNoticia) {
        this.FechaNoticia = FechaNoticia;
    }
    
}
