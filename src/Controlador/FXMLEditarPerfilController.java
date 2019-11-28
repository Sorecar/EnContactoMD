package Controlador;

import Modelo.BaseConexion;
import Modelo.Usuario;
import Modelo.UsuarioDAOImplementacion;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLEditarPerfilController implements Initializable {

    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @FXML
    private TextField tfNombre;
    @FXML
    private PasswordField tfContraseña;
    @FXML
    private TextField tfTelefono;
    @FXML
    private PasswordField tfMascota;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            llenarText();
        });
    }

    @FXML
    private void Guardar(ActionEvent event) {
        if (!tfNombre.getText().isEmpty() || !tfContraseña.getText().isEmpty() || !tfTelefono.getText().isEmpty() || !tfMascota.getText().isEmpty()) {
            Usuario usuario = new Usuario(tfNombre.getText(), tfContraseña.getText(), tfTelefono.getText(), tfMascota.getText());
            usuario.setId(Id);
            UsuarioDAOImplementacion usuarioDAO = new UsuarioDAOImplementacion();
            try {
                usuarioDAO.update(usuario);
                JOptionPane.showMessageDialog(null, "Se actualizaron los datos");
                regresarVentana();
                Stage mainWindow;
                mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainWindow.close();
            } catch (Exception e) {
                System.out.println("Boton Guardar-Editar Perfil");
                System.out.println("Error al guardar los combios del usuario");
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
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
            System.out.println("Boton Cancelar-Editar Perfil");
            System.out.println(i);
        }
    }

    private void regresarVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLMenuPrincipal.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLMenuPrincipalController controller = fxmlLoader.getController();
        controller.setId(this.getId());
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - MENU PRINCIPAL");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    @FXML
    public void llenarText() {
        try {
            String sql = "SELECT * FROM usuarios WHERE Id=" + this.Id;
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                tfNombre.setText(rs.getString("Usuario"));
                tfContraseña.setText(rs.getString("Contraseña"));
                tfTelefono.setText(rs.getString("Telefono"));
                tfMascota.setText(rs.getString("Mascota"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
