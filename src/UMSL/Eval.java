/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//slider got to 
package UMSL;
    import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.*;
import org.apache.derby.jdbc.*;
import sun.jdbc.odbc.JdbcOdbcDriver;
/**
 *
 * @author cdsyc8
 */
//public class Eval {



public class Eval extends JFrame implements ActionListener, ItemListener, ChangeListener
{
    //DECLARE THE ELEMENTS OR OBJECTS THAT YOU WILL PUT IN YOUR FRAME
    //NOTICE HOW A PANEL IS CREATED FOR EACH ONE THIS WILL MAKE IT EASIER BUILD
    
    public JLabel teamLabel;
    private JComboBox teamComboBox;
    private JPanel teamPanel;
    JTextField comBox;
    
   // JTextField comments............................................................
    
//    private JLabel courseLabel;kjk
//    private JComboBox courseComboBox;
//    private JPanel coursePanel;

    
    private JLabel q1Label;
    
    private JLabel q2Label;
    private JLabel q3Label;
    private JLabel q4Label;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JPanel questionPanel;
    private ButtonGroup questionGroup1;
    private double average;

    private JSlider q1Slider;
    private JSlider q2Slider;
    private JSlider q3Slider;
    private JSlider q4Slider;
  
    
    private JButton submitButton;
    private JButton clearButton;
    private JPanel buttonPanel;
    
    //instance variables to hold our data from the gui object to update the database
    String myteamname;
//    String courseName;
    int q1;
    int q2;
    int q3;
    int q4;
    
    // instance variables used to manipulate database
   private Connection myConnection;
   private Statement myStatement;
   private ResultSet myResultSet;
           
    
   
   //MAIN METHOD: NOTICE WE TAKE IN THE ARGUMENTS THAT ARE
   //PASSED IN AND INSTANTIATE OUR CLASS WITH THEM
    public static void main(String args[])
    {
        // check command-line arguments
      //if ( args.length == 2 )
      //{
         // get command-line arguments
        String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        //String databaseDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
        //String databaseURL = "jdbc:derby://localhost:1527/PureEval";
        String databaseURL = "jdbc:derby://localhost:1527/Eval;create=true;";
        
         
         // create new Eval
         Eval eval = new Eval( databaseDriver, databaseURL );
         eval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
      //}
      //else // invalid command-line arguments
      //{
      //   System.out.println( "Usage: java EVAL needs databaseDriver databaseURL" );
      //}
    }
    
    //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
    //TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
    public Eval(String databaseDriver, String databaseURL)
    {
        // establish connection to database
      try
      {
         // load Sun driver
         //Class.forName( databaseDriver );
         DriverManager.registerDriver(new ClientDriver());
         // connect to database
         myConnection = DriverManager.getConnection( databaseURL );

         // create Statement for executing SQL
         myStatement = myConnection.createStatement();
      }
      catch ( SQLException exception )
      {
         exception.printStackTrace();
      }
      //catch ( ClassNotFoundException exception )
     // {
      //   exception.printStackTrace();
      //}

      createUserInterface(); // set up GUI
      
      

      
    }
    
    
    
    private void createUserInterface()
    {
      // get content pane for attaching GUI components
      Container contentPane = getContentPane();

      // enable explicit positioning of GUI components
      contentPane.setLayout( null );  
        
      // INSTRUCTOR COMBO BOX SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
      // set up Instructor Panel
      teamPanel = new JPanel();
      teamPanel.setBounds(40, 20, 276, 48 );
      teamPanel.setBorder( BorderFactory.createEtchedBorder() );
      teamPanel.setLayout( null );
      contentPane.add( teamPanel );

      // set up Instructor Label
      teamLabel = new JLabel();
      teamLabel.setBounds( 25, 15, 100, 20 );
      teamLabel.setText( "TEAMS:" );
      teamPanel.add( teamLabel );

      // set up accountNumberJComboBox
      teamComboBox = new JComboBox();
      teamComboBox.setBounds( 150, 15, 96, 25 );
      teamComboBox.addItem( "" );
      teamComboBox.setSelectedIndex( 0 );
      teamPanel.add( teamComboBox );
      
      
      
      //RADIO BUTTON SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      // set up Question Panel and Radio Buttons
      questionPanel = new JPanel();
      questionPanel.setBounds( 40, 90, 275, 400 );
      questionPanel.setBorder( BorderFactory.createEtchedBorder() );
      questionPanel.setLayout( null );
      contentPane.add( questionPanel );
      
      // set up question1 Label
      q1Label = new JLabel();
      q1Label.setBounds( 10, 0, 270, 25 );
      q1Label.setText ("Q1: How much do you like pizza" );
      questionPanel.add( q1Label );
      
      q2Label = new JLabel();
      q2Label.setBounds( 10, 100, 270, 25 );
      q2Label.setText ("Q2: How much do you like golf" );
      questionPanel.add( q2Label );
      
      q3Label = new JLabel();
      q3Label.setBounds( 10, 200, 270, 25 );
      q3Label.setText ("Q3: How funny are you" );
      questionPanel.add( q3Label );
      
      q4Label = new JLabel();
      q4Label.setBounds( 10, 300, 270, 25 );
      q4Label.setText ("Q4: How much do you like baseball" );
      questionPanel.add( q4Label );
      
      // set up the radio buttons for question 1
//      rb1 = new JRadioButton( "1", false );
//      rb1.setBounds(20, 30, 40, 40 );
//      rb1.setVisible(true);
//      rb1.addItemListener(this);
//      
//      rb2 = new JRadioButton("2", false);
//      rb2.setBounds(80, 30, 40, 40 );
//      rb2.setVisible(true);
//      rb2.addItemListener(this);
//      
//      rb3 = new JRadioButton( "3", false );
//      rb3.setBounds(140, 30, 40, 40 );
//      rb3.setVisible(true);
//      rb3.addItemListener(this);
      
      q1Slider = new JSlider(JSlider.HORIZONTAL, 1,7,4);
      q1Slider.setBounds(10, 25, 200, 75 );
      q1Slider.setVisible(true);
      //.addChangeListener(this);
        //q1Slider.setMajorTickSpacing(10);
q1Slider.setPaintTicks(true);

//Create the label table

Hashtable labelTable = new Hashtable();
labelTable.put( new Integer( 0 ), new JLabel("C-") );
labelTable.put( new Integer( 1 ), new JLabel("C") );
labelTable.put( new Integer( 2 ), new JLabel("C+") );
labelTable.put( new Integer( 3 ), new JLabel("B-") );
labelTable.put( new Integer( 4 ), new JLabel("B") );
labelTable.put( new Integer( 5 ), new JLabel("B+") );
labelTable.put( new Integer( 6 ), new JLabel("A-") );
labelTable.put( new Integer( 7 ), new JLabel("A") );

//.....and so on
q1Slider.setLabelTable( labelTable );

q1Slider.setPaintLabels(true);
      
      q2Slider = new JSlider(JSlider.HORIZONTAL, 1,7,4);
      q2Slider.setBounds(10, 125, 200, 75 );
      q2Slider.setVisible(true);
      //q2Slider.addChangeListener(this);
      //q1Slider.setMajorTickSpacing(10);
q2Slider.setPaintTicks(true);
q2Slider.setLabelTable( labelTable );
q2Slider.setPaintLabels(true);
      
///...................................do same for the others
      q3Slider = new JSlider(JSlider.HORIZONTAL, 1,7,4);
      q3Slider.setBounds(10, 225, 200, 75 );
      q3Slider.setVisible(true);
      //q3Slider.addChangeListener(this);
    q3Slider.setPaintTicks(true);
    q3Slider.setLabelTable( labelTable );
    q3Slider.setPaintLabels(true);
      
      q4Slider = new JSlider(JSlider.HORIZONTAL, 1,7,4);
      q4Slider.setBounds(10, 325, 200, 75 );
      q4Slider.setVisible(true);
      //q4Slider.addChangeListener(this);
    q4Slider.setPaintTicks(true);
    q4Slider.setLabelTable( labelTable );
    q4Slider.setPaintLabels(true);
      
      
      
      // create logical relationship between JRadioButtons
      questionGroup1 = new ButtonGroup();
//      questionGroup1.add( rb1 );
//      questionGroup1.add( rb2 );
//      questionGroup1.add( rb3 );
      
      // add radio button to the panel
//      questionPanel.add( rb1 );
//      questionPanel.add( rb2 );
//      questionPanel.add( rb3 );
      
      questionPanel.add( q1Slider );
      questionPanel.add( q2Slider );
      questionPanel.add( q3Slider );
      questionPanel.add( q4Slider );
      
      // Average Panel
      
      // Create new panel
      JPanel avgPanel = new JPanel ();
      
      // Make a text box
      final JTextField avgBox = new JTextField ();
      
      avgBox.setBounds(200, 15, 60, 40 );
      avgBox.setVisible(true);
      
      // Make a button
        // Below make a function to calculate average on button press
        // Should also display in text box
      JButton avgButton = new JButton ("Calc Avg");
      avgButton.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed (ActionEvent event)
              {
               q1 = q1Slider.getValue();
               q2 = q2Slider.getValue();
               q3 = q3Slider.getValue();
               q4 = q4Slider.getValue();
               average = (double)((q1 + q2 + q3 + q4) / 4); 
               String avgStr = Double.toString (average);
               avgBox.setText(avgStr);
              
              }
      }); 
      avgButton.setBounds(10, 15, 100, 50 );
      avgButton.setVisible(true);
      // Label your text box
      
      JLabel avgLabel = new JLabel ("Computed average from questions above");
      avgLabel.setBounds(80, 30, 120, 40 );
      avgLabel.setVisible(true);
      
      
      
      // Add label, button, and text box to panel
      
      //avgPanel.add(avgLabel);
      avgPanel.add(avgButton);
      avgPanel.add(avgBox);
      avgPanel.setBounds( 40, 500, 276, 75 );
      avgPanel.setBorder( BorderFactory.createEtchedBorder() );
      avgPanel.setLayout( null );
      contentPane.add( avgPanel );
      
      //comm box...................................................
      
//JPanel comBox = new JPanel ();//.............................................................
////      JLabel 
////      // Make a text box
//      //final JTextField comBox = new JTextField ();
////      
//comBox.setBounds(200, 30, 60, 40 );
//comBox.setVisible(true);
//      avgBox.setVisible(true);  
      


//buttonPanel.setBounds( 40, 200, 276, 75 );
//buttonPanel.setBorder( BorderFactory.createEtchedBorder() );
//buttonPanel.setLayout( null );
//contentPane.add( buttonPanel );
      
// SUBMIT BUTTON SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      buttonPanel = new JPanel();
      buttonPanel.setBounds( 40, 560, 276, 75 );
      buttonPanel.setBorder( BorderFactory.createEtchedBorder() );
      buttonPanel.setLayout( null );
      contentPane.add( buttonPanel );
      
      comBox = new JTextField ();
      
      comBox.setBounds(10, 15, 146, 55 );
      comBox.setVisible(true);
      buttonPanel.add(comBox);
      
      submitButton = new JButton( "SUBMIT" );
      submitButton.setBounds(166, 15, 100, 50);
      submitButton.setVisible(true);
      buttonPanel.add(submitButton);
      submitButton.addActionListener(this);
      
      
      loadTeams();
      
   

      setTitle( "EVAL" );   // set title 
      setSize( 375, 700 ); // set window size
      setVisible( true );  // display window
    }

    //OVERRIDING THIS FUNCTION BECAUSE OUR CLASS IMPLEMENTS THE ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent event)
    {
        myteamname = (String) teamComboBox.getSelectedItem();
//        courseName = (String)courseComboBox.getSelectedItem();
        if(event.getSource().equals(submitButton))
        {
            updateTeam();
        }

    }
    
    public void stateChanged(ChangeEvent event)
    {
        JSlider source = (JSlider)event.getSource();
        if ( source == q1Slider && !(source.getValueIsAdjusting()))
        {
            q1 = q1Slider.getValue();
        }
       
        else if ( source == q2Slider && !(source.getValueIsAdjusting()))
        {
            q2 = q2Slider.getValue();
        }
        else if ( source == q3Slider && !(source.getValueIsAdjusting()))
        {
           q3 = q3Slider.getValue();
        }
         else if ( source == q4Slider && !(source.getValueIsAdjusting()))
         {
             q4 = q4Slider.getValue();
         }
         
        else if (!(source.getValueIsAdjusting()));
        {
            //JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
        }
    }
    @Override
    public void itemStateChanged(ItemEvent event)
    {
        
        if ( event.getSource() == rb1 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q1 = Integer.parseInt(rb1.getText());
        }
        else if (event.getSource() == rb2 && event.getStateChange() == ItemEvent.SELECTED)
        {
            q2 = Integer.parseInt(rb2.getText());
        }
        else if (event.getSource() == rb3 && event.getStateChange() == ItemEvent.SELECTED) 
        {
           q3 = Integer.parseInt(rb3.getText());
        }
        else if( event.getSource() == rb1 && event.getStateChange() == ItemEvent.DESELECTED)
        {
            //JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
        }
    }
     private void updateTeam()
   {
      // update balance in database
      try
      {
        
          //q2 = 200;
          String sql = "UPDATE APP.TEAM SET q1 = " + q1 + "," + "q2 = " + q2 + "," + "q3 = " + q3 + "," + "q4 = " + q4 + "," + "avgscore = " + average + ", comments = " + "'" + comBox.getText() + "'" + " WHERE " + 
                       "TEAMNAME = " + "'" + myteamname + "'";
          
//          String sql2 =  "UPDATE APP.TEAMEVAL" + " SET q2 = " + q2 + " WHERE " + 
//                       "TEAMNAME = '" + myteamname + "'" + "and course = '" + courseName + "'";
          myStatement.executeUpdate(sql);
//          myStatement.executeUpdate(sql2);
          
      }
      catch ( SQLException exception )
      {
         exception.printStackTrace();
      }

   } // end method updateBalance
    private void loadTeams()
    {
     
        // get all account numbers from database
      try 
      {
         //myResultSet = myStatement.executeQuery( "SELECT DISTINCT lastname FROM InstEval" );
         myResultSet = myStatement.executeQuery( "SELECT TEAMNAME FROM APP.TEAM" );
         // add account numbers to accountNumberJComboBox
         while ( myResultSet.next() )
         {
            //instructorComboBox.addItem( myResultSet.getString( "lastname" ) );
            teamComboBox.addItem( myResultSet.getString( "TEAMNAME" ) );
         }

         myResultSet.close(); // close myResultSet

      } // end try

      catch ( SQLException exception )
      {
         System.out.println(exception.getMessage());
      }
    }
    
   
}
