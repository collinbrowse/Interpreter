package parser;

import interpreter.DivisionError;
import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Multiplication extends Expression {

    private Negation leftOperand;
    private ArrayList<Negation> rightOperand = new ArrayList();
    private ArrayList<Token> operators = new ArrayList();

    /**
     * Multiplication -> Negation (MULT|DIV|MOD Negation)*
     * @param tokenizer To provide tokens
     */

    public Multiplication(Tokenizer tokenizer) {

        // Negation
        leftOperand = new Negation(tokenizer);

        // (MULT|DIV|MOD Negation)*
        while (tokenizer.hasNext(Token.Type.MULT, Token.Type.DIV, Token.Type.MOD)) {
            operators.add(tokenizer.next());
            rightOperand.add(new Negation(tokenizer));
        }
    }

    /**
     *
     * @param state The current program state
     * @return The result after multiplication has been performed on left/right operand
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        // Evaluate the left operand.
        Object value = leftOperand.evaluate(state);

        // If it's the only operand, return its value.
        if (rightOperand.size() == 0)
            return value;

        // Otherwise, if it's not a number value, throw a type error on it.
        else if (!(value instanceof Number))
            throw new TypeError(leftOperand.firstToken());

        // Evaluate each other operand one by one.
        int count = 0;
        for (Negation n : rightOperand){

            Object temp = n.evaluate(state);

            // If it's not a number value, throw a type error on it
            if (!(temp instanceof Number))
                throw new TypeError(n.firstToken());

            Token t = operators.get(count);

            // otherwise apply the appropriate operation.
            if (t.type == Token.Type.MULT) {

                if (isFloat(value, temp))
                    value = ((Number) value).doubleValue() * ((Number) temp).doubleValue();
                else
                    value = (Integer) value * (Integer) temp;
            }

            else if (t.type == Token.Type.DIV) {

                if (temp.equals(0))
                    throw new DivisionError(n.firstToken());
                else if (isFloat(value, temp))
                    value = ((Number) value).doubleValue() / ((Number) temp).doubleValue();
                else
                    value = (Integer) value / (Integer) temp;
            }

            else if (t.type == Token.Type.MOD) {

                if (temp.equals(0))
                    throw new DivisionError(n.firstToken());
                else if (isFloat(value, temp))
                    value = ((Number) value).doubleValue() % ((Number) temp).doubleValue();
                else
                    value = (Integer) value % (Integer) temp;
            }
            count ++;
        }

        // Return the final result after all the operations have been applied from left to right
        return value;
    }

    /**
     *
     * @param x an object
     * @param y an object
     * @return Whether one object is a float
     */
    private boolean isFloat(Object x, Object y) {
        return (x instanceof Float || y instanceof Float);
    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return leftOperand.firstToken();
    }
}
