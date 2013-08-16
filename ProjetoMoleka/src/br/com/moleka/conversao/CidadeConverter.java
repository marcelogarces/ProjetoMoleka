package br.com.moleka.conversao;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.com.moleka.model.dao.CidadeDAO;
import br.com.moleka.model.dominio.Cidade;
import br.com.moleka.util.FacesContextUtil;

@FacesConverter(value="cidade")
public class CidadeConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                long id = Long.parseLong(submittedValue);
                CidadeDAO cidadeDAO = new CidadeDAO(FacesContextUtil.getRequestEntityManager());
                return cidadeDAO.obterCidadePorId(id);     
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Não é uma cidade válido."));
            }
        }
    }

	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Cidade) value).getId());
        }
    }

	
}
