package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lsp.RulerView;
import com.shawnlin.numberpicker.NumberPicker;



public class HomePage extends AppCompatActivity {


    boolean genderBackgroundSelected  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ImageView WomenImage = findViewById(R.id.female);
        ImageView menImage = findViewById(R.id.male);
        WomenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(genderBackgroundSelected == false){
                    Drawable drawableBackground = getResources().getDrawable(R.drawable.circle);
                    WomenImage.setBackground(drawableBackground);
                    genderBackgroundSelected = true;
                }
                else{
                    menImage.setBackground(null);
                    Drawable drawableBackground = getResources().getDrawable(R.drawable.circle);
                    WomenImage.setBackground(drawableBackground);
                    genderBackgroundSelected = true;
                }
            }
        });
        menImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(genderBackgroundSelected == false){
                    Drawable drawableBackground = getResources().getDrawable(R.drawable.circle);
                    menImage.setBackground(drawableBackground);
                }
                else {
                    WomenImage.setBackground(null);
                    Drawable drawableBackground = getResources().getDrawable(R.drawable.circle);
                    menImage.setBackground(drawableBackground);
                    genderBackgroundSelected = true;
                }
            }
        });

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!genderBackgroundSelected){
                    Toast.makeText(HomePage.this, "Please Select Gender First to Proceed", Toast.LENGTH_SHORT).show();
                }

                else {
                    Dialog dialog = new Dialog(HomePage.this);
                    dialog.setContentView(R.layout.bmi_calulator_dialog_box);

                    RulerView rulerView = (RulerView) findViewById(R.id.rulerView);
                    NumberPicker WeightnumberPicker = findViewById(R.id.weight);
                    NumberPicker AgenumberPicker = findViewById(R.id.age);

                    double Height = (double) rulerView.currentScale;
                    double Weight = (double) WeightnumberPicker.getValue();
                    double Age = (double) AgenumberPicker.getValue();

                    double calculatedBMI = ((double) Weight / (double) (Height * Height)) * 10000;

                    TextView BMi = dialog.findViewById(R.id.BMI);
                    BMi.setText("" + String.format("%.2f", calculatedBMI) + " kg/m²");
                    TextView Normality = dialog.findViewById(R.id.Normality);
                    TextView Description = dialog.findViewById(R.id.Description);
                    if(calculatedBMI < 18.50){
                        Normality.setText("Underweight");
                        Description.setText("A BMI of less than 18.5 is considered underweight. People who are underweight may be at risk for health problems such as malnutrition, osteoporosis, and anemia.");
                    }
                    else if(calculatedBMI > 18.50 && calculatedBMI <=24.90 ){
                        Normality.setText("Normal weight");
                        Description.setText("A BMI of 18.5 to 24.9 is considered normal weight. People who are at a normal weight are at the lowest risk for developing chronic diseases such as heart disease, stroke, type 2 diabetes, and some types of cancer.");
                    }
                    else if(calculatedBMI > 25.00 && calculatedBMI <= 30.00 ){
                        Normality.setText("Overweight");
                        Description.setText("A BMI of 25 to 29.9 is considered overweight. People who are overweight are at increased risk for developing chronic diseases such as heart disease, stroke, type 2 diabetes, and some types of cancer.");
                    }
                    else{
                        Normality.setText("Obese");
                        Description.setText("A BMI of 30 or greater is considered obese. People who are obese are at a very high risk for developing chronic diseases such as heart disease, stroke, type 2 diabetes, and some types of cancer.");
                    }

                    dialog.show();
                }
            }
        });
    }

}