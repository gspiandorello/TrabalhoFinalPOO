package View;

import Model.DataRegistry;

import java.util.Scanner;

public class UserPanel {
    DataRegistry database = new DataRegistry();

    public void UserPanel(){
        String menuOption;

        do{
            System.out.println(" --------------- BEM-VINDO USUARIO --------------- ");

            System.out.println("Consultar entregas...............................1");
            System.out.println("Consultar cobranca mensal........................2");
            System.out.println("Voltar ao MENU INICIAL...........................3");

            System.out.print("Insira uma opcao do menu: ");

            Scanner input = new Scanner(System.in);
            menuOption = input.nextLine();

            switch (menuOption){
                case "1":
                    database.queryAllEntregas();
                    break;
                case "2":
                    database.queryAllEntregas();
                    break;
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
