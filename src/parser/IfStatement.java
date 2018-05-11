package parser;

import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

import static parser.Statement.getStatement;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class IfStatement extends Statement {

    private Expression expression;
    private Statement if_statement, else_statement;

    /**
     * IfStatement -> IF LPAREN Expression RPAREN Statement (ELSE Statement)?
     * @param tokenizer To provide token
     */

    public IfStatement(Tokenizer tokenizer) {

        // Statement has already checked to make sure there is an if at the front of the tokenizer
        tokenizer.next();

        //LPAREN
        tokenizer.next(Token.Type.LPAREN);

        // Expression
        expression = Expression.getExpression(tokenizer);

        // RPAREN
        tokenizer.next(Token.Type.RPAREN);

        // Statement
        if_statement = getStatement(tokenizer);

        // (ELSE Statement)?
        if (tokenizer.hasNext(Token.Type.ELSE)) {
            tokenizer.next();
            else_statement = getStatement(tokenizer);
        }


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

        // If the expression evaluates to true, execute the first statement.
        else if ((Boolean) value) {
            if_statement.execute(state);
        }

        // Otherwise, if there is a second statement, execute that.
        else if (else_statement != null) {
            else_statement.execute(state);
        }
    }
}
