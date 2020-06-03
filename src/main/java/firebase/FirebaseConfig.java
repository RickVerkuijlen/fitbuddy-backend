package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "firebase")
public class FirebaseConfig {


    private static final Logger log = LoggerFactory.getLogger(FirebaseConfig.class);

    @PostConstruct
    public void init() {
        System.out.println("Firebaseconfig initializing");
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("fitbuddy-firebase.json");

        FirebaseOptions options = null;
        try {
            assert serviceAccount != null;
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        } catch (IOException e) {
            log.error(e.toString());
        }

        assert options != null;
        FirebaseApp.initializeApp(options);
    }
}
