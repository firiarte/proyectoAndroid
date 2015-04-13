package boa.mysportsboa;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boa.mysportsboa.Adapter.HorizontalListView;


public class MainActivity extends ActionBarActivity {
    private static String LOG_TAG="MAIN ACTIVITY";

    private MainActivity miClase = this;

    private static String[] dataEventos;
    private static String[] dataEquipos;
    private static String[] dataFixtures;


    public static List<String> stringLista;
    private static Object[] objectArray;

    private static EventoModel[] DataEventosModels;
    private static EquipoModel[] DataEquiposModels;
    private static FixtureModel[] DataFixturesModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Resources _res = getResources();
        stringLista= new ArrayList<>();
        /**
         * Cargamos los eventos
         */
        GetDataTask taskEvent = new GetDataTask();
        try {
            objectArray=taskEvent.execute("event").get();
            DataEventosModels = Arrays.copyOf(objectArray,objectArray.length,EventoModel[].class);
            for(EventoModel event : DataEventosModels)
            {
                stringLista.add(event.nombreEvento);
            }
            dataEventos = stringLista.toArray(new String[stringLista.size()]);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Main Activity-Events" + e.getMessage(), e);
            dataEventos = _res.getStringArray(R.array.items_eventos);
        }
        /**
         * Cargamos los Equipos
         */
        GetDataTask taskTeam = new GetDataTask();
        stringLista= new ArrayList<>();
        try {
            objectArray=taskTeam.execute("team").get();
            DataEquiposModels = Arrays.copyOf(objectArray,objectArray.length,EquipoModel[].class);
            for(EquipoModel equipo : DataEquiposModels)
            {
                stringLista.add(equipo.nombre);
            }
            dataEquipos = stringLista.toArray(new String[stringLista.size()]);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Main Activity-Teams;" + e.getMessage(), e);
            dataEquipos = _res.getStringArray(R.array.items_equipos);
        }
        /**
         * Cargamos los Fixtures
         */
        GetDataTask taskFixture = new GetDataTask();
        stringLista= new ArrayList<>();
        try {
            objectArray=taskFixture.execute("fixture").get();
            DataFixturesModels = Arrays.copyOf(objectArray,objectArray.length,FixtureModel[].class);
            for(FixtureModel fixture : DataFixturesModels)
            {
                stringLista.add(fixture.descripcion);
            }
            dataFixtures = stringLista.toArray(new String[stringLista.size()]);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Main Activity-Fixtures;" + e.getMessage(), e);
        }


        HorizontalListView listviewEventos = (HorizontalListView) findViewById(R.id.listEventos);
        listviewEventos.setAdapter(new HAdapterEventos());

        HorizontalListView listviewEquipos = (HorizontalListView) findViewById(R.id.listEquipos);
        listviewEquipos.setAdapter(new HAdapterEquipos());

        HorizontalListView listviewFixtures = (HorizontalListView) findViewById(R.id.listFixtures);
        listviewFixtures.setAdapter(new HAdapterFixtures());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /**
     * ADAPTER DE EVENTOS.
     */
    private class  HAdapterEventos extends BaseAdapter {

        public HAdapterEventos(){

            super();
        }
        private View.OnClickListener mOnButtonClicked = new View.OnClickListener() {

            public void onClick(View v) {

                Button bt = (Button) v;
                Toast.makeText(getApplicationContext(), "EVENTO:  " + bt.getText(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(miClase, eventoActivity.class);
                i.putExtra("tituloEvento", bt.getText().toString());
                startActivity(i);
            }
        };


        public int getCount() {
            return dataEventos.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main, null);
            ImageView im = (ImageView) retval.findViewById(R.id.icon_dato);
            Button button = (Button) retval.findViewById(R.id.btn_dato);



            if(position == 0)
                im.setBackgroundResource(R.mipmap.evetfut);
            else
                im.setBackgroundResource(R.mipmap.eventbas);


            button.setText(dataEventos[position]);
            button.setOnClickListener(mOnButtonClicked);

            return retval;
        }

    };

    /**
     * ADAPTER DE EQUIPOS.
     */
    private class  HAdapterEquipos extends BaseAdapter {

        public HAdapterEquipos(){

            super();
            //this.EsEvento = esEvento;
        }
        private View.OnClickListener mOnButtonClicked = new View.OnClickListener() {

            public void onClick(View v) {
                Button bt = (Button) v;

                //Toast.makeText(getApplicationContext(), "EQUIPO:  " + bt.getText(), Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "EQUIPO:  " + bt.getText(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(miClase, equipoActivity.class);
                i.putExtra("tituloEquipo", bt.getText().toString());
                startActivity(i);
            }
        };


        public int getCount() {
            return dataEquipos.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main, null);
            ImageView im = (ImageView) retval.findViewById(R.id.icon_dato);
            Button button = (Button) retval.findViewById(R.id.btn_dato);


            if(position == 0)
                im.setBackgroundResource(R.mipmap.eq1sistemas);
            else if(position == 1)
                im.setBackgroundResource(R.mipmap.eq2operaciones);
            else if(position == 2)
                im.setBackgroundResource(R.mipmap.eq3atocbb);
            else if(position == 3)
                im.setBackgroundResource(R.mipmap.eq4talleres);
            else if(position == 4)
                im.setBackgroundResource(R.mipmap.eq5gaf);
            else if(position == 5)
                im.setBackgroundResource(R.mipmap.eq6comercial);
            else
                im.setBackgroundResource(R.mipmap.eq7mm);


            button.setText(dataEquipos[position]);
            button.setOnClickListener(mOnButtonClicked);

            return retval;
        }

    };
    /**
     * ADAPTER DE FIXTURES.
     */
    private class  HAdapterFixtures extends BaseAdapter {

        public HAdapterFixtures(){

            super();
            //this.EsEvento = esEvento;
        }
        private View.OnClickListener mOnButtonClicked = new View.OnClickListener() {

            public void onClick(View v) {
                Button bt = (Button) v;

                Toast.makeText(getApplicationContext(), "En construcción...", Toast.LENGTH_SHORT).show();
            }
        };


        public int getCount() {
            return dataFixtures.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main, null);
            ImageView im = (ImageView) retval.findViewById(R.id.icon_dato);
            Button button = (Button) retval.findViewById(R.id.btn_dato);
            im.setBackgroundResource(R.mipmap.iconfixture2);


            button.setText(dataFixtures[position]);
            button.setOnClickListener(mOnButtonClicked);

            return retval;
        }

    };
}
