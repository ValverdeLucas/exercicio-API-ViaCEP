import java.util.ArrayList;
import java.util.List;

public class CEP {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    public CEP(Endereco enderecoCEP) {
        this.logradouro = enderecoCEP.logradouro();
        this.bairro = enderecoCEP.bairro();
        this.cep = enderecoCEP.cep();
        this.cidade = enderecoCEP.localidade();
        this.uf = enderecoCEP.uf();
    }

    @Override
    public String toString() {
        return "Logradouro: " + logradouro + " | " +
                "Bairro: " + bairro + " | " +
                "CEP: " + cep + " | " +
                "Cidade: " + cidade + " | " +
                "UF: " + uf;
    }
}
