package boa.mysportsboa;

/**
 * Created by Gary on 12/04/15.
 */
public class FixtureModel {
    public Integer id;
    public String descripcion;
    public String fecha;
    public FixtureModel(Integer idFixture,String desc,String date){
        id=idFixture;
        descripcion=desc;
        fecha=date;
    }
}
