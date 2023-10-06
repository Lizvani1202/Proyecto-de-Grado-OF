package com.example.SistemaGIS.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Utils {
    public static Integer differenceInMinutes(LocalDateTime date1, LocalDateTime date2) {
        return (int) ChronoUnit.MINUTES.between(date1, date2);
    }

    public static Integer calcSpeedKmH(Integer timeInMinutes, Integer distance) {
        return (int) (distance / (timeInMinutes / 60.0));
    }
}
