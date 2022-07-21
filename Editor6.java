							             /*.........................Editor............................*/


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
class Editor6 extends WindowAdapter implements ActionListener, ItemListener {
  Frame f, inner_frame,inner_frame2;
  MenuBar mb;
  Menu m1, m2, m3;
  Frame f4; 
  MenuItem nw, opn, ext, sve, rep, ct, cpy, pst, save, findnext;
  CheckboxMenuItem bld, itlc;
  TextArea t;
  TextField t2, t3;
  FileDialog fd;
  FileOutputStream fos;
  FileInputStream fis;
  String file_name ="";											//here
  String data = "";
  int ch, style = 0;
  int index = 0;
  Button b1, b2, b3, b4, yes, no, y, n, yy, nn, yes1,b5,b6,b7;
  Panel p,p1;
  Label l,l1;
  String regex, input, dum = "";
  Pattern pat;
  Matcher mat;
  int x = 0;
  int m = 0;
  int mn = 0;
  int op=0;
  int last=0;

  public Editor6() {
    
	f = new Frame();
    f.setSize(1700, 900);
    t = new TextArea();
    f.add(t);
    f.addWindowListener(this);
   

    mb = new MenuBar();
    m1 = new Menu("File");
    m2 = new Menu("Edit");
    m3 = new Menu("Others");

    l = new Label("Do you want to save your file..?");

    nw = new MenuItem("New");
    nw.addActionListener(this);
    opn = new MenuItem("Open");
    opn.addActionListener(this);
    sve = new MenuItem("Save as");
    sve.addActionListener(this);
    ext = new MenuItem("Exit");
    ext.addActionListener(this);
    rep = new MenuItem("Replace");
    rep.addActionListener(this);
    ct = new MenuItem("Cut");
    cpy = new MenuItem("Copy");
    pst = new MenuItem("Paste");
    bld = new CheckboxMenuItem("Bold");
    bld.addItemListener(this);
    itlc = new CheckboxMenuItem("Italic");
    itlc.addItemListener(this);
    save = new MenuItem("Save");
    save.addActionListener(this);
    findnext = new MenuItem("find next");
    findnext.addActionListener(this);
    //itlc.setState(true);

    mb.add(m1);
    mb.add(m2);
    mb.add(m3);
    m1.add(nw);
    m1.add(opn);
    m1.add(save);
    m1.add(sve);
    m1.addSeparator();
    m1.add(ext);
    m2.add(bld);
    m2.add(itlc);
    m3.add(ct);
    m3.add(cpy);
    m3.add(pst);
    m3.add(findnext);
    m3.add(rep);
    f.setMenuBar(mb);
    f.setVisible(true);

    inner_frame = new Frame();
    inner_frame.setSize(300, 100);
    
   
  }
 

  public void itemStateChanged(ItemEvent e) {
    if ((e.getSource() == bld) && (bld.getState() == true) && (itlc.getState() == false)) {
      Font f = new Font("Arial", Font.BOLD, 14);
      t.setFont(f);
      /*else
      {
      style--;
      Font f=new Font("Lucida",Font.REGULAR,10);
      t.setFont(f);
      }*/
    }
    if ((e.getSource() == itlc) && (itlc.getState() == true) && (bld.getState() == false))

    {
      Font f = new Font("Arial", Font.ITALIC, 14);
      t.setFont(f);
    }

    if ((e.getSource() == bld) && (bld.getState() == true) && (itlc.getState() == true)) {
      Font f = new Font("Arial", Font.BOLD + Font.ITALIC, 14);
      t.setFont(f);
    }
    if ((e.getSource() == itlc) && (bld.getState() == true) && (itlc.getState() == true)) {
      Font f = new Font("Arial", Font.BOLD + Font.ITALIC, 14);
      t.setFont(f);
    }

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == yes1) {
      fd = new FileDialog(f, "SAVE", FileDialog.SAVE);
      fd.setVisible(true);
      file_name = fd.getFile();
      try {
        fos = new FileOutputStream(file_name);
        data = t.getText();
        for (int i = 0; i < data.length(); i++) {
          ch = data.charAt(i);
          fos.write(ch);
        }
        fos.close();
      } catch (IOException io) {
        System.out.print(io.getMessage());
	   
      }
	
	System.exit(0);
    }
   

    if (e.getSource() == ext) {									     //exit
      if (!data.equals(t.getText())) {
        inner_frame = new Frame();
        inner_frame.setSize(400, 100);
	  inner_frame.setAlwaysOnTop(true);
        Panel p = new Panel();
        p.add(l);
        yes = new Button("Yes");
        yes.addActionListener(this);
        no = new Button("No");
        no.addActionListener(this);
        p.add(yes);
        p.add(no);
        inner_frame.add(p);
        inner_frame.setVisible(true);
        inner_frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            Window w = e.getWindow();
            w.setVisible(false);
            w.dispose();
            f.setVisible(true);
            f.setEnabled(true);
            data = "";
          }
        });

      } else {
        System.exit(1);
      }
    }
    if (e.getSource() == save) 
	{										//save
      	if (last==0) 
		{
      		try{ 
				 fd = new FileDialog(f, "SAVE", FileDialog.SAVE);
      			  fd.setVisible(true);
        			file_name = fd.getFile();
				
				
      			  if(!file_name.equals(""))
				{
			    try {
          				fos = new FileOutputStream(file_name);
					data=t.getText();
      				for (int i = 0; i < data.length(); i++) 
					{
      			      ch = data.charAt(i);
            			fos.write(ch);
          				}		
          				fos.close();
	    				op=1;
					last=1;
				}
				
				catch (IOException io) 
					{
          					System.out.print(io.getMessage());
				        }
				}
				else
				{
					last=0;
				}
		
		
        		} 
	
			catch(NullPointerException n)
			{}

      	} 
		else
		 {
        		try {
          			fos = new FileOutputStream(file_name);
         			 data = t.getText();
         			 for (int i = 0; i < data.length(); i++) {
         			   ch = data.charAt(i);
           			 fos.write(ch);
         			 }
         			 fos.close();
          			data = t.getText();
       	 } 
		catch (IOException io) {
          System.out.print(io.getMessage());
        }
		catch(NullPointerException n1)
		{}
      }
    }
    if (e.getSource() == no) {
      System.exit(1);
    }
    if (e.getSource() == yes) {
      inner_frame.setVisible(false);
      if (file_name.equals("")) {
        fd = new FileDialog(f, "SAVE", FileDialog.SAVE);
        fd.setVisible(true);
        file_name = fd.getFile();
        try {
          fos = new FileOutputStream(file_name);
          data = t.getText();
          for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);
            fos.write(ch);
          }
          fos.close();
          System.exit(1);
		data=t.getText();
        } catch (IOException io) {
          System.out.print(io.getMessage());
        }
		catch(NullPointerException n1)
		{}
      } else {
        try {
          data = t.getText();
          fos = new FileOutputStream(file_name);
          int i = 0;
          while (i != data.length()) {
            ch = data.charAt(i);
            fos.write(ch);
            i++;
          }
          fos.close();
          inner_frame.setVisible(false);
          System.exit(1);
        } catch (IOException E) {
          System.out.print(E.getMessage());}
		catch(NullPointerException n2)
		{}
        
      }
    }
	
     if (e.getActionCommand().equals("find next")){	
	inner_frame2 = new Frame();
      inner_frame2.setSize(300, 100);							//find next
      
      p = new Panel();
      inner_frame2.add(p);
      t2 = new TextField("Find next");
      b4 = new Button("Find next");
      p.add(t2);
      p.add(b4);
      b4.addActionListener(this);
	inner_frame2.setVisible(true);
	inner_frame2.setAlwaysOnTop(true);

      inner_frame2.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          Window w = e.getWindow();
          w.setVisible(false);
          w.dispose();
		
          f.setVisible(true);
          f.setEnabled(true);
         // data = "";
        }
      });
    }
    if (e.getSource() == b4) {
    try{  regex = t2.getText();
      input = t.getText();
	
      pat = Pattern.compile("\\r");
	mat = pat.matcher(input);
	input=mat.replaceAll("");
      pat = Pattern.compile(regex);
    	mat=pat.matcher(input);
	
      if (mat.find(index)) {
        t.select(mat.start() , mat.end());
        index = t.getSelectionEnd();
        t.requestFocus();
	}
	}
	catch(IndexOutOfBoundsException | IllegalStateException I)
	{
	
	}
      	
    }

	
    if (e.getSource() == rep) {
      inner_frame = new Frame();
      inner_frame.setSize(400, 100);
      inner_frame.setVisible(true);
	inner_frame.setAlwaysOnTop(true);
      Panel p = new Panel();
      inner_frame.add(p);
      b1 = new Button("replace");
      b2 = new Button("replace all");
      b3 = new Button("find");
      t2 = new TextField("find");
      t3 = new TextField("replace");
      b1.addActionListener(this);
      b2.addActionListener(this);
      b3.addActionListener(this);
      p.add(t2);
      p.add(t3);
      p.add(b1);
      p.add(b2);
      p.add(b3);
      inner_frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          Window w = e.getWindow();
          w.setVisible(false);
          w.dispose();
          f.setVisible(true);
          f.setEnabled(true);

        }
      });

    }
    if (e.getSource() == b3) {										//find
    
	regex = t2.getText();
      input = t.getText();
	
      pat = Pattern.compile("\\r");
	mat = pat.matcher(input);
	input=mat.replaceAll("");
      pat = Pattern.compile(regex);
    	mat=pat.matcher(input);
	 try{ 
      if (mat.find(index)) {
    t.select(mat.start() , mat.end());
        index = t.getSelectionEnd();
	  t.requestFocus();
	  
        }
		}
		catch(IllegalStateException | IndexOutOfBoundsException I)
		{
			//System.out.print("YOman");
		}
		
		
    }
    if (e.getSource() == b2) {										//replace all
      regex = t2.getText();
      input = t.getText();
      Pattern patt = Pattern.compile(regex);
      Matcher match = patt.matcher(input);
	try{
      while (match.find()) {
        t.setText(match.replaceAll(t3.getText()));
        //t.setText(input);
      }
	}
	catch(IllegalStateException | IndexOutOfBoundsException I)
	{
		//System.out.print("Yoman");
	}
    }
    if (e.getSource() == b1) {										//replace
     try{ regex = t2.getText();
      input = t.getText();
	
      pat = Pattern.compile("\\r");
	mat = pat.matcher(input);
	input=mat.replaceAll("");
      pat = Pattern.compile(regex);
    	mat=pat.matcher(input);
	
     
      Pattern patt = Pattern.compile(regex);
      Matcher match = patt.matcher(input);
      if (t.getSelectionStart() == t.getSelectionEnd()) {
        t.setText(match.replaceFirst(t3.getText()));
      } else {
        match.find(t.getSelectionStart());
        StringBuffer xyz = new StringBuffer(input);
        xyz.replace(match.start(), match.end(), t3.getText());
        t.setText(xyz.toString());
	   t.select(0,0);
        //t.setText(input);
		
      }
	}
	catch(IllegalStateException | IndexOutOfBoundsException I)
	{
		//System.out.print("Yoman");
	}
    }
	
    if (e.getSource() == sve) {	
	try{										//save as
      fd = new FileDialog(f, "SAVE", FileDialog.SAVE);
      fd.setVisible(true);
      file_name = fd.getFile();
 	if(!file_name.equals(""))
        {
	  	fos = new FileOutputStream(file_name);
        	data = t.getText();
       	 for (int i = 0; i < data.length(); i++) 
		{
        	  ch = data.charAt(i);
        	  fos.write(ch);
		 fos.close();
	  	op=1;
	 	 last=1;
        	}
        }
	  else
	  {
		last=0;
	  }
	 
      } catch (IOException io) {
        System.out.print(io.getMessage());
      }
	catch(NullPointerException n)
	{
	}
    }

    if ((e.getActionCommand().equals("New"))) {							//new
	
      if (!data.equals(t.getText())) {
	  data = "";
        inner_frame = new Frame();
        inner_frame.setSize(400, 100);
        Panel p = new Panel();
        p.add(l);
        y = new Button("Yes");
        y.addActionListener(this);
        n = new Button("No");
        n.addActionListener(this);
        p.add(y);
        p.add(n);
        inner_frame.add(p);
        inner_frame.setVisible(true);
        inner_frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
		try{
            Window w = e.getWindow();
            w.setVisible(false);
            w.dispose();
            f.setVisible(true);
            f.setEnabled(true);
		last=0;																	//here
             }
		catch(NullPointerException N)
		{}
          }
        });
      } else {
		data="";
        t.setText("");
		last=0;																	//here
      }
    }
    if (e.getSource() == y) {
    try{
	 inner_frame.setVisible(false);
      fd = new FileDialog(f, "SAVE", FileDialog.SAVE);
      fd.setVisible(true);
      file_name = fd.getFile();
      data = t.getText();
     	  
	  try {
        fos = new FileOutputStream(file_name);
        for (int i = 0; i < data.length(); i++) {
          ch = data.charAt(i);
          fos.write(ch);
		last=0;
        }
        fos.close();
        t.setText("");
	  data=t.getText();
      } catch (IOException io) {
        System.out.print(io.getMessage());
      }
	}
	catch(NullPointerException N)
	{}
    }
    if (e.getSource() == n) {
      inner_frame.setVisible(false);
      t.setText("");
	last=0;
    }

   /* if ((e.getActionCommand().equals("Open"))) {
      if ((t.getText()).equals("")) {
        try {
          fd = new FileDialog(f, "OPEN", FileDialog.LOAD);
          fd.setVisible(true);
          file_name = fd.getFile();
          fis = new FileInputStream(file_name);
          t.setText("");
          while ((ch = fis.read()) != -1) {
            dum = dum + "" + (char) ch;

          }
          t.setText(t.getText() + "" + dum);
          fis.close();
		
          dum = "";
        } catch (IOException io) {
          System.out.print(io.getMessage());
        }
      }

      if (!data.equals(t.getText())) {
        inner_frame = new Frame();
        inner_frame.setSize(400, 100);
        Panel p = new Panel();
        p.add(l);
        yy = new Button("Yes");
        yy.addActionListener(this);
        nn = new Button("No");
        nn.addActionListener(this);
        p.add(yy);
        p.add(nn);
        inner_frame.add(p);
        inner_frame.setVisible(true);
        data = t.getText();
        inner_frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            Window w = e.getWindow();
            w.setVisible(false);
            w.dispose();
            f.setVisible(true);
            f.setEnabled(true);
            data = "";
          }
        });
      } if (data.equals(t.getText()))
	  {
        try {
          fd = new FileDialog(f, "OPEN", FileDialog.LOAD);
          fd.setVisible(true);
          file_name = fd.getFile();
          fis = new FileInputStream(file_name);
          t.setText("");
          while ((ch = fis.read()) != -1) {
            dum = dum + "" + (char) ch;

          }
          t.setText(t.getText() + dum);
          fis.close();
          data = t.getText();
        } catch (IOException io) {
          System.out.print(io.getMessage());
        }
        dum = "";
      }
	
    }
    if (e.getSource() == yy) {

      try {
        inner_frame.setVisible(false);
        fd = new FileDialog(f, "SAVE", FileDialog.SAVE);
        fd.setVisible(true);
        file_name = fd.getFile();
        fos = new FileOutputStream(file_name);
        for (int i = 0; i < data.length(); i++) {
          ch = data.charAt(i);
          fos.write(ch);
        }
        fos.close();
        fd = new FileDialog(f, "OPEN", FileDialog.LOAD);
        fd.setVisible(true);
        file_name = fd.getFile();
        fis = new FileInputStream(file_name);
        t.setText("");
        while ((ch = fis.read()) != -1) {
          dum = dum + "" + (char) ch;

        }
        t.setText(t.getText() + "" + dum);
        fis.close();
        data = t.getText();
        dum = "";
      } catch (IOException io) {
        System.out.print(io.getMessage());
      }
    }
    if (e.getSource() == nn) {
      inner_frame.setVisible(false);
      try {
        fd = new FileDialog(f, "OPEN", FileDialog.LOAD);
        fd.setVisible(true);
        file_name = fd.getFile();
        fis = new FileInputStream(file_name);
        t.setText("");
        while ((ch = fis.read()) != -1) {
          dum = dum + "" + (char) ch;

        }
        t.setText(t.getText() + "" + dum);
        fis.close();
        dum = "";
      } catch (IOException io) {
        System.out.print(io.getMessage());
      }
    }
    //data="";

  }*/

//if(e.getActionCommand.equals("Open"))
if ((e.getActionCommand().equals("Open")))						//open
{

      try
{
if(data.equals(t.getText()))
{

fd=new FileDialog(f,"OPEN",FileDialog.LOAD);
fd.setVisible(true);
file_name=fd.getFile();
fis=new FileInputStream(file_name);
//bis=new BufferedInputStream(bis);
String s="";
t.setText("");
while((ch=fis.read())!=-1)
s+=(char)ch;
t.setText(s);
fis.close();
//bis.close();
//f1.setTitle(file_name);
data=t.getText();
}
else
{
	f4=new Frame("Notepad"); f4.setSize(250,150);// f.setEnabled(false);
	f4.setLocationRelativeTo(null);
	l1=new Label("Do you want to save changes to untitled?");  
	l1.setFont(new Font("Arial",Font.PLAIN,12));
	l1.setForeground(Color.BLUE);
	p1=new Panel();
	b5=new Button("Save");
	b6=new Button("Don't Save");
	b7=new Button("Cancel");
	b5.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{ f4.setVisible(false);
		f.setVisible(true);
		if(op==0)
		{													//working
			try{
				fd=new FileDialog(f4,"SAVE",FileDialog.SAVE);
				fd.setVisible(true);
				file_name=fd.getFile();
				if(!file_name.equals(""))
				{
					data=t.getText();
					fos=new FileOutputStream(file_name);
					for(int i=0;i<data.length();i++)
					{
						ch=data.charAt(i);
						fos.write(ch);
					}
					fos.close();
					t.setText("");
					t.requestFocus();
					//f4.setVisible(false);
					last=1;
				}
				else
				{
					last=0;
				}
				fd=new FileDialog(f,"OPEN",FileDialog.LOAD);
				fd.setVisible(true);
				file_name=fd.getFile();
				fis=new FileInputStream(file_name);
//bis=new BufferedInputStream(fis);
				String s="";
//ta.setText("");
				while((ch=fis.read())!=-1)
				s+=(char)ch;
				t.setText(s);
				fis.close();
//bis.close();
				data=t.getText();
				f.setTitle(file_name);
//f4.setVisible(false);
//f1.setVisible(true);
//f.setEnabled(true);
 				op=1;
			}
			catch(IOException io)
			{
				System.out.print(io.getMessage());
			}
			catch(NullPointerException e1)
			{}
	}
	else 
	{
	try {
          fos = new FileOutputStream(file_name);
          data = t.getText();
          for (int i = 0; i < data.length(); i++) {
            ch = data.charAt(i);
            fos.write(ch);
          }
          fos.close();
          data = t.getText();
	   
        } catch (IOException io) {
          System.out.print(io.getMessage());
        }
	   try{
	     fd=new FileDialog(f,"OPEN",FileDialog.LOAD);
	     fd.setVisible(true);
		file_name=fd.getFile();
		fis=new FileInputStream(file_name);
		//bis=new BufferedInputStream(bis);
		String s="";
		t.setText("");
		while((ch=fis.read())!=-1)
		s+=(char)ch;
		t.setText(s);
		fis.close();
		//bis.close();
		//f1.setTitle(file_name);
		data=t.getText();
		}
		catch(FileNotFoundException ex1)
		{
		}
		catch(IOException ex2)
		{
		}
		catch(NullPointerException ex3)
		{}
}

}
});
b6.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{ f4.setVisible(false);
f.setVisible(true);
try{
fd=new FileDialog(f,"OPEN",FileDialog.LOAD);
fd.setVisible(true);
file_name=fd.getFile();
fis=new FileInputStream(file_name);
//bis=new BufferedInputStream(fis);
String s="";
t.setText("");
while((ch=fis.read())!=-1)
s+=(char)ch;
t.setText(s);
fis.close();
//bis.close();
data=t.getText();
f.setTitle(file_name);
//f4.setVisible(false);
f.setVisible(true);
f.setEnabled(true);
}
catch(IOException io)
{
System.out.print(io.getMessage());
}
catch(NullPointerException n)
{
}
}

});
b7.addActionListener(this);
p1.add(b5);
p1.add(b6);
p1.add(b7);
f4.add(p1,"South");
f4.add(l1,"West");
//f4.setVisible(true);
f4.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
Window w=e.getWindow();
w.setVisible(false);
w.dispose();
f.setVisible(true);
f.setEnabled(true);
}
}); f4.setVisible(true);
}
}
catch(IOException io)
{
System.out.print(io.getMessage());
}
catch(NullPointerException n2)
{
}
}
}
  public void windowClosing(WindowEvent we) {							//window closing

   try
   {
	 if (!data.equals(t.getText())) {
      inner_frame = new Frame();
      inner_frame.setSize(400, 100);
      Panel p = new Panel();
      p.add(l);
      yes1 = new Button("Yes");
      yes1.addActionListener(this);
      no = new Button("No");
      no.addActionListener(this);
      p.add(yes1);
      p.add(no);
      inner_frame.add(p);
      inner_frame.setVisible(true);
      inner_frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          Window w = e.getWindow();
          w.setVisible(false);
          w.dispose();
          f.setVisible(true);
          f.setEnabled(true);
        }
      });

    } else {
      Window w = we.getWindow();
      w.setVisible(false);
      w.dispose();
      System.exit(1);
    }
   }
   catch(NullPointerException n)
   {}
}

  
  public static void main(String args[]) {
    Editor6 e = new Editor6();

  
}


}