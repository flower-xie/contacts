package control;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Actions;
import model.People;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSex;
	private JTextField txtNumber;
	private JTextField txtBirthday;
	private JTextField txtEmail;
	private JTextField txtHabit;
	private JLabel label;
	private JComboBox comboBox;
	private Actions actions = new Actions();
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Main frame = new Main();
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
	public Main() {
		setResizable(false);
		setTitle("通讯录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {

			Image image = ImageIO.read(this.getClass().getResource("/1.jpg"));//更换图标
			this.setIconImage(image);

		} catch (IOException e2) {
			e2.printStackTrace();
		}

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 46, 54, 15);
		contentPane.add(lblName);

		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(10, 82, 54, 15);
		contentPane.add(lblSex);

		JLabel lblNumber = new JLabel("Num:");
		lblNumber.setBounds(10, 122, 43, 15);
		contentPane.add(lblNumber);

		JLabel lblBirthday = new JLabel("Birth:");
		lblBirthday.setBounds(10, 160, 54, 15);
		contentPane.add(lblBirthday);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 198, 43, 15);
		contentPane.add(lblEmail);

		JLabel lblHabit = new JLabel("Habit:");
		lblHabit.setBounds(10, 237, 54, 15);
		contentPane.add(lblHabit);

		txtName = new JTextField();
		txtName.setBounds(55, 43, 134, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtSex = new JTextField();
		txtSex.setBounds(55, 79, 134, 21);
		contentPane.add(txtSex);
		txtSex.setColumns(10);

		txtNumber = new JTextField();
		txtNumber.setBounds(55, 119, 134, 21);
		contentPane.add(txtNumber);
		txtNumber.setColumns(10);

		txtBirthday = new JTextField();
		txtBirthday.setBounds(55, 157, 134, 21);
		contentPane.add(txtBirthday);
		txtBirthday.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(55, 195, 134, 21);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtHabit = new JTextField();
		txtHabit.setColumns(10);
		txtHabit.setBounds(55, 234, 134, 21);
		contentPane.add(txtHabit);

		label = new JLabel("Contacts：");
		label.setBackground(Color.BLUE);
		label.setBounds(230, 10, 76, 18);
		contentPane.add(label);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateFormat format = new SimpleDateFormat("yy-MM-dd");
				Date date = new Date();
				try {

					date = format.parse(txtBirthday.getText());

				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				int i = Integer.parseInt(txtID.getText());
				People people = new People(i, txtName.getText(), txtSex.getText(), 
						txtNumber.getText(), date, txtEmail.getText(), txtHabit.getText());
				try {

					actions.update(people, i);
					update();
					JOptionPane.showMessageDialog(null, "Update successfully!");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(331, 179, 93, 28);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");//Delete the people from MySQL DataBase.
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						actions.delete(comboBox.getSelectedItem().toString());
						update();
						JOptionPane.showMessageDialog(null, "Delete successfully!");
					}

				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(331, 230, 93, 28);
		contentPane.add(btnDelete);

		JButton btnQuery = new JButton("Query");//Query the detail of people from MySQL DataBase.
		btnQuery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					People people = actions.queryString(comboBox.getSelectedItem().toString());
					txtName.setText(people.getName());
					txtBirthday.setText(people.getBirthday().toString());
					txtEmail.setText(people.getEmail());
					txtHabit.setText(people.getHabit());
					txtNumber.setText(people.getNumber());
					txtSex.setText(people.getSex());
					txtID.setText(people.getId()+"");

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Query failed!");
				}
			}
		});
		btnQuery.setBounds(331, 131, 93, 28);
		contentPane.add(btnQuery);

		JButton btnAdd = new JButton("Add");//Add the people into MySQL DataBase.
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateFormat format = new SimpleDateFormat("yy-MM-dd");
				Date date = new Date();
				try {

					date = format.parse(txtBirthday.getText());

				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Please input the information to Add!");
				}
				People people = new People(Integer.parseInt(txtID.getText()), txtName.getText(), txtSex.getText(), 
						txtNumber.getText(), date, txtEmail.getText(), txtHabit.getText());
				try {

					List<People> isHas = actions.query();
					if (!isHas.isEmpty()) {
						JOptionPane.showMessageDialog(null, "The input name already extists!");
					}else{

						actions.add(people);
						update();
						JOptionPane.showMessageDialog(null, "Add successfully!");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Duplicate entry "+txtID.getText()+" for key 'PRIMARY'");
				}
			}
		});
		btnAdd.setBounds(331, 82, 93, 28);
		contentPane.add(btnAdd);

		comboBox = new JComboBox();//把数据库中的所有联系人添加到下拉列表中
		comboBox.setBounds(228, 37, 196, 33);
		update();

		JButton btnClear = new JButton("Clear");//Clear all of JTextField.
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clear();
			}
		});
		btnClear.setBounds(230, 230, 93, 28);
		contentPane.add(btnClear);

		txtID = new JTextField();
		txtID.setBounds(55, 10, 134, 21);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 13, 54, 15);
		contentPane.add(lblNewLabel);
	}

	public void clear() {
		txtName.setText("");
		txtBirthday.setText("");
		txtEmail.setText("");
		txtHabit.setText("");
		txtNumber.setText("");
		txtSex.setText("");
		txtID.setText("");
	}

	public void update() {//Update the contacts according to MySQL DataBase;
		List<People> list = new ArrayList<People>();
		try {

			list = actions.query();
			String[] strings = new String[list.size()];
			int i = 0;
			for (People people : list) {
				strings[i++] = people.getName();
			}
			comboBox.setModel(new DefaultComboBoxModel<>(strings));
			clear();

		} catch (Exception e) {
		}
		contentPane.add(comboBox);
	}
}
