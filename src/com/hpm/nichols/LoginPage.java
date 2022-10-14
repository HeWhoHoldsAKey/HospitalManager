package com.hpm.nichols;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class LoginPage {
	private Text txtUsername;
	private Text txtPassword;
	private HashMap<String, String> loginInfo = new HashMap<String, String>();
	private boolean loggedIn;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {

		loggedIn = false;

		// Putting in the login info
		// It is stored as USERNAME, PASSWORD
		loginInfo.put("Admin", "Admin");
		loginInfo.put("Daniel", "apPle");
		loginInfo.put("Johnny", "59876");

		// Setting up the window
		Display display = Display.getDefault();
		Shell shlLogin = new Shell();
		shlLogin.setSize(450, 300);
		shlLogin.setText("Login");
		shlLogin.setLayout(null);

		// Username input
		txtUsername = new Text(shlLogin, SWT.BORDER);
		txtUsername.setText("Username");
		txtUsername.setBounds(127, 65, 165, 21);

		// Password input
		txtPassword = new Text(shlLogin, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setText("Password");
		txtPassword.setBounds(127, 116, 165, 21);

		// THIS IS COOL. This makes sure no one else can see your password. not even
		// you.
		txtPassword.setEchoChar('*');

		// States that this is the login pannel
		Label lblLoginPannel = new Label(shlLogin, SWT.NONE);
		lblLoginPannel.setAlignment(SWT.CENTER);
		lblLoginPannel.setBounds(170, 10, 75, 15);
		lblLoginPannel.setText("Login Pannel");

		// The login button
		Button btnLogin = new Button(shlLogin, SWT.NONE);
		btnLogin.setBounds(170, 178, 75, 25);
		btnLogin.setText("LOGIN");

		// More lables
		Label lblUsername = new Label(shlLogin, SWT.NONE);
		lblUsername.setBounds(47, 68, 55, 15);
		lblUsername.setText("Username");

		Label lblPassword = new Label(shlLogin, SWT.NONE);
		lblPassword.setBounds(47, 119, 55, 15);
		lblPassword.setText("Password");

		Label lblWrongCreds = new Label(shlLogin, SWT.NONE);
		lblWrongCreds.setAlignment(SWT.CENTER);
		lblWrongCreds.setBounds(127, 44, 165, 15);
		lblWrongCreds.setText("Wrong Username Or Password");
		lblWrongCreds.setVisible(false);

		// This checks to see if you pressed the login button
		btnLogin.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Dont mess with this. Kinda useless but is required for. Idk.

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loggedIn = false;

				// Checks to see if you entered a valid username and password combo
				for (String i : loginInfo.keySet()) {
					if (txtUsername.getText().contains(i) && txtPassword.getText().contains(loginInfo.get(i))) {
						loggedIn = true;
						// break;
					}
				}

				// lets ya know if ya logged in or didnt get the login correct.
				if (loggedIn) {
					System.out.println("Logging In");
					shlLogin.close();
				} else {
					lblWrongCreds.setVisible(true);
					System.out.println("Wrong Credentials");
				}

			}

		});

		// No idea what this does but im keeping it down here because it seems like a
		// loop
		shlLogin.open();
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public boolean getLoggedIn() {
		return loggedIn;
	}

}
