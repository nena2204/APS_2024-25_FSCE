import java.util.Scanner;
//За дадена двојно поврзана листа од N цели броеви, 
//треба да се најде бројот на елементи такви што просекот на елементите 
//од пред него во листата е поголем од просекот на елементи после него во листата.

//Влез: Првиот број од влезот е бројот на елементи во листата N, 
//а потоа во следниот ред се дадени самите елементи одделени со празно место.

//Излез: Бројот на елементи што го задоволуваат условот.
//EXAMPLE:
/*
INPUT:
5
1 2 3 4 5
OUTPUT:
0
INPUT:
4
5 4 3 2
OUTPUT:
2
*/
class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
  class DLL<E> {
     private DLLNode<E> first, last;

     public DLL() {
         // Construct an empty SLL
         this.first = null;
         this.last = null;
     }

     public void insertFirst(E o) {
         DLLNode<E> ins = new DLLNode<E>(o, null, first);
         if (first == null)
             last = ins;
         else
             first.pred = ins;
         first = ins;
     }

     public void insertLast(E o) {
         if (first == null)
             insertFirst(o);
         else {
             DLLNode<E> ins = new DLLNode<E>(o, last, null);
             last.succ = ins;
             last = ins;
         }
     }

     public void insertAfter(E o, DLLNode<E> after) {
         if (after == last) {
             insertLast(o);
             return;
         }
         DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
         after.succ.pred = ins;
         after.succ = ins;
     }

     public void insertBefore(E o, DLLNode<E> before) {
         if (before == first) {
             insertFirst(o);
             return;
         }
         DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
         before.pred.succ = ins;
         before.pred = ins;
     }

     public E deleteFirst() {
         if (first != null) {
             DLLNode<E> tmp = first;
             first = first.succ;
             if (first != null) first.pred = null;
             if (first == null)
                 last = null;
             return tmp.element;
         } else
             return null;
     }

     public E deleteLast() {
         if (first != null) {
             if (first.succ == null)
                 return deleteFirst();
             else {
                 DLLNode<E> tmp = last;
                 last = last.pred;
                 last.succ = null;
                 return tmp.element;
             }
         } else
             return null;
     }

     public E delete(DLLNode<E> node) {
         if (node == first) {
             return deleteFirst();
         }
         if (node == last) {
             return deleteLast();
         }
         node.pred.succ = node.succ;
         node.succ.pred = node.pred;
         return node.element;

     }

     public DLLNode<E> find(E o) {
         if (first != null) {
             DLLNode<E> tmp = first;
             while (!tmp.element.equals(o) && tmp.succ != null)
                 tmp = tmp.succ;
             if (tmp.element.equals(o)) {
                 return tmp;
             } else {
                 System.out.println("Elementot ne postoi vo listata");
             }
         } else {
             System.out.println("Listata e prazna");
         }
         return null;
     }

     public void deleteList() {
         first = null;
         last = null;
     }

     public int getSize() {
         int listSize = 0;
         DLLNode<E> tmp = first;
         while(tmp != null) {
             listSize++;
             tmp = tmp.succ;
         }
         return listSize;
     }

     @Override
     public String toString() {
         String ret = new String();
         if (first != null) {
             DLLNode<E> tmp = first;
             ret += tmp.toString();
             while (tmp.succ != null) {
                 tmp = tmp.succ;
                 ret += "<->" + tmp.toString();
             }
         } else
             ret = "Prazna lista!!!";
         return ret;
     }

     public String toStringR() {
         String ret = new String();
         if (last != null) {
             DLLNode<E> tmp = last;
             ret += tmp.toString();
             while (tmp.pred != null) {
                 tmp = tmp.pred;
                 ret += "<->" + tmp.toString();
             }
         } else
             ret = "Prazna lista!!!";
         return ret;
     }

     public DLLNode<E> getFirst() {
         return first;
     }

     public DLLNode<E> getLast() {

         return last;
     }

     public void mirror() {

         DLLNode<E> tmp = null;
         DLLNode<E> current = first;
         last = first;
         while(current!=null) {
             tmp = current.pred;
             current.pred = current.succ;
             current.succ = tmp;
             current = current.pred;
         }

         if(tmp!=null && tmp.pred!=null) {
             first=tmp.pred;
         }
     }
 }

//За дадена двојно поврзана листа од N цели броеви,
//треба да се најде бројот на елементи такви што просекот
//на елементите од пред него во листата е поголем
//од просекот на елементи после него во листата.
// PRED > POSLE
//Влез: Првиот број од влезот е бројот на елементи во листата N,
//а потоа во следниот ред се дадени самите елементи одделени
//со празно место.
//Излез: Бројот на елементи што го задоволуваат условот.
public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();//kolku broevi
        DLL<Integer> list=new DLL<>();
        for (int i=0;i<n;i++){
            list.insertLast(sc.nextInt());
        }
        //vnesena lista
        int counter=0;
        //1 2 3 4 5
        //how many elements
        DLLNode<Integer> tmp=list.getFirst().succ;
        while (tmp!=null){
            int left=0;
            int sumleft=0;
            DLLNode<Integer> before=tmp.pred;
            while (before!=null){
                left++;
                sumleft+=before.element;
                before=before.pred;
            }
            double avleft=(double)sumleft/left;

            int right=0;
            int sumright=0;
            DLLNode<Integer> after=tmp.succ;
            while (after!=null){
                right++;
                sumright+=after.element;
                after=after.succ;
            }
            double avright=(double)sumright/right;
            if (avleft>avright){
                counter++;
            }
            tmp=tmp.succ;
        }
        System.out.println(counter);
    }
}
