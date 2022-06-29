package Helper;

import java.util.InputMismatchException;

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
}
