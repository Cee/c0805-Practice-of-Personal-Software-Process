/**
 *@author & date
 *Name:Wang
 *Date:13.07.24                
 **/

/**
 *@Summary
 *Class:
 *Calculate{
 *public void go(String file1, String file2){
 *public static void main(String[] args);
 *public ArrayList<Double> readData(String fileName);
 *public double getAverage(ArrayList<Double> cal);
 *public double calBeta0(double beta1, double xAverage, double yAverage);
 *public double calBeta1(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList, double xAverage, double yAverage);
 *public double calR(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList);
 *public double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList);
 *public double multiple(ArrayList<Double> xArrayList);
 *public double gamma(double x);
 *public double f(double x,double dof);
 *public double getTailArea(double r, ArrayList<Double> arrayList);
 *public double cal(double x,double dof);
 *public double getX(double p,double dof);
 *public double getSigma(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1);
 *public double getRange(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1,double xk);
 *}
 **/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Class
public class Calculate {
	//Start
	public void go(String file1, String file2){
		ArrayList<Double> xArrayList = new ArrayList<Double>();
		xArrayList = readData(file1);
		ArrayList<Double> yArrayList = new ArrayList<Double>();
		yArrayList = readData(file2);	
		double xAverage = getAverage(xArrayList);
		double yAverage = getAverage(yArrayList);
		
		double beta1 = calBeta1(xArrayList,yArrayList,xAverage,yAverage);
		double beta0 = calBeta0(beta1,xAverage,yAverage);
		double r = calR(xArrayList,yArrayList);
		
		double x = 247.88;
		double y = beta0 + beta1 * x;
		double range = getRange(xArrayList, yArrayList, beta0, beta1, x);
		
		System.out.println("r:"+r);
		System.out.println("r^2:"+r*r);
		System.out.println("tailArea:"+getTailArea(r, xArrayList));
		System.out.println("beta0:"+beta0);
		System.out.println("beta1:"+beta1);
		System.out.println("yk:"+y);
		System.out.println("Range:"+range);
		System.out.println("UPI:"+(y+range));
		System.out.println("LPI:"+(y-range));
		
	}
	//End
	//Start
	public static void main(String[] args){
		Calculate test = new Calculate();
		System.out.println("----------Test 3----------");
		test.go("5.txt","6.txt");
		System.out.println("----------Test 4----------");
		test.go("5.txt","7.txt");
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
	public double gamma(double x){
		double ans = 1;
		while (x > 1){			
			x = x - 1;
			ans = ans * x;
		}
		if (Math.abs(x-1/2)< 1) return ans*Math.sqrt(Math.PI); else return ans; 
	}
	//End
	//Start
	public double f(double x,double dof){
		double cal = 0;
		cal = Math.pow((1 + x * x / dof), -(dof + 1) / 2) * gamma((dof + 1) / 2);
		cal = cal / (Math.sqrt(dof * Math.PI) * gamma(dof / 2)); 
		return cal;
	}
	//End
	//Start
	public double getTailArea(double r, ArrayList<Double> arrayList){
		double x = Math.abs(r) * Math.sqrt(arrayList.size() - 2) / Math.sqrt(1 - r * r);
		return 1 - 2 * cal(x, arrayList.size() - 2);			
	}
	//End
	//Start
	public double cal(double x,double dof){
		int num_seg = 10;
		final double E = 0.00001;
		double answer_old = 0, answer_new = 0;
		do{
			double w = x / num_seg;
			answer_old = answer_new;
			answer_new = 0;
			for (int i = 1; i < num_seg; i++){
				if (i % 2 == 0){
					answer_new += 2 * f(i*w,dof);
				}else{
					answer_new += 4 * f(i*w,dof);
				}
			}
			answer_new += f(0,dof) + f(x,dof);
			answer_new *= w / 3;
			num_seg *= 2;
		}while(Math.abs(answer_old - answer_new) > E);
		return answer_new;
	}
	//End
	//Start
	public double getX(double p,double dof){
		double x = 1.0;
		double d = 0.5;
		boolean isBig = false;		
		int num_seg = 1000;
		final double E = 0.00000001;
		double answer = 0;
		while(Math.abs(answer - p) > E){
			double w = x / num_seg;			
			answer = 0;
			for (int i = 1; i < num_seg; i++){
				if (i % 2 == 0){
					answer += 2 * f(i*w,dof);
				}else{
					answer += 4 * f(i*w,dof);
				}
			}
			answer += f(0,dof) + f(x,dof);
			answer *= w / 3;
			if (Math.abs(answer - p) > E){
				if (isBig){
					if (answer <= p) {
						d /= 2;
						isBig = !isBig;
					}
					x -= d;
				}else{
					if (answer >= p) {
						d /= 2;
						isBig = !isBig;
					}
					x += d;
				}
			}
		}
		return x;
	}
	//End
	//Start
	public double getSigma(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1){
		ArrayList<Double> cal = new ArrayList<Double>();
		for (int i = 0; i < xArrayList.size(); i++){
			cal.add(yArrayList.get(i) - b0 - b1 * xArrayList.get(i));
		}
		return Math.sqrt(multiple(cal,cal) / (xArrayList.size() - 2));
	}			
	//End
	//Start
	public double getRange(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1,double xk){
		double range = getX(0.35, xArrayList.size() - 2) * getSigma(xArrayList, yArrayList, b0, b1);
		ArrayList<Double> cal = new ArrayList<Double>();
		for (int i = 0; i < xArrayList.size(); i++){
			cal.add(xArrayList.get(i) - getAverage(xArrayList));
		}		
		return range * Math.sqrt( 1 + (1.0 / xArrayList.size()) + ((xk - getAverage(xArrayList)) * (xk - getAverage(xArrayList)) / multiple(cal,cal)));
	}
	//End
}
//ClassEnd
