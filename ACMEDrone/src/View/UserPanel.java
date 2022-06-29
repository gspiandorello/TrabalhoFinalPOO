package View;

import java.util.Scanner;

public class UserPanel {

    public UserPanel(){
        int menuOption;
        do{
            System.out.println(" --------------- BEM-VINDO USUARIO --------------- ");

            System.out.println("Salvar e encerrar................................0");
            System.out.println("Consultar entregas...............................1");
            System.out.println("Consultar cobranca mensal........................2");

            System.out.print("Insira uma opcao do menu: ");

            // InputMismatch exception
            Scanner input = new Scanner(System.in);
            menuOption = input.nextInt();

            switch (menuOption){
                case 0:
                    // iniciar o salvamento se foi realizado algo
                case 1:
                    // cadastrar localizacao
                case 2:
                    // cadastrar novo drone
            }
        }while(menuOption <1 || menuOption > 5);

    }
}
