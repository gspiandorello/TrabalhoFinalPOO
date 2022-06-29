package Model;

import Exceptions.DuplicateID;

import java.util.ArrayList;

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

}
