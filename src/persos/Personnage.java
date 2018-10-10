package persos;
import actions.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public abstract class Personnage {
	protected EspritCombatif espritCombatif = new Pacifiste();
	protected Deplacement dep = new Marcher();
	protected Soin soin = new PetiteTrousse();
	protected int vie = -1;
	protected String nom;
	
	public Personnage() {
		this.vie = 100;
		this.nom ="John Doe";
	}
	protected Personnage(String n, EspritCombatif esp, Deplacement depl, Soin soin) {
		this.espritCombatif = esp;
		this.soin = soin;
		this.dep = depl;
		this.vie = 100;
		this.nom= n;
	}
	
	public String seDeplacer() {
		return dep.deplacer();
	}
	public String combattre() {
		return espritCombatif.combat();
	}
	public String soigner() {
		return soin.soigne();
	}
	public void setSoin(Soin soin) {
		this.soin = soin;
	}
	public void setEspritCombatif(EspritCombatif esp) {
		this.espritCombatif = esp;
	}
	public void setDeplacement(Deplacement dep) {
		this.dep = dep;
	}
	public void attaquer(Personnage target) {
		target.setVie(target.getVie() - this.getDamage());
	}
	public void setVie(int k) { 
		this.vie = k;
		if (k<0) {
			System.out.println(this.getClass().getSimpleName()+ " est mort");
		} else if (this.vie > 100) this.vie = 100;
	System.out.println(this.getClass().getSimpleName()+ " a maintenant "+this.vie+ " PV");
	}
	
	public int getVie() {return this.vie;}
	public int getDamage() {return this.espritCombatif.getDmg();}
	public String toString() { return this.getClass().getSimpleName();}
	public Soin getSoin() { return this.soin;}
	public EspritCombatif getEspritCombatif() { return this.espritCombatif;}
	public Deplacement getDep() {
		return dep;
	}
	public void setDep(Deplacement dep) {
		this.dep = dep;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
