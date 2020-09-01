package rpc.chapter2;

import rpc.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    public User findUserById(Integer id) throws IOException {
        Socket s = new Socket("127.0.0.1", 8080);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeInt(123);
        s.getOutputStream().write(bos.toByteArray());
        s.getOutputStream().flush();

        DataInputStream dis = new DataInputStream(s.getInputStream());
        int receivedId = dis.readInt();
        String name = dis.readUTF();

        User u = new User(id, name);
        System.out.println("u: " + u);
        dos.close();
        s.close();

        return  u;
    }
}
