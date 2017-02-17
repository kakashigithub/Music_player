package com.example.abhishek.music_player;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_1 extends AppCompatActivity {

    int k=0;
    ListView l1;
    List<String>z;
    public static ArrayList<String> FinalList=new ArrayList<>();
    public static ArrayList<File> FileList=new ArrayList<>();
   // File f6[];

    String k1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        //String list2[];

        List<File>sk1;
        File f1= Environment.getExternalStorageDirectory();
        File f2[]=f1.listFiles();
        //String[] list1=f1.list();
        //File fo[] = new File[0];
        for(int i=0;i<f2.length;i++)
        {
            recSearch(f2[i]);
        }



        l1=(ListView)findViewById(R.id.list_1);
        MyArrayAdapter adapter=new MyArrayAdapter(this,R.layout.mylist_songs,R.id.textView,FinalList,l1,FileList);
        l1.setAdapter(adapter);

    }
//    public  void send(File file)
//    {

    //}
    public void recSearch(File f)
    {
        File[] children=f.listFiles();

        for(File file:children)
        {
            if(file.isDirectory())
                recSearch(file);
            else if(file.isFile())
            {
                if(file.getName().endsWith(".mp3"))
                {
                    FinalList.add(file.getName());
                    FileList.add(file);
                }
            }
        }
    }


}
class MyArrayAdapter extends ArrayAdapter<String>
        {


            ListView l3;
            Context c;
            List<File>f4;
            boolean b=true;
            MediaPlayer m1;
            public MyArrayAdapter(Context context, int rid,int tid,List<String>strk,ListView l2,List<File>f3) {

                super(context, rid,tid,strk);
                l3=l2;
                c=context;
               f4=f3;

            }
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {


                View v= super.getView(position, convertView, parent);
                int p;
                l3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Uri uri=Uri.fromFile(f4.get(position));
                        Intent in=new Intent(Intent.ACTION_VIEW);
                        in.setDataAndType(uri,"audio/*");
                        c.startActivity(in);
                      //  String str=(String) parent.getItemAtPosition(position);
                        //Toast.makeText(c,str,Toast.LENGTH_SHORT).show();


                        /*String str=(String) parent.getItemAtPosition(position);
                        Toast.makeText(c,str,Toast.LENGTH_SHORT).show();
                        if(b) {
                            m1 = MediaPlayer.create(c, Uri.fromFile(f4[position]));
                            m1.start();
                            b=false;

                        }
                        else
                        {
                            m1.stop();
                            b=true;
                        }*/
                    }
                });


                return  v;

            }
        }