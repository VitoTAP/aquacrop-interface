package org.uncertweb.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Adapted from http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=4.
 * 
 * @author Richard Jones
 *
 */
public class StreamGobbler extends Thread {
    private InputStream stream;
    
    public StreamGobbler(InputStream stream) {
        this.stream = stream;
    }
    
    public void run() {
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while (reader.readLine() != null) {
            	// ignore
            }    
        }
        catch (IOException e) {
        	// also ignore
        }
    }
}
