package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/6/18.
 */
public class BinodeQuestion {

    @Test
    public void treetolist() {

//        Binode root = new Binode(20);
//        insertintotree(root, 10);
//
//        insertintotree(root, 5);
//        insertintotree(root, 4);
//        insertintotree(root, 6);
//        insertintotree(root, 15);
//        insertintotree(root, 16);
//        insertintotree(root, 25);
//        insertintotree(root, 21);
//        insertintotree(root, 22);
//        insertintotree(root, 30);


        // book data
        Binode root = new Binode(4);
        insertintotree(root, 2);

        insertintotree(root, 1);
        insertintotree(root, 0);
        insertintotree(root, 3);
        insertintotree(root, 5);
        insertintotree(root, 6);



        printtree(root);

        System.out.println();


        Binode list = convert(root);

        Binode first = getFirst(list);
        printlist(first, true);

        System.out.println();
        Binode last = getLast(list);
        printlist(last, false);


    }


    public void insertintotree(Binode root, int data) {


        if (root == null) {
            return;
        }


        Binode current = root;
        Binode prev = current;

        int right = 1;
        int left = 2;
        int came = 0;

        while (current != null) {

            prev = current;

            if (data < current.data) {
                current = current.left;
                came = left;
            } else if (data > current.data) {
                current = current.right;
                came = right;
            }
        }


        Binode node = new Binode(data);
        node.left = null;
        node.right = null;
        if (came == left) {
            prev.left = node;
        }
        if (came == right) {
            prev.right = node;
        }


    }

    private void printtree(Binode root) {

        if (root == null) {
            return;
        }

        printtree(root.left);
        System.out.print(root.data + " ");
        printtree(root.right);
    }

    private void printlist(Binode root, boolean forward) {

        if (root == null) {
            return;
        }

        Binode current = root;
        while (current != null) {
            System.out.print(current.data + " ");

            if (forward) {
                current = current.right;

            } else {
                current = current.left;
            }
        }

    }


    public Binode convert(Binode root) {
        if (root == null) {
            return null;
        }

        if (root.right == null && root.left == null) {
            return root;
        }

        Binode leftList = convert(root.left);
        Binode rightList = convert(root.right);


        Binode lastNodeOfLeftList = getLast(leftList);
        Binode firstNodeOfRightList = getFirst(rightList);

        root.left = lastNodeOfLeftList;
        if (lastNodeOfLeftList != null) {
            lastNodeOfLeftList.right = root;
        }

        root.right = firstNodeOfRightList;
        if (firstNodeOfRightList != null) {
            firstNodeOfRightList.left = root;
        }

        return root;

    }


    public Binode getLast(Binode root) {
        if (root == null) {
            return null;
        }


        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public Binode getFirst(Binode root) {
        if (root == null) {
            return null;
        }


        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

}


class Binode {
    public Binode left, right;

    Binode(int data) {
        this.data = data;
    }

    public int data;
}