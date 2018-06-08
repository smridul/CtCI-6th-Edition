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


      /*  Box b1 = new Box(4, 2, 4);
        Box b2 = new Box(2, 1, 1);
        Box b3 = new Box(5, 3, 4);
        Box b4 = new Box(6, 3, 8);
        ArrayList<Box> boxes = new ArrayList<>();
        boxes.add(b1);
        boxes.add(b2);
        boxes.add(b3);
        boxes.add(b4);


*/

        Box[] boxList = { new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2), new Box(9, 7, 3)};
		ArrayList<Box> boxes = new ArrayList<Box>();
		for (Box b : boxList) {
			boxes.add(b);
		}






        Integer[] weights = new Integer[boxes.size()];
        int max = buildWeightNumber2(boxes, weights);


        System.out.print(Arrays.toString(weights));
        System.out.println("max is "  +max);

    }

    // wrong
    private void buildWeightNumber(ArrayList<Box> boxes, Integer[] weights) {
        Collections.sort(boxes, new BoxComparator());

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

    // i think correct
    public static int buildWeightNumber2(ArrayList<Box> boxes, Integer[] weights) {
        Collections.sort(boxes, new BoxComparator());
        int max=0;
        // let weight[i] be the number which tells that max weight when that box is selected
        for (int i = boxes.size() - 1; i >= 0; i--) {
            Box currentbox = boxes.get(i);
            int totalheight = currentbox.height;
            int maxtotalheightwhencurrentselected=totalheight;
            for (int j = i + 1; j < boxes.size(); j++) {
                if (boxes.get(j).canBeAbove(currentbox)) {
                    totalheight = currentbox.height + weights[j];
                    maxtotalheightwhencurrentselected = Math.max(totalheight, maxtotalheightwhencurrentselected);
                }
            }
            weights[i] = maxtotalheightwhencurrentselected;
            max=Math.max(max, weights[i]);
        }
        return max;
    }

    // i think correct with book BOX
    public static int buildWeightNumber3(ArrayList<Q8_13_Stack_of_Boxes.Box> boxes, Integer[] weights) {

        Collections.sort(boxes, new Q8_13_Stack_of_Boxes.BoxComparator());

        int max=0;
        // let weight[i] be the number which tells that max weight when that box is selected
        for (int i = boxes.size() - 1; i >= 0; i--) {
            Q8_13_Stack_of_Boxes.Box currentbox = boxes.get(i);
            int totalheight = currentbox.height;
            int maxtotalheightwhencurrentselected=totalheight;
            for (int j = i + 1; j < boxes.size(); j++) {
                if (boxes.get(j).canBeAbove(currentbox)) {
                    totalheight = currentbox.height + weights[j];
                    maxtotalheightwhencurrentselected = Math.max(totalheight, maxtotalheightwhencurrentselected);
                }
            }
            weights[i] = maxtotalheightwhencurrentselected;
            max=Math.max(max, weights[i]);
        }
        return max;
    }

    @Test
    public void waysToGetExpression() {

        String expression = "1^0|0|1";
        expression = "0&0&0&1^1|0";
        Map<String, Integer> results = new HashMap<>();
        System.out.print(numberofWaysToGetExpression(expression, true, results));
        //     System.out.print(" calls "+ calls);
        //   System.out.println(" results size "+ results.size());
        for (String s : results.keySet()) {
            //          System.out.println(s + " " + results.get(s)) ;
        }
    }

    private int numberofWaysToGetExpression(String expression, boolean result, Map<String, Integer> results) {

        if (results.get(expression + "->" + result) != null) {
            return results.get(expression + "->" + result);
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

        results.put(expression + "->" + result, ways);
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

    @Test
    public void Question16_4() {
        int[][] array = new int[][]{
                {1, 0, 0},
                {1, 0, 1},
                {0, 1, 1}
        };


        boolean win = false;
        int winner = -1;
        //check row
        for (int i = 0; i < 3; i++) {
            if (isRowDone(i, array)) {
                win = true;
                winner = array[i][0];
                break;
            }
        }

        if (!win) {
            //check column
            for (int i = 0; i < 3; i++) {
                if (checkColumn(i, array)) {
                    win = true;
                    winner = array[0][i];
                    break;
                }
            }
        }

        if (!win) {
            //check column
            if (isDiagDone(array)) {
                winner = array[0][0];
            } else if (isDiagDone2nd(array)) {
                winner = array[2][0];
            }
        }

        System.out.println("Winner is " + winner);

    }


    boolean isRowDone(int r, int[][] array) {
        if (array[r][0] == -1) {
            return false;
        }

        int winner = array[r][0];
        for (int i = 0; i < 3; i++) {
            if (array[r][i] != winner) {
                return false;
            }
        }
        return true;
    }

    boolean isDiagDone(int[][] array) {
        if (array[0][0] == -1) {
            return false;
        }

        int winner = array[0][0];
        for (int i = 0; i < 3; i++) {
            if (array[i][i] != winner) {
                return false;
            }
        }
        return true;
    }

    boolean isDiagDone2nd(int[][] array) {
        if (array[2][0] == -1) {
            return false;
        }

        int winner = array[2][0];
        for (int i = 0; i < 3; i++) {
            if (array[2 - i][i] != winner) {
                return false;
            }
        }
        return true;
    }

    boolean checkColumn(int c, int[][] array) {
        if (array[0][c] == -1) {
            return false;
        }

        int winner = array[0][c];
        for (int i = 0; i < 3; i++) {
            if (array[i][c] != winner) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void question16_5() {
        int n = 1000;
        int powerof2 = 0;
        int powerof5 = 0;


        for (int i = 1; i <= n; i++) {
            powerof2 += power(i, 2);
        }
        for (int i = 1; i <= n; i++) {
            powerof5 += power(i, 5);
        }
        System.out.println(Math.min(powerof2, powerof5));
    }

    int power(int number, int power) {

        int numberOfpower = 0;
        for (; number > 0; ) {
            if (number % power == 0) {
                numberOfpower++;
            } else {
                break;
            }
            number = number / power;
        }
        return numberOfpower;
    }


    @Test
    public void printTheNumberInwords() {


        HashMap<Integer, String> onesToString = new HashMap<>();
        onesToString.put(1, "one");
        onesToString.put(2, "two");
        onesToString.put(3, "three");
        onesToString.put(4, "four");
        onesToString.put(5, "five");
        onesToString.put(6, "six");
        onesToString.put(7, "seven");
        onesToString.put(8, "eight");
        onesToString.put(9, "nine");

        HashMap<Integer, String> twoOnesToString = new HashMap<>();

        twoOnesToString.put(0, "ten");
        twoOnesToString.put(1, "eleven");
        twoOnesToString.put(2, "twelve");
        twoOnesToString.put(3, "thirteen");
        twoOnesToString.put(4, "fourtenn");
        twoOnesToString.put(5, "fifteen");
        twoOnesToString.put(6, "sixteen");
        twoOnesToString.put(7, "seventeen");
        twoOnesToString.put(8, "eighteen");
        twoOnesToString.put(9, "nineteen");

        HashMap<Integer, String> tensToString = new HashMap<>();
        tensToString.put(2, "twenty");
        tensToString.put(3, "thirty");
        tensToString.put(4, "fourty");
        tensToString.put(5, "fifty");
        tensToString.put(6, "sixty");
        tensToString.put(7, "sevnty");
        tensToString.put(8, "eighty");
        tensToString.put(9, "ninety");

        String[] prefixes = new String[]{"thousand", "million", "billion"};


        int n = 223101;
        String s = printNumber(n, onesToString, twoOnesToString, tensToString, prefixes);
        System.out.println(s);
    }


    String printNumber(int n, HashMap<Integer, String> onesToString,
                       HashMap<Integer, String> twoOnesToString, HashMap<Integer, String> tensToString,
                       String[] prefixes) {
        if (n == 0) return "zero";


        String words = "";
        int prefixIndex = 0;
        for (int i = 1000000000; i > 0; i = i / 1000) {

            int first = n / i;
            if (first != 0) {
                String threeWords = convertThreeDigitToWord(first, onesToString, twoOnesToString, tensToString, prefixes);
                words += threeWords;
                if (prefixIndex != 3) {
                    words += prefixes[2 - prefixIndex] + " ";
                }
            }
            prefixIndex++;
            n = n % i;

        }

        return words;
    }


    String convertThreeDigitToWord(int n, HashMap<Integer, String> onesToString,
                                   HashMap<Integer, String> twoOnesToString, HashMap<Integer, String> tensToString,
                                   String[] prefixes) {

        String words = "";

        int first = n / 100;
        String hundred = "";
        if (first != 0) {
            hundred += onesToString.get(first) + " ";
            hundred += "hundred" + " ";
        }
        words = hundred;

        n = n % 100;
        first = n / 10;

        if (first == 1) {
            words += twoOnesToString.get(n % 10) + " ";
            return words;
        } else if (first != 0) {
            words += tensToString.get(n / 10) + " ";

        }

        n = n % 10;
        if (n != 0) {
            words += onesToString.get(n) + " ";
        }

        return words;
    }

    @Test
    public void questionConsequtiveMaxsum() {

        // int[] arr = new int[]{2, -8, 3, -2, 4, -10};
        //int[] arr = new int[]{-1, 3, 3, -2, 4, -10};
        int[] arr = {-2, -8, -1, -2, -3, -10};


        int sum = 0;


        MaxSumPair maxSumPair = getMaxsum(arr, 0);

        int lsum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (maxSumPair.rightIndex >= i) {
                sum = maxSumPair.sum - lsum;
                if (sum > maxSumPair.sum) {
                    maxSumPair.startIndex = i;
                    maxSumPair.sum = sum;
                }
                lsum = lsum + arr[i];
            } else {
                if (maxSumPair.sum > arr[i]) {
                    continue;
                } else {
                    maxSumPair = getMaxsum(arr, i);
                    lsum = arr[i];
                }
            }
        }


        System.out.println("Max sum is " + maxSumPair.sum + " leftElement=" + maxSumPair.startIndex + " rightElement= " + maxSumPair.rightIndex);

    }

    private MaxSumPair getMaxsum(int[] arr, int start) {
        int sum = 0;
        int maxSum = -10000;
        MaxSumPair maxSumPair = new MaxSumPair();
        maxSumPair.startIndex = start;
        for (int i = start; i < arr.length; i++) {
            sum += arr[i];
            if (sum > maxSum) {
                maxSumPair.rightIndex = i;
                maxSum = sum;
            }
        }
        maxSumPair.sum = maxSum;
        return maxSumPair;
    }


    @Test
    public void operations() {

        int a = 10, b = 2;
        System.out.println(a + (~b + 1));


        int sum = 0;
        int count = 0;
        for (; sum < a; ) {
            sum = sum + b;
            count++;

        }
        if (sum > a) count--;
        System.out.println(count);
    }

    @Test
    public void planks_small_long() {
        HashSet<Integer> lengths = allLengths(12, 2, 3);

        for (int i : lengths) {
            System.out.print(i + ", ");
        }

        System.out.println(" ");

        lengths = allLengthsIterative(12, 2, 3);

        for (int i : lengths) {
            System.out.print(i + ", ");
        }
    }

    private HashSet<Integer> allLengths(int k, int n, int m) {
        if (k == 1) {
            HashSet<Integer> lengths = new HashSet<>();
            lengths.add(n);
            lengths.add(m);
            return lengths;
        } else {
            HashSet<Integer> lengths = allLengths(k - 1, n, m);
            HashSet<Integer> newSet = new HashSet<>();
            for (int len : lengths) {
                newSet.add(len + n);
                newSet.add(len + m);
            }
            return newSet;

        }
    }

    private HashSet<Integer> allLengthsIterative(int k, int n, int m) {
        HashSet<Integer> lengths = new HashSet<>();
        lengths.add(n);
        lengths.add(m);

        HashSet<Integer> newSet = new HashSet<>();

        for (int i = 2; i <= k; i++) {
            for (int len : lengths) {
                newSet.add(len + n);
                newSet.add(len + m);
            }
            lengths.clear();
            lengths.addAll(newSet);
            newSet.clear();
        }
        return lengths;
    }

    @Test
    public void psudohits() {
        String actual = "RGBYR";
        String guess =  "GGRRG";

        char results[] = returnHitsPsudohitcount(guess, actual);
        printHits(results);

        String a ="a".substring(2);
        System.out.println();


    }

    char[] returnHitsPsudohitcount(String guess, String actual){
        char arr[] = new char[]{'0', '0', '0', '0', '0'};
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c == actual.charAt(i)) {
                arr[i] = 'H';
            } else {
                for(int j=0; j< actual.length(); j++){
                    if(actual.charAt(j) == c && arr[j] == '0'){
                        arr[j] = 'P';
                        break;
                    }
                }
            }
        }
        return arr;
    }

    void printHits(char [] results){
        int hits=0; int psudohits = 0;
        for (int i=0; i<results.length; i++){
            if(results[i]=='H') hits++;
            else if(results[i]=='P') psudohits++;
        }
        System.out.println("psudo hits = " + psudohits + " hits = "+ hits);
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

class MaxSumPair {
    int sum;
    int startIndex;
    int rightIndex;
}

 class Box {
    public int height;
    public int width;
    public int depth;

   /* public Box(int h, int w, int d) {
        this.depth = d;
        this.height = h;
        this.width = w;
    }
    */

    // book constructor
    public Box(int w, int h, int d) {
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
