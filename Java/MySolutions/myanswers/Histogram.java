package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/8/18.
 */
public class Histogram {


    @Test
    public void question() {

        int[] input = new int[]{0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0};

        int volume = 0;
        for (int i = 0; i < input.length; i++) {
            int element = input[i];

            int height = calculateHeight(i, input);

            if (height == 0) {
                continue;
            }
            if (element == 0) {
                volume += height * 1;
            } else if (height > element) {

                volume += 1 * (height - element);
            }
        }

        System.out.println("volume is " + volume);

    }


    private int calculateHeight(int index, int[] array) {
        // calculate max height for left part
        //(0, index-1 )

        int maxLeft = 0;
        for (int i = 0; i <= index - 1; i++) {
            if (array[i] > maxLeft) {
                maxLeft = array[i];
            }
        }

        if (maxLeft == 0) {
            return 0;
        }

        // calculate max height for right part
        //(index+1, length-1 )
        int maxRight = 0;
        for (int i = index + 1; i <= array.length - 1; i++) {
            if (array[i] > maxRight) {
                maxRight = array[i];
            }
        }

        // return min of it
        return Math.min(maxLeft, maxRight);

    }
}
