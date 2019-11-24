/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BaseConexion;
import Modelo.Evento;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author FAMSA
 */
public class FXMLDetallesController implements Initializable {

    @FXML
    private TableView<Evento> tablaeventos;
    @FXML
    private TableColumn<Evento, String> ColumUsuario;
    @FXML
    private TableColumn<Evento, String> ColumnNEvento;
    @FXML
    private TableColumn<Evento, String> ColumFecha;
    
    private ObservableList <Evento> data = FXCollections.observableArrayList();
    
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
            DatosTabla();
        });
    }   
    
    private void DatosTabla(){
        this.tablaeventos.getItems().clear();
        String sql = "SELECT * FROM eventos";
        try {
            this.con = this.conn.getConexion();
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            System.out.println("Si se pudo");
            while(rs.next()){
                Evento evento = new Evento(rs.getString("Nombre"), rs.getString("Contacto"), rs.getString("Fecha") 
                        ,rs.getString("Hora"), rs.getString("Lugar"), rs.getString("Descripcion"), rs.getString("Usuario"));
                data.add(evento);
                System.out.println("SI");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ColumUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        this.ColumnNEvento.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.ColumFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        this.tablaeventos.setItems(data);
    }

    @FXML
    private void Ver(MouseEvent event) throws IOException {
        String nombre = "";
        String usuario = "";
        String fecha = "";
        if(this.tablaeventos.getSelectionModel().getSelectedItem() != null){
            nombre = this.tablaeventos.getSelectionModel().getSelectedItem().getNombre();
            usuario = this.tablaeventos.getSelectionModel().getSelectedItem().getUsuario();
            fecha = this.tablaeventos.getSelectionModel().getSelectedItem().getFecha();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLDetallesEvento.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            FXMLDetallesEventoController controller = fxmlLoader.getController();
            controller.setId(this.Id);
            controller.setNombre(nombre);
            controller.setUsuario(usuario);
            controller.setFecha(fecha);
            Stage stage = new Stage();
            stage.setTitle("ENCONTACTO - EVENTOS");
            stage.setScene(new Scene(root1));
            stage.show();
            Stage mainWindow;
            mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainWindow.close();  
        }else{
                        
        }
    }

    @FXML
    private void Inicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLEventos.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLEventosController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EVENTOS");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }
    
}
