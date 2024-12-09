package SecondAndThirdProjectOfClass;

import java.util.Scanner;

public class RabbitGamePart2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("RULES:\n"
                + "1- Each carrots 5 point.(if you can feed the rabbits).\n"
                + "2- If you don't have enough carrot for rabit you lose 5 point.\n"
                + "3- There is a role that if you go [x.10] places you jumped [x.1] place. ([x.10] places are teleportatial.)\n"
                + "3- Each rats -5 point by carrot numbers.(if rat eat 2 carrot you lose 10 point).\n"
                + "5- If you don't have carrot and you saw rat don't worry you don't lose any point.\n"
                + "6- If you exit from the hell you directly lose.\n"
                + "7- If you exit from the heaven your points will exist.\n"
                + "8- Player start with 1 point.\n"
                + "9- There is a kid security for younger than (5y.o). \n"
                + "10- If someone arrive heaven he directly win. \n"
                + "11- If someone arrive hell he directly lose.");

        System.out.println("");
        System.out.println("Enter s number: ");
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

        String[] names = {"Player1", "Player2"};

        double[] players = new double[2];

        while (players[0] == players[1]) {//player1 and player2 not equal.
            for (int a = 0; a < 2; a++) {
                double i = (int) (Math.random() * (s) + 1);
                double j = (int) (Math.random() * ((2 * i) - 1) + 1);
                if (j >= 10) {//for second step [x.y]-> 0.y ex. 0.11
                    j = j / 100.0;
                } else {
                    j = j / 10.0;//for second step [x.y]-> 0.y ex. 0.7
                }
                players[a] = (i + j);//two different players[].

            }
        }
        String[] definers = {"rat1Place", "rat2Place", "rabit1Place", "rabit2Place", "rabit3Place", "heavenPlace", "hellPlace"};
        double[] items = new double[7];// Do until all items different
        while (items[0] == items[1] || items[0] == items[2] || items[0] == items[3] || items[0] == items[4] || items[0] == items[5] || items[0] == items[6]
                || items[1] == items[2] || items[1] == items[3] || items[1] == items[4] || items[1] == items[5] || items[1] == items[6] || items[2] == items[3]
                || items[2] == items[4] || items[2] == items[5] || items[2] == items[6] || items[3] == items[4] || items[3] == items[5]
                || items[3] == items[6] || items[4] == items[5] || items[4] == items[6] || items[5] == items[6]) {
            for (int i = 0; i < items.length; i++) {
                double randomI = (int) (Math.random() * (s) + 1);//[1,s]
                double randomJ = (int) (Math.random() * ((2 * randomI) - 1) + 1);//[1,j]
                if (randomJ >= 10) {
                    randomJ = randomJ / 100.0;//for second step [x.y]-> 0.y ex. 0.11 
                } else {
                    randomJ = randomJ / 10.0;//for second step [x.y]-> 0.y ex. 0.7
                }
                items[i] = (randomI + randomJ);//define

            }
        }
        //counters
        int count1 = 0;
        int count2 = 0;
        int point1 = 1;
        int point2 = 1;
        int countFeed1 = 0;
        int countFeed2 = 0;
        int visitRat1 = 0;
        int visitRat2 = 0;

        while (exit == 1) {

            System.out.println("Do you want to see any item?");
            System.out.println("Yes[5] / No[6]");
            int yesOrNo = scan.nextInt();
            if (yesOrNo == 5) {//You can see the item places when you are playing in whenever you want.
                System.out.println("[0]rat1Place / [1]rat2Place / [2]rabit1Place / [3]rabit2Place / [4]rabit3Place / [5]heavenPlace / [6]hellPlace");
                System.out.println("Which item do you want to see?");
                int a = scan.nextInt();

                
                System.out.println(definers[a] + ": [" + items[a] + "]");//which item you want

            } else {
                System.out.println("");
            }
            System.out.println("--------");
            for (int a = 0; a < 2; a++) {
                System.out.println(names[a] + ": [" + players[a] + "]");//player locations
            }

            System.out.println("Player1 carrots: " + count1);
            System.out.println("Player1 points: " + point1);
            System.out.println("--------");
            System.out.println("Player2 carrots: " + count2);
            System.out.println("Player2 points: " + point2);
            System.out.println("");
            areaOfGame(s);

            System.out.println("");
            System.out.println("For Player1: ");
            System.out.println("[2]: down, [4]: left, [6]: right, [8]: up");
            int selectMove = scan.nextInt();
            if (findWinner(items, personMove(s, players[0], selectMove), definers, point1, point2) == true) {
                System.out.println("");
                findCarrotCollector(count1, count2);//print who find most carrots
                findRabbitFeeder(countFeed1, countFeed2);//print who feed most rabbit
                findRatVisitor(visitRat1, visitRat2);//print who visit most rats
                exit = 0;//stop code
                break;
            } else if (hellPlace(items, personMove(s, players[0], selectMove), definers) == true) {
                System.out.println("PLAYER1 LOSE!!");
                System.out.println("");
                findCarrotCollector(count1, count2);
                findRabbitFeeder(countFeed1, countFeed2);
                findRatVisitor(visitRat1, visitRat2);
                exit = 0;
                break;
            } else {

                canMove(s, players[0], selectMove);//looking can you move

                //move
                double num = personMove(s, players[0], selectMove) % 1; //for ex. [3.2]%1-> 0.2 
                double num1 = personMove(s, players[0], selectMove) - num;//for ex.[3.2]-0.2->3.0
                //[3.2] -> 32%2->0         
                //if num1(3.0)%2->0 thats not correct so there i no carrot.
                type = (int) (Math.random() * 4);//[0,3]

                while ((10 * personMove(s, players[0], selectMove)) % 2 == 0 && num1 % 2 == 0) {//first condition looking first step 3.2->0.2 second condition looking first step 3.2 -> 3.0
                    System.out.println("Carrot exist.");
                    boolean answer2 = generateAQueastion(type);//question
                    if (answer2 == true) {
                        count1 += 1;//carrot value
                        point1 = 5 * count1;
                        System.out.println("You have " + count1 + " carrot.");
                        System.out.println("You won 5 point.");
                    }
                    break;

                }//end of loop

                if (personMove(s, players[0], selectMove) == items[2] || personMove(s, players[0], selectMove) == items[3]
                        || personMove(s, players[0], selectMove) == items[4]) {//there is a rabit
                    int numRab = (int) ((Math.random() * 3) + 1);//[1,3]ranndom
                    System.out.println("Rabbit exist");
                    System.out.println("rabbit want " + numRab + " carrot.");
                    if (count1 >= numRab) {//if you have enough carrot
                        countFeed1++;
                        point1 += numRab * 5;
                        System.out.println("You feed the rabbit.");
                        System.out.println("You won " + numRab * 5 + " point.");
                        count1 -= numRab;//carrots decreasing when rabits eat
                    } else {
                        System.out.println("You can't feed the rabit.");
                    }
                }
                if (personMove(s, players[0], selectMove) == items[0] || personMove(s, players[0], selectMove) == items[1]) {//there is a rat
                    System.out.println("Rat exist.");
                    visitRat1++;
                    int numRat = (int) ((Math.random() * 3) + 1);//[1,3]random
                    System.out.println("Rat want " + numRat + " carrot.");
                    if (count1 >= numRat) {//if you have enough carrot
                        point1 -= numRat * 5;
                        System.out.println("Rat eat your carrots.");
                        System.out.println("You lose " + numRat * 5 + " point.");
                        count1 -= numRat;//carrots decreasing when rabits eat
                    } else {
                        System.out.println("You have no carrot for rat you are luck.");
                        System.out.println("Rat didn't eat your carrots.");
                    }
                }
            }
            System.out.println("");
            System.out.println("For Player2: ");
            System.out.println("[2]: down, [4]: left, [6]: right, [8]: up");
            int selectMove1 = scan.nextInt();
            if (findWinner(items, personMove(s, players[1], selectMove1), definers, point1, point2) == true) {
                System.out.println("");
                findCarrotCollector(count1, count2);
                findRabbitFeeder(countFeed1, countFeed2);
                findRatVisitor(visitRat1, visitRat2);
                exit = 0;
                break;
            } else if (hellPlace(items, personMove(s, players[1], selectMove1), definers) == true) {
                System.out.println("PLAYER1 LOSE!!");
                System.out.println("");
                findCarrotCollector(count1, count2);
                findRabbitFeeder(countFeed1, countFeed2);
                findRatVisitor(visitRat1, visitRat2);
                exit = 0;
                break;
            } else {

                canMove(s, players[1], selectMove1);

                double num = personMove(s, players[1], selectMove) % 1; //for ex. [3.2]%1-> 0.2 
                double num1 = personMove(s, players[1], selectMove) - num;//for ex.[3.2]-0.2->3.0
                //[3.2] -> 32%2->0         
                //if num1(3.0)%2->0 thats not correct so there i no carrot.
                type = (int) (Math.random() * 4);//[0,3]

                while ((10 * personMove(s, players[1], selectMove)) % 2 == 0 && num1 % 2 == 0) {//first condition looking first step 3.2->0.2 second condition looking first step 3.2 -> 3.0
                    System.out.println("Carrot exist.");
                    boolean answer3 = generateAQueastion(type);
                    if (answer3 == true) {
                        count2 += 1;//carrot value
                        point2 = 5 * count2;
                        System.out.println("You have " + count2 + " carrot.");
                        System.out.println("You won 5 point.");
                    }
                    break;
                }//end of loop
                if (personMove(s, players[1], selectMove) == items[2] || personMove(s, players[1], selectMove) == items[3]
                        || personMove(s, players[1], selectMove) == items[4]) {//there is a rabit
                    int numRab = (int) ((Math.random() * 3) + 1);//[1,3]ranndom
                    System.out.println("Rabbit exist");
                    System.out.println("rabbit want " + numRab + " carrot.");
                    if (count2 >= numRab) {//if you have enough carrot
                        countFeed2++;
                        point2 += numRab * 5;
                        System.out.println("You feed the rabbit.");
                        System.out.println("You won " + numRab * 5 + " point.");
                        count1 -= numRab;//carrots decreasing when rabits eat
                    } else {
                        System.out.println("You can't feed the rabit.");
                    }
                }
                if (personMove(s, players[1], selectMove) == items[0] || personMove(s, players[1], selectMove) == items[1]) {//there is a rat
                    System.out.println("Rat exist.");
                    visitRat2++;
                    int numRat = (int) ((Math.random() * 3) + 1);//[1,3]random
                    System.out.println("Rat want " + numRat + " carrot.");
                    if (count2 >= numRat) {//if you have enough carrot
                        point2 -= numRat * 5;
                        System.out.println("Rat eat your carrots.");
                        System.out.println("You lose " + numRat * 5 + " point.");
                        count2 -= numRat;//carrots decreasing when rabits eat
                    } else {
                        System.out.println("You have no carrot for rat you are luck.");
                        System.out.println("Rat didn't eat your carrots.");
                    }
                }
            }

            players[0] = personMove(s, players[0], selectMove);
            players[1] = personMove(s, players[1], selectMove1);
            //gives new location for players

            System.out.println("Do you want to exit the game?");
            System.out.println("[0]Yes \n [1]No");
            exit = scan.nextInt();//exit code
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
                    }
                    break;

                }
            }
            System.out.println("");//for jump line
        }
    }//end of areaOfGame method

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

                    if (j == ((2 * i) - 1) || j == 1) {
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

                    if (j == ((2 * i) - 1) || j == 1) {
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

        for (int a = 0; a < 2; a++) {

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
        }
        return p1;

    }//end of personMoves method

    public static boolean findWinner(double[] heaven, double person, String[] define, int point1, int point2) {

        if (person == heaven[5]) {
            if (point1 > point2) {
                System.out.println("PLAYER1 WİN!!\n");
                System.out.println("Player1 points: " + point1);
                System.out.println("Player2 points: " + point2);
            } else if (point1 < point2) {
                System.out.println("PLAYER2 WİN!!");
                System.out.println("Player1 points: " + point1);
                System.out.println("Player2 points: " + point2);
            } else {
                System.out.println("DRAW..");
                System.out.println("Player1 points: " + point1);
                System.out.println("Player2 points: " + point2);
            }
            System.out.println("[" + person + "]");
            System.out.println("Heaven exist.");

            System.out.println("");
            System.out.println("ITEMS: ");
            System.out.println("");
            for (int a = 0; a < 7; a++) {
                System.out.println(define[a] + ": [" + heaven[a] + "]");
            }

            return true;
        } else {
            System.out.println("[" + person + "]");
            System.out.println("Player1 points: " + point1);
            System.out.println("Player2 points: " + point2);
            return false;
        }

    }//end of findWinner method

    public static boolean hellPlace(double[] hell, double person, String[] define) {
        if (person == hell[6]) {
            System.out.println("[" + person + "]");

            System.out.println("Hell exist.");

            System.out.println("");
            System.out.println("ITEMS: ");
            System.out.println("");
            for (int a = 0; a < 7; a++) {
                System.out.println(define[a] + ": [" + hell[a] + "]");//print all items
            }
            return true;
        } else {
            System.out.println("");
            return false;
        }
    }//end of hellPlace method

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

    public static void findCarrotCollector(int count1, int count2) {
        if (count2 > count1) {
            System.out.println("Player2 has the most carrots.");
            System.out.println("Player1 carrots: " + count1);
            System.out.println("Player2 carrots: " + count2);
        } else if (count1 > count2) {
            System.out.println("Player1 has the most carrots.");
            System.out.println("Player1 carrots: " + count1);
            System.out.println("Player2 carrots: " + count2);
        } else {
            System.out.println("Player1 and Player2 has equal number of carrots.");
            System.out.println("Player1 carrots: " + count1);
            System.out.println("Player2 carrots: " + count2);
        }

    }//end of findCarrotCollector

    public static void findRabbitFeeder(int countFeed1, int countFeed2) {
        if (countFeed1 > countFeed2) {
            System.out.println("Player1 has fed the most amount of individual rabbits.");
            System.out.println("Player1 feed rabits: " + countFeed1);
            System.out.println("Player2 feed rabits: " + countFeed2);
        }
        if (countFeed1 < countFeed2) {
            System.out.println("player2 has fed the most amount of individual rabbits.");
            System.out.println("Player1 feed rabits: " + countFeed1);
            System.out.println("Player2 feed rabits: " + countFeed2);
        } else {
            System.out.println("player1 and player2 has fed same amount of individual rabbits.");
            System.out.println("Player1 feed rabits: " + countFeed1);
            System.out.println("Player2 feed rabits: " + countFeed2);
        }
    }//end of findRabbitFeeder

    public static void findRatVisitor(int visitRat1, int visitRat2) {
        if (visitRat1 > visitRat2) {
            System.out.println("Player1 visits rats the most.");
            System.out.println("Player1 visits rats: " + visitRat1);
            System.out.println("Player2 visits rats: " + visitRat2);
        } else if (visitRat1 < visitRat2) {
            System.out.println("Player2 visits rats the most.");
            System.out.println("Player1 visits rats: " + visitRat1);
            System.out.println("Player2 visits rats: " + visitRat2);
        } else {
            System.out.println("Player2 and player1 has visits rats same.");
            System.out.println("Player1 visits rats: " + visitRat1);
            System.out.println("Player2 visits rats: " + visitRat2);
        }
    }//end of findRatVisitor

}//end of class
