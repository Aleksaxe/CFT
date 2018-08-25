import java.io.*;
import java.util.ArrayList;

public class FileWorker {
    ArrayList<Integer> list = new ArrayList<>();
    ;
    int i = 0;

    FileWorker(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        String s;

        while ((s = br.readLine()) != null) {

            list.add(Integer.parseInt(s));
        }
    }
public ArrayList getAr(){
        return list;
}

}
