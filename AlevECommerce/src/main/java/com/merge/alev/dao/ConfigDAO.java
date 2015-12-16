package com.merge.alev.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.merge.alev.dao.model.Category;
import com.merge.alev.dao.model.Fee;
import com.merge.alev.dao.model.Order;
import com.merge.alev.dao.model.OrderDetail;
import com.merge.alev.dao.model.Product;
import com.merge.alev.dao.model.ProductPicture;

@Configuration
@EnableTransactionManagement
public class ConfigDAO {

	private static SessionFactory sessionFactory;
	private static HibernateTransactionManager transactionManager;
	
	public ConfigDAO() {
	
		initSessionFactory();
		transactionManager = new HibernateTransactionManager(sessionFactory);
		
	}
	
	private static void initSessionFactory() {
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
		
		ssrb.applySetting("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver")
			.applySetting("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost:1453/alevecom")
			.applySetting("hibernate.connection.username", "alevecom")
			.applySetting("hibernate.connection.password", "AlevECom")
			
			.applySetting("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
			.applySetting("hibernate.current_session_context_class", "thread")

			.applySetting("hibernate.show_sql", "true")
			.applySetting("hibernate.format_sql", "true")

			.applySetting("hibernate.c3p0.timeout", "30")
			.applySetting("hibernate.c3p0.max_size", "16")
			.applySetting("hibernate.c3p0.min_size", "4")
			.applySetting("hibernate.c3p0.max_statements", "64")
			
			;
			
		StandardServiceRegistry ssr = ssrb.build();
		
		MetadataSources mds = new MetadataSources(ssr);

		mds.addAnnotatedClass(Category.class);
		mds.addAnnotatedClass(Product.class);
		mds.addAnnotatedClass(Fee.class);
		mds.addAnnotatedClass(ProductPicture.class);
		mds.addAnnotatedClass(Order.class);
		mds.addAnnotatedClass(OrderDetail.class);
		
//		mds.addPackage("com.merge.alev.dao.model");
		
		Metadata md = mds.buildMetadata();
		
		SessionFactoryBuilder sfb = md.getSessionFactoryBuilder();

		sessionFactory = sfb.build();

	}
	
	@Bean
	public SessionFactory sessionFactory() {
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager txManager() {
		return transactionManager;
	}
	
	
}
