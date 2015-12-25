package foo.bar;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import foo.bar.com.x.cache.TemplatePools;
import foo.bar.com.x.cache.shard.DbAccessor;
import foo.bar.com.x.cache.shard.rule.RouterRule;
import foo.bar.com.x.cache.shard.rule.UserHashRule;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongqiang
 * Date: 15-9-29
 * Time: 下午6:18
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDao {
    private MongoTemplate mongoTemplate;
    private DbAccessor<SqlMapClientTemplate> ibatisXaDbTemplateAccessor;
    private DbAccessor<SqlMapClientTemplate> ibatisSingleDbTemplateAccessor;
    private TemplatePools templatePools;
    public void xa(final int lotteryType,String userpin){


        Object f=  ibatisXaDbTemplateAccessor.routeTx(null).execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                boolean flag = true;
                try {
                    Map<String,String>params=new HashMap<String, String>();
                    params.put("physicTableName","amount");
                    ibatisXaDbTemplateAccessor.routeDb(new UserHashRule("pin")).update("Amount.updateAmount",params);
                    ibatisXaDbTemplateAccessor.routeDb(new UserHashRule("pin")).update("Amount.updateAmount",params);
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                } finally {
                    if (!flag) {
                        status.setRollbackOnly();
                    }
                }
                return flag;
            }
        }) ;
        System.out.println(f);
    }
    public void ixa(){

        Object f=  templatePools.getXaTransactionTemplate().execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                boolean flag = true;
                try {
                    int i = templatePools.getXaSqlTemplate(1).update("Amount.updateAmount");
                    int j = templatePools.getXaSqlTemplate(2).update("Amount.updateAmount");
                    System.out.println(i + "," + j);
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                } finally {
                    if (!flag) {
                        status.setRollbackOnly();
                    }
                }
                return flag;
            }
        }) ;
        System.out.println(f);
    }
    public void singleTx(){
//        templatePools.getTransactionTemplate(1);
        final RouterRule rule=new UserHashRule("pin");
        Object f=   ibatisSingleDbTemplateAccessor.routeTx(rule).execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                boolean flag = true;
                try {
//                    Map<String,Integer>param=new HashMap<String, Integer>();
//                    param.put("num",10000);
//                    Bean bean=new Bean();
//                    bean.setNum(1002);
                   Object o= ibatisSingleDbTemplateAccessor.routeDb(rule).queryForList("Amount.queryAmount1");
                    System.out.println(o);
//                    ibatisSingleDbTemplateAccessor.sqlTemplate(1).update("Amount.updateAmount1");
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                } finally {
                    if (!flag) {
                        status.setRollbackOnly();
                    }
                }
                return flag;
            }
        }) ;
        System.out.println(f);
    }
    public void defaultSingleTx(){
        Object f=   ibatisSingleDbTemplateAccessor.defaultTxTemplate().execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                boolean flag = true;
                try {
                    ibatisSingleDbTemplateAccessor.defaultSqlTemplate().update("Amount.updateAmount", new HashMap());
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                } finally {
                    if (!flag) {
                        status.setRollbackOnly();
                    }
                }
                return flag;
            }
        }) ;
        System.out.println(f);
    }
    public void mongocrud(){
        MongoRecord r=new MongoRecord();
        r.setName("xx1");
//        MongoRecord1 r1=new MongoRecord1();
//        r1.setName("zz");
        //不指定集合名称，就会插入一个以类名为名称的集合中
       //mongoTemplate.insert(r);
        mongoTemplate.insert(r, "cap1");

//        List rs=new ArrayList();
//        rs.add(r);
//        rs.add(r1);
//
//        mongoTemplate.insertAll(rs);
        //update//
//       DBObject db= QueryBuilder.start("name").is("zxx").get();
//        Update update=new Update();
//        update.set("name","zxx1");
//        mongoTemplate.setWriteResultChecking(WriteResultChecking.LOG);
//      WriteResult result= mongoTemplate.updateMulti(new BasicQuery(db), update, "cap1");
//
//        System.out.println(result);
        DBObject db= QueryBuilder.start("name").is("zxx1").get();
        //mongo只保证输入的参数是合法的，然后转换的流程由自己保证，最后肯定会执行，即使可能没有集合或者记录
        mongoTemplate.remove(new BasicQuery(db),"cap1");

       System.out.println(mongoTemplate.findAll(MongoRecord.class, "cap1"));
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setIbatisXaDbTemplateAccessor(DbAccessor ibatisXaDbTemplateAccessor) {
        this.ibatisXaDbTemplateAccessor = ibatisXaDbTemplateAccessor;
    }

    public void setIbatisSingleDbTemplateAccessor(DbAccessor ibatisSingleDbTemplateAccessor) {
        this.ibatisSingleDbTemplateAccessor = ibatisSingleDbTemplateAccessor;
    }

    public void setTemplatePools(TemplatePools templatePools) {
        this.templatePools = templatePools;
    }
}
