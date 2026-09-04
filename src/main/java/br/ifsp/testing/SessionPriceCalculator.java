package br.ifsp.testing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SessionPriceCalculator {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final double basePricePerMinute;
    private static final double OFF_PEAK_DISCOUNT = 0.8;

    public SessionPriceCalculator(double basePricePerMinute) {
        this(basePricePerMinute, null, null);
    }

    SessionPriceCalculator(double basePricePerMinute, LocalDateTime startTime, LocalDateTime endTime) {
        this.basePricePerMinute = basePricePerMinute;
        setStartTime(startTime);
        setEndTime(endTime);
    }

    public void checkin() {
        this.startTime = LocalDateTime.now();
    }

    public double checkout() {
        if (endTime == null)
            setEndTime(LocalDateTime.now());

        if (startTime == null)
            throw new IllegalStateException("Session start not registered.");

        double totalPrice = 0;
        LocalDateTime currentTime = startTime;

        while (currentTime.isBefore(endTime)) {
            boolean isOffPeak = isOffPeakHour(currentTime);
            totalPrice += basePricePerMinute * (isOffPeak ? OFF_PEAK_DISCOUNT : 1.0);
            currentTime = currentTime.plusMinutes(1);
        }
        return new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private boolean isOffPeakHour(LocalDateTime time) {
        LocalTime hour = time.toLocalTime();
        return (hour.isBefore(LocalTime.of(8, 0)) || !hour.isBefore(LocalTime.of(21, 0)));
    }

    private void setStartTime(LocalDateTime startTime) {
        if (startTime == null) return;
        if (isGymClosed(startTime.toLocalTime()))
            throw new IllegalStateException("Can not start session when gym is closed.");
        this.startTime = startTime;
    }

    private void setEndTime(LocalDateTime endTime) {
        if (endTime == null) return;
        if (isGymClosed(endTime.toLocalTime()))
            throw new IllegalStateException("Can not finish session when gym is closed.");
        this.endTime = endTime;
    }

    private boolean isGymClosed(LocalTime hour) {
        return hour.isBefore(LocalTime.of(5, 0)) || hour.isAfter(LocalTime.of(23, 0));
    }
}
