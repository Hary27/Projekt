
package warstwa_klienta_dekstop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.PatternSyntaxException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import warstwa_biznesowa.dto.Produkt_dto;

public class Produkt_form extends JPanel implements ActionListener {

    JLabel lnazwa = new JLabel("Nazwa"); 		
    JTextField nazwa = new JTextField(15);
    JLabel lcena = new JLabel("Cena");		
    JTextField cena = new JTextField(15);
    JLabel limie = new JLabel("Imie"); 		
    JTextField imie = new JTextField(15);
    JLabel lnazwisko = new JLabel("Nazwisko"); 		
    JTextField nazwisko = new JTextField(30);
    JLabel lmiasto = new JLabel("Miasto"); 		
    JTextField miasto = new JTextField(30);
    JLabel lulica = new JLabel("Ulica"); 		
    JTextField ulica = new JTextField(30);
    		
    JLabel ldata_zakupu = new JLabel("Data zakupu");		
    JTextField data_zakupu = new JTextField(15);	
    JLabel ldata_dostarczenia = new JLabel("Data dostarczenia");		
    JTextField data_dostarczenia = new JTextField(15);
    JButton dodaj_produkt = new JButton("Dodaj produkt");

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  	
        add(lnazwa);			
        add(nazwa);
        add(lcena);			 
        add(cena);
        add(limie);			
        add(imie);			       			 
        add(lnazwisko);			 
        add(nazwisko);			 
        add(lmiasto);			 
        add(miasto);			 
        add(lulica);			 
        add(ulica);
        
        add(ldata_zakupu);			 
        add(data_zakupu);			 
        add(ldata_dostarczenia);			 
        add(data_dostarczenia);			 
        dodaj_produkt.addActionListener(this);	
        //obslugiwany przez obiekt typu Produkt_form za pomoca metody actionPerformed
        add(dodaj_produkt);			//dodanie przycisku do obiektu typu JPanel
    }

    public String content_validate(JTextField val, int typ) {//walidacja danych 
        String s = val.getText();
        if (s.equals("") || s.length() > 15) { 		//sprawdzenie, czy lancuch jest pusty lub dluzszy niż 15 znakow
            JOptionPane.showMessageDialog(this, "Lancuch danych jest dluzszy niz 15 lub jest pusty");
            return null;
        } else {
            s = s.replaceAll(" ", "_"); 		//wyeliminowanie spacji z lancucha
            val.setText(s);
        }
        if (typ == 1) {		                          // jesli sa dane liczbowe, sprawdzenie, czy można dokonać parsowania na liczbe
            try {
                Float.parseFloat(s);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Blad formatu danych liczbowych");
                return null;
            }
        }
        return s;
    }

    public String[] form_produkt() {
        if (content_validate(nazwa, 0) == null) //walidacja danych nazwy jako lancucha
        {
            return null;
        }
        if (content_validate(cena, 1) == null) //walidacja danych ceny jako liczby
        {
            return null;
        }
        if (content_validate(imie, 0) == null) //walidacja danych imie jako lancucha
        {
            return null;
        }
        if (content_validate(nazwisko, 0) == null) //walidacja danych imie jako lancucha
        {
            return null;
        }
        
        if (content_validate(miasto, 0) == null) //walidacja danych ceny jako lancucha
        {
            return null;
        }
        if (content_validate(ulica, 0) == null) //walidacja danych ceny jako lancucha
        {
            return null;
        }
        String dane[] = {(String) nazwa.getText() , (String) cena.getText(), (String) imie.getText(), (String) nazwisko.getText(), (String) miasto.getText(), (String) ulica.getText()};	//tablica z danymi produktu
        return dane;
    }

    public Date data() {
        if (content_validate(data_zakupu, 0) == null) //walidacja danych daty jako lancucha
        {
            return null;
        }
        int rok, miesiac, dzien;
        String data1 = data_zakupu.getText();
        try {
            String[] data2 = data1.split("-");	//podzial lancucha daty np 12-12-2016 na tablicę lancuchow, reprezentujacych elementy daty
            rok = Integer.parseInt(data2[2]);
            miesiac = Integer.parseInt(data2[1]);
            dzien = Integer.parseInt(data2[0]);
        } catch (PatternSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {        //kontrola poprawności 	 	JOptionPane.showMessageDialog(this, "Blad formatu daty");		         //formatu daty
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar();		//pomocnicza klasa do utworzenia daty 
        gc.set(rok, miesiac - 1, dzien);			//tworzenie daty
        return gc.getTime();				//pobranie daty jako obiektu typu Date
    }

    @Override
    public void actionPerformed(ActionEvent evt) //obsluga zdarzenia kliknięcia na przycisk "Dodaj_produkt"
    {
        String[] dane = form_produkt(); //utworzenie tablicy z danymi produktu: nazwa,imie, cena, promocja
        if (dane == null) {
            return;
        }
        Date data_ = data(); //utworzenie daty
        if (data_ == null) {
            return;
        }
        Produkt_dto produkt = new Produkt_dto(dane, data_, data_);
        GUI_main.getFacade().utworz_produkt(produkt); // wywołanie metody
    }
}
