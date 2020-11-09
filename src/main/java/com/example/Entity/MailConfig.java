package com.example.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author AllenVan
 * @version 1.0
 * @date 2020/10/27
 */

@Configuration
@ConfigurationProperties(prefix = "mail-config")
public class MailConfig {

    private String[] recipient = null;
    private String environmental;
    private String from;

    public void setEnvironmental(String environmental) {
        this.environmental = environmental;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setRecipient(String[] recipient) {
        this.recipient = recipient;
    }

    public String getEnvironmental() {
        return environmental;
    }

    public String getFrom() {
        return from;
    }

    public String[] getRecipient() {
        return recipient;
    }


}
