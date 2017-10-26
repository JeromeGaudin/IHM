import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;


public class Main {
	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		date = new GregorianCalendar();
		if(args.length == 1) {
			try {
				date.setTime(Date.valueOf(args[0]));
			} catch(IllegalArgumentException ex) {
				System.err.println("Date au mauvais format ( format : yyyy-mm-dd )");
				System.exit(1);
			}
		}
		Ecran ecran = new Ecran(date);
		ecran.setVisible(true);
	}
}
