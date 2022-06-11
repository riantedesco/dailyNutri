package br.upf.dailyNutri.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.upf.dailyNutri.util.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 *
 * @author rctte
 */
public class TokenJwt {

    private static Key chave;
    private String jwt;

    public TokenJwt(Key chave) {
        this.chave = chave;
    }

    public static boolean validarToken(String token) {
        chave = Utils.gerarChave();
        boolean tokenValido = false;
        if (token != null) {
            try {
                //Gerando a Claims 
                Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
                tokenValido = true;
                //exception referente ao token expirado...
            } catch (ExpiredJwtException e) {
                //retorna para o App a exceção 
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token inválido!", e);
                //exeption referente a problema no token...
            } catch (MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
                //retorna para o App a exceção 
            	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido!", e);
            }
        } else {
            //retorna para o App a exceção 
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum cabeçalho de autenticação presente!");
        }
        return tokenValido;
    }

    /**
     * Gerando token JWT
     *
     * @param nomeUsuario
     * @param dataExpiracao
     * @return
     */
    public String gerarToken(String nomeUsuario, Date dataExpiracao) {
        jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT") //definindo cabeçalho
                .setSubject(nomeUsuario) //assunto do token
                .setIssuer("upf") //quem é o emissor do token
                .setIssuedAt(new Date()) //data de criação
                .claim("password", "sdlkjsdoijonpvf65v4e6fv5e6ver")
                .setExpiration(dataExpiracao) //data de expiração do token
                .signWith(SignatureAlgorithm.HS256, chave) //assinatura do token
                .compact(); //contruir o JWT                
        return jwt;
    }

    /**
     * Recuperando assunto do Token
     *
     * @param token
     * @return
     */
    public String recuperarSubjectDoToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    /**
     * Recuperando emissor do Token
     *
     * @param token
     * @return
     */
    public String recuperarIssuerDoToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
        return claimsJws.getBody().getIssuer();
    }

    /**
     * Método que lê um token e retorna o claim especifico.
     *
     * @param token
     * @param claim
     * @return
     */
    public String resuperarClaim(String token, String claim) {
        String subject = "";
        if (token != null && !token.equals("")) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
            subject = claimsJws.getBody().get(claim).toString();
        }
        return subject;
    }

}
