package Controlador;

import Modelo.Noticia;
import Modelo.NoticiaDAOImplementacion;
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

    @FXML
    private TextField HoraN;
    @FXML
    private TextField FechaN;
    @FXML
    private TextField UsuarioN;
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
        String Usuario = UsuarioN.getText();
        String Noticia = NoticiaD.getText();
        String HoraNoticia = HoraN.getText();
        String FechaNoticia = FechaN.getText();
        if (!UsuarioN.getText().isEmpty() || !NoticiaD.getText().isEmpty() || !HoraN.getText().isEmpty() || !FechaN.getText().isEmpty()) {
            Noticia noticia = new Noticia(Usuario, Noticia, HoraNoticia, FechaNoticia);
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

}
