package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/7/18.
 */
public class Massuse {



    @Test
    public void testit(){

        int [] massages= new int[]{45, 30, 60, 105, 45, 150};
        int max = maxMinutes(massages);

    }
    public static int maxMinutes(int[] massages) {

        int[] weightNumber = new int[massages.length];

        int maxMinutes = 0;
        for (int i = massages.length - 1; i >= 0; i--) {

            int current = massages[i];

            int maxSumWhenCurrentSelected = current;
            for (int j = i + 2; j < massages.length; j++) {

                int sum = current + weightNumber[j];
                maxSumWhenCurrentSelected = Math.max(sum, maxSumWhenCurrentSelected);

            }
            weightNumber[i] = maxSumWhenCurrentSelected;
            maxMinutes = Math.max(maxMinutes, maxSumWhenCurrentSelected);
        }

        return maxMinutes;
    }
}
