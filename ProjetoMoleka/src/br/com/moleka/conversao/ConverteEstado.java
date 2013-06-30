package br.com.moleka.conversao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.util.FacesContextUtil;

@FacesConverter(value = "converterEstado")
public class ConverteEstado implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String value) {

		if (value != null && !value.equals("")) {
			EstadoDAO estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());
			
			return null;//estadoDAO
			//return dao.getByIdEstado(Long.valueOf(value));
			}
			return null;
			}
	

	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object value) {
		/*
		if (value instanceof Estado) {
			Estado estado = (Estado) value;
			return String.valueOf(estado.getIdEstado());
			}
			return "";
			}
			*/
		return "";
	}

}
