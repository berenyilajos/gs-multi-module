package com.example.jpademo.parser.impl;

import com.example.jpademo.bd.BDProduct;
import com.example.jpademo.parser.JsonParser;
import com.example.jpademo.parser.XmlParser;
import com.example.jpademo.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

@Component
public class ProductParser extends JsonParser<BDProduct> /*implements Parser<BDProduct>*/ {
    public ProductParser() {
        super(BDProduct.class);
    }
}

//@Component
//public class ProductParser extends XmlParser<BDProduct> implements Parser<BDProduct> {
//
//}
