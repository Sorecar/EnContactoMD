package Modelo;

public class Evento {
    
    private String ConInfo;
    private String FechaEvento;
    private String HoraEvento;
    private String Evento;

    public Evento(String ConInfo, String FechaEvento, String HoraEvento, String Evento) {
        this.ConInfo = ConInfo;
        this.FechaEvento = FechaEvento;
        this.HoraEvento = HoraEvento;
        this.Evento = Evento;
    }

    public String getConInfo() {
        return ConInfo;
    }

    public void setConInfo(String ConInfo) {
        this.ConInfo = ConInfo;
    }

    public String getFechaEvento() {
        return FechaEvento;
    }

    public void setFechaEvento(String FechaEvento) {
        this.FechaEvento = FechaEvento;
    }

    public String getHoraEvento() {
        return HoraEvento;
    }

    public void setHoraEvento(String HoraEvento) {
        this.HoraEvento = HoraEvento;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String Evento) {
        this.Evento = Evento;
    }
    
}
