import java.util.ArrayList;

public class Merge {
    ArrayList list1;
    ArrayList list2;
    Integer[] ar1;
    Integer[] ar2;
    public Merge(ArrayList list1, ArrayList list2) {
        this.list1 = list1;
        this.list2 = list2;
        ar1 = (Integer[]) list1.toArray(new Integer[list1.size()]);
        ar2 = (Integer[]) list1.toArray(new Integer[list2.size()]);

        //int[] ar3=ar1;
       // int[] ar4=new int[ar2.length];
      //  for (int i=0;i<ar3.length;i++) System.out.println(ar3[i]);
       // merging(ar3,ar4);
    }


    public static int[] merging(Integer[] arr1, Integer arr2[]) {
        int n = arr1.length + arr2.length;
        int[] mergetArray = new int[n];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n; i++) {
            if (i1 == arr1.length) {
                mergetArray[i] = arr2[i2++];
            } else if (i2 == arr2.length) {
                mergetArray[i] = arr1[i1++];
            } else {
                if (arr1[i1] < arr2[i2]) {
                    mergetArray[i] = arr1[i1++];
                } else {
                    mergetArray[i] = arr2[i2++];
                }
            }
            for (int q=0;q<mergetArray.length;q++){
                System.out.println(mergetArray[q]);
            }
        }
        return mergetArray;

    }


}

