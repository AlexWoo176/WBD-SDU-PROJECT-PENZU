package codegym.project.sdupenzu.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseAppService {
    private static FirebaseApp firebaseApp;

    private FirebaseAppService() {}

    public static FirebaseApp getFirebaseApp() throws IOException {

        if (firebaseApp == null) {
            FileInputStream serviceAccount =
                    new FileInputStream("C:/Users/Admin/Desktop/Project Sdu Team/alexwoo.json");

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl("https://alexwoo-project.firebaseio.com")
                    .setStorageBucket("alexwoo-project.appspot.com")
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        }
        return firebaseApp;
    }
}
