package tenderi.service;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import tenderi.domain.User;
//
//@Service
//public class NotificationService {
//
//    private JavaMailSender javaMailSender;
//    @Autowired
//    public NotificationService(JavaMailSender javaMailSender){
//        this.javaMailSender=javaMailSender;
//    }
//    public void sendMailNotification(User user) throws MailException
//    {
//        SimpleMailMessage mail=new SimpleMailMessage();
//mail.setTo(user.getEmail());
//mail.setFrom("drasko.kosovic@gmail.com");
//mail.setSubject("potvrda registracije");
//String str=user.getUsername();
//String korisnik = str.substring(0, 1).toUpperCase() + str.substring(1);
//mail.setText("" + korisnik + "   Uspjesno ste se registrovali");
//javaMailSender.send(mail);
//
//
//    }
//}
