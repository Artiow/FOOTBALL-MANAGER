package ru.vldf.sportsportal.config;

import org.hibernate.SessionFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Value("${hibernate-property.show_sql}")
    private String SHOW_SQL;
    @Value("${hibernate-property.hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate-property.hibernate.connection.useUnicode}")
    private String HIBERNATE_USE_UNICODE;
    @Value("${hibernate-property.hibernate.connection.characterEncoding}")
    private String HIBERNATE_CHARACTER_ENCODING;

    private Properties getHibernateProperties() {
        return new Properties() {
            {
                setProperty("show_sql", SHOW_SQL);
                setProperty("hibernate.dialect", HIBERNATE_DIALECT);

                setProperty("hibernate.connection.useUnicode", HIBERNATE_USE_UNICODE);
                setProperty("hibernate.connection.characterEncoding", HIBERNATE_CHARACTER_ENCODING);
            }
        };
    }

    @Bean(name = "dataSource")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        String URL = "jdbc:mysql://localhost:3306/sportsportal?"
                + "autoReconnect=true&"
                + "autoReconnectForPools=true&"
                + "useJDBCCompliantTimezoneShift=true&"
                + "useLegacyDatetimeCode=false&"
                + "serverTimezone=Europe/Moscow";

        dataSource.setUrl(URL);
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("ru.vldf.sportsportal.model");
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setDataSource(getDataSource());

        return sessionFactoryBean;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }
}