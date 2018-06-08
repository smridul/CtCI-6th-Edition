package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/3/18.
 */
public class NumberOf2s {


    @Test
    public void calculate2s() {


        int n = 249;
        int count = 0;
        int a = n;
        int powerof10 = 1;
        while (a != 0) {
            a = a / 10;
            count++;
        }

        //start with 10^(count-1);
        powerof10 = (int) Math.pow(10, count - 1);

        System.out.println(calculate2(n, powerof10));
    }


    private int calculate2(int n, int powerof10) {

        if (n < 10) {
            return n >= 2 ? 1 : 0;
        }


        int baserow = n / powerof10;
        int rows = baserow * calculate2(powerof10 - 1, powerof10 / 10);
        rows = rows + calculate2(n % powerof10, powerof10 / 10);

        if (baserow > 2) {
            rows += powerof10;
        } else if (baserow == 2) {

            rows += (n % powerof10) + 1;
        }

        return rows;

    }


}
