package boa.mysportsboa;

/**
 * Created by Gary on 12/04/15.
 */
public class EventoModel {
    public Integer eventoID;
    public String nombreEvento;
    public String deporteTipo;
    public EventoModel(Integer id, String nombre, String deporte) {
        eventoID=id;
        nombreEvento=nombre;
        deporteTipo=deporte;
    }
}
