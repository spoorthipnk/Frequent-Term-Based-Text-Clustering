package Home;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Testing {

    public Testing(ArrayList al) throws FileNotFoundException, IOException {
        int num = 0;
        FileOutputStream fos = new FileOutputStream("Count.txt");
        int i = 0, j = 0, k = 0;
        String s = null;
        for (i = 0; i < al.size(); i++) {
            ArrayList f1 = (ArrayList) al.get(i);
            for (j = 0; j < al.size(); j++) {
                ArrayList f2 = (ArrayList) al.get(j);
                int count = 0;
                for (k = 0; k < f1.size(); k++) {
                    String val = null;
                    int temp1 = Integer.parseInt(f1.get(k).toString());
                    int temp2 = Integer.parseInt(f2.get(k).toString());
                    if ((temp1 == 1) && (temp2 == 1)) {
                        val = "0";
                        System.out.print("u \t");
                    } else {
                        val = "u";
                        System.out.print("0 \t");
                    }
                    if (val.equals("0")) {
                        count++;
                    }
                }
                System.out.println("Count is: " + count);
                System.out.print("\n");
                num = count;
                s = i + "," + j + "=" + num + " ";
                fos.write(s.getBytes());
            }
            String s1 = "\r\n";
            fos.write(s1.getBytes());
            fos.flush();
        }
    }
}
