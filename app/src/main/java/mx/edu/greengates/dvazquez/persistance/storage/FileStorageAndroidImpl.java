package mx.edu.greengates.dvazquez.persistance.storage;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mx.edu.greengates.dvazquez.persistance.model.MyFile;

/**
 * Implementation for the IStorageAndroid interface
 * The constructor receives the file name to store your object
 */
public class FileStorageAndroidImpl implements IStorageAndroid {

    private String fileName;

    public FileStorageAndroidImpl(String filename){
        this.fileName = filename;
    }

    @Override
    public void updateManagementData(Context context, MyFile management) throws IOException {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(management);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e){
            throw new IOException("Unable to access Application Data.");
        }
    }


    @Override
    public MyFile getManagementData(Context context) throws IOException {

        FileInputStream fileInputStream = context.openFileInput(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try{
            MyFile management = (MyFile) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return management;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new IOException("File not Found");
        }
    }
}



