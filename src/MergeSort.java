import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MergeSort {


    public MergeSort(String outPutNameFile) throws IOException {
        BufferedWriter bf=new BufferedWriter(new FileWriter(outPutNameFile));
        FileWorker fileWorker1=new FileWorker("1.txt");
        FileWorker fileWorker2=new FileWorker("2.txt");
        
    }
}
