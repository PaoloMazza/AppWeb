package Actions.Messaggi;

import Beans.Login;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class inviaMessaggioDipendente extends Action{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        Login login =(Login)request.getSession().getAttribute("login");
        int x = 0;
        String addressees[] = request.getParameter("destinatari").split(";");
        String oggetto = request.getParameter("oggetto");
        String messaggio = request.getParameter("messaggio");

        if(addressees.equals(null)){
            request.setAttribute("exitCode","Inserire almeno un destinatario");
            return mapping.findForward("ERROR");
        }
        if(messaggio.equals("null")){
            request.setAttribute("exitCode","Inserire il messaggio");
            return mapping.findForward("ERROR");
        }

        for(int i = 0; i<addressees.length;i++){
            if(!database.MailExists(addressees[i])){
                request.setAttribute("exitCode","Dipendente "+addressees[i] +"non trovato");
                return mapping.findForward("ERROR");
            }
           if(login.getTipo() != 1) {
               if (login.getIdFarmacia() != database.getEmployeePharmacyId(database.getUserByMail(addressees[i]))) {
                   request.setAttribute("exitCode", addressees[i] + "non è un dipendente della farmacia");
                   return mapping.findForward("ERROR");
               }
           }else if(login.getIdFarmacia() != database.getEmployeePharmacyId(database.getUserByMail(addressees[i])) && database.getUserByMail(addressees[i]).equals("ROOT")){
               request.setAttribute("exitCode", addressees[i] + "non è un dipendente della farmacia");
               return mapping.findForward("ERROR");
           }
        }

        try{
            database.sendMessage(login.getMail(),addressees,oggetto,messaggio);
            request.setAttribute("exitCode","Messaggio inviato");
            return mapping.findForward("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("exitCode","Errore nell'invio del messaggio");
            return mapping.findForward("SUCCESS");
        }
    }
}
