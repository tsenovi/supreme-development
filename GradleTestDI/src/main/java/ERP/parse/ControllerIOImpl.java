package ERP.parse;

import java.io.*;
import java.util.Arrays;

public class ControllerIOImpl implements ControllerIO {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T loadFile(String fileName) {
        T data = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading from file!");
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return data;
    }
    
    @Override
    public <T> void overwriteFile(String fileName, T data) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error while writing to file!");
        }
    }
}
