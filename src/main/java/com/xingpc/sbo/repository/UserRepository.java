package com.xingpc.sbo.repository;

import com.xingpc.sbo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/3/25 17:42
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
