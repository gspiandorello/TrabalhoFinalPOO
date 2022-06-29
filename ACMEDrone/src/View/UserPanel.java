package View;

import java.util.Scanner;

public class UserPanel {

    public UserPanel(){
        String menuOption;
        do{
            System.out.println(" --------------- BEM-VINDO USUARIO --------------- ");

            System.out.println("Salvar e encerrar................................0");
            System.out.println("Consultar entregas...............................1");
            System.out.println("Consultar cobranca mensal........................2");
            System.out.println("Encerrar o programa..............................3");

            System.out.print("Insira uma opcao do menu: ");

            Scanner input = new Scanner(System.in);
            menuOption = input.nextLine();

            switch (menuOption){
                case "0":
                    break;
                    // iniciar o salvamento se foi realizado algo
                case "1":
                    break;
                    // cadastrar localizacao
                case "2":
                    break;
                    // cadastrar novo drone
                case "3":
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println();
                    System.out.println("Opcao inválida! Redigite.");
            }
        }while(!menuOption.equals("3"));

    }
}
