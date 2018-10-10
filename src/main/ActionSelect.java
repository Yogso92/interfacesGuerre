package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.Actions;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import persos.*;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionSelect extends JFrame {
	private JPanel contentPane;
	private JList listA = new JList();
	private JList listT = new JList();
	private JList listP = new JList();
	public ActionSelect(Personnage[] p) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		listP.setVisibleRowCount(1);
		contentPane.add(listP, BorderLayout.WEST);
		listT.setVisibleRowCount(1);
		contentPane.add(listT, BorderLayout.CENTER);
		listA.setVisibleRowCount(1);
		contentPane.add(listA, BorderLayout.EAST);
		
		
		listP.setListData(p);
		listT.setListData(p);
		listA.setListData(Actions.values());
		
		JLabel lblTop = new JLabel("Acteur / Cible / action");
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTop, BorderLayout.NORTH);
		
		JButton btnGo = new JButton("Go");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ActionHandler performAction = new ActionHandler((Personnage) listP.getSelectedValue(),(Personnage) listT.getSelectedValue(), (Actions) listA.getSelectedValue());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
			
		});
		contentPane.add(btnGo, BorderLayout.SOUTH);
		
		
		
	}
	
	public String returnAction() {
		return listA.getSelectedValue().toString();
	}
	public Object returnP() {
		return (Object) listP.getSelectedValue();
	}
	public Object returnT() {
		return (Object) listT.getSelectedValue();
	}
	
	
}
