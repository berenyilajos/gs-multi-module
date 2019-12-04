package com.example.demo.parser.impl;

import com.example.demo.bd.BDProduct;
import com.example.common.parser.base.JsonParser;
import org.springframework.stereotype.Component;

@Component
public class ProductParser extends JsonParser<BDProduct> /*implements Parser<BDProduct>*/ {
    public ProductParser() {
        super(BDProduct.class);
    }
}

//@Component
//public class ProductParser extends XmlParser<BDProduct> /*implements Parser<BDProduct>*/ {
//
//}
