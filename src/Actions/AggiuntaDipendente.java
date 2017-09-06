package Actions;

import Beans.Dipendente;
import Beans.Login;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AggiuntaDipendente extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Database database = Database.getInstance();
        Login log = (Login) request.getSession().getAttribute("login");

        if(log == null)
            System.out.println("ERRORE: il login è nullo");

        Dipendente dipendente = new Dipendente();
        dipendente.setCF(request.getParameter("CF"));
        dipendente.setNome(request.getParameter("Nome"));
        System.out.println(dipendente.getNome());
        dipendente.setCognome(request.getParameter("Cognome"));
        dipendente.setIndirizzo(request.getParameter("Indirizzo"));
        dipendente.setMail(request.getParameter("Mail"));
        dipendente.setPassword(request.getParameter("Password"));
        dipendente.setIdFarmacia(log.getIdFarmacia());
        String tipo =  request.getParameter("Tipo");

        if("OB".equals(tipo))
            dipendente.setTipo(2);
        else
            dipendente.setTipo(3);

        if(!database.UserExists(dipendente.getCF())){
            database.insertUser(dipendente);
            request.setAttribute("exitCode", "REGISTRAZIONE AVVENUTA CON SUCCESSO");
            return mapping.findForward("SUCCESS");
        }else{
            request.setAttribute("exitCode","Utente già presente");
            return mapping.findForward("ERROR");
        }
    }
}
