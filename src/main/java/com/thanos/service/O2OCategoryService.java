package com.thanos.service;




import com.thanos.model.O2OCategory;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thanos Yu on 2017/3/28.
 */
public interface O2OCategoryService {
    /**
     * fetch O2OCategory by id
     * @param id O2OCategory id
     * @return O2OCategory
     */
    public O2OCategory get(Integer id);

    /**
     * save O2OCategory
     * @param model O2OCategory
     * @return null
     */
    public Serializable save(O2OCategory model) ;

    /**
     * update O2OCategory
     * @param model O2OCategory
     * @return null
     */
    public Object update(O2OCategory model);

    /**
     * fetch O2OCategory
     * @return List<O2OCategory>
     */
    public List<O2OCategory> getAll();
}
