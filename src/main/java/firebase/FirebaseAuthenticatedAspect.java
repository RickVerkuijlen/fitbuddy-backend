package firebase;

import com.google.firebase.auth.FirebaseAuthException;
import firebase.FirebaseSecurityProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class FirebaseAuthenticatedAspect {

    @Around("@annotation(firebase.FirebaseSecurity) && execution(public * *(..))")
    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Analyzing token...");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        FirebaseSecurityProvider provider = new FirebaseSecurityProvider();
        Object value = null;

        try {
            provider.authenticateToken(request.getHeader("FB_TOKEN"));
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            assert response != null;
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token is Invalid");
        }
        return value;
    }
}
