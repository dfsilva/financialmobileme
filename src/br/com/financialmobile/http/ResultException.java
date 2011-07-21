
package br.com.financialmobile.http;

import java.io.IOException;

public class ResultException extends IOException {
    
    protected Throwable cause = null;
    
    public ResultException() {
        super();
    }
    
    public ResultException(final String message) {
        super(message);
    }
    
    public ResultException(final Throwable th) {
        super(th.getMessage());
        cause = th;
    }

    public Throwable getCause() {
        return cause;
    }
}
