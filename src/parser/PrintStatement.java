package parser;

import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Represents a parsed Decaf print statement.
 * Created by ltorrey on 3/9/2016.
 */
public class PrintStatement extends Statement {

    private Expression expression;

    /**
     * PrintStatement -> PRINT Expression SEMICOLON
     * @param tokenizer To provide tokens
     */
    public PrintStatement(Tokenizer tokenizer) {

        // PRINT
        tokenizer.next();

        // Expression
        expression = Expression.getExpression(tokenizer);

        // SEMICOLON
        tokenizer.next(Token.Type.SEMICOLON);
    }

    /**
     * Evaluate the Decaf expression represented by this object.
     * @param state The current program state
     */
    @Override
    public void execute(HashMap<String, Object> state) {

        // Evaluate the expression.
        Object value = expression.evaluate(state);

        // Print it to the console.
        System.out.println(value);
    }
}
