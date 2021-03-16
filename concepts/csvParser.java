import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    ArrayList<String> lines = new ArrayList<String>();

    public void convertToCVSLine(String[] data){
        String s = Stream.of(data).map(this::escapeSpecialCharacters).
                collect(Collectors.joining(","));

	lines.add(s);
    }

    public void createCVS(String name) throws IOException {
        String nameR = name + ".csv";
        try {
            File myfile = new File(name);
            if(myfile.createNewFile()) {
                System.out.println("File created: " + myfile.getName());

                //start writing in file here
                FileWriter writer = new FileWriter(name);
                for(int i = 0; i < lines.size(); i++) {
                    writer.write(lines.get(i));
                    writer.newLine();
                }
                writer.close();
                System.out.println("Wrote to file successfully");

            }
            else {
                System.out.println("File already exist");
            }

        }catch(IOException e) {
            System.out.println("a error took place");
            e.printStackTrace();
        }
    }
}