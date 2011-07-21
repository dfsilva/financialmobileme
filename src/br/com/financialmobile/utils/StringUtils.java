/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financialmobile.utils;

import java.util.Vector;

/**
 *
 * @author diego
 */
public class StringUtils {

    public static String[] split(String original, String separator) {
        Vector nodes = new Vector();
        //String separator = ":";
// Parse nodes into vector
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
// Get the last node
        nodes.addElement(original);

// Create splitted string array
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
            }
        }
        return result;
    }

    public static boolean comparaStrings(String original, String comparator){
        for(int i=0; i< comparator.length(); i++){
            char s = comparator.charAt(i);   

        }

        return false;
    }
}
