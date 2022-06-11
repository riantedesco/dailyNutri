package br.upf.dailyNutri.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import br.upf.dailyNutri.jwt.TokenJwt;

/**
 *
 * @author rctte
 */
public class Utils {

    /**
     * Gerar uma chave a ser utilizada na assinatura do token.
     *
     * @return
     */
    public static Key gerarChave() {
        /*
        A string "upf" após a execução de SHA-256 e a execução do EncodeBase64
        obteve como resultado final o conteúdo que consta na variável keyString
        Essa string deve ser conhecida apenas por aplicações que precisam gerar ou validar um token ou 
        consumir as informações contidas nela.
         */
    	String keyString = "YzBhZTgwZWM2ZTI5Njk1YzQ3YWIxYzg0ZTk5NjkxZjQ4YzIwZGRkMGVlZWU4NTFiMjhjZDg5NzU5NTFjODQ3ZQ==";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "HmacSHA256");
        return key;
    }

    /**
     * Método que gera uma data de expiração que posteriormente vai definir a
     * validade de um token em minutos...
     *
     * @param tempoEmMinutos
     * @return
     */
    public static Date definirDataDeExpiracao(long tempoEmMinutos) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(tempoEmMinutos);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static String processarTokenJWT(String usuario) {
        Key chave = Utils.gerarChave();
        TokenJwt token = new TokenJwt(chave);
        //42.000 minutos equivalente a 30 dias
        Date dataExpiracao = Utils.definirDataDeExpiracao(42000L);
        return token.gerarToken(usuario, dataExpiracao);
    }
}
