package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.AddressMapper;
import com.mtgz.xw.api.dao.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiAddressService {
    @Autowired
    private AddressMapper addressDao;


    public Address selectByPrimaryKey(Integer id) {
        return addressDao.selectByPrimaryKey(id);
    }


    public List<Address> selectByMap(Map<String, Object> map) {
        return addressDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return addressDao.countByMap(map);
    }


    public void save(Address address) {
        addressDao.save(address);
    }


    public void update(Address address) {
        addressDao.updateByPrimaryKey(address);
    }


    public void delete(Integer id) {
        addressDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        addressDao.deleteBatch(ids);
    }

}
