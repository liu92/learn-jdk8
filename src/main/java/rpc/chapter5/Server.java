package rpc.chapter5;

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

    private static  void  process(Socket s) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        InputStream in = s.getInputStream();
        OutputStream out  = s.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);


        String className = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();
        
        Class clazz = null;
        
        //从服务注册表找到具体的类
        clazz = rpc.chapter6.UserServiceImpl.class;
        
        Method method = clazz.getMethod(methodName, parameterTypes);

        Object invoke = method.invoke(clazz.newInstance(), args);

        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeObject(invoke);
        oos.flush();
    }












}
