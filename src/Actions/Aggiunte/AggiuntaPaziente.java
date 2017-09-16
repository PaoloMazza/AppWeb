package Actions.Aggiunte;

import Beans.Login;
import Beans.Paziente;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AggiuntaPaziente extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        Login log = (Login)request.getSession().getAttribute("login");
        Paziente paziente = new Paziente();
        paziente.setCodiceFiscale(request.getParameter("CodiceFiscale"));
        paziente.setNome(request.getParameter("Nome"));
        paziente.setCognome(request.getParameter("Cognome"));
        paziente.setDatadiNascita(request.getParameter("Data"));

        System.out.println(log.getCodiceFiscale()+ "," +log.getIdFarmacia());

        if(database.PatientExists(paziente.getCodiceFiscale())){
            request.setAttribute("exitCode", "Paziente già presente nel sistema");
            return mapping.findForward("ERROR");
        }else{
            database.InserimentoPaziente(paziente,log.getCodiceFiscale(),log.getIdFarmacia());
            request.setAttribute("exitCode", "Paziente inserito nel sistema!");
            return mapping.findForward("SUCCESS");
        }
    }
}
