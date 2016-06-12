package ownerFunctionality;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utility.PopUp;
import utility.TableFromResultSet;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class BusinessStatistics extends JDialog {

	private final JPanel mainPane = new JPanel();
	private JTextField businessIdTextField;

	private String username;
	private Connection con;
	
	private int businessId;
		
	private JLabel statusLabel;
	
	
	private JTable customerTable;
	private JTable menuItemTable;
	
	

	
	public BusinessStatistics(Connection con, String username) {
		setTitle("Business Statistics");
		this.con = con;
		this.username = username;
		try {
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		customerTable = new JTable();
		menuItemTable = new JTable();
		setBounds(100, 100, 900, 500);
		getContentPane().setLayout(new BorderLayout());
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPane, BorderLayout.CENTER);
		
		JLabel lblBusinessId = new JLabel("Business ID: ");
		lblBusinessId.setBounds(11, 16, 81, 16);
		
		businessIdTextField = new JTextField();
		businessIdTextField.setBounds(98, 11, 46, 26);
		businessIdTextField.setColumns(10);
		
		JLabel MaxOrderedLabel = new JLabel("Most Popular Menu Item:");
		MaxOrderedLabel.setBounds(11, 55, 156, 16);
		
		JLabel lblLeastPopularMenu = new JLabel("Least Popular Menu Item:");
		lblLeastPopularMenu.setBounds(11, 77, 158, 16);
		
		JScrollPane menuItemScrollPane = new JScrollPane();
		menuItemScrollPane.setBounds(11, 149, 373, 256);
		
		JLabel lblAverageNumberOf = new JLabel("Average Number Of Orders By Customers:");
		lblAverageNumberOf.setBounds(437, 55, 263, 16);
		
		JScrollPane customersScrollPane = new JScrollPane();
		customersScrollPane.setBounds(437, 149, 452, 256);
		
		JLabel lblMaximumNumberOf = new JLabel("Maximum Number Of Orders:");
		lblMaximumNumberOf.setBounds(437, 77, 185, 16);
		
		JLabel lblLeastNumberOf = new JLabel("Least Number Of Orders:");
		lblLeastNumberOf.setBounds(437, 99, 156, 16);
		
		JLabel maxMenuItemResultLabel = new JLabel("");
		maxMenuItemResultLabel.setBounds(179, 55, 205, 16);
		
		JLabel minMenuItemResultLabel = new JLabel("");
		minMenuItemResultLabel.setBounds(175, 77, 209, 16);
		
		JLabel lblTotalNumberOf = new JLabel("Total Number of Orders:");
		lblTotalNumberOf.setBounds(437, 121, 153, 16);
		
		JLabel aveNumOrdersResultLabel = new JLabel("");
		aveNumOrdersResultLabel.setBounds(737, 55, 75, 16);
		
		JLabel minNumOfOrdersResultLabel = new JLabel("");
		minNumOfOrdersResultLabel.setBounds(737, 99, 75, 16);
		
		JLabel totalNumOrdersResultLabel = new JLabel("");
		totalNumOrdersResultLabel.setBounds(737, 121, 75, 16);
		mainPane.setLayout(null);
		mainPane.add(MaxOrderedLabel);
		mainPane.add(maxMenuItemResultLabel);
		mainPane.add(lblBusinessId);
		mainPane.add(businessIdTextField);
		mainPane.add(lblLeastPopularMenu);
		mainPane.add(minMenuItemResultLabel);
		mainPane.add(menuItemScrollPane);
		mainPane.add(lblAverageNumberOf);
		mainPane.add(lblTotalNumberOf);
		mainPane.add(lblMaximumNumberOf);
		mainPane.add(customersScrollPane);
		mainPane.add(aveNumOrdersResultLabel);
		mainPane.add(lblLeastNumberOf);
		mainPane.add(totalNumOrdersResultLabel);
		mainPane.add(minNumOfOrdersResultLabel);
		
		JLabel maxNumOrdersResultLabel = new JLabel("");
		maxNumOrdersResultLabel.setBounds(737, 77, 75, 16);
		mainPane.add(maxNumOrdersResultLabel);
		
		statusLabel = new JLabel("");
		statusLabel.setForeground(Color.RED);
		statusLabel.setBounds(11, 417, 878, 16);
		mainPane.add(statusLabel);
		
		
		customersScrollPane.setViewportView(customerTable);
		menuItemScrollPane.setViewportView(menuItemTable);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						System.out.println("ok button pressed");
						
						
						minNumOfOrdersResultLabel.setText("");
						maxNumOrdersResultLabel.setText("");
						aveNumOrdersResultLabel.setText("");
						totalNumOrdersResultLabel.setText("");
						
						
						try{
							businessId = Integer.parseInt(businessIdTextField.getText());
						}catch (Exception ex){
							ex.printStackTrace();
							statusLabel.setText("Invalid Input");
							return;
						}
						
						
						String ownerUserNameFromDB;
						String checkThatOwnerOwnsBusiness = "select OwnerUserName from business where BusinessID=?";
						try{
							PreparedStatement stmt = con.prepareStatement(checkThatOwnerOwnsBusiness);
							
							stmt.setString(1, "" + businessId);;
							ResultSet rs = stmt.executeQuery();
							
							
							if(rs.next()){
								ownerUserNameFromDB = rs.getString(1);
							}else{
								statusLabel.setText("The Business ID does not Exist in the database.");
								return;
							}		
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						statusLabel.setText("Database error");
						return;
					}
						
						if(!ownerUserNameFromDB.equals(username)){
							statusLabel.setText("You can only view the statistics for one of your own businesses");
							return;
						}
						
						
						
						String totalOrdersByEachCustomer = "select customerUserName, COUNT(*) AS numOrders from orders where businessID=? GROUP BY customerUserName ";
						
						try{
							PreparedStatement stmt = con.prepareStatement(totalOrdersByEachCustomer);
							
							stmt.setString(1, "" + businessId);;
							ResultSet rs = stmt.executeQuery();
							
							ResultSetMetaData rsmd = rs.getMetaData();
							TableFromResultSet.replaceTable(customerTable, rs, rsmd);
							
							if(customerTable.getRowCount() ==0){
								statusLabel.setText("No Orders were found for this business. Try posting some ads on Google");
							}
							
							
						}catch (SQLException ex) {
							System.out.println(ex.getMessage());
							statusLabel.setText("Database error");
							return;
						}
						
						
						String customerStatsQuery = "select Min(numOrders), Max(numOrders),Avg(numOrders), count(numOrders) from"
								+ "(select COUNT(*) AS numOrders from orders where businessID=? GROUP BY customerUserName) ";
						try{
							PreparedStatement stmt = con.prepareStatement(customerStatsQuery);
							
							stmt.setString(1, "" + businessId);;
							ResultSet rs = stmt.executeQuery();
							
							if(rs.next()){
								minNumOfOrdersResultLabel.setText("" + rs.getInt(1));
								maxNumOrdersResultLabel.setText("" + rs.getInt(2));
								aveNumOrdersResultLabel.setText("" + rs.getDouble(3));
								int x = rs.getInt(4);
								System.out.println(x);
								totalNumOrdersResultLabel.setText("" + x);
							}
							
						}catch (SQLException ex) {
							System.out.println(ex.getMessage());
							statusLabel.setText("Database error");
							return;
						}
						
						
						
						
						//populating the menuitem table
						String totalOrdersForEachMenuItemQuery = "select M.menuItemID, M.Name, M.Price, COUNT(O.orderID) from MenuItem M,Orders O, Includes I where I.orderID=O.orderID AND M.MenuItemID=I.MenuItemID  AND M.BusinessID = ? GROUP BY M.menuItemID, M.Name, M.Price ";
						try{
							PreparedStatement stmt = con.prepareStatement(totalOrdersForEachMenuItemQuery);
							
							stmt.setString(1, "" + businessId);;
							ResultSet rs = stmt.executeQuery();
							
							ResultSetMetaData rsmd = rs.getMetaData();
							TableFromResultSet.replaceTable(menuItemTable, rs, rsmd);
							
							if(menuItemTable.getRowCount() ==0){
								statusLabel.setText("No Orders were found for this business. Try posting some ads on Google");
							}
							
							
						}catch (SQLException ex) {
							System.out.println(ex.getMessage());
							statusLabel.setText("Database error");
							return;
						}
						
						
						
						//finding the most popular food
						
						String menuItemMaxQuery ="select M.menuItemID, M.Name "
								+ "from MenuItem M, Orders O, Includes I "
								+ "Where M.menuItemID=I.menuItemID AND I.orderID=O.orderID AND M.businessID=? "
								+ "GROUP BY M.menuItemID, M.name Having count(*) IN "
								+ "(select Max(numOrders) "
								+ "from (select M.menuItemID, COUNT(O.orderID) AS numOrders "
								+ "from MenuItem M,Orders O, Includes I "
								+ "where I.orderID=O.orderID AND M.MenuItemID=I.MenuItemID  AND M.BusinessID = ? "
								+ "GROUP BY M.menuItemID))";
						try{
							PreparedStatement stmt = con.prepareStatement(menuItemMaxQuery);
							
							stmt.setString(1, "" + businessId);
							stmt.setString(2, "" + businessId);
							ResultSet rs = stmt.executeQuery();
							
							if(rs.next()){
								maxMenuItemResultLabel.setText(rs.getString(1)+ " "+ rs.getString(2));
							}
							
						}catch (SQLException ex) {
							System.out.println(ex.getMessage());
							statusLabel.setText("Database error");
							return;
						}
						
						
						//finding the least popular food
						
						String menuItemMinQuery = "select M.menuItemID, M.Name "
								+ "from MenuItem M, Orders O, Includes I "
								+ "Where M.menuItemID=I.menuItemID AND I.orderID=O.orderID AND M.businessID=? "
								+ "GROUP BY M.menuItemID, M.name Having count(*) IN "
								+ "(select Min(numOrders) "
								+ "from (select M.menuItemID, COUNT(O.orderID) AS numOrders "
								+ "from MenuItem M,Orders O, Includes I "
								+ "where I.orderID=O.orderID AND M.MenuItemID=I.MenuItemID  AND M.BusinessID = ? "
								+ "GROUP BY M.menuItemID))";
						try{
							PreparedStatement stmt = con.prepareStatement(menuItemMinQuery);
							
							stmt.setString(1, "" + businessId);
							stmt.setString(2, "" + businessId);
							ResultSet rs = stmt.executeQuery();
							
							if(rs.next()){
								minMenuItemResultLabel.setText(rs.getString(1) + " " + rs.getString(2));
							}
							
						}catch (SQLException ex) {
							System.out.println(ex.getMessage());
							statusLabel.setText("Database error");
							return;
						}
						
						
						
						
						
						
						
						
						
						
					}
					}
				);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	
	private void closeDialog(){
		this.dispose();
	}
	
	
	
}
