package br.com.moleka.dataModel;


import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Pessoa;

public class DataModelPessoa extends LazyDataModel<Pessoa> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Pessoa> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, String> filtros) {
		
			return  null;
	}

	public List<Pessoa> load(int arg0, int arg1, String arg2, boolean arg3,
			Map<String, String> arg4) {
		// TODO Auto-generated method stub
		return null;
	}

}
