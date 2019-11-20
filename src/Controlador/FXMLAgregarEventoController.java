package Controlador;

import Modelo.BaseConexion;
import Modelo.Evento;
import Modelo.EventoDAOImplementacion;
import Modelo.Noticia;
import Modelo.NoticiaDAOImplementacion;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLAgregarEventoController implements Initializable {
    
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
    
    @FXML
    private TextArea TextLugar;
    @FXML
    private TextArea TextHora;
    @FXML
    private TextArea TextNombre;
    @FXML
    private TextArea TextFecha;
    @FXML
    private TextArea TextContacto;
    @FXML
    private TextArea TextDescripcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {

        });
    }    

    @FXML
    private void Guardar(ActionEvent event){
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
        FXMLNoticiasController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EVENTOSLuis");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void recuperarUsuario() {
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
