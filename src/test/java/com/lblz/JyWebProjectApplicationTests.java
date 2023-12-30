package com.lblz;

import org.junit.jupiter.api.Test;

import java.util.Objects;

//@SpringBootTest
class JyWebProjectApplicationTests {

    public static void main(String[] args) {
        test01(null,null);
    }

    @Test
    void contextLoads() throws InstantiationException, IllegalAccessException {
        System.out.println(10>>1); // 1/2
        System.out.println(5<<1); // 1*2
    }


    /**
     *  @description  有a-z,1-26个28个字母和数字,每八个交叉,剩下的不用交叉
     *  @date 2022/4/8 9:14
     */
    public static void test01(String[] letters, Integer[] nums) {
        if(Objects.isNull(letters)){
            letters = "abcdefghijklmnopqrstuvwxyz".split("");
        }
        if(Objects.isNull(nums)){
            nums = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        }
        StringBuffer stringBuffer = new StringBuffer();
        String remainderNum =",";
        for (int i = 0; i < letters.length; i++){
            stringBuffer.append(letters[i]);
            remainderNum += nums[i].toString();
            if((i+1)%8 == 0){
                stringBuffer.append(remainderNum+",");
                remainderNum = ",";
            }
        }
        stringBuffer.append(remainderNum);
        System.out.println(stringBuffer);
    }
}
