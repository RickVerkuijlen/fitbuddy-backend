package firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirebaseSecurityProvider {


    private static final Logger log = LoggerFactory.getLogger(FirebaseSecurityProvider.class);

    public void authenticateToken(String firebaseTokenId) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(firebaseTokenId, true);
        } catch (FirebaseAuthException e) {
            log.error(e.toString());
        }
    }

}
