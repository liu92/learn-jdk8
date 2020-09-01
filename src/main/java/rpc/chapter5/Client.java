package rpc.chapter5;



import java.io.IOException;

/**
 * 第三个版本，使用代理模式， 动态代理
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
        IUserService service = Stub.getStub();
        System.out.println(service.findUserById(123));
    }
}
