package com.lansmancai.lanbook.ui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import com.lansmancai.lanbook.dao.BookDao;
import com.lansmancai.lanbook.dao.BookInRecordDao;
import com.lansmancai.lanbook.dao.BookSaleRecordDao;
import com.lansmancai.lanbook.dao.ConcernDao;
import com.lansmancai.lanbook.dao.InRecordDao;
import com.lansmancai.lanbook.dao.SaleRecordDao;
import com.lansmancai.lanbook.dao.TypeDao;
import com.lansmancai.lanbook.dao.impl.BookDaoImpl;
import com.lansmancai.lanbook.dao.impl.BookInRecordDaoImpl;
import com.lansmancai.lanbook.dao.impl.BookSaleRecordDaoImpl;
import com.lansmancai.lanbook.dao.impl.ConcernDaoImpl;
import com.lansmancai.lanbook.dao.impl.InRecordDaoImpl;
import com.lansmancai.lanbook.dao.impl.SaleRecordDaoImpl;
import com.lansmancai.lanbook.dao.impl.TypeDaoImpl;
import com.lansmancai.lanbook.service.BookService;
import com.lansmancai.lanbook.service.ConcernService;
import com.lansmancai.lanbook.service.InRecordService;
import com.lansmancai.lanbook.service.SaleRecordService;
import com.lansmancai.lanbook.service.TypeService;
import com.lansmancai.lanbook.service.impl.BookServiceImpl;
import com.lansmancai.lanbook.service.impl.ConcernServiceImpl;
import com.lansmancai.lanbook.service.impl.InRecordServiceImpl;
import com.lansmancai.lanbook.service.impl.SaleRecordServiceImpl;
import com.lansmancai.lanbook.service.impl.TypeServiceImpl;

/**
 * 主界面的JFrame
 */
public class MainFrame extends JFrame{
	
	SalePanel salePanel;
	
	RepertoryPanel repertoryPanel;
	
	BookPanel bookPanel;
	
	ConcernPanel concernPanel;
	
	TypePanel typePanel;
	
	CommonPanel currentPanel;
	
	//业务接口
	TypeService typeService;
	
	ConcernService concernService;
	
	BookService bookService;
	
	SaleRecordService saleRecordService;
	
	InRecordService inRecordService;
	
	private Action sale = new AbstractAction("销售管理", new ImageIcon("images/sale.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(salePanel);
		}
	};
	
	private Action repertory = new AbstractAction("库存管理", new ImageIcon("images/repertory.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(repertoryPanel);
		}
	};

	private Action book = new AbstractAction("书本管理", new ImageIcon("images/book.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(bookPanel);
			bookPanel.initImage();
			bookPanel.repaint();
		}
	};
	
	private Action type = new AbstractAction("种类管理", new ImageIcon("images/type.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(typePanel);
		}
	};
	
	private Action concern = new AbstractAction("出版社管理", new ImageIcon("images/concern.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(concernPanel);
		}
	};
	
	public MainFrame() {
		TypeDao typeDao = new TypeDaoImpl();
		ConcernDao concernDao = new ConcernDaoImpl();
		BookDao bookDao = new BookDaoImpl();
		SaleRecordDao saleRecordDao = new SaleRecordDaoImpl();
		BookSaleRecordDao bookSaleRecordDao = new BookSaleRecordDaoImpl();
		InRecordDao inRecordDao = new InRecordDaoImpl();
		BookInRecordDao bookInRecordDao = new BookInRecordDaoImpl();
		this.typeService = new TypeServiceImpl(typeDao);
		this.concernService = new ConcernServiceImpl(concernDao);
		this.bookService = new BookServiceImpl(bookDao, typeDao, concernDao);
		this.saleRecordService = new SaleRecordServiceImpl(saleRecordDao, 
				bookSaleRecordDao, bookDao);
		this.inRecordService = new InRecordServiceImpl(inRecordDao, bookInRecordDao, bookDao);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("系统");
		menuBar.add(menu);
		
		menu.add(sale).setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		menu.add(repertory).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
		menu.add(book).setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_MASK));
		menu.add(type).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
		menu.add(concern).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
		
		//让界面作为第一显示界面
		this.salePanel = new SalePanel(this.bookService, this.saleRecordService);
		this.add(salePanel);
		this.currentPanel = salePanel;
		//初始化销售界面的数据
		this.salePanel.initData();
		
		//初始化库存管理界面
		repertoryPanel = new RepertoryPanel(this.inRecordService, this.bookService);
		//初始化书本管理界面
		bookPanel = new BookPanel(this.bookService, this.typeService, 
				this.concernService);
		//初始化出版社管理界面
		concernPanel = new ConcernPanel(this.concernService);
		//初始化种类管理界面
		typePanel = new TypePanel(this.typeService);
		
		this.setJMenuBar(menuBar);
		this.setTitle("图书进存销管理系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	//切换各个界面
	private void changePanel(CommonPanel commonPanel) {
		//移除当前显示的JPanel
		this.remove(currentPanel);
		//添加需要显示的JPanel
		this.add(commonPanel);
		//设置当前的JPanel
		this.currentPanel = commonPanel;
		this.repaint();
		this.setVisible(true);
		//重新读取数据
		commonPanel.setViewDatas();
		//刷新列表
		commonPanel.clear();
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
