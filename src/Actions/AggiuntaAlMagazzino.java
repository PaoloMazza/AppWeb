package Actions;

import Beans.Login;
import Utilities.Database;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AggiuntaAlMagazzino extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Database database = Database.getInstance();
        int N = database.NumeroProdotti();
        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();

        for(int i = 1; i< N;i++){
            System.out.println(i);
            if(!request.getParameter("ordina" + i).equals("0") || request.getParameter("ordina" +(i)).contains("-")){
               // System.out.println(request.getParameter("ordina"+(i)));
                Integer id = i+1;
                // System.out.println(id);
                Integer quantity = Integer.parseInt(request.getParameter("ordina"+(i)));
               // System.out.println(quantity);
                hashMap.put(id,quantity);
            }
        }

        Login login =(Login)request.getSession().getAttribute("login");
        database.insertOrder(hashMap,login.getIdFarmacia());
        request.setAttribute("exitCode", "ORDINE INSERITO CON SUCCESSO");
        return mapping.findForward("SUCCESS");


    }
}
