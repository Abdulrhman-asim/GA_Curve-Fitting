package ass2;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class CurveFittingGA {
	Vector<Solution> generation;
	TestCase casee;

	public CurveFittingGA(TestCase casee) {
		this.casee = casee;
	}

	public void generatePopulation() {
		generation = new Vector<Solution>();
		int degree = casee.getdegree();

		double tmpfit = 0;
		for (int i = 0; i < 500; i++) {
			double[] codearr = new double[degree];
			
			for (int j = 0; j < degree; j++) {
				Random r = new Random();
				int lowerBound = -10;
				int upperBound = 10;
				
				double x = lowerBound + (upperBound - lowerBound) * r.nextDouble();
				codearr[j] = x;
				
					
			}
			tmpfit = calcFitness(codearr);
			generation.add(new Solution(tmpfit, codearr));
		
		}
	}

	public int maxValueIndex(Vector<Solution> pinkman) {
		// pinkman holds the population for which you wish to get the index of the solution with the
		// lowest fitness
		Solution max = pinkman.get(0);
		int maxInd = 0;
		for (int i = 1; i < pinkman.size(); i++) {
			if (max.getFitness() > pinkman.get(i).getFitness()) {
				max = pinkman.get(i);
				maxInd = i;
			}
		}

		return maxInd;

	}

	public Vector<Solution> rouletteSelection() {
		double totalFitness = 0;
		Vector<Solution> salamanca = new Vector<Solution>();
		
		for (int i = 0; i < generation.size(); i++) {
			totalFitness += generation.get(i).getFitness();
		}

		int ind;
		for (int i = 0; i < generation.size(); i++) {
			double randomNum = Math.random() * totalFitness;
			
			for (ind = 0; ind < generation.size() && randomNum > 0; ind++) {
				randomNum -= generation.get(ind).getFitness();
			}
			if (ind >= 500)
				ind--;
			salamanca.add(generation.get(ind));
		}
		return salamanca;
	}

	public Vector<Solution> crosstate() {

		// Gustavo is the Generation after the Selection
		Vector<Solution> gustavo = rouletteSelection();
		// Walter will hold new Offsprings
		
		int chromosomeLength = gustavo.get(0).getCode().length;

		for (int i = 0; i < 300; i++) {
			Vector<Solution> walter = new Vector<Solution>();
			for (int j = 0; j < gustavo.size()/2; j++) {
				Solution parent1 = gustavo.get(j);
				Solution parent2 = gustavo.get(j + 250);
				Solution offspring1 = new Solution();
				Solution offspring2 = new Solution();

				Random rand = new Random();

				int r1 = rand.nextInt(chromosomeLength-1);
				

				while (r1 == 0)
					r1 = rand.nextInt(chromosomeLength-1);

				double r2 = Math.random();

				if (r2 < 0.71) {
					double[] tmp = new double[chromosomeLength];
					double[] tmp2 = new double[chromosomeLength];
					for(int k=0 ;k<chromosomeLength ;k++)
					{
						if(k< r1)
							tmp[k] = parent1.getCode()[k];
						else
							tmp[k] = parent2.getCode()[k];
						
					}
					
					for(int k=0 ;k<chromosomeLength ;k++)
					{
						if(k< r1)
							tmp[k] = parent2.getCode()[k];
						else
							tmp[k] = parent1.getCode()[k];
						
					}
					
					offspring1.setCode(tmp);
					offspring1.setFitness(calcFitness(tmp));
					
					offspring2.setCode(tmp2);
					offspring2.setFitness(calcFitness(tmp2));

				} 
				else if(r2 >= 0.71){
					offspring1.setCode(parent1.getCode());
					offspring1.setFitness(parent1.getFitness());
					

					offspring2.setCode(parent2.getCode());
					offspring2.setFitness(parent2.getFitness());
					

				}

				// For Offspring1 mutation;
				
				r1 = rand.nextInt(chromosomeLength);
				double b = 1;
				double r11 = rand.nextDouble();
				double bound;
				
				
				
				if(r11 <= 0.5)
				{
					bound = offspring1.getCode()[r1] - (-10);
					double r12 = rand.nextDouble();
					double tmp = Math.pow((1-i/200),b);
					
					double delta = bound * (1 - Math.pow(r12,tmp));
					offspring1.getCode()[r1] -= delta;
				}
				else
				{
					bound = 10 - offspring1.getCode()[r1];
					
					double r12 = rand.nextDouble();
					double tmp = Math.pow((1-i/200),b);
					double delta = bound * (1 - Math.pow(r12,tmp));
					offspring1.getCode()[r1] += delta;
				}
				
				
				r11 = rand.nextDouble();
				
				
				
				// For Offspring2 mutation;
				
				r1 = rand.nextInt(chromosomeLength-(chromosomeLength/2));
				r11 = rand.nextDouble();
				
				
				
				if(r11 <= 0.5)
				{
					bound = offspring2.getCode()[r1] - (-10);	
					double r12 = rand.nextDouble();
					double tmp = Math.pow((1-i/200),b);
					double delta = bound * (1 - Math.pow(r12,tmp));
					offspring2.getCode()[r1] -= delta; 		
					
				}
				else
				{
					bound = 10 - offspring2.getCode()[r1];
					double r12 = rand.nextDouble();
					double tmp = Math.pow((1-i/200),b);
					double delta = bound * (1 - Math.pow(r12,tmp));
					offspring2.getCode()[r1] += delta; 	
				}
				
				
				
				
						
				offspring1.setFitness(calcFitness(offspring1.getCode()));
				offspring2.setFitness(calcFitness(offspring2.getCode()));
						
						
						
				walter.add(offspring1);
				walter.add(offspring2);
				
			}

			int maxInd = maxValueIndex(gustavo);
			Solution bestFit = new Solution(gustavo.get(maxInd));
			walter.remove(maxInd);
			walter.add(bestFit);
			gustavo = new Vector<Solution>(walter);


		}
		return gustavo;

	}


	public double calcFitness(double[] lewis) {
		double value = 0;
		
		for (int i = 0; i < casee.noOfPoints; i++) {
			double ycalc = lewis[0];
			double tmp = 0;
			for(int j = 1; j < lewis.length;j++)
			{
				tmp = Math.pow(casee.getPoints().get(i).getX(),j) * lewis[j];
				ycalc+= tmp;
				
			}
			
			tmp = Math.pow((ycalc - casee.getPoints().get(i).getY()),2);
			value +=  tmp;
		}
		
		value = value / (double) casee.getNoOfPoints();
		
		return value;
	}
}
