package boa.mysportsboa;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONException;

/**
 * Created by Gary on 12/04/15.
 */
public class GetDataTask extends AsyncTask<String, Integer, Object[]> {
    private static final String LOG_TAG = GetDataTask.class.getSimpleName();
    private ArrayAdapter<String> arrayAdapter;
@Override
protected Object[] doInBackground(String... params) {

    Utility utils= new Utility();
    String resultString;
    if(params[0]=="event") {
        resultString = utils.getDataInitFromNetwork("Evento");
        try {
            return utils.parseEventJson(resultString);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing Evento :" + e.getMessage(), e);
            e.printStackTrace();
            return new String[]{"No DATA"};
        }
    }else if(params[0]=="team") {
        resultString = utils.getDataInitFromNetwork("Equipo");
        try {
            return utils.parseTeamJson(resultString);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing Equipo :" + e.getMessage(), e);
            e.printStackTrace();
            return new String[]{"No DATA"};
        }
    }else if(params[0]=="fixture"){
        resultString = utils.getDataInitFromNetwork("Fixture");
        try {
            return utils.parseFixtureJson(resultString);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing Fixture :" + e.getMessage(), e);
            e.printStackTrace();
            return new String[]{"No DATA"};
        }
    };
    return new String[]{"No DATA"};
}

//@Override
//protected void onPostExecute(String[] strings) {
 //   try
  //      arrayAdapter.clear();
  //      for (String result : strings) {
  //      arrayAdapter.add(result);
  //      }
  //      }
}
