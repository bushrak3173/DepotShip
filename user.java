import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class user  extends JFrame implements ActionListener {
	
	JPanel main_user_panel;
	JLabel jl_row_number, jl_col_number, jl_bomb;
	JTextField jtf1, jtf2, jtf3;
	JButton okbutt;
	
	public user() {
		
		Image icon = Toolkit.getDefaultToolkit().getImage("Image/bomb1.png");  
		this.setIconImage(icon);  
		
		main_user_panel= new JPanel();
		main_user_panel.setLayout(new BorderLayout());
		

		JPanel row_col_panel = new JPanel();
		row_col_panel.setOpaque(true);
		row_col_panel.setBackground(new Color(255, 153, 230));
		
		JPanel user_input_panel = new JPanel();
		user_input_panel.setOpaque(true);
		user_input_panel.setBackground(new Color(179, 153, 255));
		
		JPanel btn_panel = new JPanel();
        btn_panel.setOpaque(true);
		btn_panel.setBackground(new Color(204, 153, 255));
		
		row_col_panel.setLayout(new GridLayout(3,1));
		jl_row_number = new JLabel("Row:");
		jl_col_number = new JLabel("Column:");
		jl_bomb = new JLabel("Number of Bombs:");
		
		jl_row_number.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		jl_col_number.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		jl_bomb.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		
		row_col_panel.add(jl_row_number);
		row_col_panel.add(jl_col_number);
		row_col_panel.add(jl_bomb);
		
		user_input_panel.setLayout(new GridLayout(3,1));
		jtf1 = new JTextField(15);
		jtf2 = new JTextField(15);
		jtf3 = new JTextField(15);
		
		
		user_input_panel.add(jtf1);
		user_input_panel.add(jtf2);
		user_input_panel.add(jtf3);
		
		okbutt = new JButton("OK");
		okbutt.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		okbutt.setSize(5, 5);
		okbutt.addActionListener(this);
	    btn_panel.add(okbutt);
		
	    main_user_panel.add(row_col_panel, BorderLayout.WEST);
		main_user_panel.add(user_input_panel, BorderLayout.EAST);
		main_user_panel.add(btn_panel, BorderLayout.SOUTH);
		
		main_user_panel.setOpaque(true);
		main_user_panel.setBackground(new Color(153, 179, 255));
		this.add(main_user_panel);
		
		this.setTitle("Depot Ship Menu");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	    
		
		
	}
	
	
	
	
	
@Override
	public void actionPerformed(ActionEvent arg0) {
	 int rows = Integer.parseInt(jtf1.getText().trim());
	 int cols = Integer.parseInt(jtf2.getText().trim());
	 int bombs =  Integer.parseInt(jtf3.getText().trim());
	
	 this.dispose();
	  main gui = new main(rows,cols,bombs);
		
	}

}