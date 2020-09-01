package rpc.chapter6;



import java.io.IOException;

/**
 * 第六个版本， 动态代理
 * 返回任意类型的对象
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
        IUserService service = (IUserService) Stub.getStub(IUserService.class);
        System.out.println(service.findUserById(123));
    }
}
