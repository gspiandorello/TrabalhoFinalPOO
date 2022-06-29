package Model;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class ClienteTest {

    @org.junit.jupiter.api.BeforeAll
    static void preset(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Pedro Silva", "pedro@mail.com",
                "pedro", null));

        clientes.add(new Cliente("Pedro Silva", "pedro@mail.com",
                "pedro", null));


    }

    @org.junit.jupiter.api.Test
    void setEmail() {
        Cliente newClient = new Cliente("Pedro Silva", "pedro@mail.com",
                "pedro", null);
        String oldEmail = newClient.getEmail();

        newClient.setEmail("exemplo@mail.com");

        assertNotEquals(newClient.getEmail(), oldEmail);
    }

    @org.junit.jupiter.api.Test
    void setSenha() {
        Cliente newClient = new Cliente("Pedro Silva", "pedro@mail.com",
                "pedro", null);
        String oldPassword = newClient.getSenha();

        newClient.setSenha("pedrinho");

        assertNotEquals(newClient.getSenha(), oldPassword);
    }
}