package Utilities;

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
            resultSet=psm.executeQuery();

            if(resultSet.isBeforeFirst())
                return true;

            return false;
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


}
