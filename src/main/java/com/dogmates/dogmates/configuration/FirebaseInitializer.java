package com.dogmates.dogmates.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

import static com.google.auth.oauth2.GoogleCredentials.fromStream;

@Service
public class FirebaseInitializer {
    @PostConstruct
    public void initialize() throws IOException {
        val serviceAccount = new FileInputStream("./service_acc_firebase.json");

        val options = new FirebaseOptions.Builder()
                .setCredentials(fromStream(serviceAccount))
                .setDatabaseUrl("https://pawmates-71be7.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
