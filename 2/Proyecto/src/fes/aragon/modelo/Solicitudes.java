package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Solicitudes {
	private static Solicitudes instancia=new Solicitudes();
	private ObservableList<Solicitud> grupoSolicitudes=FXCollections.observableArrayList();
	private boolean modificarSolicitud=false;
	private int indice=-1;
	
	private Solicitudes() {
		/*Solicitud s=new Solicitud();
		s.setAreatrabajo("Demo");
		s.setMaxestudios("Licenciatura");
		s.setExplaboral(true);
		s.setEmpresa("Refrescos");
		Perfil p=new Perfil();
		p.setNombre("Luis");
		p.setApellidoPaterno("Demo");
		p.setApellidoMaterno("jeje");
		p.setCorreo("demo@ge.com");
		p.setRfc("wencie");
		p.setTelefono("34254534");
		p.setEdad("12");
		s.setPerfl(p);
		Domicilio dom=new Domicilio();
		dom.setDireccion("A1");
		dom.setCalle("A2");
		dom.setColonia("A3");
		dom.setEstado("Mexico");
		dom.setCodpostal("43143");
		s.setDomicilio(dom);
		this.grupoSolicitudes.add(s);
		*/
	}

	public static Solicitudes getInstancia() {
		return instancia;
	}

	public boolean isModificarSolicitud() {
		return modificarSolicitud;
	}

	public void setModificarSolicitud(boolean modificarSolicitud) {
		this.modificarSolicitud = modificarSolicitud;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public ObservableList<Solicitud> getGrupoSolicitudes() {
		return grupoSolicitudes;
	}

	public void setGrupoSolicitudes(ObservableList<Solicitud> grupoSolicitudes) {
		this.grupoSolicitudes = grupoSolicitudes;
	}

	
	
	
	
	

}
