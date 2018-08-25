import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FileWorker {
    ArrayList<Integer> ar1 = new ArrayList<>();
    ;
    int i = 0;

    FileWorker(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        String s;

        while ((s = br.readLine()) != null) {
            //нужна обработка только инт!
            ar1.add(Integer.parseInt(s));
        }

        Collections.sort(ar1);
        System.out.println(ar1);

    }


}
