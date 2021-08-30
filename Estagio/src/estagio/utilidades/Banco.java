package estagio.utilidades;
//Padrão Singleton

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;

public class Banco {
    static public Conexao con;
    private static final int port = 5432;

    private Banco(){}
    
    public static boolean conectar()
    {   
        con = new Conexao();
        return con.conectar("jdbc:postgresql://localhost/","estagio","postgres","postgres123");
    }
    
    public static Conexao getCon() {
        return con;
    }

    public static void setCon(Conexao con) {
        Banco.con = con;
    }
    
    public static void realizaBackup(String arq) 
    {
        String reslinha = "";
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(arq);
            if (p != null) 
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);                
                String linha;
                while ((linha = reader.readLine()) != null)                 
                    reslinha += linha+"\n";                
            }
            JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!\n");
        } catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "Erro no backup!\n" + ex.getMessage());
        }
    }
    
    public static boolean realizaBackupNoMessage(String arq) 
    {
        String reslinha = "",erros = "";
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(arq);
            if (p != null) 
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);                
                String linha;
                while ((linha = reader.readLine()) != null)                 
                    reslinha += linha+"\n"; 
                
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                while ((linha = reader.readLine()) != null)                 
                    erros += linha+"\n"; 
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
            erros += ex.getMessage();
        } 
        return erros.equals("");
    }
    
    public static void realizaRestaure(String arqlote)
    {
        String linha;
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(arqlote);
            if (p != null) 
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);                
                while ((linha = reader.readLine()) != null)                 
                    linha += linha;                
            }
            JOptionPane.showMessageDialog(null, "Restore realizado com sucesso!\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Restore!\n" + ex.getMessage());
        }
    }
    
    public static void realizaRestaureNoMessage(String arqlote)
    {
       String linha;
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(arqlote);
            if (p != null) 
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);                
                while ((linha = reader.readLine()) != null)                 
                    linha += linha;                
            }
        } catch (IOException ex) {
        }
    }
    
    public static boolean criarBD(String BD)
    {
        try
        {
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url, "postgres", "postgres123");

            Statement statement = con.createStatement();
            statement.execute("CREATE DATABASE " + BD + " WITH OWNER = postgres ENCODING = 'UTF8'  "
                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252'  "
                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    
    
    public static void backup()
    {
        try
        {
            executeBAT("copiar.bat");
            new Alert(Alert.AlertType.INFORMATION, "Backup realizado com sucesso!", ButtonType.OK).showAndWait();
        } catch (IOException ex)
        {
            new Alert(Alert.AlertType.ERROR, "Erro no backup!" + ex.getMessage(), ButtonType.OK).showAndWait();
        }
 
    }
 
    public static void backup(String path)
    {
        try
        {
            DateTimeFormatter form = DateTimeFormatter.ofPattern("dd_MM_uuuu");
 
            String st = ".\\bkp\\pg_dump.exe --dbname=postgresql://postgres:postgres123@localhost:" + port + 
                    "/dbpapelaria --format custom --blobs --verbose --file \"" + path + 
                    "\\"+form.format(LocalDate.now())+".sql\"";
            System.out.println(st);
            Process p = Runtime.getRuntime().exec(st);
 
            if (p != null)
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);
                String linha;
                while ((linha = reader.readLine()) != null)
                {
                    System.out.println(linha);
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Backup realizado com sucesso!", ButtonType.OK).showAndWait();
 
        } 
        catch (IOException ex)
        {
            new Alert(Alert.AlertType.ERROR, "Erro no backup!" + ex.getMessage(), ButtonType.OK).showAndWait();
        }
    }
 
 
    public static void restore()
    {
        try
        {
            executeBAT("restaurar.bat");
            new Alert(Alert.AlertType.INFORMATION, "Restauração realizada com sucesso!", ButtonType.OK).showAndWait();
        } catch (IOException ex)
        {
            new Alert(Alert.AlertType.ERROR, "Erro na restauração!" + ex.getMessage(), ButtonType.OK).showAndWait();
        }
    }
 
    public static void restore(String path)
    {
        try
        {
            String st = ".\\bkp\\pg_restore --clean --exit-on-error --verbose --dbname=postgresql://postgres:postgres123@localhost:" + port + "/dbpapelaria \""+path+"\"";
            System.out.println(st);
            Process p = Runtime.getRuntime().exec(st);
 
            if (p != null)
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);
                String linha;
                while ((linha = reader.readLine()) != null)
                {
                    System.out.println(linha);
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Backup realizado com sucesso!", ButtonType.OK).showAndWait();
 
        } 
        catch (IOException ex)
        {
            new Alert(Alert.AlertType.ERROR, "Erro no backup!" + ex.getMessage(), ButtonType.OK).showAndWait();
        }
    }
    
    private static void executeBAT(String name) throws IOException
    {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("bkp\\" + name);
        if (p != null)
        {
            InputStreamReader str = new InputStreamReader(p.getErrorStream());
            BufferedReader reader = new BufferedReader(str);
            String linha;
            while ((linha = reader.readLine()) != null)
            {
                System.out.println(linha);
            }
        }
    }
}
