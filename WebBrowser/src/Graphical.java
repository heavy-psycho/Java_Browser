import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Graphical extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8639390771471694401L;
	private JButton buton1;
	private JPanel myPanel;
	private String[] HTML;
	private String URL;
	JTextField text;
	JTextPane affichage;

	public static void main(String[] args) throws IOException{
		String[] a=WebGet.getHTML(null,null);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("END OF DOCUMENT");
		Graphical affiche = new Graphical();
		affiche.affichage();
	}

	public void affichage(){
		JFrame myFrame=new JFrame();
		myPanel= new JPanel();
		JPanel northPanel=new JPanel();
		buton1=new JButton("Afficher le site");
		BorderLayout layout= new BorderLayout();
		text=new JTextField();
		//affichage;
		myPanel.setLayout(layout);
		JTextPane affichage=new JTextPane();

		buton1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evt) {
				JButton src=(JButton)evt.getSource();
				URL=text.getText();
				try {
					HTML=WebGet.getHTML(URL,"/");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		});

		myPanel.setBackground(Color.white);
		text.setSize(200,20);

		northPanel.add(buton1,BorderLayout.EAST);
		northPanel.add(text,BorderLayout.WEST);
		myPanel.add(affichage,BorderLayout.SOUTH);
		myPanel.add(northPanel,BorderLayout.NORTH);
		
		
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		myFrame.setSize(300,300);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {



	}

}
