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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author FAMSA
 */
public class FXMLEliminarEventoController implements Initializable {
    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;
    
    private int Id;
    
    @FXML
    private TableView<Evento> tabla;
    @FXML
    private TableColumn<Evento, String> ColumNombre;
    @FXML
    private TableColumn<Evento, String> ColumFecha;
    private ObservableList<Evento> data = FXCollections.observableArrayList();
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            actualizarTabla();
        });
    }    

    @FXML
    private void Eliminar(ActionEvent event) {
        if (tabla.getSelectionModel().getSelectedItem() != null) {
            try {
                //Aqui es cuando lo borra
                EventoDAOImplementacion eventoDAO = new EventoDAOImplementacion();
                eventoDAO.remove(tabla.getSelectionModel().getSelectedItem().getId());
                JOptionPane.showMessageDialog(null, "Evento eliminado exitosamente");
                //Esto es de para cerra la ventana, codigo extra
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
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un evento");
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) throws IOException {
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
    
    private void actualizarTabla() {
        try {
            recuperarUsuario();
            String sql = "SELECT * FROM eventos WHERE usuario='" + rs.getString("Usuario") + "'";
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Evento evento = new Evento(rs.getString("Nombre"), rs.getString("Contacto"), rs.getString("Fecha"), rs.getString("Hora"),
                        rs.getString("Lugar"), rs.getString("Descripcion"), rs.getString("Usuario"));
                evento.setId(rs.getInt("Id"));
                evento.setFecha(rs.getString("Fecha"));
                data.add(evento);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ColumNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tabla.setItems(data);
    }
    
    private void recuperarUsuario() {
        try {
            String sql = "SELECT * FROM usuarios WHERE Id=" + this.Id;
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
