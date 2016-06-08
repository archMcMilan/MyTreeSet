package collection;

import java.util.*;

/**
 * Created by Artem on 08.06.16.
 */
public class MyTreeSet<T extends Comparable<T>> implements Set<T> {
    private final Node<T> root = new Node<>(null);
    private int size;

    private class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
        T value;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean add(T t) {
        if (root.value == null) {
            size++;
            root.value = t;
        }
        Node<T> current = root;
        while (current != null) {
            T val = (T) t;
            int cmp = val.compareTo(current.value);
            if (cmp == 0) {
                return false;
            } else if (cmp < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node<>(t);
                    current.left.parent = current;
                    size++;
                    return true;
                }

            } else {//if (cmp > 0)
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new Node<>(t);
                    current.right.parent = current;
                    size++;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(root.value==null){
            return false;
        }
        Node<T> current = root;
        while (current != null) {
            int resOfCompare = current.value.compareTo((T) o);
            if (resOfCompare < 0) {
                current = current.left;
            } else if (resOfCompare > 0) {
                current = current.right;
            } else {//resOfCompare==0
                if (current.left == null && current.right == null) {//if current is leaf
                    if (current.parent.left == current) {//left child deleted
                        current.parent.left = null;
                    } else current.parent.right = null;//right child deleted
                } else if (current.left != null) {//left branch
                    Node<T> maxLeft = current.left;
                    while (maxLeft.right != null) {//find rightest element in left branch
                        maxLeft = maxLeft.right;
                    }
                    current.value = maxLeft.value;//exchange with deleted element
                    if (maxLeft.parent.right == maxLeft) {
                        if (maxLeft.left != null) {//if exchanged element has branch
                            maxLeft.parent.right = maxLeft.left;
                        } else {
                            maxLeft.parent.right = null;
                        }
                    } else {//left child is a leaf
                        maxLeft.parent.left = maxLeft.left;
                    }
                } else {//right branch
                    Node<T> maxRight = current.right;
                    while (maxRight.left != null) {//find leftest element in right branch
                        maxRight = maxRight.left;
                    }
                    current.value = maxRight.value;
                    if (maxRight.parent.left == maxRight) {
                        if (maxRight.right != null) {
                            maxRight.parent.left = maxRight.right;
                        } else {
                            maxRight.parent.left = null;
                        }
                    } else {
                        maxRight.parent.right = maxRight.right;
                    }
                }
                size--;
                return true;
            }
        }
        return false;
    }



    @Override
    public boolean contains(Object o) {
        Node<T> current = root;
        while (current != null) {
            int cmp = ((T)o).compareTo(current.value);
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                current = current.left;
            }
            if (cmp > 0) {
                current = current.right;
            }
        }
        return false;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void show(Node root, int i){// output non sorted list with layer indexes
        if(root!=null){
            System.out.println(i+" "+root.value+" ");
            i++;
            if(root.left!=null){
                show(root.left,i);
            }
            if(root.right!=null){
                show(root.right,i);
            }
        }

    }

    public void showSort(Node root){//output sorted list
        if(root.left!=null){
            showSort(root.left);
        }else{
            System.out.println(root.value);
            if(root.right!=null){
                showSort(root.right);
            }
            return;
        }
        System.out.println(root.value);
        if(root.right!=null){
            showSort(root.right);
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index=0;
            LinkedList<Integer> layerReturn=new LinkedList<>();
            int layerCount=0;
            Node curNode=root;

            @Override
            public boolean hasNext() {
                if(size>index){
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (curNode.left != null && curNode.right != null) {
                    index++;
                    layerReturn.add(layerCount);
                    layerCount++;
                    Node returned=curNode;
                    curNode = curNode.left;
                    return (T) returned.value;
                } else if (curNode.left != null && curNode.right == null) {
                    index++;
                    layerCount++;
                    Node returned=curNode;
                    curNode = curNode.left;
                    return (T) returned.value;
                } else if (curNode.left == null && curNode.right != null) {
                    index++;
                    layerCount++;
                    Node returned=curNode;
                    curNode = curNode.right;
                    return (T) returned.value;
                } else if (curNode.left == null && curNode.right == null) {
                    Node returned=curNode;
                    if(layerReturn.size()>0){
                        while (layerCount > layerReturn.getLast()) {
                            layerCount--;
                            curNode = curNode.parent;
                        }
                        layerReturn.pollLast();
                        curNode = curNode.right;
                    }
                    index++;
                    layerCount++;
                    return (T) returned.value;

                }
                return null;
            }
        };
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }


}
