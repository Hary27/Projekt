package warstwa_biznesowa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Produkt1 implements Serializable {

    public Long getId_() {
        if (id == null) {
            return new Long(0);
        }
        return id;
    }

    @Id
    private Long id;
    private String nazwa;
    private float cena;
    private String imie;
    private String nazwisko;
    private String miasto;
    private String ulica;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date data_zakupu;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date data_dostarczenia;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produkt1 other = (Produkt1) obj;
        if (Float.floatToIntBits(this.cena) != Float.floatToIntBits(other.cena)) {
            return false;
        }
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warstwa_biznesowa.entity.Produkt1[ id=" + id + " ]";
    }

}
