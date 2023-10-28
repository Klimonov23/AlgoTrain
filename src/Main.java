import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        System.out.println(reduce("aabccccaaa"));
    }


    public static boolean canModificated(String s1,String s2){
        //Существуют три вида модифицирующих операций со строками: вставка
        //символа, удаление символа и замена символа. Напишите функцию, которая
        //проверяет, находятся ли две строки на расстоянии одной модификации (или
        //нуля модификаций).
        char[] arr1=s1.toCharArray();
        char[] arr2=s2.toCharArray();
        int p1=0;
        int p2=0;
        int count=0;
        while (p1< arr1.length && p2< arr2.length){
            if (arr1[p1]==arr2[p2]){
                p1++;
                p2++;
            }
            else {
                count++;
                if (arr1.length> arr2.length) p1++;
                if (arr1.length< arr2.length) p2++;
                if (arr1.length== arr2.length) {p1++;p2++;}
            }
        }
        return count <= 1;
    }

    public static String reduce(String s){
        //Реализуйте метод для выполнения простейшего сжатия строк с использованием счетчика повторяющихся символов. Например, строка ааЬсссссааа превращается в а2Ыс5а3. Если •сжатая� строка не становится короче исходной,
        //Вопросы собеседования 83
        //то метод возвращает исходную строку. Предполагается, что строка состоит
        //только из букв верхнего и нижнего регистра (a-z).
        char[] arr=s.toCharArray();
        char example=arr[0];
        int count=1;
        int i=1;
        StringBuilder res=new StringBuilder();
        while (i<arr.length){
            if (arr[i]==example){
                count++;
                i++;
            }
            else{
                res.append(example).append(count);
                example=arr[i];
                count=1;
                i++;
            }
        }
        res.append(example).append(count);

        return (res.length()==s.length())? s : res.toString();
    }


}