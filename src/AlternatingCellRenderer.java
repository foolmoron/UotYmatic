import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class AlternatingCellRenderer extends JLabel implements ListCellRenderer {

	public AlternatingCellRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String name = list.getName();
		if (value instanceof Vote) {
			if (name.equals("num"))
				setText(((Vote) value).toString(1));
			else if (name.equals("pos"))
				setText(((Vote) value).toString(2));
			else if (name.equals("vot"))
				setText(((Vote) value).toString(3));
		} else
			setText(value.toString());

		if (isSelected)
			setBackground(new Color(184, 202, 229));
		else if (index % 2 == 0)
			setBackground(new Color(238, 238, 238));
		else
			setBackground(Color.white);

		if (value instanceof Vote) {
			if (isSelected && ((Vote) value).post.isDouble())
				setBackground(new Color(255, 70, 50));
			else if (((Vote) value).post.isDouble())
				setBackground(new Color(255, 125, 105));
			else if (isSelected && (((Vote) value).post.isDuplicate()))
				setBackground(new Color(75, 170, 75));
			else if (((Vote) value).post.isDuplicate())
				setBackground(new Color(125, 245, 90));
			else if (isSelected && ((Vote) value).post.isBadVotecount())
				setBackground(new Color(90, 125, 225));
			else if (((Vote) value).post.isBadVotecount())
				setBackground(new Color(110, 155, 225));
			else if (isSelected && (((Vote) value).post.isFishy()))
				setBackground(new Color(255, 102, 0));
			else if (((Vote) value).post.isFishy())
				setBackground(new Color(255, 180, 80));
		}

		this.repaint();
		return this;

	}

}
