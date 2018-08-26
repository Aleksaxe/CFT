import java.io.*;

abstract class FileHead<T> {
	private T headData;
	private BufferedReader br;
	private FileReader reader;
	private boolean finishReached;
    private File filepath;

    /**
     *
     * @param filepath путь к читаемому файлу
     * @throws FileNotFoundException
     */
	protected FileHead( File filepath) throws FileNotFoundException {
		reader = new FileReader(filepath);
		br = new BufferedReader(reader);
	}
	
	T getHeadData() {
		return headData;
	}
	
	public boolean canAdvance(){
		return !finishReached;
	}

    /**
     *
     * @param lastOutputWritten последняя запись для сравнения
     * @throws IOException
     */
	public void tryAdvance(T lastOutputWritten) throws IOException {
		String line;
		T data = null;
		while ((line = br.readLine()) != null) {
			data = convert(line);
			if (compare(data, lastOutputWritten)<0){
				System.out.println("Нарушен порядок сортировки: элемент " + line +
						" из файла " + filepath + " меньше последнего записанного в выходной файл элемента "
						+ lastOutputWritten.toString());
			}else{
				break;
			}
		}
		//если читать больше нечего то  закрываем поток
		if (line==null) {
			finishReached = true;
			close();
		}
		// и присваиваем данные...
		headData = data;
	}
	//закрываем ридеры
	private void close() throws IOException {
		br.close();
		reader.close();
	}


    /**
     *
     * @param input конвертация строки\числа к объекту T
     */
	abstract T convert(String input);

    /**
     *Сравниваем два входных параметра .
     */
	abstract int compare(T x,T y);
}