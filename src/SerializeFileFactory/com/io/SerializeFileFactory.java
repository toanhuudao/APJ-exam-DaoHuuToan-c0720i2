package SerializeFileFactory.com.io;

import community.com.model.Contact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SerializeFileFactory {

    public static HashMap<Integer, Contact> csdl;


    public static boolean saveFile(HashMap<Integer, Contact> csdl, String path) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(csdl);
            objectOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static HashMap<Integer, Contact> readFile(String path) {

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object data = objectInputStream.readObject();
            csdl= (HashMap<Integer, Contact>) data;
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return csdl;
    }

}