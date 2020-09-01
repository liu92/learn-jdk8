package rpc.chapter9_hession02;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import rpc.User;

import java.io.*;

/**
 * @ClassName: HellHessianVsJdk
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:33
 * History:
 * @<version> 1.0
 */
public class HellHessianVsJdk {
   private static  boolean running = true;

    public static void main(String[] args) throws Exception {
        User u = new User(1, "22");
        System.out.println("hessian:" + hessianSerialize(u).length);
        System.out.println("jdk:" + jdkSerialize(u).length);
    }



    /**
     * hessian序列化
     * @param o
     * @return
     * @throws Exception
     */
    public static  byte[]  hessianSerialize(Object o ) throws Exception{
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


    private static byte[] jdkSerialize(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return  bytes;

    }

    public static  Object jdkDeserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream input = new ObjectInputStream(bais);
        Object object = input.readObject();
        bais.close();
        input.close();
        return  object;
    }






}
