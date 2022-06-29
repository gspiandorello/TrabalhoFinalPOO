package Model;

import java.util.Date;

public class EntregaNaoPerecivel extends Entrega{
    private String material;
    private double preco = calculaValor();


    public EntregaNaoPerecivel(int id, String descricao, Date data,
                               double peso, String emailCliente, Localizacao origem, Localizacao destino, String material) {
        super(id, descricao, data, peso, 0, origem, destino);
        this.material = material;
    }

    @Override
    public double calculaValor() {
        return (this.peso*20 + 30*this.origem.getDistance(this.destino));
    }
}
