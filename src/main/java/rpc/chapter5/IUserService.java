package rpc.chapter5;

import rpc.User;

/**
 * @ClassName: IUserService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:21
 * @History:
 * @<version> 1.0
 */
public interface IUserService {
    /**
     * 查询
     * @param id
     * @return
     */
    User findUserById(Integer id);
}
