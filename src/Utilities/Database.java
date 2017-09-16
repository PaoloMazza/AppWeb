package Utilities;

import Beans.Dipendente;
import Beans.Farmacia;
import Beans.Medico;
import Beans.Paziente;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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

    public ResultSet Login(String CF, String Password) throws SQLException {
        String query =  "SELECT DISTINCT * FROM Dipendente WHERE CFdipendente = ? AND Password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,CF);
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


    public String FillSalesTable(int id, boolean ricetta){
        String out = "";
        String query = "";

        if(!ricetta)
          query = "SELECT DISTINCT * FROM Magazzino INNER JOIN Prodotto ON Magazzino.CodiceProdotto = Prodotto.idProdotto WHERE IdFarmacia = ?";
        else
          query = "SELECT DISTINCT * FROM Magazzino INNER JOIN Prodotto ON Magazzino.CodiceProdotto = Prodotto.idProdotto WHERE IdFarmacia = ? AND Prodotto.Ricetta = 1";

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

        for(int i = 1; i< NumeroProdotti()+1; i++){
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


    public void insertInvoice(HashMap<Integer,Integer> prodotti, int id, String ricetta) throws SQLException {

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

        String query = "INSERT INTO Fattura(IdFarmacia,Data,Prezzo,Ricetta,Articoli) Values (?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);
        statement.setString(2,dateFormat.format(date));
        statement.setDouble(3,finalPrice);
        statement.setString(4,ricetta);
        statement.setString(5,insert);
        statement.executeUpdate();
    }


//METODI PER IL MAGAZZINO


    private void incrementProductQuantity(int sum, int FarmacyId, int prodotto) throws SQLException {
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


}
