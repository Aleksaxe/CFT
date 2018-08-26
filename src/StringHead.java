import java.io.File;
import java.io.FileNotFoundException;

public class StringHead extends FileHead {
    String x,y;
    private int input;
    /**
     * @param index
     * @param filepath путь к читаемому файлу
     * @throws FileNotFoundException
     */
    protected StringHead(int index, File filepath) throws FileNotFoundException {
        super(index, filepath);
    }

    @Override
    Object convert(String input) {
        return input;
    }

    @Override
    int compare(Object x, Object y) {
        this.x= (String) x;
        this.y= (String) y;
        int z=((String) x).compareTo((String) y);
        return z;
    }
}
