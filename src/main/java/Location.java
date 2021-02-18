import org.apache.tools.ant.types.FileList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.BufferedReader;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Location {

    String reportLocation;

    public void getLocation() {
        String[] testFolderNameArray = {};
        Integer testFolderCounter = 0;
        Integer counterForArray = 0;
        try {
            File folder = new File("/Users/ignacio/workspace/angular-mobile-automation-appium/src/main/java/reports");
            File[] listOfFiles = folder.listFiles();
            //System.out.println(listOfFiles.length);
            for (File file : listOfFiles) {
                if ( file.isDirectory() == true && file.getName().contains("test") == true) {
                        testFolderCounter++;
                        //fileBefore = file.getName();
                        //System.out.println(file.getName());
                }
            }
            testFolderNameArray = new String[testFolderCounter];
            for (File file : listOfFiles) {
                if ( file.isDirectory() == true && file.getName().contains("test") == true) {
                    testFolderNameArray[counterForArray] = file.getName();

                    System.out.println(testFolderNameArray[counterForArray]);
                    counterForArray++;
                }
            }
            for (int i = 0; i < counterForArray-1; i++){
                for(int j = i+1; j < counterForArray; j++) {
                    if (testFolderNameArray[i].compareTo(testFolderNameArray[j]) > 0) {
                        String temp = testFolderNameArray[i];
                        testFolderNameArray[i] = testFolderNameArray[j];
                        testFolderNameArray[j] = temp;
                    }
                }
            }
            //System.out.println(Arrays.toString(testFolderNameArray));
            reportLocation = testFolderNameArray[counterForArray-1];
            //System.out.println("Location"+reportLocation);

        } catch (Exception ex) {

        }
    }
}
