package lunartools.progressdialog;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

public class ProgressDialog extends JDialog implements ActionListener,PropertyChangeListener{
	private static final String PROPERTY_PROGRESS="progress";
	private static final String PROPERTY_STATE = "state";
	public static final String PROPERTY_LINE1="line1";
	public static final String PROPERTY_LINE2="line2";
	public static final String PROPERTY_BAR1="bar1";
	static final String ACTIONCOMMAND_CANCEL="cancel";
	private ProgressPanel progressPanel;
	private SwingWorker<?, ?> swingWorker;
	private static volatile boolean canceled;

	public static boolean executeWithProgresssDialog(JFrame frame, String dialogTitle, String infoLine1, SwingWorker<?, ?> swingWorker) {
		return executeWithProgresssDialog(frame, dialogTitle, infoLine1, null, swingWorker);
	}

	public static boolean executeWithProgresssDialog(JFrame frame, String dialogTitle, String infoLine1, String infoLine2, SwingWorker<?, ?> swingWorker) {
		ProgressDialog progressDialog = new ProgressDialog(frame , dialogTitle, infoLine1,infoLine2);
		progressDialog.swingWorker=swingWorker;
		swingWorker.addPropertyChangeListener(progressDialog);
		swingWorker.execute();
		progressDialog.setVisible(true);
		progressDialog.dispose();
		return canceled;
	}

	private ProgressDialog(JFrame jFrame, String title, String infoLine1, String infoLine2) {
		super(jFrame,title,true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		int dialogWidth=300;
		int dialogHeight=160;
		Rectangle bounds=jFrame.getBounds();
		bounds.x+=((bounds.width-dialogWidth)>>1);
		bounds.y+=((bounds.height-dialogHeight)>>1);
		bounds.width=dialogWidth+16;
		bounds.height=dialogHeight;
		setBounds(bounds);

		progressPanel = new ProgressPanel(this,infoLine1,infoLine2);
		progressPanel.setOpaque(false);
		setContentPane(progressPanel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(PROPERTY_PROGRESS)) {
			int v=(Integer)evt.getNewValue();
			progressPanel.progressBar1.setValue(v);
		}else if(evt.getPropertyName().equals(PROPERTY_STATE)) {
			if(evt.getNewValue()==StateValue.DONE) {
				this.setVisible(false);
			}
		}else if(evt.getPropertyName()==PROPERTY_LINE1) {
			progressPanel.label1.setText((String)evt.getNewValue());
		}else if(evt.getPropertyName()==PROPERTY_LINE2) {
			if(progressPanel.label2!=null) {
				progressPanel.label2.setText((String)evt.getNewValue());
			}
		}else if(evt.getPropertyName()==PROPERTY_BAR1) {
			progressPanel.progressBar1.setString((String)evt.getNewValue());
		}
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand()==ACTIONCOMMAND_CANCEL) {
			ProgressDialog.canceled=true;
			swingWorker.cancel(false);
			this.setVisible(false);
		}
	}

}
