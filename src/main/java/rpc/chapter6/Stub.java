package rpc.chapter6;

import rpc.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 *  但是这里仅仅实现了findByUserId的方法代理，如果要实现其他方法的代理该怎么做呢？
 *  这了 就要从协议层做出改进
 *
 *  服务端也要做出对应处理
 * @ClassName: Stub
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:42
 * History:
 * @<version> 1.0
 */
public class Stub {

    public static Object getStub(Class clazz) {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket s = new Socket("127.0.0.1", 8888);

                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                String clazzName = clazz.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                oos.writeUTF(clazzName);
                oos.writeUTF(methodName);
                //方法的参数类型
                oos.writeObject(parameterTypes);
                //再写方法的具体参数
                oos.writeObject(args);
                oos.flush();


                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User u = (User) ois.readObject();
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
