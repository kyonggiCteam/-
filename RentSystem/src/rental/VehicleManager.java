package rental;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import mgr.Manager;

public class VehicleManager extends Manager<Vehicle> {
	ArrayList<Vehicle> breakdownList = new ArrayList<>();

	public int breakdownReport(String code) {
		Vehicle breakdown = find(code);
		breakdown.state = -1;
		
		int msgflag = 0;
		try {
	         BufferedReader vehicleReader = null;
	         BufferedWriter vehicleWriter = null;
	
	         vehicleReader = new BufferedReader(new FileReader("vehicle.txt"));
	         vehicleWriter = new BufferedWriter(new FileWriter("vehicle.txt", true));
	
	         ArrayList<String[]> arrays = new ArrayList<String[]>();
	         String str = null;
	
	         while ((str = vehicleReader.readLine()) != null) {
	            String[] strarr = str.split(" ");
	            if (strarr.length > 0)
	               arrays.add(strarr);
	            if (strarr[0].matches(code)) {
	               if (strarr[2].equals("-1")) {
	                  msgflag = 1;
	               }
	               strarr[2] = "-1";
	            }
	         }
	         new FileOutputStream("vehicle.txt").close();
	
	         for (int i = 0; i < arrays.size(); i++) {
	            String[] outputarr = arrays.get(i);
	            for (int k = 1; k < outputarr.length; k++) {
	               outputarr[0] = outputarr[0] + " " + outputarr[k];
	            }
	            String oneLine = outputarr[0];
	            if (i == arrays.size() - 1) {
	               vehicleWriter.write(oneLine);
	            } else {
	               vehicleWriter.write(oneLine + "\n");
	            }
	         }
	         vehicleReader.close();
	         vehicleWriter.close();
	      } catch (Exception ex) {
	      }
		
		return msgflag;
	}
}