package com.neu.array;

/**
 * ���⣺һ����n��Ԫ�ص����飬��n��Ԫ�ؿ���������Ҳ�����Ǹ����������������Ե�һ������Ԫ�ؿ������һ�������������飬
 *      һ����������ж��������������������������͵����ֵ��
 * Created by lihongyan on 2015/10/24.
 */
public class MaxSubArray {
    //����һ�����������ҳ����е������飬Ȼ�����������ĺͣ�������������ĺ���ȡ���ֵ
    public static int maxSubArray1(int[] array){
        int length = array.length;
        int maxSum = 0;
        int thisSum = 0;
        int i,j,k;
        for(i=0;i<length;i++){
            for(j=i;j<length;j++){
                thisSum = 0;
                for(k=i;k<j;k++){
                    thisSum += array[k];
                }
                if(thisSum>maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }
    //���������ظ������Ѿ������������ Sum[i,j] = Sum[i,j-1] + array[j],ʡȥ����Sum[i,j]��ʱ��
    public static int maxSubArray2(int[] array){
        int length = array.length;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<length;i++){
            int sum = 0;
            for(int j=i;j<length;j++){
                sum += array[j];
                if(sum>maxSum){
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }
    //��̬�滮�����ȿ��Ը�����������һ��Ԫ��array[length-1]�����������Ĺ�ϵ��Ϊ�������������
    //      1)������������array[length-1],����array[length-1]��β
    //      2)array[length-1]�����������������
    //      3)��������鲻����array[length-1]����ô��array[1,...,length-1]��������������ת��Ϊ��array[1,...length-2]�����������
    public static int max(int x,int y){
        return x>y?x:y;
    }
    public static int maxSubArray3(int[] array){
        int length = array.length;
        int[] end = new int[length];
        int[] all = new int[length];
        end[length-1] = array[length-1];
        all[length-1] = array[length-1];
        end[0] = all[0] = array[0];
        for(int i=1;i<length;i++){
            end[i] = max(end[i-1]+array[i],array[i]);
            all[i] = max(end[i],all[i-1]);
        }
        return all[length-1];
    }
    //�Ż��Ķ�̬�滮��
    public static int maxSubArray4(int[] array){
        int length = array.length;
        int nAll = array[0];
        int nEnd = array[0];
        for(int i=1;i<length;i++){
            nEnd = max(nEnd+array[i],array[i]);
            nAll = max(nEnd,nAll);
        }
        return nAll;
    }
    //ȷ������������λ��
    private static int begin = 0;
    private static int end = 0;
    public static int maxSubArray5(int[] array){
        int maxSum = Integer.MIN_VALUE;
        int nSum = 0;
        int nStart = 0;
        for (int i=0;i<array.length;i++){
            if(nSum<0){
                nSum = array[i];
                nStart = i;
            }else{
                nSum += array[i];
            }
            if(nSum>maxSum){
                maxSum = nSum;
                begin = nStart;
                end =i;
            }
        }
        return maxSum;
    }
    public static void main(String[] args){
        int[] array = {1,-2,4,8,-4,7,-1,-5};
        System.out.println(maxSubArray1(array));
        System.out.println(maxSubArray2(array));
        System.out.println(maxSubArray3(array));
        System.out.println(maxSubArray4(array));
        System.out.println(maxSubArray5(array));
        System.out.println(begin+" "+end);

    }
}
