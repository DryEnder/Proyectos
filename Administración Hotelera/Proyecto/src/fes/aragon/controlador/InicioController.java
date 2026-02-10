package fes.aragon.controlador;

import static javafx.scene.control.ButtonType.OK;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.modelo.Perfil;
import fes.aragon.modelo.Solicitud;
import fes.aragon.modelo.Solicitudes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InicioController extends BaseController implements Initializable {
	
	@FXML
    private Button btnAbrir;
	
	@FXML
	private Button btnEliminar;
	
	@FXML
    private Button btnModificar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Solicitud, String> clmAreaTrabajo;

    @FXML
    private TableColumn<Solicitud, String> clmEmpresa;

    @FXML
    private TableColumn<Solicitud, String> clmEstudios;

    @FXML
    private TableColumn<Solicitud, Boolean> clmExpLaboral;
    
    @FXML
    private TableColumn<Solicitud, Perfil> clmNombre;

    @FXML
    private TableView<Solicitud> tblTabla;

    @FXML
    void abrirArchivo(ActionEvent event) {
    	try {
			this.abrirArchivo(btnAbrir);
			this.desabilitar(false);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			this.ventanaEmergente("Mensaje", "Problema en el archivo", "Hay un problema en el archivo, consulta al programador");
			e.printStackTrace();
		}
    }
    
    @FXML
    void eliminarArchivo(ActionEvent event) {
    	int indice=this.tblTabla.getSelectionModel().getSelectedIndex();
    	if(indice>=0) {
    		this.tblTabla.getItems().remove(indice);
    	}else {
    		Alert alerta;
    		alerta=new Alert(AlertType.INFORMATION);
    		alerta.setTitle("Dialogo de Aviso");
    		alerta.setHeaderText("Se necesita una fila");
    		alerta.setContentText("Por favor selecciona una fila, para la modificacion");
    		Optional<ButtonType> resultado=alerta.showAndWait();
    		if(resultado.get().equals(OK)) {
    			
    		}
    	}
    }

    @FXML
    void guardarArchivo(ActionEvent event) {
    	try {
			this.guardarArchivo(btnGuardar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.ventanaEmergente("Mensaje", "Problema en el archivo", "Hay un problema en el archivo, consulta al programador");
			e.printStackTrace();
		}
    }
    
    @FXML
    void modificarArchivo(ActionEvent event) {
    	int indice=this.tblTabla.getSelectionModel().getSelectedIndex();
    	if(indice>=0) {
    		Solicitudes.getInstancia().setModificarSolicitud(true);
    		Solicitudes.getInstancia().setIndice(indice);
    		this.nuevaVentana("Solicitud");
    	}else {
    		Alert alerta;
    		alerta=new Alert(AlertType.INFORMATION);
    		alerta.setTitle("Dialogo de Aviso");
    		alerta.setHeaderText("Se necesita una fila");
    		alerta.setContentText("Por favor selecciona una fila, para la modificacion");
    		Optional<ButtonType> resultado=alerta.showAndWait();
    		if(resultado.get().equals(OK)) {
    			
    		}
    	}
    }

    @FXML
    void nuevoArchivo(ActionEvent event) {
    	Solicitudes.getInstancia().setIndice(-1);
    	Solicitudes.getInstancia().setModificarSolicitud(false);
    	Solicitudes.getInstancia().getGrupoSolicitudes().add(new Solicitud());
    	this.nuevaVentana("Solicitud");
    	this.desabilitar(false);
    }

    @FXML
    void salir(ActionEvent event) {
    	Platform.exit();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.clmAreaTrabajo.setCellValueFactory(new PropertyValueFactory<>("areatrabajo"));
		this.clmEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
		this.clmEstudios.setCellValueFactory(new PropertyValueFactory<>("maxestudios"));
		this.clmExpLaboral.setCellValueFactory(new PropertyValueFactory<>("explaboral"));
		this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("perfil"));
		this.tblTabla.setItems(Solicitudes.getInstancia().getGrupoSolicitudes());
		this.desabilitar(true);
	}
	
	private void desabilitar(boolean valor) {
		this.btnGuardar.setDisable(valor);
		this.btnModificar.setDisable(valor);
		this.btnEliminar.setDisable(valor);
	}
	
	
}
