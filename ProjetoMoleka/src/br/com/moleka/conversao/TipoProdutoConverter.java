package br.com.moleka.conversao;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.com.moleka.model.dao.TipoProdutoDAO;
import br.com.moleka.model.dominio.TipoProduto;
import br.com.moleka.util.FacesContextUtil;

@FacesConverter(value="tipoProduto")
public class TipoProdutoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                long id = Long.parseLong(submittedValue);
                TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO(FacesContextUtil.getRequestEntityManager());
                return tipoProdutoDAO.obterTipoProdutoPorCodigo(id);        
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Não é um tipo de produto válido."));
            }
        }
    }

	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		System.out.println("teste gitHub.");
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((TipoProduto) value).getId());
        }
    }

}
