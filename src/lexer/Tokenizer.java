package lexer;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Like a Scanner but especially for Decaf tokens.
 * Started by ltorrey on 2/29/2016 and completed by Collin Browse.
 */
public class Tokenizer {

    private ArrayList<Token> tokenList;
    private int row;
    private int col;

    /**
     * Read all lines from the scanner and make a list of tokens.
     * Don't include the comment and whitespace types in the list.
     * Syntax error if there's a token that matches none of the types.
     * @param scanner Points at a code file or string
     */
    public Tokenizer(Scanner scanner) {

        tokenList = new ArrayList<Token>();
        row = 0;

        while (scanner.hasNextLine()) {

            row++;
            col = 1;        // Restart col for a new line

            String code = scanner.nextLine();

            // For each line of code
            while (!code.equals("")) {

                int count = 0;          // Counter for Syntax Error Checking

                // Check each token type
                for (Token.Type type : Token.Type.values()) {

                    // Find a token match
                    String text = type.getMatch(code);

                    // If text isn't null then there's a match
                    if (text != null) {
                        // Only add tokens that aren't whitespace or comments
                        code = code.substring(text.length());           // Chop off text from code
                        col += text.length();                           // Increment col
                        if (type != Token.Type.WHITESPACE && type != Token.Type.COMMENT) {
                            tokenList.add(new Token(type, text, row, col-text.length())); // Create the token and add to tokenList
                        }
                        break;
                    }
                    count++;
                }
                // If we have checked every possible token without a match, throw a Syntax Error
                if (Token.Type.values().length == count) {
                    throw new SyntaxError(row, col);
                }
            }

        }
    }


    /**
     * @return Whether the list contains another token.
     */
    public boolean hasNext() {

        // If there are no tokens in tokenList return false, else true
        return !tokenList.isEmpty();
    }

    /**
     * @param types Zero or more token types (variable-arity)
     * @return Whether the first token in the list is one of these types.
     */
    public boolean hasNext(Token.Type ... types) {

        if (tokenList.isEmpty())
            return false;
        else {
            for (int i = 0; i < types.length; i ++) {
                if (tokenList.get(0).type.equals(types[i]))
                    return true;
            }
        }
        return false;
    }

    /**
     * Consumes and returns a token.
     * Syntax error if there isn't one.
     * @return The first token in the list.
     */
    public Token next() {

        if (tokenList.isEmpty())
            throw new SyntaxError(row, col);
        else
            return tokenList.remove(0);
    }

    /**
     * Consumes and returns a token.
     * Syntax error if there isn't one of these types.
     * @param types Zero or more token types (variable-arity)
     * @return The first token in the list.
     */
    public Token next(Token.Type ... types) {

        if (tokenList.isEmpty())
            throw new SyntaxError(row, col);
        else {
            for (int i = 0; i < types.length; i ++) {
                if (tokenList.get(0).type.equals(types[i]))
                    return tokenList.remove(0);
            }
        }
        throw new SyntaxError(tokenList.get(0));
    }
}
