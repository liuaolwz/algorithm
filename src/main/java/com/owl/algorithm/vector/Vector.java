package com.owl.algorithm.vector;

/**
 * 向量模板类
 */
public class Vector<T> {
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
   * 以待复制区间长度的2倍作为初始长度，将数组种的元素复制到向量中
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
  protected int dissordered() {
    return 0;
  }

  @SuppressWarnings("unchecked")
  protected T get(int rank) {
    return (T) _elem[rank];
  }

  /**
   * 无序向量查找
   */
  protected int find(int lo, int hi) {
    return 0;
  }

  /**
   * 有序向量查找
   */
  protected int search(int lo, int hi) {
    return 0;
  }

  @SuppressWarnings("unchecked")
  protected T remove(int rank) {
    if (rank<0||rank>_size-1){
      return null;
    }
    _size--;
    T tmp = (T) _elem[rank];
    _elem[rank] = null;
    return tmp;
  }

  protected int insert(int index, T data) {
    expand();
    _size++;
    _elem[index] = data;
    return index;
  }

  protected void sort() {

  }
  private void expand(){
    if (_size<_capacity)return;
    final Object[] oldElem = _elem;
    _elem = new Object[_capacity<<=1];
    for (int i = 0; i < _size; i++) {
      _elem[i] = oldElem[i];
    }
  }
}
