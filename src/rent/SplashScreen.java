package rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Window.Type;

public class SplashScreen extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		            
		                int counter;
					 	SplashScreen frame = new SplashScreen();
					 	frame.setUndecorated(true);
					 	frame.setLocationRelativeTo(null);
					 	frame.setVisible(true);
					
					
					for(counter=0;counter<=100;counter++)
					{
						try {
							frame.progressBar.setValue(counter);
							Thread.sleep(100);
							frame.lblNewLabel.setText("Program Loading \t"+Integer.toString(counter)+"%");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Login login=new Login();
					login.setVisible(true);
					login.setLocationRelativeTo(null);
					frame.dispose();
			
			}
	
	

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 438);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
	    JLabel lblNewLabel_3 = new JLabel("Developed by Muhammad Ahsan 1912310");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
	    lblNewLabel_3.setBounds(362, 105, 365, 27);
	    contentPane.add(lblNewLabel_3);
		
	    lblNewLabel = new JLabel("");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(61, 338, 546, 27);
		contentPane.add(lblNewLabel);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(32, 178, 170));
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(103, 328, 481, 49);
		contentPane.add(progressBar);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SplashScreen.class.getResource("/images1/LOGO1.PNG")));
		lblNewLabel_1.setBounds(51, 11, 654, 339);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(SplashScreen.class.getResource("/images1/abstract-seamless-geometric-shape-vector-pattern-background-59766366.jpg")));
		lblNewLabel_2.setBounds(0, 0, 752, 434);
		contentPane.add(lblNewLabel_2);
	}
}
