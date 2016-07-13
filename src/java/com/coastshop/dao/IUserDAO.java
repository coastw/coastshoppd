/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;
import com.coastshop.vo.User;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IUserDAO {
    public boolean doCreate(User user) throws Exception;
    public boolean doDelete(int id) throws Exception;
    public boolean doUpdate(User user) throws Exception;
    public User getById(int id) throws Exception;
    public User getByName(String name) throws Exception;
    public List<User> getAll() throws Exception;
    public List<User> getAll(String keyword) throws Exception;
    public int getCount() throws Exception;
}
