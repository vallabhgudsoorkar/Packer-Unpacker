import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

class Front
{
	public static void main(String arg[])
	{
		Window obj = new Window();
	}
}

class Window //implements ActionListener
{
	public Window()
	{
		JFrame f = new JFrame("Login for Packing Activity");
		
		JButton bobj = new JButton("Submit");
		bobj.setBounds(100,200,140,40);
		// --------------------
		JLabel lobj1 = new JLabel("Enter Folder name");
		lobj1.setBounds(10,10,100,100);

		JTextField tf1 = new JTextField();
		// (x cordinate, y cordinate, widtyh, height)
		tf1.setBounds(100,50,130,30);
		// ------------------------------------
		JLabel lobj2 = new JLabel("Enter File name");
		lobj2.setBounds(10,110,100,100);

		JTextField tf2 = new JTextField();
		// (x cordinate, y cordinate, widtyh, height)
		tf2.setBounds(100,120,130,30);

		f.add(lobj1);
		f.add(bobj);
		f.add(tf1);
		f.add(lobj2);
		f.add(tf2);

		f.setSize(300,300);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bobj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eobj){
				Packer pobj = new Packer(tf1.getText(), tf2.getText());
				f.setVisible(false);
				NewWindow o = new NewWindow();
			}
		});
	}
}

class NewWindow
{
	public NewWindow()
	{
		
		JFrame f = new JFrame("Login for Unpacking Activity");
		
		JButton bobj = new JButton("Submit");
		bobj.setBounds(100,200,140,40);
		// --------------------
		JLabel lobj1 = new JLabel("Enter File name");
		lobj1.setBounds(10,10,100,100);

		JTextField tf1 = new JTextField();
		// (x cordinate, y cordinate, widtyh, height)
		tf1.setBounds(100,50,130,30);
		

		f.add(lobj1);
		f.add(bobj);
		f.add(tf1);;

		f.setSize(300,300);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bobj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eobj){
				Unpacker uobj = new Unpacker(tf1.getText());
				f.setVisible(false);
				Window obj = new Window();
			}
		});
	}
}

class Packer
{
	// Object for file writing
	public FileOutputStream outstream = null;

	// parametrised constructor
	public Packer(String FolderName, String FileName)
	{
		try
		{
			// Create new file for packing
			File outfile = new File(FileName);
			outstream = new FileOutputStream(FileName);

			// Set the current working directory for folder traversal
			// System.setProperty("user.dir",FolderName);
			
			TravelDirectory(FolderName);
		}
		catch(Exception obj)
		{
			System.out.println(obj);
		}
	}

	public void TravelDirectory(String path)
	{
		File directoryPath = new File(path);
		int counter = 0;
		// Get all file names from directory
		File arr[] = directoryPath.listFiles();

		System.out.println("-------------------------------");
		for(File filename : arr)
		{
			//System.out.println(filename.getAbsolutePath());
			
			if(filename.getName().endsWith(".txt"))
			{
				counter++;
				System.out.println("Packed file : "+filename.getName());
				PackFile(filename.getAbsolutePath());	
			}		
		}
		System.out.println("-------------------------------");
		System.out.println("Succesfully packed files : "+counter);
		System.out.println("-------------------------------");
	}

	public void PackFile(String FilePath)
	{
//		System.out.println("File name received "+ FilePath);
		byte Header[] = new byte[100];
		byte Buffer[] = new byte[1024];
		int length = 0;

		FileInputStream istream = null;

		File fobj = new File(FilePath);

		String temp = FilePath+" "+fobj.length();
		
			// Create header of 100 bytes
		for(int i = temp.length(); i< 100; i++)
		{
			temp = temp + " ";
		}	

		Header = temp.getBytes();
		try
		{
			// open the file for reading
			istream = new FileInputStream(FilePath);

			outstream.write(Header,0,Header.length);
			
			while((length = istream.read(Buffer)) > 0)
			{
				outstream.write(Buffer,0,length);
			}

			istream.close();
		}
		catch(Exception obj)
		{}
		// System.out.println("Header : "+temp.length());

		// Packing logic
	}
}


class Unpacker
{	
	public FileOutputStream outstream = null;

	public Unpacker(String src)
	{
		unpackFile(src);
	}

	public void unpackFile(String FilePath)
	{
		try
		{
			FileInputStream instream = new FileInputStream(FilePath);
			
			byte Header[] = new byte[100];
			int length = 0;
			int counter = 0;

			while((length = instream.read(Header,0,100)) > 0)
			{
				String str = new String(Header);
				
				// c:/asdas/asdasd/asdas/demo.txt 45
				String ext = str.substring(str.lastIndexOf("\\"));

				ext = ext.substring(1);

				String words[] = ext.split("\\s");
				String name = words[0];
				int size = Integer.parseInt(words[1]);

				byte arr[] = new byte[size];
				instream.read(arr,0,size);
				
				System.out.println("New File gets created as :"+name);
				// New file gets created
				FileOutputStream fout = new FileOutputStream(name);
				// Write the data into newnly created file
				fout.write(arr,0,size);

				counter++;
			}

			System.out.println("Sucessfully unpacked files : "+counter);
		}
		catch(Exception obj)
		{}
	}
}
