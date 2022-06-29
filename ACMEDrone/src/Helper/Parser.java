package Helper;

import Model.Cliente;
import Model.Drone;
import Model.Entrega;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public final class Parser {
    public static Integer parseIntegerFromString(String s){
        try{
            return Integer.parseInt(s);
        } catch (InputMismatchException inputException){
            System.out.println("Input type incorrect, insert a Integer value.");
        }
        return -1;
    }

    public static Double parseDoubleFromString(String s){
        try{
            return Double.parseDouble(s);
        } catch (InputMismatchException inputException){
            System.out.println("Input type incorrect, insert a Double value ('.' as decimal separator).");
        }
        return -1.;
    }

    public static ArrayList<Cliente> readClientsData(String filename) throws FileNotFoundException, Exception {
        ArrayList<Cliente> collection = new ArrayList<>();

        // Create a Buffered Reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get("data/"+filename))) {
            // CSV file delimiter
            String DELIMITER = ";";
            // Read file line by line
            String line;
            // Skips the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Convert line into columns
                ArrayList<String> columns = (ArrayList<String>) List.of(line.split(DELIMITER));
                collection.add(Cliente.constructFromStrings(columns));
            }
        }
        return collection;
    }

    public static ArrayList<Drone> readDroneData(String filename) throws FileNotFoundException, Exception {
        ArrayList<Drone> collection = new ArrayList<>();

        // Create a Buffered Reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get("data/"+filename))) {
            // CSV file delimiter
            String DELIMITER = ";";
            // Read file line by line
            String line;
            // Skips the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Convert line into columns
                ArrayList<String> columns = (ArrayList<String>) List.of(line.split(DELIMITER));
                collection.add(Drone.constructFromStrings(columns));
            }
        }
        return collection;
    }

    public static ArrayList<Entrega> readEntregasData(String filename) throws FileNotFoundException, Exception {
        ArrayList<Entrega> collection = new ArrayList<>();

        // Create a Buffered Reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get("data/"+filename))) {
            // CSV file delimiter
            String DELIMITER = ";";
            // Read file line by line
            String line;
            // Skips the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Convert line into columns
                ArrayList<String> columns = (ArrayList<String>) List.of(line.split(DELIMITER));
                collection.add(Entrega.constructFromStrings(columns));

            }
        }
        return collection;
    }
}
