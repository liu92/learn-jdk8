package rpc.chapter4;

import rpc.User;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: Server
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:33
 * History:
 * @<version> 1.0
 */
public class Server {
   private static  boolean running = true;

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ServerSocket ss = new ServerSocket(8888);
        while (running){
            Socket s = ss.accept();
            process(s);
            s.close();
        }
    }

    private static  void  process(Socket s) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InputStream in = s.getInputStream();
        OutputStream out  = s.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();

        IUserService service = new UserServiceImpl();
        Method method = service.getClass().getMethod(methodName, parameterTypes);

        User user = (User) method.invoke(service, args);

        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(user);
        oos.flush();

    }












}
