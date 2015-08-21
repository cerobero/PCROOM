package ui.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.GameAdministrationDao;

public class GameAdministration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	GameList gameList = null;
	GameAdministrationDao gameDao =new GameAdministrationDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameAdministration frame = new GameAdministration();
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
	public GameAdministration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 306, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 424, 0);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("        \uAC8C\uC784\uAD00\uB9AC");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 19));
		label_2.setBounds(56, 26, 168, 35);
		contentPane.add(label_2);
		
		JLabel label_1 = new JLabel("      \uAC8C\uC784\uC774\uB984 : ");
		label_1.setBounds(5, 95, 97, 28);
		contentPane.add(label_1);
		
		
		JLabel label_3 = new JLabel("         \uC694\uAE08 :  ");
		label_3.setBounds(5, 150, 97, 28);
		contentPane.add(label_3);
		
		JButton button = new JButton("\uCD94\uAC00");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameList = new GameList();
				//gameDao = new GameAdministrationDao();
				int temp = Integer.valueOf(textField_1.getText());
				gameList.setGame_name(textField.getText());
				gameList.setGame_price(temp);
				gameDao.insert(gameList);
			}
		});
		button.setBounds(29, 227, 97, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\uC0AD\uC81C");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//System.out.println(textField.getText());
				gameDao.delete(textField.getText());
			}
		});
	
		button_1.setBounds(156, 227, 103, 23);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setBounds(108, 98, 151, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(108, 154, 151, 24);
		contentPane.add(textField_1);
		
		GameList gamelist = new GameList();
		List<GameList> listgame = new ArrayList<>();
	}
}
