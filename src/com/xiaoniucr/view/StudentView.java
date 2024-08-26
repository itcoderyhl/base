package com.xiaoniucr.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.xiaoniucr.dao.StudentDao;
import com.xiaoniucr.entity.Student;

import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * 学生管理
 * 
 * @author Lenovo
 *
 */
public class StudentView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField usernameStrText, nicknameStrText;
	private JLabel usernameStrLabel, nicknameStrLabel;
	private StudentDao studentDao = new StudentDao();
	private JTextField usernameText;
	private JTextField nicknameText;
	private JLabel sexLabel;
	private JTextField birthdayText;
	private JTextField telephoneText;
	private JTextField emailText;
	private JRadioButton maleRadio,femaleRadio;
	private JButton searchBtn, addBtn, updateBtn, deleteBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView frame = new StudentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentView() {

		this.setTitle("学生管理");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 969, 440);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 55, 635, 324);
		contentPane.add(scrollPane);
		
		usernameStrLabel = new JLabel("学号：");
		usernameStrLabel.setBounds(6, 14, 43, 30);
		contentPane.add(usernameStrLabel);

		usernameStrText = new JTextField();
		usernameStrText.setBounds(45, 15, 107, 30);
		usernameStrText.setColumns(10);
		contentPane.add(usernameStrText);

		nicknameStrLabel = new JLabel("姓名：");
		nicknameStrLabel.setBounds(162, 14, 52, 30);
		contentPane.add(nicknameStrLabel);

		nicknameStrText = new JTextField();
		nicknameStrText.setBounds(197, 15, 107, 30);
		contentPane.add(nicknameStrText);
		nicknameStrText.setColumns(10);

		searchBtn = new JButton("查询");
		searchBtn.setBackground(Color.LIGHT_GRAY);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData();
				getData(usernameStrText.getText(), nicknameStrText.getText());
			}
		});
		searchBtn.setBounds(314, 15, 65, 30);
		contentPane.add(searchBtn);

		Object[] columns = { "ID", "学号", "姓名", "性别", "生日", "电话", "邮箱" };// 字段
		Object[][] data = null;// 需要展示的数据，一般是二维数组
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		table.setRowHeight(30);

		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		tcr.setVerticalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		// 设置表头居中显示
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		table.getTableHeader().setDefaultRenderer(hr);
		table.getTableHeader().setFont(new Font("黑体", Font.PLAIN, 14));
		table.getTableHeader().setPreferredSize(new Dimension(1, 30));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 1) {
					int row = table.getSelectedRow();
					int id = Integer.valueOf(table.getValueAt(row, 0).toString());
					Student student = studentDao.getById(id);
					usernameText.setText(student.getUsername());
					nicknameText.setText(student.getNickname());
					if(student.getSex() == 0){
						maleRadio.setSelected(true);
						femaleRadio.setSelected(false);
					}else{
						maleRadio.setSelected(false);
						femaleRadio.setSelected(true);
					}
					birthdayText.setText(student.getBirthday());
					telephoneText.setText(student.getTelephone());
					emailText.setText(student.getEmail());
				}
			}
		});


		getData(null, null);
		scrollPane.setViewportView(table);


		JLabel usernameLabel = new JLabel("学号：");
		usernameLabel.setBounds(662, 55, 54, 30);
		contentPane.add(usernameLabel);

		usernameText = new JTextField();
		usernameText.setBounds(699, 56, 216, 30);
		contentPane.add(usernameText);
		usernameText.setColumns(10);

		JLabel nicknameLabel = new JLabel("姓名：");
		nicknameLabel.setBounds(662, 106, 54, 30);
		contentPane.add(nicknameLabel);

		nicknameText = new JTextField();
		nicknameText.setBounds(699, 107, 216, 30);
		contentPane.add(nicknameText);
		nicknameText.setColumns(10);

		sexLabel = new JLabel("性别：");
		sexLabel.setBounds(662, 152, 54, 30);
		contentPane.add(sexLabel);

		maleRadio = new JRadioButton("男");
		maleRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maleRadio.setSelected(true);
				femaleRadio.setSelected(false);
			}
		});
		maleRadio.setSelected(true);
		maleRadio.setBounds(699, 152, 65, 30);
		contentPane.add(maleRadio);

		femaleRadio = new JRadioButton("女");
		femaleRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maleRadio.setSelected(false);
				femaleRadio.setSelected(true);
			}
		});
		femaleRadio.setBounds(766, 152, 65, 30);
		contentPane.add(femaleRadio);

		JLabel birthdayLabel = new JLabel("生日：");
		birthdayLabel.setBounds(662, 192, 54, 30);
		contentPane.add(birthdayLabel);

		birthdayText = new JTextField();
		birthdayText.setColumns(10);
		birthdayText.setBounds(699, 193, 216, 30);
		contentPane.add(birthdayText);

		JLabel telephoneLabel = new JLabel("电话：");
		telephoneLabel.setBounds(662, 241, 54, 30);
		contentPane.add(telephoneLabel);

		telephoneText = new JTextField();
		telephoneText.setBounds(699, 242, 216, 30);
		contentPane.add(telephoneText);
		telephoneText.setColumns(10);

		JLabel emailLabel = new JLabel("邮箱：");
		emailLabel.setBounds(662, 288, 54, 30);
		contentPane.add(emailLabel);

		emailText = new JTextField();
		emailText.setBounds(699, 289, 216, 30);
		contentPane.add(emailText);
		emailText.setColumns(10);

		addBtn = new JButton("添加");
		addBtn.setBackground(Color.LIGHT_GRAY);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameText.getText();
				String nickname = nicknameText.getText();
				Integer sex = 0;
				//获取性别
				for(Component c : contentPane.getComponents()){
		            if(c instanceof JRadioButton){
		                if(((JRadioButton) c).isSelected()){
		                	String text = ((JRadioButton)c).getText();
		                	if("男".equals(text)){
		                		sex = 0;
		                	}else{
		                		sex = 1;
		                	}
		                }
		            }
		        }
				String birthday = birthdayText.getText();
				String telephone = telephoneText.getText();
				String email = emailText.getText();
				if(username == null || "".equals(username)){
					JOptionPane.showMessageDialog(contentPane, "请输入学号", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nickname == null || "".equals(nickname)){
					JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(birthday == null || "".equals(birthday)){
					JOptionPane.showMessageDialog(contentPane, "请输入生日", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(telephone == null || "".equals(telephone)){
					JOptionPane.showMessageDialog(contentPane, "请输入电话", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(email == null || "".equals(email)){
					JOptionPane.showMessageDialog(contentPane, "请输入邮箱", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Student student = studentDao.getByUsername(username);
				if(student != null){
					JOptionPane.showMessageDialog(contentPane, "学号已存在", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				student = new Student();
				student.setUsername(username);
				student.setNickname(nickname);
				student.setSex(sex);
				student.setBirthday(birthday);
				student.setTelephone(telephone);
				student.setEmail(email);
				boolean flag = studentDao.save(student);
				if(flag){
					JOptionPane.showMessageDialog(contentPane, "保存成功!");
					clearData();
					getData(null, null);
				}else{
					JOptionPane.showMessageDialog(contentPane, "保存失败!", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		addBtn.setBounds(699, 341, 65, 30);
		contentPane.add(addBtn);

		updateBtn = new JButton("修改");
		updateBtn.setBackground(Color.LIGHT_GRAY);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取选中行
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int id = Integer.valueOf(table.getValueAt(row, 0).toString());
				String username = usernameText.getText();
				String nickname = nicknameText.getText();
				Integer sex = 0;
				//获取性别
				for(Component c : contentPane.getComponents()){
		            if(c instanceof JRadioButton){
		                if(((JRadioButton) c).isSelected()){
		                	String text = ((JRadioButton)c).getText();
		                	if("男".equals(text)){
		                		sex = 0;
		                	}else{
		                		sex = 1;
		                	}
		                }
		            }
		        }
				String birthday = birthdayText.getText();
				String telephone = telephoneText.getText();
				String email = emailText.getText();
				if(username == null || "".equals(username)){
					JOptionPane.showMessageDialog(contentPane, "请输入学号", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(nickname == null || "".equals(nickname)){
					JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(birthday == null || "".equals(birthday)){
					JOptionPane.showMessageDialog(contentPane, "请输入生日", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(telephone == null || "".equals(telephone)){
					JOptionPane.showMessageDialog(contentPane, "请输入电话", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(email == null || "".equals(email)){
					JOptionPane.showMessageDialog(contentPane, "请输入邮箱", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Student student = studentDao.getById(id);
				if(!student.getUsername().equals(username)){
					Student exist = studentDao.getByUsername(username);
					if(exist != null){
						JOptionPane.showMessageDialog(contentPane, "学号已存在", "系统提示",JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				student.setUsername(username);
				student.setNickname(nickname);
				student.setSex(sex);
				student.setBirthday(birthday);
				student.setTelephone(telephone);
				student.setEmail(email);
				boolean flag = studentDao.update(student);
				if(flag){
					JOptionPane.showMessageDialog(contentPane, "保存成功!");
					clearData();
					getData(null, null);
				}else{
					JOptionPane.showMessageDialog(contentPane, "保存失败!", "系统提示",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		updateBtn.setBounds(775, 341, 65, 30);
		contentPane.add(updateBtn);

		deleteBtn = new JButton("删除");
		deleteBtn.setBackground(Color.LIGHT_GRAY);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取选中行
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(contentPane, "确定删除此记录吗？", "提示", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					int id = Integer.valueOf(table.getValueAt(row, 0).toString());
					boolean flag = studentDao.delete(id);
					if (flag) {
						JOptionPane.showMessageDialog(contentPane, "删除成功!");
						clearData();
						getData(null, null);
					} else {
						JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);

					}
				}
				return;
			}
		});
		deleteBtn.setBounds(850, 341, 65, 30);
		contentPane.add(deleteBtn);

	}

	// 填充表格数据
	public void getData(String username, String nickname) {
		List<Student> list = studentDao.queryList(username, nickname);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// 清除原有行
		// 填充数据
		for (Student item : list) {
			String[] arr = new String[7];
			arr[0] = item.getId() + "";
			arr[1] = item.getUsername();
			arr[2] = item.getNickname();
			arr[3] = item.getSex() == 0 ? "男" : "女";
			arr[4] = item.getBirthday();
			arr[5] = item.getTelephone();
			arr[6] = item.getEmail();
			// 添加数据到表格
			tableModel.addRow(arr);
		}
	}
	
	
	private void clearData(){
		usernameText.setText("");
		nicknameText.setText("");
		maleRadio.setSelected(true);
		femaleRadio.setSelected(false);
		birthdayText.setText("");
		telephoneText.setText("");
		emailText.setText("");
	}
}
