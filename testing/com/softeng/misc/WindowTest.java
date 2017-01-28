package com.softeng.misc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.softeng.window.MainWindow;
import com.softeng.window.ManagerWindow;

public class WindowTest {



	@Test
	public void testManagerWindow() {
		ManagerWindow window = new ManagerWindow();
		window.event(); 
	}  
	@Test
	public void testMainWindow() {
		MainWindow window = new MainWindow();
		window.Events(); 
	}



}
