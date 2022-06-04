package ci.palmafrique.vonabri.jwt;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Component
public interface AuthenticationManager {

	 Authentication authenticate(Authentication authentication)
	            throws AuthenticationException;
}
