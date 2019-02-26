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
        button =findViewById(R.id.button);
        searchEditText =findViewById(R.id.search_edit_text);
        responseText =findViewById(R.id.response_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeSearchAsyncTask task = new RecipeSearchAsyncTask();

                task.setResListner(new RecipeSearchAsyncTask.RecipeListener() {
                    @Override
                    public void onRecipeCallback(String response) {

                        responseText.setText(response);
                    }
                });
                String searchTerm = searchEditText.getText().toString();
                task.execute(searchTerm);
            }
        });
    }
}
