package s4.T000006; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Class;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}
*/


public class TestCase {
    public static void main(String[] args) {
        try {
            FrequencerInterface myObject;
            int freq;
            System.out.println("checking " + Frequencer.class.getTypeName());
            myObject = new s4.T000006.Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("H".getBytes());
            freq = myObject.frequency();
            System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
            if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred: STOP");
            System.exit(1);
        }

        if (args.length > 0){
            try {
                FrequenceTestFromFile(args[0]);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        }
        try {
            InformationEstimatorInterface myObject;
            double value;
            System.out.println("checking "+ InformationEstimator.class.getTypeName());
            myObject = new s4.T000006.InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            myObject.setTarget("0".getBytes());
            value = myObject.estimation();
            System.out.println(">0 "+value);
            myObject.setTarget("01".getBytes());
            value = myObject.estimation();
            System.out.println(">01 "+value);
            myObject.setTarget("0123".getBytes());
            value = myObject.estimation();
            System.out.println(">0123 "+value);
            myObject.setTarget("00".getBytes());
            value = myObject.estimation();
            System.out.println(">00 "+value);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred: STOP");
            System.exit(1);
        }
    }

    public static void FrequenceTestFromFile(String path) throws FileNotFoundException {
        FrequencerInterface myObject = new s4.T000006.Frequencer();
        System.out.println("checking " + Frequencer.class.getTypeName() + " with : " + path);
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader( new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                //System.out.print(line);
                if (line.isEmpty()) continue;
                String[] sp = line.split("\t");
                myObject.setSpace(sp[0].getBytes());
                myObject.setTarget(sp[1].getBytes());
                int freq = (myObject.frequency());
                if (freq != Integer.parseInt(sp[2])){
                    throw new AssertionError(line + "\tactual\t" + freq);
                }
                System.out.println("passed : " + line + "\t" + freq);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
            throw e;
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}

