package firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class FirebaseSecurityProvider {

    public void authenticateToken(String firebaseTokenId) {
        try {
            FirebaseAuth.getInstance().verifyIdToken(firebaseTokenId, true);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }

}
