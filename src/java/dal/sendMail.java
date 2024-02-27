/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author SINHDQ
 */
public class sendMail {

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static final String ALL = alpha + alphaUpperCase + digits + specials;

    public void send(String to, String mess) {
        final String user = "sinhdqhe150849@fpt.edu.vn";
        final String pass = "toikhongbiet2001";
        String sub = "Email take password.";
        String msg = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <h1 style=\"color: blue;\">SMarket</h1>\n"
                + "    <h3 style=\"color: blue;\">Please, Enter code in box.</h3>\n"
                + "    <div>Code: " + mess + "</div>\n"
                + "    <h3 style=\"color: blue;\">Don't public!</h3>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.port", "587");

        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
         */
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Random generator = new Random();

    /**
     *                                                             
     * Random string with a-zA-Z0-9, not included special characters
     */
    public String createCapcha(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Random string password with at least 1 digit and 1 special character
     */
    public String randomPassword(int numberOfCharactor) {
        List<String> result = new ArrayList<>();
        Consumer<String> appendChar = s -> {
            int number = randomNumber(0, s.length() - 1);
            result.add("" + s.charAt(number));
        };
        appendChar.accept(digits);
        appendChar.accept(specials);
        while (result.size() < numberOfCharactor) {
            appendChar.accept(ALL);
        }
        Collections.shuffle(result, generator);
        return String.join("", result);
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        sendMail s = new sendMail();
        //System.out.println(s.createCapcha(8));
        s.send("sinhphuung2001@gmail.com", s.createCapcha(8));
    }
}