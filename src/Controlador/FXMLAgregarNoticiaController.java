package Controlador;

import Modelo.BaseConexion;
import Modelo.Noticia;
import Modelo.NoticiaDAOImplementacion;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAgregarNoticiaController implements Initializable {

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
    private TextArea NoticiaD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Guardar(ActionEvent event) {
        try{
        if (!NoticiaD.getText().isEmpty()) {
            recuperarUsuario();
            Noticia noticia = new Noticia(rs.getString("Usuario"), NoticiaD.getText());
            NoticiaDAOImplementacion noticiaDAO = new NoticiaDAOImplementacion();
            try {
                noticiaDAO.create(noticia);
                System.out.println("Noticia publicada con exito");
                regresarVentana();
                Stage mainWindow;
                mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainWindow.close();
            } catch (Exception e) {
                System.out.println("bt Guardar RUC");
                System.out.println("Error Noticia NO Publicada");
            }
        } else {
            System.out.println("Algun campo esta vacio");
        }
        }catch(SQLException e){
            System.out.println(e);
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLNoticias.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - NOTICIAS");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void recuperarUsuario() {
        try {
            String sql = "SELECT * FROM usuarios WHERE Id=" + this.Id;
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                System.out.println("Agregar Noticia usuario:"+ rs.getString("Usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
