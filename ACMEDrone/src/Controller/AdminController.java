package Controller;

import Exceptions.DuplicateID;
import Model.DataRegistry;

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
    
}
