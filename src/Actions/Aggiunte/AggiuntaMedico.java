package Actions.Aggiunte;

import Beans.Login;
import Beans.Medico;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AggiuntaMedico extends Action{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        Medico medico = new Medico();
        medico.setCodiceRegionale(Integer.parseInt(request.getParameter("CodiceRegionale")));
        medico.setCF(request.getParameter("CodiceFiscale"));
        medico.setNome(request.getParameter("Nome"));
        medico.setCognome(request.getParameter("Cognome"));
        medico.setIndirizzo(request.getParameter("Indirizzo"));


        if(database.MedicExists(medico.getCodiceRegionale())){
            request.setAttribute("exitCode", "Medico già presente nel sistema");
            return mapping.findForward("ERROR");
        }else{
            database.InserimentoMedico(medico);
            request.setAttribute("exitCode", "Medico inserito con successo");
            return mapping.findForward("SUCCESS");
        }
    }
}

