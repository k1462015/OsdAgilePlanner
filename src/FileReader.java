import java.io.*;

/**
 * Created by Tahmidul on 28/11/2015.
 */
public class FileReader {

    public static void main(String[] args){
        BufferedReader br;
        try{
            br = new BufferedReader(new java.io.FileReader("C:\\Users\\Tahmidul\\Desktop\\in.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                //First identify if it is a object deceleration
                if(checkIfContains(line,": Task",": Staff")){
                    System.out.println(line+" Contains object deceleration");
                }
                line = br.readLine();
            }
            String everything = sb.toString();
            br.close();
        }catch (IOException e){
            System.out.println("IO Exception");
        }
    }

    public static boolean checkIfContains(String subject,String...args){
        for (int i = 0;i < args.length;i++){
            if(subject.contains(args[i])){
                return true;
            }
        }
        return false;
    }
}
