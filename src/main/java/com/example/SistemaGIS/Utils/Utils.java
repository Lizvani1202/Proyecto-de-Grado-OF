package com.example.SistemaGIS.Utils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Utils {

    public static Integer differenceInMinutes(LocalDateTime date1, LocalDateTime date2) {
        return (int) ChronoUnit.MINUTES.between(date1, date2);
    }
    public static BigInteger differenceInSeconds(LocalDateTime date1, LocalDateTime date2) {
        return BigInteger.valueOf(ChronoUnit.SECONDS.between(date1, date2));
    }

    public static Integer calcSpeedKmH(BigInteger timeInSeconds, Integer distance) {
        return (int) (distance / (timeInSeconds.doubleValue() / 3600.0));
    }
}
