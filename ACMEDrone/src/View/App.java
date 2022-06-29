package View;

import Controller.AdminController;

import java.util.Scanner;

public class App {
    private Scanner input = new Scanner(System.in);

    public App(){
        System.out.println("Bem-vindos ao ACMEDrone!");

        System.out.println("--- Insira suas credenciais para o login ---");
        System.out.print("E-mail: ");
        String emailInput = input.nextLine();

        System.out.print("Senha: ");
        String senhaInput = input.nextLine();

        if(AdminController.verificaCredenciaisAdmin(emailInput, senhaInput))
            new AdminPanel();
        else
            new UserPanel();

    }
}
