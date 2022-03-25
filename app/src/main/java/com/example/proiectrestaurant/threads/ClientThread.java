package com.example.proiectrestaurant.threads;

import android.util.Log;
import android.widget.TextView;

import com.example.proiectrestaurant.utils.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;
    private TextView resultView;

    public ClientThread(String ipAddress, int port, String sendMsg, TextView resultView) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.sendMsg = sendMsg;
        this.resultView = resultView;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(ipAddress, port);

            PrintWriter printWriter = Helper.getWriter(clientSocket);
            printWriter.println(sendMsg+"\r\n");
            Log.e(Constants.TAG,"before readline");

            BufferedReader bufferedReader = Helper.getReader(clientSocket);
            final String result = bufferedReader.readLine();
            resultView.post(new Runnable(){
                @Override
                public void run() {
                    resultView.append(result + "\n");

                }
            });
        } catch (UnknownHostException unknownHostException) {
            Log.e(Constants.TAG, unknownHostException.getMessage());
            if (Constants.DEBUG) {
                unknownHostException.printStackTrace();
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        }
    }
}
