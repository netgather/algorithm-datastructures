package com.neu.beauty.struct;

/**
 * 问题：电话号码对应英语单词
 *      电话的号码盘一般可以用于输入英文字母。如用2可以输入A、B、C，用3可以输入D、E、F等。
 *      对于号码5869872，可以依次输出其代表的所有字母组合。如JTMWTPA、JTMWTPA...
 *  ①根据这样的对应关系设计一个程序，尽可能快地从这些字母组合中找到一个有意义的单词来表述一个电话号码。
 *    如：可以用单词“computer”来描述号码26678837。
 *  ②对于一个电话号码，是否可以用一个单词来代表？怎样才是最快的方法？显然，不是所有的电话号码都能够
 *    对应到单词上去。
 * Created by lihongyan on 2015/11/2.
 */
public class TeleNumMapWord {
    //分析与解法：除了0、1之外，其他数字上最少都有3个字符，其中7/9上有4个字符，我们假设0/1输出的是空字符。
    //   首先，将文艺简单化，若电话号码只有一位正数，例如4，那么其代表的“单词”为G、H、I，据此可以画出一颗排列树。
    //   其次，若电话号码升级到两位数，例如2和4。第一步：在选择一个第一位数字所代表的字符的基础上，遍历第二位数字
    //        所代表的字符，直到遍历完第一位数字代表的所有字符。
    //   最后，可以在4所代表的排列树的基础上，进一步画出42所代表的排列树。
    //   总结，通过遍历这棵排列树所有的叶子节点而得到的所有路径的集合，即为42所能代表的所有“单词”的集合
    //考虑：如何遍历得到一个电话号码所能代表的“单词”集合

    //问题①
    //方法一、直接循环法：
    //   ①将各个数字所能代表的字符存储在一个二维数组中，其中假设0、1所代表的为空字符
    //   ②将各个数字所能代表的字符总数记录于另一个数组中
    //   ③用一个数组存储电话号码
    //   ④将数字目前所代表的字符在其所能代表的字符集中的位置用一个数组存储起来
    //   ⑤使用多层for循环打印
    // 问题：假设电话号码只有3位，那么可能会有人很快写出3个for循环来，针对3位的电话号码，此3个for循环可以很好地解决问题，
    //     但是不同地区的电话号码的位数不同，此外若是电话号码升级了，我们都需要修改原地阿妈增加若干个for循环
    public static void teleNumMapWord1(String teleNum){
        if(teleNum==null){
            return;
        }
        int length = teleNum.length();
        String[][] map = {{""},{""},{"A","B","C"},{"D","E","F"},{"G","H","I"},{"J","K","L"},
                          {"M","N","O"},{"P","Q","R","S"},{"T","U","V"},{"W","X","Y","Z"}};//①
        int[] total = {0,0,3,3,3,3,3,4,3,4};//②
        int[] number = new int[length];//③
        for(int i=0;i<length;i++){
            number[i] = (int)teleNum.charAt(i) - '0';
        }
        int[] position = new int[length];//④
        while(true){
            for(int i=0;i<length;i++){
                System.out.print(map[number[i]][position[i]]);
            }
            System.out.print(" ");
            System.out.println();
            int k = length - 1;//k为最后一个元素的索引位置，循环遍历
            while(k>=0){
                if(position[k] < total[number[k]] - 1){
                    position[k]++;
                    break;
                }else{
                    position[k] = 0;
                    k--;
                }
            }
            if(k<0){
                break;
            }
        }
    }
    //问题①
    //方法二、递归的方法：可以从循环算法中那些for循环方法中得到启示--每层的for循环，其实可以看成一个递归函数调用
    private static String[][] array = {{""},{""},{"A","B","C"},{"D","E","F"},{"G","H","I"},{"J","K","L"}, {"M","N","O"},{"P","Q","R","S"},{"T","U","V"},{"W","X","Y","Z"}};
    private static int[] sum = {0,0,3,3,3,3,3,4,3,4};
    public static void teleNumMapWord2(int[] number,int[] position,String teleNum,int index){
        int length = teleNum.length();
        if(index<0){
            for(int i=0;i<length;i++){
                System.out.print(array[number[i]][position[i]]);
            }
            System.out.print(" ");
            System.out.println();
        }else{
            for(position[index]=0;position[index]<sum[number[index]];position[index]++){
                teleNumMapWord2(number,position,teleNum,index-1);
            }
        }
    }
    //将字母映射为数字--循环实现
    public static int[] wordToTeleNum1(String[] word){
        if(word==null){
            return null;
        }
        int length = word.length;
        String[][] map = {{""},{""},{"A","B","C"},{"D","E","F"},{"G","H","I"},{"J","K","L"},
                          {"M","N","O"},{"P","Q","R","S"},{"T","U","V"},{"W","X","Y","Z"}};
        int[] total = {0,0,3,3,3,3,3,4,3,4};
        int temp = 0;
        int[] result = new int[length];
        for(int l=0;l<length;l++){
            for(int m=0;m<map.length;m++){
                for(int n=0;n<total[m];n++){
                    if(word[l].equalsIgnoreCase(map[m][n])){
                        result[temp] = m;
                        temp++;
                    }
                }
            }
        }
        return result;
    }
    private static String[][] wordMap = {{""},{""},{"A","B","C"},{"D","E","F"},{"G","H","I"},{"J","K","L"}, {"M","N","O"},{"P","Q","R","S"},{"T","U","V"},{"W","X","Y","Z"}};
    private static int[] top = {0,0,3,3,3,3,3,4,3,4};
    private static int[] a;
    //将字母映射为数字--递归实现
    public static void wordToTeleNum2(String[] word,int index){
        if(index>=word.length){
            return;
        }else{
            for(int i=0;i<wordMap.length;i++){
                for(int j=0;j<top[i];j++){
                    if(word[index].equalsIgnoreCase(wordMap[i][j])){
                        a[index] = i + 1;
                        wordToTeleNum2(word,index+1);
                    }else{
                        continue;
                    }
                }
            }
        }
    }
    //问题②
    //方法一、利用问题①的解法，把该电话号码所对应的字符全部计算出来，然后去查询字典进行匹配，判断是否有答案

    //方法二、如果查询的字典的次数较多，可直接把字典里面的所有单词都按照这种转换规则转换为数字，并存储到文件中，
    //       使之成为另一本数字字典。然后，通过这个电话号码查表的方式来得到结果。
    public static void main(String[] args){
        //问题①  方法一
        teleNumMapWord1("5869872");
        //问题①  方法二
        String teleNum = "5869872";
        int length = teleNum.length();
        int[] position = new int[length];
        int[] number = new int[length];//③
        for(int i=0;i<length;i++){
            number[i] = (int)teleNum.charAt(i) - '0';
        }
        teleNumMapWord2(number,position,teleNum,length-1);

        //将字母映射为数字--循环实现
        String[] array = {"C","O","M","P","U","T","E","R"};
        int[] result = wordToTeleNum1(array);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]+1);
        }
        //将字母映射为数字--递归实现
        a = new int[array.length];
        wordToTeleNum2(array,0);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }

        //问题②  方法一
        //问题②  方法二
    }
}
