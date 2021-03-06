import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CalAverage {
	
	public CalAverage(){
		ArrayList<Float> cal = new ArrayList<Float>();
		cal = readData("data_1.txt");
		float averageFloat = getAverage(cal);
		float standardFloat = getStandard(cal);		
		System.out.println(averageFloat+" "+standardFloat);
		cal = readData("data_2.txt");
		averageFloat = getAverage(cal);
		standardFloat = getStandard(cal);		
		System.out.println(averageFloat+" "+standardFloat);
	}
	
	public static void main(String[] args){
		new CalAverage();
	}

	public float getAverage(ArrayList<Float> cal){
		float sumFloat = 0;
		float averageFloat = 0;
		for (int i = 0; i < cal.size(); i++){
			sumFloat += cal.get(i);
		}
		averageFloat = sumFloat / cal.size();
		return averageFloat;
	}
	
	public float getStandard(ArrayList<Float> cal){
		
		float sumFloat = 0;
		float averageFloat = 0;
		for (int i = 0; i < cal.size(); i++){
			sumFloat += cal.get(i);
		}
		averageFloat = sumFloat / cal.size();
		sumFloat = 0;
		for (int i = 0; i < cal.size(); i++){
			sumFloat += (cal.get(i) - averageFloat) * (cal.get(i) - averageFloat);
		}
		float standardFloat = 0;
		standardFloat = sumFloat / (cal.size() - 1);
		standardFloat = (float) Math.sqrt(standardFloat);	
		
		return standardFloat;
		
	}
 
	public ArrayList<Float> readData(String fileName){
		// read data
		File file = new File(fileName);
		ArrayList<Float> calList = new ArrayList<Float>();
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String lineString;
			try {
				while ((lineString = bReader.readLine())!= null){
					calList.add(Float.parseFloat(lineString));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return calList;
	}
}
