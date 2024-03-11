import java.lang.Math;

public class PolishNotation {
    
    private MathOperations math;

    public PolishNotation(){
        this.math = new MathOperations();
    }

    public String[] addSpaces(String userString){
        return userString.split(" ");
    }

    public String convertExpression(String userString){
        StringBuilder convertString = new StringBuilder();
        String mathOperands = "cCsStToOiIqQrRlLgG";

        for (int i = 0; i < userString.length(); i++) {
            char currentChar = userString.charAt(i);
            char previousChar = i > 0 ? userString.charAt(i - 1) : '\0'; // Using null character for initial previousChar
    
            // Check if a space is needed before appending the current character
            if (i > 0 && ((Character.isDigit(currentChar) && !Character.isDigit(previousChar) && previousChar != '.') || 
                (!Character.isDigit(currentChar) && currentChar != '.' && Character.isDigit(previousChar)) || 
                currentChar == '(' || previousChar == ')' || previousChar == '!' || 
                (mathOperands.contains(String.valueOf(currentChar)) &&
                !mathOperands.contains(String.valueOf(previousChar))))) {

                if (currentChar == '(' && Character.isDigit(previousChar)) {
                    convertString.append(" * ");

                }
                else convertString.append(" ");
            }
    
            convertString.append(currentChar);
        }
        
        return convertString.toString();
    }

    public double processExpression(String[] tokens){
        StringBuilder exitString = new StringBuilder();
        MyStack stack = new MyStack();
        for (int i = 0; i < tokens.length; i++){
            try {
                Double.parseDouble(tokens[i]);
                exitString.append(tokens[i] + " ");
            } catch (NumberFormatException e) {
                String token = tokens[i].toLowerCase();
                if (this.check(token)) {

                    StringBuilder str = new StringBuilder();
                    int counterOpen = 0;
                    int counterClose = 0;
                    boolean checker = false;

                    for (i = i + 1;i < tokens.length; i++){

                        if (checker) {
                            if (tokens[i].equals("(")) str.append(tokens[i] + " ");
                            if (tokens[i].equals(")")) {
                                str.append(" " + tokens[i]);
                                checker = false;
                            }
                        }

                        if (tokens[i].equals(")")) {
                            counterClose++; 
                            continue;
                        }else if (tokens[i].equals("(")) {
                            counterOpen++;
                            continue;
                        }

                        if (counterOpen == counterClose) {
                            i--;
                            break;
                        }
                        if (this.check(tokens[i])) checker = true;
                        
                        str.append(tokens[i] + " ");
                    }
                    double num = this.processExpression(str.toString().split(" "));
                    exitString.append(this.calculation(token, num));
                } else {
                    String operatorsString = stack.processStack(tokens[i].charAt(0));
                    if (operatorsString == null) continue;
                    exitString.append(operatorsString);
                }
            }
        }
        return this.math.calculate(exitString.toString() + stack.getStack());
    }

    private String calculation(String token, double num){
        switch (token) {
            case "sin":
                return String.valueOf(Math.sin(num)) + " ";
            case "cos":
                return String.valueOf(Math.cos(num)) + " ";
            case "tan":
                return String.valueOf(Math.tan(num)) + " ";
            case "sqrt":
                return String.valueOf(Math.sqrt(num)) + " ";
            case "ln":
                return String.valueOf(Math.log(num)) + " ";
            case "lg":
                return String.valueOf(Math.log10(num)) + " ";
            default:
                return null;
        }
    }
    private boolean check(String token){

        if (token.equals("sin") || 
            token.equals("cos") || 
            token.equals("tan") || 
            token.equals("sqrt") ||
            token.equals("ln") ||
            token.equals("lg")){
                return true;
        }
        return false;
    }


}
