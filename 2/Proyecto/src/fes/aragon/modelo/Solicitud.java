package fes.aragon.modelo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Solicitud implements Initializable {
	
	private Perfil perfil=new Perfil();
	private Domicilio domicilio=new Domicilio();
	private String areatrabajo;
	private boolean explaboral;
	private String maxestudios;
	private String empresa;
	private static Solicitud instancia=new Solicitud();
	
	public Solicitud() {
	}
	
	public static Solicitud getSolicitud() {
		return instancia;
	}
	
	
	
	
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}
	
	public void setPerfl(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String getAreatrabajo() {
		return areatrabajo;
	}
	public void setAreatrabajo(String areatrabajo) {
		this.areatrabajo = areatrabajo;
	}
	public boolean isExplaboral() {
		return explaboral;
	}
	public void setExplaboral(boolean explaboral) {
		this.explaboral = explaboral;
	}
	public String getMaxestudios() {
		return maxestudios;
	}
	public void setMaxestudios(String maxestudios) {
		this.maxestudios = maxestudios;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Solicitud [perfil=" + perfil + ", domicilio=" + domicilio + ", areatrabajo=" + areatrabajo
				+ ", explaboral=" + explaboral + ", maxestudios=" + maxestudios + ", empresa=" + empresa + "]";
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
}
