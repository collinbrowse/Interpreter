package parser;

import lexer.Token;
import lexer.Tokenizer;

/**
 * Created by CollinBrowse on 3/17/16.
 */
public abstract class Operand extends Expression {

    /**
     * Operand -> Literal | Identifier | Parenthesis
     * @param tokenizer To provide tokens
     * @return An object of the appropriate subclass
     */
    public static Operand getOperand(Tokenizer tokenizer) {

        // Literal
        if (tokenizer.hasNext(Token.Type.INT_LITERAL, Token.Type.FLOAT_LITERAL, Token.Type.TRUE, Token.Type.FALSE))
            return new Literal(tokenizer);
        // Identifier
        else if (tokenizer.hasNext(Token.Type.IDENTIFIER))
            return new Identifier(tokenizer);
        // Parenthesis
        else
            return new Parenthesis(tokenizer);
        }

    }

