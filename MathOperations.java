import java.util.Stack;
import java.lang.Math;

public class MathOperations {
    
    public double calculate(String expression){
        String[] tokens = expression.split(" ");
        Stack<Double> numbers = new Stack<>();
        double left, right;
        char operator;
        for (String token : tokens){
            try{
                numbers.push(Double.parseDouble(token));
            }catch(NumberFormatException e){
                operator = token.charAt(0);
                if (operator == '!'){
                    right = numbers.pop();
                    numbers.push((double)this.factorial((int)right));
                    continue;
                }
                
                right = numbers.pop();
                left = numbers.pop();
                switch (operator){
                    case '+':
                        numbers.push(left + right);
                        break;
                    case '-':
                        numbers.push(left - right);
                        break;
                    case '*':
                        numbers.push(left * right);
                        break;
                    case '/':
                        numbers.push(left / right);
                        break;
                    case '^':
                        numbers.push(Math.pow(left, right));
                        break;
                }
            }
            
        }
        return numbers.pop();
    }

    private int factorial(int n){
        int result = 1;
        for (int i = 1; i <= n; i++){
            result *= i;
        }
        return result;
    }
}
