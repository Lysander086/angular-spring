package com.example.jpademo.repo;

import com.example.jpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    /* !!! 方法名和参数名需要遵守一定的规则, Spring Data JPA 才能自动转化为 JPQL:
     * - 方法名通常包含多个实体属性用于查询,属性之间可以使用 AND 和 OR 连接,也支持Between 、LessThan 、GreaterThan 、Like;
     * - 方法名可以以 findBy 、getBy 、queryBy 开头;
     * - 查询结果可以排序, 方法名包含OrderBy＋属性＋ASC(DESC);
     * - 可以通过Top 、First 来限定查询结果集;
     * */

    public User findByName(String name);

}
