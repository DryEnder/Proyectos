package fes.aragon.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fes.aragon.modelo.Solicitud;
import fes.aragon.modelo.Solicitudes;
import fes.aragon.modelo.TipoError;
import fes.aragon.modelo.archivo.SolicitudArchivo;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BaseController {
	protected boolean rfcValido=true;
	protected boolean correoValido=true;
	protected boolean telefonoValido=true;
	
	/*
	 * EEXPRESIONES REGULARES
	 * 0 palabras sin espacion
	 * 1 solo numeros
	 * 2 validar RFC
	 * 3 validar correo
	 * 4 validar telefono
	 */
	
	private String[] expresiones= {"(\\w+)",
			"(\\d+)(\\.\\d{1,2})",
			"(\\w){13}",
			"^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			"(\\d){10}"};
	
	public void nuevaVentana(String archivo) {
    	try {
			Pane root=(Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/"+archivo+".fxml"));
			Scene scene=new Scene(root);
			Stage escenario=new Stage();
			escenario.setScene(scene);
			//escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			//escenario.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void ventanaEmergente(String titulo, String encabezado, String contenido) {
		Alert alerta;
		alerta=new Alert(AlertType.INFORMATION);
		//alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX()+300);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();
	}
	
	public void cerrarVentana(Button boton) {
		Stage stage=(Stage) boton.getScene().getWindow();
		stage.close();
	}
	
	public void verificarEntrada(TextField caja, TipoError error) {
		caja.textProperty().addListener(evento -> {
			String text=caja.getText();
			if(text==null) {
				text="";
			}
			String patron=expresiones[error.ordinal()];
			Pattern pt=Pattern.compile(patron);
			Matcher match=pt.matcher(text);
			caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());
			if(error==TipoError.RFC){
				this.rfcValido=match.matches();
			}else if(error==TipoError.CORREO) {
				this.correoValido=match.matches();
			}else if(error==TipoError.TELEFONO) {
				this.telefonoValido=match.matches();
			}
		});
	}
	
	public void abrirArchivo(Button boton) throws IOException, ClassNotFoundException {
		Stage stage=(Stage) boton.getScene().getWindow();
		FileChooser archivos=new FileChooser();
		archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos para Solicitudes", "*.fes"));
		archivos.setTitle("Abrir archivo de Solicitud");
		archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta=archivos.showOpenDialog(stage);
		if(ruta!=null) {
			FileInputStream fi=new FileInputStream(ruta);
			ObjectInputStream entrada=new ObjectInputStream(fi);
			ArrayList<SolicitudArchivo> datos=(ArrayList<SolicitudArchivo>)entrada.readObject();
			Solicitudes.getInstancia().getGrupoSolicitudes().clear();
			for (SolicitudArchivo solicitud : datos) {
				Solicitud objeto=new Solicitud();
				objeto.setAreatrabajo(solicitud.getAreatrabajo());
				objeto.setEmpresa(solicitud.getEmpresa());
				objeto.setExplaboral(solicitud.isExplaboral());
				objeto.setMaxestudios(solicitud.getMaxestudios());
				objeto.setPerfl(solicitud.getPerfil());
				objeto.setDomicilio(solicitud.getDomicilio());
				Solicitudes.getInstancia().getGrupoSolicitudes().add(objeto);
			}
			fi.close();
			entrada.close();
		}
	}
	
	public void guardarArchivo(Button boton) throws IOException {
		Stage stage=(Stage) boton.getScene().getWindow();
		FileChooser archivos=new FileChooser();
		archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos para Solicitudes", "*.fes"));
		archivos.setTitle("Guardar archivo de Solicitud");
		archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta=archivos.showSaveDialog(stage);
		if(ruta!=null) {
			FileOutputStream fo=new FileOutputStream(ruta);
			ObjectOutputStream salida=new ObjectOutputStream(fo);
			ArrayList<SolicitudArchivo> solicitudes=new ArrayList<>();
			for (Solicitud solicitud : Solicitudes.getInstancia().getGrupoSolicitudes()) {
				SolicitudArchivo objeto=new SolicitudArchivo();
				objeto.setAreatrabajo(solicitud.getAreatrabajo());
				objeto.setEmpresa(solicitud.getEmpresa());
				objeto.setExplaboral(solicitud.isExplaboral());
				objeto.setMaxestudios(solicitud.getMaxestudios());
				objeto.setPerfl(solicitud.getPerfil());
				objeto.setDomicilio(solicitud.getDomicilio());
				solicitudes.add(objeto);
			}
			salida.writeObject(solicitudes);
			salida.close();
			fo.close();
		}
	}
	
	
}
