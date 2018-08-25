import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    FileWorker f1=new FileWorker("1.txt");
    FileWorker f2=new FileWorker("2.txt");
    Sort s=new Sort();
    s.Sort(f2.getAr(),false,true);
        System.out.println(f2.getAr());
       Merge m=new Merge(f1.getAr(),f2.getAr());
        System.out.println(f2.getAr());
    }
}
