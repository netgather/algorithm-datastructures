package com.neu.beauty.game;

/**
 * 问题：使用A、B分别表示中国象棋中的将、帅。假设棋盘上只有将和帅。A、B二子被限制在己方3x3的格子里运动
 *      A、B分别可以横向或纵向移动，但不能沿对角线移动。A、B不能同时处于同一直线上
 *      请写出一个程序，输出A、B所有合法位置，要求代码中只能使用一个字节存储变量
 * Created by lihongyan on 2015/11/2.
 */
public class ChinaChess {
    //分析与解法
    //流程：遍历A的位置->遍历B的位置->判断A、B的位置组合是否满足要求->如果满足，则输出。
    //     因此，需要存储A、B的位置信息，并且每次循环都要更新。
    //首先，先创建一个逻辑坐标系统，一个可行的方法是用1~9的数字，按照行有限的顺序来表示每个格子的位置，
    //     这样，只需要用取模运算就可以得到当前的列号，从而判断A、B是否互斥。
    //其次，题目要求只用一个变量，我们却要存储A和B两个子的位置信息，可选的数据类型有boolean、byte、int类型。
    //     对于boolean类型，没有办法做任何扩展，因为它只能表示true和false两个值；
    //     对于本题来说，每个子都只需要9个数字就可以表示他们的全部位置信息。一个8位的byte类型能够表达2^8=256个值，
    //     所以使用byte就能够表示A、B的位置信息，因此可以把这个字节的变量分成两部分。
    //     用前面的4bit表示A的位置，用后面的4bit表示B的位置，而4个bit可以表示16个数，因此使用byte满足条件。
    //考虑：如何使用bit级的运算将数据从byte变量的左边和右边分别存入和读取。
    //①将byte(10100101)的右边4个bit(0101)设为n(0011):
    //     首先，清除byte右边的4个bit(0101),同时保证左边的4个bit(1010)（将byte与11110000做与运算）
    //     然后，将上一步得到的结果与n(00000011)做或运算
    //②将byte(10100101)左边的4个bit(1010)设为n(0011):
    //     首先，清除byte左边的4个bit(1010)，同时保持右边的4个bit(0101)（将byte与00001111做与运算）
    //     现在，把n移动到byte数据的左边 （00110000）
    //     然后，对以上两步得到的结果做或运算，从而得到最终的结果。
    //③得到byte数据的右边4个bit或左边的4个bit
    //     清除byte左边的4个bit，同时保持右边的4个bit(将byte与00001111做与运算)
    //     清除byte右边的4个bit，同时保持左边的4个bit(将byte与11110000做与运算)，然后将结果右移4个bit
    //考虑：如何在不声明其他变量约束的前提下创建一个for循环
    //     可以重复利用1个byte的存储单元，把它作为循环计数器并用前面提到的存取和读入技术进行操作
    public static byte getLeft(byte pos){
        return (byte)((pos&240)>>4);
    }
    public static byte getRight(byte pos){
        return (byte)(pos&15);
    }
    public static void chinaChess(){
        for(byte posA=1;posA<=9;posA++){
            for(byte posB=1;posB<=9;posB++){

                    System.out.println("A="+getLeft(posA)+"B="+getRight(posB));


            }
        }
    }
    public static void main(String[] args){
        chinaChess();

    }
}
