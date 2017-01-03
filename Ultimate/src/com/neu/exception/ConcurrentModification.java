package com.neu.exception;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 问题：Java ConcurrentModificationException
 * 场景1：单线程环境下，对Vector、ArrayList在迭代的时候如果同时对其进行修改就会抛出
 *      java.util.ConcurrentModificationException异常
 * 原因（以ArrayList为例）：
 *      ①当出现ConcurrentModificationException异常时，
 *       从异常信息可以发现，异常出现在checkForComodification()方法中
 *      ②查看ArrayList源码时发现，ArrayList源码中并没有iterator()这个方法
 *       那么很显然这个方法应该是其父类或者实现的接口中的方法，
 *       我们在其父类AbstractList中找到了iterator()方法的具体实现：
 *       public Iterator<E> iterator() {
 *          return new Itr();
 *       }
 *       从这段代码可以看出返回的是一个指向Itr类型对象的引用，在AbstractList类中找到了Itr类的具体实现，
 *       它是AbstractList的一个成员内部类。Itr实现了Iterator接口，Itr中包含3个成员变量，分别为：
 *       cursor：表示下一个要访问的元素的索引
 *       lastRet：表示上一个访问的元素的索引
 *       expectedModCount：表示对ArrayList修改次数的期望值，它的初始值为modCount。
 *       modCount是AbstractList类中的一个成员变量，该值表示对ArrayList的修改次数，
 *       查看ArrayList的add()和remove()方法就可以发现，
 *       每次调用add()方法或者remove()方法就会对modCount进行加1操作
 *      ③当调用list.iterator()返回一个Iterator之后，通过Iterator的hasNext()方法判断是否还有元素未被访问
 *        hasNext()方法的具体实现：
 *        public boolean hasNext() {
 *          return cursor != size();
 *        }
 *        如果下一个要访问的元素的索引等于ArrayList的大小，那就表示当前的元素是ArrayList的最后的元素
 *        如果下一个要访问的元素的索引不等于ArrayList的大小，那就表示还有元素可以访问。
 *       ④然后，通过Iterator的next()方法获取到下标为0的元素
 *         next()方法的具体实现：
 *         public E next() {
 *            checkForComodification();
 *            try {
 *               E next = get(cursor);
 *               lastRet = cursor++;
 *               return next;
 *             } catch (IndexOutOfBoundsException e) {
 *               checkForComodification();
 *               throw new NoSuchElementException();
 *             }
 *          }
 *         首先，在next()方法中首先调用checkForComodification()，然后根据cursor的值获取下一个元素，
 *              并将cursor的值赋给lastReturn，将cursor的值加1。初始时，lastRet的值为-1，cursor的值为0，
 *              进行一次next()之后lastRet的值为0，cursor的值为1。此时，modCount的值为0，
 *              expectedModCount的值为0。
 *        ⑤接着，进行判断，然后调用ArrayList的remove()方法将对应元素删除，
 *          ArrayList的中的remove方法的具体实现：
 *          public boolean remove(Object o) {
 *              if (o == null) {
 *                  for (int index = 0; index < size; index++)
 *                      if (elementData[index] == null) {
 *                          fastRemove(index);
 *                          return true;
 *                      }
 *              } else {
 *                  for (int index = 0; index < size; index++)
 *                      if (o.equals(elementData[index])) {
 *                          fastRemove(index);
 *                          return true;
 *                      }
 *              }
 *              return false;
 *          }
 *
 *          private void fastRemove(int index) {
 *              modCount++;
 *              int numMoved = size - index - 1;
 *              if (numMoved > 0)
 *              System.arraycopy(elementData, index+1, elementData, index,numMoved);
 *              elementData[--size] = null; // Let gc do its work
 *          }
 *          通过分析ArrayList的remove()方法发现，最终的删除操作是在fastRemove()方法中进行的。
 *          在fastRemove()中，首先对modCount加1，并将删除的元素设置为null，便于垃圾回收器回收，
 *          同时将ArrayList的大小减1。此时size的值为0，modCount的值为1；cursor的值为1，
 *          expectedModCount的值为0。重新回到hasNext()方法，因为size的值不等于cursor，所以判断结果为真。
 *          继续执行while循环，在while循环中调用next()，而next()方法首先会调用checkForComodification()，
 *          因为此时modCount的值为1，而expectedModCount的值为0。所以就会抛出ConcurrentModificationException
 * 关键点：出现ConcurrentModificationException异常的关键在于，调用了ArrayList中的remove()方法导致
 *        modCount和expectedModCount的值不同。
 * 解决：在对ArrayList进行迭代的时候不取调用ArrayList的remove()方法，而是调用iterator中的remove()方法
 *      iterator中的remove()方法的具体实现：
 *      public void remove() {
 *          if (lastRet == -1)
 *          throw new IllegalStateException();
 *          checkForComodification();
 *          try {
 *              AbstractList.this.remove(lastRet);
 *              if (lastRet < cursor)
 *              cursor--;
 *              lastRet = -1;
 *              expectedModCount = modCount;
 *          } catch (IndexOutOfBoundsException e) {
 *              throw new ConcurrentModificationException();
 *          }
 *       }
 *       因为在iterator中remove()方法内多出了expectedModCount = modCount;操作
 * 场景2: 多线程环境下，对Vector、ArrayList在迭代的时候如果同时对其进行修改就会抛出
 *       java.util.ConcurrentModificationException异常。即使使用单线程情况下的解决办法，
 *       仍然会出现ConcurrentModificationException异常。
 * 原因： 虽然ArrayList是非线程安全的，但是即使使用线程安全的Vector，
 *       仍然会出现ConcurrentModificationException异常。原因在于，虽然Vector使用了Synchronized进行同步，
 *       但是由于Vector是继承自AbstractList的，因此通过iterator来访问容器的话，不需要获取锁就可以访问，
 *       由于modCount是AbstractList的成员变量，因此就会造成在一个线程中删除了集合中的元素时，
 *       会修改modCount的值，而导致在其他线程中modCount和expectedModCount值不等。
 *       (在示例代码中，由于iterator是线程私有的，所以expectedModCount也是线程私有的，
 *        所以在其他线程修改expectedModCount时，不可能影响到本地线程的expectedModCount的值)
 * 方法：① 在使用iterator迭代的时候使用synchronized或者Lock进行同步
 *      ② 使用并发容器CopyOnWriteArrayList代替ArrayList和Vector
 * Created by lihongyan on 2015/12/8.
 */
public class ConcurrentModification {

    public void singleThread(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(4);
        Iterator<Integer> its = list.iterator();
        while(its.hasNext()){
            Integer it = its.next();
            if(it==4){
                its.remove();
            }
        }
        System.out.println(list.size());
    }
    public void multiThread(){
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<5;i++){
            list.add(i);
        }
        Thread thread1 = new Thread(){
            public void run() {
                Iterator<Integer> its = list.iterator();
                while (its.hasNext()) {
                    Integer it = its.next();
                    System.out.println(it);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        };
        Thread thread2 = new Thread(){
            public void run(){
                Iterator<Integer> its = list.iterator();
                while(its.hasNext()){
                    Integer it = its.next();
                    if(it==4){
                        its.remove();
                    }
                }
            };
        };
        thread1.start();
        thread2.start();
    }
    public static void main(String[] args){
        ConcurrentModification cm = new ConcurrentModification();
        //cm.singleThread();
        cm.multiThread();
    }
}
