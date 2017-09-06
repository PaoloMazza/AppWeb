package Actions;

import Beans.Farmacia;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

public class AggiuntaFarmacia extends Action {

    public  ResultSet resultSet;
    public Database database = Database.getInstance();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Farmacia farmacia = new Farmacia();
        farmacia.setNomeFarmacia(request.getParameter("NomeFarmacia"));
        farmacia.setIndirizzo(request.getParameter("Indirizzo"));
        farmacia.setTelefono(request.getParameter("Telefono"));

        farmacia.setCFtitolare(request.getParameter("CFtitolare"));
        farmacia.setNomeTitolare(request.getParameter("NomeTitolare"));
        farmacia.setCognomeTitolare(request.getParameter("CognomeTitolare"));
        farmacia.setIndirizzoTitolare(request.getParameter("IndirizzoTitolare"));
        farmacia.setMailTitolare(request.getParameter("MailTitolare"));
        farmacia.setPasswordTitolare(request.getParameter("PasswordTitolare"));


        if(database.PharmacyExist(farmacia)){
            request.setAttribute("exitCode", "Farmacia/titolare già presente nel sistema");
            return mapping.findForward("ERROR");
        }


        ResultSet resultSet2 = database.ExecuteQuery("SELECT * FROM Dipendente WHERE CFdipendente = '"+farmacia.getCFtitolare()+"'");

        if(resultSet2.isBeforeFirst()){
            System.out.println("Titolare già presente come dipendente di una farmacia");
            request.setAttribute("exitCode","Titolare già presente come dipendente di una farmacia");
            return mapping.findForward("ERROR");
        }


        database.CreatePharmacy(farmacia);





        System.out.println("REGISTRAZIONE AVVENUTA CON SUCCESSO");
        request.setAttribute("exitCode", "REGISTRAZIONE AVVENUTA CON SUCCESSO");
        return mapping.findForward("SUCCESS");








    }




}
