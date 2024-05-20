import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsultaCEP extends NullPointerException {

    private List<CEP> historicoBusca = new ArrayList<>();

    public CEP buscaEndereco(String cep) {

        String enderecoCEP = "https://viacep.com.br/ws/" + cep + "/json/";

        Gson gson = new Gson();

        HttpClient client = HttpClient
                .newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoCEP))
                .build();

        try {
            HttpResponse<String> response;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 400) {
                throw new ErroConsultaCEP("CEP não encontrado na lista de logradouros.");
            }

            Endereco endereco = gson.fromJson(response.body(), Endereco.class);

            CEP meuEndereco = new CEP(endereco);
            return meuEndereco;

        } catch (Exception e) {
            System.out.println("Opss, Houve um erro durante a consulta à API do ViaCEP.");
            e.printStackTrace();
        }
        System.out.println("Nenhum dado foi retornado, endereço sem cadastro no banco de dados!");
        return null;
    }


    public boolean inserirNoHistorico(CEP cep) {

        if (!this.historicoBusca.contains(cep)) {
            this.historicoBusca.add(cep);
            return true;
        }
        return false;
    }

    public List<CEP> getHistoricoBusca() {
        return historicoBusca;
    }
}