package net.sf.javaanpr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Simon Steinaa
 */
@RunWith(Parameterized.class)
public class RecognitionAllIT {

    // instance variables needed for parameterized test
    File carSnapshot;
    String expectedPlate;
    
    // the unit being tested
    private Intelligence intel;
       
    
    
    public RecognitionAllIT(File carSnapshot, String expectedPlate) {
        this.carSnapshot = carSnapshot;
        this.expectedPlate = expectedPlate;
    }

    @Before
    public void initialize() throws Exception {
        // the unit being tested
        intel = new Intelligence();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testDataCreator() throws Exception {
        // get the path to the snapshots (the image names of the cars)
        String snapshotDirPath = "src/test/resources/snapshots";
        // get the path to the results file (works like a map)
        String resultsPath = "src/test/resources/results.properties";
        // open the results as an input stream
        InputStream resultsStream = new FileInputStream(new File(resultsPath));

        // load the values from the file into a Properties object
        Properties properties = new Properties();
        properties.load(resultsStream);
        resultsStream.close();
        // make sure, that the load succeeded
        assertTrue(properties.size() > 0);
        
        // load the snapshots into a File array
        File snapshotDir = new File(snapshotDirPath);
        File[] snapshots = snapshotDir.listFiles();
        assertNotNull(snapshots);
        // make sure, that the load succeeded
        assertTrue(snapshots.length > 0);
        
        // return the values in a Object array with the corresponding 
        // comparison values
        Collection<Object[]> dataForOneImage = new ArrayList();
        for (File file : snapshots) {
            String name = file.getName();
            String plateExpected = properties.getProperty(name);
            dataForOneImage.add(new Object[]{file, plateExpected});
        }
        return dataForOneImage;
    }
    
    @Test
    public void testTheSnapshots() throws Exception {
        assertThat(expectedPlate,
                is(intel.recognize(new CarSnapshot(new FileInputStream(carSnapshot)), false)));
    }
}
