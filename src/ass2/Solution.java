package ass2;

public class Solution {
	
	double fitness;
	double[] code;
	
	public Solution(double tmpValue, double[] codearr) {
		fitness = tmpValue;
		code = codearr;
	}
	public Solution() {
		// TODO Auto-generated constructor stub
	}
	public Solution(Solution x) {
		
		fitness = x.getFitness();
		code = x.getCode();
	}
	public double[] getCode() {
		return code;
	}
	public void setCode(double[] code) {
		this.code = code;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	
	
	
}
