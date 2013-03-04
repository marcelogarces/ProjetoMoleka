package br.com.moleka.dataModel;


import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Pessoa;

public class DataModelPessoa extends LazyDataModel<Pessoa> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<Pessoa> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, String> filtros) {
		
			return  null;
	}

}
