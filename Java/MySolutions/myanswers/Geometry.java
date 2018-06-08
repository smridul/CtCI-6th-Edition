package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 5/30/18.
 */
public class Geometry {

    @Test
    public void bisectingSqaures() {
        // Given two squares

        Square square1 = new Square();
        Square square2 = new Square();


        // We want to get the line bisecting the sqaures.


    }
}


class Line {

    private int slope;
    private int intercept;

    public Line(int slope, int intercept) {
        this.slope = slope;
        this.intercept = intercept;
    }

    public Line(Point p1, Point p2) {
        this.slope = (p1.y - p2.y) / (p1.x - p2.x);
        this.intercept = this.slope * (0 - p1.x) + p1.y;
    }

    public static Point getTheMidPoint(Point p1, Point p2) {
        Point p = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
        return p;
    }
}

class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point a) {
        return (this.x == a.x && this.y == a.y);
    }
}

class Square {
    Point p1;
    Point p2;
    Point p3;
    Point p4;

    public Square(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Square() {
    }
    public Point getTheCenter() {
        // choose the two point randomly and get the midpoints of them
        //p1, p2
        Point temp = Line.getTheMidPoint(p1, p2);

        Point temp2 = Line.getTheMidPoint(p3, p4);

        if (temp.equals(temp2)) {
            // p1 p2 diagonal. p3 p4 are diagonal
            return temp;

        } else {
            //p1 p2 are sides, p3 p4 are sides
            // Now to find the diagonal

            temp = Line.getTheMidPoint(p1, p3);
            temp2 = Line.getTheMidPoint(p2, p4);
            if (temp.equals(temp2)) {
                //p1 p3 diagonal

                return Line.getTheMidPoint(p1, p3);
            } else {
                //p1 p4 are diagonal
                return Line.getTheMidPoint(p1, p4);
            }
        }

    }
}