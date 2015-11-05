import java.io.*; 
import java.util.List; 
import java.util.Vector; 

public class TestGenAllocator 
{ public static final int dim = 100;
  // Number of entities = dim, attributes = dim*dim

  public static void main(String[] args)
  { File file = new File("./in.txt");
    try
    { PrintWriter out = new PrintWriter(
                              new BufferedWriter(
                                   new FileWriter(file)));
      writeModel(out); 
      out.close();
    }
    catch (IOException ex)
    { System.out.println("Error generating test"); } 
  } 

  public static void writeModel(PrintWriter out)
  { out.println("s : Schedule");
    
    for (int i = 0; i < dim; i++)
    { String cname = "t" + i;
      out.println(cname + " : Task");
      out.println(cname + ".taskId = \"" + cname + "\"");
      out.println(cname + ".duration = " + i/2); 
    } 

    for (int i = 0; i < dim; i++)
    { String pkname = "s" + i;
     
      out.println(pkname  + " : Staff");
      out.println(pkname + ".staffId = \"" + pkname + "\"");
      out.println(pkname + ".costDay = " + i/5); 
    }

  }
}