package lunartools.progressdialog;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressPanel extends JPanel {
	JLabel label1;
	JLabel label2;
	JProgressBar progressBar1;

	private JButton startButton;

	public ProgressPanel(ActionListener actionListener, String infoLine1, String infoLine2) {
		int width=300;
		setLayout(null);
		setBounds(0,0,width+16,150);

		int border=20;

		int y=10;
		int xLabel1=border;
		int lineHeight=18;
		int lineDistance=20;
		int hButton=26;
		int wButton=84;

		if(infoLine2==null) {
			y+=(lineDistance>>1);
		}
		label1=new JLabel(infoLine1);
		label1.setBounds(xLabel1,y,width-xLabel1-border,lineHeight);
		add(label1);

		if(infoLine2!=null) {
			y+=lineDistance;
			label2=new JLabel(infoLine2);
			label2.setBounds(xLabel1,y,width-xLabel1-border,lineHeight);
			add(label2);
		}

		y+=lineDistance;

		progressBar1 = new JProgressBar(0, 100);
		progressBar1.setBounds(xLabel1, y, width-border-border, lineHeight);
		progressBar1.setValue(0);
		progressBar1.setStringPainted(true);
		add(progressBar1, BorderLayout.CENTER);

		y+=lineDistance+10;

		startButton = new JButton("Cancel");
		startButton.setBounds((width-wButton)>>1, y, wButton,hButton);
		startButton.setActionCommand(ProgressDialog.ACTIONCOMMAND_CANCEL);
		startButton.addActionListener(actionListener);
		add(startButton);
	}

}
