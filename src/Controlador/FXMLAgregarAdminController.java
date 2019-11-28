/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Admin;
import Modelo.AdminDAOImplementacion;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author FAMSA
 */
public class FXMLAgregarAdminController implements Initializable {

    @FXML
    private Button BtnEditar;
    @FXML
    private TextField TextContra;
    @FXML
    private Button BtnGuardar;
    @FXML
    private TextField TextAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CerrarSesion(MouseEvent event) {
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
    private void Inicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLAdmi.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - PRINCIPAL ADMINISTRADOR");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @FXML
    private void Guardar(ActionEvent event) {
        String nombre = TextAdmin.getText();
        String contraseña = TextContra.getText();
        if (!TextAdmin.getText().isEmpty() && !TextContra.getText().isEmpty()) {
            Admin admin = new Admin(nombre, contraseña);
            AdminDAOImplementacion adminDAO = new AdminDAOImplementacion();
            try {
                adminDAO.create(admin);
                System.out.println("Administrador registrado con exito");
                Principal();
                Stage mainWindow;
                mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainWindow.close();
            } catch (Exception e) {
                System.out.println("bt Guardar RUC");
                System.out.println("Error en crear al nuevo administrador");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
        }
    }
    
    private void Principal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLAdmi.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - PRINCIPAL ADMINISTRADOR");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
