package parser;

import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Represents a parsed Decaf statement.
 * Created by ltorrey on 3/9/2016.
 */
public abstract class Statement {

    /**
     * Statement -> WhileStatement | IfStatement | Assignment | PrintStatement | Block
     * @param tokenizer To provide tokens
     * @return An object of the appropriate subclass
     */
    public static Statement getStatement(Tokenizer tokenizer) {

        // WhileStatement
        if (tokenizer.hasNext(Token.Type.WHILE))
            return new WhileStatement(tokenizer);
        // IfStatement
        else if (tokenizer.hasNext(Token.Type.IF))
            return new IfStatement(tokenizer);
        // Assignment
        else if (tokenizer.hasNext(Token.Type.IDENTIFIER))
            return new Assignment(tokenizer);
        // PrintStatement
        else if (tokenizer.hasNext(Token.Type.PRINT))
            return new PrintStatement(tokenizer);
        // Block
        else
            return new Block(tokenizer);
    }

    /**
     * Execute the Decaf statement represented by this object.
     * @param state The current program state
     */
    public abstract void execute(HashMap<String,Object> state);



}
