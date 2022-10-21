package Foop2.lab3;
import java.util.*;

public class Ex3 <E extends Comparable<E>>{

    
    
    public static void main(String[] args) {

        // Creating object of ArrayList<Integer> 
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<Integer> list5 = new ArrayList<>();

        Comparator<Integer> comp = new Intcomparison();

        for(int i = 0; i<10; i++){
            list.add(i, rand.nextInt(10));
            list3.add(i, rand.nextInt(10));
        }

        System.out.println("Before: " + list.toString());
        list2 = list;
        removeDub(list);
        list2 = removeDubCon(list);

        System.out.println("After getting rid of duplicates destructively " + list);
        System.out.println("After getting rid of duplicates constructively " + list2);

        list4 = sortnatural(list3);
        list5 = list3;
        //list5 = sortcomparator(list3, comp);
        System.out.println("Sort natural method before: " + list3);
        System.out.println("Sort natural method after: " + list4);
        System.out.println("Sort natural method after: " + list5);
    }

    // Method needs to destructively remove elements from a List<E> : 

    public static <E> List<E> removeDub(List<E> list){
        ListIterator<E> iterator = list.listIterator();
        List<E> cache = new ArrayList<>();
        // loop through array and print it:
        while (iterator.hasNext()) {
            E value = iterator.next();
            if(cache.contains(value)){
                iterator.remove();
            } 
            cache.add(value);
                    
        } 
        return list;
    }

    // constructive version of above method:
    public static <E> List<E> removeDubCon(List<E> list) {
        ListIterator<E> iterator = list.listIterator();
        List<E> rlist = cloneType(list);
        while(iterator.hasNext()){
            E value = iterator.next();
            if(rlist.contains(value)){
                continue;
            }
            rlist.add(value);

        
        }
        return rlist;
    } 

    // clone helper method:
    public static <E> List<E> cloneType(List<E> list){

        if(list instanceof ArrayList){
            List<E> rlist = new ArrayList<>();
            return rlist;
        }
        else {
            List<E> rlist = new LinkedList<>();
            return rlist;
        }
    }

    // sort method version 1: natural order version
    public static <E extends Comparable<E>> List<E> sortnatural(List<E> list) {
        List<E> rlist = cloneType(list);
        rlist = removeDubCon(list);
        sortnaturalAug(rlist, 0, rlist.size()-1);
        return rlist;

        //return rlist;
    }

    // recursive method helper:
    public static <E extends Comparable<E>> void sortnaturalAug(List<E> list, Integer lowindex, Integer highindex){

        if(lowindex >= highindex) {
            return;
        }
        E pivot = list.get(highindex);
        Integer leftPointer = lowindex;
        Integer rightPointer = highindex;

        while(leftPointer < rightPointer) {
            // IS the left pointer smaller or equal to pivot:
            while((list.get(leftPointer).compareTo(pivot) <= 0) && leftPointer < rightPointer) {
                leftPointer ++;
            }

            while((list.get(rightPointer).compareTo(pivot) >= 0) && leftPointer < rightPointer) {
                rightPointer --;
            }
            
            swap(list, leftPointer, rightPointer);
        }

        swap(list, leftPointer, highindex);
        sortnaturalAug(list, lowindex, leftPointer - 1);
        sortnaturalAug(list, leftPointer + 1, highindex);
         
    }

    // sort method version 2: comparator version:
    public static <E> List<E> sortcomparator(List<E> list, Comparator<? super E> comp) {
        //ListIterator<E> iterator = list.listIterator();
        List<E> rlist = cloneType(list);
        rlist = removeDubCon(list);
        sortcomparatorAug(rlist, 0, rlist.size()-1, comp);
        return rlist;


    }

    public static <E> void sortcomparatorAug(List<E> list, Integer lowindex, Integer highindex, Comparator<? super E> comp){

        if (lowindex >= highindex){
            return;
        }

        E pivot = list.get(highindex);
        Integer leftPointer = lowindex;
        Integer rightPointer = highindex;

        while (leftPointer < rightPointer){


            while((comp.compare(list.get(leftPointer), pivot) <= 0)  && leftPointer < rightPointer) {
                leftPointer ++;
            }

            while((comp.compare(list.get(rightPointer), pivot) >= 0)  && leftPointer < rightPointer) {
                rightPointer --;
            }
        }
        swap(list, leftPointer, highindex);
        sortcomparatorAug(list, lowindex, leftPointer - 1, comp);
        sortcomparatorAug(list, leftPointer + 1, highindex, comp);
    }

    // swap helper method:
    public static <E> void swap(List<E> list, Integer index1, Integer index2){
        E temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);

    }
}
