package View;

import Model.DataRegistry;

import java.text.ParseException;
import java.util.Scanner;

public class UserPanel {
    DataRegistry database = new DataRegistry();

    public void UserPanel(String email){
        String menuOption;
        String userKey = email;
        Scanner input = new Scanner(System.in);

        do{
            System.out.println(" --------------- BEM-VINDO USUARIO --------------- ");

            System.out.println("Consultar entregas...............................1");
            System.out.println("Consultar cobranca mensal........................2");
            System.out.println("Voltar ao MENU INICIAL...........................3");

            System.out.print("Insira uma opcao do menu: ");
            menuOption = input.nextLine();

            switch (menuOption){
                case "1":
                    database.procuraEntrega(userKey);
                    break;
                case "2":
                    System.out.print("Insira uma data para pesquisar no formato MES-ANO: ");
                    String dataString = input.nextLine();

                    try {
                        database.procuraData(dataString, userKey);
                    } catch (ParseException pe){
                        System.out.println(pe.getMessage());
                    }

                case "3":
                    App app = new App();
                    app.menuInicial();
                    break;
                default:
                    System.out.println();
                    System.out.println("Opcao inválida! Redigite.");
            }
        }while(!menuOption.equals("3"));

    }
}
