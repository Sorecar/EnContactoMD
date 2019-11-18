package Controlador;

import Modelo.BaseConexion;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLAdmiController implements Initializable {
    private int Id;
    @FXML
    private TableColumn<Usuario, String> ColumUsuario;
    @FXML
    private TableColumn<Usuario, String> ColumContra;
    @FXML
    private TableColumn<Usuario, String> ColumTelefono;
    @FXML
    private TableColumn<Usuario, String> ColumMascota;
    @FXML
    private TableColumn<Usuario, String> ColumEstado;
    @FXML
    private TableView<Usuario> tablausuarios;
    private ObservableList <Usuario> data = FXCollections.observableArrayList();
    @FXML
    private Button BtnEditar;
    @FXML
    private TextField TextEstatus;
    @FXML
    private Button BtnGuardar;
    @FXML
    private TextField TextUsuario;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            DatosTabla();
        });
    }
    
    BaseConexion conn = new BaseConexion();
    Connection con = conn.getConexion();
    Statement ps = null;
    ResultSet rs = null;
    
    private void DatosTabla(){
        String sql = "SELECT * FROM usuarios";
        try {
            this.con = this.conn.getConexion();
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            System.out.println("Si se pudo");
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getString("Usuario"), rs.getString("Contraseña"), rs.getString("Telefono"), rs.getString("Mascota"));
                usuario.setEstatus(rs.getString("Estatus"));
                data.add(usuario);
                System.out.println("SI");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ColumUsuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        this.ColumContra.setCellValueFactory(new PropertyValueFactory<>("Contraseña"));
        this.ColumTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        this.ColumMascota.setCellValueFactory(new PropertyValueFactory<>("Mascota"));
        this.ColumEstado.setCellValueFactory(new PropertyValueFactory<>("Estatus"));
        this.tablausuarios.setItems(data);
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
        //FXMLMenuPrincipalController controller = fxmlLoader.getController();
        //controller.setId(this.Id);
        Stage stage = new Stage();
        stage.setTitle("ENCONTACTO - INICIO DE SESION");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void modificar(MouseEvent event) {
        if(this.tablausuarios.getSelectionModel().getSelectedItem() != null){
                
                this.BtnEditar.setDisable(false);
                System.out.println(this.getId());
            }
            else
        {
            this.BtnGuardar.setDisable(true);            
        }
    }

    @FXML
    private void Editar(ActionEvent event) throws SQLException {
          
        this.TextUsuario.setText(this.tablausuarios.getSelectionModel().getSelectedItem().getUsuario());
        this.TextEstatus.setText(this.tablausuarios.getSelectionModel().getSelectedItem().getEstatus());
        System.out.println(this.TextUsuario.getText());
        this.BtnEditar.setDisable(true);
        this.BtnGuardar.setDisable(false);
        this.setId(valorid(this.TextUsuario.getText()));
    }

    @FXML
    private void update(ActionEvent event) {
    }
    
    public int valorid(String usuario) throws SQLException{
        try {
            String sql = "SELECT * FROM usuarios WHERE Usuario=" + usuario;
            ps = con.createStatement();
            rs = ps.executeQuery(sql);
            if (rs.next()) {
                return (rs.getInt("Id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs.getInt("Id");
    }
    
}
