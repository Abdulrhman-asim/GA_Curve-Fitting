package ass2;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class RFF {
	static public Vector<TestCase> readFromFile() {

		try {
			Scanner in = new Scanner(new File("input-2.txt"));
			int numOfCases;
			numOfCases = in.nextInt();
			
			Vector<TestCase> testCases = new Vector<TestCase>();
			for(int i = 0; i < numOfCases; i++)
			{
				TestCase T = new TestCase();
				T.setNoOfPoints(in.nextInt());
				T.setDegree(in.nextInt()+1);
				for(int j = 0; j < T.getNoOfPoints(); j++)
				{
					double tmpX = in.nextDouble();
					double tmpY = in.nextDouble();
					T.addPoint(tmpX,tmpY);
				}
				testCases.add(T);
			}
			in.close();
			return testCases;
		} catch (Exception E) {
			System.out.println("error404 not found!!");
		}
		return null;
	}
}