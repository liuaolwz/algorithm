package com.owl.algorithm.vector;

import com.owl.algorithm.stack.Stack;
import com.sun.istack.internal.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 向量模板类
 */
public class Vector<T extends Comparable<T>> {
    private int _size, _capacity;
    private Object[] _elem;
    private final int DEFAULT_CAPACITY = 10;

    public Vector() {
        this._size = 0;
        this._capacity = DEFAULT_CAPACITY;
        this._elem = new Object[_capacity];
    }

    public Vector(int _capacity) {
        this._size = 0;
        this._capacity = _capacity;
        this._elem = new Object[_capacity];
    }

    /**
     * 数组区间复制
     */
    public Vector(T[] arr, int lo, int hi) {
        copeFrom(arr, lo, hi);
    }

    /**
     * 向量区间复制
     */
    public Vector(Vector v, int lo, int hi) {
        copeFrom(v.getElem(), lo, hi);
    }

    /**
     * 以待复制区间长度的2倍作为初始长度，将数中的元素复制到向量中
     */
    private void copeFrom(Object[] arr, int lo, int hi) {
        _elem = new Object[(hi - lo) << 2];
        _size = 0;
        while (lo < hi) {
            _elem[_size++] = arr[lo++];
        }
    }

    protected int size() {
        return _size;
    }

    protected boolean empty() {
        return _size <= 0;
    }

    private Object[] getElem() {
        return _elem;
    }

    /**
     * 判断逆序对
     */
    @SuppressWarnings("unchecked")
    protected int dissordered() {
        int asc = 0, desc = 0;
        for (int i = 0; i < _size - 1; i++) {
            if (((T) _elem[i]).compareTo(((T) _elem[i + 1])) > 0) {
                desc++;
            } else {
                asc++;
            }
        }
        return Math.abs(desc - asc);
    }

    @SuppressWarnings("unchecked")
    protected T get(int rank) {
        return (T) _elem[rank];
    }

    /**
     * 无序向量查找,返回最后一个t的位置，没有时返回lo-1
     */
    protected int find(@NotNull T t, int lo, int hi) {
        while (lo < hi-- && !t.equals(_elem[hi])) ;
        return hi;//若lo<hi则查找失败
    }

    /**
     * 有序向量区间[lo,hi)查找，二分查找
     */
    @SuppressWarnings("unchecked")
    protected int search(@NotNull T t, int lo, int hi) {
        int mi = (lo + hi) >> 2;
        while (lo < hi) {
            int cmp = t.compareTo((T) _elem[mi]);
            if (cmp < 0) {//深入前半段
                hi = mi;
            } else if (0 < cmp) {//深入后半段
                lo = mi + 1;
            } else {
                return mi;
            }
        }
        return -1;//查找失败
    }

    @SuppressWarnings("unchecked")
    protected T remove(int rank) {
        if (rank < 0 || rank > _size - 1) {
            return null;
        }
        _size--;
        shrink();
        T tmp = (T) _elem[rank];
        while (rank++ < _size - 1) {
            _elem[rank] = _elem[rank + 1];
        }
        return tmp;
    }

    protected int insert(int index, T data) {
        _size++;
        expand();
        _elem[index] = data;
        return index;
    }

    /**
     * 扩容
     */
    private void expand() {
        if (_size < _capacity) return;
        final Object[] oldElem = _elem;
        _elem = new Object[_capacity <<= 1];
        System.arraycopy(oldElem, 0, _elem, 0, _size);
    }

    /**
     * 缩容
     */
    private void shrink() {
        if (_capacity < DEFAULT_CAPACITY << 1) return;
        if (_size << 2 > _capacity) return;//以25%为界
        Object[] oldElem = _elem;
        _elem = new Object[_capacity >>= 1];//容量减半
        System.arraycopy(oldElem, 0, _elem, 0, _size);
    }

    /**
     * 交换两个元素
     */
    private void swap(int i, int j) {
        if (i == j) return;
        Object tmp = _elem[i];
        _elem[i] = _elem[j];
        _elem[j] = tmp;
    }

    /**
     * 判断i元素是否比j元素小
     */
    private boolean less(int i, int j) {
        return get(i).compareTo(get(j)) > 0;
    }

    //各种排序方法

    /**
     * 冒泡:每一趟内循环都将最大的元素归位到当前无序部分的最右端，无序部分的规模进而-1
     */
    public void bubbleSort1() {
        for (int i = 1; i < _size; i++) {
            for (int j = 1; j < _size - i; j++) {
                if (!less(j - 1, j)) swap(j - 1, j);
            }
        }
    }

    public void bubbleSort2() {
        for (int i = _size; 0 < i; i--) {
            for (int j = 1; j < i; j++) {
                if (!less(j - 1, j)) swap(j - 1, j);
            }
        }
    }

    //改进版，可以避免不必要的比较
    public void bubbleSort3() {
        boolean sorted = false;
        int n = _size;//无序部分的规模
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < n; i++) {
                if (!less(i - 1, i)) {
                    sorted = false;
                    swap(i - 1, i);
                }
            }
            n--;
        }
    }

    /**
     * 选择排序：每一次内循环都选择右侧无序部分的最小元素，与无序部分首元素交换
     */
    public void selectSort() {
        for (int i = 0; i < _size; i++) {
            int min = i;//从i开始，选择最小的元素
            for (int j = i + 1; j < _size; j++) {
                if (less(j, min)) min = j;
            }
            //交换min与i
            swap(i, min);
        }
    }

    /**
     * 插入排序
     */
    public void insertSort() {

    }

    //归并排序：二路归并
    public void mergeSort(int lo, int hi) {
        if (hi - lo < 2) return;//递归基
        int mi = (lo + hi) >> 1;
        mergeSort(lo, mi);
        mergeSort(mi, hi);
        merge(lo, mi, hi);
    }

    /**
     * 归并算法:合并有序向量[lo,mi)和[mi,hi)
     */
    @SuppressWarnings("unchecked")
    private void merge(int lo, int mi, int hi) {
        int a = mi - lo;
        Object[] A = new Object[a];//缓存前子数组
        System.arraycopy(_elem, lo, A, 0, a);
        int b = hi - mi;
        Object[] B = new Object[b];//缓存后子数组
        System.arraycopy(_elem, mi, B, 0, b);
        for (int i = 0, j = 0, k = 0; j < a | k < b; ) {
            T ta = (T) A[j];
            T tb = (T) B[k];
            if ((j < a) && (!(k < b) || (ta.compareTo(tb) <= 0))) _elem[lo + i++] = A[j++];
            if ((k < b) && (!(j < a) || (tb.compareTo(ta) < 0))) _elem[lo + i++] = A[j++];
        }
    }

    public void quickSort1() {
        quickSort(0, _size);//对后缀进行递归
    }

    /**
     * 快速排序：非递归
     */
    public void quickSort2() {
        Stack<Integer> rangStack = new Stack<>();
        rangStack.push(0);
        rangStack.push(_size - 1);
        while (!rangStack.empty()) {
            int lo = rangStack.pop();
            int hi = rangStack.pop();
            int mi = partition(lo, hi);
            if (lo < mi - 1) {//左侧序列入栈
                rangStack.push(lo);
                rangStack.push(mi - 1);
            }
            if (mi + 1 < hi) {//右侧序列入栈
                rangStack.push(mi + 1);
                rangStack.push(hi);
            }
        }
    }

    /**
     * 选取一个中轴点，使得轴点左侧元素都比轴点小，右侧元素都比轴点大，然后递归处理
     */
    private void quickSort(int lo, int hi) {
        if (hi - lo < 2) {
            return;
        }
        int mi = partition(lo, hi - 1);
        quickSort(lo, mi);//对前缀进行递归
        quickSort(mi + 1, hi);//对后缀进行递归
    }

    //轴点选取
    private int partition(int lo, int hi) {
        swap(lo, ThreadLocalRandom.current().nextInt(lo, hi));//随机交换lo与任意一个元素的位置，可以降低轴点即为端点的可能性
        T pivot = get(lo);//将第一个元素作为轴点，备份，此时可以认为lo为空
        while (lo < hi) {
            /*
                注意pivot.compareTo(get(hi)) <= 0条件，即如果相等依然拓展
             */
            while ((lo < hi) && pivot.compareTo(get(hi)) <= 0) hi--;//向左拓展右端向量
            _elem[lo] = _elem[hi];//一旦遇到小于pivot者，则将其填充到lo的位置，从而归入左侧，此时可认为hi处为空，再从左侧遍历
            while ((lo < hi) && get(hi).compareTo(pivot) <= 0) lo++;//向右拓展左端向量
            _elem[hi] = _elem[lo];//一旦遇到大于pivot者，将其填充至hi位置，归入右侧，此时认为lo再次置空
        }
        //lo==hi，且该位置原始元素已经归位
        _elem[lo] = pivot;//将备份轴点至于左右侧向量中间
        return lo;
    }

    //堆排序
    public void heapSort() {

    }

    //希尔排序
    public void sellSort() {

    }

}
