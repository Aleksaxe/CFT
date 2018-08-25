import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    FileWorker f1=new FileWorker("1.txt");
    FileWorker f2=new FileWorker("2.txt");
    MergeSort m=new MergeSort();
    m.MergeSort(f2.getAr(),false,true);
        System.out.println(f1.getAr());
        System.out.println(f2.getAr());
    }
}
