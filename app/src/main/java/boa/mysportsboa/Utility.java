package boa.mysportsboa;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * Created by Gary on 12/04/15.
 */
public class Utility {
    private static final String LOG_TAG = Utility.class.getSimpleName();

    public static String getDataInitFromNetwork(String dato) {
        Log.d(LOG_TAG, "Starting network connection");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        //String timeFrame = "p" + days;

        try {
            //final String FIXTURE_BASE_URL = "http://10.0.0.5/mysportsapi/api/"+ dato;
            final String FIXTURE_BASE_URL = "http://192.168.17.23/mysportsapi/api/"+ dato;
            Uri builtUri = Uri.parse(FIXTURE_BASE_URL).buildUpon()
                    .build();
            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null)
                return "";
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0)
                return "";

            return buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error al conectar---> "+e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

    public static EventoModel[] parseEventJson(String fixtureJson) throws JSONException {
        JSONObject jsonObject;
        jsonObject = new JSONObject(fixtureJson);
        ArrayList<EventoModel> result = new ArrayList<EventoModel>();

        final String DESCRIPCION="descripcion";
        final String DEPORTE ="nombreDeporte";
        final String EVENTO_ID="eventoID";

        JSONArray eventsArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < eventsArray.length(); i++) {
            String descripcion;
            String deporte;
            Integer id;
            JSONObject matchObject = eventsArray.getJSONObject(i);

            descripcion = matchObject.getString(DESCRIPCION);
            deporte = matchObject.getString(DEPORTE);
            id=matchObject.getInt(EVENTO_ID);

            //String resultString = new Formatter().format("%s", descripcion).toString();
            result.add(new EventoModel(id,descripcion, deporte));
        }
        return result.toArray(new EventoModel[result.size()]);
    }
    public static EquipoModel[] parseTeamJson(String url) throws JSONException {
        JSONObject teamObject;
        teamObject = new JSONObject(url);
        ArrayList<EquipoModel> result = new ArrayList<EquipoModel>();

        final String NOMBRE="nombreEquipo";
        final String EQUIPO_ID="equipoID";

        JSONArray eventsArray = teamObject.getJSONArray("data");

        for (int i = 0; i < eventsArray.length(); i++) {
            String nombre;
            Integer id;
            JSONObject matchObject = eventsArray.getJSONObject(i);

            nombre = matchObject.getString(NOMBRE);
            id=matchObject.getInt(EQUIPO_ID);
            result.add(new EquipoModel(id,nombre));
        }
        return result.toArray(new EquipoModel[result.size()]);
    }

    public static FixtureModel[] parseFixtureJson(String url) throws JSONException {
        JSONObject teamObject;
        teamObject = new JSONObject(url);
        ArrayList<FixtureModel> result = new ArrayList<FixtureModel>();

        final String DESCRIPCION_F="descripcion";
        final String FIXTURE_ID="fixtureID";
        //final String FECHA="fechaProgramada";

        JSONArray fistureArray = teamObject.getJSONArray("data");

        for (int i = 0; i < fistureArray.length(); i++) {
            String nombre;
            Integer id;
            String fecha;

            JSONObject matchObject = fistureArray.getJSONObject(i);

            id=matchObject.getInt(FIXTURE_ID);
            //fecha=matchObject.getString(FECHA);
            nombre = matchObject.getString(DESCRIPCION_F);

            result.add(new FixtureModel(id,nombre,""));
        }
        return result.toArray(new FixtureModel[result.size()]);
    }
}
