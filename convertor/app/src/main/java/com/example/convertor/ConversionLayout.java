package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ConversionLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_layout);
        Intent intent = getIntent();
        String adapterType = intent.getStringExtra("adapterType");

        Spinner FromSpinner = findViewById(R.id.FromSpinner);
        Spinner ToSpinner = findViewById(R.id.ToSpinner);

        TextView converterName = findViewById(R.id.converterName);
        converterName.setText(adapterType + " Converter");

        if(adapterType.equals("Volume")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Volume, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("Weight")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Weight, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("Mass")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Mass, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("FriendShip")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Weight, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("BMI")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Weight, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("Length")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Length, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("Time Unit")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.TimeUnit, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }
        if(adapterType.equals("Age")){
            ConstraintLayout constraintLayout8 = findViewById(R.id.constraintLayout8);
            constraintLayout8.setVisibility(View.GONE);
            ConstraintLayout constraintLayout = findViewById(R.id.SpinnersLayout);
            constraintLayout.setVisibility(View.GONE);
            LinearLayout InputBoxLinearLayout = findViewById(R.id.InputBoxLinearLayout);
            TextView ResultTextView = findViewById(R.id.ResultTextView);
            ResultTextView.setText("Enter Data To Calculate Your Age");
            InputBoxLinearLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Weight, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);

        }
        if(adapterType.equals("Time Country")){
            ConstraintLayout AgeCalculatorLayout = findViewById(R.id.AgeCalculatorLayout);
            AgeCalculatorLayout.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.TimeCountry, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            FromSpinner.setAdapter(arrayAdapter);
            ToSpinner.setAdapter(arrayAdapter);
        }

        Button convertButton = findViewById(R.id.ConvertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapterType.equals("Volume")){
                    EditText InputText1 = findViewById(R.id.InputText1);

                    try {
                        int value = Integer.parseInt(String.valueOf(InputText1.getText()));
                        if(FromSpinner.getSelectedItem().equals("Cubic Meter") && ToSpinner.getSelectedItem().equals("Cubic Kilometer")){

                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            float inputValue = Float.parseFloat(String.valueOf(InputText1.getText()));
                            float formula = inputValue *  1000000000;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + String.format("%.2f", formula));
                        }
                        else if(FromSpinner.getSelectedItem().equals("Cubic Kilometer") && ToSpinner.getSelectedItem().equals("Cubic Meter")){

                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            float inputValue = Float.parseFloat(String.valueOf(InputText1.getText()));
                            float formula = inputValue /  1000000000;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + String.format("%.2f", formula));
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if(adapterType.equals("Weight")){
                    EditText InputText1 = findViewById(R.id.InputText1);

                    try {
                        int value = Integer.parseInt(String.valueOf(InputText1.getText()));
                        if(FromSpinner.getSelectedItem().equals("KG") && ToSpinner.getSelectedItem().equals("LBS")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue /  2;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                        if(FromSpinner.getSelectedItem().equals("LBS") && ToSpinner.getSelectedItem().equals("KG")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue *  0.45;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if(adapterType.equals("Mass")){
                    EditText InputText1 = findViewById(R.id.InputText1);
                    try {
                        int value = Integer.parseInt(String.valueOf(InputText1.getText()));
                        if(FromSpinner.getSelectedItem().equals("Kilo") && ToSpinner.getSelectedItem().equals("Grams")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue *  1000;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                        if(FromSpinner.getSelectedItem().equals("Grams") && ToSpinner.getSelectedItem().equals("Kilo")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue /  1000;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if(adapterType.equals("Length")){
                    EditText InputText1 = findViewById(R.id.InputText1);
                    try {
                        int value = Integer.parseInt(String.valueOf(InputText1.getText()));
                        if(FromSpinner.getSelectedItem().equals("CentiMeters") && ToSpinner.getSelectedItem().equals("Meters")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue *  100;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                        if(FromSpinner.getSelectedItem().equals("Meters") && ToSpinner.getSelectedItem().equals("CentiMeters")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue /  100;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if(adapterType.equals("Time Country")){
                    EditText InputText1 = findViewById(R.id.InputText1);
                    try {
                        int value = Integer.parseInt(String.valueOf(InputText1.getText()));
                        if(FromSpinner.getSelectedItem().equals("Pakistan") && ToSpinner.getSelectedItem().equals("Belgium")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue +  4;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                        if(FromSpinner.getSelectedItem().equals("Belgium") && ToSpinner.getSelectedItem().equals("Pakistan")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            double formula = inputValue -  4;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if(adapterType.equals("Age")){
                    try {
                        EditText DayInput = findViewById(R.id.DayInput);
                        EditText MonthInput = findViewById(R.id.MonthInput);
                        EditText YearInput = findViewById(R.id.YearInput);

                        int day = Integer.parseInt(DayInput.getText().toString());
                        int month = Integer.parseInt(MonthInput.getText().toString());
                        int year = Integer.parseInt(YearInput.getText().toString());

                        LocalDate currentDate = LocalDate.now();
                        int ageInYears = currentDate.getYear() - year;
                        int ageInMonths = (ageInYears * 12) + (currentDate.getDayOfMonth() - month);
                        int ageInWeeks = (ageInYears * 52) + ((currentDate.getDayOfMonth() - day)/7);

                        TextView ResultTextView = findViewById(R.id.ResultTextView);
                        ResultTextView.setText("Your Age in Years is " + ageInYears + "\n" + "Your Age in Months is " + ageInMonths + "\n" + "Your Age in Weeks is " + ageInWeeks);
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                else if(adapterType.equals("Time Unit")){
                    EditText InputText1 = findViewById(R.id.InputText1);
                    try {
                        int value = Integer.parseInt(String.valueOf(InputText1.getText()));
                        if(FromSpinner.getSelectedItem().equals("Hours") && ToSpinner.getSelectedItem().equals("Minutes")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            int inputValue = Integer.parseInt(String.valueOf(InputText1.getText()));
                            int formula = inputValue *  60;
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + formula);
                        }
                        if(FromSpinner.getSelectedItem().equals("Minutes") && ToSpinner.getSelectedItem().equals("Hours")){
                            TextView ResultTextView = findViewById(R.id.ResultTextView);
                            double inputValue = Double.parseDouble(String.valueOf(InputText1.getText()));
                            DecimalFormat decimalFormat = new DecimalFormat("#.#");
                            double formula = inputValue /  60;
                            String FormatedResult = decimalFormat.format(formula);
                            ResultTextView.setText(value + " " + FromSpinner.getSelectedItem() + " in " + ToSpinner.getSelectedItem() + " are " + FormatedResult);
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(ConversionLayout.this, "Exception" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}