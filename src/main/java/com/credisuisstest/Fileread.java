package com.credisuisstest;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

public class Fileread
{
    String s;
    Map<String,Integer> m1=new HashMap<String, Integer>();
    Map<String, String[]> m2 = new HashMap<String,String[]>();
    boolean dbop=true;

    public Fileread(String s) {this.s=s;};

    public boolean filevalidation()
    {
        if ((s.substring(s.length()-5))==".json")
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void filereadmethod()
    {
        if (!(s.substring(s.length()-5)).equals(".json"))
        {
            return;
        }
        JSONParser parser = new JSONParser();
        {
            try {
                FileReader reader = new FileReader(s);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                Iterator<?> i = jsonArray.iterator();

                while (i.hasNext()) {
                    JSONObject obj = (JSONObject) i.next();
                    String id = (String) obj.get("id");
                    String state = (String) obj.get("state");
                    String type = (String) obj.get("type");
                    String host = (String) obj.get("host");
                    long tmpstmp = (Long) obj.get("timestamp");

                    if (m1.containsKey(id))
                    {
                        m1.put(id,Math.abs((int)m1.get(id)-(int)tmpstmp));
                    }
                    else
                    {
                        m1.put(id,(int)tmpstmp);
                        if (type!=null && host!=null)
                        {
                            String[] stra=new String[2];
                            stra[0]=type;
                            stra[1]=host;
                            m2.put(id,stra);

                        }
                    }
                }


            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File not found");
            } catch (ParseException e) {
                System.out.println("error in file upload");
            }
            catch (Exception e)
            {
                System.out.println("file error");
            }

        }
    }

    public void  dboperations() throws Exception {
        if (m1.isEmpty()) {
            return;
        }


        String INSERT_LOG_DETAILS = " INSERT INTO LOGTABLE (eventid, eventduration, type, host, alert) " +
                " VALUES (?, ?, ?, ?, ?);";
        Connection con = new Dbaccess().getConnection();
        String createlogtable = readToString("sql/logtable.sql");

        try{
            con.createStatement().executeUpdate(createlogtable);
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_LOG_DETAILS);
            for (String str:m1.keySet())
            {
                if (m1.get(str)>=4)
                {
                        preparedStatement.setString(1, str);
                        preparedStatement.setString(2, String.valueOf(m1.get(str)));
                        preparedStatement.setString(5, "True");

                        if (m2.containsKey(str))
                        {
                            preparedStatement.setString(3, m2.get(str)[0]);
                            preparedStatement.setString(4, m2.get(str)[1]);
                        }
                        else
                        {
                            preparedStatement.setString(3, "NA");
                            preparedStatement.setString(4, "NA");

                        }

                        preparedStatement.executeUpdate();


                }
            }
            PreparedStatement pst = con.prepareStatement("select * from LOGTABLE");
            pst.clearParameters();
            ResultSet rs = pst.executeQuery();

            List<Logdetail> logdetails = new ArrayList<>();
            while(rs.next()){
                logdetails.add(new Logdetail(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                        )
                );
            }

            for(Logdetail c : logdetails) {
                System.out.println(c);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            dbop=false;
        }
        finally {
            con.close();
        }
    }

    public boolean dbvalidation()
    {
        if (m1.isEmpty()|!filevalidation()) {
            return false;
        }
        else
        {
            return true;
        }

    }
    public static String readToString(String fname) throws Exception {
        File file = new File(fname);
        String string = FileUtils.readFileToString(file, "utf-8");
        return string;
    }

}
