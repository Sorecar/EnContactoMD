package Controlador;

import Modelo.BaseConexion;
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

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfPass;

    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    int idUsuario = 0;
    String estatus;
    String tipoUsuario = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Ingresar(ActionEvent eventI) {
        if (!tfUsuario.getText().isEmpty() || !pfPass.getText().isEmpty()) {
            validar(tfUsuario.getText(), pfPass.getText()); //Aqui se realiza la consulta si es usuario o admin
            if (idUsuario != 0 || tipoUsuario != "") {
                if (tipoUsuario == "Usuario") {
                    if (estatus.equals("Activo")) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLMenuPrincipal.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            FXMLMenuPrincipalController controller = fxmlLoader.getController();
                            controller.setId(idUsuario);
                            Stage stage = new Stage();
                            stage.setTitle("EN CONTACTO");
                            stage.setScene(new Scene(root1));
                            stage.show();
                            Stage mainWindow;
                            mainWindow = (Stage) ((Node) eventI.getSource()).getScene().getWindow();
                            mainWindow.close();
                        } catch (Exception i) {
                            System.out.println(i);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Lo sentimos tu cuenta esta bloqueada");
                    }
                } else if (tipoUsuario == "Admin") {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLAdmi.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        FXMLAdmiController controller = fxmlLoader.getController();
                        controller.setId(idUsuario);
                        Stage stage = new Stage();
                        stage.setTitle("EN CONTACTO");
                        stage.setScene(new Scene(root1));
                        stage.show();
                        Stage mainWindow;
                        mainWindow = (Stage) ((Node) eventI.getSource()).getScene().getWindow();
                        mainWindow.close();
                    } catch (Exception i) {
                        System.out.println(i);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
            }
        }
    }

    @FXML
    private void Registrarse(ActionEvent event
    ) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLRegistrarse.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("ENCONTACTO - REGISTRARSE");
            stage.setScene(new Scene(root1));
            stage.show();
            Stage mainWindow;
            mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainWindow.close();
        } catch (Exception i) {
            System.out.println("Boton Registrarse-Login");
            System.out.println(i);
        }
    }

    @FXML
    private void RecuperarPass(ActionEvent event
    ) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLRecuperarContra.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("ENCONTACTO - RECUPERAR CONTRASEÑA");
            stage.setScene(new Scene(root1));
            stage.show();
            Stage mainWindow;
            mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainWindow.close();
        } catch (Exception i) {
            System.out.println("Boton Recuperar Contraseña-Login");
            System.out.println(i);
        }
    }
    //Comprueba si existe el usuario primero y despues el administrador en sus respectivas bases de datos

    private void validar(String usuario, String contraseña) {
        //Checamos si es usuario
        try {
            String sql = "SELECT * FROM usuarios WHERE Usuario='" + usuario + "' && Contraseña='" + contraseña + "'";
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                idUsuario = rs.getInt("Id");
                tipoUsuario = "Usuario";
                estatus = rs.getString("Estatus");
            } else {
                //Checamos si es administrador ya que en usuario no se encontro
                sql = "SELECT * FROM Administrador WHERE Nombre='" + usuario + "' && Contraseña='" + contraseña + "'";
                ps = con.createStatement();
                rs = ps.executeQuery(sql);
                if (rs.next()) {
                    idUsuario = rs.getInt("Id");
                    tipoUsuario = "Admin";
                }
            }
        } catch (SQLException ex) {
            System.out.println("Validar-Login");
            System.out.println(ex);
        }
    }
}
