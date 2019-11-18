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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLEditarNoticiaController implements Initializable {

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
        try {
            if (!NoticiaD.getText().isEmpty()) {
                Noticia noticia = new Noticia(rs.getString("Usuario"), NoticiaD.getText());
                noticia.setId(rs.getInt("idNoticia"));
                NoticiaDAOImplementacion noticiaDAO = new NoticiaDAOImplementacion();
                try {
                    noticiaDAO.update(noticia);
                    JOptionPane.showMessageDialog(null, "Noticia editada con exito");
                    
                    regresarVentana();
                    Stage mainWindow;
                    mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    mainWindow.close();
                } catch (Exception e) {
                    System.out.println("bt Guardar RUC");
                    System.out.println("Error Noticia NO Editada");
                }
            } else {
                System.out.println("No puede dejar la noticia vacia");
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("No se pudo actualizar");
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
        FXMLNoticiasController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - NOTICIAS");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void recuperarUsuario() {
        try {
            String sql = "SELECT * FROM usuarios WHERE Id=" + this.Id;
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                System.out.println("El usuario actual es: " + rs.getString("Usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error aqui 1");
        }
    }

    @FXML
    private void RecuperarNoticia(MouseEvent event) {
        recuperarUsuario();
        try {
            String sql = "SELECT * FROM noticia WHERE usuario='" + rs.getString("Usuario") + "' ORDER BY idnoticia DESC";
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if(rs.next()){
                System.out.println(rs.getString("Noticia"));
                NoticiaD.setText(rs.getString("Noticia"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error aca");
        }
    }
}
