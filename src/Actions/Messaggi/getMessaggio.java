package Actions.Messaggi;

import Beans.Messaggi;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getMessaggio extends Action{

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        System.out.println(request.getParameter("id"));
        Messaggi messaggio = database.getMessageById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("messaggio",messaggio);
        return mapping.findForward("SUCCESS");
    }
}
