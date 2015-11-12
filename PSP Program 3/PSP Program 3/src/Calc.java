/**
 *@author & date
 *Name:Wang
 *Date:13.07.10                  
 **/

/**
 *@Summary
 *Class:
 *Calc{
 *public void go();
 *public static void main(String[] args);
 *public ArrayList<Double> readData(String fileName);
 *public double getAverage(ArrayList<Double> cal);
 *public double calBeta0(double beta1, double xAverage, double yAverage);
 *public double calBeta1(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList, double xAverage, double yAverage);
 *public double calR(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList);
 *public double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList);
 *public double multiple(ArrayList<Double> xArrayList);
 *}
 **/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//Class
public class Calc {
	//Start
	double beta1;
	double beta0;
	double r;
	double y;
	
	public void go(){
		
		ArrayList<Double> xArrayList = new ArrayList<Double>();
		xArrayList = readData("data_1.txt");
		ArrayList<Double> yArrayList = new ArrayList<Double>();
		yArrayList = readData("data_2.txt");	
		double xAverage = getAverage(xArrayList);
		double yAverage = getAverage(yArrayList);
		
		beta1 = calBeta1(xArrayList,yArrayList,xAverage,yAverage);
		beta0 = calBeta0(beta1,xAverage,yAverage);
		r = calR(xArrayList,yArrayList);
		
		double x = 386;
		y = beta0 + beta1 * x;
		
		System.out.println("xAverage:"+xAverage);
		System.out.println("yAverage:"+yAverage);
		System.out.println("beta0:"+beta0);
		System.out.println("beta1:"+beta1);
		System.out.println("r:"+r);
		System.out.println("r^2:"+r*r);
		System.out.println("y:"+y);
		
	}
	//End
	//Start
	public static void main(String[] args){
		Calc calc = new Calc();
		calc.go();
	}
	//End
	//Start
	public ArrayList<Double> readData(String fileName){
		// read data
		File file = new File(fileName);
		ArrayList<Double> calList = new ArrayList<Double>();
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String lineString;
			try {
				while ((lineString = bReader.readLine())!= null){
					calList.add(Double.parseDouble(lineString));
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
	//End
	//Start
	public double getAverage(ArrayList<Double> cal){
		double sumFloat = 0;
		double averageFloat = 0;
		for (int i = 0; i < cal.size(); i++){
			sumFloat += cal.get(i);
		}
		averageFloat = sumFloat / cal.size();
		return averageFloat;
	}
	//End
	//Start
	public double calBeta0(double beta1, double xAverage, double yAverage){
		double cal = yAverage - beta1*xAverage;
		return cal;
	}
	//End
	//Start
	public double calBeta1(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList, double xAverage, double yAverage){
		double cal = 0;
		cal = multiple(xArrayList, yArrayList)-(xArrayList.size()*xAverage*yAverage);
		cal = cal / (multiple(xArrayList, xArrayList) - xArrayList.size()*xAverage*xAverage);
		return cal;
	}
	//End
	//Start
	public double calR(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
		double cal = 0;
		cal = xArrayList.size()*multiple(xArrayList, yArrayList)-multiple(xArrayList)*multiple(yArrayList);
		double minus = 0;
		minus = xArrayList.size()*multiple(xArrayList, xArrayList)-multiple(xArrayList)*multiple(xArrayList);
		minus *= yArrayList.size()*multiple(yArrayList, yArrayList)-multiple(yArrayList)*multiple(yArrayList);
		cal = cal / Math.sqrt(minus);
		return cal;
	}
	//End
	//Start
	public double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
		double cal = 0;
		for (int i = 0; i < xArrayList.size(); i++){
			cal += xArrayList.get(i)*yArrayList.get(i);
		}
		return cal;
	}
	//End
	//Start
	public double multiple(ArrayList<Double> xArrayList){
		double cal = 0;
		for (int i = 0; i < xArrayList.size(); i++){
			cal += xArrayList.get(i);
		}
		return cal;
	}
	//End
	//Start
}
//ClassEnd
