import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MeteoSensorAdapter implements MeteoSensor {

    private final SensorTemperature sensorTemperature;

    public MeteoSensorAdapter(SensorTemperature sensorTemperature) {
        this.sensorTemperature = sensorTemperature;
    }

    @Override
    public int getId() {
        return sensorTemperature.identifier();
    }

    @Override
    public Float getTemperature() {
        return (float) sensorTemperature.temperature();
    }

    @Override
    public Float getHumidity() {
        return null;
    }

    @Override
    public Float getPressure() {
        return null;
    }

    @Override
    public LocalDateTime getDateTime() {
        LocalDate date = LocalDate.ofYearDay(sensorTemperature.year(), sensorTemperature.day());
        LocalTime time = LocalTime.ofSecondOfDay(sensorTemperature.second());
        return LocalDateTime.of(date, time);
    }
}
