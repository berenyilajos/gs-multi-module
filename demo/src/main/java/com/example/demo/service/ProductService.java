package com.example.demo.service;

import com.example.demo.bd.BDProduct;
import com.example.demo.helper.EntityHelper;
import com.example.jpademo2.transactions.Demo2Transactional;
import com.example.jpademo2.dao.ProductEntityDao;
import com.example.jpademo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductEntityDao productEntityDao;

    public List<BDProduct> findAll() {
        return EntityHelper.entityToBdProducts(productRepository.findAll());
    }

    public List<BDProduct> getAllProducts() {
        return EntityHelper.entityToBdProducts(productEntityDao.findAll());
    }

    public List<BDProduct> findByName(String name) {
        return EntityHelper.entityToBdProducts(productRepository.findByName(name));
    }

    public List<BDProduct> getAllProductsByName(String name) {
        return EntityHelper.entityToBdProducts(productEntityDao.findByName(name));
    }

    public List<BDProduct> findProductsCheaperThen(BigDecimal price) {
        return EntityHelper.entityToBdProducts(productEntityDao.findProductsCheaperThen(price));
    }

    public BDProduct addProduct(BDProduct bdProduct) {
        return EntityHelper.entityToBd(productRepository.save(EntityHelper.bdToEntity(bdProduct)));
    }

    @Demo2Transactional
    public BDProduct save(BDProduct bdProduct) {
        return EntityHelper.entityToBd(productEntityDao.save(EntityHelper.bdToEntity(bdProduct)));
    }
}
