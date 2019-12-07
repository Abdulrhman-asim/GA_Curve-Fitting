package ass2;

import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		
		Vector<TestCase> testCases = RFF.readFromFile();
		
		for(int i = 0; i < testCases.size(); i++)
		{
			CurveFittingGA solver = new CurveFittingGA(testCases.get(i));
			Vector<Solution> finalGen = new Vector<Solution>(); 
			solver.generatePopulation();
			
			finalGen = solver.crosstate();
			int bestInd = solver.maxValueIndex(finalGen);
			
			
			System.out.println("Case " + (i+1) + " Error rate: " + finalGen.get(bestInd).getFitness());
			
			for(int j=0;j<finalGen.get(bestInd).getCode().length;j++)
			{
				
					System.out.println("a" + j + ": " + finalGen.get(bestInd).getCode()[j]);
				
			}
		}
	}

}
