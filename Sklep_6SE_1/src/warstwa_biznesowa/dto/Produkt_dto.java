package warstwa_biznesowa.dto;

import java.io.Serializable;
import java.util.Date;

public class Produkt_dto implements Serializable {

    protected long id;
    protected String nazwa;
    protected float cena;
    protected String imie;
    protected String nazwisko;
    protected String miasto;
    protected String ulica;
    protected Date data_zakupu;
    protected Date data_dostarczenia;

    public Produkt_dto() {
    }

    public Produkt_dto(String[] dane, Date data1, Date data2) {
        nazwa = dane[0];
        cena = Float.parseFloat(dane[1]);
        imie = dane[2];
        nazwisko = dane[3];
        miasto = dane[4];
        ulica = dane[5];
        data_zakupu = data1;
        data_dostarczenia = data2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public Date getData_zakupu() {
        return data_zakupu;
    }

    public void setData_zakupu(Date data_zakupu) {
        this.data_zakupu = data_zakupu;
    }
    public Date getData_dostarczenia() {
        return data_dostarczenia;
    }

    public void setData_dostarczenia(Date data_dostarczenia) {
        this.data_dostarczenia = data_dostarczenia;
    }

    public Produkt_dto(Produkt_dto o) {
        nazwa = o.getNazwa();
        cena = o.getCena();
        imie = o.getImie();
        nazwisko = o.getNazwisko();
        miasto = o.getMiasto();
        ulica = o.getUlica();
        data_zakupu = o.getData_zakupu();
        data_dostarczenia = o.getData_dostarczenia();
    }

}
