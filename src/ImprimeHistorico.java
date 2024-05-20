import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ImprimeHistorico {

    public void imprimeHistorico(List cep) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File file = new File("buscas-cep.json");
        FileWriter writer = new FileWriter(file);

        writer.write(gson.toJson(cep));
        System.out.println("Arquivo gerado com sucesso! Arquivo salvo em: " + file.getAbsolutePath());
        writer.close();

    }
}
