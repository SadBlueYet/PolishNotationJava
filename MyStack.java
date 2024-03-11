import java.util.Map;
import java.util.Stack; 


public class MyStack {

    private Stack<Character> stack;

    public MyStack(){
        this.stack = new Stack<>();
    }

    public String processStack(char operator){

        StringBuilder stackOperators = new StringBuilder();

        boolean flag = false;
        int operatorPriority = getPriority(operator);

        do{
            
            if (this.stack.empty()){
                this.stack.push(operator);
                break;
            }
            int stackOperatorPriority = getPriority(this.stack.peek());
            if (this.stack.empty() || (this.stack.peek() == '(' && operator != ')') || operatorPriority > stackOperatorPriority) {
                this.stack.push(operator);
                flag = true;
            } else if (operator == ')' && this.stack.peek() == '(') {
                this.stack.pop();
                flag = true;  
            } else {
                stackOperators.append(this.stack.pop() + " ");
            }
        } while (!flag);
        return stackOperators.isEmpty() ? null : stackOperators.toString();
    }

    public String getStack(){
        StringBuilder stackString = new StringBuilder();
        do{
            try{
                stackString.append(this.stack.pop() + " ");
            } catch (java.util.EmptyStackException e){
                return "";
            }
            
        } while(!this.stack.empty());
        return stackString.toString();
    }

    private int getPriority(char operator) {
        Map<Integer, char[]> priority = Map.of(
            0, new char[]{')'},
            1, new char[]{'+', '-'},
            2, new char[]{'*', '/'},
            3, new char[]{'^'},
            4, new char[]{'!'},
            6, new char[]{'('}
        );
        boolean flag = false;

        for (Map.Entry<Integer, char[]> entry : priority.entrySet()) {
            for (char c : entry.getValue()) {
                if (c == operator) flag = true;
                if (flag) break;
            }
            if (flag) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
