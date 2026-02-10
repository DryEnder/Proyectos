package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Perfil;
import fes.aragon.modelo.Solicitud;
import fes.aragon.modelo.Solicitudes;
import fes.aragon.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PerfilController extends BaseController implements Initializable {
	private Solicitud solicitud;
	private String mensajes="";

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRfc;

    @FXML
    private TextField txtTelefono;

    @FXML
    void cancelarPerfil(ActionEvent event) {
    	this.cerrarVentana(btnCancelar);
    }

    @FXML
    void nuevoPerfil(ActionEvent event) {
    	if (this.verificar()) {
    	Perfil perfil=new Perfil();
    	perfil.setNombre(this.txtNombre.getText());
    	perfil.setApellidoPaterno(this.txtApellidoPaterno.getText());
    	perfil.setApellidoMaterno(this.txtApellidoMaterno.getText());
    	perfil.setRfc(this.txtRfc.getText());
    	perfil.setCorreo(this.txtCorreo.getText());
    	perfil.setTelefono(this.txtTelefono.getText());
    	perfil.setEdad(this.txtEdad.getText());
    	solicitud.setPerfl(perfil);
    	this.cerrarVentana(btnAceptar);
    	}else {
    		this.ventanaEmergente("Mensaje", "Datos incorrectos", this.mensajes);
    		this.mensajes="";
    	}
    	
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.verificarEntrada(txtRfc, TipoError.RFC);
		this.verificarEntrada(txtCorreo, TipoError.CORREO);
		this.verificarEntrada(txtTelefono, TipoError.TELEFONO);
		
		if(Solicitudes.getInstancia().isModificarSolicitud()) {
			this.solicitud=Solicitudes.getInstancia().getGrupoSolicitudes().get(
					Solicitudes.getInstancia().getIndice());
			this.txtNombre.setText(solicitud.getPerfil().getNombre());
			this.txtApellidoPaterno.setText(solicitud.getPerfil().getApellidoPaterno());
			this.txtApellidoMaterno.setText(solicitud.getPerfil().getApellidoMaterno());
			this.txtRfc.setText(solicitud.getPerfil().getRfc());
			this.txtCorreo.setText(solicitud.getPerfil().getCorreo());
			this.txtTelefono.setText(solicitud.getPerfil().getTelefono());
			this.txtEdad.setText(solicitud.getPerfil().getEdad());
		}else {
			solicitud=Solicitudes.getInstancia().getGrupoSolicitudes().get(
					Solicitudes.getInstancia().getGrupoSolicitudes().size()-1);
		}
		
	}
	
	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNombre.getText() == null)
				|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes += "El nombre no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtApellidoPaterno.getText() == null)
				|| (this.txtApellidoPaterno.getText() != null && this.txtApellidoPaterno.getText().isEmpty())) {
			this.mensajes += "El apellido paterno no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtApellidoMaterno.getText() == null)
				|| (this.txtApellidoMaterno.getText() != null && this.txtApellidoMaterno.getText().isEmpty())) {
			this.mensajes += "El apellido materno no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtRfc.getText() == null) || (this.txtRfc.getText() != null && this.txtRfc.getText().isEmpty())) {
			this.mensajes += "El RFC no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtRfc.getText() == null) || (this.txtRfc.getText() != null && !this.txtRfc.getText().isEmpty())) {
			if (!this.rfcValido) {
				this.mensajes += "El RFC no es valido, minimo=13, maximo=13 caracteres\n";
				valido = false;
			}
		}
		if ((this.txtCorreo.getText() == null)
				|| (this.txtCorreo.getText() != null && this.txtCorreo.getText().isEmpty())) {
			this.mensajes += "El correo no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null)
				|| (this.txtCorreo.getText() != null && !this.txtCorreo.getText().isEmpty())) {
			if (!this.correoValido) {
				this.mensajes += "El correo no es valido, esta mal estructurado\n";
				valido = false;
			}
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono.getText() != null && this.txtTelefono.getText().isEmpty())) {
			this.mensajes += "El telefono no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono.getText() != null && !this.txtTelefono.getText().isEmpty())) {
			if (!this.telefonoValido) {
				this.mensajes += "El telefono no es valido, minimo=10, maximo=10 números\n";
				valido = false;
			}
		}
		return valido;
	}

}
