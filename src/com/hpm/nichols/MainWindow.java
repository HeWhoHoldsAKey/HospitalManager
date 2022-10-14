package com.hpm.nichols;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;
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
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private Text txtPtFirstName;
	private Text txtPtLastName;
	private Text txtInsuranceInfo;
	private Text txtSymptoms;
	private Text txtDrnotes;
	private Button btnIsQuarentined;
	private Spinner spinnerSafetyLevel;
	private Spinner spinnerRoomNum;
	private Spinner spinnerPatientId;
	private Boolean editingenabled = false;
	
	//Set to -1 as a marker that nothing has been selected
	private int patientSelectedIndex = -1;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		
		//Letting it know what display how how to set up the main window
		Display display = Display.getDefault();
		Shell shlHospitalmanager = new Shell();
		shlHospitalmanager.setSize(920, 1080);
		shlHospitalmanager.setText("HospitalManager");
		shlHospitalmanager.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		//Creates a tabl folder to store the different tabs or pages in
		TabFolder tabFolder = new TabFolder(shlHospitalmanager, SWT.NONE);
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);
		
		//Creates the first page for the list
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Patient List");

		//Sets up the foundation of the table.
		patientTable = formToolkit.createTable(tabFolder, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		tbtmNewItem.setControl(patientTable);
		formToolkit.paintBordersFor(patientTable);
		patientTable.setHeaderVisible(true);
		patientTable.setLinesVisible(true);
		
		//Creates the patient info page
		TabItem tbtmPatientinfotab = new TabItem(tabFolder, SWT.NONE);
		tbtmPatientinfotab.setText("PatientInfoTab");
		
		//Sets the layout of the page
		ScrolledForm scrldfrmViewPatientInfo = formToolkit.createScrolledForm(tabFolder);
		tbtmPatientinfotab.setControl(scrldfrmViewPatientInfo);
		formToolkit.paintBordersFor(scrldfrmViewPatientInfo);
		scrldfrmViewPatientInfo.setText("Patient Info");

		// ---------------------------------------------------//
		// This is simply setting lables on the editing page //
		// -------------------------------------------------//

		Label lblPatientFirstName = formToolkit.createLabel(scrldfrmViewPatientInfo.getBody(), "Patient FirstName",
				SWT.NONE);
		lblPatientFirstName.setAlignment(SWT.CENTER);
		lblPatientFirstName.setBounds(10, 13, 110, 15);

		Label lblPatientLastName = formToolkit.createLabel(scrldfrmViewPatientInfo.getBody(), "Patient Last Name",
				SWT.NONE);
		lblPatientLastName.setAlignment(SWT.CENTER);
		lblPatientLastName.setBounds(10, 40, 110, 15);

		Label lblInsInfo = formToolkit.createLabel(scrldfrmViewPatientInfo.getBody(), "Insurance Info", SWT.NONE);
		lblInsInfo.setAlignment(SWT.CENTER);
		lblInsInfo.setBounds(10, 61, 109, 15);

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

		Label lblSafetyLvl = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblSafetyLvl.setAlignment(SWT.CENTER);
		lblSafetyLvl.setBounds(275, 40, 82, 15);
		formToolkit.adapt(lblSafetyLvl, true, true);
		lblSafetyLvl.setText("Safety Level");

		Label lblEditingDisplay = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblEditingDisplay.setAlignment(SWT.CENTER);
		lblEditingDisplay.setBounds(544, 170, 143, 15);
		formToolkit.adapt(lblEditingDisplay, true, true);
		lblEditingDisplay.setText("Editing Disabled");

		Label lblPatientID = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblPatientID.setAlignment(SWT.CENTER);
		lblPatientID.setBounds(464, 40, 98, 15);
		formToolkit.adapt(lblPatientID, true, true);
		lblPatientID.setText("Patient ID");

		Label lblRoomnumber = new Label(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		lblRoomnumber.setAlignment(SWT.CENTER);
		lblRoomnumber.setBounds(464, 13, 98, 15);
		formToolkit.adapt(lblRoomnumber, true, true);
		lblRoomnumber.setText("RoomNumber");

		// -------------------------------------------------------------------------------//
		// This is simply setting the lable text and other weird stuff up on the editing //
		// page                                                                         //
		// ----------------------------------------------------------------------------//

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

		txtSymptoms = formToolkit.createText(scrldfrmViewPatientInfo.getBody(), "New Text", SWT.NONE);
		txtSymptoms.setEditable(false);
		txtSymptoms.setText("Symptoms");
		txtSymptoms.setBounds(126, 118, 329, 202);

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

		spinnerRoomNum = new Spinner(scrldfrmViewPatientInfo.getBody(), SWT.BORDER);
		spinnerRoomNum.setEnabled(false);
		spinnerRoomNum.setMaximum(5000);
		spinnerRoomNum.setBounds(568, 13, 98, 22);
		formToolkit.adapt(spinnerRoomNum);
		formToolkit.paintBordersFor(spinnerRoomNum);

		spinnerPatientId = new Spinner(scrldfrmViewPatientInfo.getBody(), SWT.BORDER);
		spinnerPatientId.setEnabled(false);
		spinnerPatientId.setMaximum(999999999);
		spinnerPatientId.setBounds(568, 40, 98, 22);
		formToolkit.adapt(spinnerPatientId);
		formToolkit.paintBordersFor(spinnerPatientId);

		// This button is special because. For some reason its not picky like the others.
		Button btnToggleedit = new Button(scrldfrmViewPatientInfo.getBody(), SWT.NONE);
		btnToggleedit.setBounds(579, 191, 75, 25);
		formToolkit.adapt(btnToggleedit, true, true);
		btnToggleedit.setText("ToggleEdit");

		// --------------------------------------------------------------------------//
		// This is all menu stuff and its wildly confusing and honestly unnecessary //
		// ------------------------------------------------------------------------//

		// This states that there is a menue in the style of BAR at the top.
		Menu menu = new Menu(shlHospitalmanager, SWT.BAR);
		shlHospitalmanager.setMenuBar(menu);

		// This states that there will be an item in the menu that cascades when pressed
		MenuItem mntmMenu = new MenuItem(menu, SWT.CASCADE);
		mntmMenu.setText("Menu");

		// This adds. an entire different menu inside the menu. idek at this point but
		// adding a button wont work because. yeah.
		Menu menu_1 = new Menu(mntmMenu);
		mntmMenu.setMenu(menu_1);

		// FINALLY, this is the save but- i mean menu item. Because menus cant have
		// buttons.
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setText("Save");

		// Sets the columns up by setting the name of the column to one of the titles
		for (String i : titles) {
			TableColumn column = new TableColumn(patientTable, SWT.NULL);
			column.setText(i);
		}

		// Updates the table and gets the show on the road
		updateTable();

		// When you select a patient this brings all the data to the page where you can
		// edit the patient info

		patientTable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				patientSelectedIndex = patientTable.getSelectionIndex();

				txtPtFirstName.setText(patients.get(patientSelectedIndex).getFirstName());
				txtPtLastName.setText(patients.get(patientSelectedIndex).getFirstName());
				spinnerRoomNum.setSelection(patients.get(patientSelectedIndex).getRoomNumber());
				spinnerPatientId.setSelection(patients.get(patientSelectedIndex).getPatientId());
				txtInsuranceInfo.setText(patients.get(patientSelectedIndex).getInsuranceInfo());
				txtSymptoms.setText(patients.get(patientSelectedIndex).getSymptoms());
				btnIsQuarentined.setSelection(patients.get(patientSelectedIndex).getQuarentined());
				spinnerSafetyLevel.setSelection(patients.get(patientSelectedIndex).getSafetyLevel());
				txtDrnotes.setText(patients.get(patientSelectedIndex).getDrNotes());

			}

			// It gets very unhappy when this is not here but its only use is so that it has
			// a default setting when nothing has happened thus, there is no code.
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		// Toggles weather or not editing is turned on.

		btnToggleedit.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// Flips the boolean
				editingenabled = !editingenabled;

				// This sets the editable value of each item
				txtPtFirstName.setEditable(editingenabled);
				txtPtLastName.setEditable(editingenabled);
				txtInsuranceInfo.setEditable(editingenabled);
				txtSymptoms.setEditable(editingenabled);
				txtDrnotes.setEditable(editingenabled);
				btnIsQuarentined.setEnabled(editingenabled);
				spinnerSafetyLevel.setEnabled(editingenabled);
				spinnerRoomNum.setEnabled(editingenabled);
				spinnerPatientId.setEnabled(editingenabled);

				// Checks weather or not editing is enabled or disabled and changes the display
				// accordingly

				if (editingenabled) {
					lblEditingDisplay.setText("Editing Enabled");
				} else {
					lblEditingDisplay.setText("Editing Disabled");

				}

			}

			// It gets very unhappy when this is not here but its only use is so that it has
			// a default setting when nothing has happened thus, there is no code.

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		// When you press the save button in the drop down menu this occures!
		mntmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//Checks to make sure the current selection is valid
				if(patientSelectedIndex != -1) {
					// Updates the current patient object using the values inside the editing page.
					patients.get(patientSelectedIndex).setFirstName(txtPtFirstName.getText());
					patients.get(patientSelectedIndex).setLastName(txtPtLastName.getText());
					patients.get(patientSelectedIndex).setInsuranceInfo(txtInsuranceInfo.getText());
					patients.get(patientSelectedIndex).setSymptoms(txtSymptoms.getText());
					patients.get(patientSelectedIndex).setDrNotes(txtDrnotes.getText());
					patients.get(patientSelectedIndex).setQuarentined(btnIsQuarentined.getSelection());
					patients.get(patientSelectedIndex).setSafetyLevel(spinnerSafetyLevel.getSelection());
					patients.get(patientSelectedIndex).setRoomNumber(spinnerRoomNum.getSelection());
					patients.get(patientSelectedIndex).setPatientId(spinnerPatientId.getSelection());
				}
				// Tells the project io to save the list of patients
				ProjectIo.savePatients(patients);

				// Updates the table so if anything changed it will be visible
				updateTable();
			}
		});

		// This is funky code of which i don't exactly know how or why it works but it
		// prevents a memory leak and is essentially the main loop of the program.
		shlHospitalmanager.open();
		shlHospitalmanager.layout();
		while (!shlHospitalmanager.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	public void updateTable() {

		// Multithreading is here!
		// If need be shoot me an email and i could somewhat explain this!
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {

				// In previous versions i forgot that this was important and it would duplicate
				// the table causing extra memory usage.
				patientTable.removeAll();

				// YOU AALSO NEED TO CLEAR THIS OR IT WILL DOUBLE EVERY TIME
				patients.clear();

				// Loads in all the patients from the file. Will say this could be more
				// efficient by using a temporary file to store the unsaved changes however i
				// Don't believe that is necessary because. yeah.
				patients.addAll(ProjectIo.loadPatients());

				// Loops through the list updating the entire table
				// Also could be more efficient but SWT is not a fan of it at all so if you
				// would like to figure it out go for it cuz, I'm good.
				for (Patient i : patients) {
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
			}
		});

		// If you don't pack the table, it has no idea where to show the items thus
		// nothing will show up.
		// Why? idk don't ask me ask the guys who designed SWT.
		for (int i = 0; i < titles.length; i++) {
			patientTable.getColumn(i).pack();
		}

	}
}
