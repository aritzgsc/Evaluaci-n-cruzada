package gui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import domain.Medal.Metal;

//GUI.13 Renderer personalizado de un JTable

public class MedalTableCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			if ((Metal) value == Metal.GOLD) {
				label.setForeground(new Color(0xFFD700));
			} else if ((Metal) value == Metal.SILVER) {
				label.setForeground(new Color(0xC0C0C0));
			} else if ((Metal) value == Metal.BRONZE){
				label.setForeground(new Color(0xCD7F32));
			} else {
				label.setForeground(Color.RED);
			}
			
			label.setFont(getFont().deriveFont(Font.BOLD));
			
			return label;
			
		}
		
	}