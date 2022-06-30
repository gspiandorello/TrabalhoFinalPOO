package Model;

import Exceptions.DuplicateID;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class DataRegistry {
    private static ArrayList<Cliente> clientesList = new ArrayList<>();
    private ArrayList<Drone> dronesList = new ArrayList<>();
    private static ArrayList<Entrega> entregasList = new ArrayList<>();
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

    public Entrega getEntregaByID(int id){
        for(Entrega e : entregasList){
            if(e.getNumero()==id) return e;
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

    public Cliente getClientByEmail(String email){
        for(Cliente c : clientesList){
            if(c.getEmail().equals(email)) return c;
        }
        return null;
    }

    public Localizacao getLocByEmail(String email){
        for(Cliente c : clientesList){
            if(c.getEmail().equals(email)) return c.getLocalizacao();
        }
        return null;
    }

    public static boolean verificaCredenciaisCliente(String email, String senha){
        for(Cliente c : clientesList){
            if(email.equals(c.getEmail()) && senha.equals(c.getSenha()))
                return true;
        }
        return false;
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
    public boolean printAllAvailableDrones(String email, double peso) {
        Localizacao locDestino = getClienteByEmail(email).getLocalizacao();
        boolean flag=false;
        for (Drone drone : dronesList) {
            Localizacao locBase = drone.getBase();
            double deltaDistance = locBase.getDistance(locDestino);

            if (drone.getAutonomiaKm() >= deltaDistance && drone.getCargaMaxima() >= peso){
                System.out.println(drone);
                flag=true;
            }
        }
        return flag;
    }

    public Localizacao getDroneLocByID(int ID){
        for (Drone d:
             this.dronesList) {
            if(d.getIdentificador()==ID) return d.getBase();
        }
        return null;
    }
    public Drone getDroneByID(int ID){
        for (Drone d:
             this.dronesList) {
            if(d.getIdentificador()==ID) return d;
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

    public void addEntregaPerecivel(int entregaID, String descricao, Date date, double pesoEntrega,
                                    String emailCliente, Localizacao origem, Localizacao destino,
                                    Date data, int droneID) throws ParseException, DuplicateID {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dataHojeString = sdf.format(new Date());
        Date dataHoje = sdf.parse(dataHojeString);

        if(checkEntregaID(entregaID)){
            entregasList.add(new EntregaPerecivel(entregaID, descricao, date,
                    pesoEntrega, getClienteByEmail(emailCliente), origem, destino, data, getDroneByID(droneID)));
            //int id, String descricao, Date data,
            //                            double peso, Cliente cliente, Localizacao origem, Localizacao destino, Date perecivelAte, Drone drone
        } else{
            throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para entregas.");
        }
    }

    public void addEntregaNaoPerecivel(int entregaID, String descricao, Date date, double pesoEntrega,
                                       String email, Localizacao origem, Localizacao destino,
                                       String material, int idDrone) throws ParseException, DuplicateID {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dataHojeString = sdf.format(new Date());
        Date dataHoje = sdf.parse(dataHojeString);

        if(checkEntregaID(entregaID)){
            entregasList.add(new EntregaNaoPerecivel(entregaID, descricao, dataHoje,
                    pesoEntrega, getClienteByEmail(email), origem, destino, material, getDroneByID(idDrone)));
            System.out.println(getEntregaByID(entregaID));
        } else{
            throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para entregas.");
        }
    }

    public void queryAllEntregas(){
        for(Cliente c : clientesList){
            for(Entrega entrega : entregasList){
                if(entrega.cliente.getEmail().equals(c.getEmail())){
                    System.out.println(c + "\t" + entrega.getDrone() + "\t"+ entrega.calculaValor());
                }
            }
        }
    }

    public void procuraEntrega(String email) {
        for (Entrega entrega : entregasList) {
            if (entrega.cliente.getEmail().equals(email)) {
                System.out.println(entrega.cliente + "\t" + entrega.getDrone() + "\t" + entrega.calculaValor());
            }
        }
    }

    public void procuraData(String dataString, String email) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Date data = sdf.parse(dataString);
    }

    public void readClientsData(String nomeArquivo) throws IOException {
        Path path = Paths.get("data/"+nomeArquivo);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line = null;
        reader.readLine();
        while((line = reader.readLine()) != null){
            String[] temp = line.split(";");
            String nome = temp[0];
            String email = temp[1];
            String senha = temp[2];
            String codigoLocalizacao = temp[3];
            try{
                addCliente(nome, email, senha, getLocalizacaoByID(Integer.parseInt(codigoLocalizacao)));
                System.out.println(getClienteByEmail(email));
            } catch (DuplicateID e){
                System.out.println("[ERRO] E-mail ja adicionado em colecao de dados para clientes.");
            }
        }
    }

    public void readDroneData(String nomeArquivo) throws IOException {
        Path path = Paths.get("data/"+nomeArquivo);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line = null;
        reader.readLine();
        while((line = reader.readLine()) != null){
            String[] temp = line.split(";");
            String identificador = temp[0];
            String cargaMaxima = temp[1];
            String autonomia = temp[2];
            String codigoLocalizacao = temp[3];
            try{
                addDrone(Integer.parseInt(identificador), Double.parseDouble(cargaMaxima), Double.parseDouble(autonomia),
                        getLocalizacaoByID(Integer.parseInt(codigoLocalizacao)));
                System.out.println(getDroneByID(Integer.parseInt(identificador)));
            } catch (DuplicateID e){
                System.out.println("[ERRO] ID ja adicionado em colecao de dados para drones.");
            }
        }
    }

    public void readDeliveryData(String nomeArquivo) throws IOException, ParseException{
        Path path = Paths.get("data/"+nomeArquivo);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line = null;
        reader.readLine();
        while((line = reader.readLine()) != null){
            String[] temp = line.split(";");
            String tipo = temp[0];
            String numero = temp[1];
            String descricao = temp[2];
            String data = temp[3];
            String peso = temp[4];
            String emailCliente = temp[5];
            String codigoLocalizacaoOrigem = temp[6];
            String codigoLocalizacaoDestino = temp[7];
            String validadeOuMaterial = temp[8];
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dataHojeString = sdf.format(new Date());
            Date dataHoje = sdf.parse(dataHojeString);
            try{
                if(validadeOuMaterial.contains("/")){
                    if(checkEntregaID(Integer.parseInt(numero))){
                        entregasList.add(new EntregaPerecivel(Integer.parseInt(numero), descricao, dataHoje,
                                Double.parseDouble(peso), getClienteByEmail(emailCliente), getLocalizacaoByID(Integer.parseInt(codigoLocalizacaoOrigem)),
                                getLocalizacaoByID(Integer.parseInt(codigoLocalizacaoDestino)), sdf.parse(validadeOuMaterial)));
                    } else{
                        throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para entregas.");
                    }
                }
                else{
                    if(checkEntregaID(Integer.parseInt(numero))){
                        EntregaNaoPerecivel entrega = new EntregaNaoPerecivel(Integer.parseInt(numero), descricao, dataHoje,
                                Double.parseDouble(peso), getClienteByEmail(emailCliente), getLocalizacaoByID(Integer.parseInt(codigoLocalizacaoOrigem)),
                                getLocalizacaoByID(Integer.parseInt(codigoLocalizacaoDestino)), validadeOuMaterial);
                        entregasList.add(entrega);
                    } else{
                        throw new DuplicateID("[ERRO] ID ja adicionado em colecao de dados para entregas.");
                    }
                }
                System.out.println(getEntregaByID(Integer.parseInt(numero)));
            } catch (DuplicateID e){
                System.out.println("[ERRO] E-mail ja adicionado em colecao de dados para clientes.");
            }
        }
    }

    public void readLocalizationsData(String nomeArquivo) throws IOException {
        Path path = Paths.get("data/"+nomeArquivo);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line = null;
        reader.readLine();
        while((line = reader.readLine()) != null){
            String[] temp = line.split(";");
            String codigo = temp[0];
            String logradouro = temp[1];
            String latitude = temp[2];
            String longitude = temp[3];
            try{
                addLocalizacao(Integer.parseInt(codigo), logradouro, Double.parseDouble(latitude), Double.parseDouble(longitude));
                System.out.println(getLocalizacaoByID(Integer.parseInt(codigo)));
            } catch (DuplicateID e){
                System.out.println("[ERRO] E-mail ja adicionado em colecao de dados para localizacao.");
            }
        }
    }

}
