package ERP.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParserImpl implements DateParser {
    private final String DATE_FORMAT = "dd/MM/yyyy";
    private final SimpleDateFormat sdfDate = new SimpleDateFormat(DATE_FORMAT);

    public String getDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public Date parse(String date) {
        Date dateOnly = null;
        try {
            dateOnly = sdfDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateOnly;
    }

    @Override
    public int parseWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public boolean isCorrectDate(String date) {
        return isCorrectInput(date);
    }

    private boolean isCorrectInput(String data) {
        try {
            sdfDate.parse(data);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
