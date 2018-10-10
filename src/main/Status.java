package main;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.Refreshable;
import persos.Personnage;

public class Status extends JPanel implements Refreshable{
	private JTable table = new JTable();
	final DefaultTableModel model = (DefaultTableModel)table.getModel();
	//private static JButton btnAction = new JButton("Action");
	protected Object[][] data;
	//private JButton btnRefresh= new JButton("Refresh");
	String[] col = {"Nom", "Classe", "Arme", "Soin", "Vie"};

	public Status( ArrayList<Personnage> p) {
		

		setData(p, col);
		
		
		setBounds(100, 100, 678, 470);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		this.add(scrollPane);
		//contentPane.add(btnAction, BorderLayout.SOUTH);
		/*btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setData(p, colNames);
			}
		});
		contentPane.add(btnRefresh, BorderLayout.EAST);
		btnAction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ActionSelect action = new ActionSelect(p);
							action.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});*/
	}
	private void setData(ArrayList<Personnage> p, String[] colNames){
		data = new Object[p.size()][5];
		int i = 0;
		model.setRowCount(p.size());
		model.setColumnCount(5);
		
		for(Personnage pe : p) {
		this.data[i][0] = pe.getNom();
		this.data[i][1] = pe;
		this.data[i][2] = pe.getEspritCombatif().getArme();
		this.data[i][3] = pe.getSoin().getClass().getSimpleName();
		this.data[i][4] = pe.getVie();
		i++;}
		i = 0;
		for(Object[] d:data) {
			int k = 0;
			for (Object d2:d) {
				table.setValueAt(data[i][k], i, k);
				k++;
			}
			i++;
		}
		for( int l = 0; l< colNames.length; l++) table.getColumnModel().getColumn(l).setHeaderValue(colNames[l]);
		model.fireTableDataChanged();
	}
	public void refresh(ArrayList<Personnage> p) {
		setData(p, col);
	}
}

