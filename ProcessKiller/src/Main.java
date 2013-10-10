import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			String metaId = ManagementFactory.getRuntimeMXBean().getName();
			int index = metaId.indexOf("@");
			String selfPid = metaId.substring(0, index);
			System.out.println(selfPid);
			Process process = Runtime.getRuntime().exec("tasklist");
			Scanner in = new Scanner(process.getInputStream());
			ArrayList<String> otherIds = new ArrayList<String>();
			while (in.hasNextLine()) {
				String p = in.nextLine();
//				System.out.println(p);
				if (p.contains(args[0])) {
					StringBuffer buf = new StringBuffer();
					for (int i = 0; i < p.length(); i++) {
						char ch = p.charAt(i);
						if (ch != ' ') {
							buf.append(ch);
						}
					}
					otherIds.add(buf.toString().split("Console")[0].substring(args[0].length()));
				}

			}
			for(int i = 0;i<otherIds.size();i++){
				if(!otherIds.get(i).equals(selfPid)){

					Runtime.getRuntime().exec("tskill "+otherIds.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
