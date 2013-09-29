package br.com.moleka.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	
	
	public static void enviarMensagemExcecao(Throwable throwable){
		
		String remetente = "sistemaMoleka@niltech.com";
		String usuario = "marcelo.rj.ni@gmail.com";
		String senha = "mgg200601053081";
		String destinatarios = "marcelo.rj.ni@gmail.com";
		String assunto = "Erro/Falha no sistema";
		String mensagem =  "causa.... "+throwable.getCause() +"\n Erro....." + throwable.getMessage();
		
		enviarEmail(propriedadesParaConexaoGmail(), remetente, usuario, senha, destinatarios, assunto, mensagem);
		
	}
		
	public static void enviarEmail(Properties propriedadeServidor,String remetente,final String usuario,final String senha,String destinatarios,String assunto,String mensagem){
		
		 Session session = Session.getDefaultInstance(propriedadeServidor,
                 new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication() 
                      {
                            return new PasswordAuthentication(usuario,senha);
                      }
                 });
		
	        session.setDebug(true);
	        
	        try {

	              Message message = new MimeMessage(session);
	              message.setFrom(new InternetAddress(remetente)); 

	              
	              Address[] toUser = InternetAddress
	                         .parse(destinatarios);  
	     
	              message.setRecipients(Message.RecipientType.TO, toUser);
	              message.setSubject(assunto);
	              message.setText(mensagem);
	         
	              Transport.send(message);
  
	         } catch (MessagingException e) {
	              throw new RuntimeException(e);
	        }
		
	}
	
	public static Properties propriedadesParaConexaoGmail(){
		
	Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        return props;
		
	}

}
