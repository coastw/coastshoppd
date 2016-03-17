/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coastshop.service;

import com.coastshop.vo.Discount;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IDiscountService {
    public int add(Discount discount) throws Exception;
    public int update(Discount discount) throws Exception;
    public Discount getById(int id) throws Exception;
    public List<Discount> getAll() throws Exception;
}
