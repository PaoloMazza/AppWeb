package Beans;

import org.apache.struts.action.ActionForm;

public class Login extends ActionForm {
    private String CodiceFiscale, Password, Nome, Cognome;
    int IdFarmacia, Tipo;

    public String getCodiceFiscale() {
        return CodiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public int getIdFarmacia() {
        return IdFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        IdFarmacia = idFarmacia;
    }

    public int getTipo() { return Tipo; }

    public void setTipo(int tipo) { Tipo = tipo; }
}
