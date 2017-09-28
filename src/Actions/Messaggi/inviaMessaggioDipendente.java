package Actions.Messaggi;

import Beans.Login;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class inviaMessaggioDipendente extends Action{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        Login login =(Login)request.getSession().getAttribute("login");
        int x = 0;
        String addressees[] = request.getParameter("destinatari").split(";");

        System.out.println("ii");
        for(int i = 0; i < addressees.length;i++) {
            System.out.println(addressees[i]);
            addressees[i].replace("\\\\s+", "");
        }
        System.out.println("ii");


        String oggetto = request.getParameter("oggetto");
        String messaggio = request.getParameter("messaggio");

        if(addressees.equals(null)){
            request.setAttribute("exitCode","Inserire almeno un destinatario");
            return mapping.findForward("ERROR");
        }
        if(messaggio.equals(null)){
            request.setAttribute("exitCode","Inserire il messaggio");
            return mapping.findForward("ERROR");
        }

        for(int i = 0; i<addressees.length;i++){
            System.out.println(addressees[i]);
            if(!database.MailExists(addressees[i])){
                request.setAttribute("exitCode","Indirizzo "+addressees[i] +"non trovato");
                return mapping.findForward("ERROR");
            }
              if (login.getTipo() != 1) {
                      if (login.getIdFarmacia() != database.getEmployeePharmacyId(database.getUserByMail(addressees[i]))) {
                          request.setAttribute("exitCode", addressees[i] + "non è un dipendente della farmacia");
                          return mapping.findForward("ERROR");
                      }
              } else
                  if(!Objects.equals(addressees[i], new String("sistema@root.com"))){
                    System.out.println("Entrato nell'if di /sistema@root.com con " + addressees[i]);
                  if (login.getIdFarmacia() != database.getEmployeePharmacyId(database.getUserByMail(addressees[i]))) {
                      request.setAttribute("exitCode", addressees[i] + "non è un dipendente della farmacia");
                      return mapping.findForward("ERROR");
                  }
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
