package Beans;

import org.apache.struts.action.ActionForm;

public class Dipendente extends ActionForm {
    String CF, Nome, Cognome, Indirizzo, Mail, Password;
    int tipo, idFarmacia;

    public int getIdFarmacia() { return idFarmacia; }

    public void setIdFarmacia(int idFarmacia) { this.idFarmacia = idFarmacia;}

    public String getCF() { return CF; }
    public void setCF(String CF) { this.CF = CF; }

    public String getNome() { return Nome; }
    public void setNome(String nome) { Nome = nome; }

    public String getCognome() { return Cognome; }
    public void setCognome(String cognome) { Cognome = cognome; }

    public String getIndirizzo() { return Indirizzo; }
    public void setIndirizzo(String indirizzo) { Indirizzo = indirizzo; }

    public String getMail() { return Mail; }
    public void setMail(String mail) { Mail = mail; }

    public String getPassword() { return Password; }
    public void setPassword(String password) { Password = password;}

    public int getTipo() {return tipo;}
    public void setTipo(int tipo) {this.tipo = tipo;}
}
