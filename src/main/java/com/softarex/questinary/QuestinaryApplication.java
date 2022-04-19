package com.softarex.questinary;

import com.softarex.questinary.entity.User;
import com.softarex.questinary.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuestinaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestinaryApplication.class, args);
    }

}
