package swingsedemo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import swingsedemo.excel.Book;
import swingsedemo.excel.ServiceExcelLoad;
import swingsedemo.excel.ServiceExcelSave;
import swingsedemo.txt.ServiceTxt;
import swingsedemo.xml.ServiceXmlRead;
import swingsedemo.xml.ServiceXmlSave;

public class SwingMain extends JFrame implements ActionListener {

	private JButton testExcel;
	private JButton saveExcel;
	private JButton saveXml;
	private JButton readXml;
	private JPanel przyciski;
	private JPanel dolny;
	private JProgressBar progres;
	private JLabel label;

	public SwingMain() {
		progres = new JProgressBar();
		label = new JLabel();
		label.setText("Gotowy");
		dolny = new JPanel();
		dolny.setLayout(new BorderLayout());
		dolny.add(progres, "North");
		dolny.add(label, "South");

		testExcel = new JButton("Wczytaj Excel");
		testExcel.setActionCommand("excelRead");
		testExcel.addActionListener(this);
		saveExcel = new JButton("Zapisz w Excelu");
		saveExcel.setActionCommand("saveExcel");
		saveExcel.addActionListener(this);
		saveXml = new JButton("Zapisz Xml");
		saveXml.setActionCommand("xmlSave");
		saveXml.addActionListener(this);
		readXml = new JButton("Wczytaj Xml");
		readXml.setActionCommand("readXml");
		readXml.addActionListener(this);

		przyciski = new JPanel();
		przyciski.setLayout(new FlowLayout());
		przyciski.add(testExcel);
		przyciski.add(saveExcel);
		przyciski.add(saveXml);
		przyciski.add(readXml);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(przyciski, "Center");
		getContentPane().add(dolny, "South");

		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Testowa aplikacja");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SwingMain();

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// czytanie pliku excelowego
		if (e.getActionCommand().equals("excelRead")) {
			buttonBoolean(false);
			SwingWorker worker = new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					progres.setIndeterminate(true);
					label.setText("Obrabiam plik excela");
					String excelFilePath = "c:\\Users\\mc\\Documents\\projekty\\swingsedemo\\Books.xlsx";
					ServiceExcelLoad reader = new ServiceExcelLoad();
					List<Book> listBooks = null;
					try {
						listBooks = reader.readBooksFromExcelFile(excelFilePath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(listBooks.toString());
					return null;
				}

				@Override
				public void done() {
					progres.setVisible(false);
					buttonBoolean(true);
					label.setText("Gotowy");
					JOptionPane.showMessageDialog(getContentPane(), "Excel sukces");
				}
			};
			worker.execute();
		}

		// tworzenie pliku excelowego
		if (e.getActionCommand().equals("saveExcel")) {
			buttonBoolean(false);
			SwingWorker worker = new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					progres.setIndeterminate(true);
					label.setText("Zapisuję plik xls");
					ServiceExcelSave excelWriter = new ServiceExcelSave();
					List<Book> listBook = excelWriter.getListBook();
					String excelFilePath = "NiceJavaBooks.xls";
					excelWriter.writeExcel(listBook, excelFilePath);
					return null;
				}

				@Override
				public void done() {
					progres.setVisible(false);
					buttonBoolean(true);
					label.setText("Gotowy");
					JOptionPane.showMessageDialog(getContentPane(), "Excel sukces");
				}
			};
			worker.execute();
		}

		if (e.getActionCommand().equals("xmlSave")) {
			buttonBoolean(false);
			SwingWorker worker = new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					progres.setIndeterminate(true);
					label.setText("Obrabiam plik xml");
					ServiceXmlSave serviceXml = new ServiceXmlSave();
					serviceXml.generateXmlFile(serviceXml.books());
					//zapisanie do pliku
					ServiceTxt sTxt = new ServiceTxt();
					sTxt.saveBookToFile(serviceXml.books());
					//odczytanie pliku
					sTxt.loadBookToFile();
					return null;
				}

				@Override
				public void done() {
					progres.setVisible(false);
					buttonBoolean(true);
					label.setText("Gotowy");
					JOptionPane.showMessageDialog(getContentPane(), "Excel sukces");
				}
			};
			worker.execute();
		}

		if (e.getActionCommand().equals("readXml")) {
			buttonBoolean(false);
			SwingWorker worker = new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					progres.setIndeterminate(true);
					label.setText("Obrabiam plik xml");
					File file = new File("list_books.xml");
					ServiceXmlRead serviceXmlRead = new ServiceXmlRead();
					serviceXmlRead.readXml(file);
					return null;
				}

				@Override
				public void done() {
					progres.setVisible(false);
					buttonBoolean(true);
					label.setText("Gotowy");
					JOptionPane.showMessageDialog(getContentPane(), "Excel sukces");
				}
			};
			worker.execute();
		}
	}

	public void buttonBoolean(boolean a) {
		testExcel.setEnabled(a);
		saveXml.setEnabled(a);
	}

}