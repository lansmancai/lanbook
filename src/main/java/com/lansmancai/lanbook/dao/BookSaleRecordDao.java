package com.lansmancai.lanbook.dao;

import java.util.Collection;

import com.lansmancai.lanbook.vo.BookSaleRecord;

/**
 * 书本销售记录DAO接口
 * 
 */
public interface BookSaleRecordDao {

	/**
	 * 根据销售记录id获取该销售记录下所有的书的销售记录
	 * @param saleRecordId
	 * @return
	 */
	Collection<BookSaleRecord> findBySaleRecord(String saleRecordId);

	/**
	 * 保存一条书的销售记录
	 * @param record
	 * @return
	 */
	String saveBookSaleRecord(BookSaleRecord record);
	
}
