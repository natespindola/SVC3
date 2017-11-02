package database;

import core.ConnectionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CreateDataBaseCommand
{
    public String readCommand() throws Exception
    {
        File file=new File("src/database/Banco_de_dados.sql");
        boolean ok = file.exists();
        boolean ok2 = file.isFile();
        FileReader fl = new FileReader(file);
        BufferedReader br = new BufferedReader(fl);
        String everything = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(" ");
                line = br.readLine();
            }
            everything = sb.toString();
            br.close();
        }catch(Exception e){
            br.close();
            e.printStackTrace();
        }

        return everything;
    }

    public static void main(String[] args)
    {
        CreateDataBaseCommand cb = new CreateDataBaseCommand();
        try {
            String every = cb.readCommand();
            Connection connection = new ConnectionFactory().getConnection();
            String[] st = every.split(";");
            for (String query:
                 st) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.execute();
                ps.close();
            }
        }catch (Exception e)
        {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
