/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BaseConexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author FAMSA
 */
public class FXMLDetallesEventoController implements Initializable {

    @FXML
    private TextArea TextNombre;
    @FXML
    private TextArea TextFecha;
    @FXML
    private TextArea TextHora;
    @FXML
    private TextArea TextLugar;
    @FXML
    private TextArea TextDescripcion;
    @FXML
    private TextArea TextContacto;
    
    private int Id;
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    private String Usuario, Nombre, Fecha;
    public String getUsuario(){
        return Usuario ;
    }
    public void setUsuario(String usuario){
        this.Usuario = usuario;
    }
    public String getNombre(){
        return Nombre ;
    }
    public void setNombre(String nombre){
        this.Nombre = nombre;
    }
    public String getFecha(){
        return Fecha ;
    }
    public void setFecha(String fecha){
        this.Fecha = fecha;
    }
    
    
    
    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(()->{
            Datos();
        });
    }    

    @FXML
    private void Inicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLDetalles.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLDetallesController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EVENTOS");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }
    
    private void Datos(){
        String nombre = this.getNombre();
        String usuario = this.getUsuario(); 
        String fecha = this.getFecha();
        System.out.println(nombre);
        System.out.println(usuario);
        try {
            String sql = "SELECT * FROM eventos WHERE Nombre='"+nombre+"' AND Usuario='"+usuario+"' AND Fecha='"+fecha+"'";
            this.con = this.conn.getConexion();
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            if (rs.next()) {    
                TextNombre.setEditable(false);
                TextNombre.setText(rs.getString("Nombre"));
                TextContacto.setEditable(false);
                TextContacto.setText(rs.getString("Contacto"));
                TextFecha.setEditable(false);
                TextFecha.setText(rs.getString("Fecha"));
                TextHora.setEditable(false);
                TextHora.setText(rs.getString("Hora"));
                TextLugar.setEditable(false);
                TextLugar.setText(rs.getString("Lugar"));
                TextDescripcion.setEditable(false);
                TextDescripcion.setText(rs.getString("Descripcion"));
            }
        }catch(SQLException e) {
            System.out.println(e);
        }
    }
    
}
