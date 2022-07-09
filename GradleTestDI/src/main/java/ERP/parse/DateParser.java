package ERP.parse;

import java.util.Date;

public interface DateParser {
    Date parse(String date);

    int parseWeekNumber(Date date);

    boolean isCorrectDate(String date);

    String getDateFormat();
}
