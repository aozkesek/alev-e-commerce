package com.merge.alev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.HsqlException;
import org.hsqldb.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.merge.alev")
public class AlevECommerce {

	static ApplicationContext applicationContext = null; 
	static Server dbServer = null;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static void main(String[] args) throws Exception {
		
		startDb();
		initDb();
		
		applicationContext = SpringApplication.run(AlevECommerce.class, args);
		
//		stopDb();
		
	}

	static void startDb() throws Exception {
		dbServer = new Server();
		dbServer.setAddress("localhost");
		dbServer.setPort(1453);
		dbServer.setDatabaseName(0, "alevecom");
		dbServer.setDatabasePath(0, "data/alevecom");
//		dbServer.setErrWriter(null);
//		dbServer.setLogWriter(null);
		dbServer.start();
	}
	
	static void stopDb() {
		
		if (dbServer != null) {
			dbServer.stop();
			dbServer.shutdown();
		}
		
	}
	
	static void initDb() {
		
		Connection dbConnection = null;
		
		try {
			dbConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:1453/alevecom", "alevecom", "AlevECom");	
		} catch (SQLException e) {
			if (e.getCause() instanceof HsqlException) {
				
				try {
					dbConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:1453/alevecom;close_result=true", "sa", "");
					Statement stmt = dbConnection.createStatement();
					
					stmt.execute("CREATE USER \"alevecom\" PASSWORD 'AlevECom'");
					stmt.execute("CREATE SCHEMA ALEVECOM AUTHORIZATION \"alevecom\"");
					stmt.execute("ALTER USER \"alevecom\" SET INITIAL SCHEMA ALEVECOM");
					
					stmt.execute("CREATE CACHED TABLE ALEVECOM.CATEGORIES"
							+ " ( ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY"
							+ " , VERSION INTEGER"
							+ " , CATEGORYNAME VARCHAR(100) NOT NULL UNIQUE"		
							+ " )"
							);
					
					stmt.execute("CREATE CACHED TABLE ALEVECOM.PRODUCTS"
							+ " ( ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY "	
							+ " , VERSION INTEGER"
							+ " , CATEGORY_ID INTEGER NOT NULL FOREIGN KEY REFERENCES CATEGORIES(ID)"	
							+ " , NAME VARCHAR(200) NOT NULL UNIQUE"		
							+ " , TITLE VARCHAR(100) NOT NULL"		
							+ " , DESCRIPTION VARCHAR(2000)"		
							+ " , COLORS VARCHAR(100)"		
							+ " , SIZES VARCHAR(100)"		
							+ " , PRICE DOUBLE"		
							+ " , ACTUALPRICE DOUBLE"		
							+ " , CREATEDATE TIMESTAMP"		
							+ " , UPDATEDATE TIMESTAMP"		
							+ " )"
							);
					
					stmt.execute("CREATE CACHED TABLE ALEVECOM.PRODUCTPICTURES"
							+ " ( ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY "	
							+ " , VERSION INTEGER"
							+ " , PRODUCT_ID INTEGER NOT NULL FOREIGN KEY REFERENCES PRODUCTS(ID)"		
							+ " , PATH VARCHAR(1000)"		
							+ " , NAME VARCHAR(200)"		
							+ " )"
							);
					
					stmt.execute("CREATE CACHED TABLE ALEVECOM.ORDERS"
							+ " ( ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY "	
							+ " , VERSION INTEGER"
							+ " , ORDERNO VARCHAR(50) NOT NULL UNIQUE"		
							+ " , CUSTOMERNAME VARCHAR(100) NOT NULL"		
							+ " , CUSTOMERMIDDLE VARCHAR(100)"		
							+ " , CUSTOMERLASTNAME VARCHAR(100) NOT NULL"		
							+ " , CUSTOMERTELNO VARCHAR(30) NOT NULL"		
							+ " , CUSTOMEREMAIL VARCHAR(300) NOT NULL"		
							+ " , DELIVERTO VARCHAR(200)"		
							+ " , DELIVERYADDRESS VARCHAR(500) NOT NULL"		
							+ " , POSTCODE VARCHAR(30) NOT NULL"		
							+ " , ORDERSTATUS VARCHAR(100)"		
							+ " , PAYMENTSTATUS VARCHAR(100)"		
							+ " , TRACKNUMBER VARCHAR(100)"		
							+ " , ORDERDATE TIMESTAMP"		
							+ " , TOTALAMOUNT DOUBLE"		
							+ " , TOTALFEES DOUBLE"		
							+ " )"
							);

					stmt.execute("CREATE CACHED TABLE ALEVECOM.ORDERDETAILS"
							+ " ( ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY "	
							+ " , VERSION INTEGER"
							+ " , ORDER_ID INTEGER NOT NULL FOREIGN KEY REFERENCES ORDERS(ID)"		
							+ " , PRODUCT_ID INTEGER NOT NULL FOREIGN KEY REFERENCES PRODUCTS(ID)"		
							+ " , SIZE VARCHAR(30) NOT NULL"		
							+ " , COLOR VARCHAR(30) NOT NULL"		
							+ " , QUANTITY INTEGER"		
							+ " , ACTUALPRICE DOUBLE"		
							+ " , TOTALPRICE DOUBLE"		
							+ " )"
							);

					stmt.execute("CREATE CACHED TABLE ALEVECOM.FEES"
							+ " ( ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY "			
							+ " , VERSION INTEGER"
							+ " , AMOUNT DOUBLE"		
							+ " , NAME VARCHAR(200) NOT NULL UNIQUE"		
							+ " )"
							);
					
					stmt.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			else
				e.printStackTrace();
		}
		finally {
			try {
				if (dbConnection != null && !dbConnection.isClosed())
					dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	public static <T> T getBean(String beanName) {
	
		if (applicationContext.containsBean(beanName))
			return (T) applicationContext.getBean(beanName);
		
		return null;
	}
	
	public static <T> T getBean(String beanName, Class<T> requiredType) {
		
		if (applicationContext.containsBean(beanName))
			return applicationContext.getBean(beanName, requiredType);
		
		return null;
	}
	
}
