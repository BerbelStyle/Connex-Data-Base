
package conexionbbdd;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBBDD {
 
    private final String url = "jdbc:postgresql://127.0.0.1:5432/BDMusica";
    //private final String user = "postgres";
    private final String user = "postgres";
    private final String password = "xd98";
    private final Logger logger = Logger.getLogger(ConexionBBDD.class.getName());
    private Connection conn = null;

    public Connection connect() { 
        try {
            Class.forName("org.postgresql.Driver").newInstance(); 
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Driver no detectado");
        } 
        return conn;
    }
    
    public void disconnect(){
        try {
            conn.close();
            System.out.println("Desconectado");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido cerrar la conexión",ex);
        } catch (Exception ex){
            logger.log(Level.WARNING, "Excepción capturada",ex);
        }
    }
    
    public void getAllPonent(){
        try {
            Statement stmnt = conn.createStatement();
            
            String prueba1 = "SELECT * FROM musico;"; 
            
            String prueba2 = "SELECT * FROM cancion;"; 
            
            String instrumentos = "SELECT * FROM instrumento;";
            
            String prueba3 = "SELECT * FROM concierto;"; 
            
            String admin = "INSERT INTO instrumento VALUES (4, 'Guitarrita')";
            
            String ejercicio1 = "select nom_grupo from grupo inner join genero on cod_genero_genero = cod_genero "
                    + "where nom_genero = 'Jazz'";
            
            ResultSet rs = stmnt.executeQuery(instrumentos);            
            System.out.println("Listado de musicos");
            System.out.println("============================");
            while(rs.next()){
                System.out.println("\n["+rs.getString(2).trim()+"]: "); //+rs.getString(2).trim()+".");
            }
            System.out.println("\n============================");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
    }
 
    public static void main(String[] args) {
        ConexionBBDD app = new ConexionBBDD();
        app.connect();
        app.getAllPonent();
        app.disconnect();
    }
}

