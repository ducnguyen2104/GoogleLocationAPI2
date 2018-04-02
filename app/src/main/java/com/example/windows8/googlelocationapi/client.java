package com.example.windows8.googlelocationapi;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

/**
 * Created by Windows 8 on 13/10/2017.
 */

class UDPClient extends AsyncTask<String, Integer, Boolean> {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    String ipG, portG, messToG;
    public UDPClient(String iG, String pG, String mG) {
        ipG = iG; portG = pG; messToG = mG;
    }

    @Override
    protected Boolean doInBackground(String... arg0) {
        DatagramSocket sU = null;
        DatagramPacket p ;
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        try {
            Calendar cal = Calendar.getInstance();
            sU = new DatagramSocket();
            messToG = messToG + dateFormat.format(cal.getTime());
            byte[] data = messToG.getBytes();
            p = new DatagramPacket(data, data.length, java.net.InetAddress.getByName(ipG), parseInt(portG));
            sU.send(p);
            Thread.sleep(1000*10);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        sU.close();
        return null;
    }
}
