package View;

import Controller.AdminController;
import Exceptions.DuplicateID;
import Helper.Parser;
import Model.DataRegistry;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class AdminPanel {

    public AdminPanel(){
        AdminController adminController = new AdminController();
        int menuOption;


        do{
            System.out.println(" ------------ PAINEL DE ADMINISTRADOR ------------ ");

            System.out.println("Salvar e encerrar................................0");
            System.out.println("Cadastrar nova localizacao.......................1");
            System.out.println("Cadastrar novo drone.............................2");
            System.out.println("Cadastrar novo cliente...........................3");
            System.out.println("Cadastrar nova entrega...........................4");
            System.out.println("Consultar todas entregas.........................5");
            System.out.println("Simular carga de dados...........................6");

            System.out.print("Insira uma opcao do menu: ");

            // InputMismatch exception
            Scanner input = new Scanner(System.in);
            menuOption = input.nextInt();

            switch (menuOption){
                case 0:
                    // iniciar o salvamento se foi realizado algo
                case 1:
                    // Cadastra nova localização no banco de dados
                    adminController.criaLocalizacao();
                    break;
                case 2:
                    // Cadastrar novo drone
                    adminController.criaDrone();
                    break;
                case 3:
                    // Cadastrar novo cliente
                    adminController.criaCliente();
                    break;
                case 4:
                    // Cadastrar nova entrega
                    try{
                        adminController.criaEntrega();
                    } catch (Exception e){
                        System.out.println("Cadastro vazio, tente novamente");
                    }
                    break;
                case 5:
                    // Consultar todas entregas
                    adminController.getTodasEntregas();
                    break;
                case 6:
                    // Carregar a partir de .dat e mostrar na tela
                    adminController.readFromDataFiles();
            }
        }while(menuOption <1 || menuOption > 5);

    }
}
