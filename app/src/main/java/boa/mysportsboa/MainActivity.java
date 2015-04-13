package boa.mysportsboa;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import boa.mysportsboa.Adapter.HorizontalListView;


public class MainActivity extends ActionBarActivity {

    private static String[] dataEventos;
    private static String[] dataEquipos;
    private MainActivity miClase = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources _res = getResources();
        dataEventos = _res.getStringArray(R.array.items_eventos);
        dataEquipos = _res.getStringArray(R.array.items_equipos);

        HorizontalListView listviewEventos = (HorizontalListView) findViewById(R.id.listEventos);
        listviewEventos.setAdapter(new HAdapterEventos());

        HorizontalListView listviewEquipos = (HorizontalListView) findViewById(R.id.listEquipos);
        listviewEquipos.setAdapter(new HAdapterEquipos());
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
}
