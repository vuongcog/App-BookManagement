package com.example.doan;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBConfigUtil {
    final static String DATABASE_NAME = "doan10.sqlite";
    final static String DB_PATH_SUFFIX = "/databases/";
    public static void coppyDatabaseFromAssets(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
                    File dbDir = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!dbDir.exists()) dbDir.mkdir();
            InputStream is = null;
            OutputStream os = null;
            try {
                is = context.getAssets().open(DATABASE_NAME);
                String outputFilePath = context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
                os = new FileOutputStream(outputFilePath);
                byte[] buffer = new byte[1024];
                int lentgth;
                while ((lentgth = is.read(buffer))> 0){
                    os.write(buffer,0,lentgth);
                }
                os.flush();
                Toast.makeText(
                        context, "Đã chép CSDL xong",
                        Toast.LENGTH_LONG
                ).show();

            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG);
            } finally {
                try {
                    os.close();

                } catch (IOException e) {
                }
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
