package DAO;

import java.util.List;

/**
 *
 * @author Ivens
 */
public class ContactDao implements Dao{
    
    private String sqlFindContacts = "SELECT * FROM CONTACT A WHERE A.ID_USER = ?";

    @Override
    public void insert(Object obj) {
        
    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public List<Object> getObjects(String whereCondition) {
        return null;
    }

    @Override
    public Object getObjById(String id) {
        return null;
    }

    @Override
    public Object getObjByUnique(String whereUnique) {
        return null;
    }
    
}
