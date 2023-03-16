package refugeoly;


import java.util.*;
import java.io.*;


public class Refugeoly 
{

    
    public static void main(String[] args) 
    {
        double debug, d;
        int p=0, extraturn=0;
        String name, save, load, ans;
        int losses=0, turn=1, dice, sq;
        Scanner input = new Scanner(System.in);
        Board board = new Board();        //Το ταμπλό του παιχνιδιού
        Square tmpsq = new Square();     //Προσωρινή μεταβλητή τύπου Square
        Refugee ref[];                  //Οι παίκτες-πρόσφυγες
        GiverEntity NGOBank = new GiverEntity();          //Η Μη-Κυβερνητική τράπεζα
        ReceiverEntity MafiaBank = new ReceiverEntity();  //Η τράπεζα της Μαφίας
        PrintWriter outputstream = null;
        Scanner inputstream = null;
        
        
        
        
        System.out.printf("Welcome to Refugeoly!\n\n");
        
        
        
        do
        {
            System.out.printf("How many players are going to play?\n");
            p=input.nextInt();
            input.nextLine();
            
        }while(p<1 || p>4);     //Όριο παικτών=4
        
        ref = new Refugee[p];   //Δημιουργεία πίνακα p παικτών
        
        int[] stay = new int[p];   //Πόσους γύρους πρέπει να μείνει ο κάθε παίκτης
        int[] lives = new int[p];  //Πόσες "ζωές" έχει ο κάθε παίκτης
        
        for (int i=0 ; i<p ; i++)
        {
            stay[i]=0;
            lives[i]=0;
        }
        
        
        
        for (int i=0 ; i<p ; i++)
        {
            System.out.printf("Give name of player #%d: ", i+1);
            name=input.nextLine();
            ref[i]=new Refugee();
            ref[i].setName(name);
            System.out.printf("\n");
        }
        
        
        System.out.printf("Give the name of the NGO (Non-Governmental Organization): ");
        name=input.nextLine();
        NGOBank.setName(name);
        
        
        System.out.printf("\n\nGive the name of the Mafia: ");
        name=input.nextLine();
        MafiaBank.setName(name);
        System.out.printf("\n\n");
        
        
        
        
        
        
        
        
        System.out.printf("Game Start!\n\n");
        do
        {
            
            if (losses==p)
            {
                System.out.printf("Game Lost! All players have gone bankrupted!\nExited Game.\n");
                return;
            }
            
            
            if (turn==p+1 && extraturn==0)  //Η σειρά αλλάζει μόνο αν ο παίκτης δεν έχει την εντολή να ξαναρίξει το ζάρι
            {
                turn=1;
            }
            extraturn=0;
            
            
            if (ref[turn-1].getMoney()==0)  //Ο παίκτης δεν παίζει αν έχει χρεοκοπήσει
            {
                stay[turn-1]=0;   //Για επιβεβαίωση...
                lives[turn-1]=0;  //Για επιβεβαίωση...
                
                if (turn+1>p)
                {
                    turn=1;
                }
                else
                {
                    turn=turn+1;
                }
            }
            
            
            if (stay[turn-1]==1)   //Ο παίκτης παραλείπεται (Μένει έναν γύρο)
            {
                if (turn+1>p)
                {
                    turn=1;
                }
                else
                {
                    turn=turn+1;
                }
                
                stay[turn-1]=stay[turn-1]-1;
            }
            
            
            do  //Save function
            {
                System.out.printf("Do you want to save the game? (y/n)\n");
                save=input.next();
                if (save.compareTo("y")==0 || save.compareTo("Y")==0)
                {
                    try
                    {
                        outputstream=new PrintWriter(new FileOutputStream("gamesave.txt"));
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.printf("Could not Save Game!\n\n");
                        outputstream.close();
                    }
                    
                    outputstream.printf("%d\n", p);   //Αποθήκευση αριθμού παικτών
                    
                    outputstream.printf("%d\n", turn); //Αποθήκευση σειράς επόμενου παίκτη
                    
                    for (int i=0 ; i<p ; i++)   //Αποθήκευση στοιχείων κάθε παίκτη με την σειρά
                    {
                        outputstream.printf("%s\n", ref[i].getName());  //Αποθήκευση ονόματος
                        outputstream.printf("%f\n", ref[i].getMoney()); //Αποθήκευση χρημάτων
                        outputstream.printf("%d\n", ref[i].getSquare()); //Αποθήκευση τετραγώνου
                        outputstream.printf("%f\n", ref[i].getExpenses()); //Αποθήκευση εξόδων
                    }
                    
                    outputstream.printf("%s\n", NGOBank.getName()); //Αποθήκευση ονόματος NGO
                    outputstream.printf("%f\n", NGOBank.getMoney()); //Αποθήκευση χρημάτων NGO
                    
                    outputstream.printf("%s\n", MafiaBank.getName()); //Αποθήκευση ονόματος MafiaBank
                    outputstream.printf("%f\n", MafiaBank.getMoney()); //Αποθήκευση χρημάτων MafiaBank
                    
                    outputstream.close();
                    
                    System.out.printf("GAME SAVED!\n\n");
                }
                
            }while(save.compareTo("y")!=0 && save.compareTo("n")!=0 && save.compareTo("Y")!=0 && save.compareTo("N")!=0);
            
            
            
            do   //Load function
            {
                System.out.printf("Do you wish to load a previously saved game? (y/n)\n");
                load=input.next();
                if (load.compareTo("y")==0 || load.compareTo("Y")==0)
                {
                    try
                    {
                        inputstream=new Scanner(new File("gamesave.txt"));
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.printf("Could not Load Game! Save not Found!\n\n");
                        inputstream.close();
                    }
                    
                    p=inputstream.nextInt();  //Φόρτωση αριθμού παικτών
                    inputstream.nextLine();
                    
                    turn=inputstream.nextInt();  //Φόρτωση σειράς παίκτη
                    inputstream.nextLine();
                    
                    for (int i=0 ; i<p ; i++)   //Φόρτωση στοιχείων κάθε παίκτη με την σειρά
                    {
                        ref[i].setName(inputstream.nextLine());  //Φόρτωση ονόματος
                        ref[i].setMoney(inputstream.nextDouble());  //Φόρτωση χρημάτων
                        inputstream.nextLine();
                        ref[i].setSquare(inputstream.nextInt());  //Φόρτωση τετραγώνου
                        inputstream.nextLine();
                        ref[i].setExpenses(inputstream.nextDouble());  //Φόρτωση εξόδων
                        inputstream.nextLine();
                    }
                    
                    NGOBank.setName(inputstream.nextLine());  //Φόρτωση ονόματος NGO
                    NGOBank.setMoney(inputstream.nextDouble());  //Φόρτωση χρημάτων NGO
                    inputstream.nextLine();
                    
                    MafiaBank.setName(inputstream.nextLine());  //Φόρτωση ονόματος MafiaBank
                    MafiaBank.setMoney(inputstream.nextDouble());  //Φόρτωση χρημάτων Mafia Bank
                    inputstream.nextLine();
                    
                    System.out.printf("GAME LOADED!\n\n");
                }
            }while(load.compareTo("y")!=0 && load.compareTo("n")!=0 && load.compareTo("Y")!=0 && load.compareTo("N")!=0);
            
            
            
            
            
            
            
            
            do
            {
                System.out.printf("Player %d rolls the dice (0=Exit): ", turn);
                dice=input.nextInt();
                input.nextLine();
                System.out.printf("\n");

            }while(dice<0 || dice>6);

            if (dice==0)
            {
                System.out.printf("Exited Game.\n");
                return;
            }

            ref[turn-1].moveTo(dice);   //Ο παίκτης μετακινείται όσα κουτάκια του λέει το ζάρι
            
            sq=ref[turn-1].getSquare();  //Αποθήκευση του τετραγώνου του τωρινού παίκτη σε μια προσωρινή μεταβλητή
            
            
            
            
            
			
            if (sq==1)
            {
                PayMoneyAction pma = new PayMoneyAction();
                
                tmpsq.setText("Food for the journey. Pay $100.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
		System.out.printf(tmpsq.getText());
                
                
                
                Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
                
                
            }
            else if (sq==2)
            {
                RollDiceAction rda = new RollDiceAction();
                
                tmpsq.setText("Car. You get a free lift. Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(rda);
                System.out.printf(tmpsq.getText());
                
                extraturn=rda.rollDice(sq);
		continue;
            }
            else if (sq==3)
            {
		PayMoneyAction pma = new PayMoneyAction();
                
                tmpsq.setText("Communication gear. Mobile phone and sim card. Pay $300 to the Mafia Bank.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
		System.out.printf(tmpsq.getText());
                  
                Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
            }
            else if (sq==4)
            {
                GoToAction gta = new GoToAction();
                
                tmpsq.setText("Army Control. You go back to war box (0).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
		System.out.printf(tmpsq.getText());
		
		gta.act(ref[turn-1]);
            }
            else if (sq==5)
            {
		GoToAction gta = new GoToAction();
                
                tmpsq.setText("Border 1. Go back to war box (0).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
		System.out.printf(tmpsq.getText());
		
		gta.act(ref[turn-1]);
                
            }
            else if (sq==6)
            {
		PayMoneyAction pma = new PayMoneyAction();
                
                tmpsq.setText("Mafia. Pay 1000$.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
		System.out.printf(tmpsq.getText());
                
                Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
            }
            else if (sq==7)
            {
		tmpsq.setText("Life Vest. You have an extra life if you land in the sea (Box 10).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
		System.out.printf(tmpsq.getText());
                
		lives[turn-1]=lives[turn-1]+1;
            }
            else if (sq==8)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("GPS Location. Stay for a turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
		System.out.printf(tmpsq.getText());
                
		stay[turn-1]=sot.stay(sq);
            }
            else if (sq==9)
            {
		PayMoneyAction pma = new PayMoneyAction();
                RollDiceAction rda = new RollDiceAction();
                
                tmpsq.setText("Boat. Pay 3000$ to the Mafia Bank. Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                tmpsq.setAction(rda);
		System.out.printf(tmpsq.getText());
                
		Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
                
                extraturn=rda.rollDice(sq);
                continue;
            }
            else if (sq==10)
            {
                
                if (lives[turn-1]!=0)
                {
                    tmpsq.setText("You have an extra life. Next player continues the journey.\n");
                    tmpsq.setNumber(sq);
                    board.addSquare(tmpsq);
                    System.out.printf(tmpsq.getText());
                    
                    lives[turn-1]=lives[turn-1]-1;
                }
                else
                {
                    
                    tmpsq.setText("Dead at Sea. You are dead and out of the game.\n");
                    tmpsq.setNumber(sq);
                    board.addSquare(tmpsq);
                    System.out.printf(tmpsq.getText());
                    
                    ref[turn-1].setMoney(0);
                    stay[turn-1]=0;
                    lives[turn-1]=0;
                    losses=losses+1;
                }
            }
            else if (sq==11)
            {
                StayOneTurn sot = new StayOneTurn();
                
                tmpsq.setText("You get sick. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
		
                
                stay[turn-1]=sot.stay(sq);
            }
            else if (sq==12)
            {
                RollDiceAction rda = new RollDiceAction();
                
                tmpsq.setText("You reach an EU coast. Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(rda);
                System.out.printf(tmpsq.getText());
                
                
                extraturn=rda.rollDice(sq);
                continue;
            }
            else if (sq==13)
            {
                PayMoneyAction pma = new PayMoneyAction();
                
		tmpsq.setText("Tent and sleeping bag. Pay 200$ to the Maﬁa Bank.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                System.out.printf(tmpsq.getText());
                
                Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
            }
            else if (sq==14)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Border Police. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
                stay[turn-1]=sot.stay(sq);
            }
            else if (sq==15)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("Border Control 2. Back to Border Control 1.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==16)
            {
                PayMoneyAction pma = new PayMoneyAction();
                RollDiceAction rda = new RollDiceAction();
                
		tmpsq.setText("Refugee Camp. Pay 500$ to the Maﬁa bank to leave and roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                tmpsq.setAction(rda);
                System.out.printf(tmpsq.getText());
                
		Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
                
                extraturn=rda.rollDice(sq);
                continue;
            }
            else if (sq==17)
            {
                RollDiceAction rda = new RollDiceAction();
                
		tmpsq.setText("Train. Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(rda);
                System.out.printf(tmpsq.getText());
                
		extraturn=rda.rollDice(sq);
                continue;
            }
            else if (sq==18)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("Red Cross Shelter. Jump to river crossing box (22).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==19)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Guard Dogs. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
                stay[turn-1]=sot.stay(sq);
            }
            else if (sq==20)
            { 
                try
                {
                    NGOBank.giveMoney(1000);
                }
                catch (NoMoneyException e)
                {
                    System.out.printf("%s", e.getMessage());
                    continue;
                }
                
                GetMoneyAction gma = new GetMoneyAction();
                
		tmpsq.setText("NGO Support. You receive 1000$ from NGO Bank.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gma);
                System.out.printf(tmpsq.getText());
                
                gma.act(ref[turn-1]);
                
            }
            else if (sq==21)
            {
                PayMoneyAction pma = new PayMoneyAction();
                
		tmpsq.setText("Theft. You lose 1500$. Place this money in the Players Expenses box.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                System.out.printf(tmpsq.getText());
                
		Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
            }
            else if (sq==22)
            {
		tmpsq.setText("River Crossing. Roll dice and go backwards by the number  on the dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                System.out.printf(tmpsq.getText());
                
		do
                {
                    System.out.printf("Player %d rolls the dice again to go backwards: ", turn);
                    dice=input.nextInt();
                    input.nextLine();
                    System.out.printf("\n");
                
                }while(dice<1 || dice>6);
                
                ref[turn-1].moveTo(-dice);
                
                if (turn+1>p)
                {
                    turn=1;
                }
                else
                {
                    turn=turn+1;
                }
            }
            else if (sq==23)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("NGO Lift. Jump to family reunion box (29).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==24)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Border Police. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
                stay[turn-1]=sot.stay(sq);
            }
            else if (sq==25)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("Border Control 3. Back to border 2 (Box 15).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==26)
            {
                PayMoneyAction pma = new PayMoneyAction();
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Asylum Paperwork. Pay 1000$ to Maﬁa Bank. Option A: Pay $1500 to Maﬁa Bank and roll dice. Option B: Don’t pay and stay 2 turns. (a/b)\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
		Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
                
                do
                {
                    ans=input.nextLine();
                }while(ans.compareTo("a")==0 || ans.compareTo("A")==0 || ans.compareTo("b")==0 || ans.compareTo("B")==0);
                
                if (ans.compareTo("a")==0 || ans.compareTo("A")==0)
                {
                    tmp.setMoney(ref[turn-1].getMoney());

                    if (tmp.giveMoney(100)==-1)
                    {
                        ref[turn-1].setMoney(0);
                        losses=losses+1;
                    }
                    else
                    {
                        pma.act(ref[turn-1]);
                        MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                    }
                    
                    do
                    {
                        System.out.printf("Player %d rolls the dice again: ", turn);
                        dice=input.nextInt();
                        input.nextLine();
                        System.out.printf("\n");
                
                    }while(dice<1 || dice>6);
                
                    ref[turn-1].moveTo(dice);
                }
                else if (ans.compareTo("b")==0 || ans.compareTo("B")==0)
                {
                    stay[turn-1]=sot.stay(sq);
                }
            }
            else if (sq==27)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Storm. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
		stay[turn-1]=sot.stay(sq);
            }
            else if (sq==28)
            {
		tmpsq.setText("UNHCR Aid. Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                System.out.printf(tmpsq.getText());
                
		do
                {
                    System.out.printf("Player %d rolls the dice again: ", turn);
                    dice=input.nextInt();
                    input.nextLine();
                    System.out.printf("\n");
                
                }while(dice<1 || dice>6);
                
                ref[turn-1].moveTo(dice);
            }
            else if (sq==29)
            {
                GoToAction gta = new GoToAction();
                
                tmpsq.setText("Family Reunion. Jump to bus box (31).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==30)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("Right Wing Militia. Back to Border police box (24).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==31)
            {
                PayMoneyAction pma = new PayMoneyAction();
                
		tmpsq.setText("Maﬁa Bus. Pay 800$ to Maﬁa Bank. Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                System.out.printf(tmpsq.getText());
                
		Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
                
		do
                {
                    System.out.printf("Player %d rolls the dice again: ", turn);
                    dice=input.nextInt();
                    input.nextLine();
                    System.out.printf("\n");
                
                }while(dice<1 || dice>6);
                
                ref[turn-1].moveTo(dice);
            }
            else if (sq==32)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Government Detention Camp. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
                stay[turn-1]=sot.stay(sq);
            }
            else if (sq==33)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText(" Asylum Seeker Application rejected. Back to Train box (17) and Roll dice.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
                
		do
                {
                    System.out.printf("Player %d rolls the dice again: ", turn);
                    dice=input.nextInt();
                    input.nextLine();
                    System.out.printf("\n");
                
                }while(dice<1 || dice>6);
                
                ref[turn-1].moveTo(dice);
            }
            else if (sq==34)
            {
                StayOneTurn sot = new StayOneTurn();
                
		tmpsq.setText("Border Police. Stay one turn.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(sot);
                System.out.printf(tmpsq.getText());
                
                
		stay[turn-1]=sot.stay(sq);
            }
            else if (sq==35)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("Border Control 4. Back to Border 3 (Box 25).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==36)
            {
		tmpsq.setText("Asylum Seeker Application Approved. You win.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                System.out.printf(tmpsq.getText());
                
                System.out.printf("Player %s wins the game with %f cash and a total of %f expenses.\n", ref[turn-1].getName(), ref[turn-1].getMoney(), ref[turn-1].getExpenses());
                System.out.printf("(Set the money of the winner to 0)\n\n");
                ref[turn-1].setMoney(0);   //Ο παίκτης δεν ξαναπαίζει
            }
            else if (sq==37)
            {
                PayMoneyAction pma = new PayMoneyAction();
                
		tmpsq.setText("Maﬁa. Pay 1000$ to Maﬁa Bank.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(pma);
                System.out.printf(tmpsq.getText());
                
		Refugee tmp = new Refugee();    //"Προσωρινός" παίκτης για έλεγχο
                tmp.setMoney(ref[turn-1].getMoney());
                
                if (tmp.giveMoney(100)==-1)
                {
                    ref[turn-1].setMoney(0);
                    losses=losses+1;
                }
                else
                {
                    pma.act(ref[turn-1]);
                    MafiaBank.receiveMoney(ref[turn-1].giveMoney(100));
                }
            }
            else if (sq==38)
            {
                GoToAction gta = new GoToAction();
                
		tmpsq.setText("Deported. You are sent back to war box (0).\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                tmpsq.setAction(gta);
                System.out.printf(tmpsq.getText());
                
		gta.act(ref[turn-1]);
            }
            else if (sq==39)
            {
		tmpsq.setText("New Home. You are ﬁnally accepted. You win.\n");
                tmpsq.setNumber(sq);
                board.addSquare(tmpsq);
                System.out.printf(tmpsq.getText());
                
		System.out.printf("Player %s wins the game with %f cash and a total of %f expenses.\n", ref[turn-1].getName(), ref[turn-1].getMoney(), ref[turn-1].getExpenses());
                System.out.printf("(Set the money of the winner to 0)\n\n");
                ref[turn-1].setMoney(0);   //Ο παίκτης δεν ξαναπαίζει
            }
            
            
            
            
            
            
            System.out.printf("\nStatistics so far:\n\t");
            for (int i=1 ; i<=p ; i++)
            {
                System.out.printf("Player %s's stats:\n\t", ref[i-1].getName());
                System.out.printf("Money: %f\n\t", ref[i-1].getMoney());
                System.out.printf("Expenses: %f\n\t", ref[i-1].getExpenses());
                System.out.printf("Current square: %d\n\n", ref[i-1].getSquare());
            }
            
            System.out.printf("%s's Bank account contains: %f euro.\n", NGOBank.getName(), NGOBank.getMoney());
            System.out.printf("%s's Bank account contains: %f euro.\n\n", MafiaBank.getName(), MafiaBank.getMoney());
            
            
            
            if (p>1)
            {
                turn=turn+1;
            }
            
            
            
        }while(true);
        
        
        
        
    }
    
}
