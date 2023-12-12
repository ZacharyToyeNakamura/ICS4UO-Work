// Exercises 5.3 Interfaces – Comparable
// https://docs.google.com/document/d/1pd8ztiDuDCfW-8jFKAnuAxKT2kaGRmqpJqq-ir4CDCc/edit
// Lesson
// https://docs.google.com/document/d/1XJ8x7aXzIJGzzTNefbzNOXkCHlDXPfHarkKGl-d3axU/edit

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

 public class Main {
    public static final String salt = "qwertyuiopasdfghjklzxcvbnm";
    public static final Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("Started.");
        ArrayList<Person> ppl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String newLastName = "", newFirstName = "";
            for (int j = 0; j < 10; j++) {
                newLastName += salt.charAt(Math.abs(rand.nextInt() % salt.length()));
                newFirstName += salt.charAt(Math.abs(rand.nextInt() % salt.length()));
            }
            ppl.add(new Person(newFirstName, newLastName, Math.abs(rand.nextInt() % 100)));
        }
        Collections.sort(ppl);
//        for (int i = 0; i < ppl.size(); i++) System.out.println(ppl.get(i));



        // Part 2
        Die d1 = new Die(6), d2 = new Die(6);
        d1.roll(); d2.roll();
        while (d1.getValue() != d2.getValue()) {
            System.out.println("You rolled " + d1.getValue() + " and "  + d2.getValue() + ". Try Again!");
            d1.roll();
            d2.roll();
        }
        System.out.println("You got doubles: " +  d1.getValue() + ", " + d2.getValue());



        // Part 2b
        final int NUM_GAMES = 30;
        ArrayList<Die> p1Dice = new ArrayList<>(), p2Dice = new ArrayList<>();
        for (int i = 0; i < NUM_GAMES; i++) {
            p1Dice.add(new Die(20));
            p1Dice.get(i).roll();
            p2Dice.add(new Die(20));
            p2Dice.get(i).roll();
        }
        Collections.sort(p1Dice);
        Collections.sort(p2Dice);
        int p1Points = 0, draws = 0;
        for (int i = 0; i < NUM_GAMES; i++) {
            if(p1Dice.get(i).compareTo(p2Dice.get(i)) > 0) p1Points++;
            else if(p1Dice.get(i).compareTo(p2Dice.get(i)) == 0) draws++;
        }
        System.out.println("Results: \n P1 P2");
        for (int i = 0; i < NUM_GAMES; i++) {
            System.out.println(" " + p1Dice.get(i).getValue() + " " + p2Dice.get(i).getValue());
        }
        System.out.println("Player 1 Points: " + p1Points);
        System.out.println("Player 2 Points: " + (NUM_GAMES - p1Points - draws));
        if (p1Points > NUM_GAMES - p1Points - draws) System.out.println("Player 1 Wins!");
        else if (p1Points == NUM_GAMES - p1Points - draws) System.out.println("Tie!");
        else System.out.println("Player 2 Wins!");




    }
}