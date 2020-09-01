package rpc.chapter6;

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

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        while (running){
            Socket s = ss.accept();

        }
    }

    private static  void  process(Socket s) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InputStream in = s.getInputStream();
        OutputStream out  = s.getOutputStream();
        ObjectInputStream oos = new ObjectInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);

        String methodName = oos.readUTF();
        Class[] parameterTypes = (Class[]) oos.readObject();
        Object[] args = (Object[]) oos.readObject();
        IUserService service = new UserServiceImpl();
        Method method = service.getClass().getMethod(methodName, parameterTypes);

        User user = (User) method.invoke(service, args);

        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }












}
