import com.IDbyDNA.Service.DataFormatter;
import com.IDbyDNA.Service.Impl.JsonFormatter;
import com.IDbyDNA.Service.Impl.TextFormatter;

public class IDbyDNAApplication {

    public static void main(String args[]) {

        //Question 1: Calculating distinct k-mers, total k-mers and highest k-mer count

        //Question 2: Parsing through test and JSON file (Includes both valid and invalid scenarios)

        DataFormatter dataFormatterTextValid = new TextFormatter("src/main/resources/sample3.txt");
        dataFormatterTextValid.runAnalysis();
        dataFormatterTextValid.printAnalysis();
        System.out.println("");

        DataFormatter dataFormatterTextInvalid = new TextFormatter("src/main/resources/sample4.txt");
        dataFormatterTextInvalid.runAnalysis();
        dataFormatterTextInvalid.printAnalysis();
        System.out.println("");

        DataFormatter dataFormatterJsonValid = new JsonFormatter("src/main/resources/sample1.json");
        dataFormatterJsonValid.runAnalysis();
        dataFormatterJsonValid.printAnalysis();
        System.out.println("");

        DataFormatter dataFormatterJsonInvalid = new JsonFormatter("src/main/resources/sample2.json");
        dataFormatterJsonInvalid.runAnalysis();
        dataFormatterJsonInvalid.printAnalysis();
        System.out.println("");

    }

}
