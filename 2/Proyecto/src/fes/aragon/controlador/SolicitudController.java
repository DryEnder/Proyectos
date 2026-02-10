package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Solicitud;
import fes.aragon.modelo.Solicitudes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SolicitudController extends BaseController implements Initializable{
	private Solicitud solicitud;
	private String mensajes="";
	
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnDomicilio;

    @FXML
    private Button btnPerfil;
    
    @FXML
    private ChoiceBox<String> chcEmpresa;

    @FXML
    private CheckBox chkExperienciaLaboral;

    @FXML
    private TextField txtAreaTrabajo;

    @FXML
    private TextField txtNivelEstudio;

    @FXML
    void cancelarSolicitud(ActionEvent event) {
    	this.cerrarVentana(btnCancelar);
    }

    @FXML
    void nuevoDomicilio(ActionEvent event) {
    	
    	this.nuevaVentana("Domicilio");
    }

    @FXML
    void nuevoPerfil(ActionEvent event) {
    	
    	this.nuevaVentana("Perfil");
    }

    @FXML
    void nuevoSolicitud(ActionEvent event) {
    	if(this.verificar()) {
    	solicitud.setAreatrabajo(this.txtAreaTrabajo.getText());
    	solicitud.setMaxestudios(this.txtNivelEstudio.getText());
    	solicitud.setExplaboral(this.chkExperienciaLaboral.isSelected());
    	solicitud.setEmpresa(this.chcEmpresa.getValue());
    	if(Solicitudes.getInstancia().isModificarSolicitud()) {
    		Solicitudes.getInstancia().getGrupoSolicitudes().set(
    				Solicitudes.getInstancia().getIndice(), solicitud);
    		Solicitudes.getInstancia().setIndice(-1);
    		Solicitudes.getInstancia().setModificarSolicitud(false);
    	}else {
    		Solicitudes.getInstancia().getGrupoSolicitudes().set
    			(Solicitudes.getInstancia().getGrupoSolicitudes().size()-1, solicitud);
    	}
    	this.cerrarVentana(btnCancelar);
    	}else {
    		this.ventanaEmergente("Mensaje", "Datos incorrectos", this.mensajes);
    		this.mensajes="";
    	}
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.chcEmpresa.getItems().addAll("Selecciona una Empresa","Refrescos","Muebleria","Electronicos","Dulceria");
		this.chcEmpresa.getSelectionModel().select(0);
		
		if(Solicitudes.getInstancia().isModificarSolicitud()) {
			this.solicitud=Solicitudes.getInstancia().getGrupoSolicitudes().get(
					Solicitudes.getInstancia().getIndice());
			this.txtAreaTrabajo.setText(solicitud.getAreatrabajo());
			this.txtNivelEstudio.setText(solicitud.getMaxestudios());
			this.chkExperienciaLaboral.setSelected(solicitud.isExplaboral());
			this.chcEmpresa.setValue(solicitud.getEmpresa());
		}else {
			solicitud=Solicitudes.getInstancia().getGrupoSolicitudes().get(
					Solicitudes.getInstancia().getGrupoSolicitudes().size()-1);
		}
		
	}
	
	private boolean verificar() {
		boolean valido = true;
		if ((this.txtAreaTrabajo.getText() == null)
				|| (this.txtAreaTrabajo.getText() != null && this.txtAreaTrabajo.getText().isEmpty())) {
			this.mensajes += "El Area de Trabajo no es valido, es vacio\n";
			valido = false;
		}
		if (this.chcEmpresa.getSelectionModel().getSelectedIndex() == 0
				|| this.chcEmpresa.getSelectionModel().getSelectedIndex() == -1) {
			this.mensajes += "Seleccione una Empresa\n";
			valido = false;
		}
		
		if ((this.txtNivelEstudio.getText() == null)
				|| (this.txtNivelEstudio != null && this.txtNivelEstudio.getText().isEmpty())) {
			this.mensajes += "El Nivel de Estudio no es valido, es vacio\n";
			valido = false;
		}
		
		return valido;
	}

}
