package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import actions.EspritCombatif;
import actions.Soin;
import main.Status;
import persos.Guerrier;
import persos.Medecin;
import persos.Personnage;
import persos.Sniper;

public class Fenetre extends JFrame {

	protected static ArrayList<Personnage> p = new ArrayList<Personnage>();
	
	private JPanel menuStatus = new JPanel();
	private JPanel menuAdd = new JPanel();
	private JPanel menuDel = new JPanel();
	private JPanel menuDo = new JPanel();
	private JPanel menu = new JPanel();
	private CardLayout clm = new CardLayout();
	Status status;
	JPanel action = new JPanel();
	DelPan delete;
	AddPan add = new AddPan();
	private JPanel content;
	private CardLayout cl;
	Bouton boutonAdd = new Bouton("Ajouter");
	Bouton boutonDel = new Bouton("Supprimer");
	Bouton boutonDo = new Bouton("Actions");
	Bouton boutonCancel = new Bouton("Annuler");
	Bouton boutonCancel2 = new Bouton("Annuler");
	Bouton boutonCancel3 = new Bouton("Annuler");
	Bouton boutonAddConf = new Bouton("Confirmer");
	Bouton boutonDelConf = new Bouton("Supprimer");
	Bouton boutonDoConf = new Bouton("Confirmer");
	
	public Fenetre() {
		//initialisation fenetre
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		this.setTitle("War Simulator");
		this.setSize(480, 720);
		this.setBackground(Color.GRAY);
		this.getContentPane().setLayout(new BorderLayout());
		
		//initialisation menu
		p.add(new Sniper());
		p.add(new Guerrier());
		p.add(new Medecin());
		status = new Status(p);
		delete = new DelPan(p);
		menu.setPreferredSize(new Dimension(480, 30));
		menu.setLayout(clm);
		menu.add(menuDo, "Action");
		menu.add(menuAdd, "Add");
		menu.add(menuDel, "Delete");
		menu.add(menuStatus, "Status");
		clm.show(menu,  "Status");
		
		menuStatus.setLayout(new BoxLayout(menuStatus, BoxLayout.LINE_AXIS));
		menuStatus.add(boutonDel);
		menuStatus.add(boutonAdd);
		menuStatus.add(boutonDo);

		
		menuDel.setLayout(new BoxLayout(menuDel, BoxLayout.LINE_AXIS));
		menuDel.add(boutonCancel);
		menuDel.add(boutonDelConf);
		boutonDelConf.setSize(menuDel.getWidth()/2-5, menuDel.getHeight());
		boutonCancel.setSize(menuDel.getWidth()/2-5, menuDel.getHeight());
		
		menuAdd.setLayout(new BoxLayout(menuAdd, BoxLayout.LINE_AXIS));
		menuAdd.add(boutonCancel2);
		menuAdd.add(boutonAddConf);
		boutonAddConf.setSize(menuAdd.getWidth()/2-5, menuAdd.getHeight());
		boutonCancel2.setSize(menuAdd.getWidth()/2-5, menuAdd.getHeight());
		
		menuDo.setLayout(new BoxLayout(menuDo, BoxLayout.LINE_AXIS));
		menuDo.add(boutonCancel3);
		menuDo.add(boutonDoConf);
		boutonDoConf.setSize(menuDo.getWidth()/2-5, menuDo.getHeight());
		boutonCancel3.setSize(menuDo.getWidth()/2-5, menuDo.getHeight());
		
		boutonCancel.addActionListener((e)->{status.refresh(p); cl.show(content, "Status"); clm.show(menu, "Status");});
		boutonCancel2.addActionListener((e)->{status.refresh(p); cl.show(content, "Status"); clm.show(menu, "Status");});
		boutonCancel3.addActionListener((e)->{status.refresh(p); cl.show(content, "Status"); clm.show(menu, "Status");});
		boutonDel.addActionListener((e)->{delete.refresh(p); cl.show(content, "Delete"); clm.show(menu, "Delete");});
		boutonAdd.addActionListener((e)->{cl.show(content, "Add"); clm.show(menu, "Add");});
		boutonDo.addActionListener((e)->{cl.show(content, "Action"); clm.show(menu, "Action");});
		//reste à ajouter les ActionListener pour les confirmations
		boutonAddConf.addActionListener(new ListAddConf()); 
		boutonDelConf.addActionListener(new ListDelConf());
		
		
		
		
		//initialisation CardLayout
		content = new JPanel();
		cl = new CardLayout();
		//initialisation vues
		
		status.setBackground(Color.BLUE);
		action.setBackground(Color.BLUE);
		delete.setBackground(Color.BLUE);
		add.setBackground(Color.BLUE);
		
		content.setLayout(cl);
		content.add(status, "Status");
		content.add(action, "Action");
		content.add(delete, "Delete");
		content.add(add, "Add");
		
		this.add(menu, BorderLayout.SOUTH);
		this.add(content, BorderLayout.CENTER);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args){
	    Fenetre fen = new Fenetre();
	    
	  }
	
	public static ArrayList<Personnage> getPers(){
		return p;
	}
	
	private class ListAddConf implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Personnage pe;
			Constructor[] c = add.getBuilders();
			try {
				pe = (Personnage) c[0].newInstance();
				pe.setEspritCombatif((EspritCombatif) c[1].newInstance());
				pe.setSoin((Soin)c[2].newInstance());
				pe.setNom(add.getNom());
				p.add(pe);
				System.out.println(pe);
				status.refresh(p);
				//insérer popup ici
				
				cl.show(content, "Status"); clm.show(menu, "Status");
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class ListDelConf implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Personnage pe = delete.getDel();
			p.remove(pe);
			status.refresh(p);
			delete.refresh(p);
			cl.show(content, "Status"); clm.show(menu, "Status");
			
		}
	}
	
}
