package Model;

import java.util.Date;

public class EntregaNaoPerecivel extends Entrega{
    private String material;


    public EntregaNaoPerecivel(int id, String descricao, Date data,
                               double peso, String emailCliente, Localizacao origem, Localizacao destino, String material) {
        super(id, descricao, data, peso, 0, origem, destino);
        this.material = material;
    }

    @Override
    public double calculaValor() {
        //Localizacao locOrigem = HashLocalizacao.getLocByID(this.origem);
        //Localizacao locDestino = HashLocalizacao.getLocByID(this.destino);
        return 0.0;//(this.peso*20 + 30*locDestino.getDistance(locOrigem));
    }
}
