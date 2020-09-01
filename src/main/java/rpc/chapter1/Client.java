package rpc.chapter1;

import rpc.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 最原始RPC ,这种方式是将 其转换成二进制的形式,
 * 但是这种方式特别麻烦，如果修改属性，那么响应的也需要
 *
 * 在网络传输中 都是通过二进制方式来传输的
 * @ClassName: Client
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:23
 * History:
 * @<version> 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 8080);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        dos.write(123);
        s.getOutputStream().write(bos.toByteArray());
        s.getOutputStream().flush();

        DataInputStream dis = new DataInputStream(s.getInputStream());
        int id = dis.readInt();
        String name = dis.readUTF();

        User u = new User(id, name);
        System.out.println("u: " + u);
        dos.close();
        s.close();
    }
}
