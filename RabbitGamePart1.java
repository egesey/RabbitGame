package SecondAndThirdProjectOfClass;

import java.util.Scanner;

public class RabbitGamePart1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Roles:\n"
                + "1- Each carrots 5 point.(if you can feed the rabbits).\n"
                + "2- If you don't have enough carrot for rabit you lose 5 point.\n"
                + "3- There is a role that if you go [x.10] places you jumped [x.1] place. ([x.10] places are teleportatial.)\n"
                + "3- Each rats -5 point by carrot numbers.(if rat eat 2 carrot you lose 10 point).\n"
                + "5- If you don't have carrot and you saw rat don't worry you don't lose any point.\n"
                + "6- If you exit from the hell you directly lose.\n"
                + "7- If you exit from the heaven your points will exist.\n"
                + "8- Player start with 1 point.\n"
                + "9- There is a kid security for younger than (5y.o).");

        System.out.println("");
        System.out.println("Enter the s value: ");
        int s = scan.nextInt();
        System.out.println("press[1] for open the game");
        int exit = scan.nextInt();//This exit value for start and stop the system.
        System.out.println("");
        System.out.println("There is a kid security..Answer the question(+5y.o): ");
        int type = (int) (Math.random() * 4);//[0,3]
        boolean answer1 = generateAQueastion(type);
        if (answer1 == false) {//if player can give a correct answer he can play the game otherwise he cannot open the game.
            exit = 0;
            System.out.println("Kid security alarm!");
        } else {

            exit = 1;
        }

        //Player
        double player1Place = player1Place(s);

        //Rats
        double rat1Place = randomPlaces(s);
        double rat2Place = randomPlaces(s);

        //Rabits
        double rabit1Place = randomPlaces(s);

        double rabit2Place = randomPlaces(s);

        double rabit3Place = randomPlaces(s);

        //Heaven Hell
        double heavenPlace = randomPlaces(s);

        double hellPlace = randomPlaces(s);
//I write this code for make different all defined values.
        while (player1Place == rat1Place || player1Place == rat2Place || player1Place == rabit1Place || player1Place == rabit2Place || player1Place == rabit3Place
                || player1Place == heavenPlace || player1Place == hellPlace || rat1Place == rat2Place || rat1Place == rabit1Place || rat1Place == rabit2Place
                || rat1Place == rabit3Place || rat1Place == heavenPlace || rat1Place == hellPlace || rat2Place == rabit1Place || rat2Place == rabit2Place
                || rat2Place == rabit3Place || rat2Place == heavenPlace || rat2Place == hellPlace || rabit1Place == rabit2Place || rabit1Place == rabit3Place
                || rabit1Place == heavenPlace || rabit1Place == hellPlace || rabit2Place == rabit3Place || rabit2Place == heavenPlace || rabit2Place == hellPlace
                || rabit3Place == heavenPlace || rabit3Place == hellPlace || heavenPlace == hellPlace) {
            rat1Place = randomPlaces(s);
            rat2Place = randomPlaces(s);
            rabit1Place = randomPlaces(s);//redefine untill make different all values.
            rabit2Place = randomPlaces(s);
            rabit3Place = randomPlaces(s);
            heavenPlace = randomPlaces(s);
            hellPlace = randomPlaces(s);
            player1Place = player1Place(s);
        }//end of loop

        int count1 = 0;//counter
        int point = 1;//starter point

        //game loop
        while (exit == 1) {//this while will stop when I define exit to 0
            System.out.println("Do you want to see Item Places?");
            System.out.println("Yes[5] / No[6]");
            int yesOrNo = scan.nextInt();
            if (yesOrNo == 5) {//You can see the item places when you are playing in whenever you want.
                System.out.println("Item Places: \n");
                System.out.println("Rat1: " + "[" + rat1Place + "]");
                System.out.println("Rat2: " + "[" + rat2Place + "]");
                System.out.println("Rabit1: " + "[" + rabit1Place + "]");
                System.out.println("Rabit2: " + "[" + rabit2Place + "]");
                System.out.println("Rabit3: " + "[" + rabit3Place + "]");
                System.out.println("heaven: " + "[" + heavenPlace + "]");
                System.out.println("hell: " + "[" + hellPlace + "]");
            } else {
                System.out.println("");
            }
            System.out.println("Player1 place: [" + player1Place + "]");//player place
            System.out.println("Carrots: " + count1);
            System.out.println("Points: " + point);
            areaOfGame(s);
            System.out.println("[2]: down, [4]: left, [6]: right, [8]: up");
            int selectMove = scan.nextInt();

            System.out.println("[" + personMove(s, player1Place, selectMove) + "]");//personMoves method changed player places according to your selectMove.
            canMove(s, personMove(s, player1Place, selectMove), selectMove);
            isThereACarrot(personMove(s, player1Place, selectMove), type);//looking is there a carrot and prints according to your answer to question.

            double num = personMove(s, player1Place, selectMove) % 1; //for ex. [3.2]%1-> 0.2 
            double num1 = personMove(s, player1Place, selectMove) - num;//for ex.[3.2]-0.2->3.0
            //[3.2] -> 32%2->0                                      //if num1(3.0)%2->0 thats not correct so there i no carrot.
            while (answer1 == true && (10 * personMove(s, player1Place, selectMove)) % 2 == 0 && num1 % 2 == 0) {//first condition looking first step 3.2->0.2 second condition looking first step 3.2 -> 3.0
                count1 += 1;//carrot value
                point = (count1 * 5);//each carrot 5 point
                System.out.println("You have " + count1 + " carrot.");
                System.out.println("You won 5 point.");
                break;
            }//end of loop
            isThereARat(personMove(s, player1Place, selectMove), rat1Place, rat2Place);//looking is there a rat and prints
            isThereARabit(personMove(s, player1Place, selectMove), rabit1Place, rabit2Place, rabit3Place);//looking is there a rabit and prints

            if (personMove(s, player1Place, selectMove) == rabit1Place || personMove(s, player1Place, selectMove) == rabit2Place
                    || personMove(s, player1Place, selectMove) == rabit3Place) {//there is a rabit
                int numRab = (int) ((Math.random() * 3) + 1);//[1,3]ranndom
                System.out.println("rabbit want " + numRab + " carrot.");
                if (count1 >= numRab) {//if you have enough carrot
                    point += numRab * 5;
                    System.out.println("You feed the rabit.");
                    System.out.println("You won " + numRab * 5 + " point.");
                    count1 -= numRab;//carrots decreasing when rabits eat
                } else {
                    System.out.println("You can't feed the rabit.");
                }
            }
            if (personMove(s, player1Place, selectMove) == rat1Place || personMove(s, player1Place, selectMove) == rat2Place) {//there is a rat
                int numRat = (int) ((Math.random() * 3) + 1);//[1,3]random
                System.out.println("Rat want " + numRat + " carrot.");
                if (count1 >= numRat) {//if you have enough carrot
                    point -= numRat * 5;
                    System.out.println("Rat eat your carrots.");
                    System.out.println("You lose " + numRat * 5 + " point.");
                    count1 -= numRat;//carrots decreasing when rabits eat
                } else {
                    System.out.println("You have no carrot for rat you are luck.");
                    System.out.println("Rat didn't eat your carrots.");
                }
            }

            exitToHell(personMove(s, player1Place, selectMove), hellPlace);//look is there a hell
            exitToHeaven(personMove(s, player1Place, selectMove), heavenPlace);//look is there a heaven

            if (personMove(s, player1Place, selectMove) == heavenPlace) {//there is heaven
                System.out.println("POINTS: " + point);
                System.out.println("CARROTS: " + count1);//results
                System.out.println("");
                System.out.println("Item Places: \n");
                System.out.println("Rat1: " + "[" + rat1Place + "]");
                System.out.println("Rat2: " + "[" + rat2Place + "]");
                System.out.println("Rabit1: " + "[" + rabit1Place + "]");
                System.out.println("Rabit2: " + "[" + rabit2Place + "]");
                System.out.println("Rabit3: " + "[" + rabit3Place + "]");
                System.out.println("heaven: " + "[" + heavenPlace + "]");
                System.out.println("hell: " + "[" + hellPlace + "]");
            } else {
                System.out.println("");
            }

            if (personMove(s, player1Place, selectMove) == hellPlace) {//there is hell and stop the system
                exit = 0;
            } else if (personMove(s, player1Place, selectMove) == heavenPlace) {//there is heaven and stop the system

                exit = 0;
            } else {

                player1Place = personMove(s, player1Place, selectMove);
                System.out.println("Do you want to exit the game?");
                System.out.println("[0]Yes \n [1]No");
                exit = scan.nextInt();//for ask do you want to exit in every step
            }
        }//end of loop

    }//end of main method

    public static void areaOfGame(int s) {

        for (int i = 1; i <= s; i++) {
            for (int space = 1; space <= (s - i); space++) {//when i increasing space dicrease
                System.out.print("     ");//this makes looks like pyramid

            }

            int j = ((2 * i) - 1);//given in pdf

            if (i == 1) {
                System.out.print("[" + i + "," + j + "]");
            } else {
                for (int column = 1; column <= (2 * i) - 1; column++) {
                    j = 1;
                    while (j <= ((2 * i) - 1)) {//always print [i,j] while this condition is finish
                        System.out.print("[" + i + "," + j + "]");
                        j++;
                    }//end of loop
                    break;

                }
            }
            System.out.println("");//for jump line
        }

    }//end of areaOfGame method

    public static double player1Place(int s) {

        double i = (int) (Math.random() * (s) + 1);
        double j = (int) (Math.random() * ((2 * i) - 1) + 1);
        if (j >= 10) {//for second step [x.y]-> 0.y ex. 0.11
            j = j / 100.0;
        } else {
            j = j / 10.0;//for second step [x.y]-> 0.y ex. 0.7
        }
        return i + j;

    }//end of player1Place method

    public static double randomPlaces(int s) {

        double randomI = (int) (Math.random() * (s) + 1);//[1,s]
        double randomJ = (int) (Math.random() * ((2 * randomI) - 1) + 1);//[1,j]

        if (randomJ >= 10) {
            randomJ = randomJ / 100.0;//for second step [x.y]-> 0.y ex. 0.11
        } else {
            randomJ = randomJ / 10.0;//for second step [x.y]-> 0.y ex. 0.7
        }

        return randomI + randomJ;

    }//end of randomPlaces method

    public static void canMove(int s, double p1, int direction) {
        if ((p1 * 10) < 100) {//ex 2.3-> 23 6.11-> 61.1
            double j = (int) ((p1 * 10) % 10);//3
            double i = (int) (((p1 * 10) - j) / 10);//2

            switch (direction) {
                case 2:
                    if (i == s) {

                        System.out.println("you can't go down!");

                    }
                    break;
                case 4:
                    if (j == 1) {
                        System.out.println("You can't go left!");

                    }
                    break;
                case 6:
                    if (j == ((2 * i) - 1)) {
                        System.out.println("You can't go right!");
                    }
                    break;
                case 8:

                    if (j == ((2 * i) - 1) || j == 1 || j == 10) {
                        System.out.println("You can't go up!");
                    }
                    break;
                default:
                    System.out.println("there is an eror...");
                    break;
            }//end of switch direction

        } else if ((p1 * 10) > 100) {//ex 6.11-> 611
            double j = (p1 * 100) % 100;
            double i = ((p1 * 100) - j) / 100;

            switch (direction) {
                case 2:
                    if (i == s) {

                        System.out.println("you can't go down!");

                    }
                    break;
                case 4:
                    if (j == 1) {
                        System.out.println("You can't go left!");

                    }
                    break;
                case 6:
                    if (j == ((2 * i) - 1)) {
                        System.out.println("You can't go right!");
                    }
                    break;
                case 8:

                    if (j == ((2 * i) - 1) || j == 1 || j == 10) {
                        System.out.println("You can't go up!");
                    }
                    break;
                default:
                    System.out.println("there is an eror...");
                    break;
            }//end of switch direction 
        }

    }//end of canMove method

    public static double personMove(int s, double p1, int selectMove) {

        if ((p1 * 10) < 100) {//ex 2.3-> 23 6.11-> 61.1
            double j = (int) ((p1 * 10) % 10);//3
            double i = (int) (((p1 * 10) - j) / 10);//2

            switch (selectMove) {
                case 2: //down

                    if (i == s) {

                    } else {
                        j++;
                        i++;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j

                            return p1;
                        } else if (j > 10) {//6 11 -> 6.11
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {//2.3
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }

                    break;
                case 4: //left

                    if (j == 1) {

                    } else {
                        j--;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }
                    break;
                case 6: //right

                    if (j == ((2 * i) - 1)) {

                    } else {
                        j++;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }
                    break;
                case 8: //up

                    if (j == ((2 * i) - 1) || j == 1) {
                    } else {
                        j--;
                        i--;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }
                    break;
                default:
                    System.out.println("there is an eror...");
                    break;
            }//end of switch selectMove
        } else if ((p1 * 10) > 100) {//ex 6.11-> 611
            double j = (p1 * 100) % 100;
            double i = ((p1 * 100) - j) / 100;

            switch (selectMove) {
                case 2: //down

                    if (i == s) {
                    } else {
                        j++;
                        i++;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }
                    }

                    break;
                case 4: //left

                    if (j == 1) {
                    } else {
                        j--;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }
                    break;
                case 6: //right

                    if (j == ((2 * i) - 1)) {
                    } else {
                        j++;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }
                    break;
                case 8: //up

                    if (j == ((2 * i) - 1) || j == 1) {
                    } else {
                        j--;
                        i--;
                        if (j == 10) {

                            p1 = i + j / 100;//i.j
                            return p1;
                        } else if (j > 10) {
                            p1 = (((i * 10) + j / 10) / 10) + (j % 10);

                            return p1;
                        } else {
                            p1 = i + (j / 10.0);
                            return p1;
                        }

                    }
                    break;

                default:
                    System.out.println("there is an eror...");
                    break;

            }//end of switch selectMove

        } else {
            System.out.println("Something wrong here...");
        }
        return p1;
    }//end of personMoves method

    public static void isThereACarrot(double player1Place, int type) {
//3.2 -> 3 - 0.2 = 3 -> 3%2 != 0 There is no carrot.Ex.
        double num = player1Place % 1;  //0.2 Ex.
        double num1 = player1Place - num; //3 Ex.

        if ((10 * player1Place) % 2 == 0 && num1 % 2 == 0) {

            System.out.println("There is an carrot.");
            System.out.println("Question: ");
            if (generateAQueastion(type) == false) {
                System.out.println("You cannot win any carrot.");
            }

        } else {
            System.out.println("carrot: -Not here-");

        }

    }//end of isThereACarrot method

    public static void isThereARat(double player1Place, double rat1Place, double rat2Place) {

        if (player1Place == rat1Place || player1Place == rat2Place) {

            System.out.println("There is a rat.");

        } else {
            System.out.println("rat: -Not here-");

        }

    }//end of isThereARat method

    public static boolean generateAQueastion(int type) {
        Scanner scan = new Scanner(System.in);

        if (type == 0) {//Addition
            int num1 = (int) ((Math.random() * 10) + 1);//[1,10]
            int num2 = (int) ((Math.random() * 10) + 1);//[1,10]
            System.out.println("What is " + num1 + " + " + num2 + "(int): ");
            int answer = scan.nextInt();
            return answer == (num1 + num2);
        } else if (type == 1) {//Subtraction
            int num3 = (int) ((Math.random() * 10) + 1);//[1,10]
            int num4 = (int) ((Math.random() * 10) + 1);//[1,10]
            System.out.println("What is " + num3 + " - " + num4 + "(int): ");
            int answer1 = scan.nextInt();
            return answer1 == (num3 - num4);
        } else if (type == 2) {//Division
            int num5 = (int) ((Math.random() * 10) + 1);//[1,10]
            int num6 = (int) ((Math.random() * 10) + 1);//[1,10]
            System.out.println("What is " + num5 + " / " + num6 + "(int): ");
            int answer2 = scan.nextInt();
            return answer2 == (num5 / num6);
        } else {//Multiplication
            int num7 = (int) ((Math.random() * 10) + 1);//[1,10]
            int num8 = (int) ((Math.random() * 10) + 1);//[1,10]
            System.out.println("What is " + num7 + " * " + num8 + "(int): ");
            int answer3 = scan.nextInt();
            return answer3 == (num7 * num8);
        }

    }//end of generateAQueastion method

    public static void isThereARabit(double player1Place, double rabit1Place, double rabit2Place, double rabit3Place) {
        if (player1Place == rabit1Place || player1Place == rabit2Place || player1Place == rabit3Place) {

            System.out.println("There is a rabit.");

        } else {
            System.out.println("rabbit: -Not here-");

        }
    }//end of isThereARabit method

    public static void exitToHell(double player1Place, double hellPlace) {
        if (player1Place == hellPlace) {
            System.out.println("Hell exist.");
            System.out.println("");
            System.out.println("YOU LOSE.");
        } else {
            System.out.println("Hell: -Not here-");
        }

    }//end of exitToHell method

    public static void exitToHeaven(double player1Place, double heavenPlace) {
        if (player1Place == heavenPlace) {
            System.out.println("Heaven exist.");
            System.out.println("");
            System.out.println("YOU WON.");
        } else {
            System.out.println("Heaven: -Not here-");
        }
    }//end of exitToHeaven method

}//end of class
