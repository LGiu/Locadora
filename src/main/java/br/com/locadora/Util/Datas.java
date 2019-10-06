package br.com.locadora.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Datas {

    public static Date dataSerializada(Date date) {
        OffsetDateTime localDateTime = date.toInstant().atZone(ZoneId.of("UTC")).toOffsetDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            return stringToDate(localDateTime.format(dtf), "yyyy-MM-dd'T'HH:mm:ss.SSS");
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date stringToDate(String data, String formato) throws ParseException {
        return new SimpleDateFormat(formato).parse(data);
    }

    public static Date data(Date date, int minutos) {
        Date dt = new Date(date.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MINUTE, minutos);
        return c.getTime();
    }
}