package employee.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Date {
    public String dateNowToIso(){
        TimeZone tz = TimeZone.getDefault();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new java.util.Date());
        return nowAsISO;
    }
}
