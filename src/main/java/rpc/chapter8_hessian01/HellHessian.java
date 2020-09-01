package rpc.chapter8_hessian01;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import rpc.User;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: HellHessian
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:33
 * History:
 * @<version> 1.0
 */
public class HellHessian {
   private static  boolean running = true;

    public static void main(String[] args) throws Exception {
        User u = new User(1, "22");
        byte[] bytes = serialize(u);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("bytes: " + bytes[i]);
        }

        System.out.println(bytes.length);
        User u1 = (User) deserialize(bytes);
        System.out.println(u1);
    }

    /**
     * hessian序列化
     * @param o
     * @return
     * @throws Exception
     */
    public static  byte[]  serialize(Object o ) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return  bytes;
    }

    /**
     * hessian反序列化
     * @param bytes
     * @return
     * @throws Exception
     */
    public static  Object deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(bais);
        Object object = input.readObject();
        bais.close();
        input.close();
        return  object;
    }










}
