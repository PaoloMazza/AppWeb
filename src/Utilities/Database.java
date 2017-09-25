package Utilities;

import Beans.*;
import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class Database {

    private static Database database = new Database();
    private Connection conn;

    //METODI PER IL FUNZIONAMENTO DEL DATABASE
    private Database(){

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Farmacia","root","oloapazzam");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance( ) {
        return database;
    }

    public ResultSet ExecuteQuery(String query){
        ResultSet resultSet;

        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
          System.out.println(e.getMessage());
           return null;
        }

        return resultSet;
    }

    //FUNZIONI PER LA FARMACIA

    public int getFarmaciaId(String CFtitolare) throws SQLException {

        String query = "SELECT IdFarmacia FROM Farmacia WHERE CFtitolare = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,CFtitolare);
        ResultSet resultSet = statement.executeQuery();
        try {
            while(resultSet.next())
                return resultSet.getInt("IdFarmacia");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

//METODI PER LA CREAZIONE DELLA FARMACIA
    public boolean PharmacyExist(Farmacia farmacia) throws SQLException {

            ResultSet resultSet;
            String part1 = "SELECT DISTINCT NomeFarmacia FROM Farmacia WHERE NomeFarmacia = ? AND Indirizzo = ? AND Telefono = ?";
            PreparedStatement psm = conn.prepareStatement(part1);
            psm.setString(1, farmacia.getNomeFarmacia());
            psm.setString(2,farmacia.getIndirizzo());
            psm.setString(3,farmacia.getTelefono());
            return psm.executeQuery().isBeforeFirst();

        }


    public void CreatePharmacy(Farmacia farmacia) throws SQLException {
        String farm = "INSERT INTO Farmacia(NomeFarmacia,Indirizzo,Telefono,CFtitolare) Values (?,?,?,?)";
        PreparedStatement psm = conn.prepareStatement(farm);

        psm.setString(1, farmacia.getNomeFarmacia());
        psm.setString(2, farmacia.getIndirizzo());
        psm.setString(3,farmacia.getTelefono());
        psm.setString(4,farmacia.getCFtitolare());
        psm.executeUpdate();

        String titolare = "INSERT INTO Dipendente Values (?,?,?,?,?,?,?,?)";
        PreparedStatement psm2  = conn.prepareStatement(titolare);
        psm2.setString(1,farmacia.getCFtitolare());
        psm2.setInt(2, 1);
        psm2.setString(3,farmacia.getNomeTitolare());
        psm2.setString(4,farmacia.getCognomeTitolare());
        psm2.setString(5,farmacia.getIndirizzoTitolare());
        psm2.setString(6,farmacia.getMailTitolare());
        psm2.setString(7,farmacia.getPasswordTitolare());
        psm2.setInt(8,getFarmaciaId(farmacia.getCFtitolare()));
        psm2.executeUpdate();

        int i = 0;
        ResultSet set = database.ExecuteQuery("SELECT idProdotto FROM Prodotto");

        String query = "INSERT INTO Magazzino(IdFarmacia, CodiceProdotto, QuantitaProdotto) VALUES ";
        while(set.next())
        {
            if(i!=0)
                query += ",";

            query += "(" + database.getFarmaciaId(farmacia.getCFtitolare()) + ", '" + set.getString("idProdotto") + "', 0)";
            i++;
        }

        Statement magazzino = conn.createStatement();
        magazzino.executeUpdate(query);
    }

    //METODI PER IL LOGIN

    public ResultSet Login(String Mail, String Password) throws SQLException {
        String query =  "SELECT DISTINCT * FROM Dipendente WHERE Mail = ? AND Password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,Mail);
        preparedStatement.setString(2,Password);
        return preparedStatement.executeQuery();
    }


    //FUNZIONE PER L'INSERIMENTO DI UN PAZIENTE

    public boolean PatientExists(String cf) throws SQLException {
        String query = "SELECT DISTINCT * FROM Paziente WHERE CodiceFiscale = ?";
        PreparedStatement psm = conn.prepareStatement(query);
        psm.setString(1, cf);
        return psm.executeQuery().isBeforeFirst();
    }

    public void InserimentoPaziente(Paziente paziente, String cf, int id) throws SQLException {
        String titolare = "INSERT INTO Paziente Values (?,?,?,?,?,?)";
        PreparedStatement psm2  = conn.prepareStatement(titolare);
        psm2.setString(1,paziente.getCodiceFiscale());
        psm2.setString(2,paziente.getNome());
        psm2.setString(3,paziente.getCognome());
        psm2.setString(4,paziente.getDatadiNascita());
        psm2.setString(5, cf);
        psm2.setInt(6,id);
        psm2.executeUpdate();
    }

    //FUNZIONE PER L'INSERIMENTO DI UN MEDICO

    public boolean MedicExists(int codiceRegionale) throws SQLException {
        String query = "SELECT DISTINCT * FROM Medico WHERE CodiceRegionale = ?";
        PreparedStatement psm = conn.prepareStatement(query);
        psm.setInt(1, codiceRegionale);
        return psm.executeQuery().isBeforeFirst();
    }

    public void InserimentoMedico(Medico medico) throws SQLException {
        String titolare = "INSERT INTO Medico Values (?,?,?,?,?)";
        PreparedStatement psm2  = conn.prepareStatement(titolare);
        psm2.setInt(1,medico.getCodiceRegionale());
        psm2.setString(2,medico.getCF());
        psm2.setString(3,medico.getNome());
        psm2.setString(4,medico.getCognome());
        psm2.setString(5, medico.getIndirizzo());
        psm2.executeUpdate();
    }

    //METODI PER L'INSERIMENTO DELL'UTENTE

    public boolean UserExists(String CF) throws SQLException {
        String query = "SELECT DISTINCT * FROM Dipendente WHERE CFdipendente = ?";
        PreparedStatement psm = conn.prepareStatement(query);
        psm.setString(1, CF);
        return psm.executeQuery().isBeforeFirst();
    }

    public void insertUser(Dipendente dipendente) throws SQLException {
        String titolare = "INSERT INTO Dipendente Values (?,?,?,?,?,?,?,?)";
        PreparedStatement psm2  = conn.prepareStatement(titolare);
        psm2.setString(1,dipendente.getCF());
        psm2.setInt(2, dipendente.getTipo());
        psm2.setString(3,dipendente.getNome());
        psm2.setString(4,dipendente.getCognome());
        psm2.setString(5,dipendente.getIndirizzo());
        psm2.setString(6,dipendente.getMail());
        psm2.setString(7,dipendente.getPassword());
        psm2.setInt(8,dipendente.getIdFarmacia());
        psm2.executeUpdate();
    }

    //FUNZIONI PER LE TABELLE DI VENDITA/ACQUISTO

    public String fillWarehouseTable(int id){
        String out = "";
        String query = "SELECT DISTINCT * FROM Prodotto";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            int x = 1;
            while (resultSet.next()) {
                out = out.concat("<tr><td><p name=\"id"+x+"\" >" + resultSet.getInt("IdProdotto") + "</p></td><td><p>" + resultSet.getString("NomeProdotto") + "</p></td><td><p>" + resultSet.getDouble("Prezzo") + " &#8364</p></td><td><p>" + getProductQuantity(resultSet.getInt("IdProdotto"),id) + "</p></td><td><p><input type=\"number\" value=0 min=\"0\" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" class=\"ordina\"></td></tr>");
                x++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return out;
    }


    public String FillSalesTable(int id, boolean NOricetta){
        String out = "";
        String query = "";

        if(!NOricetta)
          query = "SELECT DISTINCT * FROM Magazzino INNER JOIN Prodotto ON Magazzino.CodiceProdotto = Prodotto.idProdotto WHERE IdFarmacia = ?";
        else
          query = "SELECT DISTINCT * FROM Magazzino INNER JOIN Prodotto ON Magazzino.CodiceProdotto = Prodotto.idProdotto WHERE IdFarmacia = ? AND Prodotto.Ricetta = 0";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            String ricetta1 = "";
            int x = 1;
            while (resultSet.next()) {
                if(productNeedsReceipt(resultSet.getInt("CodiceProdotto")))
                    ricetta1 = "SI";
                else
                    ricetta1 = "NO";

                out = out.concat("<tr><td><p name=\"id"+x+"\" >" + resultSet.getInt("CodiceProdotto") + "</p></td><td><p>" + resultSet.getString("NomeProdotto") + "</p></td><td><p>" + resultSet.getDouble("Prezzo") + " &#8364</p></td><td><p>" + resultSet.getInt("QuantitaProdotto") + "</p></td><td>"+ricetta1+"<p></p></td><td><p><input type=\"number\" value=0 min=0 max="+resultSet.getInt("QuantitaProdotto") +" name=\"ordina" + x + "\" size=\"3\" id=\"ordina" + x + "\" class=\"ordina\"></td></tr>");
                x++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return out;
    }

//FUNZIONI PER ORDINI/FATTURE

    public void insertOrder(HashMap<Integer,Integer> hashMap, int id) throws SQLException {

        String insert = "";
        double finalPrice = 0;
        int counter = 0;

        for(int i = 1; i<= NumeroProdotti(); i++){
            if(hashMap.containsKey(i)){
                double price = getProductPrice(i);
                String name = getProductname(i);
                price *= hashMap.get(i);
                finalPrice += price;
                System.out.println("chiave =" +hashMap.get(i)+ " idFarmacia=" +id+" Prodotto= " +i);
                incrementProductQuantity(hashMap.get(i),id,i);
                if(counter ==0)
                    insert = insert + name+ " * "  + hashMap.get(i);
                else
                    insert = insert + "," + name+ " * "  + hashMap.get(i);

                counter++;
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new Date();

        String query = "INSERT INTO Ordine Values (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,dateFormat.format(date));
        statement.setDouble(2,finalPrice);
        statement.setString(3,insert);
        statement.setInt(4,id);
        statement.executeUpdate();
    }


    public void insertInvoice(HashMap<Integer,Integer> prodotti, int id, String ricetta,String dipendente) throws SQLException {

        String insert = "";
        double finalPrice = 0;
        int counter = 0;

        Integer ids[] = prodotti.keySet().toArray(new Integer[prodotti.size()]);

        for(int i = 0; i< ids.length;i++){
            double price = getProductPrice(ids[i]);
            String name = getProductname(ids[i]);
            price *= prodotti.get(ids[i]);
            finalPrice += price;
            DecrementProductQuantity(prodotti.get(ids[i]),id,ids[i]);
            if(counter ==0)
                insert = insert + name+ " * "  + prodotti.get(ids[i]);
            else
                insert = insert + "," + name+ " * "  + prodotti.get(ids[i]);

            counter++;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new Date();

        String query = "INSERT INTO Fattura(IdFarmacia,Data,Prezzo,Ricetta,Articoli,FattaDa) Values (?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);
        statement.setString(2,dateFormat.format(date));
        statement.setDouble(3,finalPrice);
        statement.setString(4,ricetta);
        statement.setString(5,insert);
        statement.setString(6,dipendente);
        statement.executeUpdate();
    }


//METODI PER IL MAGAZZINO


    private void incrementProductQuantity(int sum, int FarmacyId, int prodotto) throws SQLException {
        String query;
        PreparedStatement statement;

        if(!productExists(prodotto,FarmacyId)) {
            query = "INSERT INTO Magazzino VALUES (?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1,FarmacyId);
            statement.setInt(2,prodotto);
            statement.setInt(3,sum);
            statement.executeUpdate();
        }
       else{
            query = "UPDATE Magazzino SET QuantitaProdotto = QuantitaProdotto + ? WHERE IdFarmacia = ? AND CodiceProdotto = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, sum);
            statement.setInt(2, FarmacyId);
            statement.setString(3, String.valueOf(prodotto));
            statement.executeUpdate();
        }

    }


    private void DecrementProductQuantity(int sum, int FarmacyId, int prodotto) throws SQLException {
        String query;
        PreparedStatement statement;
        if(getProductQuantity(prodotto,FarmacyId) == 0) {
            query = "INSERT INTO Magazzino VALUES (?,?,?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1,FarmacyId);
            statement.setInt(2,prodotto);
            statement.setInt(3,sum);
            statement.executeUpdate();
        }
        else {
            query = "UPDATE Magazzino SET QuantitaProdotto = QuantitaProdotto - ? WHERE IdFarmacia = ? AND CodiceProdotto = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, sum);
            statement.setInt(2, FarmacyId);
            statement.setString(3, String.valueOf(prodotto));
            statement.executeUpdate();
        }

    }

//FUNZIONI PER IL PRODOTTO

    public boolean productExists(int prodotto, int pharmacyId) throws SQLException {
    String query = "SELECT * FROM Magazzino WHERE idFarmacia = ? AND CodiceProdotto = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setInt(1, pharmacyId);
    statement.setInt(2,prodotto);
    ResultSet resultSet= statement.executeQuery();
    return resultSet.isBeforeFirst();
    }

    public int NumeroProdotti(){
        int quantity = 0;
        String query = "SELECT DISTINCT * FROM Prodotto";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                quantity++;

            return quantity;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int quantitàNelMagazzino(int id){
        int quantity = 0;
        String query = "SELECT DISTINCT * FROM Magazzino WHERE IdFarmacia = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                quantity++;

            return quantity;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private double getProductPrice(int id){
        double price = 0;
        String query = "SELECT DISTINCT Prezzo FROM Prodotto WHERE idProdotto = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            return resultSet.getDouble("Prezzo");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String getProductname(int id){
        double price = 0;
        String query = "SELECT DISTINCT NomeProdotto FROM Prodotto WHERE idProdotto = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getString("NomeProdotto");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer getProductQuantity(int idProdotto, int idFarmacia){
        String query = "SELECT QuantitaProdotto FROM Magazzino WHERE IdFarmacia = ? AND CodiceProdotto = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idFarmacia);
            statement.setString(2,String.valueOf(idProdotto));
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                int quantity =resultSet.getInt("QuantitaProdotto");
                return quantity;
            }
            else
                return 0;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private boolean productNeedsReceipt(int id) throws SQLException {
        String query = "SELECT Ricetta FROM Prodotto WHERE idProdotto = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        if(resultSet.getBoolean("Ricetta") == true)
            return true;
        else
            return false;

    }


    //FUNZIONE PER VENDITA

    public String Invoice(HashMap<Integer,Integer> products){
        String out = "";
        Integer keys[] = products.keySet().toArray(new Integer[products.size()]);
        Integer quantities[] = products.values().toArray(new Integer[products.size()]);

        for(int i = 0; i< keys.length;i++) {
            String productName = getProductname(keys[i]);
            Double price = getProductPrice(keys[i]);
            out = out.concat("<tr><td><p name=\"id" + keys[i] + "\" >" + keys[i] + "</p></td><td><p>" + productName + "</p></td><td><p>" + price + " &#8364</p></td><td><p>" + quantities[i] + "</p></td></tr>");
        }
        return out;
    }


    public boolean InvoiceNeedsPrescription(Integer[] keys) throws SQLException {
        boolean ThereIsAReceipt = false;
        for(int i = 0; i< keys.length;i++){
            if(productNeedsReceipt(keys[i]))
                ThereIsAReceipt = true;
        }

        return ThereIsAReceipt;
    }

    //FUNZIONE PER REGISTRARE LA RICETTA

    public void insertReceipt(Integer CodiceRicetta, Integer CodiceMedico, String CodicePaziente, String data) throws SQLException {
        String titolare = "INSERT INTO Ricetta Values (?,?,?,?)";
        PreparedStatement psm2  = conn.prepareStatement(titolare);
        psm2.setInt(1,CodiceRicetta);
        psm2.setInt(2, CodiceMedico);
        psm2.setString(3,CodicePaziente);
        psm2.setString(4,data);;
        psm2.executeUpdate();
    }

    public boolean checkInvoice(Integer CodiceRicetta, Integer CodiceMedico, String CodicePaziente) throws SQLException {
        String query = "SELECT CodiceRicetta FROM Ricetta WHERE CodiceRicetta = ? AND CodiceMedico = ? AND Paziente = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,CodiceRicetta);
        statement.setInt(2,CodiceMedico);
        statement.setString(3,CodicePaziente);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.isBeforeFirst();
    }

    //METODI PER I MESSAGGI

    public String fillTableMessages(String CF){
        String query = "SELECT * FROM Messaggio WHERE destinatario = ? ORDER BY data DESC ";
        String result = "";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,CF);
            ResultSet resultSet = statement.executeQuery();

          if(resultSet.isBeforeFirst()){
              resultSet.next();
               while (!resultSet.isAfterLast()) {
                int id = resultSet.getInt("idMessaggio");
                String mittente = getNameSurname(resultSet.getString("mittente"));
                String oggetto = resultSet.getString("oggetto");
                String data = resultSet.getString("data");
                result += "<form action=\"/LeggiMail.do\" method=\"post\"><tr><td><input type=\"text\" value="+id+" name = \"id\" readonly></td><td><p>" + mittente + "</p></td><td><p>" + oggetto + "</p></td><td><p>" + data + "</p></td><td> <button type=\"submit\">Visualizzare</button></td></form>";
                resultSet.next();
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String fillTableContacts(String CF, int code) throws Exception{
        String query = "";
        int pharmacyId = getEmployeePharmacyId(CF);
        PreparedStatement statement = null;
        String result = "";

        switch (code){

            case 1:
                query = "SELECT * FROM Dipendente WHERE IdFarmacia = ? OR  IdFarmacia = 0 ORDER BY IdFarmacia DESC";
                statement = conn.prepareStatement(query);
                statement.setInt(1,pharmacyId);
                break;
            case 0:
                query = "SELECT * FROM Dipendente WHERE TipoDipendente = 1 ORDER BY IdFarmacia DESC";
                statement = conn.prepareStatement(query);
                break;

            default:
                query = "SELECT * FROM Dipendente WHERE IdFarmacia = ? ORDER BY IdFarmacia DESC";
                statement = conn.prepareStatement(query);
                statement.setInt(1,pharmacyId);
                break;
        }

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.isBeforeFirst()){
            resultSet.next();
            while (!resultSet.isAfterLast()) {
                String name = resultSet.getString("Nome");
                String surname = resultSet.getString("Cognome");
                int farmacia = resultSet.getInt("IdFarmacia");
                String cf = resultSet.getString("CFdipendente");
                result += "<form action=\"/LeggiMail.do\" method=\"post\"><tr><td><p>" + name + "</p></td><td><p>" + surname + "</p></td><td><p>" + farmacia + "</p></td><td><p>" + cf + "</p></td></tr>";
                resultSet.next();
            }
        }

        return result;

    }

    public void sendMessage(String sender, String[] receiver, String object, String message) throws Exception{

        for(int i = 0; i < receiver.length;i++){
            String query = "INSERT INTO Messaggio (mittente, destinatario, oggetto, messaggio, data) Values (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, sender);
            statement.setString(2, receiver[i]);
            statement.setString(3, object);
            statement.setString(4,message);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date date = new Date();
            statement.setString(5,dateFormat.format(date));


            statement.executeUpdate();

        }

    }

    public Messaggi getMessageById(int code) throws Exception{
        String query = "SELECT * FROM Messaggio WHERE idMessaggio = ? ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,code);
        ResultSet set = statement.executeQuery();

        set.next();
        Messaggi messaggio = new Messaggi();
        messaggio.setMittente(getNameSurname(set.getString("mittente")));
        messaggio.setOggetto(set.getString("oggetto"));
        messaggio.setMessaggio(set.getString("messaggio"));
        return messaggio;

    }

    //METODI PER I DIPENDENTI

    public int getEmployeePharmacyId(String cf){
        String query = "SELECT IdFarmacia FROM Dipendente WHERE CFdipendente = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,cf);
            ResultSet set = statement.executeQuery();
            set.next();
            System.out.println(set.getInt("IdFarmacia"));
            return set.getInt("IdFarmacia");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getNameSurname(String cf){
        String query = "SELECT * FROM Dipendente WHERE CFdipendente = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,cf);
            ResultSet set = statement.executeQuery();
            set.next();
            String res = set.getString("Nome") + " " + set.getString("Cognome");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<String> getAllContacts() throws SQLException {
        ArrayList<String> contacts = new ArrayList<>();
        String query = "SELECT * FROM Dipendente";
        ResultSet set = ExecuteQuery(query);

        set.next();
        while(!set.isAfterLast()){
            contacts.add(set.getString("CFdipendente"));
            set.next();
        }

        return contacts;

    }

    //METODO PER STATISTICHE PER IL TITOLARE DELLA FARMACIA

    private String fromNumberToMonth(String month){
        switch (month){
            case "1":
                return "\"Gennaio\"";
            case "2":
                return "\"Febbraio\"";
            case "3":
                return "\"Marzo\"";
            case "4":
                return "\"Aprile\"";
            case "5":
                return "\"Maggio\"";
            case "6":
                return "\"Giugno\"";
            case "7":
                return "\"Luglio\"";
            case "8":
                return "\"Agosto\"";
            case "9":
                return "\"Settembre\"";
            case "10":
                return "\"Ottobre\"";
            case "11":
                return "\"Novembre\"";
            case "12":
                return "\"Dicembre\"";
        }
        return null;
    }

    //1 se acquisti, 0 se vendite
    public LinkedHashMap<String,Integer> getPurchaseStatistics(String begin, String end, String year, int id, int type) throws SQLException {
        LinkedHashMap<String, Integer> statistics = new LinkedHashMap<>();
        String query;

        for(int j = Integer.parseInt(begin); j <= Integer.parseInt(end);j++) {
            statistics.put(fromNumberToMonth(String.valueOf(j)), 0);
        }

        for(int i = Integer.parseInt(begin); i<= Integer.parseInt(end);i++) {
            int counter = 0;

            if(type==1){
                query = "SELECT * FROM Ordine WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y') AND IdFarmacia = ?";
            }else {
                query = "SELECT * FROM Fattura WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y') AND IdFarmacia = ?";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.isBeforeFirst()) {
                set.next();
                while (!set.isAfterLast()) {
                    counter++;
                    set.next();
                }
                statistics.put(fromNumberToMonth(String.valueOf(i)),counter);
            }
        }
        return statistics;
    }

    public LinkedHashMap<String,Double> ricaviEVendite (String begin, String end, String year, int id, int type) throws SQLException {
        LinkedHashMap<String, Double> statistics = new LinkedHashMap<>();
        String query;

        for(int j = Integer.parseInt(begin); j <= Integer.parseInt(end);j++) {
            statistics.put(fromNumberToMonth(String.valueOf(j)), 0.0);
        }


        for(int i = Integer.parseInt(begin); i<= Integer.parseInt(end);i++) {
            double counter = 0;

            if(type==1){
                query = "SELECT * FROM Ordine WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y') AND IdFarmacia = ?";
            }else {
                query = "SELECT * FROM Fattura WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y') AND IdFarmacia = ?";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.isBeforeFirst()) {
                set.next();
                counter += set.getDouble("Prezzo");
                while (!set.isAfterLast()) {
                    counter+=set.getDouble("Prezzo");
                    set.next();
                }
                statistics.put(fromNumberToMonth(String.valueOf(i)),counter);
            }
        }
        return statistics;


    }

    public String getMostSeller(int idFarmacia) throws Exception {
        return getNameSurname(comparatorSellers(idFarmacia));
    }

    private String comparatorSellers(int id) throws Exception{
        TreeMap<String, Double> occorrenze = new TreeMap<>();

        String query = "SELECT DISTINCT CFdipendente FROM Dipendente WHERE IdFarmacia = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet set1 = statement.executeQuery();

        set1.next();
        do{
            occorrenze.put(set1.getString("CFdipendente"),0.0);
            set1.next();
        }while (!set1.isAfterLast());
        set1.close();

        String query2 = "SELECT * FROM Fattura WHERE IdFarmacia = ?";
        PreparedStatement statement2 = conn.prepareStatement(query2);
        statement2.setInt(1,id);
        ResultSet set2 = statement2.executeQuery();

        if(set2.isBeforeFirst()){
            set2.next();
            while (!set2.isAfterLast()){
                System.out.println(set2.getDouble("Prezzo"));
                occorrenze.put(set2.getString("FattaDa"),occorrenze.get(set2.getString("FattaDa"))+set2.getDouble("Prezzo"));
                set2.next();
            }
        }

        Map<String, Double> sortedHashMap = sortByValue(occorrenze);
        String[] result = sortedHashMap.keySet().toArray(new String[sortedHashMap.keySet().size()]);
        return result[result.length-1];
    }

    private static Map<String, Double> sortByValue(Map<String, Double> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }


        return sortedMap;
    }

    public Integer ReceiptProductSold(int id) throws SQLException {
        Integer totalSold = 0, WithReceipt = 0;

        String query = "SELECT * FROM Fattura WHERE IdFarmacia = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);

        ResultSet set = statement.executeQuery();

        if(set.isBeforeFirst()){
            set.next();
            while(!set.isAfterLast()){
                totalSold +=1;
                if(!set.getString("Ricetta").equals("-"))
                    WithReceipt +=1;
                set.next();
            }
        }

        return 100 - WithReceipt%totalSold;
    }

    //METODI PER LE STATISTICHE PER LA REGIONE

    public LinkedHashMap<String,Integer> getPurchaseStatisticsRG(String begin, String end, String year, int type) throws SQLException {
        LinkedHashMap<String, Integer> statistics = new LinkedHashMap<>();
        String query;

        for(int j = Integer.parseInt(begin); j <= Integer.parseInt(end);j++) {
            statistics.put(fromNumberToMonth(String.valueOf(j)), 0);
        }

        for(int i = Integer.parseInt(begin); i<= Integer.parseInt(end);i++) {
            int counter = 0;

            if(type==1){
                query = "SELECT * FROM Ordine WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y')";
            }else {
                query = "SELECT * FROM Fattura WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y')";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            if (set.isBeforeFirst()) {
                set.next();
                while (!set.isAfterLast()) {
                    counter++;
                    set.next();
                }
                statistics.put(fromNumberToMonth(String.valueOf(i)),counter);
            }
        }
        return statistics;
    }

    public LinkedHashMap<String,Double> ricaviEVenditeRG (String begin, String end, String year, int type) throws SQLException {
        LinkedHashMap<String, Double> statistics = new LinkedHashMap<>();
        String query;

        for(int j = Integer.parseInt(begin); j <= Integer.parseInt(end);j++) {
            statistics.put(fromNumberToMonth(String.valueOf(j)), 0.0);
        }


        for(int i = Integer.parseInt(begin); i<= Integer.parseInt(end);i++) {
            double counter = 0;

            if(type==1){
                query = "SELECT * FROM Ordine WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y')";
            }else {
                query = "SELECT * FROM Fattura WHERE Data BETWEEN str_to_date('01-" + i + "-"+year+"','%d-%m-%Y') AND str_to_date('31-" + (i) + "-2017','%d-%m-%Y')";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            if (set.isBeforeFirst()) {
                set.next();
                counter += set.getDouble("Prezzo");
                while (!set.isAfterLast()) {
                    counter+=set.getDouble("Prezzo");
                    set.next();
                }
                statistics.put(fromNumberToMonth(String.valueOf(i)),counter);
            }
        }
        return statistics;


    }

    public String getMostSellerRG() throws Exception {
        return getNameSurname(comparatorSellersRG());
    }

    private String comparatorSellersRG() throws Exception{
        TreeMap<String, Double> occorrenze = new TreeMap<>();

        String query = "SELECT DISTINCT CFdipendente FROM Dipendente";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet set1 = statement.executeQuery();

        set1.next();
        do{
            occorrenze.put(set1.getString("CFdipendente"),0.0);
            set1.next();
        }while (!set1.isAfterLast());
        set1.close();

        String query2 = "SELECT * FROM Fattura";
        PreparedStatement statement2 = conn.prepareStatement(query2);
        ResultSet set2 = statement2.executeQuery();

        if(set2.isBeforeFirst()){
            set2.next();
            while (!set2.isAfterLast()){
                System.out.println(set2.getString("FattaDa"));
                occorrenze.put(set2.getString("FattaDa"),occorrenze.get(set2.getString("FattaDa"))+set2.getDouble("Prezzo"));
                set2.next();
            }
        }

        Map<String, Double> sortedHashMap = sortByValue(occorrenze);
        String[] result = sortedHashMap.keySet().toArray(new String[sortedHashMap.keySet().size()]);
        System.out.println(Arrays.toString(result));
        return result[result.length-1];
    }

    public Integer ReceiptProductSoldRG() throws SQLException {
        Integer totalSold = 0, WithReceipt = 0;

        String query = "SELECT * FROM Fattura";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet set = statement.executeQuery();

        if(set.isBeforeFirst()){
            set.next();
            while(!set.isAfterLast()){
                totalSold +=1;
                if(!set.getString("Ricetta").equals("-"))
                    WithReceipt +=1;
                set.next();
            }
        }

        return 100 - WithReceipt%totalSold;
    }




}
