/**
 * @author bhanuchander
 * @version 1.0
 * @since 16th July 2014
 * */
package com.torana.util.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class Utils {
	
	   public static String convertStreamToString(InputStream is)
	            throws IOException {
	        if (is != null) {
	            int fileSize = 5500;
	            Writer writer = new StringWriter();
	            char[] buffer = new char[fileSize];
	            Reader reader = null;
	            try {
	                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	                int n;
	                while ((n = reader.read(buffer)) != -1) {
	                    writer.write(buffer, 0, n);
	                }
	            } finally {
	                is.close();
	                if (reader != null) {
	                    reader.close();
	                }
	            }
	            return writer.toString();
	        } else {
	            return "";
	        }
	    }
	  
}
