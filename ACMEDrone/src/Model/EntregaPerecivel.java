package Model;

import java.util.Date;

public class EntregaPerecivel extends Entrega{
    private Date perecivelAte;


    public EntregaPerecivel(int id, String descricao, Date data,
                            double peso, String emailCliente, int origem, int destino, Date perecivelAte) {
        super(id, descricao, data, peso, 0, origem, destino);
        this.perecivelAte = perecivelAte;
    }

    @Override
    public double calculaValor() {
        //Localizacao locOrigem = getLocByID(this.origem);
        //Localizacao locDestino = getLocByID(this.destino);
        return 0.0;//(this.peso*20 + 30*locDestino.getDistance(locOrigem))*1.1;
    }
}
