package com.dogmates.dogmates.configuration;

import com.dogmates.dogmates.PawMatesApplication;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static com.google.auth.oauth2.GoogleCredentials.fromStream;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() throws IOException {
        InputStream serviceAccount = getClass().getResourceAsStream("/service_acc_firebase.json");
        InputStream inputStream = PawMatesApplication.class.getResourceAsStream("/service_acc_firebase.json");
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .parallel().collect(Collectors.joining("\n"));
        System.out.println("RESULT: +++++ " + result);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(fromStream(serviceAccount))
                .setDatabaseUrl("https://pawmates-71be7.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
