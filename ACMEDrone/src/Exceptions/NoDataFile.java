package Exceptions;

public class NoDataFile extends Exception{
    public NoDataFile(String errorMessage){
        super("\u001B[31m" + "[ERRO] Arquivo nao contem dados do sistema!" + "\u001B[0m");
    }
}

