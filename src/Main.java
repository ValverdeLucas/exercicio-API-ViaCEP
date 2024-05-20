

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static int validateOption(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.println("Por favor, insira um valor númerico válido:");
            input.next();
        }
        return input.nextInt();
    }

    public static void main(String[] args) throws IOException, IndexOutOfBoundsException {

        Scanner input = new Scanner(System.in);
        ConsultaCEP consultaCEP = new ConsultaCEP();
        int opcaoMenu = 0;

        String menu = """      
                        
                Escolhe a opção desejada:
                                
                1- Buscar CEP
                2- Visualizar histórico de busca
                3- Gerar arquivo de buscas
                4- Sair                                           
                """;

        while (opcaoMenu != 4) {

            System.out.println(menu);
            opcaoMenu = validateOption(input);


            switch (opcaoMenu) {

                case 1:

                    System.out.println("Digite o CEP para consultar informações do logradouro: ");

                    var cep = input.next();

                    CEP meuCEP = consultaCEP.buscaEndereco(cep);
                    System.out.println(meuCEP);

                    boolean inclusoNoHistorico = consultaCEP.inserirNoHistorico(meuCEP);

                    if (inclusoNoHistorico) {
                        consultaCEP.getHistoricoBusca();
                    }
                    break;

                case 2:
                    System.out.println("****** Últimos 5 CEPs buscados ******\n");

                    if (consultaCEP.getHistoricoBusca().size() >= 5) {
                        for (int i = 0; i < 5; i++) {
                            System.out.println(i+1 + ": " + consultaCEP.getHistoricoBusca().reversed().get(i));
                        }
                    } else {
                        for (CEP itemCEP : consultaCEP.getHistoricoBusca())
                            System.out.println(consultaCEP.getHistoricoBusca().indexOf(itemCEP) + 1 + ": " + itemCEP);
                    }
                    break;

                case 3:
                    ImprimeHistorico imprimir = new ImprimeHistorico();
                    imprimir.imprimeHistorico(consultaCEP.getHistoricoBusca());
                    break;

                case 4:
                    System.out.println("""
                             ************ Sessão encerrada ************
                            """);
                    break;
            }
        }
    }
}