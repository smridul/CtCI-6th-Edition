package myanswers;


import org.junit.Test;

import java.util.HashMap;

public class ShortestSupersequence {


    @Test
    public void getShortestSupersequence() {



        int[] shorterarray = new int[]{1, 5, 9};
        int[] longerarray = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};


        HashMap<Integer, Integer> counts = new HashMap<>();

        //initialize hashmap
        for (int i = 0; i < shorterarray.length; i++) {
            counts.put(shorterarray[i], 0);
        }


        int findCount = shorterarray.length;
        if (findCount == 0) {
            return;
        }

        int leftindex = 0;
        int rightindex = 0;
        int minLeftMindex=0;
        int minRightindex=0;
        for (int i = 0; i < longerarray.length; i++) {


            int element = longerarray[i];

            if (counts.get(element) != null) {
                // this element belongs to shorter element

                if (findCount == shorterarray.length) {
                    // this is the first element to be found
                    leftindex = i;
                }


                // get the count of element
                int countOfElement = counts.get(element);

                // we got it first time
                if (countOfElement == 0) {
                    findCount--;
                }
                counts.put(element, ++countOfElement);
            }

            if (findCount == 0) {
                rightindex = i;
                break;
            }
        }


        if (findCount != 0) {
            // we found no subarray
            return;
        }

        // at this point we have found a subarray
        int minLengthOfSubarray = rightindex-leftindex+1;


        for (; rightindex < longerarray.length;) {

            int leftMostMatchingElement = longerarray[leftindex];


            //first remove this element
            int count = counts.get(leftMostMatchingElement);
            counts.put(leftMostMatchingElement, --count);


            // we are going to remove this element from subarray
            // so shift the right index only if the count if this element is 0
            // because if it is not zero ==> it is already there in subarray as a repeated element


            if (counts.get(leftMostMatchingElement) == 0) {

                while (longerarray[rightindex] != leftMostMatchingElement && rightindex < longerarray.length-1) {
                    rightindex++;

                    int element = longerarray[rightindex];
                    if(counts.get(element)!=null){
                         count = counts.get(element);
                        counts.put(element, ++count);
                    }

                }

            }

            if(rightindex >= longerarray.length-1){
                // we have hit the end of array
                break;
            }

            // we have found the removed left element so shift the left index now
            // shift left index till we get the a matching element
            leftindex++;
            while (counts.get(longerarray[leftindex]) == null) {
                leftindex++;
            }

           // so our subarray is now [leftindex, rightindex]

            int lengthOfSubArray = rightindex - leftindex+1;
            if(lengthOfSubArray < minLengthOfSubarray){
                minLeftMindex = leftindex;
                minRightindex = rightindex;
                minLengthOfSubarray = lengthOfSubArray;
            }
        }


        System.out.println("left index = " + minLeftMindex + ", right index = " +minRightindex);
        System.out.println("length of subaraay is "+ minLengthOfSubarray);

    }
}


