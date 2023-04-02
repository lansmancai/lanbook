package com.lansmancai.lanbook.jdbc;

import java.io.*;
import java.util.Properties;

/**
 * ���Թ�����
 */
public class PropertiesUtil {
	//�����б�
	private static Properties properties = new Properties();
	//�����ļ���·��
	private static String CONFIG = "cfg/jdbc.properties";
	//��ȡ��Դ�ļ�, ����������
	//private static InputStream is = PropertiesUtil.class.getResourceAsStream(CONFIG);
	//���ݿ�����
	public static String JDBC_DRIVER;
	//jdbc����url
	public static String JDBC_URL;
	//���ݿ��û���
	public static String JDBC_USER;
	//���ݿ�����
	public static String JDBC_PASS;
	static {
		try {
			//����������
			InputStream is = new FileInputStream(CONFIG);
			properties.load(is);
			properties.load(is);
			//������õĸ�������
			JDBC_DRIVER = properties.getProperty("jdbc.driver");
			JDBC_URL = properties.getProperty("jdbc.url");
			JDBC_USER = properties.getProperty("jdbc.user");
			JDBC_PASS = properties.getProperty("jdbc.pass");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
