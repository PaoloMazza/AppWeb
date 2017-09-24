package Actions.Aggiunte;

import Beans.Login;
import Beans.Vendita;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class AggiuntaRicetta extends Action{
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        Login login = (Login) request.getSession().getAttribute("login");
        Vendita vendita = (Vendita) request.getSession().getAttribute("vendita");
        Integer CodiceRicetta = Integer.parseInt(request.getParameter("CodiceRicetta"));
        Integer CodiceMedico = Integer.parseInt(request.getParameter("CodiceMedico"));
        String paziente = request.getParameter("Paziente");
        String data = request.getParameter("Data");

        if(!database.PatientExists(paziente)){
            request.setAttribute("exitCode", "Paziente non trovato nel sistema");
            return mapping.findForward("ERROR");
        }else{
            if(!database.MedicExists(CodiceMedico)){
                request.setAttribute("exitCode", "Medico non trovato nel sistema");
                return mapping.findForward("ERROR");
            }else{
                if(database.checkInvoice(CodiceRicetta,CodiceMedico,paziente)){
                    database.insertInvoice(vendita.getProdotti(), login.getIdFarmacia(), String.valueOf(CodiceRicetta),login.getCodiceFiscale());
                    request.setAttribute("exitCode", "Ricetta trovata, acquisto effettuato");
                    return mapping.findForward("SUCCESS");
                }else{
                    request.setAttribute("exitCode", "Ricetta non trovata");
                    return mapping.findForward("ERROR");
                }
            }
        }

    }
}
