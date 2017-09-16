package Beans;

import org.apache.struts.action.ActionForm;

public class Paziente extends ActionForm{
    String CodiceFiscale,Nome,Cognome,DatadiNascita;

    public String getCodiceFiscale() {
        return CodiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getDatadiNascita() {
        return DatadiNascita;
    }

    public void setDatadiNascita(String datadiNascita) {
        DatadiNascita = datadiNascita;
    }
}
