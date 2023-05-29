package helper.file;


import builder.TableBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class JSONOperations {
    private final String file;

    public JSONOperations(String file) {
        this.file = file;
    }

    public ArrayList<TableBuilder> readFile(){

        ArrayList<TableBuilder> tableBuilders = new ArrayList<>();

        try {

            BufferedReader input = new BufferedReader(new FileReader(this.file));

            String line;
            JSONObject jsonObject;
            Object obj;

            while  ((line = input.readLine()) != null){

                obj = new JSONParser().parse(line);
                jsonObject = (JSONObject)obj;

                tableBuilders.add(new TableBuilder(
                        (int)(long)jsonObject.get("score"),
                        (String)jsonObject.get("name")));
            }

            input.close();

        } catch (IOException e) {
            e.printStackTrace();
            try {
                BufferedWriter pw = new BufferedWriter(new FileWriter(file));
                pw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return tableBuilders;
    }

    public void saveToFile(TableBuilder tableBuilder) {


        ArrayList<TableBuilder> tableBuilders = readFile();
        tableBuilders.add(tableBuilder);

        tableBuilders.sort((o1,o2)->o2.getScore()-o1.getScore());

        try {

            PrintWriter pw = new PrintWriter(file);
            JSONObject jsonObject;

            for (TableBuilder tb:tableBuilders) {

                jsonObject = new JSONObject();
                jsonObject.put("score", tb.getScore());
                jsonObject.put("name", tb.getName());

                pw.println(jsonObject.toJSONString());

            }

            System.out.println("Saved in File");
            pw.flush();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie odnaleziono pliku");
        }


    }

}
