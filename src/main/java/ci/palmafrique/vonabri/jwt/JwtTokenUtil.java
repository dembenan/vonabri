package ci.palmafrique.vonabri.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ci.palmafrique.vonabri.utils.Utilities;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersion = -2555L;

	private static long JWT_TOKEN_VALIDITY = 1 * 250;
	private static long JWT_TOKEN_5_MIN_VALIDITY = 10 * 60;

	

	@Value("${jwt.secret}")
	private String secret;

//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}

	public java.util.Date getExpirationDateFromToken(String token) {
		try {
		System.out.println("expiration date1 : " + (java.util.Date) getClaimFromToken(token, Claims::getExpiration));
		return (java.util.Date) getClaimFromToken(token, Claims::getExpiration);
		}catch(ExpiredJwtException ex) {

			System.out.println("le token a expiré dans getExpirationDateFromToken" );
			return null;
		}
	}

	public String refreshToken(String token) {
		  final Claims claims = getAllClaimsFromToken(token);
		  claims.setIssuedAt(Utilities.getCurrentDate());
		  claims.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_5_MIN_VALIDITY * 1000));
		  return Jwts.builder()
		    .setClaims(claims)
		   // .signWith(SignatureAlgorithm.HS512, secret)
		    .compact();
    }
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		try {
		final java.util.Date expiration = getExpirationDateFromToken(token);
		if(expiration == null) {
			return true;
		}
		System.out.println("expiration date2 " + expiration);
		return expiration.before(Utilities.getCurrentDate());
		}catch(ExpiredJwtException ex) {
			System.out.println("le token a expiré dans isTokenExpired" );
			return false;
		}
	}

	public String generateToken(UserDetails userDetails) {
		// Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(userDetails.getUsername());
	}

	private String doGenerateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuedAt(Utilities.getCurrentDate())
				//.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_5_MIN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
	}

	public Boolean validateTokenTime(String token) {
		try {
		return (!isTokenExpired(token));
		}catch(ExpiredJwtException ex) {
			System.out.println("le token a expiré dans validateTokenTime" );
			return (isTokenExpired(token));
		}
	}

	public Jws<Claims> validateJwtToken(String token) {
		return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
	}

	public boolean validateToken(String authToken) {
		try {
			// Jwt token has not been tampered with
			Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			return false;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

}
