import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        System.out.println(calculator.solve(scaner.nextLine()));
    }

    /**
     * Solves equation and shows answer
     *
     * @param line line of the equation
     */
    public String solve(String line) {
        ArrayList<String> equation = makeReversePolishNotation(line);
        return solveReversePolishNotation(equation);
    }

    /**
     * Make reversed polish notation out of equation
     * @param line line of the equation
     * @return list of separated elements of equation
     */
    private static ArrayList<String> makeReversePolishNotation(String line) {
        // Operators priority map
        HashMap<String, Integer> operatorsToPriority = new HashMap<>();
        operatorsToPriority.put("+", 0);
        operatorsToPriority.put("-", 0);
        operatorsToPriority.put("*", 1);
        operatorsToPriority.put("/", 1);
        operatorsToPriority.put("^", 2);

        ArrayList<String> formula = getFormula(line);
        ArrayList<String> result = new ArrayList<>();

        Stack<String> stack = new Stack<>();
        for (String element : formula) { // checks every element
            if (isNumber(element)) {
                result.add(element); //adds to result list
            }

            if (element.equals("unarMinus")) {
                stack.push(element);  //adds to stack
            }

            if (isOperator(element)) {
                while ((!stack.empty()) // skips from stack to result operators with higher priority
                        && ((stack.peek().equals("unarMinus"))
                        || (operatorsToPriority.get(stack.peek()) >= operatorsToPriority.get(element)))) {
                    result.add(stack.pop());
                }
                stack.push(element); // than adds current operator to stack
            }

        }

        // Flushes remaining stack to result
        for (int i = 0; i <= stack.size(); i++) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * Makes list of separated elements of equation, using Strings
     * @param line  line of the equation
     * @return list of separated elements
     */
    private static ArrayList<String> getFormula(String line) {
        ArrayList<String> formulaList = new ArrayList<>();

        StringBuilder stack = new StringBuilder();
        char lastSymbol = 0;
        for (char symbol : // formats main string and checks every symbol
                line.replaceAll(" ", "").replaceAll(",", ".").toCharArray()) {
            if (symbol == '-' && !Character.isDigit(lastSymbol)) { // finds unarMinus
                formulaList.add("unarMinus");
                continue;
            }

            if ((Character.isDigit(symbol) || symbol == '.')) { // finds and connect numbers
                stack.append(symbol);
            } else {
                formulaList.add(stack.toString());
                formulaList.add(Character.toString(symbol));
                stack = new StringBuilder();
            }
            lastSymbol = symbol;
        }
        formulaList.add(stack.toString());

        return formulaList;
    }

    /**
     * Solves equation using reversed polish notation
     * @param equation list of separated elements of equation recorded in reversed polish notation
     * @return equation result
     */
    private static String solveReversePolishNotation(ArrayList<String> equation) {
        Stack<String> stack = new Stack<>();

        for (String element : equation) {
            if (isNumber(element)) {
                stack.push(element);
            }
            if (element.equals("unarMinus")) { // calculates function
                double x = Double.parseDouble(stack.pop());
                x = -x;
                stack.push(Double.toString(x));
            }
            if (isOperator(element)) { //calculates operators
                Operator operator = Operator.getOperator(element);
                double y = Double.parseDouble(stack.pop());
                double x = Double.parseDouble(stack.pop());
                stack.push(Double.toString(operator.go(x, y)));

            }
        }

        return stack.peek();
    }

    /**
     * Checks if string is one of mathematics operator
     * @param element string we need to check
     * @return true\false
     */
    private static boolean isOperator(String element) {
        String[] operators = {"+", "-", "*", "/", "^"};
        return Arrays.asList(operators).contains(element);
    }

    /**
     * Checks if string can be number
     * @param element string  we need to check
     * @return true\false
     */
    private static boolean isNumber(String element) {
        for (char symbol : element.toCharArray()) {
            if (!Character.isDigit(symbol) && symbol != '.') {
                return false;
            }
        }
        return true;
    }
}
