package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by smridul on 6/4/18.
 */
public class BabyNames {


    // WRONG missed the crucial merge step
    @Test
    public void babyNames() {

        ArrayList<NameFreq> names = new ArrayList<>();
        names.add(new NameFreq("john", 15));
        names.add(new NameFreq("jon", 12));

        names.add(new NameFreq("chris", 13));
        names.add(new NameFreq("kris", 4));
        names.add(new NameFreq("christo", 19));


        ArrayList<Synonym> synonyms = new ArrayList<>();
        synonyms.add(new Synonym("jon", "john"));
        synonyms.add(new Synonym("john", "johny"));

        synonyms.add(new Synonym("chris", "kris"));
        synonyms.add(new Synonym("chris", "christo"));


        ArrayList<HashSet<String>> arrayOfSets = createSets(synonyms);


        HashMap<Integer, Integer> correctingNameFreq = new HashMap<>();
        for (NameFreq nameFreq : names) {

            SetInfo setInfo = getTheSetIndex(nameFreq.name, arrayOfSets);

            if (correctingNameFreq.containsKey(setInfo.index)) {
                int count = correctingNameFreq.get(setInfo.index);
                correctingNameFreq.put(setInfo.index, count + nameFreq.freq);
            } else {
                correctingNameFreq.put(setInfo.index, nameFreq.freq);

            }
        }


        for (Integer key : correctingNameFreq.keySet()) {
            System.out.print("Name = " + key);
            System.out.print(" count = " + correctingNameFreq.get(key));
            System.out.println();

        }

    }


    private SetInfo getTheSetIndex(String name, ArrayList<HashSet<String>> arrayOfSets) {

        int i = 0;
        SetInfo setInfo = new SetInfo();
        setInfo.index = -1;
        for (HashSet<String> set : arrayOfSets) {

            if (set.contains(name)) {
                setInfo.name = name;
                setInfo.index = i;
                return setInfo;
            }
            i++;
        }



        return setInfo;
    }

    private ArrayList<HashSet<String>> createSets(ArrayList<Synonym> synonyms) {

        ArrayList<HashSet<String>> arrayOfSets = new ArrayList<>();

        for (Synonym s : synonyms) {

            // check if s.name1 or s.name2 belongs to any set

            HashSet<String> foundSet = new HashSet<>();
            boolean found = false;
            for (HashSet<String> set : arrayOfSets) {
                if (set.contains(s.name1) || set.contains(s.name2)) {
                    foundSet = set;
                    found = true;
                    break;
                }
            }

            foundSet.add(s.name1);
            foundSet.add(s.name2);
            if (found == false) {
                arrayOfSets.add(foundSet);
            }
        }
        return arrayOfSets;
    }

    class NameFreq {
        public String name;
        public int freq;

        NameFreq(String name, int freq) {
            this.name = name;
            this.freq = freq;
        }
    }

    class Synonym {
        public String name1;
        public String name2;

        Synonym(String name1, String name2) {
            this.name1 = name1;
            this.name2 = name2;
        }
    }

    class SetInfo {
        public int index;
        public String name;
    }
}

