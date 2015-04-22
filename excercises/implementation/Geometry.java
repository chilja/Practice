/**
 * 
 */
package excercises.implementation;

/**
 * @author chiljagossow
 * 
 */
public class Geometry {

  /**
   * @param args
   */
  public static void main(String[] args) {
    maxCrossingLine();

  }

  static Line findMaxCrossingLine(Point... points) {
    // handle case with only one point or none
    if (points == null) {
      return null;
    }
    if (points.length == 1) {
      // only one point => any line that crosses the point will do
      return new Line(points[0], new Point(0, 0));
    }
    Line maxLine = null;
    int maxCounter = 0;
    for (int i = 0; (i + 1) < points.length; i++) {
      for (int k = i + 1; k < points.length; k++) {
        int counter = 2;
        Line line = new Line(points[i], points[k]);
        if (line != null) {
          for (int j = k + 1; j < points.length; j++) {
            if (line.isPointOnLine(points[j])) {
              counter++;
            }
          }
          if (counter > maxCounter) {
            maxLine = line;
            maxCounter = counter;
          }
        }
      }
    }
    return maxLine;
  }

  static void maxCrossingLine() {
    Point p1 = new Point(5, 5);
    Point p2 = new Point(2, 1);
    Point p3 = new Point(1, 0);
    Point p4 = new Point(8, 7);

    System.out.println(findMaxCrossingLine(p1, p2, p3, p4, p4));

  }

}

class Line {
  static double calculateSlope(Point point1, Point point2) {
    double yDelta = point1.yCoordinate - point2.yCoordinate;
    if (yDelta == 0) {
      return yDelta;
    }
    double xDelta = point1.xCoordinate - point2.xCoordinate;
    if (xDelta == 0) {
      throw new IllegalArgumentException("Slope is infinite.");
    }
    return yDelta / xDelta;
  }

  static double calculateYIntercept(double slope, Point point) {
    if (point == null) {
      throw new IllegalArgumentException("Point must not be null.");
    }
    return point.yCoordinate - (slope * point.xCoordinate);
  }

  // f(x) = slope*x + yIntercept
  double slope;
  double yIntercept;
  final static double epsilon = 0.0001;

  boolean slopeIsIndefinite = false;

  double xIntercept;

  Line(Point point1, Point point2) {
    if ((point1 == null) || (point2 == null) || point1.equals(point2)) {
      return;
    }
    try {
      slope = calculateSlope(point1, point2);
    }
    catch (IllegalArgumentException e) {
      slopeIsIndefinite = true;
      xIntercept = point1.xCoordinate;
    }
    yIntercept = calculateYIntercept(slope, point1);
  }

  @Override
  public String toString() {
    return "f(x) = " + Double.toString(slope) + " x + " + Double.toString(yIntercept);
  }

  boolean isPointOnLine(Point point) {
    if (point == null) {
      return false;
    }
    if (slopeIsIndefinite) {
      if (point.xCoordinate == xIntercept) {
        return true;
      } else {
        return false;
      }
    }
    double result = ((slope * point.xCoordinate) + yIntercept) - point.yCoordinate;
    if ((result >= -epsilon) && (result <= epsilon)) {
      return true;
    }
    return false;
  }
}

class Point {

  double xCoordinate;
  double yCoordinate;

  Point(double x, double y) {
    yCoordinate = y;
    xCoordinate = x;
  }
}
