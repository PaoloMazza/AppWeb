package Actions.Aggiunte;

import Beans.Login;
import Beans.Medico;
import Beans.Paziente;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Aggiunta_Paziente_Medico extends Action{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        //INIZIALIZZAZIONE VARIABILI
        Medico medico = new Medico();
        Paziente paziente = new Paziente();
        Login login =(Login)request.getSession().getAttribute("login");

        paziente.setCodiceFiscale(request.getParameter("CodiceFiscalePaziente"));
        paziente.setNome(request.getParameter("NomePaziente"));
        paziente.setCognome(request.getParameter("CognomePaziente"));
        paziente.setDatadiNascita(request.getParameter("Data"));

        medico.setCodiceRegionale(Integer.parseInt(request.getParameter("CodiceRegionale")));
        medico.setCF(request.getParameter("CodiceFiscaleMedico"));
        medico.setNome(request.getParameter("NomeMedico"));
        medico.setCognome(request.getParameter("CognomeMedico"));
        medico.setIndirizzo(request.getParameter("Indirizzo"));


        if(database.PatientExists(paziente.getCodiceFiscale())){
            request.setAttribute("exitCode", "Paziente già presente nel sistema");
            return mapping.findForward("ERROR");
        }else{
            if(database.MedicExists(medico.getCodiceRegionale())) {
                request.setAttribute("exitCode", "Medico già presente nel sistema");
                return mapping.findForward("ERROR");
            }else{
                database.InserimentoPaziente(paziente,login.getCodiceFiscale(),login.getIdFarmacia());
                database.InserimentoMedico(medico);
                request.setAttribute("exitCode", "Medico e paziente inseriti con successo");
                return mapping.findForward("SUCCESS");
            }
        }


    }
}
