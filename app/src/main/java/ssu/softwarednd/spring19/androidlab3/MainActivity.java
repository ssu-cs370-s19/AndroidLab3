package ssu.softwarednd.spring19.androidlab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ssu.softwarednd.spring19.androidlab3.network.RecipeSearchAsyncTask;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText searchEditText;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEditText = findViewById(R.id.response_text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new task
                RecipeSearchAsyncTask task = new RecipeSearchAsyncTask();

                //create a Listener! (and add it to the task)
                task.setMyRecipie(new RecipeSearchAsyncTask.RecipeListener() {
                    @Override
                    public void onRecipeCallback(String response) {
                        //show response on the screen (in a text view)
                        responseText.setText(response);


                    }
                });
                //start the task
                String searchTerm = searchEditText.getText().toString();
                task.execute(searchTerm);

            }


        });
    }
}