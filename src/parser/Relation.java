package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Relation extends Expression {

    private Addition leftOperand, rightOperand;
    private Token operator;


    /**
     * Relation -> Addition (LT|LEQ|GT|GEQ Addition)?
     * @param tokenizer To provide tokens
     */

    public Relation(Tokenizer tokenizer) {

        // Addition
        leftOperand = new Addition(tokenizer);

        // (LT|LEQ|GT|GEQ Addition)?
        if (tokenizer.hasNext(Token.Type.LT, Token.Type.LEQ, Token.Type.GT, Token.Type.GEQ)) {
            operator = tokenizer.next();
            rightOperand = new Addition(tokenizer);
        }
    }

    /**
     *
     * @param state The current program state
     * @return The boolean value resulting from evaluating the relation
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        // Evaluate the left operand
        Object leftValue = leftOperand.evaluate(state);

        // If it's the only operand, return its value.
        if (rightOperand == null)
            return leftValue;

        // Otherwise, if it's not a number value, throw a type error on it.
        else if (!(leftValue instanceof Number))
            throw new TypeError(leftOperand.firstToken());

        // Evaluate the right operand
        Object rightValue = rightOperand.evaluate(state);

        //  If it's not a number value, throw a type error on it.
        if (!(rightValue instanceof Number))
            throw new TypeError(rightOperand.firstToken());

        // Apply the appropriate operation and return the boolean result.
        if (operator.type == Token.Type.LT)
            return ((Number) leftValue).doubleValue() < ((Number) rightValue).doubleValue();

        else if (operator.type == Token.Type.LEQ)
            return ((Number) leftValue).doubleValue() <= ((Number) rightValue).doubleValue();

        else if (operator.type == Token.Type.GT)
            return ((Number) leftValue).doubleValue() > ((Number) rightValue).doubleValue();

        else if (operator.type == Token.Type.GEQ)
            return ((Number) leftValue).doubleValue() >= ((Number) rightValue).doubleValue();

        throw new TypeError(rightOperand.firstToken());

    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return leftOperand.firstToken();
    }
}
