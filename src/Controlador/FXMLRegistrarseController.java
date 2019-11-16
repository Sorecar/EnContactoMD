package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAOImplementacion;
import java.io.IOException;
import java.net.URL;
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

public class FXMLRegistrarseController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private PasswordField tfContraseña;
    @FXML
    private TextField tfTelefono;
    @FXML
    private PasswordField tfMascota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Guardar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String contraseña = tfContraseña.getText();
        String telefono = tfTelefono.getText();
        String mascota = tfMascota.getText();
        if (!tfNombre.getText().isEmpty() || !tfContraseña.getText().isEmpty() || !tfTelefono.getText().isEmpty() || !tfMascota.getText().isEmpty()) {
            Usuario usuario = new Usuario(nombre, contraseña, telefono, mascota);
            UsuarioDAOImplementacion usuarioDAO = new UsuarioDAOImplementacion();
            try {
                usuarioDAO.create(usuario);
                System.out.println("Usuario registrado con exito");
                regresarVentana();
                Stage mainWindow;
                mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainWindow.close();
            } catch (Exception e) {
                System.out.println("bt Guardar RUC");
                System.out.println("Error en crear al nuevo usuario");
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
            System.out.println("Boton Cancelar RUC");
            System.out.println(i);
        }
    }

    private void regresarVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLLogin.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - INICIO DE SESION");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void guardar(ActionEvent event) {
    }
}
