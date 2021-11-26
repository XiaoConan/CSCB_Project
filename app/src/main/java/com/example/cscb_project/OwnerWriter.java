package com.example.cscb_project;

import java.util.ArrayList;

public class OwnerWriter extends UserWriter{

    @Override
    public void write(ArrayList<UserAccount> userList, String fileName, boolean append) {
        bufferWriter(userList, fileName, append, 'O');
    }

}
