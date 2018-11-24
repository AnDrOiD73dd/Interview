package ru.geekbrains.converter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConvertActivity extends AppCompatActivity {

    private EditText sourceText;    // Элемент с входным значением, которое надо сконвертировать
    private EditText destText;      // Элемент с результирующим значением
    private EditText kmPerHourValueView;
    private Button kmPerHourButton;
    private EditText kmPerHourResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        sourceText = (EditText) findViewById(R.id.celsiusValue);
        destText = (EditText) findViewById(R.id.fahrenheitValue);

        kmPerHourValueView = findViewById(R.id.et_kilometers_per_hour_value);
        kmPerHourButton = findViewById(R.id.bt_convert_to_meters_per_second);
        kmPerHourResultView = findViewById(R.id.et_meters_per_second_value);
    }

    // обработка нажатия
    public void onToFahrenheitClick(View view) {
        // получить входное значение
        float sourceValue = Float.parseFloat(sourceText.getText().toString());
        // инстанцировать конвертер
        Converter converter = new Converter(sourceValue);
        // преобразовать, обратите внимание на параметр ConvertToFahrenheit
        float destValue = converter.Convert(new ConvertToFahrenheit()).GetResult();
        // записать результат в элемент
        destText.setText(String.format("%.02f", destValue));
    }

    public void onToMetersPerSecondClick(View view) {
        float sourceValue = Float.parseFloat(kmPerHourValueView.getText().toString());
        Converter converter = new Converter(sourceValue);
        float destValue = converter.Convert(new ConvertToMetersPerSecond()).GetResult();
        kmPerHourResultView.setText(String.format("%.02f", destValue));
    }
}
