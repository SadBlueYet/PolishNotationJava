import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        String userString = in.next();
        
        PolishNotation polishNotation = new PolishNotation();
        String[] tokens = polishNotation.addSpaces(polishNotation.convertExpression(userString));
        double exitString = polishNotation.processExpression(tokens);
        System.out.println(exitString);
        in.close();
    }
}
    