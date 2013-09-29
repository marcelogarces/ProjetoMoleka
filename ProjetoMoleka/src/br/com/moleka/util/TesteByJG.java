package br.com.moleka.util;


public class TesteByJG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        try
        {
            SMSService sms = new SMSService();
            System.out.println(sms.obterVersao());
            System.out.println(sms.enviarSMS("21", "76125294", "Sistema rodando no eclipse", "marcelogarces", "26521130"));
            System.out.println(sms.creditos("marcelogarces", "26521130"));
/*
            
            CEPService cep = new CEPService();
            System.out.println(cep.obterVersao());
            System.out.println(cep.obterLogradouro("26510290", "marcelogarces", "26521130"));
            System.out.println(cep.obterCEP("logradouro", "rio de janeiro", "rj", "usuario", "senha"));
    */       
           
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
