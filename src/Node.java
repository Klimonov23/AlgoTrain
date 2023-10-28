import java.util.HashSet;
import java.util.Set;

public class Node {
    Node next = null;
    int data;


    public Node(int n) {
        data = n;
    }

    public void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public static void main(String[] args) {
        Node n1=new Node(5);
        Node n2=new Node(2);
        Node n3=new Node(4);
        Node n4=new Node(2);
        Node n5=new Node(5);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
//        Node n1 = new Node(7);
//        Node n2 = new Node(1);
//        //Node n3 = new Node(6);
//        n1.next = n2;
//       // n2.next = n3;
//        Node p1 = new Node(5);
//        Node p2 = new Node(9);
//        Node p3 = new Node(4);
//        p1.next = p2;
//        p2.next = p3;
       Node ptr = n1;
        while (ptr != null) {

            System.out.print(ptr.data);
            if (ptr.next != null) System.out.print("->");
            ptr = ptr.next;
        }
        System.out.println(isPalindrome(n1));
    }

    public static void removeDuplicates(Node list) {
        //Напишите код для удаления дубликатов из несортированного связного списка
        Set<Integer> set = new HashSet<>();
        Node ptr = list;
        Node prev = null;
        while (ptr != null) {
            if (set.contains(ptr.data)) {
                prev.next = ptr.next;
                ptr = ptr.next;
            } else {
                set.add(ptr.data);
                prev = ptr;
                ptr = ptr.next;
            }
        }
    }

    public static Node getKLast(Node list, int k) {
        //Реализуйте алгоритм для нахождения в односвязном списке k-го элемента
        Node fast = list;
        Node slow = list;
        int i = 0;
        while (i < k) {
            fast = fast.next;
            i++;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void removeNode(Node n) {
        //Реализуйте алгоритм, удаляющий узел из середины односвязного списка (то
        //есть узла, не являющегося ни начальным, ни конечным - не обязательно находящегося точно в середине). Доступ предоставляется только к этому узлу.
        int tmp = n.data;
        n.data = n.next.data;
        n.next.data = tmp;
        n.next = n.next.next;
    }

    public static Node doPart(Node n, int k) {
        //Напишите код для разбиения связного списка вокруг значения х, так чтобы
        //все узлы, меньшие х, предшествовали узлам, большим или равным х. Если х
        //содержится в списке, то значения х должны следовать строго после элементов, меньших х (см. далее). Элемент разбивки х может находиться где угодно
        //в правой части»; он не обязан располагаться между левой и правой частью.
        Node before = new Node(-10);
        Node after = new Node(-10);
        Node ptr;
        for (ptr = n; ptr != null; ptr = ptr.next) {
            if (ptr.data < k) {
                before.appendToTail(ptr.data);
            } else after.appendToTail(ptr.data);
        }
        Node pre = before.next;
        while (pre.next != null) {
            pre = pre.next;
        }
        pre.next = after.next;
        return before.next;
    }

    public static Node sum(Node n1, Node n2) {
        //Два числа хранятся в виде связных списков, в которых каждый узел представляет один разряд. Все цифры хранятся в обратном порядке, при этом
        //младший разряд (единицы) хранится в начале списка. Напишите функцию,
        //которая суммирует два числа и возвращает результат в виде связного списка.
        Node res = new Node(-11111);
        int del = 0;
        Node ptr1 = n1;
        Node ptr2 = n2;
        while (ptr1 != null && ptr2 != null) {
            del += ptr1.data + ptr2.data;
            res.appendToTail(del % 10);
            del = del / 10;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        if (ptr1 != null){
            del+= ptr1.data;
            res.appendToTail(del % 10);
            del=del/10;
            ptr1=ptr1.next;
        }
        if (ptr2 != null){
            del+= ptr2.data;
            res.appendToTail(del % 10);
            del=del/10;
            ptr2=ptr2.next;
        }
        if (del != 0) res.appendToTail(del);
        return res.next;
    }

    public static boolean isPalindrome(Node list){
        //Реализуйте функцию, проверяющую, является ли связный список палиндромом
        Node head=null;
        for (Node ptr=list;ptr!=null;ptr=ptr.next){
            Node tmp=new Node(ptr.data);
            tmp.next=head;
            head=tmp;
        }
        Node ptr1=list;
        Node ptr2=head;
        while (ptr1!=null && ptr2!=null){
            if (ptr1.data!= ptr2.data) return false;
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }
        return ptr1 == null && ptr2 == null;
    }


    public static Node getHead(Node list){
        //Для кольцевого связного списка реализуйте алгоритм, возвращающий начальный узел пе
        Node fast=list;
        Node slow=list;
        while (fast!=null &&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast) break;
        }
        if (fast==null ||fast.next==null) return null;

        slow=list;
        while (slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return fast;
    }

    public static Node getObject(Node list,Node list2){
        //Проверьте, пересекаются ли два заданных (одно-)связных списка. Верните
        //узел пересечения. Учтите, что пересечение определяется ссылкой, а не значением. Иначе говоря, если k-й узел первого связного списка точно совпадает
        //(по ссылке) сj-м узлом второго связного списка, то списки считаются пересекающимися
        int len1 = 0;
        int len2 = 0;
        for (Node ptr=list;ptr!=null;ptr=ptr.next){
            len1++;
        }
        for (Node ptr=list2;ptr!=null;ptr=ptr.next){
            len2++;
        }
        Node ptr=len1>len2 ? list : list2;
        int i=0;
        while (i<Math.abs(len1-len2)){
            i++;
            ptr=ptr.next;
        }
        Node ptr1=len1>len2 ? list2 : list;
        while (ptr1!=null && ptr!=null){
            if (ptr1==ptr) return ptr;
            ptr=ptr.next;
            ptr1=ptr1.next;
        }
        return null;
    }
}
