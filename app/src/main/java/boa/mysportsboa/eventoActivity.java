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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import boa.mysportsboa.Adapter.HorizontalListView;


public class eventoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

        String datoEntrante = getIntent().getStringExtra("tituloEvento");

        TextView textView = (TextView)findViewById(R.id.titleEvento);
        textView.setText(datoEntrante);

        ImageView imgView = (ImageView)findViewById(R.id.icon_evento);

        if(datoEntrante.equals("Integración BoA 2015"))
            imgView.setBackgroundResource(R.mipmap.evetfut);
        else
            imgView.setBackgroundResource(R.mipmap.eventbas);

        ListView listEventos = (ListView) findViewById(R.id.listViewEquipos);
        Resources _res = getResources();

        String[] listEqipos = _res.getStringArray(R.array.items_equipos);
        ArrayAdapter<String> eqps = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listEqipos);
        listEventos.setAdapter(eqps);

        listEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "EQUIPO DE LA POSICION  :  " + position, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_evento, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_evento, container, false);


            TextView textView = (TextView)rootView.findViewById(R.id.titleEvento);
            textView.setText(getActivity().getIntent().getStringExtra("tituloEvento"));

            return rootView;
        }
    }
}
