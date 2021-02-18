import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;

public class XmlFilter {

    private String reportLocation;
    Integer listNumber,testPass=0,testFail=0;
    String status,line;
    String[] arrayTestsFail = {};

    public void init(String reportLocation) {

        try {
            File file = new File("src/main/java/reports/"+reportLocation+"/report.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList nList = document.getElementsByTagName("status");
            listNumber = nList.getLength();
            arrayTestsFail = new String[listNumber];

            for (int count = 0; count < listNumber ;count++){

                String imageName = document.getElementsByTagName("report").item(count).getTextContent();
                if (imageName != ""){
                    status = document.getElementsByTagName("status").item(count).getTextContent();
                    line = document.getElementsByTagName("line").item(count).getTextContent();

                    if (status.contains("false")){
                        testFail++;
                        arrayTestsFail[testFail-1] = "Test Fail in; " + imageName;

                    }
                    else{
                        testPass++;
                    }
                }
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        for(int i=0; i < arrayTestsFail.length;i++){
            if(arrayTestsFail[i] != null) {
                //System.out.println(arrayTestsFail[i]);
            }
        }
    }

}






