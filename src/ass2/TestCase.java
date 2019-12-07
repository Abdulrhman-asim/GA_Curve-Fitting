package ass2;
import java.util.Vector;


public class TestCase {
	
	int noOfPoints;
	int degree;
	Vector<Pairr> points;
	
	public TestCase()
	{
		noOfPoints = 0;
		degree = 0;
		points = new Vector<Pairr>();
	}
	
	public int getNoOfPoints() {
		return noOfPoints;
	}
	public void setNoOfPoints(int noOfPoints) {
		this.noOfPoints = noOfPoints;
	}
	public int getdegree() {
		return degree;
	}
	public Vector<Pairr> getPoints() {
		return points;
	}
	public void setPoints(Vector<Pairr> points) {
		this.points = points;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public void addPoint(double x, double y)
	{
		points.add(new Pairr(x, y));
	}
	
}