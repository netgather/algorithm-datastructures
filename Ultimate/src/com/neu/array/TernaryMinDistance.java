package com.neu.array;

/**
 * 问题:如何求解最小三元组距离
 *     已知3个升序整数数组a[l],b[m],c[r]。请在3个数组中各找一个元素，使得组成的三元组距离最小。
 *     三元组的距离定义是：假设a[l],b[m],c[r]是一个三元组，那么距离为distance=max(|a[l]-b[m]|,|a[l]-c[r]|,|b[m]-c[r]|),
 *     设计一个求最小三元组距离的最优算法
 * Created by lihongyan on 2015/10/30.
 */
public class TernaryMinDistance {
    //方法一、蛮力法：分别遍历3个数组中的元素，求出它们的距离，然后冲这些值里面查找最小值
    //这个算法的时间复杂度为O(l*m*r),显然这个方法没有用到数组升序这一特性，因此不是最好的方法
    public static int max(int a, int b, int c) {
        int max = a > b ? a : b;
        max = max > c ? max : c;
        return max;
    }

    public static int ternaryMinDistance1(int[] a, int[] b, int[] c) {
        int aLen = a.length;
        int bLen = b.length;
        int cLen = c.length;
        int minDist = max(Math.abs(a[0] - b[0]), Math.abs(a[0] - c[0]), Math.abs(b[0] - c[0]));
        int dist = 0;
        for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < bLen; j++) {
                for (int k = 0; k < cLen; k++) {
                    dist = max(Math.abs(a[i] - b[j]), Math.abs(a[i] - c[k]), Math.abs(b[j] - c[k]));
                    if (minDist > dist) {
                        minDist = dist;
                    }
                }
            }
        }
        return minDist;
    }

    //方法二、最小距离法：假设当前遍历到这3个数组中的元素分别为x(i)、y(i)、z(i),并且x(i)<=y(i)<=z(i),此时它们的距离肯定为D=z-y，
    // 那么可以分如下三种情况讨论:
    // ①如果接下来求x(i)、y(i)、z(i+1)的距离，那么由于z(i)<=z(i+1),显然D(i)<=D(i+1)，所以它们的距离必定为：D(i)
    // ②如果接下来求x(i)、y(i+1)、z(i),由于y(i)<=y(i+1),如果y(i+1)<=z(i),此时它们的距离仍然为D(i);如果y(i+1)>z(i),
    //   那么此时的距离为D(i+1)=y(i+1)-x(i),显然D(i+1)>D(i)，所以结果仍然为D(i).
    // ③如果接下来求x(i+1)、y(i)、z(i),由于x(i)<x(i+1),如果x(i+1)<|z(i)-x(i)|+z(i),此时它们的距离为D(i+1)=z(i)-x(i+1),
    //   显然D(i+1)<D(i)。因此，对于情况三有可能产生新的解。
    //   综上所述，在求最小距离是只需考虑第3种情况，
    // 具体思路为：从3个数组的第一个元素开始，先求出它们的距离minDist，接着找出这3个数中最小数对应的数组，只对这个数组的下标往后移一个位置，
    //           接着求3个数组中当前元素的距离，若比minDist小，则把当前距离赋值给minDist,以此类推，直到遍历完其中一个数组
    public static int min(int a, int b, int c) {
        int min = a < b ? a : b;
        min = min < c ? min : c;
        return min;
    }

    public static int ternaryMinDistance2(int[] a, int[] b, int[] c) {
        int aLen = a.length;
        int bLen = b.length;
        int cLen = c.length;
        int minDist = Integer.MAX_VALUE;
        int curDist = 0;
        int min = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            curDist = max(Math.abs(a[i] - b[j]), Math.abs(a[i] - c[k]), Math.abs(b[j] - c[k]));
            if (curDist < minDist) {
                minDist = curDist;
            }
            //找出当前遍历到3个数组中的最小值
            min = min(a[i], b[j], c[k]);
            if (min == a[i]) {
                if (++i < aLen) {
                    continue;
                }else{
                    break;
                }
            } else if (min == b[j]) {
                if (++j < bLen) {
                    continue;
                }else{
                    break;
                }
            } else {
                if (++k < cLen) {
                    continue;
                }else{
                    break;
                }
            }
        }
        return minDist;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 5, 7};
        int[] b = {10, 12, 14, 16, 17};
        int[] c = {20, 21, 23, 24, 27, 30};
        System.out.println(ternaryMinDistance1(a, b, c));
        System.out.println(ternaryMinDistance2(a, b, c));
    }
}
