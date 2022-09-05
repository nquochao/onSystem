package olivia.brainstormer;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

public class TestCharPlay {
	
	@Test
	public void testReadFile() {
		String name="olivia";
		System.out.println(name);
		System.out.println(reverse(name));
		printOdds();
		readFile("./src/test/java/olivia/brainstormer/"+TestCharPlay.class.getSimpleName()+".java");
		System.out.println(formatRGB(10,220,128));
	    for ( int i = 0; i < 10; i++ ) {
            System.out.print ( fib(i) + ", " );
        }
        System.out.println ( fib(10) );
		}
    /**
     * @param s
     * @return
     */
    public static String reverse ( String s ) {
        int length = s.length(), last = length - 1;
        char[] chars = s.toCharArray();
        for ( int i = 0; i < length/2; i++ ) {
            char c = chars[i];
            chars[i] = chars[last - i];
            chars[last - i] = c;
        }
        return new String(chars);
    }
    /**
     * 
     */
    public static void printOdds() {
        for (int i = 1; i < 100; i += 2) {
            System.out.println ( i );
        }
    }
    
    public static void readFile ( String name ) {
        try {
            int total = 0;
            BufferedReader in = new BufferedReader ( new FileReader ( name ));
            for ( String s = in.readLine(); s != null; s = in.readLine() ) {
               
            	System.out.println ( s );
            }
            in.close();
        }
        catch ( Exception xc ) {
            xc.printStackTrace();
        }
    }
    
    public static int largest ( int[] input ) {
        int max = Integer.MIN_VALUE;
        for ( int i = 0; i < input.length; i++ ) {
            if ( input[i] > max ) { max = input[i]; }
        }
        return max;
    }
    
    public String formatRGB ( int r, int g, int b ) {
        return (toHex(r) + toHex(g) + toHex(b)).toUpperCase();
    }

    /**
     * @param c
     * @return
     */
    public String toHex ( int c ) {
        String s = Integer.toHexString ( c );
        return ( s.length() == 1 ) ? "0" + s : s;
    }
    /**
     * fibonnacci
     * @param n
     * @return
     */
    static long fib(int n) {
        return n <= 1 ? n : fib(n-1) + fib(n-2);
    }
}
