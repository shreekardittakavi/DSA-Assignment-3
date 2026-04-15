
public class SortingAlgorithms<E extends Comparable<? super E>> {

    public void insertionSort(E[] array) {
        if (array.length <= 1) {
            return;
        }

        int j;

        for (int p = 1; p < array.length; p++) {
            E tmp = array[p];

            for (j = p; j > 0 && tmp.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }

            array[j] = tmp;
        }
    }

    public void heapSort(E[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            percDown(array, i, array.length);
        }
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            percDown(array, 0, i);
        }
    }

    private void percDown(E[] array, int i, int n) {
        int child;
        E temp;

        for (temp = array[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && array[child].compareTo(array[child + 1]) < 0) {
                child++;
            }
            if (temp.compareTo(array[child]) < 0) {
                array[i] = array[child];
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    public void mergeSort(E[] array) {
        @SuppressWarnings("unchecked")
        E[] tmpArray = (E[]) new Comparable[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
    }

    private void mergeSort(E[] array, E[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(array, tmpArray, left, center);
            mergeSort(array, tmpArray, center + 1, right);
            merge(array, tmpArray, left, center + 1, right);
        }
    }

    private void merge(E[] array, E[] tmpArray, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int tmp = left;
        int numElements = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (array[left].compareTo(array[right]) <= 0) {
                tmpArray[tmp++] = array[left++];
            } else {
                tmpArray[tmp++] = array[right++];
            }
        }

        while (left <= leftEnd) {
            tmpArray[tmp++] = array[left++];
        }

        while (right <= rightEnd) {
            tmpArray[tmp++] = array[right++];
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            array[rightEnd] = tmpArray[rightEnd];
        }
    }

    public void quickSort(E[] array, int cutoff) {
        quickSort(array, 0, array.length, cutoff);
    }

    private void quickSort(E[] array, int left, int right, int cutoff) {
        int size = right - left;
        if (size <= 1) {
            return;
        }
        if (size <= cutoff) {
            insertionSortRange(array, left, right);
            return;
        }

        int middle = left + (size / 2);
        int pivotIndex = median3Index(array, left, middle, right - 1);
        int partition = partition(array, left, right, pivotIndex);
        quickSort(array, left, partition, cutoff);
        quickSort(array, partition + 1, right, cutoff);
    }

    private int partition(E[] array, int left, int right, int pivotIndex) {
        E pivot = array[pivotIndex];
        swap(array, pivotIndex, right - 1);

        int storeIndex = left;
        for (int i = left; i < right - 1; i++) {
            if (array[i].compareTo(pivot) < 0) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        swap(array, storeIndex, right - 1);
        return storeIndex;
    }

    private void insertionSortRange(E[] array, int left, int right) {
        for (int p = left + 1; p < right; p++) {
            E tmp = array[p];
            int j = p;
            while (j > left && tmp.compareTo(array[j - 1]) < 0) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = tmp;
        }
    }

    private void swap(E[] array, int idx1, int idx2) {
        E temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    private int median3Index(E[] array, int a, int b, int c) {
        E first = array[a];
        E second = array[b];
        E third = array[c];

        if (first.compareTo(second) <= 0) {
            if (second.compareTo(third) <= 0) {
                return b;
            } else if (first.compareTo(third) <= 0) {
                return c;
            } else {
                return a;
            }
        } else if (first.compareTo(third) <= 0) {
            return a;
        } else if (second.compareTo(third) <= 0) {
            return c;
        } else {
            return b;
        }
    }
}
