package com.x.spring.mongo;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-10-6
 * Time: 下午3:00
 * To change this template use File | Settings | File Templates.
 */
public class MongoRecord {
    private String name;
   // private String _id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }

    @Override
    public String toString() {
        return "MongoRecord{" +
                "name='" + name + '\'' +
              //  ", _id='" + _id + '\'' +
                '}';
    }
}
