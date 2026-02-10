package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Domicilio;
import fes.aragon.modelo.Solicitud;
import fes.aragon.modelo.Solicitudes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DomicilioController extends BaseController implements Initializable {
	private Solicitud solicitud;
	private String mensajes="";

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtCodigoPostal;

    @FXML
    private TextField txtColonia;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEstado;

    @FXML
    void cancelarDomicilio(ActionEvent event) {
    	this.cerrar();
    }

    @FXML
    void nuevoDomicilio(ActionEvent event) {
    	if (this.verificar()) {
    	Domicilio dom=new Domicilio();
    	dom.setDireccion(this.txtDireccion.getText());
    	dom.setCalle(this.txtCalle.getText());
    	dom.setColonia(this.txtColonia.getText());
    	dom.setEstado(this.txtEstado.getText());
    	dom.setCodpostal(this.txtCodigoPostal.getText());
    	solicitud.setDomicilio(dom);
    	this.cerrarVentana(btnAceptar);
    	}else {
    		this.ventanaEmergente("Mensaje", "Datos incorrectos", this.mensajes);
    		this.mensajes="";
    	}
    }
    
    private void cerrar() {
    	Stage stage=(Stage)btnCancelar.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Solicitudes.getInstancia().isModificarSolicitud()) {
			this.solicitud=Solicitudes.getInstancia().getGrupoSolicitudes().get(
					Solicitudes.getInstancia().getIndice());
			this.txtDireccion.setText(solicitud.getDomicilio().getDireccion());
			this.txtCalle.setText(solicitud.getDomicilio().getCalle());
			this.txtColonia.setText(solicitud.getDomicilio().getColonia());
			this.txtEstado.setText(solicitud.getDomicilio().getEstado());
			this.txtCodigoPostal.setText(solicitud.getDomicilio().getCodpostal());
		}else {
			solicitud=Solicitudes.getInstancia().getGrupoSolicitudes().get(
					Solicitudes.getInstancia().getGrupoSolicitudes().size()-1);
		}
	}
	
	private boolean verificar() {
		boolean valido = true;
		if ((this.txtDireccion.getText() == null)
				|| (this.txtDireccion.getText() != null && this.txtDireccion.getText().isEmpty())) {
			this.mensajes += "La Direccion no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCalle.getText() == null)
				|| (this.txtCalle.getText() != null && this.txtCalle.getText().isEmpty())) {
			this.mensajes += "La Calle no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtColonia.getText() == null)
				|| (this.txtColonia.getText() != null && this.txtColonia.getText().isEmpty())) {
			this.mensajes += "La colonia no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtEstado.getText() == null)
				|| (this.txtEstado.getText() != null && this.txtEstado.getText().isEmpty())) {
			this.mensajes += "El Estado no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCodigoPostal.getText() == null)
				|| (this.txtCodigoPostal.getText() != null && this.txtCodigoPostal.getText().isEmpty())) {
			this.mensajes += "El Codigo Postal no es valido, es vacio\n";
			valido = false;
		}
		
		return valido;
	}
	

}
