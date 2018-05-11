package test;

import lexer.SyntaxError;
import lexer.Token;
import lexer.Tokenizer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Scanner;
import static org.junit.Assert.*;

/**
 * Test suite for the lexical analysis stage.
 */
public class LexerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void badToken() {
        exception.expect(SyntaxError.class);
        exception.expectMessage("Syntax error on line 1, column 1");
        new Tokenizer(new Scanner("?"));
    }
    @Test
    public void semicolonToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(";"));
        Token token = tokenizer.next();
        assertEquals("SEMICOLON[;]@1,1", token.toString());
    }
    @Test
    public void commaToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(","));
        Token token = tokenizer.next();
        assertEquals("COMMA[,]@1,1", token.toString());
    }
    @Test
    public void lbraceToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("{"));
        Token token = tokenizer.next();
        assertEquals("LBRACE[{]@1,1", token.toString());
    }
    @Test
    public void rbraceToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("}"));
        Token token = tokenizer.next();
        assertEquals("RBRACE[}]@1,1", token.toString());
    }
    @Test
    public void lparenToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("("));
        Token token = tokenizer.next();
        assertEquals("LPAREN[(]@1,1", token.toString());
    }
    @Test
    public void rparenToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(")"));
        Token token = tokenizer.next();
        assertEquals("RPAREN[)]@1,1", token.toString());
    }
    @Test
    public void ifToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("if"));
        Token token = tokenizer.next();
        assertEquals("IF[if]@1,1", token.toString());
    }
    @Test
    public void elseToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("else"));
        Token token = tokenizer.next();
        assertEquals("ELSE[else]@1,1", token.toString());
    }
    @Test
    public void whileToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("while"));
        Token token = tokenizer.next();
        assertEquals("WHILE[while]@1,1", token.toString());
    }
    @Test
    public void printToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("print"));
        Token token = tokenizer.next();
        assertEquals("PRINT[print]@1,1", token.toString());
    }
    @Test
    public void intToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("int"));
        Token token = tokenizer.next();
        assertEquals("INT[int]@1,1", token.toString());
    }
    @Test
    public void floatToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("float"));
        Token token = tokenizer.next();
        assertEquals("FLOAT[float]@1,1", token.toString());
    }
    @Test
    public void booleanToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("boolean"));
        Token token = tokenizer.next();
        assertEquals("BOOLEAN[boolean]@1,1", token.toString());
    }
    @Test
    public void trueToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("true"));
        Token token = tokenizer.next();
        assertEquals("TRUE[true]@1,1", token.toString());
    }
    @Test
    public void falseToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("false"));
        Token token = tokenizer.next();
        assertEquals("FALSE[false]@1,1", token.toString());
    }
    @Test
    public void int_literalToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("1345234"));
        Token token = tokenizer.next();
        assertEquals("INT_LITERAL[1345234]@1,1", token.toString());
    }
    @Test
    public void float_literalToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("443.1234"));
        Token token = tokenizer.next();
        assertEquals("FLOAT_LITERAL[443.1234]@1,1", token.toString());
    }
    @Test
    public void identifierToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("w____333____ggg____QQQ_"));
        Token token = tokenizer.next();
        assertEquals("IDENTIFIER[w____333____ggg____QQQ_]@1,1", token.toString());
    }
    @Test
    public void assignToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("="));
        Token token = tokenizer.next();
        assertEquals("ASSIGN[=]@1,1", token.toString());
    }
    @Test
    public void plusToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("+"));
        Token token = tokenizer.next();
        assertEquals("PLUS[+]@1,1", token.toString());
    }
    @Test
    public void minusToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("-"));
        Token token = tokenizer.next();
        assertEquals("MINUS[-]@1,1", token.toString());
    }
    @Test
    public void multToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("*"));
        Token token = tokenizer.next();
        assertEquals("MULT[*]@1,1", token.toString());
    }
    @Test
    public void divToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("/"));
        Token token = tokenizer.next();
        assertEquals("DIV[/]@1,1", token.toString());
    }
    @Test
    public void modToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("%"));
        Token token = tokenizer.next();
        assertEquals("MOD[%]@1,1", token.toString());
    }
    @Test
    public void eqToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("=="));
        Token token = tokenizer.next();
        assertEquals("EQ[==]@1,1", token.toString());
    }
    @Test
    public void neqToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("!="));
        Token token = tokenizer.next();
        assertEquals("NEQ[!=]@1,1", token.toString());
    }
    @Test
    public void ltToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("<"));
        Token token = tokenizer.next();
        assertEquals("LT[<]@1,1", token.toString());
    }
    @Test
    public void gtoken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(">"));
        Token token = tokenizer.next();
        assertEquals("GT[>]@1,1", token.toString());
    }
    @Test
    public void leqToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("<="));
        Token token = tokenizer.next();
        assertEquals("LEQ[<=]@1,1", token.toString());
    }
    @Test
    public void GEQToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(">="));
        Token token = tokenizer.next();
        assertEquals("GEQ[>=]@1,1", token.toString());
    }
    @Test
    public void andToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("&&"));
        Token token = tokenizer.next();
        assertEquals("AND[&&]@1,1", token.toString());
    }
    @Test
    public void orToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("||"));
        Token token = tokenizer.next();
        assertEquals("OR[||]@1,1", token.toString());
    }
    @Test
    public void notToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("!"));
        Token token = tokenizer.next();
        assertEquals("NOT[!]@1,1", token.toString());
    }
    @Test
    public void commentToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("// "));
        assertFalse(tokenizer.hasNext());
    }
    @Test
    public void commentCheckToken() {
        exception.expect(SyntaxError.class);
        exception.expectMessage("Syntax error on line 1, column 1");
        Tokenizer tokenizer = new Tokenizer(new Scanner("?"));
        Token token = tokenizer.next();
    }
    @Test
    public void whitespaceToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(""));
        assertFalse(tokenizer.hasNext());
    }
    @Test
    public void commentWhitespaceToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("//   "));
        assertFalse(tokenizer.hasNext());
    }
    @Test
    public void lineToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("print ;"));
        Token token = tokenizer.next(Token.Type.PRINT);
        assertEquals("PRINT[print]@1,1", token.toString());
        Token token2 = tokenizer.next(Token.Type.SEMICOLON);
        assertEquals("SEMICOLON[;]@1,7", token2.toString());
    }
    @Test
    public void line2Token(){
        Tokenizer tokenizer = new Tokenizer(new Scanner("print;\n&&      while"));
        Token token = tokenizer.next(Token.Type.PRINT);
        assertEquals("PRINT[print]@1,1", token.toString());
        Token token2 = tokenizer.next(Token.Type.SEMICOLON);
        assertEquals("SEMICOLON[;]@1,6", token2.toString());
        Token token3 = tokenizer.next(Token.Type.AND);
        assertEquals("AND[&&]@2,1", token3.toString());
        Token token4 = tokenizer.next(Token.Type.WHILE);
        assertEquals("WHILE[while]@2,9", token4.toString());
    }
    @Test
    public void line3Token(){
        exception.expect(SyntaxError.class);
        exception.expectMessage("Syntax error on line 2, column 1");
        new Tokenizer(new Scanner("if \n?"));
    }
    @Test
    public void hasnextToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("// Comment \nh________"));
        assertTrue(tokenizer.hasNext(Token.Type.IDENTIFIER));
    }
    @Test
    public void togetherToken() {
        Tokenizer tokenizer = new Tokenizer(new Scanner("while (x > 1) {print x;}"));
        Token token = tokenizer.next(Token.Type.WHILE);
        assertEquals("WHILE[while]@1,1", token.toString());
        Token token2 = tokenizer.next(Token.Type.LPAREN);
        assertEquals("LPAREN[(]@1,7", token2.toString());
        Token token3 = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[x]@1,8", token3.toString());
        Token token4 = tokenizer.next(Token.Type.GT);
        assertEquals("GT[>]@1,10", token4.toString());
        Token token5 = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[1]@1,12", token5.toString());
        Token token6 = tokenizer.next(Token.Type.RPAREN);
        assertEquals("RPAREN[)]@1,13", token6.toString());
        Token token7 = tokenizer.next(Token.Type.LBRACE);
        assertEquals("LBRACE[{]@1,15", token7.toString());
        Token token8 = tokenizer.next(Token.Type.PRINT);
        assertEquals("PRINT[print]@1,16", token8.toString());
    }
    @Test
    public void compoundTest() {
        Tokenizer tokenizer = new Tokenizer(new Scanner(
                "if(1 >= 2.5 == false){\n"+
                        "\twhile(true){\n"+
                        "\t\tnum = left + right-1;\n"+
                        "\t}\n"+
                        "} else if(60 <= .12 != true){\n"+
                        "\tnum2 = left2 * right2/2;\n"+
                        "} else {\n"+
                        "\tprint (!3&&4);\n"+
                        "}"
        ));
        // if(1 >= 2.5 == false){
        Token token = tokenizer.next(Token.Type.IF);
        assertEquals("IF[if]@1,1", token.toString());
        token = tokenizer.next(Token.Type.LPAREN);
        assertEquals("LPAREN[(]@1,3", token.toString());
        token = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[1]@1,4", token.toString());
        token = tokenizer.next(Token.Type.GEQ);
        assertEquals("GEQ[>=]@1,6", token.toString());
        token = tokenizer.next(Token.Type.FLOAT_LITERAL);
        assertEquals("FLOAT_LITERAL[2.5]@1,9", token.toString());
        token = tokenizer.next(Token.Type.EQ);
        assertEquals("EQ[==]@1,13", token.toString());
        token = tokenizer.next(Token.Type.FALSE);
        assertEquals("FALSE[false]@1,16", token.toString());
        token = tokenizer.next(Token.Type.RPAREN);
        assertEquals("RPAREN[)]@1,21", token.toString());
        token = tokenizer.next(Token.Type.LBRACE);
        assertEquals("LBRACE[{]@1,22", token.toString());

        // while(true){
        token = tokenizer.next(Token.Type.WHILE);
        assertEquals("WHILE[while]@2,2", token.toString());
        token = tokenizer.next(Token.Type.LPAREN);
        assertEquals("LPAREN[(]@2,7", token.toString());
        token = tokenizer.next(Token.Type.TRUE);
        assertEquals("TRUE[true]@2,8", token.toString());
        token = tokenizer.next(Token.Type.RPAREN);
        assertEquals("RPAREN[)]@2,12", token.toString());
        token = tokenizer.next(Token.Type.LBRACE);
        assertEquals("LBRACE[{]@2,13", token.toString());

        // num = left + right-1;
        token = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[num]@3,3", token.toString());
        token = tokenizer.next(Token.Type.ASSIGN);
        assertEquals("ASSIGN[=]@3,7", token.toString());
        token = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[left]@3,9", token.toString());
        token = tokenizer.next(Token.Type.PLUS);
        assertEquals("PLUS[+]@3,14", token.toString());
        token = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[right]@3,16", token.toString());
        token = tokenizer.next(Token.Type.MINUS);
        assertEquals("MINUS[-]@3,21", token.toString());
        token = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[1]@3,22", token.toString());
        token = tokenizer.next(Token.Type.SEMICOLON);
        assertEquals("SEMICOLON[;]@3,23", token.toString());

        // }
        token = tokenizer.next(Token.Type.RBRACE);
        assertEquals("RBRACE[}]@4,2", token.toString());

        // } else if(60 <= .12 != true){
        token = tokenizer.next(Token.Type.RBRACE);
        assertEquals("RBRACE[}]@5,1", token.toString());
        token = tokenizer.next(Token.Type.ELSE);
        assertEquals("ELSE[else]@5,3", token.toString());
        token = tokenizer.next(Token.Type.IF);
        assertEquals("IF[if]@5,8", token.toString());
        token = tokenizer.next(Token.Type.LPAREN);
        assertEquals("LPAREN[(]@5,10", token.toString());
        token = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[60]@5,11", token.toString());
        token = tokenizer.next(Token.Type.LEQ);
        assertEquals("LEQ[<=]@5,14", token.toString());
        token = tokenizer.next(Token.Type.FLOAT_LITERAL);
        assertEquals("FLOAT_LITERAL[.12]@5,17", token.toString());
        token = tokenizer.next(Token.Type.NEQ);
        assertEquals("NEQ[!=]@5,21", token.toString());
        token = tokenizer.next(Token.Type.TRUE);
        assertEquals("TRUE[true]@5,24", token.toString());
        token = tokenizer.next(Token.Type.RPAREN);
        assertEquals("RPAREN[)]@5,28", token.toString());
        token = tokenizer.next(Token.Type.LBRACE);
        assertEquals("LBRACE[{]@5,29", token.toString());

        // num2 = left2 * right2/2;
        token = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[num2]@6,2", token.toString());
        token = tokenizer.next(Token.Type.ASSIGN);
        assertEquals("ASSIGN[=]@6,7", token.toString());
        token = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[left2]@6,9", token.toString());
        token = tokenizer.next(Token.Type.MULT);
        assertEquals("MULT[*]@6,15", token.toString());
        token = tokenizer.next(Token.Type.IDENTIFIER);
        assertEquals("IDENTIFIER[right2]@6,17", token.toString());
        token = tokenizer.next(Token.Type.DIV);
        assertEquals("DIV[/]@6,23", token.toString());
        token = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[2]@6,24", token.toString());
        token = tokenizer.next(Token.Type.SEMICOLON);
        assertEquals("SEMICOLON[;]@6,25", token.toString());

        // } else {
        token = tokenizer.next(Token.Type.RBRACE);
        assertEquals("RBRACE[}]@7,1", token.toString());
        token = tokenizer.next(Token.Type.ELSE);
        assertEquals("ELSE[else]@7,3", token.toString());
        token = tokenizer.next(Token.Type.LBRACE);
        assertEquals("LBRACE[{]@7,8", token.toString());

        // print (!3&&4);
        token = tokenizer.next(Token.Type.PRINT);
        assertEquals("PRINT[print]@8,2", token.toString());
        token = tokenizer.next(Token.Type.LPAREN);
        assertEquals("LPAREN[(]@8,8", token.toString());
        token = tokenizer.next(Token.Type.NOT);
        assertEquals("NOT[!]@8,9", token.toString());
        token = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[3]@8,10", token.toString());
        token = tokenizer.next(Token.Type.AND);
        assertEquals("AND[&&]@8,11", token.toString());
        token = tokenizer.next(Token.Type.INT_LITERAL);
        assertEquals("INT_LITERAL[4]@8,13", token.toString());
        token = tokenizer.next(Token.Type.RPAREN);
        assertEquals("RPAREN[)]@8,14", token.toString());
        token = tokenizer.next(Token.Type.SEMICOLON);
        assertEquals("SEMICOLON[;]@8,15", token.toString());

        // }
        token = tokenizer.next(Token.Type.RBRACE);
        assertEquals("RBRACE[}]@9,1", token.toString());
}
}