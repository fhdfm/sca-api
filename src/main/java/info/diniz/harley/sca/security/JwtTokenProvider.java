package info.diniz.harley.sca.security;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String gerarToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        JwtBuilder jwt = Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret);
        
        Collection<?> permissoes = userPrincipal.getAuthorities();
        
        if (permissoes != null && !permissoes.isEmpty()) {
        	for (Object grantedAuthority : permissoes) {
        		jwt.claim("perfil", ((GrantedAuthority) grantedAuthority).getAuthority());
        	}
        }
                
        return jwt.compact();
    }

    public Long recuperarIdUsuario(String token) {
    	String chave = token.replace("Bearer", "");
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(chave)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validarToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Assinatura inválida.");
        } catch (MalformedJwtException ex) {
            logger.error("Token inválido.");
        } catch (ExpiredJwtException ex) {
            logger.error("Token expirado.");
        } catch (UnsupportedJwtException ex) {
            logger.error("Formato de token inválido");
        } catch (IllegalArgumentException ex) {
            logger.error("Claims vazio.");
        }
        return false;
    }
}
