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
    private AddressMapper addressMapper;


    public Address selectByPrimaryKey(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }


    public List<Address> selectByMap(Map<String, Object> map) {
        return addressMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return addressMapper.countByMap(map);
    }


    public void save(Address address) {
        addressMapper.save(address);
    }


    public void update(Address address) {
        addressMapper.updateByPrimaryKey(address);
    }


    public void delete(Integer id) {
        addressMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        addressMapper.deleteBatch(ids);
    }

}
