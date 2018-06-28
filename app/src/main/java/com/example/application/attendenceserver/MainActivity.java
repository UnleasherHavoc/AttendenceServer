package com.example.application.attendenceserver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.application.attendenceserver.R.id.spinner3;

public class MainActivity extends AppCompatActivity {

        String msgReply = "PRESENT #" + "ROLLNO";
        static int SocketServerPORT = 6666;
        EditText port;
        TextView info, infoip, msg;
        String message = "";
        ServerSocket serverSocket;
        DatabaseHandler databaseHandler;
         String b="SLOT 2";
         int slot_no =0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            toolbar.setBackgroundResource(R.drawable.clg_logo);
            try {
                getSupportActionBar().setTitle("");
            } catch (Exception e) {
                e.printStackTrace();
            }



            Spinner spinner = (Spinner) findViewById(R.id.spinner3);

            // Initializing a String Array
            String[] SLOTS = new String[]{
                    "SLOT 1",
                    "SLOT 2",
                    "SLOT 3",
                    "SLOT 4",
                    "SLOT 5",
                    "SLOT 6",

            };

            // Initializing an ArrayAdapter
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    this,R.layout.spinner_item,SLOTS
            );
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(spinnerArrayAdapter);




            port=(EditText)findViewById(R.id.editText) ;
            infoip = (TextView) findViewById(R.id.infoip);
            msg = (TextView) findViewById(R.id.msg);
            info = (TextView) findViewById(R.id.textView3);

             infoip.setText(getIpAddress());
              info.setBackgroundColor(Color.RED);
            databaseHandler=new DatabaseHandler(this,null,null,1);



        }


    @SuppressLint("SetTextI18n")
    public void startServer(View view)

    {Spinner slotspinner=(Spinner)findViewById(spinner3);
        b=(String.valueOf(slotspinner.getSelectedItem()));

        switch(b){

            case "SLOT 1":{
                b="SLOTa";
                slot_no=1;
                break;
            }

            case "SLOT 2":{
                b="SLOTb";
                slot_no=2;
                break;
            }
            case "SLOT 3": {
                b="SLOTc";
                slot_no=3;
                break;
            }
            case "SLOT 4": {
                b="SLOTd";
                slot_no=4;
                break;
            }
            case "SLOT 5": {
                b="SLOTe";
                slot_no=5;
                break;
            }
            case "SLOT 6": {
                b="SLOTf";
                slot_no=6;
                break;
            }}
        if((port.getText().toString().equals(""))){
            Toast.makeText(this,"ENTER PORT NO-",Toast.LENGTH_SHORT).show();
            return;

        }

            if(Integer.parseInt(String.valueOf(port.getText().toString()))>3000&&Integer.parseInt(String.valueOf(port.getText().toString()))<=6666)
           {

               SocketServerPORT=Integer.parseInt(String.valueOf(port.getText().toString()));
               info.setBackgroundColor(Color.GREEN);
               info.setTextColor(Color.BLACK);
                   info.setText("SELECTED SLOT:="+slot_no+"\nSERVER STARTED AND PORT :"+SocketServerPORT);
                    ExecutorService executor = Executors.newFixedThreadPool(5);
                    executor.execute(new SocketServerThread() );
           }
        else
           {
               if (Integer.parseInt(String.valueOf(port.getText().toString()))<=3000)
               {    info.setBackgroundColor(Color.RED);
                   info.setTextColor(Color.BLACK);
                   info.setText("ENTER PORT  GREATER THAN 3000");
               }
               else{info.setBackgroundColor(Color.RED);

                   info.setTextColor(Color.BLACK);
                   info.setText("ENTER PORT  LESS THAN 6666");}


           }


    }

    public String getDATE(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date=new Date();
        return dateFormat.format(date);}


        @Override
        protected void onDestroy() {
            super.onDestroy();

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private class SocketServerThread extends Thread {


            int count = 0;

            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(SocketServerPORT);

                    try {
                        while (true) {
                             Socket socket = serverSocket.accept();
                            count++;
                           /* message += "#" + count + " from " + socket.getInetAddress()
                                    + ":" + socket.getPort()+getDATE()  ;
*/
                            message += "#" + count + " from "+socket.getInetAddress()
                            +"  "+"DATE :"+ getDATE()+"   "  ;
                         // a is used for saving student in database and b is used for slot
                            String a;


                            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            a = bufferedReader.readLine();
                            //Attend  attend1=new Attend(a);


                            switch(b){

                                case "SLOTa":{
                                    databaseHandler.addinfo(a,b);
                                    break;
                                }

                                case "SLOTb":{
                                    databaseHandler.addinfo(a,b);
                                    break;
                                }
                                case "SLOTc": {
                                    databaseHandler.addinfo(a,b);
                                    break;
                                }
                                case "SLOTd": {
                                    databaseHandler.addinfo(a, b);
                                    break;
                                }
                                case "SLOTe": {
                                    databaseHandler.addinfo(a,b);
                                    break;
                                }
                                case "SLOTf": {
                                    databaseHandler.addinfo(a,b);
                                    break;
                                }














                            }

                            message += msgReply+ "---"+ a+ "\n";


                              MainActivity.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                msg.setText(message);


                                }
                               });
                            Socketreply socketreply=new Socketreply(socket ,count);
                            socketreply.start();


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }
            }

        }


    public class Socketreply extends  Thread{
           Socket socket=null;
          int Cnt_NO=0;

        Socketreply( Socket socket,int Cnt_NO){this.socket=socket; this.Cnt_NO=Cnt_NO;}


        @Override
        public void run() {
            try {
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write(Cnt_NO);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }


        //to get ipaddress
        private String getIpAddress() {
            String ip = "";
            try {
                Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                        .getNetworkInterfaces();
                while (enumNetworkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = enumNetworkInterfaces
                            .nextElement();
                    Enumeration<InetAddress> enumInetAddress = networkInterface
                            .getInetAddresses();
                    while (enumInetAddress.hasMoreElements()) {
                        InetAddress inetAddress = enumInetAddress.nextElement();

                        if (inetAddress.isSiteLocalAddress()) {
                            ip += "SERVER ADDRESS: "
                                    + inetAddress.getHostAddress() + "\n";
                        }

                    }

                }

            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            return ip;
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement



        switch (id){

            case R.id.action_settings0:{Intent intent=new Intent(this,Main3Activity.class);
             intent.putExtra("Slots","SLOTa");
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();


                return true;
            }
            case R.id.action_settings1:{Intent intent=new Intent(this,Main3Activity.class);
                intent.putExtra("Slots","SLOTb");
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();


                return true;}
            case R.id.action_settings2:{Intent intent=new Intent(this,Main3Activity.class);
                intent.putExtra("Slots","SLOTc");
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();


                return true;}
            case R.id.action_settings3:{Intent intent=new Intent(this,Main3Activity.class);
                intent.putExtra("Slots","SLOTd");
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();

                return true;}
            case R.id.action_settings4:{Intent intent=new Intent(this,Main3Activity.class);
                intent.putExtra("Slots","SLOTe");
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();


                return true;}
            case R.id.action_settings5:{Intent intent=new Intent(this,Main3Activity.class);
                intent.putExtra("Slots","SLOTf");
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();


                return true;}


             default:{}



        }
      /*  if (id == R.id.action_settings) {



           Intent intent=new Intent(this,Main3Activity.class);

            startActivity(intent);
            Toast.makeText(getApplicationContext(),"VIEWING ATTENDENCE",Toast.LENGTH_SHORT).show();
            finish();

            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }


}
