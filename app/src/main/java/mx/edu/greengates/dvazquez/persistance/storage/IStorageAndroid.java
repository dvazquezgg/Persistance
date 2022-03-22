package mx.edu.greengates.dvazquez.persistance.storage;

import android.content.Context;

import java.io.IOException;

import mx.edu.greengates.dvazquez.persistance.model.MyFile;

/**
 * This interface includes two methods for reading ad writing to the storage (a File, Database, etc.)
 *
 */
public interface IStorageAndroid {
    /**
     *
     * @param context receives the context from where you are calling the method (Activity)
     * @param management receives the MyFile Object that you want to persist
     * @throws IOException throws an exception if you can not access the storage
     */
    public void updateManagementData(Context context, MyFile management) throws IOException;

    /**
     *
     * @param context   receives the context from where you are calling the method (Activity)
     * @return          returns a MyFile Objects after reading from storage
     * @throws IOException  throws an exception if you can not access the storage
     */
    public MyFile getManagementData(Context context) throws IOException;
}
