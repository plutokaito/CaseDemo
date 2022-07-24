package thinking.in.springboot.samples.spring50.bootstrap;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;
import thinking.in.springboot.samples.spring50.bean.TransactionalServiceBean;

import java.util.Map;


@EnableTransactionManagement // 开启事务
@Configuration
@ComponentScan(basePackageClasses = TransactionalServiceBean.class)
public class TransactionServiceBeanBootStrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(TransactionServiceBeanBootStrap.class);

        Map<String, TransactionalServiceBean> beansMap = context.getBeansOfType(TransactionalServiceBean.class);
        beansMap.forEach((beanName, bean) -> {
            System.out.printf("Bean 名称 : %s , 对象 : %s\n", beanName, bean);
            bean.save();
        });
        context.close();
    }

    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition)
                    throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {
                System.out.println("txManager : 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {
            }
        };
    }

    @Bean("txManager2")
    public PlatformTransactionManager txManger2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManger2 : 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
            }
        };
    }

}
