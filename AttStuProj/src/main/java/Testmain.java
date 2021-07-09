import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Testmain {

    public static void main(String[] args) {
        Set<String> attenceList = new TreeSet();
        String s = null;
        try {

            Process p = Runtime.getRuntime().exec("python ./py/LoginFR.py");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println("Java Here ::  " + s);
                String l = s.replaceAll("\\p{P}", "");
                if(attenceList.contains(l)) {
                    System.out.println("Already added ");
                }else {
                    attenceList.add(l);
                    String name = s +" ";
//                    String dates = dateFormat.format(date) + " ";
//                    Entry e1 = new Entry(name,dates);
                    // entry.setDate("dateFormat.format(date)");
                    //entry.setName(s);
//                    entryService.saveEntry(e1);
                }
            }
            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

       } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
            e.printStackTrace();
        }
    }
}


/*
*
* //        try (Interpreter interp = new SharedInterpreter()) {
//            interp.exec("from java.lang import System");
//            interp.exec("s = 'Hello World'");
//            interp.exec("System.out.println(s)");
//            interp.exec("print(s)");
//            interp.exec("print(s[1:-1])");
//        } catch (JepException e) {
//            e.printStackTrace();
//        }

//        String s = null;
//
//        try {
//
//            // run the Unix "ps -ef" command
//            // using the Runtime exec method:
//            Process p = Runtime.getRuntime().exec("python F:\\PythonProjects\\FR\\AttendanceProject.py");
//
//            BufferedReader stdInput = new BufferedReader(new
//                    InputStreamReader(p.getInputStream()));
//
//            BufferedReader stdError = new BufferedReader(new
//                    InputStreamReader(p.getErrorStream()));
//
//            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
//            while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
//
//            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//
//            System.exit(0);
//        }
//        catch (IOException e) {
//            System.out.println("exception happened - here's what I know: ");
//            e.printStackTrace();
//            System.exit(-1);
//        }

*
* **/