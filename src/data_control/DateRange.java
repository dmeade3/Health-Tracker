package data_control;

import java.util.Date;

import static util.MainUtility.DATE_FORMAT;

/**
 * Created by dcmeade on 4/28/2017.
 */
public class DateRange
{
    private Date beginning;
    private Date end;

    public DateRange(Date beginning, Date end)
    {
        this.beginning = beginning;
        this.end = end;
    }

    public Date getBeginning()
    {
        return beginning;
    }

    public Date getEnd()
    {
        return end;
    }

    public boolean inRange(Date checkDate)
    {
        if ((checkDate.after(beginning)) && (checkDate.before(end)) ||
             (DATE_FORMAT.format(checkDate).equals(DATE_FORMAT.format(beginning))) ||
             (DATE_FORMAT.format(checkDate).equals(DATE_FORMAT.format(end))))
        {
            return true;
        }

        return false;
    }
}
