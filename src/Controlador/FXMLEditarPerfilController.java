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

public class FXMLEditarPerfilController implements Initializable {
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
        // TODO
        //Aqui tambien tenemos que poner despues de abrir el par aque se llene automaticamente
    }    

    @FXML
    private void Guardar(ActionEvent event) {
        if (!tfNombre.getText().isEmpty() || !tfContraseña.getText().isEmpty() || !tfTelefono.getText().isEmpty() || !tfMascota.getText().isEmpty()) {
            Usuario usuario = new Usuario(tfNombre.getText(), tfContraseña.getText(), tfTelefono.getText(), tfMascota.getText());
            usuario.setId(Id);
            UsuarioDAOImplementacion usuarioDAO = new UsuarioDAOImplementacion();
            try {
                usuarioDAO.update(usuario);
                System.out.println("Cambios del usuario guardados con exito");
                regresarVentana();
                Stage mainWindow;
                mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainWindow.close();
            } catch (Exception e) {
                System.out.println("Boton Guardar-Editar Perfil");
                System.out.println("Error al guardar los combios del usuario");
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

}
