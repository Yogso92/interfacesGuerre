package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import actions.CombatCouteau;
import actions.CombatPistolet;
import actions.FusilAssaut;
import actions.FusilSniper;
import actions.Operation;
import actions.PetiteTrousse;
import persos.Civil;
import persos.Guerrier;
import persos.Medecin;
import persos.Personnage;
import persos.Sniper;

public class DelPan extends JPanel implements Refreshable {
	private Class[] pickClass = {Civil.class, Guerrier.class, Medecin.class, Sniper.class};
	private Class[] pickEsp = {CombatCouteau.class, CombatPistolet.class, FusilAssaut.class, FusilSniper.class};
	private String[] classNames = {Civil.class.getSimpleName(), Guerrier.class.getSimpleName(), Medecin.class.getSimpleName(), Sniper.class.getSimpleName()};
	private String[] espNames = {CombatCouteau.class.getSimpleName(), CombatPistolet.class.getSimpleName(), FusilAssaut.class.getSimpleName(), FusilSniper.class.getSimpleName()};
	private Class[] pickSoin = {PetiteTrousse.class, Operation.class};
	private String[] soinNames = {PetiteTrousse.class.getSimpleName(), Operation.class.getSimpleName()}, filterNames = {"Classe", "Arme", "Soin", "Tout"};
	private int filterIndex = 0, resFilterIndex = 0;
	private ArrayList<Personnage> resFiltre = new ArrayList<Personnage>(), allP;
	private JComboBox filter = new JComboBox(filterNames);
	private JComboBox resultFilter = new JComboBox(classNames);
	private DefaultComboBoxModel model = (DefaultComboBoxModel) resultFilter.getModel();
	private JTable table = new JTable();
	final DefaultTableModel tModel = (DefaultTableModel)table.getModel();
	private String[] col = {"Nom", "Classe", "Arme", "Soin", "Vie"};
	private JScrollPane scrollPane = new JScrollPane(table);
	private JPanel filterPan = new JPanel();
	private int selected = 0;
	
	
	public DelPan(ArrayList<Personnage> p) {
		allP = p;
		table.setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		filterPan.setPreferredSize(new Dimension(400, 100));
		filterPan.setLayout(new BoxLayout(filterPan, BoxLayout.LINE_AXIS));
		add(filterPan, BorderLayout.NORTH);
		filterPan.add(filter);
		filterPan.add(resultFilter);
		filter.addActionListener(new ListFilter());
		resultFilter.addActionListener(new ListResultFilter());
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selected = table.getSelectedRow();
			}
		});
		add(scrollPane,BorderLayout.CENTER);
		showResults();
		tModel.fireTableDataChanged();
	}
	
	public Personnage getDel() {
		Personnage pe = (Personnage) table.getValueAt(selected, 1);
		resFiltre.remove(pe);
		return pe;
	}
	
	private void showResults(){
		resFiltre.removeAll(allP);
		if (resFilterIndex < 0) for(Personnage pe: allP) resFiltre.add(pe);
		else { switch (filterIndex){
		case 0:
			for(Personnage pe: allP ) {
				if (pickClass[resFilterIndex].equals(pe.getClass())) 
					resFiltre.add(pe);
			}
			break;
		case 1:
			for(Personnage pe: allP ) {
				if (espNames[resFilterIndex].equals(pe.getEspritCombatif().getClass().getSimpleName())) 
					resFiltre.add(pe);
			}
			break;
		case 2:
			for(Personnage pe: allP ) {
				if (soinNames[resFilterIndex].equals(pe.getSoin().getClass().getSimpleName())) 
					resFiltre.add(pe);
			}
			break;
		default:
			for(Personnage pe: allP) resFiltre.add(pe);
		}}
		Object[][] data = new Object[resFiltre.size()][5];
		int i = 0;
		tModel.setRowCount(resFiltre.size());
		tModel.setColumnCount(5);
		
		//mise en forme des données nom/classe/arme/soin/vie
		for(Personnage pe : resFiltre) {
		data[i][0] = pe.getNom();
		data[i][1] = pe;
		data[i][2] = pe.getEspritCombatif().getArme();
		data[i][3] = pe.getSoin().getClass().getSimpleName();
		data[i][4] = pe.getVie();
		i++;}
		i = 0;
		//insertion des données dans le tableau
		for(Object[] d:data) {
			int k = 0;
			for (Object d2:d) {
				table.setValueAt(data[i][k], i, k);
				k++;
			}
			i++;
		}
		for( int l = 0; l< col.length; l++) table.getColumnModel().getColumn(l).setHeaderValue(col[l]);
		tModel.fireTableDataChanged();
		
	}
	
	private class ListFilter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			filterIndex = filter.getSelectedIndex();
			model.removeAllElements();
			switch (filterIndex) {
			case 0:
				for(String n: classNames) {
					model.addElement(n);
				}
				break;
			case 1:
				for(String n: espNames) {
					model.addElement(n);
				}
				break;
			case 2:
				for(String n: soinNames) {
					model.addElement(n);
				}
				break;
			default:
				model.addElement("n/a");	
				showResults();
			}
			resultFilter.setModel(model);
			resFilterIndex = 0;
			resultFilter.setSelectedIndex(0);
			
			
		}
		
	}
	private class ListResultFilter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			resFilterIndex = resultFilter.getSelectedIndex();
			showResults();
			
		}
		
	}
	public void refresh(ArrayList<Personnage> p) {
		
		showResults();
	}
}
