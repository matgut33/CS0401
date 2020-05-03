//Matthew Gutkin
//Assignment 1
//Weasley's Wizard Wheezes
//This program is a shop, where users can purchase different products, view prices, and checkout using custom currencies

import java.util.Scanner;

public class assig1 {
  public static void main (String[] args) {
    Scanner r = new Scanner(System.in);
    String tmp;
    boolean challange = false, discount = false, surcharge = false;
    int pygmy = 0, ears = 0, bagears = 0, itmp = 0, sub=0, total=0, penalty = 0, t = 0;
    int[] wands = new int[2];
    int[] prices = new int[]{290, 60, 160, 1479, 2465};
    int[] totals = new int[5];

    while(true) {
      //reset prices, count of items, and challange vars
      prices = new int[]{290, 60, 160, 1479, 2465};
      wands = new int[2];
      pygmy = 0; ears = 0; itmp = 0;
      challange = false; discount = false; surcharge = false;

      //Welcome and ask if ready
      System.out.println(" ");
      System.out.println("Welcome to Weasley's Wizard Wheezes!");
      while(true){
        System.out.print("Can we help the next witch or wizard? (Y/N): ");
        tmp = r.nextLine();
        if (tmp.equalsIgnoreCase("N")) System.exit(0);
        if (tmp.equalsIgnoreCase("Y")) break;
        else {System.out.println("That input was invalid.");}
      }
      System.out.println(" ");
      displayPrices(prices);

      //Ask about challange
      System.out.println(" ");
      System.out.println("Do you want these prices or would you prefer to try your skill for a");
      System.out.println("discount?  You may elect to answer a challenge question -- if you answer");
      System.out.println("correctly you will get discounted prices but if you answer incorrectly");
      System.out.println("you must pay a 10% penalty on your overall bill.");
      while(true) {
        System.out.println(" ");
        System.out.print("Do you accept the challange? (Y/N): ");
        tmp = r.nextLine();
        if(tmp.equalsIgnoreCase("Y")) {System.out.println("You have accepted the challange!"); challange = true; break;}
        if (tmp.equalsIgnoreCase("N")) {System.out.println("You have declined the challange."); break;}
        else {System.out.println("That input was invalid.");}
      }

      //If they want the challange, give it to em and change discount
      if (challange) {
        System.out.println(" ");
        System.out.println("What name is listed for Harry's dad on the Marauder's Map?");
        System.out.println("1) Moony");
        System.out.println("2) Wormtail");
        System.out.println("3) Padfoot");
        System.out.println("4) Prongs");
      }
      while (challange) {
        System.out.print("> "); itmp = r.nextInt();
        if (itmp == 4) {
          System.out.println("Well done young wizard!");
          discount = true;
          challange = false;
          break;
        } else if(itmp < 4 && itmp > 0) {
          System.out.println("Oh no! That's not correct!");
          System.out.println("You will be assed a 10% surcharge on your purchases.");
          discount = false;
          challange = false;
          surcharge = true;
          break;
        } else {
          System.out.println("That input was invalid.");
          continue;
        }
      }

      //generate actual prices
      if (discount) {
        prices[0] = 261;
        prices[1] = 50;
        prices[2] = 140;
        prices[3] = 1479;
        prices[4] = 1479;
      } /*else if (surcharge) {
        for (int i = 0; i < 5; i++) {
          prices[i] *= 1.1;
        }
      }*/

      //repeat option, update, checkouts until done
      while(true) {
        itmp = option();
        if (itmp == 1) pygmy = pygmy(pygmy, prices[0]);
        else if (itmp == 2) ears = ears(ears, prices[1], prices[2]);
        else if (itmp == 3) wands = wands(wands, prices[3], prices[4], discount);
        else if (itmp == 4) displayPrices(prices);
        else if (itmp == 5) break;
      }

      //checkout process and thanks
      bagears = ears / 3;
      ears = ears % 3;
      totals[0] = pygmy * prices[0];
      totals[1] = bagears * prices[2];
      totals[2] = ears * prices[1];
      totals[3] = wands[0] * prices[3];
      totals[4] = wands[1] * prices[4];
      for (int i : totals) sub += i;
      if (surcharge) {
        penalty = (int)(sub * 0.1);
        total = sub + penalty;
      } else total = sub;
      itmp = total;
      System.out.println(" ");
      System.out.println("Here is your subtotal:");
      if (pygmy > 0) {tmp = ("     " + pygmy + " Pygmy Puffs at " + prices[0] + " Knuts each: "); System.out.printf( "%-60s %d %n", tmp, totals[0]);}
      if (bagears > 0) {tmp = ("     " + bagears + " bags of Extendable Ears at " + prices[2] + " Knuts each: "); System.out.printf( "%-60s %d %n", tmp, totals[1]);}
      if (ears > 0) {tmp = ("     " + ears + " Extendable Ears at " + prices[1] + " Knuts each: "); System.out.printf( "%-60s %d %n", tmp, totals[2]);}
      if (wands[0] > 0) {tmp = ("     " + wands[0] + " regular Trick Wands at " + prices[3] + " Knuts each: "); System.out.printf( "%-60s %d %n", tmp, totals[3]);}
      if (wands[1] > 0) {tmp = ("     " + wands[1] + " autographed Trick Wands at " + prices[4] + " Knuts each: "); System.out.printf( "%-60s %d %n", tmp, totals[4]);}
      System.out.printf("%66s %n", "-----");
      System.out.printf("%-60s %d %n", "Total:", sub);
      if (surcharge) {
        System.out.printf("%-60s %d %n", "Challange penalty of 10%:", penalty);
        System.out.printf("%66s %n", "-----");
        System.out.printf("%-60s %d %n", "New Total:", total);
      }

      System.out.println(" ");
      System.out.println("Please enter a payment amount in the following format:");
      System.out.println("     <amount><space><currency>");
      System.out.println("          Where <amount> = an integer");
      System.out.println("          Where <space> = a blank space");
      System.out.println("          Where <currency> = {Knuts, Sickles, Galleons}");
      System.out.println("     You may enter as many times as you like.  Each entry will be");
      System.out.println("     added to your total until sufficient funds have been obtained.");
      System.out.println("     Recall the currency exchange:");
      System.out.println("           29 Knuts = 1 Sickle");
      System.out.println("           493 Knuts = 17 Sickles = 1 Galleon");
      t = total;
      r = new Scanner(System.in);
      while(true) {
        System.out.print("Payment > "); tmp = r.nextLine();
        int p = 0, k = 0;
        try{
          itmp = tmp.indexOf(" ");
        } catch (Exception e) {
          System.out.println("That payment form is invalid. Try again");
          continue;
        }
        itmp = tmp.indexOf(" ");
        p = Integer.parseInt(tmp.substring(0, itmp));
        itmp ++;
        tmp = tmp.substring(itmp, tmp.length());
        if (tmp.equalsIgnoreCase("Knuts") || tmp.equalsIgnoreCase("Knut")) k = p;
        else if (tmp.equalsIgnoreCase("Sickle") || tmp.equalsIgnoreCase("Sickles")) k = p * 29;
        else if (tmp.equalsIgnoreCase("Galleons") || tmp.equalsIgnoreCase("Galleon")) k = p * 493;
        else {
          System.out.println("That payment form is invalid. Try again.");
          continue;
        }
        t -= k;
        if (t == 0) {
          System.out.println("Thank you for the exact change!");
          System.out.println("Thanks for shopping at WWW! Come again!");
          break;
        } else if (t > 0) {
          System.out.println("You have paid " + k + " Knuts.");
          System.out.println("Your new total is " + t + " Knuts.");
          continue;
        } else if (t < 0) {
          t = Math.abs(t);
          System.out.println("Thank you! You have overpaid!");
          System.out.println("Your change is: ");
          p = t % 493;
          if (p>0) {
            p = t / 493;
            t %= 493;
            System.out.println("     " + p + " Galleons.");
          }
          p = t % 17;
          if (p>0) {
            p = t / 17;
            t %= 17;
            System.out.println("     " + p + " Sickles.");
          }
          if (t > 0) System.out.println("     " + t + " Knuts.");
          System.out.println("Thanks for shopping at WWW! Come again!");
          break;
        }
      }
    }
  }

  //lists all available options
  public static int option() {
    Scanner scan = new Scanner(System.in);
    int o = 0;
    while(true) {
      System.out.println(" ");
      System.out.println("1) Update Pygmy Puffs Order");
      System.out.println("2) Update Extendable Ears Order");
      System.out.println("3) Update Trick Wands Order");
      System.out.println("4) Show price list");
      System.out.println("5) Check Out");
      System.out.print("> ");
      o = scan.nextInt();
      if (o < 6 && o > 0) return o;
      else System.out.println("That input was invlaid.");
    }
  }

  //update pygmy puff order
  public static int pygmy(int p, int price) {
    Scanner scan = new Scanner(System.in);
    System.out.println(" ");
    if (p == 0) System.out.println("No Pygmy Puffs ordered yet.");
    else System.out.println(p + " Pygmy Puffs are in your cart.");
    System.out.println("How many Pygmy Puffs would you like for " + price + " Knuts each?");
    System.out.print("> "); p += scan.nextInt();
    return p;
  }

  //update Extendable ears order
  public static int ears(int e, int price1, int price3) {
    Scanner scan = new Scanner(System.in);
    System.out.println(" ");
    if (e == 0) System.out.println("No Extendable Ears ordered yet.");
    else System.out.println(e + " Extendable Ears are in your cart.");
    System.out.println("How many Extendable Ears would you like for " + price1 + " Knuts each, or " + price3 + " Knuts for 3?");
    System.out.print("> "); e += scan.nextInt();
    return e;
  }

  //update Trick Wands orderer
  public static int[] wands(int[] w, int priceR, int priceA, boolean d) {
    Scanner scan = new Scanner(System.in);
    String tmp;
    int i = 0;
    System.out.println(" ");
    if (w[0] == 0 && w[1] == 0) System.out.println("No Trick Wands in your cart yet.");
    else if (w[0] > 0 && w[1] == 0) System.out.println(w[0] + " Tricked Wands in your cart, but no autographed Trick Wands are in your cart.");
    else if (w[1] > 0 && w[0] == 0) System.out.println(w[1] + " autographed Tricked Wands in your cart, but no regular Trick Wands in your cart yet.");
    else if (w[1] > 0 && w[0] > 0) System.out.println(w[1] + " autographed Tricked Wands in your cart and " + w[0] + " regular Trick Wands in your cart.");
    System.out.println("How many regular Trick Wands would you like to order for " + priceR + " Knuts each?");
    System.out.print("> "); i += scan.nextInt();
    scan = new Scanner(System.in);
    if (d) {
      while(true){
        System.out.println("Would you prefer autographed trick wands, since it is the same price with your discount? (Y/N)");
        System.out.print("> "); tmp = scan.nextLine();
        if (tmp.equalsIgnoreCase("Y")) {
          System.out.println("Upgrading to autographed!");
          w[1] += i;
          break;
        }
        else if(tmp.equalsIgnoreCase("N")) {
          System.out.println("Got it. Regular it is.");
          w[0] += i;
          break;
        } else System.out.println("That input was invalid.");
      }
    } else w[0] += i;
    if (!d || (d && w[0] > 0) ) {
      System.out.println("How many autographed Trick Wands would you like to order for " + priceA + " Knuts each?");
      System.out.print("> "); w[1] += scan.nextInt();
    }
    return w;
  }

  //show prices in Knuts
  public static void displayPrices(int[] p) {
    System.out.println("Here is our price list:");
    System.out.println("    Pygmy Puffs (each)        " + p[0] + " Knuts");
    System.out.println("    Extendable Ears (each)    " + p[1] + " Knuts");
    System.out.println("    Extendable Ears (three)   " + p[2] + " Knuts");
    System.out.println("    Trick Wand (regular)      " + p[3] + " Knuts");
    System.out.println("    Trick wand (autographed)  " + p[4] + " Knuts");
  }

}
