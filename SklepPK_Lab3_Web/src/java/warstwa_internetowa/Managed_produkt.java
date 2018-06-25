package warstwa_internetowa;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pomoc.JsfUtil;
import pomoc.PaginationHelper;
import pomoc.Zmiana_danych;
import warstwa_biznesowa.dto.Produkt_dto;
import warstwa_biznesowa_ejb.Fasada_warstwy_biznesowej_ejbRemote;

@Named(value = "managed_produkt")
@SessionScoped
public class Managed_produkt implements ActionListener, Serializable {

    @EJB
    private Fasada_warstwy_biznesowej_ejbRemote fasada;

    public Managed_produkt() {
    }

    private Produkt_dto produkt_dto = new Produkt_dto();

    private String nazwa;
    private float cena;
    private DataModel items;
    private int stan = 1;
    private Date data_produkcji;
    private Zmiana_danych zmiana1 = new Zmiana_danych("nazwa");
    private Zmiana_danych zmiana2 = new Zmiana_danych("cena");
    private PaginationHelper pagination; //atrybut klasy Managed_produkt
    private int powrot = 1;
    private int zmiana = 1;
    Produkt_dto produkt_dto_przed;

    public String
            zapisz() {
        fasada.zapisz();
        return "/faces/index1";
    }

    public String
            pobierz() {
        fasada.pobierz();
        refresh();
        return "/faces/index1";
    }

    public String destroy() {
        produkt_dto = (Produkt_dto) items.getRowData();
        int ile = items.getRowCount();
        if (ile == 1) {
            this.getPagination().previousPage();
        }
        performDestroy();
        return "lista_produktow";
    }

    private void performDestroy() {
        try {
            getFasada().remove(produkt_dto);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Usunieto_produkt"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Blad_usuwania"));
        }
    }

    public String refresh() {
        getPagination().updatePage();
        items = getPagination().createPageDataModel();
        return "lista_produktow";
    }

    public String prepareEdit() {
        produkt_dto = (Produkt_dto) items.getRowData();
        produkt_dto_przed = new Produkt_dto(produkt_dto);
        zmiana = 0;
        return "dodaj_produkt2";
    }

    public int getZmiana() {
        return zmiana;
    }

    public int getPowrot() {
        return powrot;
    }

    public String update() {
        try {
            boolean wynik = getFasada().edit(produkt_dto_przed, produkt_dto);
            produkt_dto = new Produkt_dto();
            zmiana = 1;
            recreateModel();
            if (wynik) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString(
                        "Produkt_zmieniony"));
            } else {
                throw new Exception();
            }
            return "lista_produktow";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,
                    ResourceBundle.getBundle("/Bundle").getString("Blad_modyfikacji"));
            return "lista_produktow";
        }
    }

    public String prepareView() {
        produkt_dto = (Produkt_dto) items.getRowData();
        powrot = 0;
        stan = 1;
        return "rezultat2";
    }

    public String powrot() {
        powrot = 1;
        produkt_dto = new Produkt_dto();
        return "lista_produktow";
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(3) {
                @Override
                public int getItemsCount() {
                    return getFasada().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    int[] range = {getPageFirstItem(), getPageLastItem() + 1};
                    return new ListDataModel(getFasada().findRange(range));
                }
            };
        }
        return pagination;
    }

    private void recreateModel() {
        items = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "lista_produktow";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "lista_produktow";
    }

    public Zmiana_danych getZmiana1() {
        return zmiana1;
    }

    public void setZmiana1(Zmiana_danych zmiana2) {
        this.zmiana1 = zmiana2;
    }

    public Zmiana_danych getZmiana2() {
        return zmiana2;
    }

    public void setZmiana2(Zmiana_danych zmiana2) {
        this.zmiana2 = zmiana2;
    }
    private NumberConverter number_convert = new NumberConverter();

    public NumberConverter getNumber_convert() {
        this.number_convert.setPattern("######.## zł");
        return number_convert;
    }

    public void setNumber_convert(NumberConverter Number_convert) {
        this.number_convert = Number_convert;
    }

    public int getMin() {
        return 0;
    }

    public int getMax() {
        return 100;
    }

    public Fasada_warstwy_biznesowej_ejbRemote getFasada() {
        return fasada;
    }

    public void setFasada(Fasada_warstwy_biznesowej_ejbRemote fasada) {
        this.fasada = fasada;
    }

    public String getNazwa() {
        return produkt_dto.getNazwa();
    }

    public void setNazwa(String nazwa) {
        this.produkt_dto.setNazwa(nazwa);
    }

    public float getCena() {
        return produkt_dto.getCena();
    }

    public void setCena(float cena) {
        this.produkt_dto.setCena(cena);
    }

    public String getImie() {
        return produkt_dto.getImie();
    }

    public void setImie(String imie) {
        this.produkt_dto.setImie(imie);
    }

    public String getNazwisko() {
        return produkt_dto.getNazwisko();
    }

    public void setNazwisko(String nazwisko) {
        this.produkt_dto.setNazwisko(nazwisko);
    }

    public String getMiasto() {
        return produkt_dto.getMiasto();
    }

    public void setMiasto(String miasto) {
        this.produkt_dto.setMiasto(miasto);
    }

    public String getUlica() {
        return produkt_dto.getUlica();
    }

    public void setUlica(String ulica) {
        this.produkt_dto.setUlica(ulica);
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }


    public Date getData_zakupu() {
        return produkt_dto.getData_zakupu();
    }

    public void setData_zakupu(Date data_zakupu) {
        this.produkt_dto.setData_zakupu(data_zakupu);
    }

    public Date getData_dostarczenia() {
        return produkt_dto.getData_dostarczenia();
    }

    public void setData_dostarczenia(Date data_dostarczenia) {
        this.produkt_dto.setData_dostarczenia(data_dostarczenia);
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public void dodaj_produkt() {
        fasada.utworz_produkt(produkt_dto);
        powrot = 1;
        zmiana = 1;
        dane_produktu();
        recreateModel();
        getPagination().nextPage();
    }

    public void dane_produktu() {
        stan = 1;
        produkt_dto = fasada.dane_produktu();
        if (produkt_dto == null) {
            produkt_dto = new Produkt_dto();
            stan = 0;
        }
    }

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        dodaj_produkt();
    }

    public DataModel utworz_DataModel() {
        return new ListDataModel(fasada.items_());
    }

    public DataModel getItems() {
        if (items == null || fasada.isStan()) {
            items = getPagination().createPageDataModel();
        }
        zmiana = 1;
        powrot = 1;
        return items;
    }

    public void zakrespromocji(FacesContext context,
            UIComponent toValidate, Object value) {
        stan = 1;
        int input = ((Long) value).intValue();
        if (input < getMin() || input > getMax()) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Dane poza zakresem");
            context.addMessage(toValidate.getClientId(context), message);
            stan = 0;
        }
    }
}
