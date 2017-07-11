package common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * ������ʾ����
 */
public class CoolToolTip extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel();
	private boolean haveShowPlace;
	private Component attachedCom; // Ҫ��ʾ��ʾ���ݵ����
	private Component parentWindow; // Ҫ��ʾ��ʾ���ݵ�����Ĵ���

	public CoolToolTip(Component parent, Component attachedComponent, Color fillColor, int borderWidth, int offset) {
		this.parentWindow = parent;
		this.attachedCom = attachedComponent;
		label.setBorder(new EmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));
		label.setBackground(fillColor);
		label.setOpaque(true);
		label.setFont(new Font("system", 0, 12));

		setOpaque(false);
		// this.setBorder(new BalloonBorder(fillColor, offset));
		this.setLayout(new BorderLayout());
		add(label);

		setVisible(false);
		// ��������ʾʱ����ƶ�������Ҳ�����ƶ�
		this.attachedCom.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				if (isShowing()) {// ������ʾ ��ʾ�˵ģ���������λ��
					determineAndSetLocation();
				}
			}
		});
	}

	private void determineAndSetLocation() {
		if (!attachedCom.isShowing()) {
			return;
		}
		Point loc = attachedCom.getLocationOnScreen(); // �ؼ��������Ļ��λ��
		Point paPoint = parentWindow.getLocationOnScreen(); // ��Ӧ�����������Ļ��λ��
		// System.out.println(attachedComponent.getLocationOnScreen());
		setBounds(loc.x - paPoint.x, loc.y - paPoint.y - getPreferredSize().height, getPreferredSize().width,
				getPreferredSize().height);
	}

	public void setText(String text) {
		label.setText(text);
	}

	// �������ݱ���ͼƬ
	public void setIcon(Icon icon) {
		label.setIcon(icon);
	}

	// �������ݵ����ֺ�ͼƬ��ľ���
	public void setIconTextGap(int iconTextGap) {
		label.setIconTextGap(iconTextGap);
	}

	@Override
	public void setVisible(boolean show) {
		if (show) {
			determineAndSetLocation();
			findShowPlace();
		}
		super.setVisible(show);
	}

	private void findShowPlace() {
		if (haveShowPlace) {
			return;
		}
		
		JLayeredPane layeredPane = null;
		if (parentWindow instanceof JDialog) {
			layeredPane = ((JDialog) parentWindow).getLayeredPane();
		} else if (parentWindow instanceof JFrame) {
			layeredPane = ((JFrame) parentWindow).getLayeredPane();
		}

		if (layeredPane != null) {
			layeredPane.add(this, JLayeredPane.POPUP_LAYER);
			haveShowPlace = true;
		}
	}

	public Component getAttachedComponent() {
		return attachedCom;
	}

	public void setAttachedComponent(Component attachedComponent) {
		this.attachedCom = attachedComponent;
	}
}