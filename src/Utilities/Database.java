package Utilities;

import Beans.Dipendente;
import Beans.Farmacia;

import java.sql.*;

public class Database {

    private static Database database = new Database();
    private Connection conn;

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
        psm2.setInt(2, 1);
        psm2.setString(3,dipendente.getNome());
        psm2.setString(4,dipendente.getCognome());
        psm2.setString(5,dipendente.getIndirizzo());
        psm2.setString(6,dipendente.getMail());
        psm2.setString(7,dipendente.getPassword());
        psm2.setInt(8,dipendente.getIdFarmacia());
        psm2.executeUpdate();
    }



}
