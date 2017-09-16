package Beans;

import org.apache.struts.action.ActionForm;

public class Medico extends ActionForm {
    int CodiceRegionale;
    String CF,Nome,Cognome,Indirizzo;

    public int getCodiceRegionale() {
        return CodiceRegionale;
    }

    public void setCodiceRegionale(int codiceRegionale) {
        CodiceRegionale = codiceRegionale;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
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

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }
}
