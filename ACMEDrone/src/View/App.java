package View;

import Controller.AdminController;
import Model.DataRegistry;

import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class App {
    private Scanner input = new Scanner(System.in);
    DataRegistry database = new DataRegistry();
    UserPanel user = new UserPanel();
    AdminPanel admin= new AdminPanel();
    public void menuInicial() {
        System.out.println("Bem-vindos ao ACMEDrone!");
        System.out.println("- MENU INICIAL -");
        System.out.println("1 - Seguir para tela de login");
        System.out.println("2 - Sair do programa");
        String op = input.nextLine();
        switch (op) {

            case "1":
                Login();
                break;
            case "2":
                exit(2);
            default:
                System.out.println("Insira uma opção válida.");
                break;
        }
    }

        public void Login(){

            System.out.println("--- Insira suas credenciais para o login ---");

            System.out.print("E-mail: ");
            String emailInput = input.nextLine();

            System.out.print("Senha: ");
            String senhaInput = input.nextLine();


            if (AdminController.verificaCredenciaisAdmin(emailInput, senhaInput))
                admin.AdminPanel();
            else if (database.verificaCredenciaisCliente(emailInput, senhaInput))
                user.UserPanel();
            else
                System.out.println("Usuario não encontrado");
            Login();
        }
    }

