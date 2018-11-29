package DAO;

import java.util.List;

/**
 *
 * @author Ivens
 */
public interface Dao {
    
    void insert(Object obj);
    void delete(Object obj);
    void update(Object obj);
    List<Object> getObjects(String whereCondition);
    Object getObjById(String id);
    Object getObjByUnique(String whereUnique);
    
}
