package br.com.financialmobile.http;

import java.util.Vector;

class ResultTokenizer {
    
    private final String expression;
    private final int length;
    
    private int pos;
    
    ResultTokenizer(final String expr) {
        if (expr == null) {
            throw new IllegalArgumentException("path cannot be null");
        }
        
        expression = expr;
        length = expression.length();
        pos = 0;
    }
    
    Vector tokenize() {
        final Vector tokens = new Vector();
        String tok;
        for (pos = 0, tok = next(); !"".equals(tok); tok = next()) {
            tokens.addElement(tok);
        }
        return tokens;
    }
    
    private String next() {
        final StringBuffer sbuf = new StringBuffer();
        
        if (pos >= length) {
            return sbuf.toString();
        }
        
        final char del = expression.charAt(pos);
        if (isDelimiter(del)) {
            pos++;
            sbuf.append(del);
            return sbuf.toString();
        }
        
        for (int i = pos; i < length; i++) {
            final char ch = expression.charAt(i);
            if (isDelimiter(ch)) {
                pos = i;
                return sbuf.toString();
            } else {
                sbuf.append(ch);
            }
        }

        pos = length;
        return sbuf.toString();
    }
    
    static boolean isDelimiter(final char ch) {
        switch (ch) {
            case Result.SEPARATOR:
            case Result.ARRAY_START:
            case Result.ARRAY_END:
                return true;
        }
        return false;
    }
}
