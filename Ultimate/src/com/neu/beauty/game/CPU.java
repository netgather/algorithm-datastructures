package com.neu.beauty.game;

/**
 * 问题：如何让CPU占用率曲线听你指挥
 *      写一个程序，让用户来决定Windows任务管理器的CPU占用率，程序越精简越好。
 *      可以实现下面三种情况：
 *      ①CPU的占用率固定在50%
 *      ②CPU的占用率为一条直线，具体占用率由命令行参数决定（参数取值范围1~100）
 *      ③CPU占用率状态是一条正弦曲线
 *      当任务管理器报告CPU使用率为0时，通过任务管理器的“进程”栏可以看到
 *      System Idel process占用了CPU的空闲时间。系统中有那么多进程，它们什么时候能“闲下来”呢？
 *      这些程序或者在等待用户的输入，或者在等待某些事件的发生，或者主动进入休眠状态
 *      Created by lihongyan on 2015/11/2.
 */
public class CPU {
    //原理：在任务管理器的一个刷新周期内，CPU忙（执行应用程序）的时间和刷新周期总时间的比率，
    //     就是CPU的占用率，也就是说，任务管理器中显示的是每个刷新周期内CPU占用率的统计平均值，
    //     因此我们可以写一个程序，让它在任务管理器的刷新期间内一会儿忙，一会儿闲，然后调节忙闲的比率，
    //     就可以控制任务管理器中现实的CPU占用率
    //方法一、简单的解法：要操纵CPU的使用率曲线，就需要是CPU在一段时间内跑busy和idel两个不同的循环，
    //                从而通过不同的事件比例，来调节CPU使用率。
    //       busy循环可以通过执行空循环来实现，idel可以通过sleep()来实现。
    //注意：①尽量减少sleep/awake的频率，以减少操作系统内核调度程序的干扰
    //     ②尽量不要调用system call(比如I/O这些privilege instruction)，因为它会导致很多不可控的内核运行时间
    //缺点：不能适应机器的差异性。一旦换了一个CPU，就需要重新估算n值
    public static void method1(int n){
        for(;;){
            for(int i=0;i<n;i++){
                ;
            }
            try {
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        method1(960000000);
    }
}
