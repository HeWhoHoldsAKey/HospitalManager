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

		loginInfo.put("Admin", "Admin");
		loginInfo.put("Daniel", "apPle");
		loginInfo.put("Johnny", "59876");

		Display display = Display.getDefault();
		Shell shlLogin = new Shell();
		shlLogin.setSize(450, 300);
		shlLogin.setText("Login");
		shlLogin.setLayout(null);

		txtUsername = new Text(shlLogin, SWT.BORDER);
		txtUsername.setText("Username");
		txtUsername.setBounds(127, 65, 165, 21);

		txtPassword = new Text(shlLogin, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setText("Password");
		txtPassword.setBounds(127, 116, 165, 21);
		txtPassword.setEchoChar('*');
		
		Label lblLoginPannel = new Label(shlLogin, SWT.NONE);
		lblLoginPannel.setAlignment(SWT.CENTER);
		lblLoginPannel.setBounds(170, 10, 75, 15);
		lblLoginPannel.setText("Login Pannel");

		Button btnLogin = new Button(shlLogin, SWT.NONE);
		btnLogin.setBounds(170, 178, 75, 25);
		btnLogin.setText("LOGIN");

		Label lblUsername = new Label(shlLogin, SWT.NONE);
		lblUsername.setBounds(47, 68, 55, 15);
		lblUsername.setText("Username");

		Label lblPassword = new Label(shlLogin, SWT.NONE);
		lblPassword.setBounds(47, 119, 55, 15);
		lblPassword.setText("Password");

		btnLogin.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// DOnt mess with this

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loggedIn = false;

				for (String i : loginInfo.keySet()) {
					if (txtUsername.getText().contains(i) && txtPassword.getText().contains(loginInfo.get(i))) {
						loggedIn = true;
						// break;
					}
				}
				if (loggedIn) {
					System.out.println("Logging In");
					shlLogin.close();
				} else {
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
