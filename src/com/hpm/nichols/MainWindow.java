package com.hpm.nichols;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MainWindow {
	private String[] titles = { "First name", "Last name", "Room Num", "Patient ID", "Insurance Info", "Symptoms",
			"Quarentined", "Safety Level" };
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table patientTable;
	private HashMap<Integer, Patient> patients = new HashMap<Integer, Patient>();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shlHospitalmanager = new Shell();
		shlHospitalmanager.setSize(774, 395);
		shlHospitalmanager.setText("HospitalManager");
		shlHospitalmanager.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shlHospitalmanager, SWT.NONE);
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Patient List");

		patientTable = formToolkit.createTable(tabFolder, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);
		tbtmNewItem.setControl(patientTable);
		formToolkit.paintBordersFor(patientTable);
		patientTable.setHeaderVisible(true);
		patientTable.setLinesVisible(true);

		Menu menu = new Menu(shlHospitalmanager, SWT.BAR);
		shlHospitalmanager.setMenuBar(menu);

		MenuItem mntmMenu = new MenuItem(menu, SWT.CASCADE);
		mntmMenu.setText("Menu");

		Menu menu_1 = new Menu(mntmMenu);
		mntmMenu.setMenu(menu_1);

		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setText("Save");
		
		createPatients();
		
		for (String i : titles) {
			TableColumn column = new TableColumn(patientTable, SWT.NULL);
			column.setText(i);
		}

		for (Patient i : patients.values()) {
			TableItem item = new TableItem(patientTable, SWT.NULL);
			item.setText(0, i.getFirstName());
			item.setText(1, i.getLastName());
			item.setText(2, String.valueOf(i.getRoomNumber()));
			item.setText(3, String.valueOf(i.getPatientId()));
			item.setText(4, i.getInsuranceInfo());
			item.setText(5, i.getSymptoms());
			item.setText(6, String.valueOf(i.getQuarentined()));
			item.setText(7, String.valueOf(i.getSafetyLevel()));
		}

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			patientTable.getColumn(loopIndex).pack();
		}

		System.out.println(patientTable.getColumnCount());

		shlHospitalmanager.open();
		shlHospitalmanager.layout();
		while (!shlHospitalmanager.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void createPatients() {
		String[] Symptoms = {"anorexia",
				"weight loss",
				"cachexia",
				"chills and shivering",
				"convulsions",
				"deformity",
				"discharge",
				"dizziness / Vertigo",
				"fatigue ",
				"malaise",
				"asthenia",
				"hypothermia",
				"jaundice",
				"muscle weakness",
				"pyrexia ",
				"sweats",
				"swelling",
				"swollen or painful lymph node(s)",
				"weight gain"};
	}
}
