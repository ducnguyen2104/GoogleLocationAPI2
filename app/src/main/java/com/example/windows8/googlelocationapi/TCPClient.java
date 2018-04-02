package com.example.windows8.googlelocationapi;

import android.os.Build;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Windows 8 on 16/10/2017.
 */

class TCPClient extends Thread {
    public String ipA, portA, messFromA;
    public TCPClient(String iA, String pA) {
        ipA = iA; portA = pA;
    }
    public void run() {
        Socket sT = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            sT = new Socket(ipA,Integer.parseInt(portA));
            //sT = new Socket("192.168.1.24",2911);
            /*DataOutputStream outToServer = new DataOutputStream(sT.getOutputStream());
            DataInputStream inFromServer = new DataInputStream(sT.getInputStream());
            outToServer.writeBytes("001" + '\n');
            messFromA = inFromServer.readLine();
            outToServer.close();
            inFromServer.close();*/
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sT.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(sT.getInputStream()));
            bw.append(Build.MODEL).append("\n");
            bw.flush();
            while (br.readLine() != null) {
                messFromA = messFromA + br.readLine();
            }
            br.close();
            bw.close();
            sT.close();
        }
        catch(UnknownHostException e) {
            messFromA = "Server not found";
            //System.exit(1);
        }catch (IOException e) {
            messFromA = "Cant connect";
            e.printStackTrace();
            //System.exit(1);
        }
    }
}