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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLEliminarNoticiaController implements Initializable {

    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    private int Id;
    @FXML
    private TableColumn<Noticia, String> ColumNoticia;
    @FXML
    private TableColumn<Noticia, String> ColumFecha;
    @FXML
    private TableView<Noticia> tabla;
    private ObservableList<Noticia> data = FXCollections.observableArrayList();

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            actualizarTabla();
        });
    }

    @FXML
    private void Eliminar(ActionEvent event) {
        if (tabla.getSelectionModel().getSelectedItem() != null) {
            try {
                //Aqui es cuando lo borra
                NoticiaDAOImplementacion noticiaDAO = new NoticiaDAOImplementacion();
                noticiaDAO.remove(tabla.getSelectionModel().getSelectedItem().getId());
                JOptionPane.showMessageDialog(null, "Publicacion eliminada exitosamente");
                //Esto es de para cerra la ventana, codigo extra
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
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una publicacion");
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) throws IOException {
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

    private void actualizarTabla() {
        try {
            recuperarUsuario();
            String sql = "SELECT * FROM noticia WHERE usuario='" + rs.getString("Usuario") + "'";
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Noticia noticia = new Noticia(rs.getString("usuario"), rs.getString("noticia"));
                noticia.setId(rs.getInt("idnoticia"));
                noticia.setFecha(rs.getString("Fecha"));
                data.add(noticia);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ColumNoticia.setCellValueFactory(new PropertyValueFactory<>("Noticia"));
        ColumFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tabla.setItems(data);
    }

    private void recuperarUsuario() {
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
