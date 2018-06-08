package myanswers;

import org.junit.Test;

import java.util.HashMap;

import CtCILibrary.AssortedMethods;

/**
 * Created by smridul on 6/4/18.
 */
public class ShortestDistanceTwoWords {


    @Test
    public void findShortestDistance() {


        String array[] = new String[]{"cat", "a", "3", "6", "cat", "0", "cat", "5", "3", "dog", "3", "0", "9", "dog",
                "0", "4", "cat"};


        array = AssortedMethods.getLongTextBlobAsStringList();



        HashMap<String, Integer> map = new HashMap<>();

        String word1 = "cat";
        String word2 = "dog";

        // book example
         word1 = "path";
         word2 = "their";
        int currentDistance = 0;
        // to search will be 0 or 1
        int findNext = 0;
        int found = -1;
        int pos = 0;
        int shortestDis = Integer.MAX_VALUE;
        String[] toSearch = new String[]{word1, word2};
        for (int i = 0; i < array.length; i++) {

            //  if(array[i].equals(toSearch[0]) || array[i].equals(toSearch[1])){
            if ((found = foundWordsoneOrTwo(array[i], toSearch)) != -1) {

                if (findNext == found) {
                    // update if we have already found the complemented elment before
                    if (map.containsKey(toSearch[1 - found])) {
                        currentDistance = i - pos;
                        if (currentDistance < shortestDis) {
                            shortestDis = currentDistance;
                        }
                    }
                    findNext = 1 - found;
                    pos = i;

                    map.put(array[i], 1);
                } else {
                    findNext = 1 - found;
                    pos = i;
                    map.put(array[i], 1);
                }
            }

        }


        System.out.println("Shortest distance is " + shortestDis);

    }


    int foundWordsoneOrTwo(String word, String[] arr) {
        if (arr[0].equalsIgnoreCase(word)) {
            return 0;
        } else if (arr[1].equalsIgnoreCase(word)) {
            return 1;
        }
        return -1;
    }
}
