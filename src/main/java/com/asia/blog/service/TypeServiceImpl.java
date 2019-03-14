package com.asia.blog.service;

import com.asia.blog.NotFoundException;
import com.asia.blog.dao.TypeRepository;
import com.asia.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }


    @Transactional
    @Override
    public Type getType(Long id) {
        Optional<Type> getId = typeRepository.findById(id);

        return getId.orElse(null);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
//        Type t = typeRepository.findOne(id);
        Optional<Type> getId = typeRepository.findById(id);
        if (getId.orElse(null) == null){
            throw new NotFoundException("不存在该类型");
        }

        BeanUtils.copyProperties(type,getId.orElse(null));
        return typeRepository.save(getId.orElse(null));
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
