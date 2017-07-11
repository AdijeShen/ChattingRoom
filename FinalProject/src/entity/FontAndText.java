package entity;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class FontAndText {
	public static final int GENERAL = 0; // ����
	private String msg = "", name = "����"; // Ҫ������ı�����������

	private int size = 0; // �ֺ�

	private Color color = new Color(225, 225, 225); // ������ɫ

	private SimpleAttributeSet attrSet = null; // ���Լ�

	public FontAndText() {
	}

	// ��������ֵ
	public FontAndText(String msg, String fontName, int fontSize, Color color) {
		this.msg = msg;
		this.name = fontName;
		this.size = fontSize;
		this.color = color;
	}
	
	// ��ȡ����ֵ
	public SimpleAttributeSet getAttrSet() {
		attrSet = new SimpleAttributeSet();
		if (name != null) {
			StyleConstants.setFontFamily(attrSet, name);
		}
		StyleConstants.setFontSize(attrSet, size);
		if (color != null)
			StyleConstants.setForeground(attrSet, color);
		return attrSet;
	}

	// ��дtoString�����������ݴ��䣨��ʽ������|�ֺ�|R-G-B|��Ϣ��
	public String toString() {
		return name + "|" + size + "|" + color.getRed() + "-" + color.getGreen() + "-" + color.getBlue() + "|" + msg;
	}

	// get/set ����
	public String getText() {
		return msg;
	}

	public void setText(String text) {
		this.msg = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
