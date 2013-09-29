/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.moleka.util;

import java.util.HashMap;

/**
 * Classe para abstrair as chamadas ao WebService ByJG para SMS
 * @author jg
 */
public class CEPService extends ByJGBaseWebService
{
    protected static final String SERVICE = "cep";

    /**
     * Retorna a versao do WebService
     * @return Versão
     * @throws Exception
     */
    public String obterVersao() throws Exception
    {
        return this.executeWebService(CEPService.SERVICE, "obterVersao", null);
    }

    /**
     * Obtém o logradouro
     * @param cep
     * @param usuario
     * @param senha
     * @return
     * @throws Exception
     */
    public String obterLogradouro(String cep , String usuario , String senha) throws Exception
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);
        params.put("usuario", usuario);
        params.put("senha", senha);

        return this.executeWebService(CEPService.SERVICE, "obterLogradouroAuth", params);
    }

    public String obterCEP(String logradouro , String localidade , String UF , String usuario , String senha) throws Exception
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("logradouro", logradouro);
        params.put("localidade", localidade);
        params.put("UF", UF);
        params.put("usuario", usuario);
        params.put("senha", senha);

        return this.executeWebService(CEPService.SERVICE, "obterCEPAuth", params);
    }

}
