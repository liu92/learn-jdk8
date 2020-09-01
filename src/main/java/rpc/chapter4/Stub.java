package rpc.chapter4;

import rpc.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @ClassName: Stub
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:42
 * History:
 * @<version> 1.0
 */
public class Stub {

    public static IUserService getStub() {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket s = new Socket("127.0.0.1", 8888);

                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                oos.writeUTF(methodName);
                oos.writeObject(args);
                oos.flush();


                DataInputStream dis = new DataInputStream(s.getInputStream());
                int receivedId = dis.readInt();
                String name = dis.readUTF();

                User u = new User(receivedId, name);
                System.out.println("u: " + u);
                oos.close();
                s.close();

                return  u;
            }
        };
        Object o = Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class}, h);
        return (IUserService)o;
    }
}
