import java.util.*;
import java.util.Stack;

public class Array {

    public static boolean canJump(int[] nums) {
        //Вам выдается целочисленный массив nums. Изначально вы располагаетесь по первому индексу массива, и каждый элемент в массиве представляет вашу максимальную длину перехода в этой позиции.
        //Верните true, если вы можете достичь последнего индекса, или false в противном случае.

        //[2,3,1,1,4]

        int sum=0;
        int i=0;
    return true;
    }


    public static void main(String[] args) {//Слияние отрезков
        int[][] arr={{1,3},{100,200},{2,4}};
//        ArrayList<String> strings=new ArrayList<>();
//        strings.add("eat");
//        strings.add("tea");
//        strings.add("tan");
//        strings.add("ate");
//        strings.add( "nat");
//        strings.add("bat");
        System.out.println(Arrays.deepToString(getMergeCut(arr)));
    }

    //Дан список интов, повторяющихся элементов в списке нет.
    // Нужно преобразовать это множество в строку, сворачивая соседние по числовому ряду числа в диапазоны.
    public static String rollUp(int[] nums){
        Arrays.sort(nums);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(nums[0]);
        int iss=nums[0];
        for (int i=1;i<nums.length;i++){
            if (nums[i]-nums[i-1]==1){
                iss++;
            }
            else {

                stringBuilder.append("-").append(iss).append(",");
                iss=nums[i];
                stringBuilder.append(iss);
            }
        }
        if (stringBuilder.charAt(stringBuilder.length()-1)==',') stringBuilder.append(iss);
        else
        stringBuilder.append("-").append(iss);
        return stringBuilder.toString();
    }
    //// Дан не пустой массив из нулей и единиц.
    // Нужно определить, какой максимальный по длине подинтервал единиц можно получить, удалив ровно один элемент массива.
    public static int getMaxLength(int[] nums){
        int maxLen=0;
        int lastZeroIndex=0;
        int len=0;
        boolean isZeroMissed=false;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                if (isZeroMissed) {
                    if (len > maxLen) maxLen = len;
                    i = lastZeroIndex;
                    len = 0;
                    isZeroMissed = false;
                }else {
                    lastZeroIndex = i;
                    isZeroMissed = true;
                }
            }
            else len++;
        }
        if (len > maxLen) maxLen = len;

        if (maxLen==nums.length) maxLen-=1;
        return maxLen;
    }

    public static List<List<String>> getGroupByWord(List<String> strings){
        //Сгруппировать слова по "общим буквам".
        Map<String,List<String>> map=new HashMap<>();
        for (String s : strings){
            char[] tmp=s.toCharArray();
            Arrays.sort(tmp);
            String key=new String(tmp);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(s);
        }
        List<List<String>> res=new ArrayList<>();
        for (String s : map.keySet()){
            char[] tmp=s.toCharArray();
            Arrays.sort(tmp);
            String key=new String(tmp);
            res.add(map.get(key));
        }
        return res;
    }

    public static int[][] getMergeCut(int[][] nums){
        Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
        Stack<int[]> stack=new Stack<>();
        var curr=nums[0];
        for (int i=1;i<nums.length;i++){
            int[] next=nums[i];
            if (curr[1]>=next[0]) curr=new int[]{curr[0],Math.max(curr[1],next[1])};
            else {
                stack.push(curr);
                curr=next;
            }
        }
        stack.push(curr);


        var ret = new int[stack.size()][];
        for(int i = ret.length-1; i>=0; i--)
            ret[i] = stack.pop();
        return ret;
    }
}
