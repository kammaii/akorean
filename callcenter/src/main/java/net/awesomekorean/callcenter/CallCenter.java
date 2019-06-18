package net.awesomekorean.callcenter;

import java.io.*;

public class CallCenter {

  public void readCalls(String pathToFile) {

    if(pathToFile == null) {
      System.out.println("Oops, null file");
    }

    File callFile = new File(pathToFile);
    try {
      BufferedReader fileReader = new BufferedReader(new FileReader(callFile));
      String line = fileReader.readLine();
      while(line != null) {
        System.out.println(line);
        line = fileReader.readLine();
        // handle call
        // call "dispatchCall(...)"
      }


    } catch (FileNotFoundException e) {
      System.out.println("Oops, can't find file: " + pathToFile);
    } catch (IOException e) {
      System.out.println("Oops, io exception when trying to read file: "+ pathToFile);
    }

  }
}
