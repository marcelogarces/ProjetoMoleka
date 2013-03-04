package br.com.moleka.managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
public class GoogleMapBean implements Serializable  {


	private static final long serialVersionUID = 1L;
	
	private MapModel model = new DefaultMapModel();
	
	public GoogleMapBean() {
		model.addOverlay(new Marker(new LatLng(-22.809287,-43.413979), "M1"));
	}
	public MapModel getModel() { return this.model; }
	
	public String getPartida() {
		return "RUA MARIO SILVA 78 CENTRO NILÓPOLIS RJ";
	}
	
	public String getDestino() {
		return "RUA JOÃO EVANGELISTA DE CAVALHO 1135 CABRAL NILÓPOLIS RJ ";
	}
	
	
	public String obterMapa(){
        
		return "/_mapa_local.xhtml?faces-redirect=true";    
	}
	
	public String obterRota(){
        
		return "/_mapa_rota.xhtml?faces-redirect=true";    
	}
}
