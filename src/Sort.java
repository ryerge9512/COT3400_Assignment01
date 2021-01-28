public class Sort {
    private long[] timestamps;
    private int timestampsIndex;
    private int[] keys;
    private int[] temp;
    private int leftBegin = 0;
    private int rightEnd;

    public Sort(int[] keys) {
        timestamps = new long[keys.length];
        this.keys = keys;
        temp = new int[keys.length];
        rightEnd = keys.length - 1;
    }

    public void doInsertionSort() {
        timestampsIndex = 0;

        for (int j = 1; j < keys.length; j++) {
            int key = keys[j];
            int i = j - 1;

            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i = i - 1;
            }

            keys[i + 1] = key;
            timestamps[timestampsIndex++] = System.nanoTime();
        }
        timestamps[timestampsIndex] = System.nanoTime();

//        System.out.println("Insertion sort\n");
        for(int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + "\t Time: " + timestamps[i]);
        }
        System.out.println();
    }

    public void doMergeSort() {
        mergeSort(keys, temp, leftBegin, rightEnd);
//        System.out.println("Merge Sort: \n");
        for(int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + "\t Time: " + timestamps[i]);
        }
        System.out.println();
    }

    public void mergeSort(int[] keys, int[] temp, int leftBegin, int rightEnd) {
        timestampsIndex = 0;
        timestamps[timestampsIndex++] = System.nanoTime();
        int mid = (leftBegin + rightEnd) / 2;

        if(leftBegin < rightEnd){
            mergeSort(keys, temp, leftBegin, mid);
            mergeSort(keys, temp, mid + 1, rightEnd);
            merge(keys, temp, leftBegin, mid + 1, rightEnd);
        }
    }

    public void merge(int[] keys, int[] temp, int leftBegin, int rightBegin, int rightEnd) {
        int leftEnd = rightBegin - 1;
        int index = leftBegin;
        int length = rightEnd - leftBegin + 1;

        while(leftBegin <= leftEnd && rightBegin <= rightEnd){
            if(keys[leftBegin] <= keys[rightBegin]){
                temp[index++] = keys[leftBegin++];
            }else{
                temp[index++] = keys[rightBegin++];
            }
            if(timestampsIndex < keys.length) {
                timestamps[timestampsIndex++] = System.nanoTime();
            }

        }

        while(leftBegin <= leftEnd){
            temp[index++] = keys[leftBegin++];
        }

        while(rightBegin <= rightEnd){
            temp[index++] = keys[rightBegin++];
        }

        for(int i = 1; i <= length; i++, rightEnd--){
            keys[rightEnd] = temp[rightEnd];
        }
    }

    public void doDescendingMergeSort() {
        descendingMergeSort(keys, temp, leftBegin, rightEnd);
//        System.out.println("Descending Merge Sort: \n");
        for(int i : keys){
            System.out.println(i);
        }
        System.out.println();
    }

    public void descendingMergeSort(int[] keys, int[] temp, int leftBegin, int rightEnd) {
        int mid = (leftBegin + rightEnd) / 2;

        if(leftBegin < rightEnd){
            descendingMergeSort(keys, temp, leftBegin, mid);
            descendingMergeSort(keys, temp, mid + 1, rightEnd);
            descendingMerge(keys, temp, leftBegin, mid + 1, rightEnd);
        }
    }

    public void descendingMerge(int[] keys, int[] temp, int leftBegin, int rightBegin, int rightEnd) {
        int leftEnd = rightBegin - 1;
        int index = leftBegin;
        int length = rightEnd - leftBegin + 1;

        while(leftBegin <= leftEnd && rightBegin <= rightEnd){
            if(keys[leftBegin] >= keys[rightBegin]){
                temp[index++] = keys[leftBegin++];
            }else{
                temp[index++] = keys[rightBegin++];
            }
        }

        while(leftBegin <= leftEnd){
            temp[index++] = keys[leftBegin++];
        }

        while(rightBegin <= rightEnd){
            temp[index++] = keys[rightBegin++];
        }

        for(int i = 1; i <= length; i++, rightEnd--){
            keys[rightEnd] = temp[rightEnd];
        }
    }

    public int[] getKeys() {
        return keys;
    }

    public long[] getTimestamps() {
        return timestamps;
    }
}
