package Model;

import java.util.Date;

public class EntregaNaoPerecivel extends Entrega{
    private String material;
    private double preco = 0;

    private int drone;

    private String emailCliente;


    public EntregaNaoPerecivel(int id, String descricao, Date data,
                               double peso, String emailCliente, Localizacao origem, Localizacao destino, String material) {
        super(id, descricao, data, peso, 0, origem, destino);
        this.material = material;
        this.emailCliente = emailCliente;
    }
    public EntregaNaoPerecivel(int id, String descricao, Date data,
                               double peso, Cliente cliente, Localizacao origem, Localizacao destino, String material, Drone drone) {
        super(id, descricao, data, peso, 0, origem, destino, cliente, drone);
        this.material = material;
    }

    public EntregaNaoPerecivel(int id, String descricao, Date data,
                               double peso, Cliente cliente, Localizacao origem, Localizacao destino, String material) {
        super(id, descricao, data, peso, 0, origem, destino, cliente);
        this.material = material;
    }



    @Override
    public double calculaValor() {

        this.preco = this.peso*20 + 30*this.origem.getDistance(this.destino);

        return this.preco;

    }


}
