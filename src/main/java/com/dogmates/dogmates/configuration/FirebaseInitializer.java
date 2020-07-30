package com.dogmates.dogmates.configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import static com.google.auth.oauth2.GoogleCredentials.fromStream;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(fromStream(serviceAccount))
                .setDatabaseUrl("https://pawmates-71be7.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
