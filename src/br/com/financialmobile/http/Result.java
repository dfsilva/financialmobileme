package br.com.financialmobile.http;

import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

public class Result {

    public static final String JS_CONTENT_TYPE = "text/javascript";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String PLAIN_TEXT_CONTENT_TYPE = "text/plain";
    public static final String TEXT_XML_CONTENT_TYPE = "text/xml";
    public static final String APPLICATION_XML_CONTENT_TYPE = "application/xml";
    public static final char SEPARATOR = '.';
    public static final char ARRAY_START = '[';
    public static final char ARRAY_END = ']';
    private final JSONObject json;
    private final JSONArray array;
    private final boolean isArray;

    // TODO: add a cache mapping subpaths to objects to improve performance
    public static Result fromContent(final String content,
            final String contentType) throws ResultException {

        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }

        if (JS_CONTENT_TYPE.equals(contentType)
                || JSON_CONTENT_TYPE.equals(contentType)
                || // some sites return JSON with the plain text content type
                PLAIN_TEXT_CONTENT_TYPE.equals(contentType)) {
            try {
                return content.startsWith("[")
                        ? new Result(new JSONArray(content))
                        : new Result(new JSONObject(content));
            } catch (Exception ex) {
                throw new ResultException(ex);
            }
        }

        throw new ResultException("Unsupported content-type: " + contentType);
    }

    private Result(final JSONObject obj) {
        if (obj == null) {
            throw new IllegalArgumentException("json object cannot be null");
        }
        isArray = false;
        this.json = obj;
        this.array = null;
    }

    private Result(final JSONArray obj) {
        if (obj == null) {
            throw new IllegalArgumentException("json object cannot be null");
        }
        isArray = true;
        this.json = null;
        this.array = obj;
    }

    public int hashCode() {
        return isArray ? array.hashCode() : json.hashCode();
    }

    public boolean equals(final Object other) {
        return isArray ? array.equals(other) : json.equals(other);
    }

    public String toString() {
        try {
            return isArray ? ((JSONArray) array).toString(2) : ((JSONObject) json).toString(2);
        } catch (Exception jx) {
            return json.toString();
        }
    }

    public boolean getAsBoolean(final String path) throws ResultException {
        final Vector tokens = new ResultTokenizer(path).tokenize();
        final JSONObject obj = isArray ? apply(array, tokens, 0) : apply(json, tokens, 0);
        return obj == null ? false : obj.optBoolean((String) tokens.lastElement());
    }

    public int getAsInteger(final String path) throws ResultException {
        final Vector tokens = new ResultTokenizer(path).tokenize();
        final JSONObject obj = isArray ? apply(array, tokens, 0) : apply(json, tokens, 0);
        return obj == null ? 0 : obj.optInt((String) tokens.lastElement());
    }

    public long getAsLong(final String path) throws ResultException {
        final Vector tokens = new ResultTokenizer(path).tokenize();
        final JSONObject obj = isArray ? apply(array, tokens, 0) : apply(json, tokens, 0);
        return obj == null ? 0 : obj.optLong((String) tokens.lastElement());
    }

    public String getAsString(final String path) throws ResultException {
        final Vector tokens = new ResultTokenizer(path).tokenize();
        final JSONObject obj = isArray ? apply(array, tokens, 0) : apply(json, tokens, 0);
        return obj == null ? null : obj.optString((String) tokens.lastElement());
    }

    public int getSizeOfArray(final String path) throws ResultException {
        final JSONArray array = getAsArray(path);
        return array == null ? 0 : array.length();
    }

    public String[] getAsStringArray(final String path) throws ResultException {
        final JSONArray jarr = getAsArray(path);
        final String[] arr = new String[jarr == null ? 0 : jarr.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (String) jarr.opt(i);
        }
        return arr;
    }

    public int[] getAsIntegerArray(final String path) throws ResultException {
        final JSONArray jarr = getAsArray(path);
        final int[] arr = new int[jarr == null ? 0 : jarr.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((Integer) jarr.opt(i)).intValue();
        }
        return arr;
    }

    private JSONArray getAsArray(final String path) throws ResultException {
        final Vector tokens = new ResultTokenizer(path).tokenize();

        if (isArray && tokens.isEmpty()) {
            return array;
        }
        final JSONObject obj = isArray ? apply(array, tokens, 0) : apply(json, tokens, 0);
        return obj == null ? null : obj.optJSONArray((String) tokens.lastElement());
    }

    private JSONObject apply(final JSONArray start, final Vector tokens, final int firstToken)
            throws ResultException {

        if (start == null) {
            return null;
        }

        final int nTokens = tokens.size();
        for (int i = firstToken; i < nTokens; i++) {
            final String tok1 = (String) tokens.elementAt(i);
            final char t1 = tok1.charAt(0);
            switch (t1) {
                case SEPARATOR:
                    throw new ResultException("Syntax error: must start with an array: " + tok1);

                case ARRAY_START:
                    if (i + 1 >= nTokens) {
                        throw new ResultException("Syntax error: array must be followed by a dimension: " + tok1);
                    }
                    final String tok2 = (String) tokens.elementAt(i + 1);
                    int dim = 0;
                    try {
                        dim = Integer.parseInt(tok2);
                    } catch (NumberFormatException nx) {
                        throw new ResultException("Syntax error: illegal array dimension: " + tok2);
                    }
                    if (i + 2 >= nTokens) {
                        throw new ResultException("Syntax error: array dimension must be closed: " + tok2);
                    }
                    final String tok3 = (String) tokens.elementAt(i + 2);
                    if (tok3.length() != 1 && tok3.charAt(0) != ARRAY_END) {
                        throw new ResultException("Syntax error: illegal close of array dimension: " + tok3);
                    }
                    if (i + 3 >= nTokens) {
                        throw new ResultException("Syntax error: array close must be followed by a separator or array open: " + tok3);
                    }
                    final String tok4 = (String) tokens.elementAt(i + 3);
                    if (tok4.length() != 1 && tok4.charAt(0) != SEPARATOR && tok4.charAt(0) != ARRAY_START) {
                        throw new ResultException("Syntax error: illegal separator after array: " + tok4);
                    }
                    i += 4;
                    if (tok4.charAt(0) == SEPARATOR) {
                        return apply(start.optJSONObject(dim), tokens, i);
                    } else if (tok4.charAt(0) == ARRAY_START) {
                        return apply(start.optJSONArray(dim), tokens, i);
                    }
                    throw new ResultException("Syntax error: illegal token after array: " + tok4);

                default:
                    throw new ResultException("Syntax error: unknown delimiter: " + tok1);
            }
        }

        return null;
    }

    private JSONObject apply(final JSONObject start, final Vector tokens, final int firstToken)
            throws ResultException {

        if (start == null) {
            return null;
        }

        final int nTokens = tokens.size();
        if (firstToken >= nTokens) {
            return start;
        }

        for (int i = firstToken; i < nTokens; i++) {
            final String tok1 = (String) tokens.elementAt(i);
            if (tok1.length() == 1 && ResultTokenizer.isDelimiter(tok1.charAt(0))) {
                throw new ResultException("Syntax error: path cannot start with a delimiter: " + tok1);
            }

            if (i + 1 >= nTokens) {
                return start;
            }
            final String tok2 = (String) tokens.elementAt(i + 1);
            final char t2 = tok2.charAt(0);
            switch (t2) {
                case SEPARATOR:
                    return apply(start.optJSONObject(tok1), tokens, i + 2);

                case ARRAY_START:
                    if (i + 2 >= nTokens) {
                        throw new ResultException("Syntax error: array must be followed by a dimension: " + tok1);
                    }
                    final String tok3 = (String) tokens.elementAt(i + 2);
                    int dim = 0;
                    try {
                        dim = Integer.parseInt(tok3);
                    } catch (NumberFormatException nx) {
                        throw new ResultException("Syntax error: illegal array dimension: " + tok3);
                    }
                    if (i + 3 >= nTokens) {
                        throw new ResultException("Syntax error: array dimension must be closed: " + tok3);
                    }
                    final String tok4 = (String) tokens.elementAt(i + 3);
                    if (tok4.length() != 1 && tok4.charAt(0) != ARRAY_END) {
                        throw new ResultException("Syntax error: illegal close of array dimension: " + tok4);
                    }
                    if (i + 4 >= nTokens) {
                        throw new ResultException("Syntax error: array close must be followed by a separator: " + tok1);
                    }
                    final String tok5 = (String) tokens.elementAt(i + 4);
                    if (tok5.length() != 1 && tok5.charAt(0) != SEPARATOR) {
                        throw new ResultException("Syntax error: illegal separator after array: " + tok4);
                    }
                    i += 4;
                    final JSONArray array = start.optJSONArray(tok1);
                    return array == null ? null : apply((JSONObject) array.opt(dim), tokens, i + 1);
            }
        }

        return start;
    }

    public JSONObject getJSONObject(String token, int index)
            throws ResultException {
        try {
            if(token == null || "".equals(token)){
                throw new ResultException("O parametro token nao pode ser nulo!");
            }

            if(index < 0){
             throw new ResultException("O indice nao pode ser menor que zero");
            }

            if(json == null || json.isNull(token)){
             throw new ResultException("O objeto esta vazio para busca");
            }

            JSONArray arr = json.optJSONArray(token);
            return arr.getJSONObject(index);
        } catch (Exception e) {
            throw new ResultException("Erro ao obter o objeto" + e.getMessage());
        }
    }
}
