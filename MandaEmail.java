// PARA FUNCIONAR CORRETAMENTE O REMETENTE PRECISA TER GMAIL

// Retorno da Função
boolean result = false;

// A String entre () apenas será necessária se o email do destinatário for uma variavel que será inserida pelo usuário
public boolean mandaEmail(String emailUsuario) {
    Properties props = new Properties();
    // Parâmetros de conexão com o Gmail
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    // Entra na conta que será a remetente do e-mail que será enviado
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() 
    {
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication("seu.email@gmail.com",
                 "senhadoemailaqui");
           }
    });
  
    // Ativa o Debug(Opcional)
    session.setDebug(true);

  // Remetente do e-mail a ser enviado
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("seu.email@gmail.com"));

      // Se seu destinatário for uma variável:
      Address[] toUser = InternetAddress 
                 .parse(emailUsuario);
      
      // Se seu destinatário for fixo:
      Address[] toUser = InternetAddress 
                 .parse("emaildo.destinatario@gmail.com");
      
      // E-mail
      message.setRecipients(Message.RecipientType.TO, toUser);
      message.setSubject(" Insira o assunto aqui "); //Assunto do E-mail
      message.setText(" Insira o conteúdo do e-mail ");
      
      // Enviando o E-mail
      Transport.send(message);
      result = true;
      System.out.println("Feito!!!");

     } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    return result;
  }
