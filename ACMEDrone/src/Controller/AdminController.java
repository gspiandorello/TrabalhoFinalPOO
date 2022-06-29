package Controller;

public class AdminController {

    public static boolean verificaCredenciaisAdmin(String email, String senha){
        if(email.equals("administracao@mail.com") && senha.equals("admin123"))
            return true;
        return false;
    }
}
