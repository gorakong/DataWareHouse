package ownerFunctionality;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import utility.TableFromResultSet;

public class ViewReviews {
	Connection con;
	String username;
	JFrame reviewFrame;
	JTable reviews;

	public ViewReviews(Connection con, String username){
		this.con = con;
		this.username = username;

		reviewFrame = new JFrame("Reviews of your Businesses");
		JLabel reviewPage = new JLabel("Reviews of your Businesses");

		JPanel contentPane = new JPanel();
		reviewFrame.setContentPane(contentPane);
		GridBagLayout gb = new GridBagLayout();

		contentPane.setLayout(gb);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GridBagConstraints buttonC = new GridBagConstraints();
		buttonC.insets = new Insets(0, 0, 0, 10);
		buttonC.ipadx = 50;
		buttonC.anchor = GridBagConstraints.WEST;
		buttonC.gridx = 1;
		buttonC.gridy = 1;
		buttonC.gridwidth = 1;
		buttonC.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints tableC = new GridBagConstraints();
		tableC.insets = new Insets(0, 0, 0, 0);
		tableC.fill = GridBagConstraints.NONE;
		tableC.gridy = 5;
		tableC.gridx = 1;
		tableC.ipadx = 500;
		tableC.gridwidth = 3;
		tableC.gridheight = 15;

		buttonC.gridy = 1;
		gb.setConstraints(reviewPage, buttonC);
		contentPane.add(reviewPage);

		reviews = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(reviews);

		gb.setConstraints(scrollPane, tableC);
		contentPane.add(scrollPane);
		try{

			PreparedStatement stmt = con.prepareStatement("select ReviewID, Rating, Comments, Dates, business.BusinessID, CustomerUsername from review, business where review.BusinessID = business.BusinessID and business.ownerUsername = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			TableFromResultSet.replaceTable(reviews, rs, rsmd);
		}
		catch(SQLException ex){
			System.out.println("Message: " + ex.getMessage());
		}


		// Resize window
		reviewFrame.pack();

		// Centre window
		Dimension d = reviewFrame.getToolkit().getScreenSize();
		Rectangle r = reviewFrame.getBounds();
		reviewFrame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

		// Set window visible
		reviewFrame.setVisible(true);


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