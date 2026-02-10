package fes.aragon.modelo;

import java.io.Serializable;

public class Domicilio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String direccion;
	private String calle;
	private String colonia;
	private String estado;
	private String codpostal;
	
	public Domicilio() {
		
	}
	
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodpostal() {
		return codpostal;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}


	@Override
	public String toString() {
		return "Domicilio [direccion=" + direccion + ", calle=" + calle + ", colonia=" + colonia + ", estado=" + estado
				+ ", codpostal=" + codpostal + "]";
	}
	
	
	
	
	
	

}
