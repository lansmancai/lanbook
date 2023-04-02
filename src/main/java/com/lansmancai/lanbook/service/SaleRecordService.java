package com.lansmancai.lanbook.service;

import java.util.Collection;
import java.util.Date;

import com.lansmancai.lanbook.vo.SaleRecord;

/**
 * ����ҵ��ӿ�
 * 
 */
public interface SaleRecordService {

	/**
	 * ����һ�����ۼ�¼
	 * @param record
	 */
	void saveRecord(SaleRecord record);
	
	/**
	 * �������ڻ�ȡ�����ڶ�Ӧ�����ۼ�¼
	 * @param date
	 * @return
	 */
	Collection<SaleRecord> getAll(Date date);
	
	/**
	 * ����id��ȡ���ۼ�¼
	 * @param id
	 * @return
	 */
	SaleRecord get(String id);
	
}
