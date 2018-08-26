import java.io.File;
import java.io.FileNotFoundException;

public class IntegerHead extends FileHead{
    Integer x,y;
    private int input;

    /**
     * @param index
     * @param filepath путь к читаемому файлу
     * @throws FileNotFoundException
     */
    protected IntegerHead(int index, File filepath) throws FileNotFoundException {
        super(index, filepath);
    }

    @Override
    Object convert(String input) {
        this.input= Integer.parseInt(input);
        return this.input;
    }

    @Override
    int compare(Object x, Object y) {
        this.x= (Integer) x;
        this.y= (Integer) y;
        int z=((Integer) x).compareTo((Integer) y);
        return  z;
    }
}
