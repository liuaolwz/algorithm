package com.owl.algorithm.sort;

public enum Sort {
  /**
   * 选择排序
   * 思想：找到数组中最小的元素，和第一元素交换位置；其次，在剩下的元素找到最小的元素，再与第二个元素交换
   * 性能：~N^2/2次比较，N次交换
   */
  SELECTION {
    @Override
    public void sort(Comparable[] a) {
      final int n = a.length;
      for (int i = 0; i < n; i++) {
        int min = i;
        for (int j = i + 1; j < n; j++) {
          if (less(a[j], a[min]))
            min = j;
        }
        exch(a, i, min);
      }
    }
  },
  /**
   * 插入排序
   * 思想：将第i个元素依次与它左边比它大的元素交换位置，类似于扑克牌排序的时候，我们会把小的牌插入到比它大的牌前面
   * 为了给这个小的牌腾位置，前面比它大的牌都要向右移动一位.<br/>
   * 性能：对于长度为N且不重复的数组，平均情况下插入排序需要~N^2/4次比较，N^2/4次交换
   * 最坏的情况需要 N^2/2次比较，N^2/2次交换，最好的情况需要N-1次比较和0次交换，当数组的初始值比较有序时性能最高。
   * 更通用的，插入排序的交换次数和数组中倒置的数量相同，比较次数小于等于（倒置数量+N-1），倒置数直接反映初始数组的有序性，
   * 例如E X A M P L E 一共有11对倒置：E-A,X-A,X-M,X-P,X-L,X-E,M-L,M-E,P-L,P-E,L-E
   */
  INSERTION {
    @Override
    public void sort(Comparable[] a) {
      final int n = a.length;
      for (int i = 0; i < n; i++) {
        //将a[i]加入到a[i-1],a[i-2],a[i-3]...之中
        for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
          exch(a, j, j - 1);
        }
      }
    }
  },

  /**
   * 希尔排序
   * 思想：插入排序的一个变体,交换不相邻的元素以对数组的局部进行排序，并最终用插入排序将局部有序的数组进行排序<br/>
   * 性能：一般情况下希尔排序比上述两种排序快的多，数组越大，优势越大，它的运行时间达不到平方级别。
   * 我们给出的h实现方案中最坏情况是与N^(3/2)正比，更通用的：使用递增序列1，4，13，40...的希尔排序不会超过N的若干倍乘于递增序列的长度
   */
  SELL {
    @Override
    public void sort(Comparable[] a) {
      final int n = a.length;
      int h = 1;
      while (h < n / 3) {
        h = 3 * h + 1;//1，4，13，40...
      }
      while (h >= 1) {
        //将数组变为h有序
        for (int i = h; i < n; i++) {
          //将a[i]插入到a[i-h],a[i-2h]...之中
          for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
            exch(a, j, j - h);
          }
        }
        //最终h会变为1，也就是说最后一次while循环相当于一次完整的插入排序
        //只不过在此之前的循环中已经将数组的倒置数降到最低了，因此最后一次循环不会有太多的交换和比较
        h = h / 3;
      }
    }
  };

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Comparable[] a, int i, int j) {
    final Comparable tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static void show(Comparable[] a) {
    for (Comparable anA : a) {
      System.out.print(anA + " ");
    }
    System.out.println();
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  public abstract void sort(Comparable[] a);
}
