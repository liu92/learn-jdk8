package rpc.chapter7;

import rpc.Product;
import rpc.User;

/**
 * @ClassName: IProductService
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:21
 * @History:
 * @<version> 1.0
 */
public interface IProductService {
    /**
     * 查询
     * @param id
     * @return
     */
    Product findUserById(Integer id);
}
