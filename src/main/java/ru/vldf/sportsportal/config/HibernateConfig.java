package ru.vldf.sportsportal.config;

import org.hibernate.SessionFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("show_sql", "false");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }

    @Bean(name = "dataSource")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sportsportal");
        dataSource.setUsername("root");

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.setDataSource(getDataSource());

        return sessionFactoryBean.getObject();
    }

//    @Autowired
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory);
//
//        return transactionManager;
//    }
}
