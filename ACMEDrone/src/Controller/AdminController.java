package Controller;

import Exceptions.DuplicateID;
import Model.DataRegistry;
import Model.Localizacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdminController {
    private final DataRegistry database = new DataRegistry();
    private Scanner input = new Scanner(System.in);

    public static boolean verificaCredenciaisAdmin(String email, String senha){
        if(email.equals("administracao@mail.com") && senha.equals("admin123"))
            return true;
        return false;
    }
    
    public void criaLocalizacao(){
        System.out.println("--- Cadastro de localizacao ---");
        System.out.print("Insira o codigo da localizacao: ");
        int loc = Integer.parseInt(input.nextLine());

        System.out.print("Insira o nome do logradouro: ");
        String endereco = input.nextLine();

        System.out.print("Insira a latitude: ");
        double lat = Double.parseDouble(input.nextLine());

        System.out.print("Insira a longitude: ");
        double lon = Double.parseDouble(input.nextLine());

        try{
            database.addLocalizacao(loc, endereco, lat, lon);
        } catch (DuplicateID e) {
            System.err.println(e.getMessage());
            System.out.println("[INFO] Tente novamente!");
            criaLocalizacao();
        }
    }

    public void criaDrone(){
        System.out.println("--- Cadastro de drone ---");
        System.out.print("Insira o identificador unico: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Insira a carga maxima do drone, em kg: ");
        double cargaMaxima = Double.parseDouble(input.nextLine());

        System.out.print("Insira a autonomia do drone, em km: ");
        double autonomiaKm = Double.parseDouble(input.nextLine());

        System.out.print("Insira a base do drone: ");
        int base = Integer.parseInt(input.nextLine());

        try{
            database.addDrone(id, cargaMaxima, autonomiaKm, database.getLocalizacaoByID(id));
        } catch (DuplicateID e) {
            System.err.println(e.getMessage());
            System.out.println("[INFO] Tente novamente!");
            criaDrone();
        }
    }

    public void criaCliente(){
        System.out.println("--- Cadastro de cliente ---");
        System.out.print("Insira o nome do cliente: ");
        String nome = input.nextLine();

        System.out.print("Insira o email do cliente: ");
        String email = input.nextLine();

        System.out.print("Insira a senha do cliente: ");
        String senha = input.nextLine();

        System.out.print("Insira a localizacao do cliente: ");
        int base = Integer.parseInt(input.nextLine());

        try{
            database.addCliente(nome, email, senha, database.getLocalizacaoByID(base));
        } catch (DuplicateID e) {
            System.err.println(e.getMessage());
            System.out.println("[INFO] Tente novamente!");
            criaCliente();
        }
    }

    public void criaEntrega() throws ParseException {
        System.out.println("--- Cadastro de Entrega ---");
        System.out.print("Insira o ID da entrega: ");
        int entregaID = Integer.parseInt(input.nextLine());

        System.out.print("Insira uma breve descricao do item: ");
        String descricao = input.nextLine();

        System.out.print("Insira o peso da entrega: ");
        double pesoEntrega = Double.parseDouble(input.nextLine());

        int tipoEntrega;
        do{
            System.out.println("Insira o tipo de entrega: ");
            System.out.println("1. Perecivel\t2.Nao Perecivel");
            tipoEntrega = Integer.parseInt(input.nextLine());
        }while(tipoEntrega<1 || tipoEntrega>2);

        System.out.println(" --- Lista de Clientes disponíveis ---");
        database.printAllClients();

        System.out.print("Insira o e-mail do cliente desejado: ");
        String email = input.nextLine();

        System.out.println(" --- Lista de Drones disponíveis ---");
        database.printAllAvailableDrones(email, pesoEntrega);

        System.out.print("Insira o ID do drone desejado: ");
        int droneID = Integer.parseInt(input.nextLine());

        if(tipoEntrega==1){
            System.out.println("Insira a date de validade do alimento perecivel: ");
            String dateString = input.nextLine();

            // Parsing date in day/month/year format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dateString);

            try{
                database.addEntregaPerecivel(entregaID, descricao, pesoEntrega,
                        email, droneID, data);
                System.out.println(" --- Entrega perecivel cadastrada ---");
            } catch (DuplicateID e) {
                System.err.println(e.getMessage());
                System.out.println("[INFO] Tente novamente!");
                criaEntrega();
            }
        } else{
            System.out.println("Insira uma breve descricao do material: ");
            String material = input.nextLine();

            try{
                database.addEntregaNaoPerecivel(entregaID, descricao, pesoEntrega,
                        email, droneID, material);
                System.out.println(" --- Entrega nao perecivel cadastrada ---");
            } catch (DuplicateID e) {
                System.err.println(e.getMessage());
                System.out.println("[INFO] Tente novamente!");
                criaEntrega();
            }
        }
    }
    
}
