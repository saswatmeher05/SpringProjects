package in.nareshit.somu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface AppUtil {
	public static String getCurrentDateAndTime() {
		return new SimpleDateFormat("dd/mm/yyyy-hh:mm:ss").format(new Date());
	}
}
