package boa.mysportsboa;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class equipoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

        String datoEntrante = getIntent().getStringExtra("tituloEquipo");

        TextView textView = (TextView)findViewById(R.id.titleEquipo);
        textView.setText(datoEntrante);

        ImageView imgView = (ImageView)findViewById(R.id.icon_equipo);

        if(datoEntrante.equals("Sistemas FC"))
            imgView.setBackgroundResource(R.mipmap.eq1sistemas);
        else if(datoEntrante.equals("Operaciones"))
            imgView.setBackgroundResource(R.mipmap.eq2operaciones);
        else if(datoEntrante.equals("Aeropuerto CBB"))
            imgView.setBackgroundResource(R.mipmap.eq3atocbb);
        else if(datoEntrante.equals("GAF"))
            imgView.setBackgroundResource(R.mipmap.eq4talleres);
        else if(datoEntrante.equals("Tallereres"))
            imgView.setBackgroundResource(R.mipmap.eq5gaf);
        else if(datoEntrante.equals("Comercial"))
            imgView.setBackgroundResource(R.mipmap.eq6comercial);
        else
            imgView.setBackgroundResource(R.mipmap.eq7mm);

        ListView listJugadores = (ListView) findViewById(R.id.listViewJugadores);
        Resources _res = getResources();

        String[] listEqipos = _res.getStringArray(R.array.items_jugadores);
        ArrayAdapter<String> eqps = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listEqipos);
        listJugadores.setAdapter(eqps);

        listJugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "JUGADOR LA POSICION  :  " + position, Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_equipo, menu);
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
}
