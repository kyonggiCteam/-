package PublicBike;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract public class Manager <T extends Manageable> {
	static ArrayList <Manageable> mList = new ArrayList<>();
	Scanner openFile(String filename) {
	Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (IOException e)
		{
			System.out.println("파일 입력 오류");
			System.exit(0);
		}
		return filein;
	}
	void readAllFile(String filename, ArrayList <Manageable> mList, int type, Factory fac) {
		Scanner filein = openFile(filename);
		Manageable m = null;
		while (filein.hasNext()) {
			m = fac.create(type);
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	static Manageable find(String kwd, ArrayList<Manageable> mList) {
		for (Manageable m: mList) {
			if (m.matches(kwd))
				return m;
		}
		return null;
	}
}
