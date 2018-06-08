package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/5/18.
 */
public class KthMultiple {




    // wrong
    @Test
    public void kth() {

        // till what pairs 5 means (3*5*5*5*7) wale numbers
        int n = 6;

        // 1st column starting with 3, 2nd column starting with 5, 3rd column starting with

        //1st row all with 1 length
        // 2nd row all with 2 length

        int[][] array = new int[n][3];


        // 1st row initialization
        array[0][0] = 1;
        array[0][1] = 1;
        array[0][2] = 1;




        for (int row = 1; row < array.length; row++) {
            for (int column = 0; column < array[0].length; column++) {
                int sum = 0;
                for (int i = column; i < array[0].length; i++) {
                    sum += array[row - 1][i];
                }
                array[row][column] = sum;
            }
        }



        printMatrix(array);
    }


    void printMatrix(int array[][]){
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[0].length; column++) {
                System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
    }
}
