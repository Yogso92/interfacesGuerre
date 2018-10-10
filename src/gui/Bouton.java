package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener {
	private int h =200, l = (int) 400;
	private Dimension dim = new Dimension(l, h);
	private String nom;
	private Color fond = Color.BLACK, texte = Color.WHITE;
	public Bouton(String str) {
		super();
		this.setPreferredSize(dim);
		this.setText(str);
		this.nom = str;
		this.addMouseListener(this);
	}
	public void paintComponent(Graphics g) {
		g.setColor(this.fond);
		g.fillRect(0, 0, 240, 80);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		FontMetrics fm = g.getFontMetrics();
		int height = fm.getHeight();
		int width = fm.stringWidth(nom);
		g.setColor(this.texte);
		g.drawString(this.nom, (this.getWidth()/2 - width/2), this.getHeight()/2 + height/4);
				
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.fond = Color.DARK_GRAY;
		this.texte = Color.WHITE;
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.fond = Color.BLACK;
		this.texte = Color.WHITE;
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.fond = Color.WHITE;
		this.texte = Color.BLACK;
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		if((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0 && event.getX() < this.getWidth())){
			this.fond = Color.DARK_GRAY;
			this.texte = Color.WHITE;
		} else {
			this.fond = Color.BLACK;
			this.texte = Color.WHITE;
		}
	}
}
