package parser;

import interpreter.NameError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Identifier extends Operand {


    private Token identifier;

    /**
     * Identifier -> IDENTIFIER
     * @param tokenizer To provide tokens
     */
    public Identifier(Tokenizer tokenizer) {

        // IDENTIFIER
        identifier = tokenizer.next(Token.Type.IDENTIFIER);
    }

    /**
     *
     * @param state The current program state
     * @return return the value that is currently bound to the identifier
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {

        String s = identifier.text;

        // If no binding exists for the identifier name, throw a name error on the identifier.
        if (!state.containsKey(s))
            throw new NameError(identifier);

        // Otherwise, return the value that is currently bound to the identifier name.
        else
            return state.get(s);
    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return identifier;
    }
}
