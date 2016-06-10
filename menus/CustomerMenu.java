package menus;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import customerFunctionality.BusinessSearch;
import customerFunctionality.FindReviews;
import customerFunctionality.ReservationMakingDialog;
import customerFunctionality.WriteReview;

public class CustomerMenu {
	Connection con;
	String username;

	JFrame menuFrame;
	

	public CustomerMenu(Connection con, String username){
		this.con = con;
		this.username = username;

		menuFrame = new JFrame("Customer Menu");

		JLabel menuPage = new JLabel("Customer Menu Page");
		JLabel sn = new JLabel("Current User : " + username);
		JButton orderViewbutton = new JButton("View Orders");
		JButton findReviewsButton = new JButton("Find Reviews");
		JButton writeReviewButton = new JButton("Write a Review");
		JButton searchForBusinessesButton = new JButton("Search for Businesses");
		
		JButton makeReservationButton = new JButton("Make a Reservation");


		JPanel contentPane = new JPanel();
		menuFrame.setContentPane(contentPane);
		GridBagLayout gb = new GridBagLayout();
		
		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints buttonC = new GridBagConstraints();
		buttonC.insets = new Insets(0, 0, 0, 10);
		buttonC.ipadx = 50;
		buttonC.anchor = GridBagConstraints.WEST;
		buttonC.gridx = 1;
		buttonC.gridy = 1;
		buttonC.fill = GridBagConstraints.HORIZONTAL;

		buttonC.gridy = 1;
		gb.setConstraints(menuPage, buttonC);
		contentPane.add(menuPage);
		
		buttonC.gridy = 2;
		gb.setConstraints(sn, buttonC);
		contentPane.add(sn);
		
		buttonC.gridy = 3;
		gb.setConstraints(orderViewbutton, buttonC);
		contentPane.add(orderViewbutton);
		
		buttonC.gridy = 4;
		gb.setConstraints(makeReservationButton, buttonC);
		contentPane.add(makeReservationButton);
		
		buttonC.gridy = 5;
		gb.setConstraints(findReviewsButton, buttonC);
		contentPane.add(findReviewsButton);
		
		buttonC.gridy = 6;
		gb.setConstraints(writeReviewButton, buttonC);
		contentPane.add(writeReviewButton);
		
		buttonC.gridy = 7;
		gb.setConstraints(searchForBusinessesButton, buttonC);
		contentPane.add(searchForBusinessesButton);
		
		
		// Create and register button listeners
		ActionListener orderViewbuttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		};
		orderViewbutton.addActionListener(orderViewbuttonListener);
		
		ActionListener findReviewsButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				FindReviews fr = new FindReviews(con);
			}
		};
		findReviewsButton.addActionListener(findReviewsButtonListener);
		
		ActionListener writeReviewButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				WriteReview wr = new WriteReview(con, username);
			}
		};
		writeReviewButton.addActionListener(writeReviewButtonListener);
		
		
		ActionListener businessSearchButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				BusinessSearch bs = new BusinessSearch(con);
			}
		};
		searchForBusinessesButton.addActionListener(businessSearchButtonListener);

		
		
		ActionListener makeReservationButtonListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("reserve button pressed!");

				try {
					ReservationMakingDialog rmw = new ReservationMakingDialog( username , con);
					rmw.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					rmw.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		makeReservationButton.addActionListener(makeReservationButtonListener);

		// On window close
		menuFrame.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{ 
				System.exit(0); 
			}
		});

		// Resize window
		menuFrame.pack();

		// Centre window
		Dimension d = menuFrame.getToolkit().getScreenSize();
		Rectangle r = menuFrame.getBounds();
		menuFrame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

		// Set window visible
		menuFrame.setVisible(true);

		// Attempt to load the Oracle JDBC driver
		try 
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		}
		catch (SQLException ex)
		{
			System.out.println("Message: " + ex.getMessage());
			System.exit(-1);
		}

	}
}
