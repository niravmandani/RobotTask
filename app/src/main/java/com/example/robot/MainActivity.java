package com.example.robot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.robot.enums.Direction;

public class MainActivity extends AppCompatActivity {

    private EditText gridInput, robotPositionInput, navigationInput;
    private TextView resultOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gridInput = findViewById(R.id.grid_input);
        robotPositionInput = findViewById(R.id.robot_position_input);
        navigationInput = findViewById(R.id.navigation_input);
        resultOutput = findViewById(R.id.result_output);
        Button executeButton = findViewById(R.id.execute_button);

        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCommand();
            }
        });
    }

    private void executeCommand() {
        try {
            String[] gridSize = gridInput.getText().toString().trim().split(" ");
            if (gridSize.length < 2) {
                Toast.makeText(MainActivity.this, "Please enter valid grid input", Toast.LENGTH_SHORT).show();
                return;
            }
            int maxX = Integer.parseInt(gridSize[0]);
            int maxY = Integer.parseInt(gridSize[1]);

            String[] robotPosition = robotPositionInput.getText().toString().trim().split(" ");
            if (robotPosition.length < 2) {
                Toast.makeText(MainActivity.this, "Please enter valid robot position", Toast.LENGTH_SHORT).show();
                return;
            }
            int startX = Integer.parseInt(robotPosition[0]);
            int startY = Integer.parseInt(robotPosition[1]);
            if (robotPosition.length < 3) {
                Toast.makeText(MainActivity.this, "Please enter valid direction", Toast.LENGTH_SHORT).show();
                return;
            }
            Direction startDirection = Direction.valueOf(robotPosition[2]);

            String navigationCommands = navigationInput.getText().toString().trim();


            Robot robot = new Robot(startX, startY, startDirection, maxX, maxY);
            for (char command : navigationCommands.toCharArray()) {
                robot.executeCommand(command);
            }
            resultOutput.setText("Result: " + robot.getPosition());
        } catch (Exception e) {
            resultOutput.setText("Error: " + e.getMessage());
        }
    }
}