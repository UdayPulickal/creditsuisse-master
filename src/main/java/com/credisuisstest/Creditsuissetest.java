package com.credisuisstest;


public class Creditsuissetest {

    public static void main(String[] args) throws Exception {

        Fileread fl=new Fileread("/E:/Credit/creditsuisse-master/logfile.json");
        fl.filereadmethod();
        fl.dboperations();
    }
}


