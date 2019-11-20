package com.example.jpademo.parser.impl;

import com.example.jpademo.bd.BDUser;
import com.example.jpademo.parser.JsonParser;
import com.example.jpademo.parser.XmlParser;
import com.example.jpademo.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

@Component
public class UserParser extends JsonParser<BDUser> /*implements Parser<BDUser>*/ {
    public UserParser() {
        super(BDUser.class);
    }
}

//@Component
//public class UserParser extends XmlParser<BDUser> /*implements Parser<BDUser>*/ {
//
//}
