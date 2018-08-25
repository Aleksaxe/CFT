import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    private boolean ascending;
    private boolean intOrString;
    ArrayList list;

    private int compare(Object o1, Object o2) {
        if (this.intOrString) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else {
            return ((String) o1).compareTo((String) o2);
        }
    }

    void MergeSort(List list,boolean ascending,boolean intOrString) {
        this.intOrString=intOrString;
        this.ascending=ascending;
        if (this.ascending) {
            for (int i = 1; i < list.size(); i++) {
                Object temp = list.get(i);
                int j;
                for (j = i - 1; j >= 0 && compare(temp, list.get(j)) < 0; j--) {
                    list.set(j + 1, list.get(j));
                }
                list.set(j + 1, temp);
            }
        } else {
            for (int i = 1; i < list.size(); i++) {
                Object temp = list.get(i);
                int j;
                for (j = i - 1; j >= 0 && compare(temp, list.get(j)) > 0; j--) {
                    list.set(j + 1, list.get(j));
                }
                list.set(j + 1, temp);
            }
        }
    }


}

