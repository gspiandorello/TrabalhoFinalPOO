package Controller;

import Exceptions.DuplicateID;
import Model.DataRegistry;
import Model.Entrega;
import Model.Localizacao;

import java.io.IOException;
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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

        System.out.print("Insira o codigo da localizacao do cliente: ");
        int base = Integer.parseInt(input.nextLine());

        try{
            database.addCliente(nome, email, senha, database.getLocalizacaoByID(base));
        } catch (DuplicateID e) {
            System.out.println(e.getMessage());
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

        System.out.print("Insira o peso da entrega (em kg): ");
        double pesoEntrega = Double.parseDouble(input.nextLine());

        System.out.println("Insira a situação da entrega: ");
        System.out.println("1 - Cadastrada");
        System.out.println("2 - Entregue");
        System.out.println("3 - Cancelada");
        int situacaoEntrega = Integer.parseInt(input.nextLine());

        String tipoEntrega;
        do{
            System.out.println("Insira o tipo de entrega: ");
            System.out.println("1. Perecivel\t2.Nao Perecivel");
            tipoEntrega = input.nextLine();
        }while(!tipoEntrega.equals("1") && !tipoEntrega.equals("2"));

        System.out.println(" --- Lista de Clientes disponiveis ---");
        database.printAllClients();

        System.out.print("Insira o e-mail do cliente desejado: ");
        String email = input.nextLine();

        System.out.println(" --- Lista de Drones disponiveis ---");
        if (!database.printAllAvailableDrones(email, pesoEntrega)){
            System.out.println("[ERRO] Nenhum drone disponivel - entrega cancelada");
            situacaoEntrega = 3;
        };

        System.out.print("Insira o ID do drone desejado: ");
        int droneID = Integer.parseInt(input.nextLine());

        if(tipoEntrega.equals("1")){
            System.out.println("Insira a date de validade do alimento perecivel: ");
            String dateString = input.nextLine();

            // Parsing date in day/month/year format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dateString);


            String dataHojeString = sdf.format(new Date());
            Date dataHoje = sdf.parse(dataHojeString);

            try{
                    database.addEntregaPerecivel(entregaID, descricao, dataHoje, pesoEntrega,
                            email, database.getLocalizacaoByID(droneID),
                            database.getLocByEmail(email), data, droneID);
                System.out.println(" --- Entrega perecivel cadastrada ---");
            } catch (DuplicateID e) {
                System.out.println(e.getMessage());
                System.out.println("[INFO] Tente novamente!");
                criaEntrega();
            }
        } else{
            System.out.println("Insira uma breve descricao do material: ");
            String material = input.nextLine();

            try{
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                String dataHojeString = sdf.format(new Date());
                Date dataHoje = sdf.parse(dataHojeString);

                database.addEntregaNaoPerecivel(entregaID, descricao, dataHoje, pesoEntrega,
                        email, database.getLocalizacaoByID(droneID),
                        database.getLocByEmail(email), material, droneID);
                System.out.println(" --- Entrega nao perecivel cadastrada ---");
            } catch (DuplicateID e) {
                System.out.println(e.getMessage());
                System.out.println("[INFO] Tente novamente!");
                criaEntrega();
            }
        }
    }

    public void getTodasEntregas(){
        database.queryAllEntregas();
    }

    public void simulaDados() throws IOException, ParseException {
        System.out.println("Digite o nome do arquivo (com extensao): ");
        System.out.println("Exemplo: \"TESTE-localizacoes.dat\"");
        System.out.println("Exemplo: \"TESTE-drones.dat\"");
        System.out.println("Exemplo: \"TESTE-clientes.dat\"");
        System.out.println("Exemplo: \"TESTE-entregas.dat\"");
        String nomeArquivo = input.nextLine();
        if(nomeArquivo.contains("cliente")){
            database.readClientsData(nomeArquivo);
        }
        else if(nomeArquivo.contains("drone")){
            database.readDroneData(nomeArquivo);
        }
        else if(nomeArquivo.contains("entrega")){
            database.readDeliveryData(nomeArquivo);
        }
        else if(nomeArquivo.contains("localiza")){
            database.readLocalizationsData(nomeArquivo);
        }

    }
    
}
