package Controlador;

import Modelo.Evento;
import Modelo.EventoDAOImplementacion;
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

public class FXMLAgregarEventoController implements Initializable {

    @FXML
    private TextArea EventoDes;
    @FXML
    private TextField ConEvento;
    @FXML
    private TextField FechaE;
    @FXML
    private TextField HoraE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Guardar(ActionEvent event) {
        String ConInfo = ConEvento.getText();
        String FechaEvento = FechaE.getText();
        String HoraEvento = HoraE.getText();
        String Evento = EventoDes.getText();
        if (!ConEvento.getText().isEmpty() || !FechaE.getText().isEmpty() || !HoraE.getText().isEmpty() || !EventoDes.getText().isEmpty()) {
            Evento evento = new Evento(ConInfo, FechaEvento, HoraEvento, Evento);
            EventoDAOImplementacion eventoDAO = new EventoDAOImplementacion();
            try {
                eventoDAO.create(evento);
                System.out.println("Evento publicado con exito");
                regresarVentana();
                Stage mainWindow;
                mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainWindow.close();
            } catch (Exception e) {
                System.out.println("bt Guardar RUC");
                System.out.println("Error Evento NO Publicado");
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/FXMLEventos.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - EVENTOS");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
