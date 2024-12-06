package lunartools.swing.text;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {

	@Override
	public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
		if(isNumeric(string)) {
			super.insertString(filterBypass, offset, string, attributeSet);
		}
	}

	@Override
	public void replace(FilterBypass filterBypass, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {
		if(isNumeric(text)) {
			super.replace(filterBypass, offset, length, text, attributeSet);
		}
	}

	private boolean isNumeric(String text) {
		for(int i=0;i<text.length();i++) {
			if(!Character.isDigit(text.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
