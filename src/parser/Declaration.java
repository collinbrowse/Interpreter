package parser;

import interpreter.NameError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Declaration {

    private Token leftOperand, dataType;
    private ArrayList<Token> rightOperand = new ArrayList();


    /**
     * Program -> (INT|FLOAT|BOOLEAN) IDENTIFIER (COMMA IDENTIFIER)* SEMICOLON
     * @param tokenizer To provide tokens
     */
    public Declaration(Tokenizer tokenizer) {

        // (INT|FLOAT|BOOLEAN)
        dataType = tokenizer.next(Token.Type.INT, Token.Type.FLOAT, Token.Type.BOOLEAN);

        // IDENTIFIER
        leftOperand = tokenizer.next(Token.Type.IDENTIFIER);

        // (COMMA IDENTIFIER)*
        while (tokenizer.hasNext(Token.Type.COMMA)) {
            tokenizer.next();
            rightOperand.add(tokenizer.next(Token.Type.IDENTIFIER));
        }

        // SEMICOLON
        tokenizer.next(Token.Type.SEMICOLON);
    }


    /**
     * Execute the Decaf declaration represented by this object.
     * @param state The current program state
     */
    public void execute(HashMap<String,Object> state) {
        declare(leftOperand, state);
        for (Token id: rightOperand)
            declare(id, state);
    }

    /**
     * Add an identifier to the program state.
     * @param id The identifier token
     * @param state The current program state
     */
    private void declare(Token id, HashMap<String,Object> state) {

        // Error on duplicate name declarations
        String name = id.text;
        if (state.containsKey(name))
            throw new NameError(id);

        // Default values depend on data type
        if (dataType.type == Token.Type.INT)
            state.put(name, 0);
        else if (dataType.type == Token.Type.FLOAT)
            state.put(name, 0.0);
        else
            state.put(name, false);
    }
}
