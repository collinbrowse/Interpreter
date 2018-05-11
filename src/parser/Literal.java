package parser;

import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Literal extends Operand {

    private Token literal;

    /**
     * Literal -> INT_LITERAL | FLOAT_LITERAL | TRUE | FALSE
     * @param tokenizer To provide tokens
     */

    public Literal(Tokenizer tokenizer) {

        // INT_LITERAL | FLOAT_LITERAL | TRUE | FALSE
        literal = tokenizer.next();
    }

    /**
     *
     * @param state The current program state
     * @return Return the appropriate Object from the literal's data type
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        // Create and return an object to represent the value of the literal text.
        String value = literal.text;

        if (literal.type == Token.Type.INT_LITERAL)
            return Integer.valueOf(value);
        else if (literal.type == Token.Type.FLOAT_LITERAL)
            return Float.valueOf(value);
        else
            return Boolean.valueOf(value);
    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return literal;
    }
}
