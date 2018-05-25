package myanswers;

import org.junit.Test;

import java.util.*;

public class MyAnswers {

    public int calls = 0;

    @Test
    public void graph4_1() throws Exception {

        Graph g = new Graph();
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");


        n1.children = new Node[]{n2, n3};
        n2.children = new Node[]{null};
        n3.children = new Node[]{n4};
        n4.children = new Node[]{null};

        n5.children = new Node[]{n6};
        n6.children = new Node[]{n7};
        n7.children = new Node[]{null};

        //given is node n1 and n4;

        System.out.println(search(n1, n7));

    }


    @Test
    public void testPerms() {
        String s = "abcde";
        List<String> perms = perms2(s);

        for (String word : perms) {
            System.out.println(word);
        }
        System.out.println("total is " + perms.size());
    }


    public List<String> perms2(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 1) {
            return Arrays.asList(str);
        }

        ArrayList<String> permutations = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String s = getMeWordWithout(str, i);
            List<String> substrings = perms2(s);
            for (String word : substrings) {
                String perm = c + word;
                permutations.add(perm);
            }
        }
        return permutations;
    }

    String getMeWordWithout(String s, int i) {
        String first = s.substring(0, i);
        String last = s.substring(i + 1, s.length());
        return first + last;
    }

    boolean search(Node root, Node b) {
        if (root == null) {
            return false;
        }
        if (root == b) {
            return true;
        }
        root.visited = true;


        for (Node n : root.children) {
            boolean found = false;
            if (n != null && n.visited == false) {
                found = search(n, b);
            }
            if (found) return true;
        }
        return false;
    }


    @Test
    public void testParenthesis() {


        ArrayList<String> result = new ArrayList<>();
        getAllParen(4, 4, "", result);

        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("Total is " + result.size());
    }

    private void getAllParen(int left, int right, String s, ArrayList<String> result) {

        if (left > right) {
            return;
        }

        if (left != 0) {
            s = s + "(";
            getAllParen(left - 1, right, s, result);
        }
        if (right != 0) {
            s = s + ")";
            getAllParen(left, right - 1, s, result);
        }

        if (left == 0 && right == 0) {
            result.add(s);
        }
    }


    @Test
    public void quest8_11() {
        //Wrongly understood question and implemented this
        System.out.print(getWays(10));

        System.out.println();

        Set<ArrayList<Integer>> completeSet = new HashSet<>();
        getSetOfWays(6, new ArrayList<Integer>(), completeSet);

        for (ArrayList<Integer> list : completeSet) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.print("Total ways: " + completeSet.size());
    }

    private int getWays(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        else {
            int ways = getWays(n - 1) + getWays(n - 5) + getWays(n - 10) + getWays(n - 25);
            return ways;
        }
    }

    private boolean getSetOfWays(int n, ArrayList<Integer> results, Set<ArrayList<Integer>> completeSet) {
        if (n < 0) return false;
        if (n == 0) {
            return true;
        } else {

            results.add(1);
            if (getSetOfWays(n - 1, results, completeSet)) {
                completeSet.add(new ArrayList<>(results));
            }
            results.remove(results.size() - 1);

            results.add(5);
            if (getSetOfWays(n - 5, results, completeSet)) {
                completeSet.add(new ArrayList<>(results));
            }
            ;
            results.remove(results.size() - 1);

            results.add(10);
            if (getSetOfWays(n - 10, results, completeSet)) {
                completeSet.add(new ArrayList<>(results));
            }
            ;
            results.remove(results.size() - 1);

            results.add(25);
            if (getSetOfWays(n - 25, results, completeSet)) {
                completeSet.add(new ArrayList<>(results));
            }
            ;
            results.remove(results.size() - 1);

        }
        return false;
    }


    @Test
    public void quest8_11_correct() {
        int n = 11;
        int[] denom = new int[]{25, 10, 5, 1};
        int[][] map = new int[n + 1][4];
        System.out.println(getWaysToGet(n, denom, 0, map) + " calls made are " + calls);
        calls = 0;
        map = new int[n + 1][4];

        System.out.println(getWaysToGetBookAnswer(n, denom, 0, map) + " calls made are " + calls);


        denom = new int[]{25, 10, 5, 2};
        map = new int[n + 1][4];
        System.out.println(getWaysToGet(n, denom, 0, map) + " calls made are " + calls);
        calls = 0;
        map = new int[n + 1][4];

        System.out.println(getWaysToGetBookAnswer(n, denom, 0, map) + " calls made are " + calls);


        // if last den is 1 then choose book answer it avoid many calls
        // if last index is not 1, then choose my answer

    }

    int getWaysToGet(int amount, int[] denom, int index, int[][] map) {
        calls++;

        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            // will never execute; better remove it
            return 0;
        }
        if (index == denom.length) {
            return 0;
        }
        if (map[amount][index] > 0) {
            return map[amount][index];
        }

        int ways = 0;

        for (int i = 0; i * denom[index] <= amount; i++) {
            int remaining = amount - i * denom[index];
            ways = ways + getWaysToGet(remaining, denom, index + 1, map);
        }
        map[amount][index] = ways;
        return ways;
    }

    int getWaysToGetBookAnswer(int amount, int[] denom, int index, int[][] map) {
        calls++;
        if (map[amount][index] > 0) {
            return map[amount][index];
        }
        if (index == denom.length - 1) {
            return 1;
        }

        int ways = 0;

        for (int i = 0; i * denom[index] <= amount; i++) {
            int remaining = amount - i * denom[index];
            ways = ways + getWaysToGetBookAnswer(remaining, denom, index + 1, map);
        }
        map[amount][index] = ways;
        return ways;
    }


    @Test
    public void eightQueen() {

        ArrayList<Integer[]> results = new ArrayList<>();
        Integer[] previousPositions = new Integer[]{-1, -1, -1, -1, -1, -1, -1, -1};
        getPathsEightQueen(0, previousPositions, results);
        for (Integer[] s : results) {

            for (int i : s) {
                System.out.print(i);
            }
            System.out.println();
        }
        System.out.println("Total size is " + results.size());
    }

    @Test
    public void eightQueenBook() {

        ArrayList<Integer[]> results = new ArrayList<>();
        Integer[] previousPositions = new Integer[]{-1, -1, -1, -1, -1, -1, -1, -1};
        getPathsEightQueenBook(0, previousPositions, results);
        for (Integer[] s : results) {
            for (int i : s) {
                System.out.print(i);
            }
            System.out.println();
        }
        System.out.println("Total size is " + results.size());

    }


    private void getPathsEightQueenBook(int row, Integer[] columns, ArrayList<Integer[]> results) {

        if (row == 8) {
            results.add(columns.clone());
            return;
        }
        for (int pos = 0; pos < 8; pos++) {
            if (canSitBook(columns, row, pos)) {
                columns[row] = pos;
                getPathsEightQueenBook(row + 1, columns, results);
            }
        }

    }

    private boolean canSitBook(Integer[] columns, int row1, int columns1) {

        for (int row2 = 0; row2 < row1; row2++) {
            int column2 = columns[row2];

            if (columns1 == column2) {
                return false;
            }
            int cdistance = Math.abs(column2 - columns1);
            int rdistance = row1 - row2;
            if (cdistance == rdistance) {
                return false;
            }
        }
        return true;
    }

    private void getPathsEightQueen(int row, Integer[] previousPositions, ArrayList<Integer[]> results) {

        if (row == 8) {
            results.add(Arrays.copyOf(previousPositions, 8));
            return;
        }
        for (int pos = 0; pos < 8; pos++) {
            if (!canSit(pos, previousPositions, row - 1)) {
                continue;
            }
            previousPositions[row] = pos;

            getPathsEightQueen(row + 1, previousPositions, results);
        }

    }

    private boolean canSit(int pos, Integer[] previousPositions, int filledRows) {

        int rowNumberTosit = filledRows + 1;
        for (int i = 0; i <= filledRows; i++) {
            int blockedPostion1 = previousPositions[i] - (rowNumberTosit - i);
            int blockedPostion2 = previousPositions[i] + (rowNumberTosit - i);
            //discard negatives or >7
            if (pos == blockedPostion1 || pos == blockedPostion2 || pos == previousPositions[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void questionStackOfBoxes() {


        Box b1 = new Box(4, 2, 4);
        Box b2 = new Box(2, 1, 1);
        Box b3 = new Box(5, 3, 4);
        Box b4 = new Box(6, 3, 8);
        ArrayList<Box> boxes = new ArrayList<>();
        boxes.add(b1);
        boxes.add(b2);
        boxes.add(b3);
        boxes.add(b4);

        Collections.sort(boxes, new BoxComparator());
        Integer[] weights = new Integer[boxes.size()];
        buildWeightNumber(boxes, weights);


        System.out.print(Arrays.toString(weights));
    }

    private void buildWeightNumber(ArrayList<Box> boxes, Integer[] weights) {

        for (int i = boxes.size() - 1; i >= 0; i--) {
            Box currentbox = boxes.get(i);
            int max = currentbox.height;
            for (int j = i + 1; j < boxes.size(); j++) {
                if (boxes.get(j).canBeAbove(currentbox)) {
                    int weight = currentbox.height + weights[j];
                    max = Math.max(weight, max);
                }
            }
            weights[i] = max;
        }
    }

    @Test
    public void waysToGetExpression() {

        String expression = "1^0|0|1";
        expression = "0&0&0&1^1|0";
        Map<String , Integer> results = new HashMap<>();
        System.out.print(numberofWaysToGetExpression(expression, true, results));
        //     System.out.print(" calls "+ calls);
        //   System.out.println(" results size "+ results.size());
        for(String s: results.keySet()){
            //          System.out.println(s + " " + results.get(s)) ;
        }



    }

    private int numberofWaysToGetExpression(String expression, boolean result, Map<String , Integer> results) {

        if(results.get(expression+"->"+result)!=null){
            return results.get(expression+"->"+result);
        }

        if (expression.length() == 1) {
            if (expression.equals("1") && result) {
                return 1;
            } else if (expression.equals("0") && !result) {
                return 1;
            }
            return 0;
        }

        calls++;


        int ways = 0;
        for (int i = 1; i < getMeOperands(expression); i++) {

            String s1 = expression.substring(0, i + (i - 1));
            String s2 = expression.substring(i + i, expression.length());
            char operator = expression.charAt(i + (i - 1));

            if (operator == '|') {
                if (result == true) {
                    ways += numberofWaysToGetExpression(s1, true, results) * numberofWaysToGetExpression(s2, false, results);
                    ways += numberofWaysToGetExpression(s1, false, results) * numberofWaysToGetExpression(s2, true, results);
                    ways += numberofWaysToGetExpression(s1, true, results) * numberofWaysToGetExpression(s2, true, results);
                } else {
                    ways += numberofWaysToGetExpression(s1, false, results) * numberofWaysToGetExpression(s2, false, results);
                }
            }
            if (operator == '&') {
                if (result == true) {
                    ways += numberofWaysToGetExpression(s1, true, results) * numberofWaysToGetExpression(s2, true, results);

                } else {
                    ways += numberofWaysToGetExpression(s1, true, results) * numberofWaysToGetExpression(s2, false, results);
                    ways += numberofWaysToGetExpression(s1, false, results) * numberofWaysToGetExpression(s2, true, results);
                    ways += numberofWaysToGetExpression(s1, false, results) * numberofWaysToGetExpression(s2, false, results);
                }
            }
            if (operator == '^') {
                if (result == true) {
                    ways += numberofWaysToGetExpression(s1, true, results) * numberofWaysToGetExpression(s2, false, results);
                    ways += numberofWaysToGetExpression(s1, false, results) * numberofWaysToGetExpression(s2, true, results);
                } else {
                    ways += numberofWaysToGetExpression(s1, false, results) * numberofWaysToGetExpression(s2, false, results);
                    ways += numberofWaysToGetExpression(s1, true, results) * numberofWaysToGetExpression(s2, true, results);
                }
            }
        }

        results.put(expression+"->"+result, ways);
        return ways;
    }

    private String getMeFirstStringWithOperands(String expression, int i) {
        return expression.substring(0, i + (i - 1));
    }

    private String getMeSecondStringWithOperands(String expression, int i) {
        return expression.substring(i + i, expression.length());
    }

    private int getMeOperands(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1' || str.charAt(i) == '0') {
                count++;
            }
        }
        return count;
    }


}// END OF CLASS


class Graph {
    public Node[] nodes;
}

class Node {
    public String name;
    boolean visited;
    public Node[] children;

    public Node(String name) {
        this.name = name;
    }
}

class Box {
    public int height;
    public int width;
    public int depth;

    public Box(int h, int w, int d) {
        this.depth = d;
        this.height = h;
        this.width = w;
    }

    public boolean canBeAbove(Box b) {
        return (b.width > width && b.height > height && b.depth > depth);
    }

    public boolean canBoxBeBelow(Box b) {
        return (b.width < width && b.height < height && b.depth < depth);
    }
}

class BoxComparator implements Comparator<Box> {

    @Override
    public int compare(Box x, Box y) {
        return y.height - x.height;
    }
}
