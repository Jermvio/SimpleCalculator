/** Class than helps find needed mathematical operator **/
public abstract class Operator {
    public static Operator getOperator(String operator) {
        if ("+".equals(operator)) {
            return new Plus();
        } else if ("-".equals(operator)) {
            return new Minus();
        } else if ("*".equals(operator)) {
            return new Multiply();
        } else if ("/".equals(operator)) {
            return new Divide();
        } else if ("^".equals(operator)) {
            return new Pow();
        } else {
            return null;
        }
    }

    /**
     * Function that will provide different calculations
     * @param a first number
     * @param b second number
     * @return result
     */
    public abstract double go(double a, double b);
}
