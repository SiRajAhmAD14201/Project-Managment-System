package com.bluebarry.project_managment_sytem.service.interfaces;

public interface EmailService {
    void sendEmailToSingleUser(String to, String subject, String text);
    void sendEmailToMultipleUsers(String[] to, String subject, String text);
    void sendEmailWithAttachment(String[] to, String subject, String text, String attachmentPath);
}
