package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

import static parser.Statement.getStatement;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class WhileStatement extends Statement {

    private Expression expression;
    private Statement statement;

    /**
     * WhileStatement -> WHILE LPAREN Expression RPAREN Statement
     * @param tokenizer To provide token
     */

    public WhileStatement(Tokenizer tokenizer) {

        // Statement has already checked to make sure there is a while at the front of the tokenizer
        tokenizer.next();

        //LPAREN
        tokenizer.next(Token.Type.LPAREN);

        // Expression
        expression = Expression.getExpression(tokenizer);

        // RPAREN
        tokenizer.next(Token.Type.RPAREN);

        // Statement
        statement = getStatement(tokenizer);
    }

    /**
     * Evaluate the Decaf expression represented by this object.
     * @param state The current program state
     */
    @Override
    public void execute(HashMap<String, Object> state) {

        // Evaluate the expression
        Object value = expression.evaluate(state);

        // if it's not a boolean value, throw a type error on it.
        if (!(value instanceof Boolean)) {
            throw new TypeError(expression.firstToken());
        }
        else {
            // While the expression evaluates to true, execute the statement and re-evaluate the expression.
            while ((Boolean) value) {
                statement.execute(state);
                value = expression.evaluate(state);
            }
        }
    }
}
