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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLMenuPrincipalController implements Initializable {

    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    private int Id;

    @FXML
    private Text user;

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
        Platform.runLater(()->{
            user.setText("Hola " + recuperarUsuario(Id));
        });
    }

    @FXML
    private void EditarPerfil(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLEditarPerfil.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLEditarPerfilController controller = fxmlLoader.getController();
        controller.setId(this.getId());
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EDITAR PERFIL");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @FXML
    private void Noticias(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLNoticias.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLNoticiasController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - NOTICIAS");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @FXML
    private void Eventos(MouseEvent event) throws IOException {
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

    @FXML
    private void CerrarSesion(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLLogin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("ENCONTACTO - INICIO DE SESION");
            stage.setScene(new Scene(root1));
            stage.show();
            Stage mainWindow;
            mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainWindow.close();
        } catch (IOException i) {
            System.out.println("Boton Cancelar RUC");
            System.out.println(i);
        }
    }

    private String recuperarUsuario(int id) {
        String sql = "SELECT * FROM usuarios WHERE id=" + id;
        try {
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("Usuario");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

}