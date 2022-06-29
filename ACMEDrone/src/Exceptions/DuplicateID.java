package Exceptions;

public class DuplicateID extends Exception{
    public DuplicateID(String errorMessage){
        super("\u001B[31m" + "[ERRO] Item ja duplicado em colecao, tente novamente!" + "\u001B[0m");
    }
}
