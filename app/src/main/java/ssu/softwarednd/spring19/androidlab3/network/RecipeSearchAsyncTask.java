package ssu.softwarednd.spring19.androidlab3.network;

import android.os.AsyncTask;

import ssu.softwarednd.spring19.androidlab3.utility.RecipeSearchHelper;
import ssu.softwarednd.spring19.androidlab3.utility.RecipeParser;

public class RecipeSearchAsyncTask extends AsyncTask<String, Void, String> {
// AsyncTask is a templated class: AsyncTask<PARAMS, PROGRESS, RESULT>
// PARAMS is the datatype that we pass to execute()
//      a String we get from the users input (in MainActivity)
// PROGRESS is the datatype we use to report updates
//      not used in this lab -> Void (aka null)
// RESULT is the datatype that we send back on completion of this task
//      a String containing a message to display for the user

    public void setRecipeListener(RecipeListener recipeListener) {
        this.recipeListener = recipeListener;
    }

    private RecipeListener recipeListener;


    @Override
    protected String doInBackground(String... params) {
        // runs on a background thread, not blocking main

        String searchTerm = params[0];  // what we're searching for

        // get the json response from Yummly's Recipe API
        String responseJson = RecipeSearchHelper.searchRecipes(searchTerm);

        // success? :)
        if (responseJson != null) {
            int numMatches = RecipeParser.countMatches(responseJson);
            return "Number of matches: " + numMatches;
        }
        // no success :(
        return "Search Failed";
    }

    @Override
    protected void onPostExecute(String result) {
        // happens after doInBackground, and runs on main thread
        super.onPostExecute(result);
        recipeListener.onRecipeCallback(result);
    }



    public interface RecipeListener {
        void onRecipeCallback(String response);
    }

}
