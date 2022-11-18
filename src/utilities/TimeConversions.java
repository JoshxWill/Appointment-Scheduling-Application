package utilities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**Time Conversions Class**/
public class TimeConversions {
    /**
     * Time Conversion
     *
     * @param UTC Universal Time
     * @return Local Time
     */
    public static Timestamp UTCtoLocal(Timestamp UTC){
        LocalDateTime localDateTime0 = UTC.toLocalDateTime();
        ZonedDateTime zonedDateTime0 = localDateTime0.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime1 = zonedDateTime0.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime localDateTime1 = zonedDateTime1.toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(localDateTime1);
        return timestamp;
    }

    /**
     * Time Conversion
     * @param UTC Universal Time
     * @return Local Time
     */
    public static Timestamp UTCtoLocal1(Timestamp UTC){
        LocalDateTime localDateTime0 = UTC.toLocalDateTime();
        ZonedDateTime zonedDateTime0 = localDateTime0.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime1 = zonedDateTime0.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime localDateTime1 = zonedDateTime1.toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(localDateTime1);
        return timestamp;
    }
}
