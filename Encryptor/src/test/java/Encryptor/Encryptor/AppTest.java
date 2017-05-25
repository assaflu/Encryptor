package Encryptor.Encryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws FileNotFoundException 
     */
    public AppTest( String testName ) throws FileNotFoundException
    {
    	System.out.println(new Scanner(new File(AppTest.class.getResource("small.xml").getFile())).useDelimiter("\\Z").next());
        //super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
