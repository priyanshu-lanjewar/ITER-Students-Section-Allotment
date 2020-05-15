import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class Details
{
    private static String name ="Not Found",regno="Not Found",branch="Not Found",section="Not Found";
    private static String Registration_number=null;


    public void setRegistration_number(String registration_number) {
        Registration_number = registration_number;
    }

    public String getName() {
        return name;
    }

    public String getRegno() {
        return regno;
    }

    public String getBranch() {
        return branch;
    }

    public String getSection() {
        return section;
    }

    public void fetch() throws IOException {

        URL url = new URL("http://saat.soa.ac.in/section/sectionallotmentdetails.php?REGDNO="+Registration_number+"&login=");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        int c = 0;

        while ((line = br.readLine()) != null) {
            switch (c){
                case 76:
                while (line.charAt(0) == ' ') {
                    line = line.replaceFirst(" ", "");
                }
                name = line;
                name = name.replace("<td>", "");
                name = name.replace("</td>", "");
                break;
                case 77:
                    while (line.charAt(0) == ' ') {
                        line = line.replaceFirst(" ", "");
                    }
                    regno = line;
                    regno = regno.replace("<td>", "");
                    regno = regno.replace("</td>", "");
                    break;
                case 78:
                    while (line.charAt(0) == ' ') {
                        line = line.replaceFirst(" ", "");
                    }
                    branch = line;
                    branch = branch.replace("<td>", "");
                    branch = branch.replace("</td>", "");
                    break;
                case 79:
                    while (line.charAt(0) == ' ') {
                        line = line.replaceFirst(" ", "");
                    }
                    section = line;
                    section = section.replace("<td>", "");
                    section = section.replace("</td>", "");
                    break;
            }
            c++;

        }
        if(name.equals("") || regno.equals("") || branch.equals("") || section.equals("")){
            name ="Not Found";
            regno="Not Found";
            branch="Not Found";
            section="Not Found";
        }

    }
}

public class StudentDetails
{
    public static void main(String[] args) throws IOException {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        Details student =new Details();
        while (true)
        {
            System.out.print("Enter 'E' to exit or Enter Registration Number to get details : ");
            String inp = b.readLine();
            if(inp.equalsIgnoreCase("E"))
                System.exit(0);
            else
            {
                student.setRegistration_number(inp);
                System.out.println("Processing.....");
                student.fetch();
                System.out.println("Student Name        : "+student.getName());
                System.out.println("Registration Number : "+student.getRegno());
                System.out.println("Branch              : "+student.getBranch());
                System.out.println("Section             : "+student.getSection());
            }
        }
    }
}
