package control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField key;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setResizable(false);
		setTitle("登录通讯录数据库……");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		name = new JTextField();
		name.setBounds(142, 62, 226, 33);
		name.setColumns(10);

		JButton button1 = new JButton("登录");

		button1.setBounds(73, 198, 107, 33);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name.getText().equals("root") && key.getText().equals("123")) {
					setVisible(false);//使当前窗体不可见
					Main frame = new Main();
					frame.setVisible(true);//打开新窗体
				}else{
					JOptionPane.showMessageDialog(null, "用户名或者密码错误，请重新输入！");
					name.setText("");
					key.setText("");//清空用户名和密码输入框
				}
			}
		});

		JLabel label = new JLabel("用户名：");
		label.setBounds(73, 66, 55, 23);

		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(73, 138, 55, 24);

		key = new JTextField();
		key.setBounds(142, 134, 226, 33);
		key.setColumns(10);

		JButton button = new JButton("退出");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(261, 198, 107, 33);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(button);
		contentPane.add(button1);
		contentPane.add(label);
		contentPane.add(label_1);
		contentPane.add(key);
		contentPane.add(name);
	}
}
