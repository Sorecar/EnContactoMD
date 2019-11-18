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
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLNoticiasController implements Initializable {

    private int medida = 0;

    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;

    @FXML
    private AnchorPane principal;

    private int Id;

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
        actualizarNoticias();
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
        //FXMLEliminarNoticiaController controller = fxmlLoader.getController();
        //controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EDITAR NOTICIA");
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
        FXMLMenuPrincipalController controller = fxmlLoader.getController();
        controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - INICIO DE SESION");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void actualizarNoticias() {
        String sql = "SELECT * FROM noticia ORDER BY Fecha DESC";
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
                ImageView iv = new ImageView("/Imagenes/User.png");
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

}
