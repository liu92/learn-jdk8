package rpc.chapter2;


import rpc.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 第二个版本，将网络通信封装起来。因为不是每个人都清楚这个网络编程。
 * 但是这个非常不完善， 只能代理一个方法
 *
 *
 * @ClassName: Client
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:23
 * History:
 * @<version> 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
       Stub stub = new Stub();
        System.out.println(stub.findUserById(123));
    }
}
