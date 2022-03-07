package com.hpm.nichols;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;

public class MainWindow {
	private String[] titles = { "First name", "Last name", "Room Num", "Patient ID", "Insurance Info", "Symptoms",
			"Quarentined", "Safety Level" };
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table patientTable;
	private HashMap<Integer, Patient> patients = new HashMap<Integer, Patient>();
	private Text txtPtFirstName;
	private Text txtPtLastName;
	private Text txtInsuranceInfo;
	private Text txtSymptoms;
	private Text txtDrnotes;
	private Button btnIsQuarentined;
	private Spinner spinnerSafetyLevel;
	private Spinner spinnerRoomNum;
	private Spinner spinnerPatientId;
	private Integer hashKey;
	private Boolean editingenabled = false;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shlHospitalmanager = new Shell();
		shlHospitalmanager.setSize(920, 1080);
		shlHospitalmanager.setText("HospitalManager");
		shlHospitalmanager.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shlHospitalmanager, SWT.NONE);
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Patient List");

		patientTable = formToolkit.createTable(tabFolder, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		tbtmNewItem.setControl(patientTable);
		formToolkit.paintBordersFor(patientTable);
		patientTable.setHeaderVisible(true);
		patientTable.setLinesVisible(true);

		TabItem tbtmPatientinfotab = new TabItem(tabFolder, SWT.NONE);
		tbtmPatientinfotab.setText("PatientInfoTab");

		ScrolledForm scrldfrmViewPatientInfo = formToolkit.createScrolledForm(tabFolder);
		tbtmPatientinfotab.setControl(scrldfrmViewPatientInfo);
		formToolkit.paintBordersFor(scrldfrmViewPatientInfo);
		scrldfrmViewPatientInfo.setText("Patient Info");

		Label lblPatientName = formToolkit.createLabel(scrldfrmViewPatientInfo.getBody(), "Patient FirstName",
				SWT.NONE);
		lblPatientName.setAlignment(SWT.CENTER);
		lblPatientName.setBounds(10, 13, 110, 15);

		Label lblNewLabel = formToolkit.createLabel(scrldfrmViewPatientInfo.getBody(), "Patient Last Name", SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 40, 110, 15);

		txtPtFirstName = formToolkit.createText(scrldfrmViewPatientInfo.getBody(), "New Text", SWT.NONE);
		txtPtFirstName.setEditable(false);
		txtPtFirstName.setText("FirstName");
		txtPtFirstName.setBounds(126, 10, 143, 21);

		txtPtLastName = formToolkit.createText(scrldfrmViewPatientInfo.getBody(), "New Text", SWT.NONE);
		txtPtLastName.setEditable(false);
		txtPtLastName.setText("LastName");
		txtPtLastName.setBounds(126, 37, 143, 21);

		txtInsuranceInfo = formToolkit.createText(scrldfrmViewPatientInfo.getBody(), "New Text", SWT.NONE);
		txtInsuranceInfo.setEditable(false);
		txtInsuranceInfo.setText("PatientInsurance Info");
		txtInsuranceInfo.setBounds(126, 64, 329, 48);

		Label lblInsInfo = formToolkit.createLabel(scrldfrmViewPatientInfo.getBody(), "Insurance Info", SWT.NONE);
		lblInsInfo.setAlignment(SWT.CENTER);
		lblInsInfo.setBounds(10, 61, 109, 15);

		txtSymptoms = formToolkit.createText(scrldfrmViewPatientInfo.getBody(), "New Text", SWT.NONE);
		txtSymptoms.setEditable(false);
		txtSymptoms.setText("Symptoms");
		txtSymptoms.setBounds(126, 118, 329, 202);

		Label lblSymptoms = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblSymptoms.setAlignment(SWT.CENTER);
		lblSymptoms.setBounds(11, 121, 109, 15);
		formToolkit.adapt(lblSymptoms, true, true);
		lblSymptoms.setText("Symptoms");

		Label lblNotes = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblNotes.setAlignment(SWT.CENTER);
		lblNotes.setBounds(10, 305, 55, 15);
		formToolkit.adapt(lblNotes, true, true);
		lblNotes.setText("Notes");

		txtDrnotes = new Text(scrldfrmViewPatientInfo.getBody(), SWT.BORDER);
		txtDrnotes.setEditable(false);
		txtDrnotes.setText("DRnotes");
		txtDrnotes.setBounds(10, 326, 876, 634);
		formToolkit.adapt(txtDrnotes, true, true);

		btnIsQuarentined = new Button(scrldfrmViewPatientInfo.getBody(), SWT.CHECK);
		btnIsQuarentined.setEnabled(false);
		btnIsQuarentined.setAlignment(SWT.CENTER);
		btnIsQuarentined.setBounds(275, 12, 110, 16);
		formToolkit.adapt(btnIsQuarentined, true, true);
		btnIsQuarentined.setText("Is Quarentined?");

		spinnerSafetyLevel = new Spinner(scrldfrmViewPatientInfo.getBody(), SWT.BORDER);
		spinnerSafetyLevel.setEnabled(false);
		spinnerSafetyLevel.setMaximum(5);
		spinnerSafetyLevel.setBounds(363, 37, 47, 22);
		formToolkit.adapt(spinnerSafetyLevel);
		formToolkit.paintBordersFor(spinnerSafetyLevel);

		Label lblNewLabel_1 = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setBounds(275, 40, 82, 15);
		formToolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("Safety Level");

		spinnerRoomNum = new Spinner(scrldfrmViewPatientInfo.getBody(), SWT.BORDER);
		spinnerRoomNum.setEnabled(false);
		spinnerRoomNum.setMaximum(5000);
		spinnerRoomNum.setBounds(568, 13, 98, 22);
		formToolkit.adapt(spinnerRoomNum);
		formToolkit.paintBordersFor(spinnerRoomNum);

		Label lblRoomnumber = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblRoomnumber.setAlignment(SWT.CENTER);
		lblRoomnumber.setBounds(464, 13, 98, 15);
		formToolkit.adapt(lblRoomnumber, true, true);
		lblRoomnumber.setText("RoomNumber");

		spinnerPatientId = new Spinner(scrldfrmViewPatientInfo.getBody(), SWT.BORDER);
		spinnerPatientId.setEnabled(false);
		spinnerPatientId.setMaximum(999999999);
		spinnerPatientId.setBounds(568, 40, 98, 22);
		formToolkit.adapt(spinnerPatientId);
		formToolkit.paintBordersFor(spinnerPatientId);

		Label lblNewLabel_2 = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(464, 40, 98, 15);
		formToolkit.adapt(lblNewLabel_2, true, true);
		lblNewLabel_2.setText("Patient ID");

		Button btnToggleedit = new Button(scrldfrmViewPatientInfo.getBody(), SWT.NONE);

		btnToggleedit.setBounds(579, 191, 75, 25);
		formToolkit.adapt(btnToggleedit, true, true);
		btnToggleedit.setText("ToggleEdit");

		Label lblEditingDisplay = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblEditingDisplay.setAlignment(SWT.CENTER);
		lblEditingDisplay.setBounds(544, 170, 143, 15);
		formToolkit.adapt(lblEditingDisplay, true, true);
		lblEditingDisplay.setText("Editing Disabled");

		Menu menu = new Menu(shlHospitalmanager, SWT.BAR);
		shlHospitalmanager.setMenuBar(menu);

		MenuItem mntmMenu = new MenuItem(menu, SWT.CASCADE);
		mntmMenu.setText("Menu");

		Menu menu_1 = new Menu(mntmMenu);
		mntmMenu.setMenu(menu_1);

		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);

		mntmSave.setText("Save");

		patients.putAll(ProjectIo.loadPatients());

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

		patientTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// 3:42 am on sunday when i did this...
				TableItem[] select = patientTable.getSelection();
				hashKey = patientTable.getSelectionIndex();

				txtPtFirstName.setText(select[0].getText(0));
				txtPtLastName.setText(select[0].getText(1));
				spinnerRoomNum.setSelection(Integer.parseInt(select[0].getText(2)));
				spinnerPatientId.setSelection(Integer.parseInt(select[0].getText(3)));
				txtInsuranceInfo.setText(select[0].getText(4));
				txtSymptoms.setText(select[0].getText(5));
				btnIsQuarentined.setSelection(Boolean.parseBoolean(select[0].getText(6)));
				spinnerSafetyLevel.setSelection(Integer.parseInt(select[0].getText(7)));
				txtDrnotes.setText(patients.get(hashKey).getDrNotes());

			}

		});

		btnToggleedit.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				editingenabled = !editingenabled;

				txtPtFirstName.setEditable(editingenabled);
				txtPtLastName.setEditable(editingenabled);
				txtInsuranceInfo.setEditable(editingenabled);
				txtSymptoms.setEditable(editingenabled);
				txtDrnotes.setEditable(editingenabled);
				btnIsQuarentined.setEnabled(editingenabled);
				spinnerSafetyLevel.setEnabled(editingenabled);
				spinnerRoomNum.setEnabled(editingenabled);
				spinnerPatientId.setEnabled(editingenabled);

				if (editingenabled) {
					lblEditingDisplay.setText("Editing Enabled");
				} else {
					lblEditingDisplay.setText("Editing Disabled");

				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		mntmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				ProjectIo.savePatients(patients, hashKey, txtPtFirstName.getText(), txtPtLastName.getText(),
						spinnerRoomNum.getSelection(), spinnerPatientId.getSelection(), txtInsuranceInfo.getText(),
						txtSymptoms.getText(), btnIsQuarentined.getSelection(), spinnerSafetyLevel.getSelection(),
						txtDrnotes.getText());

				updateTable();
			}
		});

		shlHospitalmanager.open();
		shlHospitalmanager.layout();
		while (!shlHospitalmanager.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	public void updateTable() {
		patientTable.removeAll();
		patients.putAll(ProjectIo.loadPatients());
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

		// patientTable.redraw();
	}
}
