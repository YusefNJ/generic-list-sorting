package Foop2.lab3;
import java.util.Comparator;

public class Intcomparison implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        // TODO Auto-generated method stub
        if (o1 > o2) {
            return -1;
        }
        else if (o1 == o2){
            return 0;
        }

        else{
            return 1;
        }
      
    }
    

}
