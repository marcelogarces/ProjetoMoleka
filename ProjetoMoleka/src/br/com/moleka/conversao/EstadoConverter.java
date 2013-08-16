package br.com.moleka.conversao;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.model.dominio.Estado;
import br.com.moleka.util.FacesContextUtil;


@FacesConverter(value="estado")
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                long id = Long.parseLong(submittedValue);
                EstadoDAO estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());
                return estadoDAO.obterEstadoPorId(id);        
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Não é um estado válido."));
            }
        }
    }

	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Estado) value).getId());
        }
    }

}
