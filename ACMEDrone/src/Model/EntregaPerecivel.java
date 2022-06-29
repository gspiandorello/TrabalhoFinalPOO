package Model;

import java.util.Date;

public class EntregaPerecivel extends Entrega{
    private Date perecivelAte;
    private double preco = 0;


    public EntregaPerecivel(int id, String descricao, Date data,
                            double peso, String emailCliente, Localizacao origem, Localizacao destino, Date perecivelAte) {
        super(id, descricao, data, peso, 0, origem, destino);
        this.perecivelAte = perecivelAte;
    }



    @Override
    public double calculaValor() {

        this.preco = (this.peso*20 + 30*this.origem.getDistance(this.destino))*1.1;

        return this.preco;
    }
}
