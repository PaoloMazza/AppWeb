package Actions.Log;

import Beans.Login;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

public class LoginAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Login login = new Login();
        Database database = Database.getInstance();
        ResultSet utente = database.Login(request.getParameter("CFTitolare"),request.getParameter("Password"));

        if(!utente.isBeforeFirst()){
            System.out.println("Utente non trovato");
            request.setAttribute("exitCode","Codice fiscale o password errate!");
            return mapping.findForward("ERROR");
        }else {
            utente.next();
            HttpSession session = request.getSession();
            login.setCodiceFiscale(request.getParameter("CFTitolare"));
            System.out.println(login.getCodiceFiscale());
            login.setPassword(request.getParameter("Password"));
            login.setNome(utente.getString("Nome"));
            login.setCognome(utente.getString("Cognome"));
            login.setIdFarmacia(utente.getInt("IdFarmacia"));
            login.setTipo(utente.getInt("TipoDipendente"));
            session.setAttribute("login",login);
        }

        request.setAttribute("confirm","Login Eseguito correttamente");
        switch (login.getTipo()){

            case 0:
                return (mapping.findForward("REGIONE"));

            case 1: //TITOLARE
                return (mapping.findForward("TITOLARE"));
            case 2: //Dottore Farmacista
                return (mapping.findForward("OB"));
            case 3: //Operatore di banco
                return (mapping.findForward("DF"));
        }

        return null;


    }
}
