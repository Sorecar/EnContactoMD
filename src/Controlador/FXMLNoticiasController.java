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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLNoticiasController implements Initializable {

    private int medida = 0;
    String sql = "SELECT * FROM noticia ORDER BY Fecha DESC";
    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    @FXML
    private AnchorPane principal;

    private int Id;
    @FXML
    private ComboBox<String> combo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            ObservableList<String> opciones = FXCollections.observableArrayList("Todos", "Ultima hora", "Ultimas 24 horas", "Ultimos 7 dias");
            combo.getItems().addAll(opciones);
            actualizarNoticias();

        });
    }

    @FXML
    private void AgregarNoticia(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLAgregarNoticia.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLAgregarNoticiaController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - AGREGAR NOTICIA");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @FXML
    private void EditarNoticia(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLEditarNoticia.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLEditarNoticiaController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EDITAR NOTICIA");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @FXML
    private void EliminarNoticia(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLEliminarNoticia.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLEliminarNoticiaController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - ELIMINAR NOTICIA");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
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

    @FXML
    private void Inicio(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLMenuPrincipal.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLMenuPrincipalController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - MENU PRINCIPAL");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    private void regresarVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLLogin.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - INICIO DE SESION");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void actualizarNoticias() {
        principal.getChildren().clear();
        medida = 0;
        try {
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                //Se hace mas grande el panel principal
                principal.setPrefSize(615, principal.getHeight() + 150);
                //Se crea el siguiente panel y se agrega al panel principal
                Pane p = new Pane();
                principal.getChildren().add(p);
                p.setPrefSize(615, 160);
                p.setLayoutY(medida);
                //Creamos la imagen y se agrega a p que es el panel donde estara
                ImageView iv = new ImageView("/Imagenes/userr.png");
                iv.setFitWidth(50);
                iv.setFitHeight(50);
                p.getChildren().add(iv);
                iv.setLayoutX(25);
                iv.setLayoutY(25);
                //Creamos el nombre de usuario
                Text tn = new Text(rs.getString("Usuario"));
                tn.setLayoutX(25);
                tn.setLayoutY(125);

                Text post = new Text(rs.getString("Noticia"));
                post.setLayoutX(120);
                post.setLayoutY(30);
                post.setWrappingWidth(460);

                Text fecha = new Text(rs.getString("Fecha"));
                fecha.setLayoutX(480);
                fecha.setLayoutY(125);
                p.getChildren().addAll(tn, post, fecha);

                Separator sp = new Separator(Orientation.HORIZONTAL);
                sp.setPrefSize(615, 10);
                sp.setLayoutY(medida + 150);
                principal.getChildren().add(sp);

                medida = medida + 160;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void RecFiltro(ActionEvent event) {
        String copcion = combo.getSelectionModel().getSelectedItem();
        principal.getChildren().clear();
        principal.setPrefSize(0, 0);
        principal.autosize();
        if (copcion == "Todos") {
            sql = "SELECT * FROM noticia ORDER BY Fecha DESC";
        } else if (copcion == "Ultima hora") {
            sql = "SELECT * FROM noticia WHERE Fecha between DATE_SUB(CONCAT(CURDATE(), ' ',CURTIME()), INTERVAL 1 HOUR) AND now() ORDER BY Fecha DESC;";
        } else if (copcion == "Ultimas 24 horas") {
            sql = "SELECT * FROM noticia WHERE Fecha between DATE_SUB(CONCAT(CURDATE(), ' ',CURTIME()), INTERVAL 1 DAY) AND now() ORDER BY Fecha DESC;";
        } else if (copcion == "Ultimos 7 dias") {
            sql = "SELECT * FROM noticia WHERE Fecha between DATE_SUB(CONCAT(CURDATE(), ' ',CURTIME()), INTERVAL 7 DAY) AND now() ORDER BY Fecha DESC;";
        }
        actualizarNoticias();
    }
}
