package Beans;

import org.apache.struts.action.ActionForm;

public class Login extends ActionForm {
    private String CodiceFiscale, Password;

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
}
