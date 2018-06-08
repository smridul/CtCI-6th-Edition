package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import CtCILibrary.AssortedMethods;

/**
 * Created by smridul on 6/6/18.
 */
public class Respace {


    @Test
    public void correctTheString() {


        HashSet<String> dictionary = AssortedMethods.getWordListAsHashSet();
        String input = "jesslookedjustliketimherbrother";

        //    input = "xyz";
       input = "asoneofthetopkcompaniesintheworldgooglewillsurelyattracttheattentionofcomputergurusthisdoesnothowevermeanthecompanyisforeveryone";

  //      input = "likedig";
       //input = "asone";
        HashMap<String, SpaceString> map=new HashMap<>();

        SpaceString spaceString = correctIt(input, dictionary, map);

        System.out.println(spaceString.unrecognizedCharacters);
        System.out.println(spaceString.value);


    }


    private SpaceString correctIt(String input, HashSet<String> dictionary, HashMap<String, SpaceString> map) {


        if (map.get(input)!=null){
            return map.get(input);
        }
        int unrecognizedCharacters = 0;
        int minunrecognizedCharacters = input.length();

        SpaceString spaceString = new SpaceString();
        spaceString.value = input;
        spaceString.unrecognizedCharacters = minunrecognizedCharacters;

        for (int index = 0; index < input.length(); index++) {
            // first form a string starting with index
            String s = input.substring(index, input.length());

            int indexValidWord = getTheIndexOfValidWord(s, dictionary, 0);

            if (indexValidWord == -1) {
                continue;
            } else {


                // find all valid words starting from this index

                ArrayList<String> validWords = allValidWords(s, dictionary);

                unrecognizedCharacters = index;

                String firstPart = input.substring(0, index);

                if (validWords.isEmpty()) {
                    // we are done
                   minunrecognizedCharacters = unrecognizedCharacters;

                 //   spaceString.value = newString;
                 //   spaceString.unrecognizedCharacters = minunrecognizedCharacters;

                }


                for (String word : validWords) {

                    String remaining = getRemainingWord(s, word);

                    SpaceString remainingSpaceString = correctIt(remaining, dictionary, map);

                    String newString = concate(concate(firstPart, word), remainingSpaceString.value);


                    int totalUR = unrecognizedCharacters + remainingSpaceString.unrecognizedCharacters;
                    if (totalUR < minunrecognizedCharacters) {
                        minunrecognizedCharacters = totalUR;
                        spaceString.value = newString;
                        spaceString.unrecognizedCharacters = minunrecognizedCharacters;
                    }

                }
            }
        }

        map.put(input, spaceString);
        return spaceString;

    }


    // if word cannot be formed then return -1
    private int getTheIndexOfValidWord(String s, HashSet<String> dictionary, int startIndex) {
        for (int i = startIndex; i < s.length(); i++) {

            String formed = s.substring(0, i + 1);
            if (dictionary.contains(formed)) {
                return i;
            }
        }
        return -1;
    }

    private String getRemainingWord(String original, String part) {

        String remaining = original.substring(part.length(), original.length());

        return remaining;


    }

    private String concate(String s1, String s2) {

        if (s1.isEmpty()) {
            return s2;
        } else if (s2.isEmpty()) {
            return s1;
        } else {
            return s1 + " " + s2;
        }

    }

    private ArrayList<String> allValidWords(String s, HashSet<String> dictionary) {

        ArrayList<String> validwords = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i + 1);

            if (dictionary.contains(sub)) {
                validwords.add(sub);
            }
        }
        return validwords;

    }
}

class SpaceString {
    String value;
    int unrecognizedCharacters;
}
