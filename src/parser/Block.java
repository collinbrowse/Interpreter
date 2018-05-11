package parser;

import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Block extends Statement {

    private ArrayList<Statement> statement = new ArrayList();

    /**
     * Block -> LBRACE Statement* RBRACE
     * @param tokenizer
     */
    public Block(Tokenizer tokenizer) {

        // LBRACE
        tokenizer.next(Token.Type.LBRACE);

        // Statement*
        while (!tokenizer.hasNext(Token.Type.RBRACE)) {
            statement.add(Statement.getStatement(tokenizer));
        }

        // RBRACE
        tokenizer.next(Token.Type.RBRACE);
    }


    /**
     * Evaluate the Decaf expression represented by this object.
     * @param state The current program state
     */
    @Override
    public void execute(HashMap<String, Object> state) {

        // Execute the statements in the order they appear.
        for (Statement s : statement) {
            s.execute(state);
        }
    }
}
