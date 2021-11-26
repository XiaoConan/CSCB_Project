package com.example.cscb_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class UserWriter {

    public void bufferWriter(ArrayList<UserAccount> userList, String fileName, boolean append, char symbol) {
        File file = new File(fileName);
        try {
            FileWriter fw = new FileWriter(file, append);
            for(UserAccount n:userList)
                fw.write(symbol + " " +n.getUsername() + " " + n.getPassword() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void write(ArrayList<UserAccount> userList, String fileName, boolean append);
}
