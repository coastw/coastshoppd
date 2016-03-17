/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.vo.User;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IUserService {
    public boolean insert(User user) throws Exception;
    public boolean update(User user) throws Exception;
    public boolean delete(int id) throws Exception;
    public User find(int id) throws Exception;
    public User find(String name) throws Exception;
    public List<User> findLikeName(String name) throws Exception;
}
