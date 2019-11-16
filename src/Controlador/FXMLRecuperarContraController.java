package Controlador;

import Modelo.BaseConexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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

public class FXMLRecuperarContraController implements Initializable {

    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;
    @FXML
    private TextField TfUsuario;
    @FXML
    private PasswordField TfMascota;
    @FXML
    private PasswordField TfTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void BtEnviarContra(ActionEvent event) {
        if (!TfUsuario.getText().isEmpty() ||  !TfTelefono.getText().isEmpty() || !TfMascota.getText().isEmpty()) {
            validar(TfUsuario.getText(), TfTelefono.getText() ,TfMascota.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
        }
    }

    private boolean validar(String usuario, String telefono, String mascota) {
        String sql = "SELECT Contraseña FROM usuarios WHERE Usuario='" + usuario + "' && NumTelefono='" + telefono + "' && NomMascota='" + mascota + "'";
        try {
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Tu Contraseña es " + rs.getString("Contraseña"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLLogin.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("EN CONTACTO - INICIO DE SESION");
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
