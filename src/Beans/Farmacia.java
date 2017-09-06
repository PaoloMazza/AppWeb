package Beans;

import org.apache.struts.action.ActionForm;

public class Farmacia extends ActionForm {

    //Per la farmacia
    private String NomeFarmacia, Indirizzo, Telefono;


    //Per il titolare
    private String CFtitolare, NomeTitolare, CognomeTitolare, IndirizzoTitolare, MailTitolare, PasswordTitolare;


    public String getNomeFarmacia() { return NomeFarmacia; }
    public void setNomeFarmacia(String nomeFarmacia) { NomeFarmacia = nomeFarmacia; }

    public String getIndirizzo() { return Indirizzo; }
    public void setIndirizzo(String indirizzo) { Indirizzo = indirizzo; }

    public String getTelefono() { return Telefono; }
    public void setTelefono(String telefono) { Telefono = telefono; }

    public String getCFtitolare() { return CFtitolare;}
    public void setCFtitolare(String CFtitolare) { this.CFtitolare = CFtitolare; }


    public String getNomeTitolare() { return NomeTitolare; }
    public void setNomeTitolare(String nomeTitolare) { NomeTitolare = nomeTitolare; }


    public String getCognomeTitolare() { return CognomeTitolare; }
    public void setCognomeTitolare(String cognomeTitolare) { CognomeTitolare = cognomeTitolare; }


    public String getIndirizzoTitolare() { return IndirizzoTitolare; }
    public void setIndirizzoTitolare(String indirizzoTitolare) { IndirizzoTitolare = indirizzoTitolare; }

    public String getMailTitolare() { return MailTitolare; }
    public void setMailTitolare(String mailTitolare) { MailTitolare = mailTitolare; }

    public String getPasswordTitolare() { return PasswordTitolare; }

    public void setPasswordTitolare(String passwordTitolare) { PasswordTitolare = passwordTitolare; }

    public String execute(){
        return "success";
    }
}
