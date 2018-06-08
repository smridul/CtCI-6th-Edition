package myanswers;

import org.junit.Test;

public class BitQuestions {


    @Test
    public void addWithoutArthemeticOperators() {
        int a = 5;
        int b = 5;

        System.out.println(getsum(a, b));
         System.out.println(reverse(getsum2(a,b)));
    }


    int getsum(int a, int b) {
        int xor = a ^ b;
        int and = a & b;
        int count = 0;
        int add = 0;
        String binaryformat ="";
        int bitToCopy = computeLastBit(xor);
        for (; count < 32; ) {


            // copy the bits as it is until we find the 1 in AND step:1
            while (computeLastBit(and) == 0 && count < 32) {
                add = addBit(bitToCopy, add, count);
                 binaryformat  = Integer.toBinaryString(add);
                xor = xor >> 1;
                and = and >> 1;
                bitToCopy = computeLastBit(xor);
                count++;
            }


            // now we have last bit of AND as 1  step:2

            add = addBit(bitToCopy, add, count);
             binaryformat  = Integer.toBinaryString(add);
            xor = xor >> 1;
            and = and >> 1;
            count++;

            bitToCopy = computeLastBit(xor);

            // invert the bit and copy until you find the changed bit

            if (bitToCopy == 0) {
                // just once
                add = addBit(1, add, count);
                binaryformat  = Integer.toBinaryString(add);

                //  xor = xor >> 1;
                // and = and >> 1;
                //count++;
            } else {
                do {
                    add = addBit((bitToCopy == 0) ? 1 : 0, add, count);
                    binaryformat  = Integer.toBinaryString(add);
                    xor = xor >> 1;
                    and = and >> 1;
                    count++;
                } while (bitToCopy == computeLastBit(xor) && count < 32);
            }

            // we find the changed bit. This point we have to copy this bit anyway regardless of AND bit is 1 or 0

            //  bitToCopy = (bitToCopy==0)?1:0;


            // if the AND bit is 0 we will just copy step:1


            // if the AND bit is 1 we will not copy  but move to step:2

        }


        return add;
    }


    int getsum2(int a, int b) {

        int xor = a ^ b;
        int and = a & b;
        int count = 0;
        int add = 0;
        String binaryformat="";
        int bitToCopy = computeLastBit(xor);
        for (; count < 32; ) {


            while (computeLastBit(and) == 0 && count < 31) {
                add = appendBit(bitToCopy, add);
                 binaryformat  = Integer.toBinaryString(add);
                xor = xor >> 1;
                and = and >> 1;
                bitToCopy = computeLastBit(xor);
                count++;
            }

            if(!(count < 31)){
                break;
            }


            add = appendBit(bitToCopy, add);
             binaryformat  = Integer.toBinaryString(add);
            xor = xor >> 1;
            and = and >> 1;
            count++;

            bitToCopy = computeLastBit(xor);

            // invert the bit and copy until you find the changed bit

            if (bitToCopy == 0) {
                // just once
                add = appendBit(1, add);
                 binaryformat  = Integer.toBinaryString(add);
            } else {
                do {
                    add = appendBit((bitToCopy == 0) ? 1 : 0, add);
                     binaryformat  = Integer.toBinaryString(add);
                    xor = xor >> 1;
                    and = and >> 1;
                    count++;
                } while (bitToCopy == computeLastBit(xor) && count < 32);
            }
        }
        return add;
    }


    int computeLastBit(int a) {
        return (a & 1);
    }

    // adds correctly in the beginning
    int addBit(int bit, int number, int pos) {
        if (pos > 31) return number;
        bit = bit << pos;
        return (number | bit);
    }


    int appendBit(int bit, int number) {
        String orig = Integer.toBinaryString(number);

        number = number << 1;
        String shifted = Integer.toBinaryString(number);

        int newNumber = (number | bit);
        String newNu = Integer.toBinaryString(number);


        return newNumber;
    }


    int reverse(int number) {

        // preserve the MSB bit

        // then set the MSB to 1

        // then reverse the whole number
        // the make the LSB to the preserved MSB

        int original = number;

        int msb = number>>31;

        msb = msb&1;

        String msbString = Integer.toBinaryString(msb);

        number  = number | 0x80000000;

        String orig = Integer.toBinaryString(original);
        String rev = "";
        int reverse = 0;
        String shifted = "";
        while (number != 0) {
            int lastbit = number & 1;
            reverse = reverse << 1;
            reverse = reverse | lastbit;
            rev = Integer.toBinaryString(reverse);
            number = number >>> 1;
             shifted = Integer.toBinaryString(number);

        }


        int mask = 0xfffffffe| msb;
        String maskString = Integer.toBinaryString(mask);


        reverse = reverse  & mask;

        rev = Integer.toBinaryString(reverse);

        return reverse;
    }

}


