package validacoes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author ricardo
 */
public class ValidadorPalavrao implements ConstraintValidator<ValidaPalavrao, String> {

    private List<String> palavroes;

    @Override
    public void initialize(ValidaPalavrao validaPalavrao) {
        this.palavroes = new ArrayList<>();
        //System.out.println("LENDO ARQUIVO: ");
        this.leitor("lista-palavroes-bloqueio.txt");
        //System.out.println(palavroes);
    }

    public void leitor(String path) {
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = "";
            while (true) {
                if (linha != null) {
                    if(linha != ""){
                        this.palavroes.add(linha.toLowerCase().trim());
                        //System.out.println(linha.toLowerCase().trim() + "|");
                    }
                } else {
                    break;
                }
                linha = buffRead.readLine();
            }
            buffRead.close();
        } catch (IOException e) {

        }
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {

        for (String palavrao : palavroes) {
            if (valor.toLowerCase().contains(palavrao)) {
                return false;
            }
        }

        return true;
    }

}
