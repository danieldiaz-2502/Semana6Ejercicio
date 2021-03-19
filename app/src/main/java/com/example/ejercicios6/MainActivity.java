package com.example.ejercicios6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Button upFlecha, downFlecha, leftFlecha, rightFlecha, cambioColor;
    private BufferedWriter bwriter;
    private int movx=100,movy=100, r=255,g=0,b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upFlecha = findViewById(R.id.upFlecha);
        downFlecha = findViewById(R.id.downFlecha);
        leftFlecha = findViewById(R.id.leftFlecha);
        rightFlecha = findViewById(R.id.rightFlecha);
        cambioColor = findViewById(R.id.cambioColor);

        new Thread(
                ()-> {
                    try {
                        Socket socket = new Socket("192.168.1.1", 5000);
                        InputStream is = socket.getInputStream();
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bwriter = new BufferedWriter(osw);

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

        leftFlecha.setOnClickListener(
                (v) -> {
                    movx -= 10;
                    initClient();
                }
        );
        rightFlecha.setOnClickListener(
                (v) -> {
                    movx += 10;
                    initClient();
                }
        );

        upFlecha.setOnClickListener(
                (v) -> {
                    movy -= 10;
                    initClient();
                }
        );
        downFlecha.setOnClickListener(
                (v) -> {
                    movy += 10;
                    initClient();
                }
        );

        cambioColor.setOnClickListener(
                (v) -> {
                    r = new Random().nextInt(255) + 0;
                    g = new Random().nextInt(255) + 0;
                    b = new Random().nextInt(255) + 0;
                    initClient();
                }
        );

    }

    public void initClient() {
        Gson gson = new Gson();
        Circulo circulo = new Circulo(movx, movy,r,g,b);
        String conexion = gson.toJson(circulo);

        new Thread(()-> {
            try {
                bwriter.write(conexion+"\n");
                bwriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}