package com.lansmancai.lanbook.dao;

import java.util.Collection;

import com.lansmancai.lanbook.vo.BookSaleRecord;

/**
 * �鱾���ۼ�¼DAO�ӿ�
 * 
 */
public interface BookSaleRecordDao {

	/**
	 * �������ۼ�¼id��ȡ�����ۼ�¼�����е�������ۼ�¼
	 * @param saleRecordId
	 * @return
	 */
	Collection<BookSaleRecord> findBySaleRecord(String saleRecordId);

	/**
	 * ����һ��������ۼ�¼
	 * @param record
	 * @return
	 */
	String saveBookSaleRecord(BookSaleRecord record);
	
}
