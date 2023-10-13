import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit, themes; // Jmenu :class for creating the menu items
    // File menu items
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut,copy, paste, selectAll, close, darkTheme, lightTheme, defaultTheme;

    JTextArea textArea;

    TextEditor(){

        //Initialize a frame
        frame = new JFrame();
        // Initialize menuBar frame
        menuBar = new JMenuBar();

        //Initialize text Area
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");
        themes = new JMenu("Themes"); //*

        // Initialize fle menu items
        newFile = new JMenuItem("New window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add action Listener to the file menu items
        newFile.addActionListener(this); // this KW refers to the object of text editor class
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize main menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding themes //**
        darkTheme = new JMenuItem(("Dark Theme"));
        lightTheme = new JMenuItem("Light Theme");
        defaultTheme = new JMenuItem("Default Theme");
        themes.add(darkTheme);
        themes.add(lightTheme);
        themes.add(defaultTheme); //**

        // Add action Listener to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        darkTheme.addActionListener(this);//*
        lightTheme.addActionListener(this);//*
        defaultTheme.addActionListener(this);//*

        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        // Add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(themes); //*

        // set menuBar to frame
        frame.setJMenuBar(menuBar);

        //Create Content Panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));

        //Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        //Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollPane);
        // Add panel to frame
        frame.add(panel);
        // set dimensions of frame
        frame.setBounds(100, 100, 1000, 500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
//        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // will close the application
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0); // zero means we just close the application
        }
        if(actionEvent.getSource() == openFile){
            // open a file chooser
            JFileChooser fileChooser = new JFileChooser("C");
            int chosenFile = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chosenFile == JFileChooser.APPROVE_OPTION){
                // Getting the selected file
                File file = fileChooser.getSelectedFile();
                // Getting the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // Initialize Buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //REad the content of the file line by lien
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate +"\n";
                    }
                    // Set output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            // Get
            JFileChooser fileChooser = new JFileChooser("C");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if we clicked on save botton
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+",txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    //write contents of text to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        // Adding color for themes //***
        if(actionEvent.getSource() == darkTheme){
            textArea.setBackground(Color.DARK_GRAY);
            textArea.setForeground(Color.WHITE);
        }
        if(actionEvent.getSource() == lightTheme){
            textArea.setBackground(new Color(107, 169,255));
            textArea.setForeground(Color.black);
        }
        if(actionEvent.getSource() == defaultTheme){
            textArea.setBackground(new Color(255, 255, 255));
            textArea.setForeground(Color.black);
        } ///***
        if(actionEvent.getSource() == newFile){
            TextEditor newtextEditor = new TextEditor();
        }

    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

    }
}