package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.CombatCouteau;
import actions.CombatPistolet;
import actions.FusilAssaut;
import actions.FusilSniper;
import actions.Operation;
import actions.PetiteTrousse;
import persos.Civil;
import persos.Guerrier;
import persos.Medecin;
import persos.Sniper;

import javax.swing.JComboBox;

import java.lang.reflect.Constructor;
import java.util.function.Function;

import javax.swing.BoxLayout;

public class AddPan extends JPanel {
	private JTextField nom;
	private Class[] pickClass = {Civil.class, Guerrier.class, Medecin.class, Sniper.class};
	private Class[] pickEsp = {CombatCouteau.class, CombatPistolet.class, FusilAssaut.class, FusilSniper.class};
	private String[] classNames = {Civil.class.getSimpleName(), Guerrier.class.getSimpleName(), Medecin.class.getSimpleName(), Sniper.class.getSimpleName()};
	private String[] espNames = {CombatCouteau.class.getSimpleName(), CombatPistolet.class.getSimpleName(), FusilAssaut.class.getSimpleName(), FusilSniper.class.getSimpleName()};
	private Class[] pickSoin = {PetiteTrousse.class, Operation.class};
	private String[] soinNames = {PetiteTrousse.class.getSimpleName(), Operation.class.getSimpleName()};
	private Constructor classBuilder, espBuilder, soinBuilder;
	private int index = 0;
	
	public AddPan() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		nom = new JTextField();
		add(nom);
		nom.setColumns(10);
		
		JComboBox comboClass = new JComboBox(classNames);
		JComboBox comboArme = new JComboBox(espNames);
		JComboBox comboSoin = new JComboBox(soinNames);
		comboClass.setSelectedItem(classNames[0]);
		comboArme.setSelectedItem(espNames[0]);
		comboSoin.setSelectedItem(soinNames[0]);
		try {	//initialisation des builders pour eviter null pointer/no such method
			classBuilder = pickClass[comboClass.getSelectedIndex()].getConstructor();
			espBuilder = pickEsp[comboArme.getSelectedIndex()].getConstructor();
			soinBuilder = pickSoin[comboSoin.getSelectedIndex()].getConstructor();
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboClass.addActionListener((e)->{
			try {
				classBuilder = pickClass[comboClass.getSelectedIndex()].getConstructor();
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		comboArme.addActionListener((e)->{
			try {
				espBuilder = pickEsp[comboArme.getSelectedIndex()].getConstructor();
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		comboSoin.addActionListener((e)->{
			try {
				soinBuilder = pickSoin[comboSoin.getSelectedIndex()].getConstructor();
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		add(comboClass);
		add(comboArme);
		add(comboSoin);
	}
	
	public Constructor[] getBuilders() {
		Constructor[] c = {classBuilder, espBuilder, soinBuilder};
		return c;
		
	}

	public String getNom() {
		// TODO Auto-generated method stub
		String str = nom.getText();
		nom.setText("");
		return str;		
	}
	
}
