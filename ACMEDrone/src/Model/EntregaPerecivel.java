package Model;

import java.util.Date;

public class EntregaPerecivel extends Entrega{
    private Date perecivelAte;


    public EntregaPerecivel(int id, String descricao, Date data,
                            double peso, String emailCliente, Localizacao origem, Localizacao destino, Date perecivelAte) {
        super(id, descricao, data, peso, 0, origem, destino);
        this.perecivelAte = perecivelAte;
    }

    @Override
    public double calculaValor() {
        Localizacao locOrigem = this.origem;
        Localizacao locDestino = this.destino;
        return 0.0;//(this.peso*20 + 30*locDestino.getDistance(locOrigem))*1.1;
    }
}
