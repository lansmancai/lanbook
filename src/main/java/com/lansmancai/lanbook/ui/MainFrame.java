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
 * �������JFrame
 */
public class MainFrame extends JFrame{
	
	SalePanel salePanel;
	
	RepertoryPanel repertoryPanel;
	
	BookPanel bookPanel;
	
	ConcernPanel concernPanel;
	
	TypePanel typePanel;
	
	CommonPanel currentPanel;
	
	//ҵ��ӿ�
	TypeService typeService;
	
	ConcernService concernService;
	
	BookService bookService;
	
	SaleRecordService saleRecordService;
	
	InRecordService inRecordService;
	
	private Action sale = new AbstractAction("���۹���", new ImageIcon("images/sale.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(salePanel);
		}
	};
	
	private Action repertory = new AbstractAction("������", new ImageIcon("images/repertory.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(repertoryPanel);
		}
	};

	private Action book = new AbstractAction("�鱾����", new ImageIcon("images/book.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(bookPanel);
			bookPanel.initImage();
			bookPanel.repaint();
		}
	};
	
	private Action type = new AbstractAction("�������", new ImageIcon("images/type.gif")) {
		public void actionPerformed(ActionEvent e) {
			changePanel(typePanel);
		}
	};
	
	private Action concern = new AbstractAction("���������", new ImageIcon("images/concern.gif")) {
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
		JMenu menu = new JMenu("ϵͳ");
		menuBar.add(menu);
		
		menu.add(sale).setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		menu.add(repertory).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
		menu.add(book).setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_MASK));
		menu.add(type).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
		menu.add(concern).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
		
		//�ý�����Ϊ��һ��ʾ����
		this.salePanel = new SalePanel(this.bookService, this.saleRecordService);
		this.add(salePanel);
		this.currentPanel = salePanel;
		//��ʼ�����۽��������
		this.salePanel.initData();
		
		//��ʼ������������
		repertoryPanel = new RepertoryPanel(this.inRecordService, this.bookService);
		//��ʼ���鱾��������
		bookPanel = new BookPanel(this.bookService, this.typeService, 
				this.concernService);
		//��ʼ���������������
		concernPanel = new ConcernPanel(this.concernService);
		//��ʼ�������������
		typePanel = new TypePanel(this.typeService);
		
		this.setJMenuBar(menuBar);
		this.setTitle("ͼ�����������ϵͳ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	//�л���������
	private void changePanel(CommonPanel commonPanel) {
		//�Ƴ���ǰ��ʾ��JPanel
		this.remove(currentPanel);
		//������Ҫ��ʾ��JPanel
		this.add(commonPanel);
		//���õ�ǰ��JPanel
		this.currentPanel = commonPanel;
		this.repaint();
		this.setVisible(true);
		//���¶�ȡ����
		commonPanel.setViewDatas();
		//ˢ���б�
		commonPanel.clear();
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}