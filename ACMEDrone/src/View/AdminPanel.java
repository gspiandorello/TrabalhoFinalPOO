package View;

import Controller.AdminController;
import Exceptions.DuplicateID;
import Helper.Parser;
import Model.DataRegistry;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class AdminPanel {

    public void AdminPanel(){
        DataRegistry database = new DataRegistry();
        AdminController adminController = new AdminController();
        App app = new App();
        String menuOption;

        do{
            System.out.println(" ------------ PAINEL DE ADMINISTRADOR ------------ ");

            System.out.println("Salvar dados cadastrados.........................0");
            System.out.println("Cadastrar nova localizacao.......................1");
            System.out.println("Cadastrar novo drone.............................2");
            System.out.println("Cadastrar novo cliente...........................3");
            System.out.println("Cadastrar nova entrega...........................4");
            System.out.println("Consultar todas entregas.........................5");
            System.out.println("Simular carga de dados...........................6");
            System.out.println("Voltar ao MENU INICIAL...........................7");

            System.out.print("Insira uma opcao do menu: ");

            Scanner input = new Scanner(System.in);
            menuOption = input.nextLine();

            switch (menuOption){
                case "0":
                    // iniciar o salvamento se foi realizado algo
                case "1":
                    // Cadastra nova localização no banco de dados
                    adminController.criaLocalizacao();
                    break;
                case "2":
                    // Cadastrar novo drone
                    adminController.criaDrone();
                    break;
                case "3":
                    // Cadastrar novo cliente
                    adminController.criaCliente();
                    break;
                case "4":
                    // Cadastrar nova entrega
                    try{
                        adminController.criaEntrega();
                    } catch (Exception e){
                        System.out.println("Testa isso:"+e.getMessage());
                    }
                    break;
                case "5":
                    // Consultar todas entregas
                    adminController.getTodasEntregas();
                    break;
                case "6":
//                    System.out.println("Digite o nome do arquivo com extensao: ");
//                    String nomeArquivo = input.nextLine();
                    try{
                      adminController.simulaDados();
                    } catch (IOException | ParseException e){
                        System.out.println("Ocorreu um erro ao ler o arquivo.");
                    }
                    break;
                    // Carregar a partir de .dat e mostrar na tela
                case "7":
                    app.menuInicial();
                    break;
                default:
                    System.out.println();
                    System.out.println("Opcao inválida! Redigite.");
            }
        }while(!menuOption.equals("7"));
    }
}
