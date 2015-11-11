package GuiElements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.net.*;

//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.PostMethod;


import javax.swing.*;

//import com.sun.net.ssl.HttpsURLConnection;

// To place your ships on the board

public class SetupShipsGui extends JFrame {
 
 
 JButton[][] setShips; // array to hold where you want to place the ships
 int shipSize;
 int shipHorizontal;
 GridLayout gridLayout;
 
 JButton submitBtn; // to confirm the ships location, post that shit, and progress
 JButton directionBtn;
 JButton clearBtn;
 
 final int SHIPS = 13;
 
 public SetupShipsGui() {
  
  this.setTitle("Place your ships");
  
  setShips = new JButton[10][10];
  gridLayout = new GridLayout(11, 10);
  this.setLayout(gridLayout);
  shipSize=2;
  shipHorizontal=0;
 
  // initial the frame
  for (int i = 0; i < 10; i++) {
   for (int x = 0; x < 10; x++){
   setShips[i][x] = new JButton("No Ship"+"("+i+x+")");
   setShips[i][x].addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      int loci;
      int locx;
//        JOptionPane.showMessageDialog(null, ((AbstractButton) e.getSource()).getText().substring(0,7));
//        JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
           loci = Integer.parseInt(((AbstractButton) e.getSource()).getText().substring(8,9));
           locx = Integer.parseInt(((AbstractButton) e.getSource()).getText().substring(9,10));

      if (shipSize == 2 && ((AbstractButton) e.getSource()).getText().substring(0,7).equals("No Ship")) {
          shipSize = 3;
         // Changes text on the Button
           setBlocks(loci, locx, 2);
       }
       else if (shipSize == 3 && ((AbstractButton) e.getSource()).getText().substring(0,7).equals("No Ship") ) {
          shipSize = 4;
          // Changes text on the Button
              setBlocks(loci, locx, 3);
        }
       else if (shipSize == 4 && ((AbstractButton) e.getSource()).getText().substring(0,7).equals("No Ship") ) {
          shipSize = 5;
          // Changes text on the Button
              setBlocks(loci, locx, 4);
        }
       else if (shipSize == 5 && ((AbstractButton) e.getSource()).getText().substring(0,7).equals("No Ship") ) {
          shipSize = 0;
          // Changes text on the Button
              setBlocks(loci, locx, 5);
      
        }
        else {
//              JOptionPane.showMessageDialog(null, ((AbstractButton) e.getSource()).getText().substring(22,25));

//              loci = Integer.parseInt(((AbstractButton) e.getSource()).getText().substring(26,27));
//              locx = Integer.parseInt(((AbstractButton) e.getSource()).getText().substring(27,28));
//              ((AbstractButton) e.getSource()).setText("No Ship"+"("+loci+locx+")");
        };
       
       
    }; 
   }); // end of action Listener
   this.add(setShips[i][x]);
   
   } 
  } // end of for loop
  
  
  clearBtn = new JButton("Clear Ships");
  clearBtn.setPreferredSize(new Dimension(100, 50));
  clearBtn.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
     clearBlocks();
     };
   });
  
  
  directionBtn = new JButton("<html><FONT COLOR=ORANGE><b>Set Ships Horizontal</b></html>");
  directionBtn.setPreferredSize(new Dimension(100, 50));
    directionBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
//      JOptionPane.showMessageDialog(null, ((AbstractButton) e.getSource()).getText());
   
      if (((AbstractButton) e.getSource()).getText().equals("<html><FONT COLOR=ORANGE><b>Set Ships Horizontal</b></html>")) {
//      JOptionPane.showMessageDialog(null, ((AbstractButton) e.getSource()).getText());
           shipHorizontal = 1;
           ((AbstractButton) e.getSource()).setText("<html><FONT COLOR=ORANGE><b>Set Ships Vertical</b></html>");
      }
      else {
//      JOptionPane.showMessageDialog(null, ((AbstractButton) e.getSource()).getText());
           shipHorizontal = 0;
           ((AbstractButton) e.getSource()).setText("<html><FONT COLOR=ORANGE><b>Set Ships Horizontal</b></html>");        
      };
     
     
   };
    });
  
  submitBtn = new JButton("<html><FONT COLOR=BLUE><b>Submit ships</b></html>");
  submitBtn.setPreferredSize(new Dimension(100, 50));
  submitBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    
    int actualConfirmed = 0;
        
    for (int i = 0; i < 10; i++) {
     for (int x = 0; x < 10; x++){
//       JOptionPane.showMessageDialog(null, ((AbstractButton) e.getSource()).getText().substring(0));
//       JOptionPane.showMessageDialog(null, setShips[i][x].getText().substring(22,25));
    // iterate through the buttons, check how many ships are set  
       if (setShips[i][x].getText().length() > 25) {
     if (setShips[i][x].getText().substring(22,25).equals("SET"))
//       JOptionPane.showMessageDialog(null, setShips[i][x].getText().substring(0));
      actualConfirmed++;
    } // end of for
    }
    }

    if (actualConfirmed >= SHIPS) {
     // enough ships were placed
     //2. Open the YourGrid cheese ON A NEW THREAD
     
     SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {
       
       YourGrid gui = new YourGrid();
       return null;

      }
     };
     
     //1. Send that info to the server 
     Runnable r = new Runnable() {
      // This is a critical point in the program
//      sendShips();

      @Override
      public void run() {
       try {
        System.out.println("Sending ships");
        sendPost();
       } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
       }
      }
     };
     r.run();
     
     worker.execute();
     deleteMe();
     
    }// end of if
    
    else {
     // show dialog saying user is an dumb
     JOptionPane.showInternalMessageDialog(SetupShipsGui.this, "You need at least 14 ships!", 
       "Come on", 2, null);
    } // end of else
    
   }
   
  });
  
  
  this.add(submitBtn);
  this.add(clearBtn);
  this.add(directionBtn);
  
  JLabel dontreadthis = new JLabel("<html><b>\tPlace 4 ships of 2, 3, 4 and 5 blocks</b></html>");
  this.setPreferredSize(new Dimension(1280, 720));
  this.add(dontreadthis);
  this.setBounds(0, 0, 1280, 750);
  this.setVisible(true);
  
  this.setLayout(new BorderLayout());
  this.setBounds(0, 0, 1280, 720);
//  this.setBounds(getMaximizedBounds());
  this.setVisible(true);

  
 } // end of constructor
 
 
 
 private void setBlocks(int i, int x, int blocks) {
   
     int newi;   
     int newx;
     for (int c = 0; c < blocks; c++) {
       if (shipHorizontal == 0) {
             newi=i;
             newx=x+c;     
       }
       else {
             newi=i+c;
             newx=x;
       }
       
       if (newi>9 || newx>9) {
             JOptionPane.showMessageDialog(null, "Ship placed out of bound");
             clearBlocks();
             break;
       }
             
       if (setShips[newi][newx].getText().length() > 25) {
             if (setShips[newi][newx].getText().substring(22,25).equals("SET")) {
                     JOptionPane.showMessageDialog(null, "Ship placed over existing ship");
                     clearBlocks();
                     break;
             }
       }
       
       setShips[newi][newx].setText("<html><FONT COLOR=RED>SET("+newi+newx+")</html>");   
      
     }
 }
   
   private void clearBlocks() {
     shipSize=2;
      for (int i = 0; i < 10; i++) {
        for (int x = 0; x < 10; x++){
           setShips[i][x].setText("No Ship"+"("+i+x+")");
        };
      };
   }
   
 
 private void deleteMe() {
  this.setVisible(false);
 }
 
 private void sendPost() throws Exception {

  String sendShips = "";
  
  System.out.println("Params: " + sendShips);
  
  String url = "http://9ff96ab3.ngrok.io/user1_ships";
  URL obj = new URL(url);
  HttpURLConnection con = (HttpURLConnection) obj.openConnection();

  //add request header
  con.setRequestMethod("POST");
  con.setRequestProperty("User-Agent", "User 1");
  con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

  
  // Send post request
  con.setDoOutput(true);
  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//  wr.writeBytes(sendShips);
  
  // check which ships are selected
  // send the data as well
    for (int i = 0; i < 10; i++) {
     
    for (int x = 0; x < 10; x++){
     if (setShips[i][x].getText().equals("<html><FONT COLOR=RED>SET</html>")) {
      
      // append to the params to be passed
      sendShips += ("," + i + " " + x);
      
//      // WRITE THE DATA HERE
//      String tag = "ship" + i;
//      String data = Integer.toString(i);
//      String send = tag + ":" + data + "\n";
//      wr.writeBytes(send);
      
      
     } // end of if
    }  
    } // end of for
  
  System.out.println(sendShips);
  wr.writeBytes(sendShips);
  wr.flush();
  wr.close();

  int responseCode = con.getResponseCode();
  System.out.println("\nSending 'POST' request to URL : " + url);
  
  System.out.println("Response Code : " + responseCode);

  BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
  String inputLine;
  StringBuffer response = new StringBuffer();

  while ((inputLine = in.readLine()) != null) {
   response.append(inputLine);
  }
  in.close();
  
  //print result
  System.out.println(response.toString());

 }
 
 
 // once you are done, start YourGridGui
 
}
