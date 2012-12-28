package dungMain;

import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args){
		
		ArrayList<Penis> bagOfDicks = new ArrayList<Penis>();
		bagOfDicks.add(new Penis(69));
		for (int iuP1 = 0; iuP1 < 10; iuP1 ++){
			bagOfDicks.get(0).boner();
		}
		
		System.out.println(bagOfDicks.get(0).length);
		
	}
}

class Penis{
	
	public int length;
	
	public Penis(int lng){
		length = lng;
	}
	
	public void boner(){
		length ++;
	}
	
}
