package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a parsed Decaf disjunction.
 * Created by ltorrey on 3/11/2016.
 */
public class Disjunction extends Expression {

    private Conjunction leftOperand;
    private ArrayList<Conjunction> rightOperand = new ArrayList();


    /**
     * Disjunction -> Conjunction (OR Conjunction)*
     * @param tokenizer To provide tokens
     */
    public Disjunction(Tokenizer tokenizer) {

        // Conjunction
        leftOperand = new Conjunction(tokenizer);

        // (OR Conjunction)*
        while (tokenizer.hasNext(Token.Type.OR)) {
            tokenizer.next();
            rightOperand.add(new Conjunction(tokenizer));
        }
    }

    /**
     *
     * @param state The current program state
     * @return Boolean value evaluating the Disjunction
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        // Evaluate the left operand
        Object value = leftOperand.evaluate(state);

        // If it's the only operand, return its value.
        if (rightOperand.size() == 0)
            return value;

        // Otherwise, if it's not a boolean value, throw a type error on it
        else if (!(value instanceof Boolean))
            throw new TypeError(leftOperand.firstToken());

        // If the first value is true, short circuit and return true
        else if ((Boolean) value)
            return true;


        else {

            // Evaluate each other operand one by one
            for (Conjunction c : rightOperand) {

                // If it's not a boolean value, throw a type error on it.
                value = c.evaluate(state);

                // If it's not a boolean value, throw a type error on it.
                if (!(value instanceof Boolean)) {
                    throw new TypeError(c.firstToken());
                }

                // If it evaluates to true, perform logical short-circuiting: return true without evaluating the rest.
                else if ((Boolean) value) {
                    return true;
                }
            }
        }

        // If all of the operands evaluated to false, return false.
        return false;
    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return leftOperand.firstToken();
    }
}
