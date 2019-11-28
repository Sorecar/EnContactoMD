/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BaseConexion;
import Modelo.Evento;
import Modelo.EventoDAOImplementacion;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author FAMSA
 */
public class FXMLEditarEventoController implements Initializable {

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
            RecuperarEvento();
        });
    }    

    @FXML
    private void Guardar(ActionEvent event) {
        try {
            if (!TextNombre.getText().isEmpty() && !TextContacto.getText().isEmpty()) {
                Evento evento = new Evento(TextNombre.getText(), TextContacto.getText(), TextFecha.getText(), TextHora.getText(),
                        TextLugar.getText(), TextDescripcion.getText(), rs.getString("Usuario"));
                EventoDAOImplementacion eventoDAO = new EventoDAOImplementacion();
                evento.setId(rs.getInt("Id"));
                try {
                    eventoDAO.update(evento);
                    JOptionPane.showMessageDialog(null, "Evento editado con exito");
                    
                    regresarVentana();
                    Stage mainWindow;
                    mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    mainWindow.close();
                } catch (Exception e) {
                    System.out.println("bt Guardar RUC");
                    System.out.println("Error Evento NO Editado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos que se solicitan");
                System.out.println("No puede dejar el evento vacio");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("No se pudo actualizar");
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        try {
            regresarVentana();
            Stage mainWindow;
            mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainWindow.close();
        } catch (IOException i) {
            System.out.println("Boton Cancelar RUC");
            System.out.println(i);
        }
    }
    
    private void regresarVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLEventos.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLEventosController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EVENTOS");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    private void recuperarUsuario() {
        try {
            String sql = "SELECT * FROM usuarios WHERE Id=" + this.Id;
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                System.out.println("El usuario actual es: " + rs.getString("Usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error aqui 1");
        }
    }
    
    private void RecuperarEvento() {
        recuperarUsuario();
        try {
            String sql = "SELECT * FROM eventos WHERE usuario='" + rs.getString("Usuario") + "' ORDER BY Id DESC";
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                System.out.println(rs.getString("Nombre"));
                TextNombre.setText(rs.getString("Nombre"));
                TextContacto.setText(rs.getString("Contacto"));
                TextFecha.setText(rs.getString("Fecha"));
                TextHora.setText(rs.getString("Hora"));
                TextLugar.setText(rs.getString("Lugar"));
                TextDescripcion.setText(rs.getString("Descripcion"));  
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error aca");
        }
    }
    
}
