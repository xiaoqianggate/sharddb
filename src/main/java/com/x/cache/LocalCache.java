package com.x.cache;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-10-13
 * Time: 下午4:45
 * 尺寸控制，过期，单例
 */
public class LocalCache {
    private static LocalCache localCache=new LocalCache();
    private static final int MAX_ELEMENTS_NUM=10;
    private ReadWriteLock lock = new ReentrantReadWriteLock(false);

    /**
     * 单例控制
     */
    private LocalCache(){init();}
    public static LocalCache getLocalCache(){
        return localCache;
    }
    private LinkedHashMap<String,Object>cache;
    public Object get(String key){

        return isExpire(key)?null:cache.get(key);
    }
    public void put(String key,Object val){
       cache.put(key,val);
    }
    private void init(){
        /**
         * 控制尺寸 LRU
         */
        cache=new LinkedHashMap<String, Object>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return size()>MAX_ELEMENTS_NUM;
            }
        };
        expireMap=new ConcurrentHashMap<String, Long>();

        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   try {
                       TimeUnit.SECONDS.sleep(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                 evcit();
               }
            }
        });
        t.setDaemon(true);
        t.start();

    }
    /**
     * 过期策略
     */
    private void evcit(){
        Iterator<Map.Entry<String,Long>> it=  expireMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Long>entry=it.next();
            if(entry.getValue()<=new Date().getTime()){

                cache.remove(entry.getKey());
                it.remove();
            }
        }

    }
    private ConcurrentHashMap<String,Long>expireMap;
    public void put(String key,Object val,long millSeconds){
        putSafe(key, val);
        expireMap.put(key,new Date().getTime()+millSeconds);
    }
    public void put(String key,Object val,Date time){
        if(time==null){
            throw new IllegalArgumentException("过期时间不能为空");
        }else if(new Date().after(time)){
            throw new IllegalArgumentException("过期时间有误");
        }else{
            putSafe(key, val);
            expireMap.put(key,time.getTime());
        }

    }
    private void putSafe(String key,Object val){
        Lock lk=lock.writeLock();
        boolean lc=lk.tryLock();
        if(lc){
            cache.put(key,val);
            lk.unlock();
        }

    }
    private void removeSafe(String key){
        Lock lk=lock.writeLock();
        boolean lc=lk.tryLock();
        if(lc){
            cache.remove(key);
            lk.unlock();
        }

    }
    private Object getSafe(String key){
        Object val=null;
        Lock lk=lock.readLock();
        boolean lc=lk.tryLock();
        if(lc){
            val=cache.get(key);
            lk.unlock();
        }
        return val;

    }
    private boolean isExpire(String key){
        Long time=expireMap.get(key);
        if(time==null||new Date().getTime()<=time){
           return false;
        }else{
          return true;
        }
    }
    public void print(){
        System.out.println(cache.entrySet());
        System.out.println(expireMap.entrySet()+" "+new Date().getTime());

    }


}
