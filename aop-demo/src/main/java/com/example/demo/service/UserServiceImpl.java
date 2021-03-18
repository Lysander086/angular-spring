package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userDao;
    @Autowired
    EntityManager em;

    @Override
    public Integer addUser(User user) {
        userDao.save(user);
        Integer id = user.getId();
        user.setName("1" + user.getName());
        userDao.save(user);
        return id;
    }

    public List<User> getByExample(String name) {
        User user = new User();
        Department department = new Department();
        user.setName(name);
        department.setId(1);
        Example<User> example = Example.of(user);
        List<User> list = userDao.findAll(example);
        return list;
    }

    @Override
    public List<User> getAllUser(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<User> pageObject = userDao.findAll(pageable);
//        int totalPage = pageObject.getTotalPages();
//        int realSize = pageObject.getSize();
//        long count = pageObject.getTotalElements();

        return pageObject.getContent();
    }

    public Page<User> queryUser(Integer departmentId, Pageable page) {

        StringBuilder baseJpql = new StringBuilder("from User u where 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (departmentId != null) {
            baseJpql.append("and u.department.id=:deptId ");
            params.put("depId", departmentId);
        }

        // 查询满足条件的总数
        long count = getQueryCount(baseJpql, params);
        if (count == 0) {
            return new PageImpl<>(Collections.emptyList(), page, 0);
        }

        List list = getQueryResult(baseJpql, params, page);

        /* return result */
        Page res = new PageImpl(list, page, count);
        return res;

    }

    private List getQueryResult(StringBuilder baseJpql, Map<String, Object> params, Pageable page) {
        Query query = em.createQuery("select u " + baseJpql.toString());
        setQueryParameter(query, params);
        // Query 对象在查询结果集的时候，提供 setFirstResult 来设置查询的起始位置，
        query.setFirstResult((int) page.getOffset());
        query.setMaxResults(page.getPageNumber());
        List list = query.getResultList();
        return list;
    }

    private Long getQueryCount(StringBuilder baseJpql, Map<String, Object> params) {
        Query query = em.createQuery("select count(1) " + baseJpql.toString());
        setQueryParameter(query, params);
        Number number = (Number) query.getSingleResult();
        return number.longValue();
    }

    private void setQueryParameter(Query query, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
