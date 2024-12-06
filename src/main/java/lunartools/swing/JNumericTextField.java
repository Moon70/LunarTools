package lunartools.swing;

import javax.swing.*;
import javax.swing.text.*;

import lunartools.swing.text.NumericDocumentFilter;

public class JNumericTextField extends JTextField {

	{
		setNumericDocumentFilter();
	}

	public JNumericTextField() {
		super();
	}

	public JNumericTextField(String text) {
		super(text);
	}

	public JNumericTextField(int columns) {
		super(columns);
	}

	public JNumericTextField(String text, int columns) {
		super(text,columns);
	}

	public JNumericTextField(Document doc, String text, int columns) {
		super(doc,text,columns);
	}

	private void setNumericDocumentFilter() {
		Document document=this.getDocument();
		if (document instanceof AbstractDocument) {
			((AbstractDocument)document).setDocumentFilter(new NumericDocumentFilter());
		}else {
			throw new RuntimeException("Document must be an instance of AbstractDocument");
		}
	}
}
