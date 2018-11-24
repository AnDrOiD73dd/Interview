package ru.geekbrains.converter;

public class ConvertToMetersPerSecond implements ConvertTo {
    @Override
    public float Do(float sourceValue) {
        return (sourceValue * 1000)/3600;
    }
}
