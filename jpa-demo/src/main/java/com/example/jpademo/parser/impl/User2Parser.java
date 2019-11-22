package com.example.jpademo.parser.impl;

import com.example.jpademo.bd.BDUser;
import com.example.jpademo.parser.JsonParser;
import com.example.jpademo.parser.XmlParser;
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
