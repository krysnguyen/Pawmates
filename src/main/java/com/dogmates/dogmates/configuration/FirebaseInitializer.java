package com.dogmates.dogmates.configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

import static com.google.auth.oauth2.GoogleCredentials.fromStream;

@Service
@RequiredArgsConstructor
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() throws IOException {
        InputStream serviceAccount = this.getClass().getResourceAsStream("/service_acc_firebase.json");

        System.out.println(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(fromStream(serviceAccount))
                .setDatabaseUrl("https://pawmates-71be7.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
