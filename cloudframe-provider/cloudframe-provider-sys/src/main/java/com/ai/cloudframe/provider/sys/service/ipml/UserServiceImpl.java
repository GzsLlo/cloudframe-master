package com.ai.cloudframe.provider.sys.service.ipml;

import com.ai.cloudframe.provider.sys.entity.User;
import com.ai.cloudframe.provider.sys.mapper.UserMapper;
import com.ai.cloudframe.provider.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-05-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
