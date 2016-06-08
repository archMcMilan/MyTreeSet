import collection.MyTreeSet;

/**
 * Created by Artem on 08.06.16.
 */
public class Main {
    public static void main(String[] args) {
        MyTreeSet<Integer> tree=new MyTreeSet<>();
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(21);
        tree.add(17);
        tree.add(23);
        tree.add(14);
        tree.add(4);
        tree.add(5);
        tree.add(13);
        tree.show(tree.getRoot(),0);
        System.out.println();
        //tree.showSort(tree.getRoot());
        for(Integer i:tree){
            System.out.println(i);
        }
    }
}
