package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */

public class Equality extends Expression {

    private Relation leftOperand, rightOperand;
    private Token operator;

    /**
     * Equality -> Relation (EQ|NEQ Relation)?
     * @param tokenizer To provide tokens
     */
    public Equality(Tokenizer tokenizer) {

        // Relation
        leftOperand = new Relation(tokenizer);

        // (EQ|NEQ Relation)?
        if (tokenizer.hasNext(Token.Type.EQ, Token.Type.NEQ)) {
            operator = tokenizer.next();
            rightOperand = new Relation(tokenizer);
        }
    }


    /**
     *
     * @param state The current program state
     * @return Boolean value evaluating the Equality
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        // Evaluate the left operand
        Object leftValue = leftOperand.evaluate(state);

        // If it's the only operand, return its value.
        if (rightOperand == null)
            return leftValue;

        // Evaluate the right operand.
        Object rightValue = rightOperand.evaluate(state);


        if ((leftValue instanceof Boolean) && (rightValue instanceof Boolean)
                || (leftValue instanceof Number) && (rightValue instanceof Number)) {

            // Apply the appropriate operation and return the boolean result
            if (operator.type == Token.Type.EQ)
                return leftValue.equals(rightValue);

            else if (operator.type == Token.Type.NEQ)
                return !leftValue.equals(rightValue);
        }

        //If one value is a boolean and the other is a number, or vice versa, throw a type error on the second one
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
