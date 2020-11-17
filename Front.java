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
        JFrame f = new JFrame("Login Page ");
       
        JButton bobj = new JButton("Submit");
        bobj.setBounds(100,200,140,40);

        JLabel lobj1 = new JLabel("Entre Folder Name");
        lobj1.setBounds(10,10,100,100);

        JTextField tf1 = new JTextField();
        tf1.setBounds(100,50,130,30);

        JLabel lobj2 = new JLabel("Entre File Name");
        lobj2.setBounds(10,110,100,100);

        JTextField tf2 = new JTextField();
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

        bobj.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent eobj){
                System.out.println("Folder Name : "+ tf1.getText());
                System.out.println("File  Name : "+ tf2.getText());
                Packer pobj = new Packer(tf1.getText(), tf2.getText());
                 f.setVisible(true);
            }
        });
    }
}

class NewWindow
{
    public NewWindow()
    {
        JFrame fobj = new JFrame("New frame");
        fobj.setSize(300,300);
        fobj.setVisible(true);
    }
}
class Packer
{
    public  FileOutputStream outstream = null;
    public Packer(String FolderName, String FileName)
    {
        try
       { System.out.println("Inside Packer Constructor");
        File outfile = new File(FileName);
        outstream = new FileOutputStream(FileName);

        //System.setProperty("user.dir",FolderName);
        TravelDirectory(FolderName);
       }
       catch(Exception obj)
       {
           System.out.println(obj);
       }
    }
    public void TravelDirectory(String path)
    {
        File directorypath = new File(path);
        int count = 0;

         //Get All File   
        File arr[] = directorypath.listFiles();
        for(File filename : arr )
        {
            //System.out.println(filename.getName());
            //System.out.println(filename.getAbsolutePath());
            if(filename.getName().endsWith(".txt"))
            {
                count++;
                System.out.println("Packed File ");
                packFile(filename.getAbsolutePath());
            }
            
        }

    }
    public void packFile(String FilePath)
    {
       // System.out.println("File Name recieved "+ FilePath);
            // Packing Logic
            byte Header [] = new byte[100];
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

			outstream.write(Header,0,Header.length); //used to write Heder
			while((length = istream.read(Buffer)) > 0) // used to write data of the file .
			{
				outstream.write(Buffer,0,length);
			}

			istream.close();
		}
		catch(Exception obj)
		{

        }
		// System.out.println("Header : "+temp.length());

		// Packing logic


    }
}