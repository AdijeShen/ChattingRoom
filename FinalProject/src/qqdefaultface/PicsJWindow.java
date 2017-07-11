package qqdefaultface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import frame.ChatFrame;

public class PicsJWindow extends JWindow {
	private static final long serialVersionUID = 1L;
	GridLayout gridLayout1 = new GridLayout(7, 15);//����Ϊ7*15�����񲼾�
	JLabel[] ico = new JLabel[105]; //�洢���б��������
	int i;
	ChatFrame owner;
	String[] intro = new String[105];

	public PicsJWindow(ChatFrame owner) {
		super(owner);
		this.owner = owner;
		try {
			init();
			this.setAlwaysOnTop(true);//��Զ�ö�
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void init() throws Exception {
		this.setPreferredSize(new Dimension(28 * 15, 28 * 7));//ÿ������Ϊ28*28������15��������7������105������
		JPanel p = new JPanel();
		p.setOpaque(true);		//������ʾ����Ŀؼ���͸��
		this.setContentPane(p);
		p.setLayout(gridLayout1);
		p.setBackground(SystemColor.text);
		String fileName = "";
		for (i = 0; i < ico.length; i++) {
			fileName = "qqdefaultface/" + i + ".gif";//��ȡ����·�� 

			//
			ico[i] = new JLabel(new ChatPic(PicsJWindow.class.getResource(fileName), i), SwingConstants.CENTER);//�����ʼ������
			ico[i].setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));	//���ñ߿���
			ico[i].setToolTipText("/"+i);//���ñ�����ʾ��
			ico[i].addMouseListener(new MouseAdapter() {//Ϊÿ���������õ������
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						JLabel cubl = (JLabel) (e.getSource());
						ChatPic cupic = (ChatPic) (cubl.getIcon());//��ȡ����ı��飨ChatPic��
						owner.insertSendPic(cupic);//�����촰�ڲ������
						getObj().dispose();//���ر��鴰��
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {//���ѡ��ʱ���߿���Ϊ��ɫ
					((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLUE));
				}

				@Override
				public void mouseExited(MouseEvent e) {//����ƿ�ʱ��Ϊԭ��
					((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
				}

			});
			p.add(ico[i]);//����ť��������
		}
		//����뿪�����ʱ���ؿ���
		p.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				getObj().dispose();
			}

		});
	}

	@Override
	public void setVisible(boolean show) {
		if (show) {
			determineAndSetLocation();//����������λ�ã�ʹ������������Ϣ����Ϸ�
		}
		super.setVisible(show);
	}

	private void determineAndSetLocation() {
		Point loc = owner.getPicBtn().getLocationOnScreen();//��ȡ����ͼƬ��ť�������Ļ��λ��
		setBounds(loc.x - getPreferredSize().width / 3, loc.y - getPreferredSize().height, getPreferredSize().width,
				getPreferredSize().height);//����λ��
	}

	//��ȡ��ǰ����
	private JWindow getObj() {
		return this;
	}
}
