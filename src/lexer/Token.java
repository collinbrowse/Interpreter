package lexer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a single token in a Decaf program.
 * Started by ltorrey on 2/29/2016 and completed by Collin Browse.
 */
public class Token {

    public final Type type;
    public final String text;
    public final int line, column;

    /**
     *
     * @param type One of the enum values below.
     * @param text Characters that make up the token.
     * @param line Line number within the Decaf program.
     * @param column Column number within the Decaf program.
     */
    public Token(Type type, String text, int line, int column) {
        this.type = type;
        this.text = text;
        this.line = line;
        this.column = column;
    }

    /**
     * @return A description of this token.
     */
    public String toString() {
        return type+"["+text+"]@"+line+","+column;
    }

    /**
     * Enumeration of the Decaf token types.
     */
    public enum Type {
        COMMENT("//.*"),
        WHITESPACE("\\s+"),

        SEMICOLON("\\;"),
        COMMA("\\,"),
        LBRACE("\\{"),
        RBRACE("\\}"),
        LPAREN("\\("),
        RPAREN("\\)"),

        IF("if\\b"),
        ELSE("else\\b"),
        WHILE("while\\b"),
        PRINT("print\\b"),

        INT("int\\b"),
        FLOAT("float\\b"),
        BOOLEAN("boolean\\b"),

        TRUE("true\\b"),
        FALSE("false\\b"),
        FLOAT_LITERAL("\\d*\\.\\d+"),
        INT_LITERAL("\\d+"),

        IDENTIFIER("([A-Z]|[a-z])+(([A-Z]|[a-z])*|\\d*|_*)*\\b"),

        EQ("\\=\\="),
        ASSIGN("\\="),

        PLUS("\\+"),
        MINUS("\\-"),
        MULT("\\*"),
        DIV("\\/"),
        MOD("\\%"),

        NEQ("\\!\\="),
        LEQ("\\<\\="),
        LT("\\<"),
        GEQ("\\>\\="),
        GT("\\>"),

        AND("\\&\\&"),
        OR("\\|\\|"),
        NOT("\\!")
        ;

        private String regExp;

        /**
         * @param regExp Regular expression describing this type (from the list above).
         */
        Type(String regExp) {
            this.regExp = regExp;
        }

        /**
         *
         * @param code A Decaf program (or part of one) in string form.
         * @return A prefix that matches this token type (or null).
         */
        public String getMatch(String code) {
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(code);
            return matcher.lookingAt() ? matcher.group() : null;
        }
    }
}
