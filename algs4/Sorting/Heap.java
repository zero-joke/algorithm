package algs4.Sorting;

import java.util.Arrays;

public class Heap {
    private Object[] data;
    private int size;

    public Heap() {
        data = new Object[0];
    }

    public Heap(int cap) {
        data = new Object[cap];
    }

    public void offer(Comparable<Object> v) {
        if (size >= data.length) {
            grow(size + 1);
        }
        size++;
        siftUp(size - 1, v);
    }

    public Comparable<Object> poll() {
        Comparable<Object> v = (Comparable<Object>) data[0];
        siftDown(0, (Comparable<Object>) data[--size]);
        return v;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int cap) {
        if (cap > data.length) {
            data = Arrays.copyOf(data, cap);
        }
    }

    private void siftUp(int idx, Comparable<Object> v) {
        while (idx > 0) {
            int par = (idx - 1) >>> 1;
            if (Sorts.less((Comparable<Object>) data[par], v)) {
                break;
            }
            data[idx] = data[par];
            idx = par;
        }
        data[idx] = v;
    }

    private void siftDown(int idx, Comparable<Object> v) {
        int half = size >>> 1;
        while (idx < half) {
            int left = (idx << 1) + 1;
            int ch = left;
            int right = left + 1;
            // 选择较小（对于最小堆）的子节点跟父节点交换，注意判断右子节点是否存在.
            if (right < size && Sorts.less((Comparable<Object>) data[right], (Comparable<Object>) data[left])) {
                ch = right;
            }
            if (Sorts.less(v, (Comparable<Object>) data[ch])) {
                break;
            }
            data[idx] = data[ch];
            idx = ch;
        }
        data[idx] = v;
    }
}