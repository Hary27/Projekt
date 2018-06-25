package warstwa_biznesowa;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import warstwa_biznesowa.dto.Produkt_dto;
import warstwa_biznesowa.entity.Produkt1;

public class Fasada_warstwy_biznesowej {

    public void
            produkty_z_bazy_danych(List<Produkt1> produkty_) {
        produkty.clear();
        produkty.addAll(produkty_);
    }

    public void remove(Produkt_dto p) {
        Produkt1 produkt = wykonaj_produkt(p);
        getProdukty().remove(produkt);
    }

    static long klucz = 0;
    private ArrayList<Produkt1> produkty = new ArrayList();
    boolean stan = false;

    public ArrayList<Produkt1> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt1> produkty) {
        this.produkty = produkty;
    }

    public void utworz_produkt(Produkt_dto produkt_dto) {
        Produkt1 produkt = wykonaj_produkt(produkt_dto);
        dodaj_produkt(produkt);
    }

    Produkt1 wykonaj_produkt(Produkt_dto produkt_dto) {
        Produkt1 produkt = new Produkt1();

        produkt.setNazwa(produkt_dto.getNazwa());
        produkt.setCena(produkt_dto.getCena());
        produkt.setImie(produkt_dto.getImie());
        produkt.setNazwisko(produkt_dto.getNazwisko());
        produkt.setMiasto(produkt_dto.getMiasto());
        produkt.setUlica(produkt_dto.getUlica());
        produkt.setData_zakupu(produkt_dto.getData_zakupu());
        produkt.setData_dostarczenia(produkt_dto.getData_dostarczenia());
        return produkt;
    }

    void max_klucz() {
        long max = 0;
        for (Produkt1 p : produkty) {
            if (p.getId() > max) {
                max = p.getId();
            }
        }
        klucz = max + 1;
    }

    public boolean edit(Produkt_dto o_przed, Produkt_dto o_update) {
        int idx1, idx2;
        stan = true;
        idx1 = this.istnieje_produkt(o_przed);
        if (idx1 == -1) //taki produkt do edycji nie istnieje
        {
            return false;
        }
        idx2 = this.istnieje_produkt(o_update);
        if (idx2 != -1) //nie mozna modyfikowac, bo już taki produkt istnieje
        {
            return false;
        }
        Produkt1 p = getProdukty().get(idx1);
        p.setCena(o_update.getCena());
        p.setImie(o_update.getImie());
        p.setNazwisko(o_update.getNazwisko());
        p.setMiasto(o_update.getMiasto());
        p.setUlica(o_update.getUlica());
        p.setData_zakupu(o_update.getData_zakupu());
        p.setData_dostarczenia(o_update.getData_dostarczenia());
        return true;
    }

    protected void dodaj_produkt(Produkt1 produkt) {
        if (!produkty.contains(produkt)) {
            produkty.add(produkt);
            stan = true;
        } else {
            stan = false;
        }
    }

    public Produkt_dto dane_produktu() {
        if (stan) {
            Produkt1 produkt = produkty.get(produkty.size() - 1);
            return produkt_transfer(produkt);
        }
        return null;
    }

    public Produkt_dto produkt_transfer(Produkt1 produkt) {
        Produkt_dto pom = new Produkt_dto();
        pom.setId(produkt.getId_());
        pom.setNazwa(produkt.getNazwa());
        pom.setCena(produkt.getCena());
        pom.setImie(produkt.getImie());
        pom.setNazwisko(produkt.getNazwisko());
        pom.setMiasto(produkt.getMiasto());
        pom.setUlica(produkt.getUlica());
        pom.setData_zakupu(produkt.getData_zakupu());
        pom.setData_dostarczenia(produkt.getData_dostarczenia());
        return pom;
    }

    public ArrayList<Produkt_dto> items_() {
        ArrayList<Produkt_dto> dane = new ArrayList();
        for (Produkt1 produkt : produkty) {
            dane.add(produkt_transfer(produkt));
        }
        return dane;
    }

    public ArrayList<ArrayList<String>> items() {
        ArrayList<ArrayList<String>> dane = new ArrayList();
        for (Produkt1 p : produkty) {
            ArrayList<String> wiersz = new ArrayList();
            wiersz.add(p.getId().toString());
            wiersz.add(p.getNazwa());
            wiersz.add("" + p.getCena());
            wiersz.add(p.getImie());
            wiersz.add(p.getNazwisko());
            wiersz.add(p.getMiasto());
            wiersz.add(p.getUlica());
            wiersz.add(p.getData_zakupu().toString());
  //          wiersz.add("1-1-1");
            wiersz.add(p.getData_dostarczenia().toString());
//            wiersz.add("2-2-2");
            

            dane.add(wiersz);

        }
        return dane;
    }

    public boolean isStan() {
        return stan;
    }

    public void setStan(boolean stan) {
        this.stan = stan;
    }

    public int count() {
        return produkty.size();
    }

    public ArrayList<Produkt_dto> findRange(int[] range) {
        ArrayList<Produkt_dto> pom = new ArrayList();
        if (getProdukty().isEmpty()) {
            stan = false;
            return pom;
        }
        for (int i = range[0]; i < range[1]; i++) {
            pom.add(produkt_transfer(getProdukty().get(i)));
        }
        return pom;
    }

    int istnieje_produkt(Produkt_dto pdto) {
        Produkt1 pom1 = this.wykonaj_produkt(pdto);
        return getProdukty().indexOf(pom1);
    }

}
