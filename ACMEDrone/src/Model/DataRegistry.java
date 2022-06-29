package Model;

import Exceptions.DuplicateID;
import Exceptions.NoDataFile;
import Helper.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static Helper.Parser.*;

public class DataRegistry {
    private ArrayList<Cliente> clientesList = new ArrayList<>();
    private ArrayList<Drone> dronesList = new ArrayList<>();
    private ArrayList<Entrega> entregasList = new ArrayList<>();
    private ArrayList<Localizacao> localizacaosList = new ArrayList<>();

    public void addLocalizacao(int loc, String endereco, double lat, double lon) throws DuplicateID {
        if(checkLocalizacaoID(loc))
            localizacaosList.add(new Localizacao(loc, endereco, lat, lon));
        else{
            throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para localizacao.");
        }
    }

    public void addDrone(int identificador, double cargaMaxima, double autonomiaKm, Localizacao base) throws DuplicateID {
        if(checkDroneID(identificador))
            dronesList.add(new Drone(identificador,
                    cargaMaxima, autonomiaKm, base));
        else{
            throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para drones.");
        }
    }

    public void addCliente(String nome, String email, String senha, Localizacao localizacaoByID) throws DuplicateID {
        if(checkClientEmail(email)){
            clientesList.add(new Cliente(nome, email, senha, localizacaoByID));
        }else{
            throw new DuplicateID("[ERRO] E-mail ja adicionado em colecao de dados para clientes.");
        }
    }

    public Localizacao getLocalizacaoByID(int loc){
        for(Localizacao l : localizacaosList){
            if(l.getCodigo()==loc) return l;
        }
        return null;
    }

    private boolean checkLocalizacaoID(int loc){
        for(Localizacao l : localizacaosList){
            if(l.getCodigo()==loc) return false;
        }
        return true;
    }

    private boolean checkDroneID(int id){
        for(Drone d : dronesList){
            if(d.getIdentificador()==id) return false;
        }
        return true;
    }

    private boolean checkClientEmail(String email){
        for(Cliente c : clientesList){
            if(c.getEmail().equals(email)) return false;
        }
        return true;
    }


    public void printAllClients() {
        for(Cliente c : clientesList){
            System.out.println(c);
        }
    }

    private Cliente getClienteByEmail(String email){
        for(Cliente c : clientesList){
            if(c.getEmail().equals(email)) return c;
        }
        return null;
    }

    /**
     * A partir do peso e do email, calcula a distancia e ve se é menor que carga máxima
     * @param email
     * @param peso
     */
    public void printAllAvailableDrones(String email, double peso) {
        Localizacao locDestino = getClienteByEmail(email).getLocalizacao();

        for (Drone drone : dronesList) {
            Localizacao locBase = drone.getBase();
            double deltaDistance = locBase.getDistance(locDestino);

            if (drone.getAutonomiaKm() >= deltaDistance)
                System.out.println(drone);
        }
    }

    public Localizacao getDroneLocByID(int ID){
        for (Drone d:
             this.dronesList) {
            if(d.getIdentificador()==ID) return d.getBase();
        }
        return null;
    }

    private boolean checkEntregaID(int deliveryID){
        for (Entrega delivery:
             this.entregasList) {
            if(delivery.getNumero() == deliveryID)
                return false;
        }
        return true;
    }

    public void addEntregaPerecivel(int entregaID, String descricao, double pesoEntrega,
                                    String email, int droneID, Date data) throws ParseException, DuplicateID {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dataHojeString = sdf.format(new Date());
        Date dataHoje = sdf.parse(dataHojeString);

        Localizacao origem = getClienteByEmail(email).getLocalizacao();
        Localizacao destino = getDroneLocByID(droneID);

        if(checkEntregaID(entregaID)){
            entregasList.add(new EntregaPerecivel(entregaID, descricao, dataHoje,
                    pesoEntrega, email, origem, destino, data));
        } else{
            throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para entregas.");
        }
    }

    public void addEntregaNaoPerecivel(int entregaID, String descricao,
                                       double pesoEntrega, String email,
                                       int droneID, String material) throws ParseException, DuplicateID {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dataHojeString = sdf.format(new Date());
        Date dataHoje = sdf.parse(dataHojeString);

        Localizacao origem = getClienteByEmail(email).getLocalizacao();
        Localizacao destino = getDroneLocByID(droneID);

        if(checkEntregaID(entregaID)){
            entregasList.add(new EntregaNaoPerecivel(entregaID, descricao, dataHoje,
                    pesoEntrega, email, origem, destino, material));
        } else{
            throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para entregas.");
        }
    }

    public void queryAllEntregas(){
        for(Cliente c : clientesList){
            for(Entrega entrega : this.entregasList){
                if(entrega.getEmail().equals(c.getEmail())){
                    System.out.println(c + "\t" + entrega.getDrone() + "\t"+ entrega.calculaValor());
                }
            }
        }
    }

    public void loadFromFile(String filename) throws NoDataFile {
        if(filename.contains("clientes")){
            // READ CLIENTES
            try{
                ArrayList<Cliente> clientsRead = readClientsData(filename);
                this.clientesList.addAll(clientsRead);
            } catch (Exception err){
                System.out.println("ABC");
            }

        } else if (filename.contains("drones")) {
            // READ DRONES
            try{
                ArrayList<Drone> dronesRead = readDroneData(filename);
                this.dronesList.addAll(dronesRead);
            } catch (Exception err){
                System.out.println("ABC");
            }
        } else if (filename.contains("entregas")) {
            // READ ENTREGAS
            try{
                ArrayList<Entrega> entregasRead = readEntregasData(filename);
                this.entregasList.addAll(entregasRead);
            } catch (Exception err){
                System.out.println("ABC");
            }
        } else if (filename.contains("localizacoes")) {
            // READ LOCALIZACOES
            try{
                ArrayList<Localizacao> localizacaosRead = readLocalizacoesData(filename);
                this.localizacaosList.addAll(localizacaosRead);
            } catch (Exception err){
                System.out.println("ABC");
            }
        } else {
            throw new NoDataFile("Sem dados do sistema.");
        }
    }
}
