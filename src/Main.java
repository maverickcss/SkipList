import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		boolean trace=false;
		Scanner scan = new Scanner(System.in);
		SkipList sl = null;
        char ch;
        /*  Perform list operations  */
        do
        {
            System.out.println("\nSkipList List Operations\n");
            System.out.println("0. GenerateNewSkipList");
            System.out.println("1. InsertNode");
            System.out.println("2. DeleteNode");
            System.out.println("3. ShowList");
            System.out.println("4. FindNode");
            System.out.println("5. SetTrace");
            System.out.println("6. DeleteList");
            //following methods are left
            System.out.println("7. InsertNodesFromFile");
            System.out.println("8. DeleteNodesFromFile");
            System.out.println("9. PrintStats");
            System.out.println("10. CreateDataFile");

            
            int choice = scan.nextInt();
            switch (choice)
            {
            	case 0 :
            		System.out.println("Enter the maximum levels needed for the skip list");
            		sl = new SkipList(scan.nextInt());
            		System.out.println("SkipList Generated\n");
            		break;
                case 1 :
                    System.out.println("Enter integer element to insert");
                    sl.insertNode(scan.nextInt());
                    if(trace) sl.printList();
                    break;
                case 2 :
                	System.out.println("Enter key to be deleted from skiplist");
                	sl.deleteNode(scan.nextInt());
                	if(trace) sl.printList();
                	break;
                case 3 :
                	sl.printList();
                	break;
                case 4 :
                	System.out.println("Enter key to find");
                    sl.findNode(scan.nextInt()); 
                    break;
                case 5 :
                	System.out.println("To set display list to ON enter 1, 0 otherwise");
                	if(scan.nextInt()==1) trace=true;
                	else trace=false;
                	break;
                case 6 :
                	System.out.println("Sure? 1 for yes, 0 for no");
                	if(scan.nextInt()==1) {
                		sl = null;
                	}
                	break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);

        } while (ch == 'Y'|| ch == 'y');
		
	}
}
