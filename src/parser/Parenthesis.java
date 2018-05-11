package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Parenthesis extends Operand {

    private Expression expression;

    /**
     * Parenthesis -> LAPREN Expression RPAREN
     * @param tokenizer To provide tokens
     */

    public Parenthesis(Tokenizer tokenizer) {

        // LPAREN
        tokenizer.next(Token.Type.LPAREN);

        // Expression
        expression = Expression.getExpression(tokenizer);

        // RPAREN
        tokenizer.next(Token.Type.RPAREN);
    }

    /**
     *
     * @param state The current program state
     * @return The value corresponding the evaluated expression
     */
    @Override
    public Object evaluate(HashMap<String, Object> state) {
        // Evaluate the expression and return its value
        return expression.evaluate(state);
    }

    /**
     * @return The token that begins this expression.
     */
    @Override
    public Token firstToken() {
        return expression.firstToken();
    }
}
