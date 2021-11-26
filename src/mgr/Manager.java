package mgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager<T extends Manageable> {
	public ArrayList<T> mList = new ArrayList<>();
	
	public void readAll(String filename, Factory<T> fac) {
		Scanner filein = openFile(filename);
		T m = null;
		while (filein.hasNext()) {
			m = fac.create();	// new Manageable();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}
	
	public ArrayList<T> findList(String filename, Factory<T> fac) {
		ArrayList<T> retList = new ArrayList<>();
		Scanner filein = openFile(filename);
		T m = null;
		while (filein.hasNext()) {
			m = fac.create();	// new Manageable();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
		retList = mList;
		return retList;
	}
	
	
	public void printAll() {
		for (T m: mList)
			m.print();
	}
	
	public T findatList(String kwd,ArrayList<T> findList) {
		for (T m: findList)
			if (m.matches(kwd))
				return m;
		return null;
	}
	
	public T find(String kwd) {
		for (T m: mList)
			if (m.matches(kwd))
				return m;
		return null;
	} 
	
	public List<T> findAll(String kwd) {
		List<T> results = new ArrayList<>();
		for (T m: mList)
			if (m.matches(kwd))
				results.add(m);
		return results;
	}
	
	public void search(Scanner keyScanner) {
		String kwd = null;
		while (true) {
			System.out.print(">> ");
			kwd = keyScanner.next();
			if (kwd.equals("end"))
				break;
			for (T m: mList)
				if (m.matches(kwd))
					m.print();
		}
	}
	
	Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println(filename + ": ���� ����");
			System.exit(0);
		}
		return filein;
	}
}