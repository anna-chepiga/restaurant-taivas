package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.RestaurantInfoDao;
import ua.goit.domain.RestaurantInfo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class RestaurantInfoService {
    private RestaurantInfoDao restaurantInfoDao;

    @Transactional
    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfoDao.getAllDetails();
    }

    public void setRestaurantInfoDao(RestaurantInfoDao restaurantInfoDao) {
        this.restaurantInfoDao = restaurantInfoDao;
    }

    public void sendFeedback(String name, String email, String comment) {
        String from = "taivas.site";
        String pass = "taivas*!site<>~";
        String to = "taivas.site@gmail.com";

        String subject = "Feedback from Taivas website";
        String body = "User '" + name + "' sent a comment from '" + email + "':\n\n" + comment;

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setText(body);
            message.setSubject(subject);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
