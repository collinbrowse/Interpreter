package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Conjunction extends Expression {

    private Equality leftOperand;
    private ArrayList<Equality> rightOperand = new ArrayList();

    /**
     * Conjunction -> Equality (AND Equality)*
     * @param tokenizer To provide tokens
     */
    public Conjunction(Tokenizer tokenizer) {

        // Equality
        leftOperand = new Equality(tokenizer);

        // (AND Equality)*
        while (tokenizer.hasNext(Token.Type.AND)) {
            tokenizer.next(Token.Type.AND);
            rightOperand.add(new Equality(tokenizer));
        }
    }

    /**
     *
     * @param state The current program state
     * @return Boolean value evaluating the conjunction
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        // Evaluate the left operand.
        Object value = leftOperand.evaluate(state);

        // If it's the only operand, return its value
        if (rightOperand.size() == 0)
            return value;

        // Otherwise, if it's not a boolean value, throw a type error on it.
        else if (!(value instanceof Boolean))
            throw new TypeError(leftOperand.firstToken());

        else if (!(Boolean) value)
            return false;


        else {

            for (Equality e : rightOperand) {

                // Evaluate each other operand one by one
                value = e.evaluate(state);

                // If it's not a boolean value, throw a type error on it.
                if (!(value instanceof Boolean)) {
                    throw new TypeError(e.firstToken());
                }

                // If it evaluates to false, perform logical short-circuiting: return false without evaluating the rest.
                else if (!(Boolean) value) {
                    return false;
                }
            }
        }

        // If all of the operands evaluated to true, return true.
        return true;

    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return leftOperand.firstToken();
    }
}
