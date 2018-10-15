package com.thanos.service;



import com.thanos.model.O2OCategory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thanos Yu on 2017/3/28.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class O2OCategoryServiceImpl implements O2OCategoryService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public O2OCategory get(Integer id){
        O2OCategory model = (O2OCategory) sessionFactory.getCurrentSession().get(O2OCategory.class,id);
        if (model!=null){
            Hibernate.initialize(model.getModules());
        }
        return model;
    }

    @Override
//    @Transactional(readOnly = false)
    @Transactional(readOnly = false, noRollbackFor = RuntimeException.class)
//    @Transactional(readOnly = false, noRollbackFor = Exception.class)
    public Serializable save(O2OCategory model) {
        sessionFactory.getCurrentSession().save(model);
        String s = null;
//        if (s.equals("")) {
//            int i = 0;
//        }
        return null;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Object update(O2OCategory model){
        O2OCategory user = (O2OCategory) sessionFactory.getCurrentSession().load(O2OCategory.class,model.getId());
        user.setTitle(model.getTitle());
        user.setContent(model.getContent());
        user.setVideo(model.getVideo());
        return "OK";
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<O2OCategory> getAll(){
        Query query = sessionFactory.getCurrentSession().getNamedQuery("O2OCategory.all");
        List<O2OCategory> list = query.list();
        if (list!=null&&!list.isEmpty()){
            for (O2OCategory model:list){
                Hibernate.initialize(model.getModules());
            }
        }
        return query.list();
    }

}
