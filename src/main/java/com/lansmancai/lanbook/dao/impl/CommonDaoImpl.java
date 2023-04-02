package com.lansmancai.lanbook.dao.impl;

import java.sql.ResultSet;
import java.util.Collection;

import com.lansmancai.lanbook.commons.DataUtil;
import com.lansmancai.lanbook.jdbc.JDBCExecutor;
import com.lansmancai.lanbook.vo.ValueObject;

/**
 * ����DAO�Ļ���
 * 
 */
public class CommonDaoImpl {
	//����JDBCExecutor����
	public JDBCExecutor getJDBCExecutor() {
		return JDBCExecutor.getJDBCExecutor();
	}
	
	//���ݲ�����SQL, ��Ž���ļ��϶���, �;�������ݿ�ӳ����󷵻�һ������
	public Collection getDatas(String sql, Collection<ValueObject> result, 
			Class clazz) {
		//ִ��SQL����ResultSet����
		ResultSet rs = getJDBCExecutor().executeQuery(sql);
		//��ResultSet���з�װ�����ؼ���
		return DataUtil.getDatas(result, rs, clazz);
	}
}
