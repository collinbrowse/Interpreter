package parser;

import interpreter.NameError;
import interpreter.TypeError;
import lexer.Token;
import lexer.Tokenizer;

import java.util.HashMap;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public class Assignment extends Statement {

    private Token identifier;
    private Expression expression;

    /**
     * Assignment -> IDENTIFIER ASSIGN Expression SEMICOLON
     * @param tokenizer To provide tokens
     */
    public Assignment(Tokenizer tokenizer) {

        //IDENTIFIER
        identifier = tokenizer.next();

        // ASSIGN
        tokenizer.next(Token.Type.ASSIGN);

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
    public void execute(HashMap<String,Object> state) {

        String t = identifier.text;
        // Evaluate the expression to get the new value for this binding.
        Object newValue = expression.evaluate(state);

        // If no binding exists for the identifier name, throw a name error on the identifier.
        if (!state.containsKey(t))
            throw new NameError(identifier);

        // If the new value has the same type as the value currently bound to this identifier, update the binding
        else if ((state.get(t) instanceof Boolean) && (newValue instanceof Boolean))
            state.put(t, newValue);

        else if ((state.get(t) instanceof Integer) && (newValue instanceof Integer)) {
            Integer temp = ((Integer) newValue).intValue();
            state.put(t, temp);
        }
        else if ((state.get(t) instanceof Float) && (newValue instanceof Float)) {
            Float temp = ((Integer) newValue).floatValue();
            state.put(t, temp);
        }
        // If the new value is an int and the old value is a float, convert to the new value to a float and update the binding
        else if ((state.get(t) instanceof Float) && (newValue instanceof Integer)) {
            Float temp = ((Integer) newValue).floatValue();
            state.put(t, temp);
        }

        // If there is any other type of value mismatch, throw a type error
        else
            throw new TypeError(expression.firstToken());
    }
}
