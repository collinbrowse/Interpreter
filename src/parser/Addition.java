package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Addition extends Expression {

    private Multiplication leftOperand;
    private ArrayList<Multiplication> rightOperand = new ArrayList();
    private ArrayList<Token> operators = new ArrayList();

    /**
     * Addition -> Multiplication (PLUS|MINUS Multiplication)*
     * @param tokenizer To provide tokens
     */

    public Addition(Tokenizer tokenizer) {

        // Mutliplication
        leftOperand = new Multiplication(tokenizer);

        // (PLUS|MINUS Multiplication)*
        while (tokenizer.hasNext(Token.Type.PLUS, Token.Type.MINUS)) {
            operators.add(tokenizer.next(Token.Type.PLUS, Token.Type.MINUS));
            rightOperand.add(new Multiplication(tokenizer));
        }
    }


    /**
     *
     * @param state The current program state
     * @return return the result after the addition
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        //Evaluate the left operand
        Object value = leftOperand.evaluate(state);

        // If it's the only operand, return its value
        if (rightOperand.size() == 0) {
            return value;
        }

        // Otherwise, if it's not a number value, throw a type error on it
        else if (!(value instanceof Number)) {
            throw new TypeError(leftOperand.firstToken());
        }

        int count = 0;
        for (Multiplication m : rightOperand) {

            // Evaluate each other operand one by one
            Object temp = m.evaluate(state);

            // If it's not a number value, throw a type error on it;
            if (!(temp instanceof Number)) {
                throw new TypeError(m.firstToken());
            }

            Token t = operators.get(count);

            // Otherwise apply the appropriate operation
            if (t.type == Token.Type.PLUS) {

                if (isFloat(value, temp))
                    value = ((Number) value).doubleValue() + ((Number) temp).doubleValue();
                else
                    value = (Integer) value + (Integer) temp;
            }
            else if (t.type == Token.Type.MINUS) {

                if (isFloat(value, temp))
                    value = ((Number) value).doubleValue() - ((Number) temp).doubleValue();
                else
                    value = (Integer) value - (Integer) temp;
            }
            count++;
        }
        return value;
    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return leftOperand.firstToken();
    }

    /**
     *
     * @param x an object
     * @param y an object
     * @return If one of the object is a float
     */
    private boolean isFloat(Object x, Object y) {
        return (x instanceof Float || y instanceof Float);
    }
}
