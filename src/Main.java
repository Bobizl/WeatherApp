import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String file = "src\\WeatherReport.csv";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        try{
            while((line = reader.readLine()) != null){

                String[] row = line.split(";");
                for(String index : row){
                    System.out.printf("%-20s", index);
                }
                System.out.println();
            }
        }catch(Exception ex ){
            ex.printStackTrace();
        }finally {
            reader.close();
        }
    }
}