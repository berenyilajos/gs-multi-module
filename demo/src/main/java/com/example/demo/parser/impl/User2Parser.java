package com.example.demo.parser.impl;

import com.example.demo.bd.BDUser;
import com.example.demo.parser.XmlParser;
import org.springframework.stereotype.Component;

//@Component
//public class User2Parser extends JsonParser<BDUser> /*implements Parser<BDUser>*/ {
//    public User2Parser() {
//        super(BDUser.class);
//    }
//}

@Component
public class User2Parser extends XmlParser<BDUser> /*implements Parser<BDUser>*/ {

}
