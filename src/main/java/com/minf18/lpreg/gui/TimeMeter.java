
package com.minf18.lpreg.gui;

import java.util.Calendar;

public class TimeMeter {

    private final long startTime;

    public TimeMeter() {
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    public long getTime() {
        return Calendar.getInstance().getTimeInMillis() - startTime;
    }
}
